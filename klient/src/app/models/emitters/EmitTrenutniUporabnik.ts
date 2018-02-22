import {TrenutniUporabnik} from "../token/TrenutniUporabnik";

export class EmitTrenutniUporabnik {
    public jePrijavljen: boolean;
    public trenutniUporabnik: TrenutniUporabnik;

    constructor(jePrijavljen: boolean, trenutniUporabnik: TrenutniUporabnik) {
        this.jePrijavljen = jePrijavljen;
        this.trenutniUporabnik = trenutniUporabnik;
    }
}
