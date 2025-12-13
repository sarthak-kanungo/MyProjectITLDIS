import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductInterestedV2Component } from './product-interested-v2.component';

describe('ProductInterestedV2Component', () => {
  let component: ProductInterestedV2Component;
  let fixture: ComponentFixture<ProductInterestedV2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductInterestedV2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductInterestedV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
