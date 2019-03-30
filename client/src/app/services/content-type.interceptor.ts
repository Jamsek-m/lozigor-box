import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpHeader} from "../../constants/http.header";
import {HttpMediaType} from "../../constants/http.media-type";
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: "root"
})
export class ContentTypeInterceptor implements HttpInterceptor {


    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (this.isOnBlacklist(req.url)) {
            return next.handle(req);
        }

        let headers = req.headers;

        if (!headers.has(HttpHeader.CONTENT_TYPE)) {
            headers = headers.set(HttpHeader.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON);
        }

        return next.handle(req.clone({
            headers
        }));
    }

    private isOnBlacklist(url: string): boolean {
        url = url.replace(environment.apiV1Url, "");

        if (url === "/files/upload") {
            return true;
        }
        return false;
    }

}
