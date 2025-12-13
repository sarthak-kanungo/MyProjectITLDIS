import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketIntelligenceCreateComponent } from './market-intelligence-create.component';

describe('MarketIntelligenceCreateComponent', () => {
  let component: MarketIntelligenceCreateComponent;
  let fixture: ComponentFixture<MarketIntelligenceCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketIntelligenceCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketIntelligenceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
