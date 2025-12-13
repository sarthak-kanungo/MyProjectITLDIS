import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstallationSearchComponent } from './installation-search.component';

describe('InstallationSearchComponent', () => {
  let component: InstallationSearchComponent;
  let fixture: ComponentFixture<InstallationSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstallationSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstallationSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
