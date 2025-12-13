import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCurrentStockComponent } from './create-current-stock.component';

describe('CreateCurrentStockComponent', () => {
  let component: CreateCurrentStockComponent;
  let fixture: ComponentFixture<CreateCurrentStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCurrentStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCurrentStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
