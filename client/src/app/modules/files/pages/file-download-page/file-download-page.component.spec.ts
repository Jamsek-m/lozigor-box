import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { FileDownloadPageComponent } from "./file-download-page.component";

describe("FileDownloadPageComponent", () => {
  let component: FileDownloadPageComponent;
  let fixture: ComponentFixture<FileDownloadPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileDownloadPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileDownloadPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
