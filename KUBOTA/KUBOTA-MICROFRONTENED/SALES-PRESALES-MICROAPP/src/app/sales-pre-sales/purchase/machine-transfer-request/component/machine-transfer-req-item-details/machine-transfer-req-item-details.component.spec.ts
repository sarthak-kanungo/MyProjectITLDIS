import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineTransferReqItemDetailsComponent } from './machine-transfer-req-item-details.component';

describe('MachineTransferReqItemDetailsComponent', () => {
  let component: MachineTransferReqItemDetailsComponent;
  let fixture: ComponentFixture<MachineTransferReqItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineTransferReqItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineTransferReqItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
