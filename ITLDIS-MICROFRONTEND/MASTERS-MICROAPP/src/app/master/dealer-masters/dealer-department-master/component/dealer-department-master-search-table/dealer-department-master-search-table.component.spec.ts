import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerDepartmentMasterSearchTableComponent } from './dealer-department-master-search-table.component';

describe('DealerDepartmentMasterSearchTableComponent', () => {
  let component: DealerDepartmentMasterSearchTableComponent;
  let fixture: ComponentFixture<DealerDepartmentMasterSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerDepartmentMasterSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerDepartmentMasterSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
