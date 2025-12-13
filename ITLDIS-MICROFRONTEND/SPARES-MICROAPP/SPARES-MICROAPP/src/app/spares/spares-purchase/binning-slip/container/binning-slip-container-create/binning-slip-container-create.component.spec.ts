import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BinningSlipContainerCreateComponent } from './binning-slip-container-create.component';

describe('BinningSlipContainerCreateComponent', () => {
  let component: BinningSlipContainerCreateComponent;
  let fixture: ComponentFixture<BinningSlipContainerCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BinningSlipContainerCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BinningSlipContainerCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
