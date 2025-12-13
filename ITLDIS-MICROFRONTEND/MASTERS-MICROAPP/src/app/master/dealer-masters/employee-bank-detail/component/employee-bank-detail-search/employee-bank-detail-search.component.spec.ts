import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeBankDetailSearchComponent } from './employee-bank-detail-search.component';

describe('EmployeeBankDetailSearchComponent', () => {
  let component: EmployeeBankDetailSearchComponent;
  let fixture: ComponentFixture<EmployeeBankDetailSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeBankDetailSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeBankDetailSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
