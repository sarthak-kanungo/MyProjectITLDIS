import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryChallanReturnContainerComponent } from './delivery-challan-return-container.component';

describe('DeliveryChallanReturnContainerComponent', () => {
  let component: DeliveryChallanReturnContainerComponent;
  let fixture: ComponentFixture<DeliveryChallanReturnContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryChallanReturnContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryChallanReturnContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
