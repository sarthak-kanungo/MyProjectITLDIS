import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoSearchResultComponent } from './po-search-result.component';

describe('PoSearchResultComponent', () => {
  let component: PoSearchResultComponent;
  let fixture: ComponentFixture<PoSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
