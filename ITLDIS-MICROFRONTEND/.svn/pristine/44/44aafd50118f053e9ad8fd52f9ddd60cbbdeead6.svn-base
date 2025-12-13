import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ProspectBackgroundContainerService } from './prospect-background-container.service';
import { DropDownOccupation, DropDownSoilType, DropDownMajorCropGrown } from 'ProspectBackground';
import { BaseDto } from 'BaseDto';
import { ProspectDetailsObj } from 'ProspectDetails';

@Component({
  selector: 'app-prospect-background-container',
  templateUrl: './prospect-background-container.component.html',
  styleUrls: ['./prospect-background-container.component.scss'],
  providers: [ProspectBackgroundContainerService]
})
export class ProspectBackgroundContainerComponent implements OnInit {
  dropDownOccupation: BaseDto<Array<DropDownOccupation>>
  dropDownSoilType: BaseDto<Array<DropDownSoilType>>
  dropDownMajorCropGrown: BaseDto<Array<DropDownMajorCropGrown>>
  @Input() dataAutoPopulateByMobileNumber: ProspectDetailsObj
  @Input() keyDownMobileNo: string
  @Output() submitProsprctBackgroundForm = new EventEmitter<object>()

  constructor(
    private prospectBackgroundContainerService: ProspectBackgroundContainerService
  ) { }

  ngOnInit() {
    this.dropdownOccupation()
    this.dropdownSoilType()
    this.dropdownMajorCropGrown()
  }

  dropdownOccupation() {
    this.prospectBackgroundContainerService.dropdownoccupation().subscribe(response => {
      // console.log('occupations =>', response);
      this.dropDownOccupation = response as BaseDto<Array<DropDownOccupation>>
    })
  }

  dropdownSoilType() {
    this.prospectBackgroundContainerService.dropdownsoilType().subscribe(response => {
      // console.log('soils =>', response)
      this.dropDownSoilType = response as BaseDto<Array<DropDownSoilType>>
      // console.log(this.dropDownSoilType.result[0]);

    })
  }

  dropdownMajorCropGrown() {
    this.prospectBackgroundContainerService.dropdownmajorCropGrown().subscribe(response => {
      // console.log('growns =>', response);
      this.dropDownMajorCropGrown = response as BaseDto<Array<DropDownMajorCropGrown>>

    })
  }
  getFormStatusAndData(event) {
    // console.log('background', event);
    this.submitProsprctBackgroundForm.emit({ form: 'Prospect Background', event: event })

  }

}