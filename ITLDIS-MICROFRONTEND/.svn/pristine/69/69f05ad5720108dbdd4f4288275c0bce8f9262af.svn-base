import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { CurrentMachineryDetailsService } from './current-machinery-details.service';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownBrand, CurrentMachineObj } from 'CurrentMachineDetails';
import { CurrentMachineDetailsContainerService } from '../current-machine-details-container/current-machine-details-container.service';
import { EnquiryService } from '../../enquiry.service';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { ActivatedRoute } from '@angular/router';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { ProspectDetailsObj } from 'ProspectDetails';
import { Observable, forkJoin, interval } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import 'rxjs/add/observable/interval';

@Component({
  selector: 'app-current-machinery-details',
  templateUrl: './current-machinery-details.component.html',
  styleUrls: ['./current-machinery-details.component.scss'],
  providers: [CurrentMachineryDetailsService, CurrentMachineDetailsContainerService, EnquiryCreationContainerService]
})

export class CurrentMachineryDetailsComponent implements OnInit {
  isView: boolean;
  isEdit: boolean
  isViewMobile: boolean
  currentMachineryDetailsForm: FormGroup;
  dropDownBrand: BaseDto<Array<DropDownBrand>>
  deleteDataRow = []
  @Output() getFormStatusandFormData = new EventEmitter()
  @Input() dataAutoPopulateByMobileNumber: ProspectDetailsObj

  constructor(
    private fb: FormBuilder,
    private currentMachineryDetailsService: CurrentMachineryDetailsService,
    private enquiryService: EnquiryService,
    private enqRt: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.createcurrentMachineryDetailsForm();
    this.checkOperationType()
    this.loadAllDropDownData().subscribe(dt => {
      this.dropDownBrand = dt[0] as BaseDto<Array<DropDownBrand>>
      this.patchOrCreate()
    })

    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        console.log('====>', this.currentMachineryDetailsForm.value.machineryDetails.length);
        if (this.currentMachineryDetailsForm.value.machineryDetails.length > 0) {
          this.getFormStatusandFormData.emit({ validStatus: this.currentMachineryDetailsForm.valid, formData: this.currentMachineryDetailsForm.value.machineryDetails });
        } else {
          this.getFormStatusandFormData.emit({ validStatus: this.currentMachineryDetailsForm.valid, formData: null });
        }
      } else if (value.toLowerCase() === 'clear') {
        this.currentMachineryDetailsForm.reset();
      }
    })
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        console.log('====>', this.currentMachineryDetailsForm.value.machineryDetails.length);
        if (this.currentMachineryDetailsForm.value.machineryDetails.length > 0) {
          this.getFormStatusandFormData.emit({ validStatus: this.currentMachineryDetailsForm.valid, formData: this.currentMachineryDetailsForm.value.machineryDetails, deleteRow: this.deleteDataRow });
        } else {
          this.getFormStatusandFormData.emit({ validStatus: this.currentMachineryDetailsForm.valid, formData: null });
        }
      } else if (value.toLowerCase() === 'clear') {
        this.currentMachineryDetailsForm.reset();
      }
    })
  }

  private patchOrCreate() {
    if (this.isView) {
      this.parseIdAndViewForm()
    } else if (this.isViewMobile) {
      this.parseIdAndViewMobileForm()
      this.isView = false
    } else {
    }
  }

  keyyear(event: any) {
    this.currentMachineryDetailsService.keyyear(event)
  }
  createcurrentMachineryDetailsForm() {
    this.currentMachineryDetailsForm = this.fb.group({
      machineryDetails: this.fb.array([])
    });
  }

  createMachinaryDetailsRow() {
    return this.fb.group({
      id: this.fb.control(null),
      isSelected: this.fb.control(false),
      brand: this.fb.control(null),
      model: this.fb.control(null),
      yearOfPurchase: this.fb.control(null)
    });
  }

  viewMachinaryDetailsRow(product?: CurrentMachineObj) {
    return this.fb.group({
      id: this.fb.control(product ? product.id : null),
      brand: this.fb.control(product ? product.brand : null),
      model: this.fb.control(product ? product.model : null),
      yearOfPurchase: this.fb.control(product ? product.yearOfPurchase : null),
      isSelected: this.fb.control(false)
    })
  }

  viewMobileMachinaryDetailsRow() {
    return this.fb.group({
      id: this.fb.control(''),
      brand: this.fb.control(null),
      model: this.fb.control(null),
      yearOfPurchase: this.fb.control(null),
      isSelected: this.fb.control(false)
    })
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    domain.enquiryMachineryDetails.forEach(dtl => {
      this.addRow(null, dtl)
    })
  }

  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    this.addRow(domain)
  }

  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enqNo']))
  }
  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewEnqNo(prms['mobenqNo']))
  }
  private fatchDataForEnqNo(enqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enqNo).subscribe(dto => { this.formForViewSetup(dto.result) })
  }
  private fatchDataForViewEnqNo(mobenqNo: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + mobenqNo).subscribe(dto => { this.formForViewMobileSetup(dto.result) })
  }

  addRow(product?: ViewEnquiryDomain, mcDtl?: CurrentMachineObj ) {
    let machineryDetails = this.currentMachineryDetailsForm.controls.machineryDetails as FormArray
    console.log("addRow machineryDetails ", machineryDetails);
    if (this.isView) {
      machineryDetails.push(this.viewMachinaryDetailsRow(mcDtl))
    } else if (this.isViewMobile) {
      machineryDetails.push(this.viewMobileMachinaryDetailsRow())
    } else {
      machineryDetails.push(this.createMachinaryDetailsRow())
    }
  }
  deleteRow() {
    let machineryDetails = this.currentMachineryDetailsForm.controls.machineryDetails as FormArray;
    let nonSelected = machineryDetails.controls.filter(machinery => !machinery.value.isSelected)
    let selected = machineryDetails.controls.filter(machinery => machinery.value.isSelected)
    console.log(selected);
    selected.forEach(el => {
      this.deleteDataRow.push({
        id: el.value.id,
        isSelected : el.value.isSelected,
        brand : el.value.brand,
        model: el.value.model,
        yearOfPurchase : el.value.yearOfPurchase
      })
    })
    console.log(this.deleteDataRow);
    
    if (machineryDetails.length - nonSelected.length) {
      machineryDetails.clear()
      nonSelected.forEach(el => machineryDetails.push(el))
    } else {
      this.toastr.warning('Atleast one Select Row', 'Report Delete Row')
    }
  }

  private checkOperationType() {
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isViewMobile = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'viewMobile'
  }

  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.enquiryCommonService.dropdownbrand())

    return forkJoin(...dropDownTask)
  }
}
