import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimListComponent } from './service-claim-list.component';

describe('ServiceClaimListComponent', () => {
  let component: ServiceClaimListComponent;
  let fixture: ComponentFixture<ServiceClaimListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
