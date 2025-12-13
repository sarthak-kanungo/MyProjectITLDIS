import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisEmpolyeeSearchResultComponent } from './itldis-empolyee-search-result.component';

describe('itldisEmpolyeeSearchResultComponent', () => {
  let component: itldisEmpolyeeSearchResultComponent;
  let fixture: ComponentFixture<itldisEmpolyeeSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisEmpolyeeSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisEmpolyeeSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
