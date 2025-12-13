import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutsideLabourChargesComponent } from './outside-labour-charges.component';

describe('OutsideLabourChargesComponent', () => {
  let component: OutsideLabourChargesComponent;
  let fixture: ComponentFixture<OutsideLabourChargesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutsideLabourChargesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutsideLabourChargesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
