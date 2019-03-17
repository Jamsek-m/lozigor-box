import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {from, Observable} from "rxjs";
import {AuthService} from "./auth.service";
import {switchMap} from "rxjs/operators";
import {environment} from "../../../environments/environment";


@Injectable({
    providedIn: "root"
})
export class AuthInterceptor implements HttpInterceptor {

    constructor(private auth: AuthService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (this.isUrlOnBlacklist(req.url)) {
            return next.handle(req);
        }

        return from(new Promise((resolve, reject) => {
            this.auth.refreshToken().then(
                () => {
                    resolve();
                }).catch(() => {
                this.auth.logout();
                reject();
            });
        })).pipe(
            switchMap(() => {
                const token = this.auth.getToken();
                if (token !== null) {
                    req = req.clone({
                        headers: req.headers.set("Authorization", `Bearer ${token}`)
                    });
                }
                return next.handle(req);
            })
        );
    }

    private isUrlOnBlacklist(url: string) {
        url = url.replace(environment.apiV1Url, "");
        if (url.startsWith("/auth/")) {
            return true;
        }
        return false;
    }

}
