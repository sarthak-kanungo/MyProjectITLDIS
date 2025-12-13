import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintOrQueryResolutionCreatePageComponent } from './complaint-or-query-resolution-create-page.component';

describe('ComplaintOrQueryResolutionCreatePageComponent', () => {
  let component: ComplaintOrQueryResolutionCreatePageComponent;
  let fixture: ComponentFixture<ComplaintOrQueryResolutionCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComplaintOrQueryResolutionCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintOrQueryResolutionCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
