import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDealerUserComponent } from './search-dealer-user.component';

describe('SearchDealerUserComponent', () => {
  let component: SearchDealerUserComponent;
  let fixture: ComponentFixture<SearchDealerUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDealerUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDealerUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
