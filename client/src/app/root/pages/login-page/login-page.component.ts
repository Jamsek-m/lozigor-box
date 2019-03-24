import {Component, OnInit} from "@angular/core";
import {LoginCredentials} from "../../../../models/auth/auth.models";
import {AuthService} from "../../../services/auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: "mj-login-page",
    templateUrl: "./login-page.component.html",
    styleUrls: ["./login-page.component.scss"]
})
export class LoginPageComponent implements OnInit {

    public credentials = new LoginCredentials();

    public error = "";

    private returnUrl = "/";

    constructor(private auth: AuthService, private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        if (this.auth.isAuthenticated()) {
            this.router.navigate(["/"]);
        }
        this.activatedRoute.queryParams.subscribe(params => {
            this.returnUrl = params["return"] || "/";
        });
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
                    this.router.navigate([this.returnUrl]);
                } else {
                    this.error = "Wrong username and/or password!";
                }
            }
        );
    }

}
