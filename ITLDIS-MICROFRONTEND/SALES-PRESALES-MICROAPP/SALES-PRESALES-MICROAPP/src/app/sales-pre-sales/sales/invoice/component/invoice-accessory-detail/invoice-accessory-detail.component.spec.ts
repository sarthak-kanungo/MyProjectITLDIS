import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceAccessoryDetailComponent } from './invoice-accessory-detail.component';

describe('InvoiceAccessoryDetailComponent', () => {
  let component: InvoiceAccessoryDetailComponent;
  let fixture: ComponentFixture<InvoiceAccessoryDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceAccessoryDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceAccessoryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
