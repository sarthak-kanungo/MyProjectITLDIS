import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryChallanCreateComponent } from './delivery-challan-create.component';

describe('DeliveryChallanCreateComponent', () => {
  let component: DeliveryChallanCreateComponent;
  let fixture: ComponentFixture<DeliveryChallanCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryChallanCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryChallanCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
