import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimSearchComponent } from './service-claim-search.component';

describe('ServiceClaimSearchComponent', () => {
  let component: ServiceClaimSearchComponent;
  let fixture: ComponentFixture<ServiceClaimSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
