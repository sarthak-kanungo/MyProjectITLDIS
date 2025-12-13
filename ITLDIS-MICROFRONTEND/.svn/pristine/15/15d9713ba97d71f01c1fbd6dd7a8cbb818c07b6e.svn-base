import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { Occupation, SoilType, MajorCropGrown, ViewEnquiry, SaveEnquiry } from '../../domains/enquiry';
import { ProspectBackgroundV2WebService } from '../../services/prospect-background-v2-web.service';
import { ActivatedRoute } from '@angular/router';
import { KubotaPatterns } from '../../../../../utils/kubota-patterns';

@Component({
  selector: 'app-prospect-background-v2',
  templateUrl: './prospect-background-v2.component.html',
  styleUrls: ['./prospect-background-v2.component.scss'],
  providers: [ProspectBackgroundV2WebService]
})
export class ProspectBackgroundV2Component implements OnInit, OnChanges {

  prospectBackgroundForm: FormGroup
  occupation: Array<Occupation>
  soilType: Array<SoilType> = []
  majorCropGrown : Array<MajorCropGrown> = []
  isShowoByOccupation: boolean
  @Input() enquiryByEnquiryNumber: ViewEnquiry
  @Input() set autoPopulateByMobileNo (value: SaveEnquiry){
    if(value){
      this.prospectBackgroundForm.patchValue(value)
      this.prospectBackgroundForm.get('occupation').patchValue({ occupation: value.occupation })
      let selectedSoilType = [];
      value.prospectSoilTypes.forEach(type => {
        selectedSoilType.push(this.soilType[this.soilType.findIndex(res => res.soilType === type.soilName)]);
      })
      this.prospectBackgroundForm.get('soilName').setValue(selectedSoilType);
      let selectedCropGrown = [];
      value.prospectCropNames.forEach(type => {
        selectedCropGrown.push(this.majorCropGrown[this.majorCropGrown.findIndex(res => res.cropGrown === type.cropName)]);
      })
      this.prospectBackgroundForm.get('cropName').setValue(selectedCropGrown);
    }
  }
  constructor(
    private enquiryPresenter: EnquiryPresenter,
    private prospectBackgroundV2WebService: ProspectBackgroundV2WebService,
    private actRt: ActivatedRoute,
    private kubotaPatterns: KubotaPatterns,
  ) { }

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {
      this.prospectBackgroundForm.patchValue(this.enquiryByEnquiryNumber)
      this.prospectBackgroundForm.get('occupation').patchValue(this.enquiryByEnquiryNumber.occupation ?{occupation : this.enquiryByEnquiryNumber.occupation} : null)
      if(this.enquiryByEnquiryNumber.occupation === 'Other'){
        this.isShowoByOccupation = true
      }
      this.prospectBackgroundForm.get('soilName').setValue(this.enquiryByEnquiryNumber.enquirySoilTypes);
      this.prospectBackgroundForm.get('cropName').setValue( this.enquiryByEnquiryNumber.enquiryCropGrows);
    }
  }

  ngOnInit() {
    this.patchOrCreate()
    this.loadAllDropDown()
  }

  private patchOrCreate() {
    this.prospectBackgroundForm = this.enquiryPresenter.enquiryForm.get('prospectBackground') as FormGroup
  }

  loadAllDropDown() {
    this.prospectBackgroundV2WebService.getOccupation().subscribe(response => {
      this.occupation = response.result as Array<Occupation>
    })
    this.prospectBackgroundV2WebService.getSoilType().subscribe(response => {
      this.soilType = response.result as Array<SoilType>
    })
     this.prospectBackgroundV2WebService.getMajorCropGrown().subscribe(response => {
      this.majorCropGrown = response.result as Array<MajorCropGrown>
    })
  }

  selectionoccupation(value) {
    if (value.occupation === 'Other') {
      this.isShowoByOccupation = true
    } else {
      this.isShowoByOccupation = false
    }
  }

  compareFnOccupation(c1: Occupation, c2: SaveEnquiry | ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.occupation === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.occupation;
    }
    return c1 && c2 ? c1.occupation === c2.occupation : c1 === c2;
  }

  compareFnSoilType(c1: SoilType, c2: SoilType): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.soilType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.soilType;
    }
    return c1 && c2 ? c1.soilType === c2.soilType : c1 === c2;
  }

  compareFnCropGrown(c1: MajorCropGrown, c2: MajorCropGrown): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.cropGrown === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.cropGrown;
    }
    return c1 && c2 ? c1.cropGrown === c2.cropGrown : c1 === c2;
  }

  onKeyPressAlphaNumericsOnly(event: KeyboardEvent) {
    this.kubotaPatterns.allowAlphaNumericsOnly(event)
  }

}