import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SapPageComponent } from './sap-page.component';

describe('SapPageComponent', () => {
  let component: SapPageComponent;
  let fixture: ComponentFixture<SapPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SapPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SapPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
