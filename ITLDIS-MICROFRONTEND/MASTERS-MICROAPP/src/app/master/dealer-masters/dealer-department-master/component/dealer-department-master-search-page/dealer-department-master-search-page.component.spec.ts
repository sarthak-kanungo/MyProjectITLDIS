import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerDepartmentMasterSearchPageComponent } from './dealer-department-master-search-page.component';

describe('DealerDepartmentMasterSearchPageComponent', () => {
  let component: DealerDepartmentMasterSearchPageComponent;
  let fixture: ComponentFixture<DealerDepartmentMasterSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerDepartmentMasterSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerDepartmentMasterSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
