import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, Validators, AbstractControl } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { RetailFinanceService } from './retail-finance.service';
import { DropDownCashLoan, DropDownFinalStatus, RetailFinanceObj } from 'RetailFinance';
import { RetailFinanceContainerService } from '../retail-finance-container/retail-finance-container.service';
import { EnquiryService } from '../../enquiry.service';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { BaseDto } from 'BaseDto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-retail-finance',
  templateUrl: './retail-finance.component.html',
  styleUrls: ['./retail-finance.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    RetailFinanceService, RetailFinanceContainerService, EnquiryCreationContainerService]
})
export class RetailFinanceComponent implements OnInit {
  isView: boolean
  isEdit: boolean
  @Input()
  max: Date | null
  tomorrow = new Date();
  isViewMobile: boolean
  retailFinanceForm: FormGroup;
  subsidies = [
    { subsidy: 'YES' },
    { subsidy: 'NO' }
  ];
  isSubSidySelect = false
  isCashSelected = true
  isFinanceStatus = false
  isLoanSelected = false
  isExchangerequired = false
  isPaymentDisbursed = false
  // isLoanSelected = false
  totalAmount: number
  dropDownCashLoan: BaseDto<Array<DropDownCashLoan>>
  dropDownFinalStatus: BaseDto<Array<DropDownFinalStatus>>
  @Output() saveRetailFinanceForm = new EventEmitter<object>()

  constructor(
    public dialog: MatDialog,
    private retailFinanceService: RetailFinanceService,
    private enquiryService: EnquiryService,
    private retailFinanceContainerService: RetailFinanceContainerService,
    private enqRt: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.loadAllDropDownData().subscribe(dt => {
      this.dropDownCashLoan = dt[0] as BaseDto<Array<DropDownCashLoan>>
      this.dropDownFinalStatus = dt[1] as BaseDto<Array<DropDownFinalStatus>>
      this.patchOrCreate()
    })
  }

  private patchOrCreate() {
    if (this.isView) {
      this.parseIdAndViewForm()
      this.showFinalExchangePrice()
      this.autoPopulateFinalExchangePrice()
    } else if (this.isViewMobile) {
      this.parseIdAndViewMobileForm()
    } else {
      this.formForCreateSetup()
    }
  }

  private formForCreateSetup() {
    this.retailFinanceForm = this.retailFinanceService.createretailFinanceForm();
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        console.log(this.retailFinanceForm)
        let retail: RetailFinanceObj = this.retailFinanceForm.getRawValue()
        retail.financeLoggedInDate = this.convertDateToServerFormat(retail.financeLoggedInDate)
        retail.disbursedDate = this.convertDateToServerFormat(retail.disbursedDate)
        this.saveRetailFinanceForm.emit({ validStatus: this.retailFinanceForm.valid, formData: retail });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.retailFinanceForm.reset();
      }
    })
    this.totalMarginAmount()
    this.showFinalExchangePrice()
    this.autoPopulateFinalExchangePrice()
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      this.retailFinanceForm.patchValue(domain)
      this.retailFinanceForm.controls.marginAmount.patchValue(domain.marginAmount)
      this.retailFinanceForm.controls.financeLoggedInDate.patchValue(domain.financeLoggedInDate ? new Date(domain.financeLoggedInDate) : null)
      this.retailFinanceForm.controls.disbursedDate.patchValue(domain.disbursedDate ? new Date(domain.disbursedDate) : null)
      this.dropDownCashLoan.result.findIndex(res => res.cashLoanType === domain.cashLoan)
      this.retailFinanceForm.controls.cashLoan.patchValue(domain.cashLoan)
      this.dropDownFinalStatus.result.findIndex(res => res.finalStatus === domain.financeStatus)
      this.retailFinanceForm.controls.financeStatus.patchValue(domain.financeStatus)
      this.subsidies.findIndex(res => res.subsidy === domain.subsidy)
      this.retailFinanceForm.controls.subsidy.patchValue(domain.subsidy)
    }
    if (domain.cashLoan === 'Loan') {
      this.isCashSelected = false
    }
    if (domain.financeStatus === 'Payment Disbursed') {
      this.isFinanceStatus = true
    }
    if (domain.subsidy === 'YES') {
      this.isSubSidySelect = true
    }
    if (domain.exchangeRequired === 'YES') {
      this.isExchangerequired = true
    }
  }
  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      Object.keys(domain).map(key => domain[key] = domain[key] == 'NA' ? null : domain[key])
      this.retailFinanceForm.patchValue(domain)
      this.retailFinanceForm.controls.financeLoggedInDate.patchValue(domain.financeLoggedInDate)
      this.retailFinanceForm.controls.disbursedDate.patchValue(domain.disbursedDate)
      this.dropDownCashLoan.result.findIndex(res => res.cashLoanType === domain.cashLoan)
      this.retailFinanceForm.controls.cashLoan.patchValue(domain.cashLoan)
      this.dropDownFinalStatus.result.findIndex(res => res.finalStatus === domain.financeStatus)
      this.retailFinanceForm.controls.financeStatus.patchValue(domain.financeStatus)
      this.subsidies.findIndex(res => res.subsidy === domain.subsidy)
      this.retailFinanceForm.controls.subsidy.patchValue(domain.subsidy)
    }
  }

  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enqNo']))
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        let retailData: RetailFinanceObj = this.retailFinanceForm.getRawValue()
        retailData.financeLoggedInDate = this.convertDateToServerFormat(retailData.financeLoggedInDate)
        retailData.disbursedDate = this.convertDateToServerFormat(retailData.disbursedDate)
        this.saveRetailFinanceForm.emit({ validStatus: this.retailFinanceForm.valid, formData: retailData });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.retailFinanceForm.reset();
      }
    })
    this.totalMarginAmount()
    this.showFinalExchangePrice()
    this.autoPopulateFinalExchangePrice()
  }

  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewMobileEnqNo(prms['mobenqNo']))
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let retailData: RetailFinanceObj = this.retailFinanceForm.value
        retailData.financeLoggedInDate = this.convertDateToServerFormat(retailData.financeLoggedInDate)
        retailData.disbursedDate = this.convertDateToServerFormat(retailData.disbursedDate)
        this.saveRetailFinanceForm.emit({ validStatus: this.retailFinanceForm.valid, formData: retailData });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.retailFinanceForm.reset();
      }
    })
    this.totalMarginAmount()
    this.showFinalExchangePrice()
    this.autoPopulateFinalExchangePrice()
  }

  private fatchDataForEnqNo(enqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enqNo).subscribe(dto => { this.formForViewSetup(dto.result) })
  }

  private fatchDataForViewMobileEnqNo(mobenqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + mobenqNo).subscribe(dto => { this.formForViewMobileSetup(dto.result) })
  }

  private showFinalExchangePrice() {
    this.enquiryService.selectExchangeRequired.subscribe(value => {
      this.isExchangerequired = value
      this.calculateMarginAmount()
    })

    this.enquiryService.estimatedExcnangPrice.subscribe(value => {
      this.calculateMarginAmount()
      console.log(value)
    })
  }

  private autoPopulateFinalExchangePrice() {
    this.enquiryService.estimatedExcnangPrice.subscribe(finalExchangePrice => {
      console.log('finalExchangePrice', finalExchangePrice);
      this.retailFinanceForm.controls.finalExchangePrice.patchValue(finalExchangePrice)
      this.calculateMarginAmount()
    })
  }

  private checkOperationType() {
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isViewMobile = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'viewMobile'
  }

  private intiOperationForm() {
    if (this.isView) {
      this.retailFinanceForm = this.retailFinanceService.ViewretailFinanceForm()
    } else if (this.isViewMobile) {
      this.retailFinanceForm = this.retailFinanceService.ViewMobileretailFinanceForm()
    } else {
      this.retailFinanceForm = this.retailFinanceService.createretailFinanceForm()
    }
  }

  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.retailFinanceContainerService.dropdowncshLoan())
    dropDownTask.push(this.retailFinanceContainerService.dropdownfinalStatus())

    return forkJoin(...dropDownTask)
  }

  private markFormAsTouched() {
    for (const key in this.retailFinanceForm.controls) {
      if (this.retailFinanceForm.controls.hasOwnProperty(key)) {
        this.retailFinanceForm.controls[key].markAsTouched();
      }
    }
  }

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear()
      console.log(formattedDate)
      return formattedDate
    }
    return null
  }

  selectionCashLoan(value) {
    console.log('value', value)
    if (value == 'Cash') {
      this.retailFinanceForm.controls.marginAmount.patchValue(this.retailFinanceForm.controls.assetValue.value)
      // this.retailFinanceForm.controls.marginAmount.patchValue(0)
      this.isFinanceStatus = false
      this.isCashSelected = true
    }

    if (value == 'Loan') {
      this.isCashSelected = false
      this.isFinanceStatus = true
      this.retailFinanceForm.controls.financier.reset()
      this.retailFinanceForm.controls.financeLoggedInDate.reset()
      this.retailFinanceForm.controls.estimatedFinanceAmount.reset()
      this.retailFinanceForm.controls.financeStatus.reset()
    }
    this.calculateMarginAmount()
  }

  selectionfinanceStatus(value) {
    if (value === 'Payment Disbursed') {
      this.isPaymentDisbursed = true
      this.retailFinanceForm.controls.disbursedDate.reset()
      this.retailFinanceForm.controls.disbursedFinanceAmount.reset()
    } else {
      this.isPaymentDisbursed = false
    }
    this.calculateMarginAmount()
  }

  selectionSubsidy(value) {
    if (value === 'YES') {
      this.isSubSidySelect = true
      this.retailFinanceForm.controls.subsidyAmount.setValidators(Validators.compose([Validators.required]))
      this.retailFinanceForm.controls.subsidyAmount.updateValueAndValidity();
      this.retailFinanceForm.controls.subsidyAmount.reset()
    } else {
      this.isSubSidySelect = false
      this.retailFinanceForm.controls.subsidyAmount.clearValidators()
      this.retailFinanceForm.controls.subsidyAmount.updateValueAndValidity();
      this.retailFinanceForm.controls.subsidyAmount.reset()
    }
    this.calculateMarginAmount()
  }

  private totalMarginAmount() {
    this.retailFinanceForm.controls.assetValue.valueChanges.subscribe(asstAmt => {
      this.calculateMarginAmount()
    })
    this.retailFinanceForm.controls.subsidyAmount.valueChanges.subscribe(subsidyAmt => {
      this.calculateMarginAmount()
      if (this.retailFinanceForm.controls.assetValue.value < subsidyAmt) {
        this.retailFinanceForm.controls.subsidyAmount.setValidators(Validators.compose([Validators.required, this.exceedsAssetValue.bind(this)]))
      }
    })
    this.retailFinanceForm.controls.estimatedFinanceAmount.valueChanges.subscribe(estimatedAmt => {
      this.calculateMarginAmount()
      if (this.retailFinanceForm.controls.assetValue.value < estimatedAmt) {
        this.retailFinanceForm.controls.estimatedFinanceAmount.setValidators(Validators.compose([Validators.required, this.exceedsAssetValue.bind(this)]))
      }
    })
    this.retailFinanceForm.controls.finalExchangePrice.valueChanges.subscribe(finalExchange => {
      this.calculateMarginAmount()
    })
  }

  private exceedsAssetValue(control: AbstractControl) {
    if (control && control.value) {
      let assetValue = this.retailFinanceForm.value.assetValue;
      if (parseFloat(control.value) > parseFloat(assetValue)) {
        return { 'moreThan': true }
      }
      return 0.0
    }
  }

  private calculateMarginAmount() {

    console.log(`Cash ${this.isCashSelected} && Loan ${this.isFinanceStatus} && Subsidy ${this.isSubSidySelect} && Exchange ${this.isExchangerequired}`)
    if (this.isCashSelected && !this.isFinanceStatus && !this.isSubSidySelect && !this.isExchangerequired) {
      this.retailFinanceForm.controls.marginAmount.patchValue(this.retailFinanceForm.controls.assetValue.value)
    }
    if (this.isCashSelected && !this.isFinanceStatus && this.isSubSidySelect && !this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let subsidy = Number.parseFloat(this.retailFinanceForm.controls.subsidyAmount.value)
      let marginAmount = av - subsidy
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }

    }
    if (this.isCashSelected && !this.isFinanceStatus && !this.isSubSidySelect && this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let exchange = Number.parseFloat(this.retailFinanceForm.controls.finalExchangePrice.value)
      console.log(`Av ${av} Exchange ${exchange}`)
      let marginAmount = (Number.isNaN(av) ? 0 : av) - exchange
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
    if (this.isCashSelected && !this.isFinanceStatus && this.isSubSidySelect && this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let exchange = Number.parseFloat(this.retailFinanceForm.controls.finalExchangePrice.value)
      let subsidy = Number.parseFloat(this.retailFinanceForm.controls.subsidyAmount.value)
      let marginAmount = av - (exchange + subsidy)
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
    if (!this.isCashSelected && this.isFinanceStatus && !this.isSubSidySelect && !this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let efa = Number.parseFloat(this.retailFinanceForm.controls.estimatedFinanceAmount.value)
      let marginAmount = av - efa
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
    if (!this.isCashSelected && this.isFinanceStatus && this.isSubSidySelect && !this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let efa = Number.parseFloat(this.retailFinanceForm.controls.estimatedFinanceAmount.value)
      let subsidy = Number.parseFloat(this.retailFinanceForm.controls.subsidyAmount.value)
      let marginAmount = av - (efa + subsidy)
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
    if (!this.isCashSelected && this.isFinanceStatus && !this.isSubSidySelect && this.isExchangerequired) {

      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let efa = Number.parseFloat(this.retailFinanceForm.controls.estimatedFinanceAmount.value)
      let exchange = Number.parseFloat(this.retailFinanceForm.controls.finalExchangePrice.value)
      let marginAmount = av - (efa + exchange)
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
    if (!this.isCashSelected && this.isFinanceStatus && this.isSubSidySelect && this.isExchangerequired) {
      let av = Number.parseFloat(this.retailFinanceForm.controls.assetValue.value)
      let efa = Number.parseFloat(this.retailFinanceForm.controls.estimatedFinanceAmount.value)
      let exchange = Number.parseFloat(this.retailFinanceForm.controls.finalExchangePrice.value)
      let subsidy = Number.parseFloat(this.retailFinanceForm.controls.subsidyAmount.value)
      let marginAmount = av - (efa + exchange + subsidy)
      if (marginAmount >= 0) {
        this.retailFinanceForm.controls.marginAmount.patchValue(marginAmount)
      }
    }
  }
}