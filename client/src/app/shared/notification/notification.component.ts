import {Component, Input, OnInit} from "@angular/core";
import {NotificationType} from "./notification.models";

@Component({
    selector: "mj-notification",
    templateUrl: "./notification.component.html",
    styleUrls: ["./notification.component.scss"]
})
export class NotificationComponent implements OnInit {

    @Input()
    public type: NotificationType;

    @Input()
    public message: string;

    public visible = true;

    constructor() {
    }

    ngOnInit() {
    }

    hide() {
        this.visible = false;
    }

}
