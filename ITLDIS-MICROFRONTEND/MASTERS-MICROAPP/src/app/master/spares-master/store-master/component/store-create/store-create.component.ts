import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-store-create',
  templateUrl: './store-create.component.html',
  styleUrls: ['./store-create.component.scss']
})
export class StoreCreateComponent implements OnInit {

  labelPosition = 'before';
  @Input() storeDetailsForm : FormGroup
  branches : string[] = ['C01010-01', 'C01010-02']

  constructor() { }

  ngOnInit() {
  }

}
