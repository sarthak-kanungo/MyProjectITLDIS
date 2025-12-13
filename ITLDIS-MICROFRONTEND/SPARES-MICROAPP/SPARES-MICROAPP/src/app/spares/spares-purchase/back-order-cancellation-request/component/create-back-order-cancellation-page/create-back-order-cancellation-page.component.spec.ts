import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBackOrderCancellationPageComponent } from './create-back-order-cancellation-page.component';

describe('CreateBackOrderCancellationPageComponent', () => {
  let component: CreateBackOrderCancellationPageComponent;
  let fixture: ComponentFixture<CreateBackOrderCancellationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBackOrderCancellationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBackOrderCancellationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
