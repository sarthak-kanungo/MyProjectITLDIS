import { Component, Input, OnInit } from '@angular/core';
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter';
import { FormGroup, AbstractControlOptions } from '@angular/forms';
import { TableConfig } from 'editable-table';
import { CustomerConcernWebService } from './customer-concern-web.service';
import { ServiceRepresentative } from '../../domain/job-card-domain';
@Component({
  selector: 'app-customer-concern',
  templateUrl: './customer-concern.component.html',
  styleUrls: ['./customer-concern.component.scss'],
  viewProviders: [CustomerConcernWebService]
})
export class CustomerConcernComponent implements OnInit {

  customerConcernForm: FormGroup
  serviceRepresentives: ServiceRepresentative
  dateInValue: Date;

  @Input()
  isView:boolean
  @Input()
  isEdit:boolean
  @Input()
  isCreate:boolean

  constructor(
    private customerConcernWebService: CustomerConcernWebService,
    private presenter: JobCardPresenter,
  ) { }

  ngOnInit() { 
    this.customerConcernForm = this.presenter.customerConcern
    this.patchOrCreate()

    this.serviceRepresentativeDropDown()
  }
  serviceRepresentativeDropDown() {
    this.customerConcernWebService.dropDownService().subscribe(res => {
      this.serviceRepresentives = res
    })
  }
  patchOrCreate() {
   
    this.presenter.jobCardBasicInfo.get('date').valueChanges.subscribe(
      res => {
        const dateForValidation = res
        this.dateInValue = new Date(dateForValidation)
      })
  }

  serviceRepresentativeCompare(c1: ServiceRepresentative, c2: ServiceRepresentative): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.name === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.name;
    }
    return c1 && c2 ? c1.name === c2.name : c1 === c2;
  }
}
