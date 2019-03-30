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
    private reloadCurrentDirectoryEvent: EventEmitter<void> = new EventEmitter();

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

    public renameMenuEntry(entryId: number, newName: string): Observable<MenuEntry> {
        const url = `${this.v1Api}/${entryId}`;
        return this.http.patch(url, JSON.stringify({newMenuEntryName: newName})).pipe(
            map(res => res as MenuEntry)
        );
    }

    public removeMenuEntry(entryId: number): Observable<void> {
        const url = `${this.v1Api}/${entryId}`;
        return this.http.delete(url).pipe(map(() => null));
    }


    public getDirectoryData(dirId: number): Observable<MenuEntry> {
        const url = `${this.v1Api}/dir/${dirId}`;
        return this.http.get(url).pipe(map(res => res as MenuEntry));
    }

    public createDirectory(directoryName: string, parentId: number): Observable<MenuEntry> {
        const url = this.v1Api;
        const data = JSON.stringify({
            directoryName: directoryName,
            parentId: parentId
        });
        return this.http.post(url, data).pipe(map(res => res as MenuEntry));
    }

    /*
     * Event listener for layout and list communication
     */

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

    public reloadCurrentDirectory(): void {
        this.reloadCurrentDirectoryEvent.emit();
    }

    public reloadCurrentDirectoryListener(): EventEmitter<void> {
        return this.reloadCurrentDirectoryEvent;
    }

}
