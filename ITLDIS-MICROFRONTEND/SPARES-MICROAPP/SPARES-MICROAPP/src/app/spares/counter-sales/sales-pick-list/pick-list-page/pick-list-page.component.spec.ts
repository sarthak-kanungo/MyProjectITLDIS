import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PickListPageComponent } from './pick-list-page.component';

describe('PickListPageComponent', () => {
  let component: PickListPageComponent;
  let fixture: ComponentFixture<PickListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PickListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PickListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
