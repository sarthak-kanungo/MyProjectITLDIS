import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineFormfComponent } from './machine-formf.component';

describe('MachineFormfComponent', () => {
  let component: MachineFormfComponent;
  let fixture: ComponentFixture<MachineFormfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineFormfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineFormfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
