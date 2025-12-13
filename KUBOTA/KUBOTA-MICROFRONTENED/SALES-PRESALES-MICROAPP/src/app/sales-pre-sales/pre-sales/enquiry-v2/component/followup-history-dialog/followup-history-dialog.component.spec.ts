import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FollowupHistoryDialogComponent } from './followup-history-dialog.component';

describe('FollowupHistoryDialogComponent', () => {
  let component: FollowupHistoryDialogComponent;
  let fixture: ComponentFixture<FollowupHistoryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FollowupHistoryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FollowupHistoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
