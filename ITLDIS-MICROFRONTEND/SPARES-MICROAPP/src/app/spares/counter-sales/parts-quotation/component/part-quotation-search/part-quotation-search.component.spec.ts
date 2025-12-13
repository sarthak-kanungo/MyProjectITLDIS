import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartQuotationSearchComponent } from './part-quotation-search.component';

describe('PartQuotationSearchComponent', () => {
  let component: PartQuotationSearchComponent;
  let fixture: ComponentFixture<PartQuotationSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartQuotationSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartQuotationSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
