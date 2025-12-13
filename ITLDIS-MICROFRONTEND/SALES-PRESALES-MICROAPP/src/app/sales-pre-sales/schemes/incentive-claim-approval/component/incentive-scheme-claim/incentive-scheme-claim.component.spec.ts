import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveSchemeClaimComponent } from './incentive-scheme-claim.component';

describe('IncentiveSchemeClaimComponent', () => {
  let component: IncentiveSchemeClaimComponent;
  let fixture: ComponentFixture<IncentiveSchemeClaimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveSchemeClaimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveSchemeClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
