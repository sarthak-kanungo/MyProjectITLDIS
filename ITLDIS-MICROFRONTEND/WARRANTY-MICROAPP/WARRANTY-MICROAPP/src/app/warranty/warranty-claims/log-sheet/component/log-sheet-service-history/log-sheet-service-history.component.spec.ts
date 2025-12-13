import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetServiceHistoryComponent } from './log-sheet-service-history.component';

describe('LogSheetServiceHistoryComponent', () => {
  let component: LogSheetServiceHistoryComponent;
  let fixture: ComponentFixture<LogSheetServiceHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetServiceHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetServiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
