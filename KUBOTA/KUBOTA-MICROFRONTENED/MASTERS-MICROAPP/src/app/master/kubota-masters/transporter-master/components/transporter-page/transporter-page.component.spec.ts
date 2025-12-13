import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransporterPageComponent } from './transporter-page.component';

describe('TransporterPageComponent', () => {
  let component: TransporterPageComponent;
  let fixture: ComponentFixture<TransporterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransporterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransporterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
