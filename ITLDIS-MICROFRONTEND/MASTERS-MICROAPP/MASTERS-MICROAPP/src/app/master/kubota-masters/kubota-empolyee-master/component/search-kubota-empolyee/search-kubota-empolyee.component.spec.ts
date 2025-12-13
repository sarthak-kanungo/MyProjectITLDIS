import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchitldisEmpolyeeComponent } from './search-itldis-empolyee.component';

describe('SearchitldisEmpolyeeComponent', () => {
  let component: SearchitldisEmpolyeeComponent;
  let fixture: ComponentFixture<SearchitldisEmpolyeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchitldisEmpolyeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchitldisEmpolyeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
