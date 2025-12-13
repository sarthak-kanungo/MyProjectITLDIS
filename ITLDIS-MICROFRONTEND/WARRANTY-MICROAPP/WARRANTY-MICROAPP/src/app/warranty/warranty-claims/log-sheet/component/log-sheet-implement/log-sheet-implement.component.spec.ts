import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetImplementComponent } from './log-sheet-implement.component';

describe('LogSheetImplementComponent', () => {
  let component: LogSheetImplementComponent;
  let fixture: ComponentFixture<LogSheetImplementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetImplementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetImplementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
