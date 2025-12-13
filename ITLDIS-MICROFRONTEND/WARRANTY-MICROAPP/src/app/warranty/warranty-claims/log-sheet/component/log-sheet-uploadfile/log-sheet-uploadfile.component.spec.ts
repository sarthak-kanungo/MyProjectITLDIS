import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetUploadfileComponent } from './log-sheet-uploadfile.component';

describe('LogSheetUploadfileComponent', () => {
  let component: LogSheetUploadfileComponent;
  let fixture: ComponentFixture<LogSheetUploadfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetUploadfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetUploadfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
