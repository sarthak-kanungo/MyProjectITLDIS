import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMtrListContainerComponent } from './search-mtr-list-container.component';

describe('SearchMtrListContainerComponent', () => {
  let component: SearchMtrListContainerComponent;
  let fixture: ComponentFixture<SearchMtrListContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchMtrListContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMtrListContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
