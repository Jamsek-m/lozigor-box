import {EventEmitter, Injectable, Output} from "@angular/core";
import {TrenutniUporabnik} from "../models/token/TrenutniUporabnik";
import {EmitTrenutniUporabnik} from "../models/emitters/EmitTrenutniUporabnik";

@Injectable()
export class NavbarService {

    @Output() fireJePrijavljen: EventEmitter<any> = new EventEmitter<any>();

    constructor() {
    }

    sporociDaJeUporabnikPrijavljen(jePrijavljen: boolean, uporabnik: TrenutniUporabnik) {
        const toEmit = new EmitTrenutniUporabnik(jePrijavljen, uporabnik);
        this.fireJePrijavljen.emit(toEmit);
    }

    getEmittedValueJePrijavljen() {
        return this.fireJePrijavljen;
    }

}
