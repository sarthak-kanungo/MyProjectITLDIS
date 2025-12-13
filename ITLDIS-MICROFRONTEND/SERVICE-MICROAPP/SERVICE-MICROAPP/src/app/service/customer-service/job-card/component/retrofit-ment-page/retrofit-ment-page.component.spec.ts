import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrofitMentPageComponent } from './retrofit-ment-page.component';

describe('RetrofitMentPageComponent', () => {
  let component: RetrofitMentPageComponent;
  let fixture: ComponentFixture<RetrofitMentPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetrofitMentPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetrofitMentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
