import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMachineDescripancyComplaintsListComponent } from './search-machine-descripancy-complaints-list.component';

describe('SearchMachineDescripancyComplaintsListComponent', () => {
  let component: SearchMachineDescripancyComplaintsListComponent;
  let fixture: ComponentFixture<SearchMachineDescripancyComplaintsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchMachineDescripancyComplaintsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMachineDescripancyComplaintsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
