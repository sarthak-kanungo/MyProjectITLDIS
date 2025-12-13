import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDescripancyClaimApprovalComponent } from './search-descripancy-claim-approval.component';

describe('SearchDescripancyClaimApprovalComponent', () => {
  let component: SearchDescripancyClaimApprovalComponent;
  let fixture: ComponentFixture<SearchDescripancyClaimApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDescripancyClaimApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDescripancyClaimApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
