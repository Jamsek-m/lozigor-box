import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {ChangePasswordRequest, User} from "../../../models/user/user.models";
import {map} from "rxjs/operators";

@Injectable({
    providedIn: "root"
})
export class UserService {

    private v1ApiUrl = `${environment.apiV1Url}/users`;

    constructor(private http: HttpClient) {

    }

    public getUserProfile(): Observable<User> {
        const url = `${this.v1ApiUrl}/profile`;
        return this.http.get(url).pipe(map(res => res as User));
    }

    public changePassword(request: ChangePasswordRequest): Observable<void> {
        const url = `${this.v1ApiUrl}/change-password`;
        return this.http.patch(url, JSON.stringify(request)).pipe(map(res => null));
    }

}
