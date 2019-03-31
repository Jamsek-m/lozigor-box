import {Component, OnInit} from "@angular/core";
import {MenuEntry} from "../../../../models/menu/menu.models";
import {MenuService} from "../../../services/menu/menu.service";
import {ModalService} from "../../../services/modal/modal.service";
import {FileService} from "../../../services/files/file.service";
import {ConfirmationDialogComponent} from "../../../shared/confirmation-dialog/confirmation-dialog.component";

@Component({
    selector: "mj-files-layout",
    templateUrl: "./files-layout.component.html",
    styleUrls: ["./files-layout.component.scss"]
})
export class FilesLayoutComponent implements OnInit {

    public activeMenuEntry: MenuEntry = null;
    private currentDirectory: MenuEntry = null;

    public newDir: NewDirRequest = new NewDirRequest();

    public newFile: NewFileRequest = new NewFileRequest();

    public renameEntry: RenameRequest = new RenameRequest();

    constructor(private menuService: MenuService, private modalService: ModalService, private fileService: FileService) {
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
            this.newFile.parent = currentDirectory.id;
        });
    }

    public upload(): void {
        this.newFile.show = !this.newFile.show;
    }

    public delete(): void {
        this.modalService.show(ConfirmationDialogComponent).subscribe(
            (result: any) => {
                if (result) {
                    this.menuService.removeMenuEntry(this.activeMenuEntry.id).subscribe(
                        () => {
                            this.menuService.reloadCurrentDirectory();
                        },
                        (err) => {
                            console.error(err);
                        }
                    );
                }
            }
        );
    }

    public move(): void {
        console.log("move", this.activeMenuEntry.id);
    }

    public rename(): void {
        this.renameEntry.show = !this.renameEntry.show;
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

    public setFileForm(files: FileList): void {
        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < files.length; i++) {
            this.newFile.files.push(files.item(i));
        }
    }

    public saveFile(): void {
        if (this.newFile.files.length > 0 && this.newFile.parent !== -1) {
            this.fileService.uploadFiles(this.newFile.files, this.newFile.parent).subscribe(
                (res: any) => {
                    console.log("upload succedeed!");
                    this.newFile.reset();
                    this.menuService.reloadCurrentDirectory();
                },
                (err: Error) => {
                    console.error(err);
                }
            );
        }
    }

    public renameMenuEntry(): void {
        if (this.renameEntry.value && this.renameEntry.value.length > 0) {
            this.menuService.renameMenuEntry(this.activeMenuEntry.id, this.renameEntry.value).subscribe(
                (entry: MenuEntry) => {
                    console.log("renamed!");
                    this.renameEntry.show = false;
                    this.menuService.reloadCurrentDirectory();
                },
                (err) => {
                    console.error(err);
                }
            );
        }
    }

}

class NewDirRequest {
    public show: boolean;
    public value: string;

    constructor() {
        this.show = false;
        this.value = "";
    }
}

class NewFileRequest {
    public show: boolean;
    public files: File[];
    public parent: number;

    constructor(parent?: number) {
        this.show = false;
        this.files = [];
        this.parent = 1;
    }

    public reset(): void {
        this.show = false;
        this.files = [];
    }
}

class RenameRequest {
    public show: boolean;
    public value: string;

    constructor() {
        this.show = false;
        this.value = "";
    }
}
