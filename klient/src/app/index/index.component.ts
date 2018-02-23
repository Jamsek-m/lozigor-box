import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";
import {Router} from "@angular/router";
import {MenuService} from "../services/menu.service";
import {MenuItem} from "../models/data/MenuItem";
import {MenuItemResponse} from "../models/responses/MenuItemResponse";
import {FileService} from "../services/files/file.service";

import 'rxjs/Rx' ;
import * as FileSaver from "file-saver";

@Component({
    moduleId: module.id,
    selector: "app-index-page",
    templateUrl: "index.component.html",
    styleUrls: ["index.component.css"]
})
export class IndexComponent implements OnInit {
    trenutniUporabnik;
    menu: MenuItem[];
    trenutnaMapa: number;
    parentMapa: number;
    query: string;
    trenutniElement: MenuItem;

    constructor(private auth: AuthService, private router: Router,
                private menuService: MenuService, private fileService: FileService) {}

    ngOnInit(): void {
        this.trenutniElement = null;
        this.query = "";
        this.naloziKorenskiMeni();
        this.trenutniUporabnik = this.auth.pridobiTrenutnegaUporabnika();
    }

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

    private prenesiFile(data, item: MenuItem) {
        const blob = new Blob([data], {type: item.file.contentType});
        FileSaver.saveAs(blob, item.file.lokacija);
    }

    izberiElement(item: MenuItem) {
        if (this.trenutniElement != null && this.trenutniElement.id === item.id) {
            this.trenutniElement = null;
        } else {
            this.trenutniElement = item;
        }
    }

}
