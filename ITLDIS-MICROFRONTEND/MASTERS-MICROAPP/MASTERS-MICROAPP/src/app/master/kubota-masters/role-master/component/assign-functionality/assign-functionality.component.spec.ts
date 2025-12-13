import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignFunctionalityComponent } from './assign-functionality.component';

describe('AssignFunctionalityComponent', () => {
  let component: AssignFunctionalityComponent;
  let fixture: ComponentFixture<AssignFunctionalityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignFunctionalityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignFunctionalityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
