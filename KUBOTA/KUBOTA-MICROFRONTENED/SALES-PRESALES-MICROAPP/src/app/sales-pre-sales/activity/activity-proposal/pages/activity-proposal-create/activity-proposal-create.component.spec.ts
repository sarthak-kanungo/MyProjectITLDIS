import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityProposalCreateComponent } from './activity-proposal-create.component';

describe('ActivityProposalCreateComponent', () => {
  let component: ActivityProposalCreateComponent;
  let fixture: ComponentFixture<ActivityProposalCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityProposalCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityProposalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
