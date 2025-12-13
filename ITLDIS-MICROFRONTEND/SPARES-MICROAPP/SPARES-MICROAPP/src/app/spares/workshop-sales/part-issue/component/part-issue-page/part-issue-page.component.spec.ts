import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartIssuePageComponent } from './part-issue-page.component';

describe('PartIssuePageComponent', () => {
  let component: PartIssuePageComponent;
  let fixture: ComponentFixture<PartIssuePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartIssuePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartIssuePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
