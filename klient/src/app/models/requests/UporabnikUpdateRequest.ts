import {Vloga} from "../token/TokenData";

export class UporabnikUpdateRequest {
    id: number;
    email: string;
    uporabniskoIme: string;
    geslo1: string;
    geslo2: string;
    vloge: [Vloga];
}
