import { Component, OnInit } from '@angular/core';
import { GoodsReceiptNoteCreateService } from '../goods-receipt-note-create/goods-receipt-note-create.service';

@Component({
  selector: 'app-goods-receipt-note-create-container',
  templateUrl: './goods-receipt-note-create-container.component.html',
  styleUrls: ['./goods-receipt-note-create-container.component.scss'],
  providers :[GoodsReceiptNoteCreateService]
})
export class GoodsReceiptNoteCreateContainerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
