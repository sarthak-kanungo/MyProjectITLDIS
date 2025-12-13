import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransporterMasterSearchResultComponent } from './transporter-master-search-result.component';

describe('TransporterMasterSearchResultComponent', () => {
  let component: TransporterMasterSearchResultComponent;
  let fixture: ComponentFixture<TransporterMasterSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransporterMasterSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransporterMasterSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
