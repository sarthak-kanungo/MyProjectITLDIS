import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BinningSlipContainerSearchComponent } from './binning-slip-container-search.component';

describe('BinningSlipContainerSearchComponent', () => {
  let component: BinningSlipContainerSearchComponent;
  let fixture: ComponentFixture<BinningSlipContainerSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BinningSlipContainerSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BinningSlipContainerSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
