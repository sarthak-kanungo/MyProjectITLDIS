import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { CashLoan, FinalStatus, ViewEnquiry, Financier } from '../../domains/enquiry';
import { RetailFinanceDetailsV2WebService } from '../../services/retail-finance-details-v2-web.service';
import { DateFormat } from '../../../../../utils/date-format-util';
import { itldisPatterns } from '../../../../../utils/itldis-patterns';

@Component({
  selector: 'app-retail-finance-details-v2',
  templateUrl: './retail-finance-details-v2.component.html',
  styleUrls: ['./retail-finance-details-v2.component.scss'],
  providers: [RetailFinanceDetailsV2WebService]
})
export class RetailFinanceDetailsV2Component implements OnInit, OnChanges {

  retailFinanceForm: FormGroup
  @Input()
  max: Date | null
  tomorrow = new Date();
  subsidies = [
    { subsidy: 'YES' },
    { subsidy: 'NO' }
  ];
  financierId:number
  cashLoan: Array<CashLoan>
  finalStatus: Array<FinalStatus>
  financierList: Array<Financier>
  @Input() enquiryByEnquiryNumber: ViewEnquiry
  subsidyReceived:boolean = false;
  constructor(
    public enquiryPresenter: EnquiryPresenter,
    private retailFinanceDetailsV2WebService: RetailFinanceDetailsV2WebService,
    private dateFormat: DateFormat,
    private itldisPatterns: itldisPatterns,
  ) { }

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {
      this.retailFinanceForm.patchValue(this.enquiryByEnquiryNumber)
      let subsidiesObj = this.subsidies.findIndex(res => res.subsidy === this.enquiryByEnquiryNumber.subsidy)
      this.retailFinanceForm.get('subsidy').patchValue(this.subsidies[subsidiesObj])
      this.retailFinanceForm.get('cashLoan').patchValue(this.enquiryByEnquiryNumber.cashLoan ? { cashLoan: this.enquiryByEnquiryNumber.cashLoan } : null)
      this.retailFinanceForm.get('financeStatus').patchValue({ financeStatus: this.enquiryByEnquiryNumber.financeStatus })
      this.retailFinanceForm.get('financeLoggedInDate').patchValue(this.enquiryByEnquiryNumber.financeLoggedInDate ? new Date(this.enquiryByEnquiryNumber.financeLoggedInDate) : null)
      this.retailFinanceForm.get('disbursedDate').patchValue(this.dateFormat.convertToPatchFormat(this.enquiryByEnquiryNumber.disbursedDate ? this.enquiryByEnquiryNumber.disbursedDate : null))
      if (this.enquiryByEnquiryNumber.cashLoan === 'Loan') {
        this.enquiryPresenter.isCashSelected = false
      }
      if (this.enquiryByEnquiryNumber.financeStatus === 'Payment Disbursed') {
        this.enquiryPresenter.isFinanceStatus = true;
        this.retailFinanceForm.get('assetValue').disable();
        this.retailFinanceForm.get('cashLoan').disable();
        this.retailFinanceForm.get('financier').disable();
        this.retailFinanceForm.get('financeLoggedInDate').disable();
        this.retailFinanceForm.get('estimatedFinanceAmount').disable();
        this.retailFinanceForm.get('financeStatus').disable();
      }
      if (this.enquiryByEnquiryNumber.subsidy === 'YES') {
        this.enquiryPresenter.isSubSidySelect = true
      }
      if(this.enquiryByEnquiryNumber.financier){
          this.retailFinanceDetailsV2WebService.getFinancierList(this.enquiryByEnquiryNumber.financier).subscribe(response => {
              this.financierList = response.result;
              this.retailFinanceForm.get('financier').patchValue({financierName :this.financierList[0].financierName,financierCode :this.financierList[0].financierCode})
              this.retailFinanceForm.get('financierId').patchValue(this.financierList[0].id)
          })
      }
      this.subsidyReceived = this.enquiryByEnquiryNumber.subsidyReceived;
      if(this.subsidyReceived){
          this.retailFinanceForm.get('subsidy').disable();
          this.retailFinanceForm.get('subsidyAmount').disable();
      }
      this.retailFinanceForm.get('marginAmount').patchValue(this.enquiryByEnquiryNumber.marginAmount)
      console.log('checkpoint--',this.enquiryByEnquiryNumber.marginAmount);
      
    }
  }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.patchOrCreate()
    this.loadAllDropDown()
  }

  private patchOrCreate() {
    this.retailFinanceForm = this.enquiryPresenter.enquiryForm.get('retailFinanceDetails') as FormGroup
    this.totalMarginAmount()
    this.retailFinanceForm.controls.financier.valueChanges.subscribe(value => {
         this.retailFinanceDetailsV2WebService.getFinancierList(value).subscribe(response => {
             this.financierList = response.result;
         })
      }
    );
  }

  loadAllDropDown() {
    this.retailFinanceDetailsV2WebService.getCashLoan().subscribe(response => {
      this.cashLoan = response.result as Array<CashLoan>
    })
    this.retailFinanceDetailsV2WebService.getFinalStatus().subscribe(response => {
      this.finalStatus = response.result as Array<FinalStatus>
    })
  }
  optionSelectedFinancier(event){
      this.financierId = event.option.value.id;
      this.retailFinanceForm.controls.financierId.patchValue(this.financierId);
  }
  displayFnForFinancier(fin:Financier){
      return fin ? ((typeof fin === 'string')?undefined:fin.financierName) :undefined;
  }
  
  selectionCashLoan(value) {
    console.log(value);
    this.retailFinanceForm.get('subsidy').reset()
    this.retailFinanceForm.get('subsidyAmount').reset()
    if (value.cashLoan === 'Loan') {
      this.enquiryPresenter.isCashSelected = false
      this.retailFinanceForm.get('financier').setValidators(Validators.required)
      this.retailFinanceForm.get('financeLoggedInDate').setValidators(Validators.required)
      this.retailFinanceForm.get('estimatedFinanceAmount').setValidators(Validators.required)
      this.retailFinanceForm.get('financeStatus').setValidators(Validators.required)
    } else {
      this.enquiryPresenter.isCashSelected = true
      this.enquiryPresenter.resetFinacerForCashLoan()
      this.retailFinanceForm.get('financier').clearValidators()
      this.retailFinanceForm.get('financier').updateValueAndValidity();
      this.retailFinanceForm.get('financeLoggedInDate').clearValidators()
      this.retailFinanceForm.get('financeLoggedInDate').updateValueAndValidity();
      this.retailFinanceForm.get('estimatedFinanceAmount').clearValidators()
      this.retailFinanceForm.get('estimatedFinanceAmount').updateValueAndValidity();
      this.retailFinanceForm.get('financeStatus').clearValidators()
      this.retailFinanceForm.get('financeStatus').updateValueAndValidity();
    }
    this.enquiryPresenter.calculateMarginAmount()
  }

  selectionFinanceStatus(value) {
    if (value.financeStatus === 'Payment Disbursed') {
      this.enquiryPresenter.isFinanceStatus = true
      this.enquiryPresenter.resetDisbursedForFinanceStatus()
    } else {
      this.enquiryPresenter.isFinanceStatus = false
    }
    this.enquiryPresenter.calculateMarginAmount()
  }

  selectionSubsidy(value) {
    if (value.subsidy === 'YES') {
      this.enquiryPresenter.isSubSidySelect = true
      this.enquiryPresenter.mandatoryFieldForSubsidy(true)
    } else {
      this.enquiryPresenter.resetForSubSidy()
      this.enquiryPresenter.isSubSidySelect = false
      this.enquiryPresenter.mandatoryFieldForSubsidy(false)
    }
    this.enquiryPresenter.calculateMarginAmount()
  }

  totalMarginAmount() {
    let productIntrested = this.enquiryPresenter.enquiryForm.get('productIntrested')
    this.retailFinanceForm.get('assetValue').valueChanges.subscribe(value => {
      this.enquiryPresenter.calculateMarginAmount()
      this.totalAmount()
    })
    this.retailFinanceForm.get('subsidyAmount').valueChanges.subscribe(value => {
      this.enquiryPresenter.calculateMarginAmount()
      this.totalAmount()
    })
    this.retailFinanceForm.get('estimatedFinanceAmount').valueChanges.subscribe(value => {
      console.log('estimatedFinanceAmount',value)
      this.enquiryPresenter.calculateMarginAmount()
      this.totalAmount()
    })
    this.retailFinanceForm.get('finalExchangePrice').valueChanges.subscribe(value => {
      this.enquiryPresenter.calculateMarginAmount()
      this.totalAmount()
    })
  }

  totalAmount() {
    let productIntrested = this.enquiryPresenter.enquiryForm.get('productIntrested')
    const amt = this.retailFinanceForm.get('finalExchangePrice').value + this.retailFinanceForm.get('estimatedFinanceAmount').value + this.retailFinanceForm.get('subsidyAmount').value
    console.log("amt ", amt);
    if (this.retailFinanceForm.get('assetValue').value > amt) {
      productIntrested.get('estimatedExchangePrice').setErrors(null)
      this.retailFinanceForm.get('estimatedFinanceAmount').setErrors(null)
      this.retailFinanceForm.get('subsidyAmount').setErrors(null)
    }
  }

  onKeyPressAlphaNumericsOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowAlphaNumericsOnly(event)
  }

  compareFnCashLoan(c1: CashLoan, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.cashLoan === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.cashLoan;
    }
    return c1 && c2 ? c1.cashLoan === c2.cashLoan : c1 === c2;
  }

  compareFnFinanceStatus(c1: FinalStatus, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.financeStatus === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.financeStatus;
    }
    return c1 && c2 ? c1.financeStatus === c2.financeStatus : c1 === c2;
  }

  checkEstimatedFinanceAmount(event){
    let assetValue = this.retailFinanceForm.get('assetValue').value
        if (event>assetValue) {
            this.retailFinanceForm.get('estimatedFinanceAmount').setErrors({
                estimatedFinanceAmtError:'Amount exceeds Asset Value'
            })
        }
    }

    checkSubsidyAmount(event){
      let assetValue = this.retailFinanceForm.get('assetValue').value
      let estFinAmt = this.retailFinanceForm.get('estimatedFinanceAmount').value
      let loanFieldVal = this.retailFinanceForm.get('cashLoan').value
      let subSidyVal =  this.retailFinanceForm.get('subsidy').value
      let totalAmt=assetValue-estFinAmt
          if (event>assetValue) {
              this.retailFinanceForm.get('subsidyAmount').setErrors({
                subSidyError:'Amount exceeds Asset Value'
              })
          }
          if (loanFieldVal.cashLoan==='Loan' && subSidyVal.subsidy==='YES') {
             if (event>totalAmt){
              this.retailFinanceForm.get('subsidyAmount').setErrors({
                subSidyError:'Invalid Subsidy Amount'
              })
             }
          }
      }


}
