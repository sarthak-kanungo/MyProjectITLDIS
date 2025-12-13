import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketingActivityMasterSearchComponent } from './marketing-activity-master-search.component';

describe('MarketingActivityMasterSearchComponent', () => {
  let component: MarketingActivityMasterSearchComponent;
  let fixture: ComponentFixture<MarketingActivityMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketingActivityMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketingActivityMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
