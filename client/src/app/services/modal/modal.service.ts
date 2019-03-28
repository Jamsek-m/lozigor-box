import {EventEmitter, Injectable} from "@angular/core";
import {DomService} from "./dom.service";

@Injectable({
    providedIn: "root"
})
export class ModalService {

    constructor(private domService: DomService) {
    }

    private modalResult: EventEmitter<any>;

    private modalElementId = "modal-container";
    private overlayElementId = "overlay";

    public show(component: any, data: any = {}): EventEmitter<any> {
        const componentConfig = {data};
        this.domService.appendComponentTo(this.modalElementId, component, componentConfig);
        this.toggleVisibility(true);
        this.modalResult = new EventEmitter();
        return this.modalResult;
    }

    public close(result?: any) {
        this.modalResult.emit(result);
        this.domService.removeComponent();
        this.toggleVisibility(false);
    }

    private toggleVisibility(visible: boolean): void {
        if (visible) {
            document.getElementById(this.modalElementId).classList.add("show");
            document.getElementById(this.overlayElementId).classList.add("show");
            document.getElementById(this.modalElementId).classList.remove("hidden");
            document.getElementById(this.overlayElementId).classList.remove("hidden");
        } else {
            document.getElementById(this.modalElementId).classList.add("hidden");
            document.getElementById(this.overlayElementId).classList.add("hidden");
            document.getElementById(this.modalElementId).classList.remove("show");
            document.getElementById(this.overlayElementId).classList.remove("show");
        }
    }
}
