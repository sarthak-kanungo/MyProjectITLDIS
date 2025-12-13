import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceProspectDetailsContainerComponent } from './invoice-prospect-details-container.component';

describe('InvoiceProspectDetailsContainerComponent', () => {
  let component: InvoiceProspectDetailsContainerComponent;
  let fixture: ComponentFixture<InvoiceProspectDetailsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceProspectDetailsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceProspectDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
