import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimSearchResultComponent } from './service-claim-search-result.component';

describe('ServiceClaimSearchResultComponent', () => {
  let component: ServiceClaimSearchResultComponent;
  let fixture: ComponentFixture<ServiceClaimSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
