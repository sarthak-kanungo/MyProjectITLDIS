import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpareDeliveryChallanReturnComponent } from './spare-delivery-challan-return.component';

describe('SpareDeliveryChallanReturnComponent', () => {
  let component: SpareDeliveryChallanReturnComponent;
  let fixture: ComponentFixture<SpareDeliveryChallanReturnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpareDeliveryChallanReturnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpareDeliveryChallanReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
