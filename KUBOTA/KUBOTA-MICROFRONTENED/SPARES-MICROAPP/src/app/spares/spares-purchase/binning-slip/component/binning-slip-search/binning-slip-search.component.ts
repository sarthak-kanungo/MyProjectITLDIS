import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-binning-slip-search',
  templateUrl: './binning-slip-search.component.html',
  styleUrls: ['./binning-slip-search.component.scss']
})
export class BinningSlipSearchComponent implements OnInit {
  grnTypes: string[] = [
    'KAI', 'Co-Dealer', 'Branch Transfer', 'Others'
  ]
  constructor() { }

  ngOnInit() {
  }

}
