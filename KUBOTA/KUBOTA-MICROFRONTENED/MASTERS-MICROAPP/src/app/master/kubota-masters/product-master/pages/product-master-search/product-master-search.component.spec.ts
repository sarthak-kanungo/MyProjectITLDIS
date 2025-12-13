import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductMasterSearchComponent } from './product-master-search.component';

describe('ProductMasterSearchComponent', () => {
  let component: ProductMasterSearchComponent;
  let fixture: ComponentFixture<ProductMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
