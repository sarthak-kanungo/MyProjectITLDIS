import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstallationSearchPageComponent } from './installation-search-page.component';

describe('InstallationSearchPageComponent', () => {
  let component: InstallationSearchPageComponent;
  let fixture: ComponentFixture<InstallationSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstallationSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstallationSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
