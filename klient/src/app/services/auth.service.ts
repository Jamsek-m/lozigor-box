import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from "@angular/common/http";
import {PrijavaRequest} from "../models/requests/PrijavaRequest";

import "rxjs/add/operator/toPromise";
import {TokenData} from "../models/token/TokenData";
import {Observable} from "rxjs/Observable";
import {TrenutniUporabnik} from "../models/token/TrenutniUporabnik";

@Injectable()
export class AuthService {
    private TOKEN_STORAGE_NAME = "lozigor-zeton";
    private URL_AVTENTIKACIJA = "http://localhost:8080/api/v1/prijava";
    private headers = new HttpHeaders({"Content-Type": "application/json"});

    constructor(private http: HttpClient) {
        
    }

    public static b64ToUtf8(niz: string): string {
        return decodeURIComponent(Array.prototype.map.call(atob(niz), (c) => {
            return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(""));
    }

    private static parseZeton(token: string) {
        return JSON.parse(AuthService.b64ToUtf8(token.split(".")[1]));
    }

    public shraniZeton(zeton: string): void {
        localStorage.setItem(this.TOKEN_STORAGE_NAME, zeton);
    }

    public vrniZeton(): string {
        return localStorage.getItem(this.TOKEN_STORAGE_NAME);
    }

    public prijaviUporabnika(uporabnik: PrijavaRequest) {
        const podatki = JSON.stringify(uporabnik);
        return this.http
            .post(this.URL_AVTENTIKACIJA, podatki, {headers: this.headers})
            .toPromise()
            .then(
                res => {
                    this.shraniZeton(res["zeton"]);
                }
            );
    }

    public jePrijavljen(): boolean {
        const zeton: string = this.vrniZeton();
        if (zeton) {
            const vsebina: TokenData = AuthService.parseZeton(zeton);
           return vsebina.exp > Date.now() / 1000;
        } else {
            return false;
        }
    }

    public pridobiTrenutnegaUporabnika(): TrenutniUporabnik {
        if (this.jePrijavljen()) {
            const zeton = this.vrniZeton();
            const vsebina: TokenData = AuthService.parseZeton(zeton);
            return {
                id: vsebina.uporabnik_id,
                ime: vsebina.uporabnik_ime,
                vloge: vsebina.vloge
            };
        } else {
            return null;
        }
    }

    private handleError(err: HttpErrorResponse | any) {
        console.error("Prišlo je od napake! ", err);
        return Observable.throw(err.name || err);
    }

}
