import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {TrenutniUporabnik} from "../models/token/TrenutniUporabnik";

@Component({
    moduleId: module.id,
    selector: "app-index-page",
    templateUrl: "index.component.html"
})
export class IndexComponent implements OnInit {
    sporocilo: String;

    constructor(private auth: AuthService, private router: Router) {}

    ngOnInit(): void {
        let msg = "Hello ";
        if (this.auth.jePrijavljen()) {
            const currUser: TrenutniUporabnik = this.auth.pridobiTrenutnegaUporabnika();
            msg += currUser.ime;
        } else {
            msg += "svet!";
        }
        this.nastaviSporocilo(msg);
    }

    nastaviSporocilo(msg: string) {
        this.sporocilo = msg;
    }

    preusmeriNaPrijavo() {
        this.router.navigate(["/prijava"]);
    }
}
