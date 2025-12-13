import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveSchemesClaimCreateComponent } from './incentive-schemes-claim-create.component';

describe('IncentiveSchemesClaimCreateComponent', () => {
  let component: IncentiveSchemesClaimCreateComponent;
  let fixture: ComponentFixture<IncentiveSchemesClaimCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveSchemesClaimCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveSchemesClaimCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
