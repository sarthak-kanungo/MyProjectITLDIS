import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HoursPopUpComponent } from './hours-pop-up.component';

describe('HoursPopUpComponent', () => {
  let component: HoursPopUpComponent;
  let fixture: ComponentFixture<HoursPopUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HoursPopUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HoursPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
