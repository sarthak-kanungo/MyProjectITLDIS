import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingNominationSearchTableComponent } from './training-nomination-search-table.component';

describe('TrainingNominationSearchTableComponent', () => {
  let component: TrainingNominationSearchTableComponent;
  let fixture: ComponentFixture<TrainingNominationSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingNominationSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingNominationSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
