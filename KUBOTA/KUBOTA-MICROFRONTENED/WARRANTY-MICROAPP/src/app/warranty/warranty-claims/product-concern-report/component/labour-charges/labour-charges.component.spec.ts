import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LabourChargesComponent } from './labour-charges.component';

describe('LabourChargesComponent', () => {
  let component: LabourChargesComponent;
  let fixture: ComponentFixture<LabourChargesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LabourChargesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LabourChargesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
