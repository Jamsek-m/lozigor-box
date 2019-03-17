import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { FilesRoutingModule } from "./files-routing.module";
import { FilesLayoutComponent } from "./files-layout/files-layout.component";
import { FileListPageComponent } from "./pages/file-list-page/file-list-page.component";
import { FileDownloadPageComponent } from "./pages/file-download-page/file-download-page.component";

@NgModule({
  declarations: [FilesLayoutComponent, FileListPageComponent, FileDownloadPageComponent],
  imports: [
    CommonModule,
    FilesRoutingModule
  ]
})
export class FilesModule { }
