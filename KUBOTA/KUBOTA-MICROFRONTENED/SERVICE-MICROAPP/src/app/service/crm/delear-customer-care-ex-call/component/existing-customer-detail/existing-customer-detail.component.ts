import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';

@Component({
  selector: 'app-existing-customer-detail',
  templateUrl: './existing-customer-detail.component.html',
  styleUrls: ['./existing-customer-detail.component.scss']
})
export class ExistingCustomerDetailComponent implements OnInit {
 
  @Input()  showSalesFormData:any
  @Input() customerDetailsForm:FormGroup
  @Input() isView:any
  @Input() viewPostSalesfeedback:any
  @Input() showAllServiceFeedbackForm:any
  @Input() viewPostServicefeedback:any
  constructor(private directSurveyPagePresenter: DelearCustomerCareExCallPagePresenter,
    private service: DelearCustomerCareExCallService,
    private activityRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

}
