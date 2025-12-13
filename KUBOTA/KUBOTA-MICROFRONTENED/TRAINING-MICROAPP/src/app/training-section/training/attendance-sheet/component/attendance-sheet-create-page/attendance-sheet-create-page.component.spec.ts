import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingattendanceSheetCreatePageComponent } from './attendance-sheet-create-page.component';

describe('TrainingattendanceSheetCreatePageComponent', () => {
  let component: TrainingattendanceSheetCreatePageComponent;
  let fixture: ComponentFixture<TrainingattendanceSheetCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingattendanceSheetCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingattendanceSheetCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
