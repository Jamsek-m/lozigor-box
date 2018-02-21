import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {NgbCollapseModule, NgbDropdownModule, NgbTooltip, NgbTooltipModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
    imports: [
        CommonModule,
        NgbTooltipModule.forRoot(),
        NgbDropdownModule.forRoot(),
        NgbCollapseModule.forRoot()
    ],
    exports: [
        NgbTooltip,
        NgbDropdownModule,
        NgbCollapseModule
    ]
})
export class BootstrapModule {}
