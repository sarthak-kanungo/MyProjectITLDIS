import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { DealerMasterDropdown } from 'src/app/master/itldis-masters/dealer-master/domain/dealer-master.domain';
import { PartyCode, PartyName } from '../../domain/party-master-domain';
import { PartyDetailsService } from '../party-details/party-details.service';
import { PartyMasterSearchPagePresenter } from '../party-master-search-page/partyMasterSearchPagePresenter';

@Component({
  selector: 'app-search-party',
  templateUrl: './search-party.component.html',
  styleUrls: ['./search-party.component.scss'],
  providers:[PartyDetailsService]
})
export class SearchPartyComponent implements OnInit {

  @Input()
  partyMasterSearchForm: FormGroup;
  partyCode$: BaseDto<Array<PartyCode>>;
  partyName$: BaseDto<Array<PartyName>>;
  constructor(
    private fb: FormBuilder,
    private partyMasterSearchPagePresenter: PartyMasterSearchPagePresenter,
    private partyDetailsService: PartyDetailsService
  ) { }

  ngOnInit() {
    this.partyMasterSearchForm = this.partyMasterSearchPagePresenter.partySearchDetailsForm
    this.formValueChanges();
  }

  formValueChanges() {
    this.partyMasterSearchForm.get('partyCode').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        console.log("partyCode---->", value)
        let partyCode = typeof value == "object" ? value.partyCode : value;
        this.autoPartyCode(partyCode);
      }
      if (value !== null && typeof value !== "string") {
        this.partyMasterSearchForm.get("partyCode").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });

    this.partyMasterSearchForm.get('partyName').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        console.log("partyName---->", value)
        let partyName = typeof value == "object" ? value.partName : value;
        this.autoPartyName(partyName);
      }
      if (value !== null && typeof value !== "string") {
        this.partyMasterSearchForm.get("partyName").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });

  }
  autoPartyCode(autoPartyCode) {       
    if (autoPartyCode) {
      // this.partyCode$ = this.partyDetailsService.getPartyCode(
      //   autoPartyCode as BaseDto<Array<PartyCode>>
      // );
      this.partyDetailsService.getPartyCode(autoPartyCode).subscribe(response => {
        this.partyCode$  = response as BaseDto<Array<PartyCode>>
      })
  }

}
autoPartyName(autoPartyName) {       
  if (autoPartyName) {
    // this.partyCode$ = this.partyDetailsService.getPartyCode(
    //   autoPartyCode as BaseDto<Array<PartyCode>>
    // );
    this.partyDetailsService.getPartyName(autoPartyName,0).subscribe(response => {
      this.partyName$  = response as BaseDto<Array<PartyName>>
    })
}

}
displayFnForPartyCode(selectedOption: PartyCode) {
  if (!!selectedOption && typeof selectedOption === 'string') {
    return selectedOption;
  }
  return selectedOption ? selectedOption['partyCode'] : undefined;
}
// onKeyDownPartyName(event: KeyboardEvent) {
//   if (event.key === "Backspace" || event.key === "Delete")
//     this.partyMasterPagePresenter.resetProspectDetailsForPartyCode();
// }
displayFnForPartyName(selectedOption: PartyCode) {
  if (!!selectedOption && typeof selectedOption === 'string') {
    return selectedOption;
  }
  return selectedOption ? selectedOption['partyCode'] : undefined;
}
}
