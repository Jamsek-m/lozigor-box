import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {FilesLayoutComponent} from "./files-layout/files-layout.component";
import {FileListPageComponent} from "./pages/file-list-page/file-list-page.component";
import {FileDownloadPageComponent} from "./pages/file-download-page/file-download-page.component";
import {AuthenticatedGuard} from "../../services/auth/authenticated.guard";

const routes: Routes = [
    {
        path: "", component: FilesLayoutComponent, children: [
            {path: "", pathMatch: "full", component: FileListPageComponent, canActivate: [AuthenticatedGuard]}
        ]
    },
    {path: "download/:fileId", component: FileDownloadPageComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FilesRoutingModule {
}
