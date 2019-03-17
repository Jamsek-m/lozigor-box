import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {RootLayoutComponent} from "./root-layout/root-layout.component";
import {Error404PageComponent} from "./pages/error404-page/error404-page.component";
import {LoginPageComponent} from "./pages/login-page/login-page.component";

const routes: Routes = [
    {
        path: "", component: RootLayoutComponent, children: [
            {path: "", loadChildren: "../modules/files/files.module#FilesModule"},
            {path: "user", loadChildren: "../modules/user-profile/user-profile.module#UserProfileModule"},
            {path: "login", component: LoginPageComponent},
            {path: "404", component: Error404PageComponent}
        ]
    },
    {path: "**", redirectTo: "/404"}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class RootRoutingModule {
}
