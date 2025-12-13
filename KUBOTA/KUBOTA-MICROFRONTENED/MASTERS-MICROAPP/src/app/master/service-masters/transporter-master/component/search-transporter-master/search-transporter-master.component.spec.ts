import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTransporterMasterComponent } from './search-transporter-master.component';

describe('SearchTransporterMasterComponent', () => {
  let component: SearchTransporterMasterComponent;
  let fixture: ComponentFixture<SearchTransporterMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchTransporterMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTransporterMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
