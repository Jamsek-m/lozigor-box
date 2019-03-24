import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {FileService} from "../../../../services/files/file.service";
import {FileDownloadResponse} from "../../../../../models/file/file.models";

@Component({
    selector: "mj-file-download-page",
    templateUrl: "./file-download-page.component.html",
    styleUrls: ["./file-download-page.component.scss"]
})
export class FileDownloadPageComponent implements OnInit {

    private fileId: number;

    public message = "Started downloading file...";

    constructor(private activatedRoute: ActivatedRoute, private fileService: FileService) {
    }

    ngOnInit() {
        this.fileId = this.activatedRoute.snapshot.params["fileId"];
        setTimeout(() => {
            this.downloadFile();
        }, 3000);
    }

    private downloadFile(): void {
        this.fileService.downloadFile(this.fileId).subscribe(
            (res: FileDownloadResponse) => {
                if (res === FileDownloadResponse.NOT_FOUND) {
                    this.message = "Requested file not found!";
                } else if (res === FileDownloadResponse.NO_PERMISSION) {
                    this.message = "You lack permission to download this file!";
                } else if (res === FileDownloadResponse.UNKNOWN_ERROR) {
                    this.message = "Unknown error happened when dowloading file!";
                } else {
                    this.message = "File was downloaded!";
                }
            }
        );
    }

}
