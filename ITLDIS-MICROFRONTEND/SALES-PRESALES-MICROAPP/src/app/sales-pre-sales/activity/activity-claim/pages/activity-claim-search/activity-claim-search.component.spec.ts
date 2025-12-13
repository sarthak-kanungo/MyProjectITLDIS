import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityClaimSearchComponent } from './activity-claim-search.component';

describe('ActivityClaimSearchComponent', () => {
  let component: ActivityClaimSearchComponent;
  let fixture: ComponentFixture<ActivityClaimSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityClaimSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityClaimSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
