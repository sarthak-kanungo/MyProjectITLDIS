import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodwillConcernComponent } from './goodwill-concern.component';

describe('GoodwillConcernComponent', () => {
  let component: GoodwillConcernComponent;
  let fixture: ComponentFixture<GoodwillConcernComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoodwillConcernComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodwillConcernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
