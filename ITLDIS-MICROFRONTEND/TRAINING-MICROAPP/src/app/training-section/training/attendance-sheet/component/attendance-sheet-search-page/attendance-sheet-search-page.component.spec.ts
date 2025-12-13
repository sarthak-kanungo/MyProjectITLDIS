import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingattendanceSheetSearchPageComponent } from './attendance-sheet-search-page.component';

describe('TrainingattendanceSheetSearchPageComponent', () => {
  let component: TrainingattendanceSheetSearchPageComponent;
  let fixture: ComponentFixture<TrainingattendanceSheetSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingattendanceSheetSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingattendanceSheetSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
