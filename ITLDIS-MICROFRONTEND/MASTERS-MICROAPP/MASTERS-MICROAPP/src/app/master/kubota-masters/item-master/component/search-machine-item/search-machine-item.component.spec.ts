import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMachineItemComponent } from './search-machine-item.component';

describe('SearchMachineItemComponent', () => {
  let component: SearchMachineItemComponent;
  let fixture: ComponentFixture<SearchMachineItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchMachineItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMachineItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
