import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarrentyClaimRequestImplementComponent } from './warrenty-claim-request-implement.component';

describe('WarrentyClaimRequestImplementComponent', () => {
  let component: WarrentyClaimRequestImplementComponent;
  let fixture: ComponentFixture<WarrentyClaimRequestImplementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarrentyClaimRequestImplementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarrentyClaimRequestImplementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
