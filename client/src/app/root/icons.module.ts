import {NgModule} from "@angular/core";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {library, IconDefinition} from "@fortawesome/fontawesome-svg-core";
import {faBars, faSignOutAlt, faUser} from "@fortawesome/free-solid-svg-icons";


@NgModule({
    imports: [
        FontAwesomeModule
    ],
    exports: [
        FontAwesomeModule
    ]
})
export class IconsModule {
    // fas - free solid, far - free regular, fab - free brands
    constructor() {
        const icons: IconDefinition[] = [
            faBars,
            faUser,
            faSignOutAlt
        ];
        icons.forEach(icon => library.add(icon));
    }
}
