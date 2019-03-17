import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {StyleUtil} from "../../../utils/style.util";
import {MEDIA_PHONE} from "../../../../styles/styles";
import {AuthService} from "../../../services/auth/auth.service";
import {UserRoles} from "../../../services/auth/roles";

@Component({
    selector: "mj-header",
    templateUrl: "./header.component.html",
    styleUrls: ["./header.component.scss"]
})
export class HeaderComponent implements OnInit {

    @ViewChild("navMenu")
    private navMenuRef: ElementRef<HTMLDivElement>;

    public isAuthenticated = false;
    public hasAdminRights = false;

    constructor(private auth: AuthService) {
    }

    ngOnInit() {
        this.isAuthenticated = this.auth.isAuthenticated();
        this.hasAdminRights = this.auth.userHasRole(UserRoles.ADMIN);
    }

    public toggleMobileMenu(): void {
        if (this.navMenuRef.nativeElement.clientHeight === 0) {
            this.navMenuRef.nativeElement.style.height = `${this.navMenuRef.nativeElement.scrollHeight}px`;
        } else {
            this.navMenuRef.nativeElement.style.height = "0px";
        }
    }

    public closeMobileMenuOnNavigation(): void {
        if (this.mobileMenuIsVisible()) {
            this.navMenuRef.nativeElement.style.height = "0px";
        }
    }

    private mobileMenuIsVisible(): boolean {
        const screenWidth = StyleUtil.determineScreenWidth();
        return screenWidth < MEDIA_PHONE;
    }

}
