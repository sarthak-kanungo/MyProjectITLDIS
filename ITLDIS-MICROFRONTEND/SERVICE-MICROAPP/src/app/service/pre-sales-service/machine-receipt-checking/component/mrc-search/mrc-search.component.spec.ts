import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MrcSearchComponent } from './mrc-search.component';

describe('MrcSearchComponent', () => {
  let component: MrcSearchComponent;
  let fixture: ComponentFixture<MrcSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MrcSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MrcSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
