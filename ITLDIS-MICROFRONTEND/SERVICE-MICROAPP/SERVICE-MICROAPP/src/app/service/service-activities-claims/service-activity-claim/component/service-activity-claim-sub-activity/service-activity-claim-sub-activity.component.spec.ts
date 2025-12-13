import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityClaimSubActivityComponent } from './service-activity-claim-sub-activity.component';

describe('ServiceActivityClaimSubActivityComponent', () => {
  let component: ServiceActivityClaimSubActivityComponent;
  let fixture: ComponentFixture<ServiceActivityClaimSubActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityClaimSubActivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityClaimSubActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
