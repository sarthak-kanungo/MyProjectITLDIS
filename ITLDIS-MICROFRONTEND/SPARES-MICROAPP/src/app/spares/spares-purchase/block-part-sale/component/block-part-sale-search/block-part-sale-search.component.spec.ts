import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockPartSaleSearchComponent } from './block-part-sale-search.component';

describe('BlockPartSaleSearchComponent', () => {
  let component: BlockPartSaleSearchComponent;
  let fixture: ComponentFixture<BlockPartSaleSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlockPartSaleSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockPartSaleSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
