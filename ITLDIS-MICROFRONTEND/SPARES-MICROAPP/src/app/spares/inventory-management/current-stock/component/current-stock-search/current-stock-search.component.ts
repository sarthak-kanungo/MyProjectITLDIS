import { Component, Input, OnInit } from '@angular/core';
import { CurrentStockPagePresenter } from '../current-stock-search-page/current-stock-page-presenter';
import { CurrentStockService } from '../search-current-stock/current-stock.service';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { CurrentStockPageStore } from '../current-stock-search-page/current-stock-page-store';
import { PurchaseOrderSearchWebService } from '../../../../spares-purchase/spares-purchase-order/component/purchase-order-search/purchase-order-search-web.service';
import { BaseDto } from 'BaseDto';
@Component({
  selector: 'app-current-stock-search',
  templateUrl: './current-stock-search.component.html',
  styleUrls: ['./current-stock-search.component.scss'],
  providers: [CurrentStockPagePresenter, CurrentStockService, CurrentStockPageStore, PurchaseOrderSearchWebService]

})
export class CurrentStockSearchComponent implements OnInit {

  searchItemNo//: BaseDto<Array<any>>
  itemDataDto: BaseDto<any>

  @Input() currentStockSearchResultForm: FormGroup;

  // currentStockSearchForm: FormGroup;
  constructor(
    private presenter: CurrentStockPagePresenter,
    private activateRoute: ActivatedRoute,
    private webService: CurrentStockService,
    private purchaseOrderSearchWebService: PurchaseOrderSearchWebService
  ) { }
  ngOnInit() {
    // this.currentStockSearchForm = this.presenter.currentStockSearch
    // console.log(" this.currentStockSearchForm ", this.currentStockSearchForm);

    this.formValueChanges();
  }
  formValueChanges() {
    this.currentStockSearchResultForm.get('itemNo').valueChanges.subscribe(value => {
      if (value) {
        let itemNo = typeof value == "object" ? value.item_no : value;
        this.autoItemNumber(itemNo);
      }
      // if (value !== null && typeof value !== "string") {
      //   this.currentStockSearchForm.get("itemNo").setErrors({
      //     selectFromList: "Please select from list",
      //   });
      // }
    });
  }
  autoItemNumber(value) {
    if (value.item_no != undefined && value.item_no != null) {
      value = value.item_no;
    }
    this.purchaseOrderSearchWebService.searchByPartNumber(value).subscribe(response => {
      console.log('response', response);
      this.searchItemNo = response //as BaseDto<Array<any>>
    })
  }

  displayItemNoFn(selectedOption: any) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['item_no'] : undefined;
  }
}
