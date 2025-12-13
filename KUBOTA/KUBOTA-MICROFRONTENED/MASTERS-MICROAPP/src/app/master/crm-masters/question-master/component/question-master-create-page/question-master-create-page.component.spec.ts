import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionMasterCreatePageComponent } from './question-master-create-page.component';

describe('QuestionMasterCreatePageComponent', () => {
  let component: QuestionMasterCreatePageComponent;
  let fixture: ComponentFixture<QuestionMasterCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionMasterCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionMasterCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
