import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { opsPresenter } from '../order-planning-sheet-page/order-planning-sheet-presenter';
import { OPSService } from '../service/ops.service';

@Component({
  selector: 'app-part-detail-order-planning-sheet',
  templateUrl: './part-detail-order-planning-sheet.component.html',
  styleUrls: ['./part-detail-order-planning-sheet.component.scss'],
  providers: [OPSService]

})
export class PartDetailOrderPlanningSheetComponent implements OnInit {
  ItemNumber = new FormControl(null);
 @Input() partDetails:FormArray;
  totalOrderValue: any;
  @Input() editTotalOrderValue: number;
  @Input() searchCount: number
  public itemDetailsArray: Array<FormGroup> = [];
  pageIndex: number = 0;
  pageSize: number = 10;
  itemDetailsList: [];
  constructor(
    private presenter: opsPresenter,
    private service: OPSService,
  ) { }

  ngOnInit() {

    this.presenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
      this.itemDetailsArray = res;
    })
  }
  calculatePrice(event: any) {
    this.presenter.calCulateTotalOrderValue();
    this.totalOrderValue = (this.presenter.totalOrderValueforView);

  }
  validateNumber(event: any) {


    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }


  }



}
