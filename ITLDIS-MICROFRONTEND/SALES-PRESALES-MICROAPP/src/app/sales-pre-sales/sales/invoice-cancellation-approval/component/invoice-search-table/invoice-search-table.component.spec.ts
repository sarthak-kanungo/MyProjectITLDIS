import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceSearchTableComponent } from './invoice-search-table.component';

describe('InvoiceSearchTableComponent', () => {
  let component: InvoiceSearchTableComponent;
  let fixture: ComponentFixture<InvoiceSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
