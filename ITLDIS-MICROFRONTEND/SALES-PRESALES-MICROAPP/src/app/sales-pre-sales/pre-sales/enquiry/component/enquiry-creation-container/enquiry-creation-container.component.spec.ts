import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryCreationContainerComponent } from './enquiry-creation-container.component';

describe('EnquiryCreationContainerComponent', () => {
  let component: EnquiryCreationContainerComponent;
  let fixture: ComponentFixture<EnquiryCreationContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryCreationContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryCreationContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
