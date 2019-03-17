import {enableProdMode} from "@angular/core";
import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";

import {AppModule} from "./app/app.module";
import {environment} from "./environments/environment";
import {AuthService} from "./app/services/auth/auth.service";

if (environment.production) {
    enableProdMode();
}

AuthService.initializeAuthentication().then(
    (restoredSession: boolean) => {
        console.log("Session restore at bootstrap: ", restoredSession);
        platformBrowserDynamic().bootstrapModule(AppModule)
            .catch(err => console.error(err));
    }
);
