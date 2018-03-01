import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {NgbCollapseModule, NgbDropdownModule, NgbModalModule, NgbTooltip, NgbTooltipModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
    imports: [
        CommonModule,
        NgbTooltipModule.forRoot(),
        NgbDropdownModule.forRoot(),
        NgbCollapseModule.forRoot(),
        NgbModalModule.forRoot()
    ],
    exports: [
        NgbTooltip,
        NgbDropdownModule,
        NgbCollapseModule,
        NgbModalModule
    ]
})
export class BootstrapModule {}
