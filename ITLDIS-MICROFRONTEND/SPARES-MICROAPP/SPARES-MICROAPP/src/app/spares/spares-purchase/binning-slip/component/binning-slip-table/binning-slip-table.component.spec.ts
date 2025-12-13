import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BinningSlipTableComponent } from './binning-slip-table.component';

describe('BinningSlipTableComponent', () => {
  let component: BinningSlipTableComponent;
  let fixture: ComponentFixture<BinningSlipTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BinningSlipTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BinningSlipTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
