import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";

import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {AuthService} from "./auth.service";
import {TrenutniUporabnik} from "../../models/token/TrenutniUporabnik";

@Injectable()
export class AdminRoleGuard implements CanActivate {
    private SIFRA = "ADMIN";

    constructor(private auth: AuthService, private router: Router) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        const trenutniUporabnik: TrenutniUporabnik = this.auth.pridobiTrenutnegaUporabnika();
        if (trenutniUporabnik === null) {
            this.router.navigate(["/prijava"], {
                queryParams: {"return": state.url}
            });
        } else {
            const vloge = trenutniUporabnik.vloge
                .map(item => item.sifra)
                .filter(item => item === this.SIFRA);
            if (vloge.length === 1) {
                return true;
            } else {
                this.router.navigate(["/401"]);
            }
        }
        return false;
    }
}
