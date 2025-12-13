import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchitldisUserComponent } from './search-itldis-user.component';

describe('SearchitldisUserComponent', () => {
  let component: SearchitldisUserComponent;
  let fixture: ComponentFixture<SearchitldisUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchitldisUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchitldisUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
