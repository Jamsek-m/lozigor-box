import {EventEmitter, Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {MenuEntry} from "../../../models/menu/menu.models";
import {environment} from "../../../environments/environment";
import {catchError, map} from "rxjs/operators";

@Injectable({
    providedIn: "root"
})
export class MenuService {

    private v1Api = `${environment.apiV1Url}/menu`;

    private activeElementEvent: EventEmitter<MenuEntry> = new EventEmitter();
    private currentDirectoryEvent: EventEmitter<MenuEntry> = new EventEmitter();

    constructor(private http: HttpClient) {

    }

    public getDirectoryContent(parentId: number): Observable<MenuEntry[]> {
        const url = `${this.v1Api}/${parentId}`;
        return this.http.get(url).pipe(
            map(res => res as MenuEntry[]),
            catchError((err: HttpErrorResponse) => {
                console.error(err);
                return of([]);
            })
        );
    }

    public getDirectoryData(dirId: number): Observable<MenuEntry> {
        const url = `${this.v1Api}/dir/${dirId}`;
        return this.http.get(url).pipe(map(res => res as MenuEntry));
    }

    public setActiveMenuEntry(menuEntry: MenuEntry): void {
        this.activeElementEvent.emit(menuEntry);
    }

    public activeMenuEntryListener(): EventEmitter<MenuEntry> {
        return this.activeElementEvent;
    }

    public setCurrentDirectory(currentDirectory: MenuEntry): void {
        this.currentDirectoryEvent.emit(currentDirectory);
    }

    public currentDirectoryListener(): EventEmitter<MenuEntry> {
        return this.currentDirectoryEvent;
    }

}
