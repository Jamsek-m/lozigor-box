
export interface File {
    id: number;
    // location on disk
    filename: string;
    // example: .png
    ext: string;
    // example: application/octet-stream
    mimeType: string;
    // size of file in bytes
    size: number;
    uploadedAt: Date;
    updatedAt: Date;
}

export enum FileDownloadResponse {
    OK,
    NO_PERMISSION,
    NOT_FOUND,
    UNKNOWN_ERROR
}
