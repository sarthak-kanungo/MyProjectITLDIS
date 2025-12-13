import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { MobileNo } from '../../domain/delear-customer-care-ex-call-domain';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {

  @Input()
  customerDetailsForm: FormGroup
  mobileNo$: Observable<Array<MobileNo>>;
  @Output() public customerSelecction = new EventEmitter<any>();
  customerType : any;
  relationType : any;
  constructor(private delearCustomerCareExCallPagePresenter: DelearCustomerCareExCallPagePresenter,
    private service : DelearCustomerCareExCallService ,
    private activityRoute: ActivatedRoute,) { }

  ngOnInit(): void {
    // this.service.getLookupByCode("RELATIONSHIP").subscribe(response => {
    //   this.relationType = response['result'];
    // });
    // this.service.getLookupByCode("QA_CUSTOMER_TYPE").subscribe(response => {
    //   this.customerType = response['result'];
    // console.log(this.customerType)

    // });
  }
  onClick(value) {
    this.customerSelecction.emit(value);
  }

}