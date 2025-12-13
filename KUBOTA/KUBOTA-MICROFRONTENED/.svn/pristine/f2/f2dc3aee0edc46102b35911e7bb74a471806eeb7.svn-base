import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { AutoDealer } from 'src/app/master/dealer-masters/assign-user-to-branch/domain/assign-user-to-branch-domain';
import { AutoDealerDetails, DealerMasterDropdown } from 'src/app/master/dealer-masters/dealer-employee-master/domain/dealer-employee-domain';
import { DealerDetailsForNewUser } from 'src/app/master/dealer-masters/dealer-user/domain/create-dealer-user-domain';
import { DealerDetails } from '../../domain/create-assign-org-hierarchy-to-dealer-domain';
import { CreateNewAssignOrgHierarchyToDealerService } from '../create-new-assign-org-hierarchy-to-dealer/create-new-assign-org-hierarchy-to-dealer.service';

@Component({
  selector: 'app-assign-org-hierarchy-to-dealer-search-result',
  templateUrl: './assign-org-hierarchy-to-dealer-search-result.component.html',
  styleUrls: ['./assign-org-hierarchy-to-dealer-search-result.component.scss'],
  providers: [CreateNewAssignOrgHierarchyToDealerService]
})
export class AssignOrgHierarchyToDealerSearchResultComponent implements OnInit {

  @Input() assignOrgHierarchyToDealerForm: FormGroup;
  dealerCodeList: DealerMasterDropdown[] = []
  dealerList$: DealerDetails[] = []

  constructor(
    private createNewAssignOrgHierarchyToDealerService: CreateNewAssignOrgHierarchyToDealerService

  ) { }

  ngOnInit() {
    this.formValueChanges();
  }

  formValueChanges() {
    this.assignOrgHierarchyToDealerForm.get('dealerId').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        console.log("dealer---->", value)
        let dealerCode = typeof value == "object" ? value.code : value;
        this.autoDealerCode(dealerCode);
      }
      // if (value !== null && typeof value !== "string") {
      //   this.assignOrgHierarchyToDealerForm.get("dealerId").setErrors({
      //     selectFromList: "Please select from list",
      //   });
      // }
    });
  }


  autoDealerCode(autoDealerCode) {
    if (autoDealerCode) {
      this.createNewAssignOrgHierarchyToDealerService.dealerAuto(autoDealerCode).subscribe(res => {
        this.dealerList$ = res
      })
    }

  }

  displayFnForDealerCode(selectedOption: DealerDetails) {
        if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['displayString'] : undefined;
  }
}
