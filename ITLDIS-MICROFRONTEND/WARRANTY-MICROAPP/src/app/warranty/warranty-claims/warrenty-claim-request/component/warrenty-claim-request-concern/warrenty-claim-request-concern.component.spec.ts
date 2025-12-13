import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarrentyClaimRequestConcernComponent } from './warrenty-claim-request-concern.component';

describe('WarrentyClaimRequestConcernComponent', () => {
  let component: WarrentyClaimRequestConcernComponent;
  let fixture: ComponentFixture<WarrentyClaimRequestConcernComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarrentyClaimRequestConcernComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarrentyClaimRequestConcernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
