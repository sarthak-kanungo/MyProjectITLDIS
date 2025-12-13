import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDesignationMasterComponent } from './search-designation-master.component';

describe('SearchDesignationMasterComponent', () => {
  let component: SearchDesignationMasterComponent;
  let fixture: ComponentFixture<SearchDesignationMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDesignationMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDesignationMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
