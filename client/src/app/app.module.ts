import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {RootModule} from "./root/root.module";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        RootModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
