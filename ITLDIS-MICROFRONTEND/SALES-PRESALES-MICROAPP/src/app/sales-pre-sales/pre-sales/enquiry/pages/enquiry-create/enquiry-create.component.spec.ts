import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryCreateComponent } from './enquiry-create.component';

describe('EnquiryCreateComponent', () => {
  let component: EnquiryCreateComponent;
  let fixture: ComponentFixture<EnquiryCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
