import {Datoteka} from "./Datoteka";


export class MenuItem {

    public id: number;
    public parent: number;
    public ime: string;
    public num_of_childs: number;
    public tip: string;
    public file: Datoteka;

    constructor(id: number, parent: number, ime: string, num_of_childs: number, tip: string, file: Datoteka) {
        this.id = id;
        this.parent = parent;
        this.ime = ime;
        this.num_of_childs = num_of_childs;
        this.tip = tip;
        this.file = file;
    }
}
