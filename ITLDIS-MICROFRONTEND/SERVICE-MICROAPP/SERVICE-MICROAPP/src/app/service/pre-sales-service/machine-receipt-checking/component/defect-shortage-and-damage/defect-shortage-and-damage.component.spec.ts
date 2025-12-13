import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DefectShortageAndDamageComponent } from './defect-shortage-and-damage.component';

describe('DefectShortageAndDamageComponent', () => {
  let component: DefectShortageAndDamageComponent;
  let fixture: ComponentFixture<DefectShortageAndDamageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DefectShortageAndDamageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DefectShortageAndDamageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
