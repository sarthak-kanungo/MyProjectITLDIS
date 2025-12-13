import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityClaimCreationComponent } from './activity-claim-creation.component';

describe('ActivityClaimCreationComponent', () => {
  let component: ActivityClaimCreationComponent;
  let fixture: ComponentFixture<ActivityClaimCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityClaimCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityClaimCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
