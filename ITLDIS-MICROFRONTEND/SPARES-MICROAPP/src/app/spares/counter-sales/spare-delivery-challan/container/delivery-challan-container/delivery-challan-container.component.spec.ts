import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryChallanContainerComponent } from './delivery-challan-container.component';

describe('DeliveryChallanContainerComponent', () => {
  let component: DeliveryChallanContainerComponent;
  let fixture: ComponentFixture<DeliveryChallanContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryChallanContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryChallanContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
