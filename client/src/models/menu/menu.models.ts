import {File} from "../file/file.models";

export enum MenuEntryType {
    DIR = "DIR",
    FILE = "FILE"
}

export class MenuEntry {
    public id: number;
    public parent: number;
    public name: string;
    public file: File;
    public type: MenuEntryType;
}
