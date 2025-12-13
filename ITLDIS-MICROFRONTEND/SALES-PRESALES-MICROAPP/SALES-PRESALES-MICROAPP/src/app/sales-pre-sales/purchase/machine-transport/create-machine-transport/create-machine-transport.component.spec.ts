import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMachineTransportComponent } from './create-machine-transport.component';

describe('CreateMachineTransportComponent', () => {
  let component: CreateMachineTransportComponent;
  let fixture: ComponentFixture<CreateMachineTransportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMachineTransportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMachineTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
