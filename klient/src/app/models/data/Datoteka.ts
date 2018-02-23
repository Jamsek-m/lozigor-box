
export class Datoteka {

    public id: number;
    public ime: string;
    public lokacija: string;
    public ext: string;
    public contentType: string;
    public velikost: number;
    public uploaded: number;


    constructor(id: number, ime: string, lokacija: string, ext: string, contentType: string, velikost: number, uploaded: number) {
        this.id = id;
        this.ime = ime;
        this.lokacija = lokacija;
        this.ext = ext;
        this.contentType = contentType;
        this.velikost = velikost;
        this.uploaded = uploaded;
    }
}
