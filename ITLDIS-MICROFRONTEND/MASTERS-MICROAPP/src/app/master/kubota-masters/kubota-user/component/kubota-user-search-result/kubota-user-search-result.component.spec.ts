import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisUserSearchResultComponent } from './itldis-user-search-result.component';

describe('itldisUserSearchResultComponent', () => {
  let component: itldisUserSearchResultComponent;
  let fixture: ComponentFixture<itldisUserSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisUserSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisUserSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
