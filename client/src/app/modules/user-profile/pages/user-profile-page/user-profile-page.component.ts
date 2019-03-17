import {Component, OnInit} from "@angular/core";
import {UserService} from "../../../../services/user/user.service";
import {ChangePasswordRequest, User} from "../../../../../models/user/user.models";

@Component({
    selector: "mj-user-profile-page",
    templateUrl: "./user-profile-page.component.html",
    styleUrls: ["./user-profile-page.component.scss"]
})
export class UserProfilePageComponent implements OnInit {

    public user: User;
    public validation = {
        old: "",
        p1: "",
        p2: ""
    };
    public passwordChangeRequest = new ChangePasswordRequest();

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.user = User.empty();
        this.getUserProfile();
    }

    public changePassword(): void {
        if (this.validatePassword()) {
            this.userService.changePassword(this.passwordChangeRequest).subscribe(
                () => {
                    this.passwordChangeRequest = new ChangePasswordRequest();
                },
                (err) => {
                    console.error(err);
                }
            );
        }
    }

    public validatePassword(): boolean {
        this.validation = {
            old: "",
            p1: "",
            p2: ""
        };
        let result = true;
        if (!this.passwordChangeRequest.password_1) {
            this.validation.p1 = "Field must not be empty!";
            result = result && false;
        }
        if (!this.passwordChangeRequest.password_2) {
            this.validation.p2 = "Field must not be empty!";
            result = result && false;
        }
        if (!this.passwordChangeRequest.oldPassword) {
            this.validation.old = "Field must not be empty!";
            result = result && false;
        }
        if (this.passwordChangeRequest.password_1 && this.passwordChangeRequest.password_1.length < 5) {
            this.validation.p1 = "Passwords must be at least 5 characters long!";
            result = result && false;
        }
        if (this.passwordChangeRequest.password_1 !== this.passwordChangeRequest.password_2) {
            this.validation.p1 = "Passwords must match!";
            this.validation.p2 = "Passwords must match!";
            result = result && false;
        }
        return result;
    }

    private getUserProfile(): void {
        this.userService.getUserProfile().subscribe(
            (currentUser: User) => {
                this.user = currentUser;
            },
            (err) => {
                console.error(err);
            }
        );
    }

}
