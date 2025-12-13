import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransporterMasterSearchComponent } from './transporter-master-search.component';

describe('TransporterMasterSearchComponent', () => {
  let component: TransporterMasterSearchComponent;
  let fixture: ComponentFixture<TransporterMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransporterMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransporterMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
