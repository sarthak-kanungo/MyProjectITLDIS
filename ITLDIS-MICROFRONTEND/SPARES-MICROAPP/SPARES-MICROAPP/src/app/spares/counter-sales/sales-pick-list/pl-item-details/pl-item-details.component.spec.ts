import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlItemDetailsComponent } from './pl-item-details.component';

describe('PlItemDetailsComponent', () => {
  let component: PlItemDetailsComponent;
  let fixture: ComponentFixture<PlItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
