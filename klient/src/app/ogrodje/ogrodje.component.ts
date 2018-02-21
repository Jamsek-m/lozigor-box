import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: "app-root",
    templateUrl: "./ogrodje.component.html",
    styleUrls: ["./ogrodje.component.css"]
})
export class OgrodjeComponent implements OnInit {
    dropdownCollapsed: boolean;
    menuCollapsed: boolean;
    prijavljen: boolean;

    constructor(private auth: AuthService, private router: Router) {

    }

    ngOnInit(): void {
        this.dropdownCollapsed = true;
        this.menuCollapsed = true;
        this.prijavljen = this.auth.jePrijavljen();
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
}
