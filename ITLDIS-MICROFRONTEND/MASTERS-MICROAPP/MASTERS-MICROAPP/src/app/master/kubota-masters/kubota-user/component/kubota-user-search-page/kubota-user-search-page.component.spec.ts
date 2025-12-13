import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisUserSearchPageComponent } from './itldis-user-search-page.component';

describe('itldisUserSearchPageComponent', () => {
  let component: itldisUserSearchPageComponent;
  let fixture: ComponentFixture<itldisUserSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisUserSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisUserSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
