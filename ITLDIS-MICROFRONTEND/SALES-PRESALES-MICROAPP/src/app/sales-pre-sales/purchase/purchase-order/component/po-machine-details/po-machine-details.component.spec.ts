import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoMachineDetailsComponent } from './po-machine-details.component';

describe('PoMachineDetailsComponent', () => {
  let component: PoMachineDetailsComponent;
  let fixture: ComponentFixture<PoMachineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoMachineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoMachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
