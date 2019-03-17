
export class StyleUtil {

    public static determineScreenWidth(): number {
        if (typeof (window as any)["visualViewport"] !== "undefined") {
            return (window as any)["visualViewport"]["width"];
        } else {
            return window.innerWidth;
        }
    }

}
