import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPickingSlipComponent } from './search-picking-slip.component';

describe('SearchPickingSlipComponent', () => {
  let component: SearchPickingSlipComponent;
  let fixture: ComponentFixture<SearchPickingSlipComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPickingSlipComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPickingSlipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
