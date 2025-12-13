import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { EnquiryProductInterestedService } from './enquiry-product-interested.service';
import { ItemNo, DropDownExchangeBrand, AutoPopulateByItemNo, AutoModel, SubModel, Variant, AutoPopulateByModel } from 'EnquiryProductIntrested';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { EnquiryProductInstrestedContainerService } from '../enquiry-product-interested-container/enquiry-product-instrested-container.service';
import { EnquiryService } from '../../enquiry.service';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { SalesPreSalesCommonService } from '../../../../sales-pre-sales-common-service/sales-pre-sales-common.service';
import { BaseDto } from 'BaseDto';
import { ProspectDetailsContainerService } from '../prospect-details-container/prospect-details-container.service';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-enqiry-product-interested',
  templateUrl: './enqiry-product-interested.component.html',
  styleUrls: ['./enqiry-product-interested.component.scss'],
  providers: [EnquiryProductInterestedService, EnquiryProductInstrestedContainerService,
    EnquiryCreationContainerService, SalesPreSalesCommonService, ProspectDetailsContainerService]
})
export class EnqiryProductInterestedComponent implements OnInit {
  isView: boolean
  isEdit: boolean
  isViewMobile: boolean
  exchanges = [
    { exchangeRequired: 'YES' },
    { exchangeRequired: 'NO' }
  ];

  @Input() itemNoList: BaseDto<Array<ItemNo>>
  dropDownExchangeBrand: BaseDto<Array<DropDownExchangeBrand>>
  autoPopulateByItemNoList: BaseDto<AutoPopulateByItemNo>
  @Input() model: BaseDto<Array<AutoModel>>
  @Input() subModel: BaseDto<Array<SubModel>>
  variant: BaseDto<Array<Variant>>
  productInterestedForm: FormGroup;
  @Output()
  autoItemNo = new EventEmitter<string>()
  @Output() autoPopulateDatabyItemNo = new EventEmitter<BaseDto<Array<ItemNo>>>()
  @Input() set autoPopulateByItemNo(list: BaseDto<AutoPopulateByItemNo>) {
    this.autoPopulateByItemNoList = list as BaseDto<AutoPopulateByItemNo>
    if (this.productInterestedForm && !this.isView) {
      this.productInterestedForm.patchValue(this.autoPopulateByItemNoList.result)
    }

  }
  @Input() set autoPopulateByModel(value: BaseDto<AutoPopulateByModel>) {
    if (this.productInterestedForm !== undefined) {
      this.productInterestedForm.controls.series.patchValue(value.result.series)
      this.productInterestedForm.controls.product.patchValue(value.result.product)
    }
  }
  @Input() getMobileNumber: string
  showexchange: boolean
  @Output()
  autoModel = new EventEmitter<string>()
  @Output() autoPopulatebyModel = new EventEmitter<BaseDto<Array<AutoModel>>>()
  @Output() getFormStatusandData = new EventEmitter<object>()
  checkEnquiry: BaseDto<number>
  modelValue: string
  @Input() viewPatchEnquiryData: ViewEnquiryDomain

  constructor(
    private enquiryProductInterestedService: EnquiryProductInterestedService,
    private enquiryService: EnquiryService,
    private prospectDetailsContainerService: ProspectDetailsContainerService,
    private enquiryProductInstrestedContainerService: EnquiryProductInstrestedContainerService,
    private enquiryCommonService: EnquiryCommonService,
    private enqRt: ActivatedRoute,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.loadAllDropDownData().subscribe(dt => {
      this.dropDownExchangeBrand = dt[0] as BaseDto<Array<DropDownExchangeBrand>>

      this.patchOrCreate()
    })
  }

  private patchOrCreate() {
    if (this.isView) {
      this.parseIdAndViewForm()
    } else if (this.isViewMobile) {
      this.parseIdAndViewMobileForm()
    } else {
      this.formForCreateSetup()
    }
  }

  private formForCreateSetup() {
    this.productInterestedForm = this.enquiryProductInterestedService.createproductInterestedForm()
    this.productInterestedForm.controls.itemNo.valueChanges.subscribe(value => {
      //console.log(value)
      if (value) {
        this.autoItemNo.emit(value);
      }
    });
    this.productInterestedForm.controls.model.valueChanges.subscribe(value => {
      //console.log(value)
      if (value) {
        this.autoModel.emit(value)
      }
    });
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.getFormStatusandData.emit({ validStatus: this.productInterestedForm.valid, formData: this.productInterestedForm.getRawValue() });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.productInterestedForm.reset();
      }
    });
    this.productInterestedForm.controls.exchangeModelYear.valueChanges.subscribe(year => {
      
        console.log("year", year)
    })
  }

  changesModel(event) {
    console.log('keyup', event);

  }
  keyPress(event: any) {
    this.enquiryProductInterestedService.keyPress(event)
  }
  onKeyDownItemNo(event: KeyboardEvent) {
    console.log(event.key);
    if (event.key === 'Backspace' || event.key === 'Delete') {
      if (!this.isView && !this.isViewMobile) {
        this.productInterestedForm.controls.itemDescription.reset()
        this.productInterestedForm.controls.model.reset()
        this.productInterestedForm.controls.subModel.reset()
        this.productInterestedForm.controls.variant.reset()
        this.productInterestedForm.controls.series.reset()
        this.productInterestedForm.controls.product.reset()
      }
    }

  }
  onKeyDownModel(event: any) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.productInterestedForm.controls.itemNo.reset()
      this.productInterestedForm.controls.itemDescription.reset()
      this.productInterestedForm.controls.subModel.reset()
      this.productInterestedForm.controls.variant.reset()
      this.productInterestedForm.controls.series.reset()
      this.productInterestedForm.controls.product.reset()
    }
  }

  optionSelectedModel(event) {
    console.log(event.option.value);
    this.modelValue = event.option.value
    console.log(this.getMobileNumber);
    if (this.getMobileNumber) {
      this.prospectDetailsContainerService.checkItemNumberModelInEnquiry(event.option.value, this.getMobileNumber).subscribe(response => {
        console.log(response);
        this.checkEnquiry = response as BaseDto<number>
        if (this.checkEnquiry.result === 0) {
          this.openConfirmDialog()
        }
      })
    }
    this.autoPopulatebyModel.emit(event.option.value)

  }

  optionSelectedSubModel(event) {
    // this.autoPopulatebySubModel.emit(event.option.value)
    let model = this.productInterestedForm.controls.model.value
    console.log('model', model);
    this.enquiryProductInstrestedContainerService.searchVariant(model, event.option.value).subscribe(response => {
      console.log("variant ", response);
      this.variant = response as BaseDto<Array<Variant>>
    });
  }

  optionSelectedVariant(event) {
    let model = this.productInterestedForm.controls.model.value
    let subModel = this.productInterestedForm.controls.subModel.value
    this.enquiryProductInstrestedContainerService.getItemByModelSubModelVariant(model, subModel, event.option.value).subscribe(response => {
      this.itemNoList = response as BaseDto<Array<ItemNo>>
      console.log(' this.optionSelectedVariant', this.itemNoList)
    })
  }

  selectionExchangeRequired(value) {
    if (value === 'YES') {
      this.showexchange = true
      this.productInterestedForm.controls.exchangeBrand.setValidators(Validators.compose([Validators.required]))
      this.productInterestedForm.controls.exchangeBrand.updateValueAndValidity();
      this.productInterestedForm.controls.exchangeBrand.reset()
      this.productInterestedForm.controls.exchangeModel.reset()
      this.productInterestedForm.controls.estimatedExchangePrice.reset()
    } else {
      this.showexchange = false
      this.productInterestedForm.controls.exchangeBrand.clearValidators()
      this.productInterestedForm.controls.exchangeBrand.updateValueAndValidity();
    }
    this.enquiryService.selectExchangeRequired.emit(this.showexchange)
    this.productInterestedForm.controls.estimatedExchangePrice.valueChanges.subscribe(value => {
      this.enquiryService.estimatedExcnangPrice.emit(value)
    })
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      this.productInterestedForm.patchValue(domain)
      this.dropDownExchangeBrand.result.findIndex(res => res.exchangeBrand === domain.exchangeBrand)
      this.productInterestedForm.controls.exchangeBrand.patchValue(domain.exchangeBrand)
      this.exchanges.findIndex(res => res.exchangeRequired === domain.exchangeRequired)
      this.productInterestedForm.controls.exchangeRequired.patchValue(domain.exchangeRequired)
      if (domain.exchangeRequired === 'YES') {
        this.showexchange = true
      }
    }
  }
  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    console.log(domain)
    if (domain) {
      Object.keys(domain).map(key => domain[key] = domain[key] == 'NA' ? null : domain[key])
      this.productInterestedForm.patchValue(domain)
      this.dropDownExchangeBrand.result.findIndex(res => res.exchangeBrand === domain.exchangeBrand)
      this.productInterestedForm.controls.exchangeBrand.patchValue(domain.exchangeBrand)
      this.exchanges.findIndex(res => res.exchangeRequired === domain.exchangeRequired)
      this.productInterestedForm.controls.exchangeRequired.patchValue(domain.exchangeRequired)
    }
  }
  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enqNo']))
    this.productInterestedForm = this.enquiryProductInterestedService.ViewproductInterestedForm()
    this.productInterestedForm.controls.itemNo.valueChanges.subscribe(value => {
      let model = this.productInterestedForm.controls.model.value
      this.enquiryProductInstrestedContainerService.getItemByModel(value, model).subscribe(response => {
        console.log('no==>', response);
        this.itemNoList = response as BaseDto<Array<ItemNo>>
        console.log(' this.parseIdAndViewForm', this.itemNoList)

      })
    });

    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        console.log('====>', this.productInterestedForm.getRawValue());

        this.getFormStatusandData.emit({ validStatus: this.productInterestedForm.valid, formData: this.productInterestedForm.getRawValue() });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.productInterestedForm.reset();
      }
    })
  }
  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewMobileEnqNo(prms['mobenqNo']))
    this.productInterestedForm = this.enquiryProductInterestedService.ViewMobileproductInterestedForm()
    this.productInterestedForm.controls.itemNo.valueChanges.subscribe(value => {
      console.log('parseIdAndViewMobileForm', value)
      let model = this.productInterestedForm.controls.model.value
      this.enquiryProductInstrestedContainerService.getItemByModel(value, model).subscribe(response => {
        console.log('no==>', response);
        this.itemNoList = response as BaseDto<Array<ItemNo>>
        console.log(' this.parseIdAndViewMobileForm', this.itemNoList)

      })
    });
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.getFormStatusandData.emit({ validStatus: this.productInterestedForm.valid, formData: this.productInterestedForm.getRawValue() });
        this.markFormAsTouched();
      }
      else if (value.toLowerCase() === 'clear') {
        this.productInterestedForm.reset();
      }
    })
  }

  optionSelectedItemNo(event) {
    console.log('event', event)
    console.log(event.option.value);
    this.autoPopulateDatabyItemNo.emit(event.option.value.itemNo)
  }

  private fatchDataForEnqNo(enqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enqNo).subscribe(dto => { this.formForViewSetup(dto.result) })
  }
  private fatchDataForViewMobileEnqNo(mobenqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + mobenqNo).subscribe(dto => { this.formForViewMobileSetup(dto.result) })
  }
  private checkOperationType() {
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isViewMobile = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'viewMobile'
  }
  private intiOperationForm() {
    if (this.isView) {
      this.productInterestedForm = this.enquiryProductInterestedService.ViewproductInterestedForm()
    } else if (this.isViewMobile) {
      this.productInterestedForm = this.enquiryProductInterestedService.ViewMobileproductInterestedForm()
    } else {
      this.productInterestedForm = this.enquiryProductInterestedService.createproductInterestedForm()
    }
  }
  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.enquiryProductInstrestedContainerService.dropdownExchangeBrand())

    return forkJoin(...dropDownTask)
  }

  private markFormAsTouched() {
    for (const key in this.productInterestedForm.controls) {
      if (this.productInterestedForm.controls.hasOwnProperty(key)) {
        this.productInterestedForm.controls[key].markAsTouched();
      }
    }
  }
  public selectItemNo(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['itemNo'] : undefined;
  }

  private openConfirmDialog(): void | any {
    let message = `Enquiry already created  with model ${this.modelValue} `;
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
}
