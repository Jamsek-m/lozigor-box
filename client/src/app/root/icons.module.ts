import {NgModule} from "@angular/core";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {library, IconDefinition} from "@fortawesome/fontawesome-svg-core";
import {
    faBars,
    faCloudUploadAlt,
    faExchangeAlt,
    faFile,
    faFolder,
    faFolderPlus,
    faPencilAlt,
    faSignOutAlt,
    faTrash,
    faUser
} from "@fortawesome/free-solid-svg-icons";


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
            faSignOutAlt,
            faFolder,
            faFile,
            faCloudUploadAlt,
            faTrash,
            faExchangeAlt,
            faPencilAlt,
            faFolderPlus
        ];
        icons.forEach(icon => library.add(icon));
    }
}
