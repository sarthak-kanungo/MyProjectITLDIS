import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineDescripancyComplaintsCreateComponent } from './machine-descripancy-complaints-create.component';

describe('MachineDescripancyComplaintsCreateComponent', () => {
  let component: MachineDescripancyComplaintsCreateComponent;
  let fixture: ComponentFixture<MachineDescripancyComplaintsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineDescripancyComplaintsCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineDescripancyComplaintsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
