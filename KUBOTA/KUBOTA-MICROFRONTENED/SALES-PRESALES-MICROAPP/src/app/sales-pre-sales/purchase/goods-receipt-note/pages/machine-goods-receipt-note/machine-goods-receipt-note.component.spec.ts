import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineGoodsReceiptNoteComponent } from './machine-goods-receipt-note.component';

describe('MachineGoodsReceiptNoteComponent', () => {
  let component: MachineGoodsReceiptNoteComponent;
  let fixture: ComponentFixture<MachineGoodsReceiptNoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineGoodsReceiptNoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineGoodsReceiptNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
