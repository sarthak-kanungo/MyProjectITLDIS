import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReInstallationPageComponent } from './re-installation-page.component';

describe('ReInstallationPageComponent', () => {
  let component: ReInstallationPageComponent;
  let fixture: ComponentFixture<ReInstallationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReInstallationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReInstallationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
