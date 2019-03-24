import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, of} from "rxjs";
import {catchError, map, switchMap, tap} from "rxjs/operators";
import {File, FileDownloadResponse} from "../../../models/file/file.models";
import {saveAs} from "file-saver";

@Injectable({
    providedIn: "root"
})
export class FileService {

    private v1Url = `${environment.apiV1Url}/files`;

    constructor(private http: HttpClient) {

    }

    public downloadFile(fileId: number): Observable<FileDownloadResponse> {
        const downloadUrl = `${this.v1Url}/download/${fileId}`;
        const metaUrl = `${this.v1Url}/${fileId}`;

        return this.http.get(metaUrl).pipe(
            switchMap((fileMeta: File) => {
                return this.http.get(downloadUrl, {responseType: "blob"}).pipe(
                    tap((blob: any) => {
                        this.triggerSaveDialog(blob, fileMeta);
                    }),
                    map(() => {
                        return FileDownloadResponse.OK;
                    }),
                    catchError(this.handleDownloadError)
                );
            }),
            catchError(this.handleDownloadError)
        );
    }

    private handleDownloadError(err: HttpErrorResponse): Observable<FileDownloadResponse> {
        if (!err || !err.status) {
            return of(FileDownloadResponse.UNKNOWN_ERROR);
        } else if (err.status === 401 || err.status === 403) {
            return of(FileDownloadResponse.NO_PERMISSION);
        } else if (err.status === 404) {
            return of(FileDownloadResponse.NOT_FOUND);
        }
        return of(FileDownloadResponse.UNKNOWN_ERROR);
    }

    private triggerSaveDialog(data: any, meta: File): void {
        const blob = new Blob([data], {type: meta.mimeType});
        saveAs(blob, meta.filename);
    }

}
