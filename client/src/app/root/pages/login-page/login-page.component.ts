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

    public error = "";

    constructor(private auth: AuthService, private router: Router) {
    }

    ngOnInit() {
        if (this.auth.isAuthenticated()) {
            this.router.navigate(["/"]);
        }
    }

    public login() {
        this.error = "";
        if (!this.credentials.password || !this.credentials.username) {
            this.error = "Fields must not be empty!";
            return;
        }
        this.auth.login(this.credentials).subscribe(
            (sessionStarted: boolean) => {
                if (sessionStarted) {
                    this.auth.notifyAuthChange(true);
                    this.router.navigate(["/"]);
                } else {
                    this.error = "Wrong username and/or password!";
                }
            }
        );
    }

}
