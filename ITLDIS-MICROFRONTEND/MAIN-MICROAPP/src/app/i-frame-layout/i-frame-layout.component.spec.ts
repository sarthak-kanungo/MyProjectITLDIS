import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IFrameLayoutComponent } from './i-frame-layout.component';

describe('IFrameLayoutComponent', () => {
  let component: IFrameLayoutComponent;
  let fixture: ComponentFixture<IFrameLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IFrameLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IFrameLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
