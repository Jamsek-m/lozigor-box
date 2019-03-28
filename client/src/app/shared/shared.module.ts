import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {NotificationComponent} from "./notification/notification.component";
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { ModalContainerComponent } from './modal-container/modal-container.component';

@NgModule({
    declarations: [
        NotificationComponent,
        ConfirmationDialogComponent,
        ModalContainerComponent
    ],
    imports: [
        CommonModule
    ],
    exports: [
        NotificationComponent,
        ModalContainerComponent
    ],
    entryComponents: [
        ConfirmationDialogComponent
    ]
})
export class SharedModule {
}
