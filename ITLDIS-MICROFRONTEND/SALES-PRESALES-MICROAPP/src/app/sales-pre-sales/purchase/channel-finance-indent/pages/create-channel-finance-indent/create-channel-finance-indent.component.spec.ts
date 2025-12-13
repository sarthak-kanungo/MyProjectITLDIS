import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateChannelFinanceIndentComponent } from './create-channel-finance-indent.component';

describe('CreateChannelFinanceIndentComponent', () => {
  let component: CreateChannelFinanceIndentComponent;
  let fixture: ComponentFixture<CreateChannelFinanceIndentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateChannelFinanceIndentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateChannelFinanceIndentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
