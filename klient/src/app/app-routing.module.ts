import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {IndexComponent} from "./index/index.component";
import {PrijavaComponent} from "./prijava/prijava.component";
import {AuthGuard} from "./services/auth.guard";
import {NiPravicComponent} from "./napake/401/ni.pravic.component";
import {NeObstajaComponent} from "./napake/404/ne.obstaja.component";

const routes: Routes = [
    {path: "", component: IndexComponent, pathMatch: "full", canActivate: [AuthGuard]},
    {path: "prijava", component: PrijavaComponent},
    {path: "401", component: NiPravicComponent},
    {path: "404", component: NeObstajaComponent},
    {path: "odjava", redirectTo: "/"},
    {path: "**", redirectTo: "/404"}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
