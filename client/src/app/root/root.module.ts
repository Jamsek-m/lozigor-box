import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {RootRoutingModule} from "./root-routing.module";
import {RouterModule} from "@angular/router";
import {RootLayoutComponent} from "./root-layout/root-layout.component";
import {Error404PageComponent} from "./pages/error404-page/error404-page.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {ContentTypeInterceptor} from "../services/content-type.interceptor";
import {AuthInterceptor} from "../services/auth/auth.interceptor";
import {HeaderComponent} from "./layout/header/header.component";
import {IconsModule} from "./icons.module";
import {SharedModule} from "../shared/shared.module";

@NgModule({
    declarations: [
        RootLayoutComponent,
        Error404PageComponent,
        LoginPageComponent,
        HeaderComponent
    ],
    imports: [
        CommonModule,
        RootRoutingModule,
        HttpClientModule,
        FormsModule,
        IconsModule,
        SharedModule
    ],
    exports: [
        RouterModule
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            multi: true,
            useClass: ContentTypeInterceptor
        },
        {
            provide: HTTP_INTERCEPTORS,
            multi: true,
            useClass: AuthInterceptor
        }
    ]
})
export class RootModule {
}
