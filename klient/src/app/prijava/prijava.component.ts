import {Component, OnInit} from "@angular/core";
import {PrijavaRequest} from "../models/requests/PrijavaRequest";
import {AuthService} from "../services/auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: "app-login-page",
    templateUrl: "prijava.component.html",
    styleUrls: ["./prijava.component.css"]
})
export class PrijavaComponent implements OnInit {
    uporabnik: PrijavaRequest;
    napaka: string;
    prev_page: string;

    constructor(private authService: AuthService, private router: Router,
                private route: ActivatedRoute) {

    }

    ngOnInit(): void {
        this.uporabnik = new PrijavaRequest();
        this.napaka = null;
        this.route.queryParams.subscribe(params => {
            this.prev_page = params["return"] || "/";
        });
    }

    prijaviUporabnika(): void {
        this.authService.prijaviUporabnika(this.uporabnik)
            .then(res => {
                this.router.navigateByUrl(this.prev_page);
                },
                err => {
                if (err.status === 401) {
                    this.napaka = "Napačen e-mail in/ali geslo!";
                } else {
                    console.error("Prišlo je do napake!", err);
                    this.napaka = "Napaka pri prijavi!";
                }
            });
    }
}
