import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, Validators } from '@angular/forms';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { ToastrService } from 'ngx-toastr';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-service-feedback',
  templateUrl: './post-service-feedback.component.html',
  styleUrls: ['./post-service-feedback.component.css']
})
export class PostServiceFeedbackComponent implements OnInit {
 postServiceFeedBackForm:FormArray
@Output() startRatingData = new EventEmitter<any>();
@Input() showAllServiceFeedbackForm:any
@Input() viewPostServicefeedback:any
@Input() serviceFeedBackDetails:any
@Input() callHistoryData:any
@Input() typeOfCallId:any
@Input() serviceJobCardId:any
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
  starRating: any;
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  
  constructor(
    private service:DelearCustomerCareExCallService,
    private toaster:ToastrService,
    private presenter:DelearCustomerCareExCallPagePresenter,
    private activatedRoute:ActivatedRoute
    ) { 
    
  }

  ngOnInit() {
   this.getQuestionary()
   this.postServiceFeedBackForm=this.presenter.postServiceFeedBackForm
   this.checkOperation();
   

  }
  ngOnChanges() {
   this.callHistoryData
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
        this.presenter.addRowForService(res);
        }
      })
    })
  }

}
