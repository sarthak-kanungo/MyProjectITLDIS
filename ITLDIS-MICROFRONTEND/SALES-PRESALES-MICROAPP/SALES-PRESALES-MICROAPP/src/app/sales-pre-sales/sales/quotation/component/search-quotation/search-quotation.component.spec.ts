import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchQuotationComponent } from './search-quotation.component';

describe('SearchQuotationComponent', () => {
  let component: SearchQuotationComponent;
  let fixture: ComponentFixture<SearchQuotationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchQuotationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchQuotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
