import {Component, OnInit} from "@angular/core";
import {UporabnikiService} from "../services/uporabniki/uporabniki.service";
import {Uporabnik} from "../models/data/uporabnik";
import {UporabnikUpdateRequest} from "../models/requests/UporabnikUpdateRequest";
import {Router} from "@angular/router";
import {Vloga} from "../models/token/TokenData";

@Component({
    moduleId: module.id,
    selector: "app-profil-page",
    templateUrl: "profil.component.html",
    styleUrls: ["profil.component.css"]
})
export class ProfilComponent implements OnInit {
    profil: UporabnikUpdateRequest = new UporabnikUpdateRequest();
    sporociloNapaka: string;
    prikaziUspeh: boolean;
    vloge: string;

    constructor(private userService: UporabnikiService, private router: Router) {

    }

    ngOnInit(): void {
        this.pridobiSvojePodatke();
        this.sporociloNapaka = null;
        this.prikaziUspeh = false;
    }

    pridobiSvojePodatke() {
        this.userService.pridobiSvojProfil().then((uporabnik: Uporabnik) => {
            const profilUporabnik: UporabnikUpdateRequest = new UporabnikUpdateRequest();
            profilUporabnik.email = uporabnik.email;
            profilUporabnik.geslo1 = "";
            profilUporabnik.geslo2 = "";
            profilUporabnik.id = uporabnik.id;
            profilUporabnik.vloge = uporabnik.vloge;
            profilUporabnik.uporabniskoIme = uporabnik.uporabniskoIme;
            this.profil = profilUporabnik;
            this.vloge = `[ ${profilUporabnik.vloge.map((vloga: Vloga) => vloga.naziv).join(", ")} ]`;
        });
    }

    posodobiProfil(): void {
        this.prikaziUspeh = false;
        this.sporociloNapaka = null;

        if (!this.validirajGesla()) {
            this.gesliNistaEnaki();
            return;
        }
        this.userService.posodobiSvojProfil(this.profil)
            .then(() => {
                    this.prikaziUspeh = true;
            },
                err => {
                switch (err.status) {
                    case 400:
                        this.sporociloNapaka = "Nekatera polja so nepopolna!";
                        break;
                    case 409:
                        this.sporociloNapaka = "E-mail že obstaja!";
                        break;
                    default:
                        this.sporociloNapaka = "Napaka pri posodobitvi profila!";
                        break;
                }
            });
    }

    reset(): void {
        this.pridobiSvojePodatke();
        this.sporociloNapaka = null;
        this.prikaziUspeh = false;
    }

    gesliNistaEnaki(): void {
        this.sporociloNapaka = "Gesli se ne ujemata!";
    }

    validirajGesla(): boolean {
        const g1 = this.profil.geslo1;
        const g2 = this.profil.geslo2;
        if (!g1 && !g2) {
            return true;
        } else if (g1 === g2) {
            return true;
        }
        return false;
    }

    domov(): void {
        this.router.navigate(["/"]);
    }

}
