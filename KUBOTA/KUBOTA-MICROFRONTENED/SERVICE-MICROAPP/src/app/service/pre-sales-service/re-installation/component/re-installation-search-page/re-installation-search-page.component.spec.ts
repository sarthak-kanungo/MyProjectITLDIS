import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReInstallationSearchPageComponent } from './re-installation-search-page.component';

describe('ReInstallationSearchPageComponent', () => {
  let component: ReInstallationSearchPageComponent;
  let fixture: ComponentFixture<ReInstallationSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReInstallationSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReInstallationSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
