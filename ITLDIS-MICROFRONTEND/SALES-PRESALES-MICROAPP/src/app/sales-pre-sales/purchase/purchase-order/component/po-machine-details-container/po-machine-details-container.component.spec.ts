import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoMachineDetailsContainerComponent } from './po-machine-details-container.component';

describe('PoMachineDetailsContainerComponent', () => {
  let component: PoMachineDetailsContainerComponent;
  let fixture: ComponentFixture<PoMachineDetailsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoMachineDetailsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoMachineDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
