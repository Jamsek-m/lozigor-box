import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";
import {Uporabnik} from "../../models/data/uporabnik";
import {UporabnikUpdateRequest} from "../../models/requests/UporabnikUpdateRequest";


@Injectable()
export class UporabnikiService {
    private URL_UPORABNIKI = "http://localhost:8080/api/v1/uporabniki";


    constructor(private http: HttpClient, private auth: AuthService) {

    }

    public pridobiSvojProfil(): Promise<any> {
        const url = `${this.URL_UPORABNIKI}/profil`;
        return this.http.get(url, {headers: this.generateHeaders()})
            .toPromise()
            .then(res => res as Uporabnik)
            .catch(err => {
                console.error("NAPAKA! ", err);
            });
    }

    public posodobiSvojProfil(uporabnik: UporabnikUpdateRequest): Promise<any> {
        const url = `${this.URL_UPORABNIKI}/profil`;
        const data = JSON.stringify(uporabnik);
        return this.http.put(url, data, {headers: this.generateHeaders()})
            .toPromise()
            .then();
    }

    private generateHeaders(): HttpHeaders {
        return new HttpHeaders({
            "Content-Type": "application/json",
            "Authorization": "Bearer " + this.auth.vrniZeton()
        });
    }

}
