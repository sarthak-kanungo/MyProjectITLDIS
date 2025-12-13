import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetFailurePartsComponent } from './log-sheet-failure-parts.component';

describe('LogSheetFailurePartsComponent', () => {
  let component: LogSheetFailurePartsComponent;
  let fixture: ComponentFixture<LogSheetFailurePartsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetFailurePartsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetFailurePartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
