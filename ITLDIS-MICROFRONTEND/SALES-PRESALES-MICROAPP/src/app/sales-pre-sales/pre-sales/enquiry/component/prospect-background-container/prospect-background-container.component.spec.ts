import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProspectBackgroundContainerComponent } from './prospect-background-container.component';

describe('ProspectBackgroundContainerComponent', () => {
  let component: ProspectBackgroundContainerComponent;
  let fixture: ComponentFixture<ProspectBackgroundContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProspectBackgroundContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProspectBackgroundContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
