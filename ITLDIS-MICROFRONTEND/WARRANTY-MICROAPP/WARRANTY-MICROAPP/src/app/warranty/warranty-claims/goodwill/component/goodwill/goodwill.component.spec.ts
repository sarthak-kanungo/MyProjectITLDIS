import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodwillComponent } from './goodwill.component';

describe('GoodwillComponent', () => {
  let component: GoodwillComponent;
  let fixture: ComponentFixture<GoodwillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoodwillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodwillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
