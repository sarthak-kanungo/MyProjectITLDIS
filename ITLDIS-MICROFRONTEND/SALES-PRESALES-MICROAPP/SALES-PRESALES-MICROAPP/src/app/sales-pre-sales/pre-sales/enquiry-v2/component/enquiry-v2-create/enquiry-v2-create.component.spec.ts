import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryV2CreateComponent } from './enquiry-v2-create.component';

describe('EnquiryV2CreateComponent', () => {
  let component: EnquiryV2CreateComponent;
  let fixture: ComponentFixture<EnquiryV2CreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryV2CreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryV2CreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
