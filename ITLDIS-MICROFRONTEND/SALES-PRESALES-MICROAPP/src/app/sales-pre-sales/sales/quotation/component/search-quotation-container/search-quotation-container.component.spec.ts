import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchQuotationContainerComponent } from './search-quotation-container.component';

describe('SearchQuotationContainerComponent', () => {
  let component: SearchQuotationContainerComponent;
  let fixture: ComponentFixture<SearchQuotationContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchQuotationContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchQuotationContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
