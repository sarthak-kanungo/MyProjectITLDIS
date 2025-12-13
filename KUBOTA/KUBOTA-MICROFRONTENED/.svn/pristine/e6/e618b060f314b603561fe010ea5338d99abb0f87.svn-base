import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { BaseDto } from 'BaseDto';
import { EnquiryCodeDomain } from 'quotation-dto';
import { ToastrService } from 'ngx-toastr';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { ExchangeInventorySaleService } from './exchange-inventory-sale.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-exchange-inventory-sale',
  templateUrl: './exchange-inventory-sale.component.html',
  styleUrls: ['./exchange-inventory-sale.component.scss'],
  providers: [ExchangeInventorySaleService,EnquiryCommonService,DatePipe]
})
export class ExchangeInventorySaleComponent implements OnInit {

  private searchFormValues: object = {} as object;
  private searchFormValue: object = {} as object;

  public exchangeInventoryForm: FormGroup;
  id:any;

  constructor(
    public datepipe: DatePipe,
    private router: Router,
    private fb: FormBuilder,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private enquiryCommonService : EnquiryCommonService,
    private exchangeInventorySaleService:ExchangeInventorySaleService
  ) { 
   
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        const paramsObj = { ...queryMap['params'] } as object;
        Object.keys(paramsObj).forEach(key => paramsObj[key] === "null" && delete paramsObj[key]);
        this.searchFormValues = paramsObj;
        this.id=atob(JSON.parse(this.searchFormValues['filterData']).userId);
      }
    })
  }

  ngOnInit() {
    this.createSaleViewForm();
    this.exchangeInventorySaleService.getEnquirySearchById(this.id).subscribe((data:any) => {
      console.log("search data ---->", data)
      this.exchangeInventoryForm.patchValue(data.result);
      this.exchangeInventoryForm.controls.enquiryNumber.patchValue(data.result.enquiry.enquiryNumber)
      this.exchangeInventoryForm.controls.invInDate.patchValue(
         this.datepipe.transform(data.result.invInDate, 'yyyy/MM/dd'));
         if(data.result.saledate!=null && data.result.saledate!=undefined)
         {
          this.exchangeInventoryForm.controls.saledate.patchValue(
            this.datepipe.transform(data.result.saledate, 'yyyy/MM/dd'));
         }
    });
  }

  private createSaleViewForm() {
    this.exchangeInventoryForm = this.fb.group({
      enquiryNumber: [{ value: null, disabled: true }, Validators.compose([])],
      brandName: [{ value: null, disabled: true }, Validators.compose([])],
      modelName: [{ value: null, disabled: true }, Validators.compose([])],
      modelYear: [{ value: null, disabled: true }, Validators.compose([])],
      invInDate: [{ value: null, disabled: true }, Validators.compose([])],
      estimatedExchangePrice: [{ value: null, disabled: true }, Validators.compose([])],
      buyerName: [{ value: null, disabled: true }, Validators.compose([])],
      buyerContactNo: [{ value: null, disabled: true }, Validators.compose([])],
      status: [{ value: null, disabled: true }, Validators.compose([])],
      saledate: [{ value: null, disabled: true }, Validators.compose([])],
      sellingprice: [{ value: null, disabled: true }, Validators.compose([])],
      saleremarks: [{ value: null, disabled: true }, Validators.compose([])],
    });
  }

  exit() {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
  }
 }

