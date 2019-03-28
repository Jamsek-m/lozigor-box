import {Component, OnInit} from "@angular/core";
import {MenuEntry} from "../../../../models/menu/menu.models";
import {MenuService} from "../../../services/menu/menu.service";
import {ModalService} from "../../../services/modal/modal.service";

@Component({
    selector: "mj-files-layout",
    templateUrl: "./files-layout.component.html",
    styleUrls: ["./files-layout.component.scss"]
})
export class FilesLayoutComponent implements OnInit {

    private activeMenuEntry: MenuEntry = null;
    private currentDirectory: MenuEntry = null;

    public newDir = {
        show: false,
        value: ""
    };

    constructor(private menuService: MenuService, private modalService: ModalService) {
    }

    ngOnInit() {
        this.readCurrentDirectory();
        this.readActiveMenuEntry();

        // modal example:
        /*this.modalService.show(ConfirmationDialogComponent, {lol: "krnek"}).subscribe(
            (result: any) => {
                console.log("modal data", result);
            }
        );*/
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

    public addFolder() {
        this.newDir.show = !this.newDir.show;
    }

    public saveFolder(): void {
        if (this.newDir.value && this.newDir.value.length > 0) {
            const parentId = this.currentDirectory ? this.currentDirectory.id : 1;
            this.menuService.createDirectory(this.newDir.value, parentId).subscribe(
                (res: MenuEntry) => {
                    console.log("created", res);
                    this.newDir = {
                        show: false,
                        value: ""
                    };
                    this.menuService.reloadCurrentDirectory();
                },
                (err) => {
                    console.error(err);
                }
            );
        }
    }

}
