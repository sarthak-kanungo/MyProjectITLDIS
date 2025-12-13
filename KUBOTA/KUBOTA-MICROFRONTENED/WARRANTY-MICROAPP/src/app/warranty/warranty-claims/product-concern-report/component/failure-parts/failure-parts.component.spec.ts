import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FailurePartsComponent } from './failure-parts.component';

describe('FailurePartsComponent', () => {
  let component: FailurePartsComponent;
  let fixture: ComponentFixture<FailurePartsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FailurePartsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FailurePartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
