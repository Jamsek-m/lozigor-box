import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";


import {OgrodjeComponent} from "./ogrodje/ogrodje.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {IndexComponent} from "./index/index.component";
import {AppRoutingModule} from "./app-routing.module";
import {BootstrapModule} from "./bootstrap.module";
import {PrijavaComponent} from "./prijava/prijava.component";
import {AuthService} from "./services/auth.service";
import {AuthGuard} from "./services/auth.guard";


@NgModule({
    declarations: [
        OgrodjeComponent,
        // krmilniki
        IndexComponent,
        PrijavaComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        BootstrapModule
    ],
    providers: [AuthService, AuthGuard],
    bootstrap: [OgrodjeComponent]
})
export class AppModule {
}
