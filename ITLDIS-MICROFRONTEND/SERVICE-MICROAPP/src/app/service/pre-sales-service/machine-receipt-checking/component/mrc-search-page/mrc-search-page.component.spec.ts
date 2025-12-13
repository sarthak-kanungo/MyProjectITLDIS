import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MrcSearchPageComponent } from './mrc-search-page.component';

describe('MrcSearchPageComponent', () => {
  let component: MrcSearchPageComponent;
  let fixture: ComponentFixture<MrcSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MrcSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MrcSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
