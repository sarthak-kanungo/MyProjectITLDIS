import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherMachineDetailsComponent } from './other-machine-details.component';

describe('OtherMachineDetailsComponent', () => {
  let component: OtherMachineDetailsComponent;
  let fixture: ComponentFixture<OtherMachineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OtherMachineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OtherMachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
