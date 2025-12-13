import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpolyeeDetailsComponent } from './empolyee-details.component';

describe('EmpolyeeDetailsComponent', () => {
  let component: EmpolyeeDetailsComponent;
  let fixture: ComponentFixture<EmpolyeeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpolyeeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpolyeeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
