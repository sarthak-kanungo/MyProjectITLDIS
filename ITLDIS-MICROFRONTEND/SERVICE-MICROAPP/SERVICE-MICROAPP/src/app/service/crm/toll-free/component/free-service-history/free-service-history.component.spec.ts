import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreeServiceHistoryComponent } from './free-service-history.component';

describe('FreeServiceHistoryComponent', () => {
  let component: FreeServiceHistoryComponent;
  let fixture: ComponentFixture<FreeServiceHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreeServiceHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreeServiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
