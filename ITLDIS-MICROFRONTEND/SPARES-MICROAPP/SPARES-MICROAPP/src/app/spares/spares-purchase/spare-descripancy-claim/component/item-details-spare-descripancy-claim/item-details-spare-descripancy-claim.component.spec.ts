import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemDetailsSpareDescripancyClaimComponent } from './item-details-spare-descripancy-claim.component';

describe('ItemDetailsSpareDescripancyClaimComponent', () => {
  let component: ItemDetailsSpareDescripancyClaimComponent;
  let fixture: ComponentFixture<ItemDetailsSpareDescripancyClaimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemDetailsSpareDescripancyClaimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemDetailsSpareDescripancyClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
