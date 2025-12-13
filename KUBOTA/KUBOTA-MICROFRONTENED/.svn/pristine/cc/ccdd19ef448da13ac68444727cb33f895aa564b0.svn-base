import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { LogSheetPagePresenter } from '../log-sheet-page/log-sheet-page.presenter';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { implementService } from './implement-service';

@Component({
  selector: 'app-log-sheet-implement',
  templateUrl: './log-sheet-implement.component.html',
  styleUrls: ['./log-sheet-implement.component.scss'],
  providers:[implementService]
})
export class LogSheetImplementComponent implements OnInit {
  implementHeading = ['Sl.No', 'Implement Category', 'Implement', 'Implement Make', 'Implement Sl.No','Gear Ratio', 'Engine RPM', '% of Usage', 'Usage During Failure'];
  formLength: number;
  @Input() implementForm: FormArray;
  isView = false;
  catgList=[];

  constructor(
    private logSheetPagePresenter: LogSheetPagePresenter,
    private activatedRoute: ActivatedRoute,
    private services:implementService
  ) { }

  ngOnInit() {
    this.formLength = this.logSheetPagePresenter.formLength;
    this.checkFormOperation();
    this.services.dropDownImplCatg().subscribe(response => {
      this.catgList = response;
  })
  }

  private checkFormOperation() {
    this.logSheetPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.logSheetPagePresenter.operation == Operation.CREATE) {
      this.addRow();
    } else if (this.logSheetPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
    } 
  }

  addRow() {
   
    this.logSheetPagePresenter.addRowInImplement();
    this.formLength = this.logSheetPagePresenter.formLength;
  }
  deleteRow() {
    this.logSheetPagePresenter.deleteRowInImplement();
    this.formLength = this.logSheetPagePresenter.formLength;
  }
}