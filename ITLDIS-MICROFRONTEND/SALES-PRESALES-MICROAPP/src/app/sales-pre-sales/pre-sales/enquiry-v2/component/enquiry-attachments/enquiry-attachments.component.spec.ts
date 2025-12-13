import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryAttachmentsComponent } from './enquiry-attachments.component';

describe('EnquiryAttachmentsComponent', () => {
  let component: EnquiryAttachmentsComponent;
  let fixture: ComponentFixture<EnquiryAttachmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryAttachmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryAttachmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
