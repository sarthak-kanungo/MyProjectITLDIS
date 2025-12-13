import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerDepartmentMasterComponent } from './dealer-department-master.component';

describe('DealerDepartmentMasterComponent', () => {
  let component: DealerDepartmentMasterComponent;
  let fixture: ComponentFixture<DealerDepartmentMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerDepartmentMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerDepartmentMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
