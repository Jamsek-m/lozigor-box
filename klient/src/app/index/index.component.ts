import {Component, OnInit} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: "app-index-page",
    templateUrl: "index.component.html"
})
export class IndexComponent implements OnInit {
    sporocilo: String;

    constructor() {}

    ngOnInit(): void {
        this.nastaviSporocilo("Hello world!");
    }

    nastaviSporocilo(msg: string) {
        this.sporocilo = msg;
    }
}
