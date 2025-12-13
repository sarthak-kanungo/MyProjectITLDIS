import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PdcPageComponent } from './pdc-page.component';

describe('PdcPageComponent', () => {
  let component: PdcPageComponent;
  let fixture: ComponentFixture<PdcPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PdcPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PdcPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
