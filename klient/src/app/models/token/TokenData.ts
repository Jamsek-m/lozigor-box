export class Vloga {
    id: number;
    naziv: string;
    sifra: string;
}

export class TokenData {
    sub: string;
    iat: number;
    exp: number;
    vloge: [Vloga];
    uporabnik_id: number;
    uporabnik_ime: string;
}
