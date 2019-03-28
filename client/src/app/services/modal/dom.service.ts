import {ApplicationRef, ComponentFactoryResolver, ComponentRef, EmbeddedViewRef, Injectable, Injector} from "@angular/core";

@Injectable({
    providedIn: "root"
})
export class DomService {

    private childComponentRef: ComponentRef<any>;

    constructor(private componentFactoryResolver: ComponentFactoryResolver,
                private appRef: ApplicationRef,
                private injector: Injector) {
    }

    public appendComponentTo(parentId: string, child: any, childConfig?: ChildConfig) {
        const childComponentRef = this.componentFactoryResolver
            .resolveComponentFactory(child)
            .create(this.injector);
        this.attachConfig(childConfig, childComponentRef);
        this.childComponentRef = childComponentRef;
        this.appRef.attachView(childComponentRef.hostView);
        const childDomElement = (childComponentRef.hostView as EmbeddedViewRef<any>).rootNodes[0] as HTMLElement;
        document.getElementById(parentId).appendChild(childDomElement);
    }

    public removeComponent(): void {
        this.appRef.detachView(this.childComponentRef.hostView);
        this.childComponentRef.destroy();
    }

    private attachConfig(config: ChildConfig, componentRef: ComponentRef<any>): void {
        const inputs = config.data;
        Object.keys(inputs).forEach(key => {
            componentRef.instance[key] = (inputs as any)[key];
        });
    }
}

export interface ChildConfig {
    data: any;
}
