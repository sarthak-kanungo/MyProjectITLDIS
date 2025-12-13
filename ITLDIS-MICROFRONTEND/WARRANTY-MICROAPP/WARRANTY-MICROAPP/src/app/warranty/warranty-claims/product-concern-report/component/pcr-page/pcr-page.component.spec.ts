import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcrPageComponent } from './pcr-page.component';

describe('PcrPageComponent', () => {
  let component: PcrPageComponent;
  let fixture: ComponentFixture<PcrPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcrPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcrPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
