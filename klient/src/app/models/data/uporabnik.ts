import {Vloga} from "../token/TokenData";

export class Uporabnik {
    public id: number;
    public email: string;
    public uporabniskoIme: string;
    public vloge: [Vloga];
}
