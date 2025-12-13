import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceVerifyModalComponent } from './invoice-verify-modal.component';

describe('InvoiceVerifyModalComponent', () => {
  let component: InvoiceVerifyModalComponent;
  let fixture: ComponentFixture<InvoiceVerifyModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceVerifyModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceVerifyModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
