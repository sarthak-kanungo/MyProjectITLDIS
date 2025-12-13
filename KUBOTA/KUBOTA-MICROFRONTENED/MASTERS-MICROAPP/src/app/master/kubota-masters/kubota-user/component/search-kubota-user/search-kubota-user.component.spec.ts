import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchKubotaUserComponent } from './search-kubota-user.component';

describe('SearchKubotaUserComponent', () => {
  let component: SearchKubotaUserComponent;
  let fixture: ComponentFixture<SearchKubotaUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchKubotaUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchKubotaUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
