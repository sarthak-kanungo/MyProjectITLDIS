import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelSurveyMasterSearchPageComponent } from './model-survey-master-search-page.component';

describe('ModelSurveyMasterSearchPageComponent', () => {
  let component: ModelSurveyMasterSearchPageComponent;
  let fixture: ComponentFixture<ModelSurveyMasterSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelSurveyMasterSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelSurveyMasterSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
