import {Component, OnInit} from "@angular/core";
import {LoginCredentials} from "../../../../models/auth/auth.models";
import {AuthService} from "../../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: "mj-login-page",
    templateUrl: "./login-page.component.html",
    styleUrls: ["./login-page.component.scss"]
})
export class LoginPageComponent implements OnInit {

    public credentials = new LoginCredentials();

    constructor(private auth: AuthService, private router: Router) {
    }

    ngOnInit() {
        if (this.auth.isAuthenticated()) {
            this.router.navigate(["/"]);
        }
    }

    public login() {
        if (!this.credentials.password || !this.credentials.username) {
            alert("Empty credentials!");
            return;
        }
        this.auth.login(this.credentials).subscribe(
            (sessionStarted: boolean) => {
                if (sessionStarted) {
                    this.router.navigate(["/"]);
                } else {
                    alert("Wrong credentials!");
                }
            }
        );
    }

}
