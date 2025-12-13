import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceJobcardDetailsComponent } from './service-jobcard-details.component';

describe('ServiceJobcardDetailsComponent', () => {
  let component: ServiceJobcardDetailsComponent;
  let fixture: ComponentFixture<ServiceJobcardDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceJobcardDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceJobcardDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
