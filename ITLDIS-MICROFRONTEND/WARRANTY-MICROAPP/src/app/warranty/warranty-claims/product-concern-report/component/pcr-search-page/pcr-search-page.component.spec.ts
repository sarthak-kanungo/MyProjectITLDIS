import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcrSearchPageComponent } from './pcr-search-page.component';

describe('PcrSearchPageComponent', () => {
  let component: PcrSearchPageComponent;
  let fixture: ComponentFixture<PcrSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcrSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcrSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
