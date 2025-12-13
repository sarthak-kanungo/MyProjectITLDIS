import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryDetailsProposalComponent } from './enquiry-details-proposal.component';

describe('EnquiryDetailsProposalComponent', () => {
  let component: EnquiryDetailsProposalComponent;
  let fixture: ComponentFixture<EnquiryDetailsProposalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryDetailsProposalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryDetailsProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
