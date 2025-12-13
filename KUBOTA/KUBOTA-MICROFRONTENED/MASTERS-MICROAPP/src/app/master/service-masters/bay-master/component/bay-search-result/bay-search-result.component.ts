import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bay-search-result',
  templateUrl: './bay-search-result.component.html',
  styleUrls: ['./bay-search-result.component.scss']
})
export class BaySearchResultComponent implements OnInit {
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  selected = 10;
  constructor() { }

  ngOnInit() {
  }

}
