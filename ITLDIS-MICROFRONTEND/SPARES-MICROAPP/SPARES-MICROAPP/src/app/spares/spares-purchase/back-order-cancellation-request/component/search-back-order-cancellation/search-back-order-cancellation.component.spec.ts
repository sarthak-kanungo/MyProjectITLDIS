import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBackOrderCancellationComponent } from './search-back-order-cancellation.component';

describe('SearchBackOrderCancellationComponent', () => {
  let component: SearchBackOrderCancellationComponent;
  let fixture: ComponentFixture<SearchBackOrderCancellationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBackOrderCancellationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBackOrderCancellationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
