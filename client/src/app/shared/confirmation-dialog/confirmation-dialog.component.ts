import {Component, OnInit} from "@angular/core";
import {ModalService} from "../../services/modal/modal.service";

@Component({
    selector: "mj-confirmation-dialog",
    templateUrl: "./confirmation-dialog.component.html",
    styleUrls: ["./confirmation-dialog.component.scss"]
})
export class ConfirmationDialogComponent implements OnInit {

    constructor(private modalService: ModalService) {
    }

    ngOnInit() {
    }

    public close() {
        this.modalService.close(false);
    }

    public confirm() {
        this.modalService.close(true);
    }

}
