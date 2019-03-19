import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {StyleUtil} from "../../../utils/style.util";
import {MEDIA_PHONE} from "../../../../styles/styles";
import {AuthService} from "../../../services/auth/auth.service";
import {UserRoles} from "../../../services/auth/roles";
import {Router} from "@angular/router";

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

    constructor(private auth: AuthService, private router: Router) {
    }

    ngOnInit() {
        this.initializeComponentData();
        this.auth.authChangeSubscription().subscribe(
            (isAuthenticated: boolean) => {
                this.initializeComponentData();
            }
        );
    }

    private initializeComponentData(): void {
        this.isAuthenticated = this.auth.isAuthenticated();
        this.hasAdminRights = this.auth.userHasRole(UserRoles.ADMIN);
    }

    public logout(): void {
        this.auth.logout().subscribe();
        this.router.navigateByUrl("/login");
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
