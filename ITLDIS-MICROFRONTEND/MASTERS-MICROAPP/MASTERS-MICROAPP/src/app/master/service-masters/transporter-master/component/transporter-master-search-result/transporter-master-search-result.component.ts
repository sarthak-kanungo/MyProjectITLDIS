import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transporter-master-search-result',
  templateUrl: './transporter-master-search-result.component.html',
  styleUrls: ['./transporter-master-search-result.component.scss']
})
export class TransporterMasterSearchResultComponent implements OnInit {
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  selected = 10;
  constructor() { }

  ngOnInit() {
  }

}
