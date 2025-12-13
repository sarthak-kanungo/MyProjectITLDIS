import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceSheetSearchTableComponent } from './attendance-sheet-search-table.component';

describe('AttendanceSheetSearchTableComponent', () => {
  let component: AttendanceSheetSearchTableComponent;
  let fixture: ComponentFixture<AttendanceSheetSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendanceSheetSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendanceSheetSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
