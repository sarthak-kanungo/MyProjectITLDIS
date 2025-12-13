import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquirySearchCriteriaContainerComponent } from './enquiry-search-criteria-container.component';

describe('EnquirySearchCriteriaContainerComponent', () => {
  let component: EnquirySearchCriteriaContainerComponent;
  let fixture: ComponentFixture<EnquirySearchCriteriaContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquirySearchCriteriaContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquirySearchCriteriaContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
