import {Pipe, PipeTransform} from "@angular/core";

export type SupportedFileUnits = "b" | "Kb" | "Mb" | "Gb";

@Pipe({
    name: "fileSize"
})
export class FileSizePipe implements PipeTransform {

    private multiplierTable: { [unitKey: string]: number } = {
        b: 1,
        Kb: 1024,
        Mb: 1024 * 1024,
        Gb: 1024 * 1024 * 1024,
    };

    transform(value: any, ...args: SupportedFileUnits[]): string {
        if (isNaN(parseInt(value, 10))) {
            return value;
        }

        const bytes = parseInt(value, 10);
        let unit: SupportedFileUnits = "b";

        if (bytes > this.multiplierTable["Gb"]) {
            unit = "Gb";
        } else if (bytes > this.multiplierTable["Mb"]) {
            unit = "Mb";
        } else if (bytes > this.multiplierTable["Kb"]) {
            unit = "Kb";
        }

        const convertedValue = bytes / this.multiplierTable[unit];

        return `${convertedValue.toFixed(2)} ${unit}`;
    }

}
