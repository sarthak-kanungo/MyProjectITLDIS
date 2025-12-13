import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReInstallationSearchComponent } from './re-installation-search.component';

describe('ReInstallationSearchComponent', () => {
  let component: ReInstallationSearchComponent;
  let fixture: ComponentFixture<ReInstallationSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReInstallationSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReInstallationSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
