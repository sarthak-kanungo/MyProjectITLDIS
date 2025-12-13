import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreDeliveryCheckComponent } from './pre-delivery-check.component';

describe('PreDeliveryCheckComponent', () => {
  let component: PreDeliveryCheckComponent;
  let fixture: ComponentFixture<PreDeliveryCheckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreDeliveryCheckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreDeliveryCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
