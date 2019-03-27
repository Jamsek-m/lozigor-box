import {Component, OnInit} from "@angular/core";
import {MenuEntry, MenuEntryType} from "../../../../../models/menu/menu.models";
import {MenuService} from "../../../../services/menu/menu.service";
import {FileService} from "../../../../services/files/file.service";
import {FileDownloadResponse} from "../../../../../models/file/file.models";

@Component({
    selector: "mj-file-list-page",
    templateUrl: "./file-list-page.component.html",
    styleUrls: ["./file-list-page.component.scss"]
})
export class FileListPageComponent implements OnInit {

    public menuEntries: MenuEntry[] = [];

    public activeEntry: MenuEntry = null;

    public currentDirectory: MenuEntry = null;

    constructor(private menuService: MenuService, private fileService: FileService) {
    }

    ngOnInit() {
        this.getDirectoryContent();
    }

    private getDirectoryContent(): void {
        const parentId = this.currentDirectory === null ? 1 : this.currentDirectory.id;
        this.menuService.getDirectoryContent(parentId).subscribe(
            (entries: MenuEntry[]) => {
                this.menuEntries = entries;
            }
        );
    }

    public selectActiveEntry(menuEntry: MenuEntry): void {
        if (this.activeEntry === menuEntry) {
            this.activeEntry = null;
            this.menuService.setActiveMenuEntry(null);
        } else {
            this.activeEntry = menuEntry;
            this.menuService.setActiveMenuEntry(menuEntry);
        }
    }

    public goOneLevelUp(): void {
        this.menuService.getDirectoryData(this.currentDirectory.parent).subscribe(
            (parent: MenuEntry) => {
                this.currentDirectory = parent;
                this.menuService.setCurrentDirectory(parent);
                this.getDirectoryContent();
            }
        );
    }

    public setCurrentDirectory(menuEntry: MenuEntry): void {
        if (menuEntry.type === MenuEntryType.DIR) {
            this.currentDirectory = menuEntry;
            this.menuService.setCurrentDirectory(menuEntry);
            this.getDirectoryContent();
        } else if (menuEntry.type === MenuEntryType.FILE) {
            this.fileService.downloadFileWithGivenMeta(menuEntry.file).subscribe(
                (res: FileDownloadResponse) => {
                    console.log(res);
                }
            );
        }
    }

}
