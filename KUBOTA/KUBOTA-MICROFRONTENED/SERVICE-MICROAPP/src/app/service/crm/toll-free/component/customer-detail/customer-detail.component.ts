import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TollFreeService } from '../../service/toll-free.service';
import { MobileNo } from '../../domain/toll-free-domain';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';
@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {
  @Input() customerDetailsForm: FormGroup
  mobileNo$: Observable<Array<MobileNo>>;
  @Output() public customerSelecction = new EventEmitter<any>();
  customerType: any;
  relationType: any; 
  @Input()
  isView:boolean
  constructor(private tollfreePagePresenter: TollFreePagePresenter,
    private service: TollFreeService,
    private activityRoute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.service.getLookupByCode("RELATIONSHIP").subscribe(response => {
      this.relationType = response['result'];
      console.log('relation-type------', this.relationType)
    });
    this.service.getLookupByCode("QA_CUSTOMER_TYPE").subscribe(response => {
      this.customerType = response['result'];
    console.log(this.customerType)

    });
  }
  onClick(value) {
    this.customerSelecction.emit(value);
  }
}