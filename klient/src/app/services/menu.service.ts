import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth/auth.service";
import {MenuItem} from "../models/data/MenuItem";
import {MenuItemResponse} from "../models/responses/MenuItemResponse";

@Injectable()
export class MenuService {
    private URL_MENU = "http://localhost:8080/api/v1/menu";


    constructor(private http: HttpClient, private auth: AuthService) {

    }

    // TODO: dokoncaj

    public pridobiKorenskoMapo(): Promise<any> {
        const url = `${this.URL_MENU}/root`;
        return this.http.get(url, {headers: this.generirajToken()})
            .toPromise()
            .then(res => res as MenuItemResponse)
            .catch(err => console.log("NAPAKA: ", err));
    }

    pridobiVsebinoMape(parent_id: number): Promise<any> {
        const url = `${this.URL_MENU}/dir/${parent_id}`;
        return this.http.get(url, {headers: this.generirajToken()})
            .toPromise()
            .then(res => res as MenuItemResponse)
            .catch(err => console.log("NAPAKA: ", err));

    }

    poisciZQueryjem(query: string): Promise<any> {
        const url = `${this.URL_MENU}/dir`;
        return this.http.get(url, {params: {query: query}, headers: this.generirajToken()})
            .toPromise()
            .then(res => res as MenuItem[])
            .catch(err => console.log("NAPAKA: ", err));
    }

    private generirajToken(): HttpHeaders {
        return new HttpHeaders({
            "Content-Type": "application/json",
            "Authorization": "Bearer " + this.auth.vrniZeton()
        });
    }


}
