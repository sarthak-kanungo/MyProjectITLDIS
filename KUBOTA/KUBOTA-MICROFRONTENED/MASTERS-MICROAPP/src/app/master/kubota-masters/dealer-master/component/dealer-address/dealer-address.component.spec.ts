import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerAddressComponent } from './dealer-address.component';

describe('DealerAddressComponent', () => {
  let component: DealerAddressComponent;
  let fixture: ComponentFixture<DealerAddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerAddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
