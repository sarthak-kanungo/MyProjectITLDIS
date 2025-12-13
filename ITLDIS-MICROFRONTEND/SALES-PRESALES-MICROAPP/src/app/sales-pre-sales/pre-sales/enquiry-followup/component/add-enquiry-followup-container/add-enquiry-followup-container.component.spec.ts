import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEnquiryFollowupContainerComponent } from './add-enquiry-followup-container.component';

describe('AddEnquiryFollowupContainerComponent', () => {
  let component: AddEnquiryFollowupContainerComponent;
  let fixture: ComponentFixture<AddEnquiryFollowupContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEnquiryFollowupContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEnquiryFollowupContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
