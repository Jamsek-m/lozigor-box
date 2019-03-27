import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {FilesRoutingModule} from "./files-routing.module";
import {FilesLayoutComponent} from "./files-layout/files-layout.component";
import {FileListPageComponent} from "./pages/file-list-page/file-list-page.component";
import {FileDownloadPageComponent} from "./pages/file-download-page/file-download-page.component";
import {FileSizePipe} from "../../pipes/file-size.pipe";
import {IconsModule} from "../../root/icons.module";
import {ToolbarButtonComponent} from "./components/toolbar-button/toolbar-button.component";

@NgModule({
    declarations: [FilesLayoutComponent, FileListPageComponent, FileDownloadPageComponent, FileSizePipe, ToolbarButtonComponent],
    imports: [
        CommonModule,
        FilesRoutingModule,
        IconsModule
    ]
})
export class FilesModule {
}
