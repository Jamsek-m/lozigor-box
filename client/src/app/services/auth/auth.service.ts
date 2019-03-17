import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {AuthData, AuthRole, AuthTokenPayload, AuthTokenRefreshResponse, LoginCredentials} from "../../../models/auth/auth.models";
import {environment} from "../../../environments/environment";
import {Observable, of} from "rxjs";
import {catchError, map, tap} from "rxjs/operators";
import {HttpStatus} from "../../../constants/http.status";


@Injectable({
    providedIn: "root"
})
export class AuthService {

    private static auth: AuthData;

    private v1Api = `${environment.apiV1Url}/auth`;

    constructor(private http: HttpClient) {

    }

    /**
     * Non-CDI method for restoring user session. To be used at application bootstrap
     */
    public static initializeAuthentication(): Promise<boolean> {
        return new Promise(resolve => {
            const url = `${environment.apiV1Url}/auth/token`;
            const request = new XMLHttpRequest();
            request.open("POST", url, true);
            request.setRequestHeader("Content-Type", "application/json");
            request.withCredentials = true;
            request.addEventListener("load", () => {
                if (request.status === HttpStatus.OK) {
                    const response = JSON.parse(request.responseText) as AuthTokenRefreshResponse;
                    AuthService.auth = AuthData.fromResponse(response);
                    resolve(true);
                } else {
                    resolve(false);
                }

            });
            request.addEventListener("error", () => {
                resolve(false);
            });
            request.send();
        });
    }

    /**
     * If session is still active, retrieves token and sets auth data and returns true, otherwise returns false
     */
    public restoreSession(): Observable<boolean> {
        const url = `${this.v1Api}/token`;
        return this.http.post(url, null, {withCredentials: true}).pipe(
            map(res => res as AuthTokenRefreshResponse),
            tap((res: AuthTokenRefreshResponse) => {
                AuthService.auth = AuthData.fromResponse(res);
            }),
            map((res: AuthTokenRefreshResponse) => {
                return true;
            }),
            catchError((err: HttpErrorResponse) => {
                if (err.status === HttpStatus.UNAUTHORIZED) {
                    return of(false);
                }
                // handle other type of error?
                return of(false);
            })
        );
    }

    public refreshToken(): Promise<void> {
        const refreshMilisecondsBeforeExpiry = 30000;
        return new Promise((resolve, reject) => {
            const payload = this.getTokenPayload();
            if (!payload) {
                reject();
            }

            const now = new Date();
            const timeUntilExpiry = payload.expiresAt.getTime() - now.getTime();
            if (timeUntilExpiry <= refreshMilisecondsBeforeExpiry) {
                // token is about to expire, get new one
                this.restoreSession().subscribe(
                    (sessionRefreshed: boolean) => {
                        if (sessionRefreshed) {
                            // session was restored and new token was obtained
                            resolve();
                        } else {
                            // session couldn't be restored, no token received
                            reject();
                        }
                    }
                );
            } else {
                // token is not yet expired
                resolve();
            }
        });
    }

    /**
     * Passes credentials to server, which starts new session and returns access token
     * @param credentials user credentials to be exchanged for token
     */
    public login(credentials: LoginCredentials): Observable<boolean> {
        const url = `${this.v1Api}/login`;
        return this.http.post(url, JSON.stringify(credentials), {withCredentials: true}).pipe(
            map(res => {
                return res as AuthTokenRefreshResponse;
            }),
            tap((res: AuthTokenRefreshResponse) => {
                AuthService.auth = AuthData.fromResponse(res);
            }),
            map((res: AuthTokenRefreshResponse) => {
                return true;
            }),
            catchError((err) => {
                /*if (err.status === HttpStatus.UNAUTHORIZED) {
                    return of(false);
                }*/
                // handle other type of error?
                return of(false);
            })
        );
    }

    /**
     * Destroys session and deletes token
     */
    public logout(): Observable<void> {
        const url = `${this.v1Api}/logout`;
        return this.http.get(url, {withCredentials: true}).pipe(
            tap(() => {
                AuthService.auth = null;
            }),
            catchError((err: HttpErrorResponse) => {
                console.error(err);
                return of(null);
            })
        );
    }

    public isAuthenticated(): boolean {
        if (AuthService.auth && AuthService.auth.authenticated) {
            return AuthService.auth.authenticated;
        }
        return false;
    }

    public getUsername(): string {
        if (AuthService.auth && AuthService.auth.payload) {
            return AuthService.auth.payload.username;
        }
        return "Anon";
    }

    public getUserId(): number {
        if (AuthService.auth && AuthService.auth.payload) {
            return AuthService.auth.payload.subject;
        }
        return -1;
    }

    public getUserRoles(): AuthRole[] {
        if (AuthService.auth && AuthService.auth.payload) {
            return AuthService.auth.payload.roles;
        }
        return [];
    }

    public userHasRole(role: string): boolean {
        return !!this.getUserRoles().find(userRole => userRole.code === role);
    }

    public getTokenPayload(): AuthTokenPayload {
        if (AuthService.auth && AuthService.auth.payload) {
            return AuthService.auth.payload;
        }
        return null;
    }

    public getToken(): string {
        if (AuthService.auth && AuthService.auth.accessToken) {
            return AuthService.auth.accessToken;
        }
        return null;
    }

}
