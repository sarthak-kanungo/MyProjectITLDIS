import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetConcernComponent } from './log-sheet-concern.component';

describe('LogSheetConcernComponent', () => {
  let component: LogSheetConcernComponent;
  let fixture: ComponentFixture<LogSheetConcernComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetConcernComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetConcernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
