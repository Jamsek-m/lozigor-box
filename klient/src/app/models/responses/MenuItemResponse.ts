import {MenuItem} from "../data/MenuItem";

export class MenuItemResponse {

    public id: number;
    public ime: string;
    public parent: number;
    public items: MenuItem[];

}
