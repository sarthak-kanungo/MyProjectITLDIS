import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityClaimDetailsComponent } from './service-activity-claim-details.component';

describe('ServiceActivityClaimDetailsComponent', () => {
  let component: ServiceActivityClaimDetailsComponent;
  let fixture: ComponentFixture<ServiceActivityClaimDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityClaimDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityClaimDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
