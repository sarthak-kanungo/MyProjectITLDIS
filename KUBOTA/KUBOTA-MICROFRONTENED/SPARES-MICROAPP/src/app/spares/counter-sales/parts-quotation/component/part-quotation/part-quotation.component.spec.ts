import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartQuotationComponent } from './part-quotation.component';

describe('PartQuotationComponent', () => {
  let component: PartQuotationComponent;
  let fixture: ComponentFixture<PartQuotationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartQuotationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartQuotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
