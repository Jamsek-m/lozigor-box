import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";
import {Router} from "@angular/router";
import {MenuService} from "../services/menu.service";
import {MenuItem} from "../models/data/MenuItem";
import {MenuItemResponse} from "../models/responses/MenuItemResponse";
import {FileService} from "../services/files/file.service";

// import "rxjs/Rx";
import * as FileSaver from "file-saver";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {NavbarService} from "../services/navbar.service";
import {EmitTrenutniUporabnik} from "../models/emitters/EmitTrenutniUporabnik";
import {TrenutniUporabnik} from "../models/token/TrenutniUporabnik";

@Component({
    moduleId: module.id,
    selector: "app-index-page",
    templateUrl: "index.component.html",
    styleUrls: ["index.component.css"]
})
export class IndexComponent implements OnInit {
    // avtentikacija
    trenutniUporabnik: TrenutniUporabnik;
    prijavljen: boolean;
    subscription;
    // navigacija
    menu: MenuItem[];
    trenutnaMapa: number;
    parentMapa: number;
    trenutniElement: MenuItem;
    // iskanje
    query: string;
    // upload
    uploadDatoteke: FileList;
    modalRef: NgbModalRef;

    constructor(private auth: AuthService,
                private router: Router,
                private menuService: MenuService,
                private fileService: FileService,
                private modal: NgbModal,
                private navbar: NavbarService
    ) {}

    ngOnInit(): void {
        this.trenutniElement = null;
        this.query = "";
        this.uploadDatoteke = null;
        this.naloziKorenskiMeni();

        this.subscription = this.navbar.getEmittedValueJePrijavljen()
            .subscribe((item: EmitTrenutniUporabnik) => {
                this.prijavljen = item.jePrijavljen;
                this.trenutniUporabnik = item.trenutniUporabnik;
            });
        this.prijavljen = this.auth.jePrijavljen();
        this.trenutniUporabnik = this.auth.pridobiTrenutnegaUporabnika();
    }

    // Navigacija po mapah
    naloziKorenskiMeni() {
        this.trenutnaMapa = 1;
        this.parentMapa = 0;
        this.menuService.pridobiKorenskoMapo().then((menu: MenuItemResponse) => {
            this.menu = menu.items;
            this.trenutnaMapa = menu.id;
            this.parentMapa = menu.parent;
        });
    }

    naloziMapo(id: number) {
        this.menuService.pridobiVsebinoMape(id).then((menu: MenuItemResponse) => {
            this.menu = menu.items;
            this.trenutnaMapa = menu.id;
            this.parentMapa = menu.parent;
        });
    }

    pojdiEnoGor() {
        this.naloziMapo(this.parentMapa);
    }

    handleMenuItem(item: MenuItem) {
        if (item.tip === "DIR") {
            this.naloziMapo(item.id);
        } else if (item.tip === "FILE") {
            this.prenesiDatoteko(item);
        }
    }

    prenesiDatoteko(item: MenuItem) {
        this.fileService
            .pridobiFile(item.file.id)
            .subscribe((data) => {
                    this.prenesiFile(data, item);
                },
                (err) => {
                    console.log("Napaka pri downloadu: ", err);
                });
    }

    izberiElement(item: MenuItem) {
        if (this.trenutniElement != null && this.trenutniElement.id === item.id) {
            this.trenutniElement = null;
        } else {
            this.trenutniElement = item;
        }
    }

    // Iskanje datotek
    poisciDatoteke() {
        this.menuService.poisciZQueryjem(this.query).then((items: MenuItem[]) => {
            this.menu = items;
            this.trenutnaMapa = 1;
            this.parentMapa = 0;
        });
    }

    prekliciIskanje() {
        this.query = "";
        this.naloziKorenskiMeni();
    }

    // Prikazi upload datotek
    openModalUploadFile(content) {
        this.modalRef = this.modal.open(content);
        this.modalRef.result.then((res) => {
                console.log("RES: ", res);
            },
            (err) => {
                console.error(err);
            });
    }

    handleFileInput(files: FileList) {
        this.uploadDatoteke = files;
    }

    posljiDatotekeNaServer() {
        this.fileService.shraniFile(this.trenutnaMapa, this.uploadDatoteke)
            .then((res) => {
                this.naloziMapo(this.trenutnaMapa);
                this.modalRef.close("Akcija uspela");
            })
            .catch(err => {
                console.error("Napaka: ", err);
            });
    }

    // Dovoljenja
    lahkoVidiUpload(): boolean {
        return this.auth.imaVlogo("MOD") || this.auth.imaVlogo("ADMIN");
    }

    // Pomozne metode
    private prenesiFile(data, item: MenuItem) {
        const blob = new Blob([data], {type: item.file.contentType});
        FileSaver.saveAs(blob, item.file.lokacija);
    }

}
