import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, of} from "rxjs";
import {catchError, map, switchMap, tap} from "rxjs/operators";
import {File as FileModel, FileDownloadResponse} from "../../../models/file/file.models";
import {saveAs} from "file-saver";

@Injectable({
    providedIn: "root"
})
export class FileService {

    private v1Url = `${environment.apiV1Url}/files`;

    constructor(private http: HttpClient) {

    }

    public uploadFiles(file: File[], parentId: number): Observable<any> {
        const formData = new FormData();
        file.forEach(fl => {
            formData.append("file", fl);
        });
        formData.append("parent", parentId.toString(10));
        const url = `${this.v1Url}/upload`;
        return this.http.post(url, formData);
    }

    public downloadFileWithGivenMeta(file: FileModel): Observable<FileDownloadResponse> {
        const downloadUrl = `${this.v1Url}/download/${file.id}`;
        return this.http.get(downloadUrl, {responseType: "blob"}).pipe(
            tap((blob: any) => {
                this.triggerSaveDialog(blob, file);
            }),
            map(() => {
                return FileDownloadResponse.OK;
            }),
            catchError(this.handleDownloadError)
        );
    }

    public downloadFile(fileId: number): Observable<FileDownloadResponse> {
        const downloadUrl = `${this.v1Url}/download/${fileId}`;
        const metaUrl = `${this.v1Url}/${fileId}`;

        return this.http.get(metaUrl).pipe(
            switchMap((fileMeta: FileModel) => {
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

    private triggerSaveDialog(data: any, meta: FileModel): void {
        const blob = new Blob([data], {type: meta.mimeType});
        saveAs(blob, meta.filename);
    }

}
