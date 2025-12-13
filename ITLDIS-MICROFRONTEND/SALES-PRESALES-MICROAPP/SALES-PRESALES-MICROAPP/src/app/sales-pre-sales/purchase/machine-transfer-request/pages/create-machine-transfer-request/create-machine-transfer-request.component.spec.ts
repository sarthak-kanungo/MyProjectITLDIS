import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMachineTransferRequestComponent } from './create-machine-transfer-request.component';

describe('CreateMachineTransferRequestComponent', () => {
  let component: CreateMachineTransferRequestComponent;
  let fixture: ComponentFixture<CreateMachineTransferRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMachineTransferRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMachineTransferRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
