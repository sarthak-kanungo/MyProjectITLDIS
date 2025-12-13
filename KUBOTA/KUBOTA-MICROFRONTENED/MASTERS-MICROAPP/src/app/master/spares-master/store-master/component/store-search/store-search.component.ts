import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-store-search',
  templateUrl: './store-search.component.html',
  styleUrls: ['./store-search.component.scss']
})
export class StoreSearchComponent implements OnInit {

  @Input() searchStoreForm : FormGroup
  branches : string[] = ['C01010-01', 'C01010-02']

  constructor() { }

  ngOnInit() {
  }

}
