import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceSearchTableContainerComponent } from './invoice-search-table-container.component';

describe('InvoiceSearchTableContainerComponent', () => {
  let component: InvoiceSearchTableContainerComponent;
  let fixture: ComponentFixture<InvoiceSearchTableContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceSearchTableContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceSearchTableContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
