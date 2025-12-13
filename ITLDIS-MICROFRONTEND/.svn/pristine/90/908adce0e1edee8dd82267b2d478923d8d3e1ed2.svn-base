import { Inject, Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { StorageLoginUser } from 'LoginDto';
import { ToastrService } from 'ngx-toastr';
import { AutoComplateMobileNo, ProspectDetailsObj } from 'ProspectDetails';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { SalePopUpDetails } from './sale-pop-up.domain';
import { SalePopUpService } from './sale-pop-up.service';

@Component({
  selector: 'app-sale-pop-up',
  templateUrl: './sale-pop-up.component.html',
  styleUrls: ['./sale-pop-up.component.scss'],
  providers: [SalePopUpService]
})
export class SalePopUpComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<SalePopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private toastr: ToastrService,
    private localStorageService: LocalStorageService,
    private salePopUpService: SalePopUpService,
    private login: LoginFormService,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,

  ) {
    this.oldVehId = data;
  }
  saleExchangeFrom: FormGroup
  // mobileAutoFillForm: FormGroup
  oldVehId: any;
  loginUserId: number;
  loginUser: StorageLoginUser;
  // prospectDetailsForm: FormGroup;
  autoComplateMobileNo: BaseDto<Array<AutoComplateMobileNo>>
  autoPopulateDatabyMobileNoList: BaseDto<ProspectDetailsObj>

  ngOnInit() {
    this.loginUser = this.localStorageService.getLoginUser();
    this.loginUserId = this.login.getLoginUserId()
    this.saleExchangeFrom = this.salePopUpService.createSaleExchangeFrom();
    this.mobileNumberChanges();
  }
  public displayMobileNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['customerMobileNumber'] : undefined;
  }

  mobileNumberChanges() {
    this.saleExchangeFrom.controls.buyerContactNo.valueChanges.subscribe(value => {
      if(value.length>4){
        this.autoMobileNo(value)
      }else{
        this.autoComplateMobileNo = null;
      }
    })
  }
  closeDialog(): void {
    this.dialogRef.close();
  }
  // private disableField() {
  //   this.mobileAutoFillForm.controls.firstName.disable()

  // }
  autoMobileNo(mobilenumber) {
    this.salePopUpService.mobileNoData(mobilenumber).subscribe(response => {
      this.autoComplateMobileNo = response as BaseDto<Array<AutoComplateMobileNo>>;
      // if (this.saleExchangeFrom.controls.buyerContactNo.value != null &&
      //   this.saleExchangeFrom.controls.buyerContactNo.value != undefined) {
      //   if (this.saleExchangeFrom.controls.buyerContactNo.value.length > 0 &&
      //     this.saleExchangeFrom.controls.buyerContactNo.value.length != null) {
      //     if (this.saleExchangeFrom.controls.buyerContactNo.value.length == 10) {
      //       this.autoPopulateDatabyMobileNo(this.autoComplateMobileNo.result[0].mobileNumber);
      //     }
      //   }
      // }
    });
  }
  autoPopulateDatabyMobileNo(event: any) {
    let mobileNo = event.option.value;
    this.salePopUpService.searchbyMobileNo(mobileNo).subscribe(response => {
      console.log('autoPopulateData', response);
      if (response != null) {
        this.autoPopulateDatabyMobileNoList = response as BaseDto<ProspectDetailsObj>
        if (this.autoPopulateDatabyMobileNoList.result.firstName != null
          && this.autoPopulateDatabyMobileNoList.result.middleName != null
          && this.autoPopulateDatabyMobileNoList.result.lastName != null) {
          this.saleExchangeFrom.controls.buyerName.patchValue(this.autoPopulateDatabyMobileNoList.result.firstName
            + " " + this.autoPopulateDatabyMobileNoList.result.middleName +
            " " + this.autoPopulateDatabyMobileNoList.result.lastName)
          this.saleExchangeFrom.controls.buyerName.disable();
        }
        else if (this.autoPopulateDatabyMobileNoList.result.firstName != null
          && this.autoPopulateDatabyMobileNoList.result.middleName == null
          && this.autoPopulateDatabyMobileNoList.result.lastName != null) {
          this.saleExchangeFrom.controls.buyerName.patchValue(this.autoPopulateDatabyMobileNoList.result.firstName +
            " " + this.autoPopulateDatabyMobileNoList.result.lastName)
          this.saleExchangeFrom.controls.buyerName.disable();
        }
        else if (this.autoPopulateDatabyMobileNoList.result.firstName != null
          && this.autoPopulateDatabyMobileNoList.result.middleName == null
          && this.autoPopulateDatabyMobileNoList.result.lastName == null) {
          this.saleExchangeFrom.controls.buyerName.patchValue(this.autoPopulateDatabyMobileNoList.result.firstName
          )
          this.saleExchangeFrom.controls.buyerName.disable();
        }
        else if (this.autoPopulateDatabyMobileNoList.result.firstName != null
          && this.autoPopulateDatabyMobileNoList.result.middleName != null
          && this.autoPopulateDatabyMobileNoList.result.lastName == null) {
          this.saleExchangeFrom.controls.buyerName.patchValue(this.autoPopulateDatabyMobileNoList.result.firstName
            + " " + this.autoPopulateDatabyMobileNoList.result.middleName)
          this.saleExchangeFrom.controls.buyerName.disable();
        }
      }
    })
  }
  clearForm() {
    this.saleExchangeFrom.reset();
  }
  validateForm() {
    this.saleExchangeFrom.markAllAsTouched();
    if (this.saleExchangeFrom.valid) {
      this.submitData();
    }
  }
  private submitData() {
    let saleFormDetailsFinal = {} as SalePopUpDetails;
    saleFormDetailsFinal = this.saleExchangeFrom.getRawValue() as SalePopUpDetails;
    saleFormDetailsFinal.oldVehId = this.oldVehId;
    saleFormDetailsFinal.buyerName = this.saleExchangeFrom.controls.buyerName.value;
    console.log("SaleFormDetailsFinal ", saleFormDetailsFinal);
    this.salePopUpService.updateExchangeInventory(saleFormDetailsFinal).subscribe(res => {
      if (res) {
        this.saleExchangeFrom.reset();
        // this.router.navigate(['../'], { relativeTo: this.activatedRoute })
        this.dialog.closeAll();
        this.toastr.success('Exchange Inventory successfully !', 'Success')
      }
    })
  }
}
