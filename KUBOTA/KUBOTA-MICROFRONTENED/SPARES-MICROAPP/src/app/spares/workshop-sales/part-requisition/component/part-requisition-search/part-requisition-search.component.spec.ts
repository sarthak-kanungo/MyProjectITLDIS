import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartRequisitionSearchComponent } from './part-requisition-search.component';

describe('PartRequisitionSearchComponent', () => {
  let component: PartRequisitionSearchComponent;
  let fixture: ComponentFixture<PartRequisitionSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartRequisitionSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartRequisitionSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
