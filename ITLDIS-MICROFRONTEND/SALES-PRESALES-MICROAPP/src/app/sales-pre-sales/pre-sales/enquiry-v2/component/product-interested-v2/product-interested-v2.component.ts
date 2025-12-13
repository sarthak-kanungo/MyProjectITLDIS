import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { FormGroup } from '@angular/forms';
import { itldisPatterns } from '../../../../../utils/itldis-patterns';
import { Observable } from 'rxjs';
import { ExchangeBrand, ItemNumber, Model, AutoCompSubModel, AutoCompVariant, ViewEnquiry } from '../../domains/enquiry';
import { ProductInterestedV2WebService } from '../../services/product-interested-v2-web.service';
import { ProspectDetailsV2WebService } from '../../services/prospect-details-v2-web.service';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { BaseDto } from 'BaseDto';
import { Operation } from '../../../../../utils/operation-util';

@Component({
  selector: 'app-product-interested-v2',
  templateUrl: './product-interested-v2.component.html',
  styleUrls: ['./product-interested-v2.component.scss'],
  providers: [ProductInterestedV2WebService, ProspectDetailsV2WebService]
})
export class ProductInterestedV2Component implements OnInit, OnChanges {

  productIntrested: FormGroup
  isView: boolean
  isViewMobile: boolean
  exchanges = [
    { exchangeRequired: 'YES' },
    { exchangeRequired: 'NO' }
  ];
  labelPosition = 'after';
  exchangeBrand: Array<ExchangeBrand>
  itemNumber$: Observable<Array<ItemNumber>>
  model$: Observable<Array<Model>>
  subModels: Array<AutoCompSubModel>
  variant$: Observable<Array<AutoCompVariant>>
  isShowByExchangeRequiredValue: boolean
  selectModel: string
  model: string
  isExchangeModel: boolean
  @Input() enquiryByEnquiryNumber: ViewEnquiry
  exchangeReceived:boolean = false
  constructor(
    private presenter: EnquiryPresenter,
    private itldisPatterns: itldisPatterns,
    private productInterestedV2WebService: ProductInterestedV2WebService,
    private prospectDetailsV2WebService: ProspectDetailsV2WebService,
    private dialog: MatDialog
  ) { }

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {
      if (this.enquiryByEnquiryNumber.enquiryStatus == "ALLOTTED" || this.enquiryByEnquiryNumber.countUpdate >=1) {
        this.productIntrested.get('model').disable();
      }
      this.model = this.enquiryByEnquiryNumber.model
      this.productIntrested.patchValue(this.enquiryByEnquiryNumber)
      this.productIntrested.get('itemNo').patchValue({ itemNo: this.enquiryByEnquiryNumber.itemNo })
      if(this.enquiryByEnquiryNumber.itemNo!=undefined && this.enquiryByEnquiryNumber.itemNo!=null && this.enquiryByEnquiryNumber.itemNo!=''){
          this.productIntrested.get('itemNo').disable();
      }
      this.productIntrested.get('model').patchValue({ model: this.enquiryByEnquiryNumber.model })
      this.productIntrested.get('subModel').patchValue({ subModel: this.enquiryByEnquiryNumber.subModel })
      this.productIntrested.get('exchangeBrand').patchValue({ exchangeBrand: this.enquiryByEnquiryNumber.exchangeBrand })
      this.productIntrested.get('variant').patchValue({ variant: this.enquiryByEnquiryNumber.variant })
      let exchangeRequiredObj = this.exchanges.findIndex(res => res.exchangeRequired === this.enquiryByEnquiryNumber.exchangeRequired)
      this.productIntrested.get('exchangeRequired').patchValue(this.exchanges[exchangeRequiredObj])
      if (this.enquiryByEnquiryNumber.exchangeRequired === 'YES') {
        this.isShowByExchangeRequiredValue = true
        this.presenter.isExchangerequired = true
        this.isExchangeModel = true
      }
      this.productIntrested.get('itemNo').setErrors(null);
      if(this.enquiryByEnquiryNumber.exchangeReceived==='Y'){
          this.exchangeReceived = true;
          this.productIntrested.get('exchangeReceived').patchValue(true);
          this.productIntrested.get('exchangeReceived').disable();
          this.productIntrested.get('exchangeModelYear').disable();
          this.productIntrested.get('estimatedExchangePrice').disable();
          this.productIntrested.get('exchangeModel').disable();
          this.productIntrested.get('exchangeBrand').disable();
          this.productIntrested.get('exchangeRequired').disable();
      }else{
          this.exchangeReceived = false;
          this.productIntrested.get('exchangeReceived').patchValue(false);
      }
    }
  }
  ngOnInit() {
    this.patchOrCreate()
    this.loadDropDownExchangeBrand()
    this.valueChangesForAutoComplate()
    this.patchValueForActualeExchangeAmt()
  }

  private patchOrCreate() {
    this.productIntrested = this.presenter.enquiryForm.get('productIntrested') as FormGroup
  }

  patchValueForActualeExchangeAmt() {
    if (this.presenter.operation === Operation.VIEW) {
      this.productIntrested.get('estimatedExchangePrice').valueChanges.subscribe(exchangePrice => {
        this.presenter.finalExchangePrice.patchValue(exchangePrice)
        this.presenter.calculateMarginAmount()
      })
    }
  }

  onKeyPressAllowNumbersOnly(event: KeyboardEvent) {
    
    this.itldisPatterns.allowNumbersOnly(event)
  }

  loadDropDownExchangeBrand() {
    this.productInterestedV2WebService.getExchangeBrand().subscribe(response => {
      this.exchangeBrand = response.result as Array<ExchangeBrand>
    })
  }

  onKeyPressAlphaNumericsOnly(event: KeyboardEvent) {
    this.itldisPatterns.allowAlphaNumericsOnly(event)
  }

  valueChangesForAutoComplate() {
    this.productIntrested.get('itemNo').valueChanges.subscribe(value => {
      if (value) {
        let itemNo = typeof value == 'object' ? value.itemNo : value
        this.autoItemNo(itemNo)
      }
      if (value !== null) {
        this.productIntrested.get('itemNo').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
    this.productIntrested.get('model').valueChanges.subscribe(value => {      
      if (value) {
        console.log("model--->", value)
        let model = typeof value == 'object' ? value.model : value
        this.autoModel(model)
      }
      if (value !== null && typeof value != 'object') {
        this.productIntrested.get('model').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
    this.productIntrested.get('subModel').valueChanges.subscribe(value => {
      if (value !== null) {
        this.productIntrested.get('subModel').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
    this.productIntrested.get('variant').valueChanges.subscribe(value => {
      if (value !== null) {
        this.productIntrested.get('variant').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })

    this.productIntrested.get('exchangeModelYear').valueChanges.subscribe((value) => {
      let year = parseInt(value);
      console.log('checkPoint1--',year);
      
      //(new Date()).getFullYear()
      //if (year < 1900 || year > 2099 ) {
        if (year>(new Date()).getFullYear()) {
        this.productIntrested.get('exchangeModelYear').setErrors({
          invalidYear: 'Invalid Year',
        });
      }
    })
  }

  autoItemNo(itemNo: string) {
    if (this.presenter.operation === Operation.CREATE) {
      this.itemNumber$ = this.productInterestedV2WebService.getItemNumberModelProductSeries(itemNo.toUpperCase())
    } else if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.VIEW_MOBILE) {
      this.itemNumber$ = this.productInterestedV2WebService.getItemByModel(itemNo, this.model)
    }
  }

  displayFnItemNo(itemNum: ItemNumber) {
    return itemNum ? itemNum.itemNo : undefined
  }

  optionSelectedItemNo(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.productIntrested.get('itemNo').setErrors(null);
    }
    //if (this.presenter.operation === Operation.CREATE || this.presenter.operation === Operation.VIEW_MOBILE) {
      this.productInterestedV2WebService.getByItemNo(event.option.value.itemNo).subscribe(response => {
        console.log(response);
        this.productIntrested.patchValue(response)
        this.productIntrested.get('model').patchValue({ model: response.model })
        this.productIntrested.get('subModel').patchValue({ subModel: response.subModel })
        this.productIntrested.get('variant').patchValue({ variant: response.variant })
        this.productIntrested.get('model').setErrors(null);       
        this.productIntrested.get('subModel').setErrors(null);        
        this.productIntrested.get('variant').setErrors(null);
        
        //this.productIntrested.get('model').disable();
        this.productIntrested.get('subModel').disable();
        this.productIntrested.get('variant').disable();
        // let mobileNumber = this.presenter.enquiryForm.get('prospectDetails').value.mobileNumber
        // if (mobileNumber) {
        //   this.prospectDetailsV2WebService.checkItemNumberModelInEnquiry(response.model, mobileNumber.mobileNumber).subscribe(response => {
        //     console.log(response);
        //     let checkEnquiry = response as BaseDto<number>
        //     if (checkEnquiry.result === 0) {
        //       this.openConfirmDialog()
        //     }
        //   })
        // }
      })
    //}
    /*if (this.presenter.operation === Operation.VIEW) {
      this.productInterestedV2WebService.getByItemNo(event.option.value.itemNo).subscribe(response => {
        console.log(response);
        this.productIntrested.get('itemDescription').patchValue(response.itemDescription)
      })
    }*/
  }

  onKeyDownItemNo(event: KeyboardEvent) {
    console.log(event);
    if (this.presenter.operation === Operation.CREATE) {
      if (event.key === 'Backspace' || event.key === 'Delete')
        this.presenter.resetProductHierarchyForItemNumber()
    }
    if (event.key === 'Tab') {
      this.productIntrested.get('itemNo').reset()
      this.productIntrested.get('itemNo').setErrors({
        selectFromList: 'Please select from list',
      });
    }
  }

  autoModel(model) {
    this.model$ = this.productInterestedV2WebService.getModel(model)
  }

  displayFnModel(value: Model) {
    return value ? value.model : undefined
  }

  onKeyDownModel(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.presenter.resetProductHierarchyForModel()
    }
    if (event.key === 'Tab') {
      this.productIntrested.get('model').reset()
      this.productIntrested.get('model').setErrors({
        selectFromList: 'Please select from list',
      });
    }
  }

  optionSelectedModel(event) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.productIntrested.get('model').setErrors(null);
    }
    this.selectModel = event.option.value.model
    this.checkItemNoModelForEnquiry()
    this.productInterestedV2WebService.getSeriesAndProductByModel(event.option.value.model).subscribe(response => {
      this.productIntrested.get('series').patchValue(response.series)
      this.productIntrested.get('product').patchValue(response.product)
    })
    console.log("event.option.value.model--->", event.option.value.model)
    this.itemNumber$ = this.productInterestedV2WebService.getItemByModel(event.option.value.model, event.option.value.model)
    console.log("this.itemNumber$--->", this.itemNumber$)
    this.productInterestedV2WebService.getSubModel(event.option.value.model).subscribe(response => {
      this.subModels = response.result
    })
    //this.itemNumber$ = this.productInterestedV2WebService.getItemByModel('', this.model);
    this.productIntrested.get('itemNo').reset()
    this.productIntrested.get('subModel').reset()
    this.productIntrested.get('variant').reset()
    this.productIntrested.get('subModel').enable()
    this.productIntrested.get('variant').enable()
    
    if (this.presenter.operation == Operation.VIEW && this.enquiryByEnquiryNumber.enquiryStatus != "ALLOTTED") {
      console.log("enquiryByEnquiryNumber_countUpdate------>", this.enquiryByEnquiryNumber.countUpdate)
      this.productIntrested.get('model').disable()
      this.productIntrested.get('itemNo').enable()
      // this.productIntrested.get('subModel').disable()
      // this.productIntrested.get('variant').disable()
    }
  }

  checkItemNoModelForEnquiry() {
    let mobileNumber = this.presenter.enquiryForm.get('prospectDetails').value.mobileNumber
    if (mobileNumber) {
      this.prospectDetailsV2WebService.checkItemNumberModelInEnquiry(this.selectModel, mobileNumber.mobileNumber).subscribe(response => {
        console.log(response);
        let checkEnquiry = response as BaseDto<number>
        if (checkEnquiry.result === 0) {
          this.openConfirmDialog()
        }
      })
    }
  }

  onKeyDownSubModel(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.productIntrested.get('variant').reset()
    }
    if (event.key === 'Tab') {
      this.productIntrested.get('subModel').reset()
      this.productIntrested.get('subModel').setErrors({
        selectFromList: 'Please select from list',
      });
    }
  }

  displayFnSubModel(value: AutoCompSubModel) {
    return value ? value.subModel : undefined
  }

  optionSelectedSubModel(event) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.productIntrested.get('subModel').setErrors(null);
    }
    let model = this.productIntrested.controls.model.value.model
    this.variant$ = this.productInterestedV2WebService.getVariant(model, event.option.value.subModel)
  }

  displayFnvariant(value: AutoCompVariant) {
    return value ? value.variant : undefined
  }

  onKeyDownVariant(event: KeyboardEvent) {
    if (event.key === 'Tab') {
      this.productIntrested.get('variant').reset()
      this.productIntrested.get('variant').setErrors({
        selectFromList: 'Please select from list',
      });
    }
  }

  optionSelectedVariant(event) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.productIntrested.get('variant').setErrors(null);
    }
    let model = this.productIntrested.controls.model.value.model
    let subModel = this.productIntrested.controls.subModel.value.subModel
    this.itemNumber$ = this.productInterestedV2WebService.getItemByModelSubModelVariant(model, subModel, event.option.value.variant)
  }

  selectionExchangeRequired(value) {
    if (value.exchangeRequired === 'YES') {
      this.isShowByExchangeRequiredValue = true
      this.presenter.mandatoryFieldForExchangeRequired(true)
      this.presenter.isExchangerequired = true
      this.productIntrested.get('estimatedExchangePrice').valueChanges.subscribe(exchangePrice => {
        this.presenter.finalExchangePrice.patchValue(exchangePrice)
        this.presenter.calculateMarginAmount()
      })
      if (this.presenter.operation === Operation.VIEW){
        this.isExchangeModel = true
      }
    } else {
      this.isShowByExchangeRequiredValue = false
      this.presenter.isExchangerequired = false
      this.presenter.resetExchangeForExchanreRequired()
      this.presenter.mandatoryFieldForExchangeRequired(false)
    }
    this.presenter.calculateMarginAmount()
  }

  private openConfirmDialog(): void | any {
    let message = `Enquiry already created  with model Do you want to Continue?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Ok') {
      }

    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Ok'];
    return confirmationData;
  }
  compareFnExchangeBrand(c1: ExchangeBrand, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.exchangeBrand === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.exchangeBrand;
    }
    return c1 && c2 ? c1.exchangeBrand === c2.exchangeBrand : c1 === c2;
  }

}
