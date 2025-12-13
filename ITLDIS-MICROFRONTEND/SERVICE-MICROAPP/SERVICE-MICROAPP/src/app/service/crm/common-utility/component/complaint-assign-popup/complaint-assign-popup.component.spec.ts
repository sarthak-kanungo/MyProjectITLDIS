import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintAssignPopupComponent } from './complaint-assign-popup.component';

describe('ComplaintAssignPopupComponent', () => {
  let component: ComplaintAssignPopupComponent;
  let fixture: ComponentFixture<ComplaintAssignPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComplaintAssignPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintAssignPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
