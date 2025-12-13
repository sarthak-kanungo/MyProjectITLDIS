import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyMasterPageComponent } from './party-master-page.component';

describe('PartyMasterPageComponent', () => {
  let component: PartyMasterPageComponent;
  let fixture: ComponentFixture<PartyMasterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartyMasterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartyMasterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
