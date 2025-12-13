import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDesignationLevelComponent } from './search-designation-level.component';

describe('SearchDesignationLevelComponent', () => {
  let component: SearchDesignationLevelComponent;
  let fixture: ComponentFixture<SearchDesignationLevelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDesignationLevelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDesignationLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
