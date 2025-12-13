import { Component, OnInit, Input } from '@angular/core';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { AutoActivityNo, ActivityEffectiveness } from '../../domain/service-activity-report.domain';
import { ServiceActivityReportCommonWebService } from '../../service/service-activity-report-common-web.service';
import { ServiceActivityReportCreatePagePresenter } from '../service-activity-report-create-page/service-activity-report-create-page.presenter';
import { ServiceActivityReportDetailsWebService } from './service-activity-report-details-web.service';
import { Operation } from '../../../../../utils/operation-util';

@Component({
  selector: 'app-service-activity-report-details',
  templateUrl: './service-activity-report-details.component.html',
  styleUrls: ['./service-activity-report-details.component.scss'],
  providers: [
    ServiceActivityReportDetailsWebService
  ]
})
export class ServiceActivityReportDetailsComponent implements OnInit {

  @Input() activityReportDetailsForm: FormGroup;
  activityEffectiveness: ActivityEffectiveness
  activityNoList$: Observable<Array<AutoActivityNo>>
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  isView:boolean=false;
  isCreate:boolean=false

  constructor(
    private serviceActivityReportCommonWebService: ServiceActivityReportCommonWebService,
    private serviceActivityReportCreatePagePresenter: ServiceActivityReportCreatePagePresenter,
    private serviceActivityReportDetailsWebService: ServiceActivityReportDetailsWebService
  ) { }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate() + 1);
    this.getActivityNoList()
    this.getSystemDate()
    this.loadActivityEffectivenessDropDown()
    this.viewOrEditOrCreate();
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityReportCreatePagePresenter.operation === Operation.VIEW) {
      this.isView = true
  
    } else if (this.serviceActivityReportCreatePagePresenter.operation === Operation.CREATE) {
      this.isCreate = false
    }
  }
  getSystemDate() {
    if (this.serviceActivityReportCreatePagePresenter.operation === Operation.CREATE) {
      this.serviceActivityReportDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const activityReportDate = response['result']
        this.activityReportDetailsForm.get('activityReportDate').patchValue(new Date(activityReportDate))
      })
    }
  }

  loadActivityEffectivenessDropDown() {
    this.serviceActivityReportCommonWebService.getActivityEffectiveness().subscribe(response => {
      this.activityEffectiveness = response
    })
  }

  private getActivityNoList() {
    this.activityReportDetailsForm.get('activityNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const activityNo = typeof valueChange == 'object' ? valueChange.activityNumber : valueChange
        this.autoActivityNo(activityNo)
      }
      if (valueChange !== null) {
        this.serviceActivityReportCreatePagePresenter.setErrorForActivityNo()
      }
    })
  }
  autoActivityNo(activityNo: string) {
    this.activityNoList$ = this.serviceActivityReportDetailsWebService.autoCompleteActivityNo(activityNo)
  }

  selectedActivityNo(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.activityReportDetailsForm.get('activityNo').setErrors(null);
    }
    this.serviceActivityReportDetailsWebService.getHeaderDetails(event.option.value.id).subscribe(response => {
      
      this.serviceActivityReportCreatePagePresenter.patchValueForActivityNo(response)
    })

  }

  displayFnActivityNo(value: AutoActivityNo) {
    return value ? value.activityNumber : undefined
  }

  onKeyDownActivityNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
    this.serviceActivityReportCreatePagePresenter.resetForActivityNumber()
    
    }
  }

  compareFnActivityEffectiveness(c1: ActivityEffectiveness, c2: ActivityEffectiveness): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.effectiveness === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.effectiveness;
    }
    return c1 && c2 ? c1.effectiveness === c2.effectiveness : c1 === c2;
  }

}
