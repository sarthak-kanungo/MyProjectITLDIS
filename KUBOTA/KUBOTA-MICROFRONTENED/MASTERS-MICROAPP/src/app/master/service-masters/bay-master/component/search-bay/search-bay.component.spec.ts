import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBayComponent } from './search-bay.component';

describe('SearchBayComponent', () => {
  let component: SearchBayComponent;
  let fixture: ComponentFixture<SearchBayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
