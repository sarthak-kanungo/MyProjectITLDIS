import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityClaimCreateComponent } from './activity-claim-create.component';

describe('ActivityClaimCreateComponent', () => {
  let component: ActivityClaimCreateComponent;
  let fixture: ComponentFixture<ActivityClaimCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityClaimCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityClaimCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
