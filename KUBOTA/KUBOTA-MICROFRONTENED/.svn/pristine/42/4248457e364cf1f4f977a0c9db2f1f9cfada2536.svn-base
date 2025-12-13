import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProspectBackgroundService } from './prospect-background.service';
import { ProspectDetailsObj } from 'ProspectDetails';
import { DropDownOccupation, DropDownSoilType, DropDownMajorCropGrown } from 'ProspectBackground';
import { ProspectBackgroundContainerService } from '../prospect-background-container/prospect-background-container.service';
import { EnquiryService } from '../../enquiry.service';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { BaseDto } from 'BaseDto';
import 'rxjs/add/observable/interval';
import { MatSelect } from '@angular/material';

@Component({
  selector: 'app-prospect-background',
  templateUrl: './prospect-background.component.html',
  styleUrls: ['./prospect-background.component.scss'],
  providers: [ProspectBackgroundService, ProspectBackgroundContainerService, EnquiryCreationContainerService]
})
export class ProspectBackgroundComponent implements OnInit {
  isView: boolean
  isEdit: boolean
  isViewMobile: boolean
  dropDownOccupation: BaseDto<Array<DropDownOccupation>>
  dropDownSoilType: BaseDto<Array<DropDownSoilType>>
  dropDownMajorCropGrown: BaseDto<Array<DropDownMajorCropGrown>>
  showotherinput: boolean;
  prospectBackgroundForm: FormGroup;
  @Input() set dataAutoPopulateByMobileNumber(value: ProspectDetailsObj) {
    // console.log('value', value);
    if (this.prospectBackgroundForm) {
      this.dropDownOccupation.result.findIndex(res => res.occupation === value.occupation)
      this.prospectBackgroundForm.controls.occupation.patchValue(value.occupation)
      this.prospectBackgroundForm.controls.landSize.patchValue(value.landSize)
      let selectedSoilType = [];
      value.prospectSoilTypes.forEach(type => {
        selectedSoilType.push(this.dropDownSoilType.result[this.dropDownSoilType.result.findIndex(res => res.soilType === type.soilName)]);
      })
      this.prospectBackgroundForm.controls.soilName.setValue(selectedSoilType);
      let selectedCropGrown = [];
      value.prospectCropNames.forEach(type => {
        selectedCropGrown.push(this.dropDownMajorCropGrown.result[this.dropDownMajorCropGrown.result.findIndex(res => res.cropGrown === type.cropName)]);
      })
      this.prospectBackgroundForm.controls.cropName.setValue(selectedCropGrown);
    }
  }
  @Input() set keyDownMobileNo(value: string) {
    if (value === 'Backspace' || value === 'Delete') {
      this.prospectBackgroundForm.reset()
    }
  }
  @Output() getFormStatusandData = new EventEmitter<object>()

  constructor(
    private prospectBackgroundService: ProspectBackgroundService,
    private enquiryService: EnquiryService,
    private prospectBackgroundContainerService: ProspectBackgroundContainerService,
    private enquiryCommonService: EnquiryCommonService,
    private enqRt: ActivatedRoute
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.loadAllDropDownData().subscribe(dt => {
      this.dropDownOccupation = dt[0] as BaseDto<Array<DropDownOccupation>>
      this.dropDownSoilType = dt[1] as BaseDto<Array<DropDownSoilType>>
      this.dropDownMajorCropGrown = dt[2] as BaseDto<Array<DropDownMajorCropGrown>>
      this.patchOrCreate()
    })
  }

  selectionoccupation(value) {
    console.log('other => ', value);
    if (value === 'Other') {
      this.showotherinput = true
    } else {
      this.showotherinput = false
    }
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
    this.prospectBackgroundForm = this.prospectBackgroundService.createprospectBackgroundForm()
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.getFormStatusandData.emit({ validStatus: this.prospectBackgroundForm.valid, formData: this.prospectBackgroundForm.value });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectBackgroundForm.reset();
      }
    })
  }

  keyPress(event: any) {
    this.prospectBackgroundService.keyPress(event)
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    console.log("domain>>>> ", domain);
    if (domain.occupation === 'Other') {
      this.showotherinput = true
    }
    if (domain) {

      console.log('occupation', domain.occupation);
      this.dropDownOccupation.result.findIndex(res => res.occupation === domain.occupation)
      this.prospectBackgroundForm.controls.occupation.patchValue(domain.occupation)
      this.prospectBackgroundForm.controls.landSize.patchValue(domain.landSize)
      console.log('master Data', this.dropDownSoilType);
      console.log('soliType', domain.enquirySoilTypes);

      domain.enquirySoilTypes.forEach(enSlTyp => {
        this.dropDownSoilType.result.forEach(slTyp => {
          if (slTyp.soilType === enSlTyp.soilType) {
            slTyp.id = enSlTyp.id
          }
        })
      })
      console.log('merged master Data', this.dropDownSoilType);

      let selectedSoilType = [];
      domain.enquirySoilTypes.forEach(type => {
        selectedSoilType.push(this.dropDownSoilType.result[this.dropDownSoilType.result.findIndex(res => res.soilType === type.soilType)]);
      })
      this.prospectBackgroundForm.controls.soilName.setValue(selectedSoilType);

      domain.enquiryCropGrows.forEach(enqCrop => {
        this.dropDownMajorCropGrown.result.forEach(crop => {
          if (crop.cropGrown === enqCrop.cropGrown) {
            crop.id = enqCrop.id
          }
        })
      })

      let selectedCropGrown = [];
      domain.enquiryCropGrows.forEach(type => {
        selectedCropGrown.push(this.dropDownMajorCropGrown.result[this.dropDownMajorCropGrown.result.findIndex(res => res.cropGrown === type.cropGrown)]);
      })
      this.prospectBackgroundForm.controls.cropName.setValue(selectedCropGrown);
    }
  }

  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    if (domain) {

    }
  }
  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enqNo']))
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        this.getFormStatusandData.emit({ validStatus: this.prospectBackgroundForm.valid, formData: this.prospectBackgroundForm.getRawValue() });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectBackgroundForm.reset();
      }
    })
  }
  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewMobileEnqNo(prms['mobenqNo']))
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.getFormStatusandData.emit({ validStatus: this.prospectBackgroundForm.valid, formData: this.prospectBackgroundForm.value });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.prospectBackgroundForm.reset();
      }
    })
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
      this.prospectBackgroundForm = this.prospectBackgroundService.viewprospectBackgroundForm()
    } else if (this.isViewMobile) {
      this.prospectBackgroundForm = this.prospectBackgroundService.viewMobileprospectBackgroundForm()
    } else {
      this.prospectBackgroundForm = this.prospectBackgroundService.createprospectBackgroundForm()
    }
  }

  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.prospectBackgroundContainerService.dropdownoccupation())
    dropDownTask.push(this.prospectBackgroundContainerService.dropdownsoilType())
    dropDownTask.push(this.prospectBackgroundContainerService.dropdownmajorCropGrown())

    return forkJoin(...dropDownTask)
  }
  private markFormAsTouched() {
    for (const key in this.prospectBackgroundForm.controls) {
      if (this.prospectBackgroundForm.controls.hasOwnProperty(key)) {
        this.prospectBackgroundForm.controls[key].markAsTouched();
      }
    }
  }

}
