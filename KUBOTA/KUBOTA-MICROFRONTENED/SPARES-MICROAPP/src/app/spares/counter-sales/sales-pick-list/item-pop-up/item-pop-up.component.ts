import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatAutocompleteSelectedEvent } from '@angular/material';
import { FormGroup, FormArray } from '@angular/forms';
import { Observable } from 'rxjs';
import { ItemNumberAuto } from '../domain/pl.domain';
import { ItemPopUpWebService } from './item-pop-up-web.service';
import { ToastrService } from 'ngx-toastr';
import { PartDetails } from './item-pop-up.domain';

@Component({
  selector: 'app-item-pop-up',
  templateUrl: './item-pop-up.component.html',
  styleUrls: ['./item-pop-up.component.scss'],
  providers: [ItemPopUpWebService]
})
export class ItemPopUpComponent implements OnInit {
  addNewPartForm: FormGroup
  itemDetailsPopUpTable: FormGroup
  listOfData: Array<any> = []
  deletedEntries = [];
  state: string
  itemNumberAvailableStockList: PartDetails[];
  totalIssueQtyExceed: { type: string; msg: string; };
  constructor(
    public dialogRef: MatDialogRef<ItemPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private itemPopUpWebService: ItemPopUpWebService,
    private toastr: ToastrService,
  ) { }
  ngOnInit() {
    this.addNewPartForm = this.data.addIssueQuantityForm;
    this.getAvailableStock();
  }
  private getAvailableStock() {
    if (this.data.advancedSparePartIssue) {
      this.itemPopUpWebService.getAvailableStockForPartIssue(this.data.itemNumber.itemNo || this.data.itemNumber, this.data.advancedSparePartIssue.id).subscribe(res => {
        //this.insertIssueQuantityIntoResponse(res, this.data.itemDetalList);
        this.itemNumberAvailableStockList = res;
      });
      return;
    }
    this.itemPopUpWebService.getAvailableStockByItemNumber(this.data.itemNumber.itemNo || this.data.itemNumber).subscribe(res => {
      //this.insertIssueQuantityIntoResponse(res, this.data.itemDetalList);
      this.itemNumberAvailableStockList = res.map(stockPart=>{
        if (stockPart['pickListNo'] === undefined) {
          stockPart['pickListNo'] = null;
        }
        return stockPart
      });
    });
  }
/*  private insertIssueQuantityIntoResponse(stockList: PartDetails[], issuedList: any[]) {
    issuedList.forEach((issuedPart, index) => {
      const matchedPart = stockList.find((stockPart, index) => {
        if (
          issuedPart.binId === stockPart.binId &&
          issuedPart.storeId === stockPart.storeId &&
          ((issuedPart.unitPrice || issuedPart['mrp']) === stockPart.unitPrice)
        ) {
          return true;
        }
      });
      if (matchedPart) {
        matchedPart['issueQuantity'] = issuedPart.issueQuantity;
        matchedPart['pickListNo'] = issuedPart.pickListNo;
        return;
      }
    });
  }*/
  displayFnItemNumber(itemNo: ItemNumberAuto) {
    return itemNo ? itemNo.itemNo : undefined
  }
  closeDialog(): void {
    this.dialogRef.close();
  }
  save() {
    // this.toastr.success('Item Details added !', 'Success');
    const issuedPartList = this.itemNumberAvailableStockList.filter(itemDetail => {
      if (itemDetail.issueQuantity && itemDetail.issueQuantity > 0) {
        return true;
      }
      return false;
    });
    if (this.validate(issuedPartList)) {
      this.dialogRef.close({
        list: issuedPartList,
        uuid: this.data.uuid
      });
    }
  }
  private validate(issuedPartList: PartDetails[]): boolean {
    if (issuedPartList.length <= 0) {
      this.toastr.error('Enter issue quantity for at least one record', 'Error');
      return false;
    }
    const invalid = issuedPartList.some((itemDetail => {
      if (itemDetail['error']) {
        return true;
      }
    }));
    if (invalid) {
      this.toastr.error('Invalid issue quantity', 'Error');
      return false;
    }
    if (this.totalIssueQtyExceed) {
      this.toastr.error(this.totalIssueQtyExceed.msg, 'Error');
      return false;
    }
    return true;
  }
  clearIssueQuantity() {
    this.itemNumberAvailableStockList.forEach(detail => {
      detail['issueQuantity'] = null;
      detail['error'] = null;
    })
  }
  issueQuantityValueChanged(issueQtyDetail: PartDetails) {
    this.validateIssueQty(issueQtyDetail);
    const balanceQuantity = parseInt(`${this.data.balanceQuantity}`);
    const error = this.validateTotalIssueQty(this.itemNumberAvailableStockList, balanceQuantity);
    if (error) {
      this.totalIssueQtyExceed = error;
      this.toastr.error(error.msg, 'Error');
      return;
    }
    this.totalIssueQtyExceed = null;
  }
  private validateIssueQty(issueQtyDetail: PartDetails) {
    const onlyNumberRegEx = /^[0-9]*$/g;
    if (!onlyNumberRegEx.test(`${issueQtyDetail.issueQuantity}`)) {
      issueQtyDetail['error'] = { type: 'numberPattern', msg: "Please enter only numbers" }
      return;
    }
    const issueQty = parseInt(`${issueQtyDetail.issueQuantity}`) || null;
    issueQtyDetail.issueQuantity = issueQty;
    const availableQuantity = parseInt(`${issueQtyDetail.availableQuantity}`) || 0;
    if (issueQty > availableQuantity) {
      issueQtyDetail['error'] = { type: 'valueExceed', msg: "less than available quantity" }
      return;
    }
    issueQtyDetail['error'] = null;
  }
  private validateTotalIssueQty(issueQtyDetail: PartDetails[], balanceQuantity: number) {
    const totalIssueQty = issueQtyDetail.reduce((previousValue, currentValue, currentIndex, array) => {
      previousValue += parseInt(`${currentValue.issueQuantity}`) || 0;
      return previousValue;
    }, 0)
    if (totalIssueQty > balanceQuantity) {
      return { type: 'valueExceed', msg: "Issue quantity exceeded balance quantity" };
    }
    return null;
  }
}
