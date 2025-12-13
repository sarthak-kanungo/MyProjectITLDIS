import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicInstallationDetailsComponent } from './basic-installation-details.component';

describe('BasicInstallationDetailsComponent', () => {
  let component: BasicInstallationDetailsComponent;
  let fixture: ComponentFixture<BasicInstallationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasicInstallationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicInstallationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
