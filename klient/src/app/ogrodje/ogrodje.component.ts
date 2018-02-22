import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";
import {Router} from "@angular/router";
import {NavbarService} from "../services/navbar.service";
import {TrenutniUporabnik} from "../models/token/TrenutniUporabnik";
import {EmitTrenutniUporabnik} from "../models/emitters/EmitTrenutniUporabnik";

@Component({
    selector: "app-root",
    templateUrl: "./ogrodje.component.html",
    styleUrls: ["./ogrodje.component.css"]
})
export class OgrodjeComponent implements OnInit {
    dropdownCollapsed: boolean;
    menuCollapsed: boolean;
    prijavljen: boolean;
    trenutniUporabnik: TrenutniUporabnik;
    subscription;

    constructor(private auth: AuthService, private router: Router, private navbar: NavbarService) {

    }

    ngOnInit(): void {
        this.subscription = this.navbar.getEmittedValueJePrijavljen()
            .subscribe((item: EmitTrenutniUporabnik) => {
                this.prijavljen = item.jePrijavljen;
                this.trenutniUporabnik = item.trenutniUporabnik;
            });
        this.dropdownCollapsed = true;
        this.menuCollapsed = true;
        this.prijavljen = this.auth.jePrijavljen();
        this.trenutniUporabnik = this.auth.pridobiTrenutnegaUporabnika();
    }

    odjava(): void {
        this.auth.odjaviUporabnika();
        this.prijavljen = false;
        if (this.router.url === "/") {
            window.location.reload();
        } else {
            this.router.navigate(["/"]);
        }
    }

    linkProfil(): void {
        this.router.navigate(["/profil"]);
    }

    linkManage(): void {
        this.router.navigate(["/manage"]);
    }

    linkHome(): void {
        this.router.navigate(["/"]);
    }

    linkPrijava(): void {
        this.router.navigate(["/prijava"]);
    }
}
