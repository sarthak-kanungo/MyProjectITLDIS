import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveSchemesClaimSearchComponent } from './incentive-schemes-claim-search.component';

describe('IncentiveSchemesClaimSearchComponent', () => {
  let component: IncentiveSchemesClaimSearchComponent;
  let fixture: ComponentFixture<IncentiveSchemesClaimSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveSchemesClaimSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveSchemesClaimSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
