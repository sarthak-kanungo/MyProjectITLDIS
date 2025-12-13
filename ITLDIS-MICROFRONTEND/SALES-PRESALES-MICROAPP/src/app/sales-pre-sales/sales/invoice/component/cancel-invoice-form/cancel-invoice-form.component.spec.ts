import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelInvoiceFormComponent } from './cancel-invoice-form.component';

describe('CancelInvoiceFormComponent', () => {
  let component: CancelInvoiceFormComponent;
  let fixture: ComponentFixture<CancelInvoiceFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancelInvoiceFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelInvoiceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
