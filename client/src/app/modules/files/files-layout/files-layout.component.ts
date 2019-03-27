import {Component, OnInit} from "@angular/core";
import {MenuEntry} from "../../../../models/menu/menu.models";
import {MenuService} from "../../../services/menu/menu.service";

@Component({
    selector: "mj-files-layout",
    templateUrl: "./files-layout.component.html",
    styleUrls: ["./files-layout.component.scss"]
})
export class FilesLayoutComponent implements OnInit {

    private activeMenuEntry: MenuEntry = null;
    private currentDirectory: MenuEntry = null;

    constructor(private menuService: MenuService) {
    }

    ngOnInit() {
        this.readCurrentDirectory();
        this.readActiveMenuEntry();
    }

    private readActiveMenuEntry(): void {
        this.menuService.activeMenuEntryListener().subscribe((menuEntry: MenuEntry) => {
            this.activeMenuEntry = menuEntry;
        });
    }

    private readCurrentDirectory(): void {
        this.menuService.currentDirectoryListener().subscribe((currentDirectory: MenuEntry) => {
            this.currentDirectory = currentDirectory;
        });
    }

    public upload(): void {
        console.log("upload", this.currentDirectory.id);
    }

    public delete(): void {
        console.log("delete", this.activeMenuEntry.id);
    }

    public move(): void {
        console.log("move", this.activeMenuEntry.id);
    }

    public rename(): void {
        console.log("rename", this.activeMenuEntry.id);
    }

}
