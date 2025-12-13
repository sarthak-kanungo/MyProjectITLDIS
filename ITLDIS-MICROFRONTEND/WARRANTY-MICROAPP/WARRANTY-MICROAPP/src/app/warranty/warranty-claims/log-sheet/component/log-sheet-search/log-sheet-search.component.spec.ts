import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetSearchComponent } from './log-sheet-search.component';

describe('LogSheetSearchComponent', () => {
  let component: LogSheetSearchComponent;
  let fixture: ComponentFixture<LogSheetSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
