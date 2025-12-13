import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuoatProspectDetailsComponent } from './quoat-prospect-details.component';

describe('QuoatProspectDetailsComponent', () => {
  let component: QuoatProspectDetailsComponent;
  let fixture: ComponentFixture<QuoatProspectDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuoatProspectDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuoatProspectDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
