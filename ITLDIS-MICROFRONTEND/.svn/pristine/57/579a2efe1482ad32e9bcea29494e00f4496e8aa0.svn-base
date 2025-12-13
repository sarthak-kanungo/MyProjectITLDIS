import { Component, OnInit, Input } from '@angular/core';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { ServiceActivityClaimPagePresenter } from '../service-activity-claim-page/service-activity-claim-page-presenter';
import { Observable } from 'rxjs';
import { AutoCompActivityNo } from '../../domain/service-activity-claim.domain';
import { Operation } from '../../../../../utils/operation-util';
import { ServiceActivityClaimDetailsWebService } from './service-activity-claim-details-web.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';

@Component({
  selector: 'app-service-activity-claim-details',
  templateUrl: './service-activity-claim-details.component.html',
  styleUrls: ['./service-activity-claim-details.component.scss'],
  providers: [
    ServiceActivityClaimDetailsWebService
  ]
})
export class ServiceActivityClaimDetailsComponent implements OnInit {

  isView: boolean;
  @Input() activityClaimDetailsForm: FormGroup
  activityNoList$: Observable<Array<AutoCompActivityNo>>

  constructor(
    private serviceActivityClaimPagePresenter: ServiceActivityClaimPagePresenter,
    private serviceActivityClaimDetailsWebService: ServiceActivityClaimDetailsWebService,
    private loginFormService : LoginFormService
  ) { }

  ngOnInit() {
    this.viewOrEditOrCreate()
    this.getSystemDate()
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }else if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.getActivityNoList()
    }
  }

  getSystemDate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.serviceActivityClaimDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const activityClaimDate = response['result']
        this.activityClaimDetailsForm.get('activityClaimDate').patchValue(new Date(activityClaimDate))
      })
    }
  }

  private getActivityNoList() {
    this.activityClaimDetailsForm.get('activityNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.activityNumber : valueChange
        this.autoActivityNo(activityNo)
      }
      if (valueChange !== null) {
        this.serviceActivityClaimPagePresenter.setErrorForActivityNo()
      }
    })
  }
  autoActivityNo(activityNo: string) {
    const loginUserId = this.loginFormService.getLoginUserId()
    this.activityNoList$ = this.serviceActivityClaimDetailsWebService.autoCompleteActivityNo(activityNo, loginUserId)
  }

  selectedActivityNo(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.activityClaimDetailsForm.get('activityNo').setErrors(null);
    }
    this.serviceActivityClaimDetailsWebService.getHeaderDataForActivityClaim(event.option.value.id).subscribe(response => {
      this.serviceActivityClaimPagePresenter.patchValueForActivityNo(response)
    })
  }

  onKeyDownActivityNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
    this.serviceActivityClaimPagePresenter.resetForActivityNumber()
    
    }
  }

  displayFnActivityNo(value: AutoCompActivityNo) {
    return value ? value.activityNumber : undefined
  }

}
