import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-goodwill-implement',
  templateUrl: './goodwill-implement.component.html',
  styleUrls: ['./goodwill-implement.component.scss']
})
export class GoodwillImplementComponent implements OnInit {
  @Input() implementForm: FormArray;
  implementHeading = ['Sl.No', 'Implement Category', 'Implement', 'Implement Make', 'Implement Sl.No.','Gear Ratio', 'Engine RPM', '% oF Usage', 'Usage During Failure'];
  constructor() { }

  ngOnInit() {
  }

}
