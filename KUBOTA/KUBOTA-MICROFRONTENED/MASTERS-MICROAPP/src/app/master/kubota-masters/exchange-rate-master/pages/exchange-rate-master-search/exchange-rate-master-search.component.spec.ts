import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeRateMasterSearchComponent } from './exchange-rate-master-search.component';

describe('ExchangeRateMasterSearchComponent', () => {
  let component: ExchangeRateMasterSearchComponent;
  let fixture: ComponentFixture<ExchangeRateMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExchangeRateMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeRateMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
