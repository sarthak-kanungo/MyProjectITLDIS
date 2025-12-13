import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartRequisitionComponent } from './part-requisition.component';

describe('PartRequisitionComponent', () => {
  let component: PartRequisitionComponent;
  let fixture: ComponentFixture<PartRequisitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartRequisitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartRequisitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
