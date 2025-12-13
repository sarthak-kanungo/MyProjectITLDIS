import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogSheetPageComponent } from './log-sheet-page.component';

describe('LogSheetPageComponent', () => {
  let component: LogSheetPageComponent;
  let fixture: ComponentFixture<LogSheetPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogSheetPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogSheetPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
