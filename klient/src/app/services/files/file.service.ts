import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";

@Injectable()
export class FileService {
    private URL_FILES = "http://localhost:8080/api/v1/datoteke";


    constructor(private http: HttpClient, private auth: AuthService) {

    }

    public pridobiFile(id: number) {
        const url = `${this.URL_FILES}/${id}`;
        return this.http.get(url, {
            headers: this.generirajToken(),
            responseType: "blob"
        });
    }

    private generirajToken(): HttpHeaders {
        return new HttpHeaders({
            "Content-Type": "application/json",
            "Authorization": "Bearer " + this.auth.vrniZeton()
        });
    }



}
