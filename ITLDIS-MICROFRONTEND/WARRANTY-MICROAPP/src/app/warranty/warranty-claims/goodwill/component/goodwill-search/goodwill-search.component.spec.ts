import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodwillSearchComponent } from './goodwill-search.component';

describe('GoodwillSearchComponent', () => {
  let component: GoodwillSearchComponent;
  let fixture: ComponentFixture<GoodwillSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoodwillSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodwillSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
