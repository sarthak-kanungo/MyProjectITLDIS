import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryChallansContainerComponent } from './delivery-challans-container.component';

describe('DeliveryChallansContainerComponent', () => {
  let component: DeliveryChallansContainerComponent;
  let fixture: ComponentFixture<DeliveryChallansContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryChallansContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryChallansContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
