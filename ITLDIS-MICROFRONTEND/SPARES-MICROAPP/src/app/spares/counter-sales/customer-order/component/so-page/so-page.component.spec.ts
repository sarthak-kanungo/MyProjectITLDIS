import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SoPageComponent } from './so-page.component';

describe('SoPageComponent', () => {
  let component: SoPageComponent;
  let fixture: ComponentFixture<SoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
