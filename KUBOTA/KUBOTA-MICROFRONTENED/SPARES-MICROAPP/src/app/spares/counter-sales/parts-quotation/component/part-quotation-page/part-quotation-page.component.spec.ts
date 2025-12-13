import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartQuotationPageComponent } from './part-quotation-page.component';

describe('PartQuotationPageComponent', () => {
  let component: PartQuotationPageComponent;
  let fixture: ComponentFixture<PartQuotationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartQuotationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartQuotationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
