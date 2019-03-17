import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {UserProfilePageComponent} from "./pages/user-profile-page/user-profile-page.component";
import {AuthenticatedGuard} from "../../services/auth/authenticated.guard";

const routes: Routes = [
    {path: "profile", component: UserProfilePageComponent, canActivate: [AuthenticatedGuard]},
    {path: "**", redirectTo: "/user/profile"}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserProfileRoutingModule { }
