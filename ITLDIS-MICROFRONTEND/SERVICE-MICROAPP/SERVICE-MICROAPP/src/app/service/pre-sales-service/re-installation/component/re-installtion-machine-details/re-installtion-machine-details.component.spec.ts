import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReInstalltionMachineDetailsComponent } from './re-installtion-machine-details.component';

describe('ReInstalltionMachineDetailsComponent', () => {
  let component: ReInstalltionMachineDetailsComponent;
  let fixture: ComponentFixture<ReInstalltionMachineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReInstalltionMachineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReInstalltionMachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
