import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {UserProfileRoutingModule} from "./user-profile-routing.module";
import {UserProfilePageComponent} from "./pages/user-profile-page/user-profile-page.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
    declarations: [UserProfilePageComponent],
    imports: [
        CommonModule,
        UserProfileRoutingModule,
        HttpClientModule,
        FormsModule
    ]
})
export class UserProfileModule {
}
