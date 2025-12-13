import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineItemSearchResultComponent } from './machine-item-search-result.component';

describe('MachineItemSearchResultComponent', () => {
  let component: MachineItemSearchResultComponent;
  let fixture: ComponentFixture<MachineItemSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineItemSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineItemSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
