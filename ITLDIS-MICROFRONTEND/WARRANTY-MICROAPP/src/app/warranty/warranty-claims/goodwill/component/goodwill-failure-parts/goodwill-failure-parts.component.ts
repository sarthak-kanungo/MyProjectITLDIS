import { Component, OnInit, Input } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { FailurePart } from '../../../product-concern-report/domain/product-concern-report.domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { FailurePartsWebService } from '../../../product-concern-report/component/failure-parts/failure-parts-web.service';
import { GoodwillFailurePartsWebService } from './goodwill-failure-parts-web.service'
import { PriceType } from '../../domain/goodwill.domain';
import { ActivatedRoute } from '@angular/router';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';
@Component({
  selector: 'app-goodwill-failure-parts',
  templateUrl: './goodwill-failure-parts.component.html',
  styleUrls: ['./goodwill-failure-parts.component.scss'],
  providers: [FailurePartsWebService, GoodwillFailurePartsWebService]
})
export class GoodwillFailurePartsComponent implements OnInit {
  @Input() failurePartsForm: FormArray;
  @Input() machineMasterId: number;
  
  failurePartHeading = ['Sl. No', 'Part No.', 'Description', 'Original Claim Qty', 'Rejected Qty', 'Goodwill Claim Qty', 'Goodwill Claim Accepted Qty','Price Type', 'Accepted%', 'Failure Code', 'Failure Description'];
  selectedForm: FormGroup;
  selectedCode: any;
  failureCode: FailurePart[];
  priceType: PriceType;
  viewName:string
  @Input() isView:boolean
  isKaiUser:boolean = true
  // labourChargeHeading = ['SL. NO', 'JOB CODE(FRS)', 'DESCRIPTION', 'HOURS', 'ORIGINAL CLAIM AMOUNT', 'REJECTED AMOUNT', 'GOODWILL CLAIM AMOUNT', 'GOODWILL CLAIM ACCEPTED AMOUNT', 'ACCEPTED%'];
    // outsideLabourChargeHeading = ['SL. NO', 'DESCRIPTION','ORIGINAL CLAIM AMOUNT', 'REJECTED AMOUNT', 'GOODWILL CLAIM AMOUNT', 'GOODWILL CLAIM ACCEPTED AMOUNT', 'ACCEPTED%'];
  constructor(
    private failurePartsService: FailurePartsWebService,
    private goodwillFailurePartsWebService: GoodwillFailurePartsWebService,
    private activatedRoute: ActivatedRoute,
    private presenter: GoodwillPagePresenter,
  ) { }

  ngOnInit() {
    this.dropDownPriceType();
    this.activeRoute();
  }
  private activeRoute() {
     this.activatedRoute.queryParams.subscribe((qParam) => {
        this.viewName = qParam.name;
     })
     if(this.presenter.loginUser.dealerCode){
         this.isKaiUser = false;
     }
 }
  
  private dropDownPriceType() {
    this.goodwillFailurePartsWebService.dropDownPriceType().subscribe(res => {
      this.priceType = res; 
    }, err => {
      console.log('err', err);
    });
   }

}
