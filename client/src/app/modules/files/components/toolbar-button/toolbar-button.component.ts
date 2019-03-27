import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {IconProp} from "@fortawesome/fontawesome-svg-core";

@Component({
    selector: "mj-toolbar-button",
    templateUrl: "./toolbar-button.component.html",
    styleUrls: ["./toolbar-button.component.scss"]
})
export class ToolbarButtonComponent implements OnInit {

    @Input()
    public icon: IconProp;

    @Input()
    public title: string;

    @Output()
    public whenClicked: EventEmitter<void> = new EventEmitter();


    constructor() {
    }

    ngOnInit() {
    }

    public clicked(): void {
        this.whenClicked.emit();
    }

}
