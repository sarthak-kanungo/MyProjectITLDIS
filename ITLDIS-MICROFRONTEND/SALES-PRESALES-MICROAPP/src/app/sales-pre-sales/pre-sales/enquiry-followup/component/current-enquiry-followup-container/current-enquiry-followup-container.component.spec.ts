import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentEnquiryFollowupContainerComponent } from './current-enquiry-followup-container.component';

describe('CurrentEnquiryFollowupContainerComponent', () => {
  let component: CurrentEnquiryFollowupContainerComponent;
  let fixture: ComponentFixture<CurrentEnquiryFollowupContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentEnquiryFollowupContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentEnquiryFollowupContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
