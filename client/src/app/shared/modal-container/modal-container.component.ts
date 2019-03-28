import {Component, OnInit} from "@angular/core";
import {ModalService} from "../../services/modal/modal.service";

@Component({
    selector: "mj-modal-container",
    templateUrl: "./modal-container.component.html",
    styleUrls: ["./modal-container.component.scss"]
})
export class ModalContainerComponent implements OnInit {

    constructor(private modalService: ModalService) {
    }

    ngOnInit() {
    }

    public close(): void {
        this.modalService.close();
    }

}
