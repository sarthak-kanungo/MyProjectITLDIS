import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectSurveyCustomerDetailComponent } from './direct-survey-customer-detail.component';

describe('DirectSurveyCustomerDetailComponent', () => {
  let component: DirectSurveyCustomerDetailComponent;
  let fixture: ComponentFixture<DirectSurveyCustomerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectSurveyCustomerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectSurveyCustomerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
