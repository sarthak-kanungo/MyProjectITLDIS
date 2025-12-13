import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WpdcPageComponent } from './wpdc-page.component';

describe('WpdcPageComponent', () => {
  let component: WpdcPageComponent;
  let fixture: ComponentFixture<WpdcPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WpdcPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WpdcPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
