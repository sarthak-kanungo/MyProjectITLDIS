import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailIncentiveSchemeDetailsComponent } from './retail-incentive-scheme-details.component';

describe('RetailIncentiveSchemeDetailsComponent', () => {
  let component: RetailIncentiveSchemeDetailsComponent;
  let fixture: ComponentFixture<RetailIncentiveSchemeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailIncentiveSchemeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailIncentiveSchemeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
