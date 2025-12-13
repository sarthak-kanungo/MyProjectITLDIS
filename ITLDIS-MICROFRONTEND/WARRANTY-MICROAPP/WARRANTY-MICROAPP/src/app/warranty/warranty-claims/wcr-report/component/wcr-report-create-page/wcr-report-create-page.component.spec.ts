import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WcrReportCreatePageComponent } from './wcr-report-create-page.component';

describe('WcrReportCreatePageComponent', () => {
  let component: WcrReportCreatePageComponent;
  let fixture: ComponentFixture<WcrReportCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WcrReportCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WcrReportCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
