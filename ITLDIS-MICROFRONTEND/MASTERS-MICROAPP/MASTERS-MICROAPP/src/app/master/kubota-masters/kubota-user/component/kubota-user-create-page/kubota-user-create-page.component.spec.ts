import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { itldisUserCreatePageComponent } from './itldis-user-create-page.component';

describe('itldisUserCreatePageComponent', () => {
  let component: itldisUserCreatePageComponent;
  let fixture: ComponentFixture<itldisUserCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ itldisUserCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(itldisUserCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
