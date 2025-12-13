import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemDetailsTableComponent } from './item-details-table.component';

describe('ItemDetailsTableComponent', () => {
  let component: ItemDetailsTableComponent;
  let fixture: ComponentFixture<ItemDetailsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemDetailsTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemDetailsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
