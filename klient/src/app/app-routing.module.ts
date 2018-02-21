import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {IndexComponent} from "./index/index.component";
import {PrijavaComponent} from "./prijava/prijava.component";
import {AuthGuard} from "./services/auth.guard";

const routes: Routes = [
    {path: "", component: IndexComponent, pathMatch: "full", canActivate: [AuthGuard]},
    {path: "prijava", component: PrijavaComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
