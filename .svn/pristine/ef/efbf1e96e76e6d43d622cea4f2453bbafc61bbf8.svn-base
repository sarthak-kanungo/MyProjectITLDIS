import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { ToastrService } from 'ngx-toastr';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { FormArray } from '@angular/forms';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-sales-feedback',
  templateUrl: './post-sales-feedback.component.html',
  styleUrls: ['./post-sales-feedback.component.css']
})
export class PostSalesFeedbackComponent implements OnInit {
  starRating: any;
  postSalesFeedBackForm:FormArray
  @Output() startRatingData = new EventEmitter<any>();
  @Input() showAllServiceFeedbackForm:any
  @Input() salesFeedBakDetails:any;
  @Input() viewPostSalesfeedback:any;
  @Input() showSalesFormData:any
  @Input() viewSalesCallHistory:any
  @Input() typeOfCallId:any
  @Input() salesJobCardId:any
    // questList: any=[];
    // questList :any
    questList:any=[]
    startList = [
      { id: 1, startData: "1" },
      { id: 2, startData: "2" },
      { id: 3, startData: "3" },
      { id: 4, startData: "4" },
      { id: 5, startData: "5" }
    ];
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
    
  constructor(
    private service:DelearCustomerCareExCallService,
    private toaster:ToastrService,
    private presenter:DelearCustomerCareExCallPagePresenter,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    this.getQuestionary()
   this.postSalesFeedBackForm=this.presenter.postSalesFeedBackForm
   this.checkOperation()
  }

  private checkOperation(){
  
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true;
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true;
    } else if(this.presenter.operation === Operation.VIEW){
      this.isView=true
    }
  }

  getStartRating(event){
    this.starRating=event;
    this.startRatingData.emit(this.starRating)
    
  }
 

  private getQuestionary(){
    this.service.getQuesionnaire(this.typeOfCallId).subscribe(res=>{
      this.questList=res['result'];
      this.questList.forEach(res=>{
        if(res){
        this.presenter.addRowForSales(res);
        }
      })
    })
  }

  ngOnChanges(){
    this.viewSalesCallHistory;
  }

}
