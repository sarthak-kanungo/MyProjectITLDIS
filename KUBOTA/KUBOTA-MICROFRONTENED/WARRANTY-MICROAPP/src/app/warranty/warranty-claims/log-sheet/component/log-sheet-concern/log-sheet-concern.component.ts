import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-log-sheet-concern',
  templateUrl: './log-sheet-concern.component.html',
  styleUrls: ['./log-sheet-concern.component.scss']
})
export class LogSheetConcernComponent implements OnInit {
  @Input() public concernForm: FormGroup;
  constructor() { }

  ngOnInit() {
  }

}
