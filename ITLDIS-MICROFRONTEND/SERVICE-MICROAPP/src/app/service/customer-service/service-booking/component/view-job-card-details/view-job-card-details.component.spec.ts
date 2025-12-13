import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewJobCardDetailsComponent } from './view-job-card-details.component';

describe('ViewJobCardDetailsComponent', () => {
  let component: ViewJobCardDetailsComponent;
  let fixture: ComponentFixture<ViewJobCardDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewJobCardDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewJobCardDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
