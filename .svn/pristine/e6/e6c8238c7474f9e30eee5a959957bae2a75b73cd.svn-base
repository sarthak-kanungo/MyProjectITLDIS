import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { LogSheetPagePresenter } from '../log-sheet-page/log-sheet-page.presenter';

@Component({
  selector: 'app-log-sheet-dealer',
  templateUrl: './log-sheet-dealer.component.html',
  styleUrls: ['./log-sheet-dealer.component.scss']
})
export class LogSheetDealerComponent implements OnInit {
  @Input() public remarkForm: FormGroup;
  
  constructor(
    private logSheetPagePresenter: LogSheetPagePresenter
  ) { }

  ngOnInit() {
  }

  addFile() {
    this.logSheetPagePresenter.addRowInRemark();
  }

}
