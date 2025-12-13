import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineStockSearchPageComponent } from './machine-stock-search-page.component';

describe('MachineStockSearchPageComponent', () => {
  let component: MachineStockSearchPageComponent;
  let fixture: ComponentFixture<MachineStockSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineStockSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineStockSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
