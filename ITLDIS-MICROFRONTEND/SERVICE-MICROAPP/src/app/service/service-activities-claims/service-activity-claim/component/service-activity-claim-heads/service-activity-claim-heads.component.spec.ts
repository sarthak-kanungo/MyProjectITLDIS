import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityClaimHeadsComponent } from './service-activity-claim-heads.component';

describe('ServiceActivityClaimHeadsComponent', () => {
  let component: ServiceActivityClaimHeadsComponent;
  let fixture: ComponentFixture<ServiceActivityClaimHeadsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityClaimHeadsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityClaimHeadsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
