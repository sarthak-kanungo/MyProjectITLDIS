import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAllotmentDeAllotmentComponent } from './search-allotment-de-allotment.component';

describe('SearchAllotmentDeAllotmentComponent', () => {
  let component: SearchAllotmentDeAllotmentComponent;
  let fixture: ComponentFixture<SearchAllotmentDeAllotmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAllotmentDeAllotmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAllotmentDeAllotmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
