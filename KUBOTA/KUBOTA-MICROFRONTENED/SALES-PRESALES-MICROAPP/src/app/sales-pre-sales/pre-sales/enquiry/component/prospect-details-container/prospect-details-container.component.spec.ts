import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProspectDetailsContainerComponent } from './prospect-details-container.component';

describe('ProspectDetailsContainerComponent', () => {
  let component: ProspectDetailsContainerComponent;
  let fixture: ComponentFixture<ProspectDetailsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProspectDetailsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProspectDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
