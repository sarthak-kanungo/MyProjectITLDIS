import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMachineTransportComponent } from './search-machine-transport.component';

describe('SearchMachineTransportComponent', () => {
  let component: SearchMachineTransportComponent;
  let fixture: ComponentFixture<SearchMachineTransportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchMachineTransportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchMachineTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
