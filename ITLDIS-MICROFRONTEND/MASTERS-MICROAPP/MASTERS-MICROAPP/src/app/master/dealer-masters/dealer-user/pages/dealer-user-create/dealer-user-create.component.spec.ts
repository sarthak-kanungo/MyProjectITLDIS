import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerUserCreateComponent } from './dealer-user-create.component';

describe('DealerUserCreateComponent', () => {
  let component: DealerUserCreateComponent;
  let fixture: ComponentFixture<DealerUserCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerUserCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerUserCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
