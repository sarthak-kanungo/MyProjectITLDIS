import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpareDeliveryChallanReturnSearchComponent } from './spare-delivery-challan-return-search.component';

describe('SpareDeliveryChallanReturnSearchComponent', () => {
  let component: SpareDeliveryChallanReturnSearchComponent;
  let fixture: ComponentFixture<SpareDeliveryChallanReturnSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpareDeliveryChallanReturnSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpareDeliveryChallanReturnSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
