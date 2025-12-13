import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RetailFinanceContainerService } from './retail-finance-container.service';
import { DropDownCashLoan, DropDownFinalStatus } from 'RetailFinance';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-retail-finance-container',
  templateUrl: './retail-finance-container.component.html',
  styleUrls: ['./retail-finance-container.component.scss'],
  providers: [RetailFinanceContainerService]
})
export class RetailFinanceContainerComponent implements OnInit {
  dropDownCashLoan: BaseDto<Array<DropDownCashLoan>>
  dropDownFinalStatus: BaseDto<Array<DropDownFinalStatus>>
  @Output() saveRetailfinanceForm = new EventEmitter<object>()

  constructor(
    private retailFinanceContainerService: RetailFinanceContainerService
  ) { }

  ngOnInit() {
    this.dropdownCashLoan()
    this.dropdownFinalStatus()
  }

  dropdownCashLoan() {
    this.retailFinanceContainerService.dropdowncshLoan().subscribe(response => {
      // console.log('cashs =>', response);
      this.dropDownCashLoan = response as BaseDto<Array<DropDownCashLoan>>
    })
  }

  dropdownFinalStatus() {
    this.retailFinanceContainerService.dropdownfinalStatus().subscribe(response => {
      // console.log('financeStatuses => ', response);
      this.dropDownFinalStatus = response as BaseDto<Array<DropDownFinalStatus>>

    })
  }

  getFormdataWithFormName(event) {
   // console.log('event', event);
    this.saveRetailfinanceForm.emit({ form: 'Retail Finance', event: event })
  }

}
