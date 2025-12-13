import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodwillUploadComponent } from './goodwill-upload.component';

describe('GoodwillUploadComponent', () => {
  let component: GoodwillUploadComponent;
  let fixture: ComponentFixture<GoodwillUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoodwillUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodwillUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
