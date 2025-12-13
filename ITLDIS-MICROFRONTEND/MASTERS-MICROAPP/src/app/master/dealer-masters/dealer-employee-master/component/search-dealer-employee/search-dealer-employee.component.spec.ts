import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDealerEmployeeComponent } from './search-dealer-employee.component';

describe('SearchDealerEmployeeComponent', () => {
  let component: SearchDealerEmployeeComponent;
  let fixture: ComponentFixture<SearchDealerEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDealerEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDealerEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
