import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { recieptPresenter } from '../branch-transfer-reciept-page/branch-transfer-reciept-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { BranchTransferRecieptService } from '../../service/branch-transfer-reciept.service';
import { searchIssueNo } from '../branch-transfer-reciept-page/branch-transfer-reciept-create-domain';

@Component({
  selector: 'app-create-branch-transfer-reciept',
  templateUrl: './create-branch-transfer-reciept.component.html',
  styleUrls: ['./create-branch-transfer-reciept.component.css']
})
export class CreateBranchTransferRecieptComponent implements OnInit {
   @Input() branchTransferRecieptForm:FormGroup
   @Output() totalValue= new EventEmitter();
    totalSum:number=0;
   isView:any;
   isEdit:any;
  recieptData: any;
  issueId: any;
  issueList: any;
  itemDetailsList: any;

  constructor(
    private presenter:recieptPresenter,
    private activatedRoute:ActivatedRoute,
    private service:BranchTransferRecieptService,
    ) { }

  ngOnInit() {


    this.checkFormOperation()
    this.formValueChange()
    // this.getIssueBranch()
    // this.getRecieptNo();
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      
      
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      
    }

  }
  selectionChange(event:any){

  }
  private formValueChange(){
    this.branchTransferRecieptForm.get('transferIssue').valueChanges.subscribe(data=>{
      if(data){
        this.getissueNo(data);
      }
    })
  }
  private getissueNo(txt){
      this.service.autoGenerateRecieptNo(txt).subscribe(res=>{
        if(res){
          this.recieptData=res;
          // this.getIssueBranch()
        }
      })
  }

  issueNoSelected(event:any){
   
     this.issueId=event.option.value.id
     this.getIssueBranch()
    
      
      this.presenter.itemDetailsArray.clear();
     this.presenter.itemDetailsGroup.next(null); 
     this.totalSum=0
     this.getItemDetails(event.option.value.id);

     
  }

  private getItemDetails(issueId: number){
    this.service.getRecieptDetails(`${issueId}`).subscribe(data=>{
      this.itemDetailsList=data;
      if(this.itemDetailsList){
        let totSum: number[] = [];
        let i = 0;
        this.itemDetailsList.forEach(itemData => {
             this.presenter.addRow(itemData)
             let num = 0
          const data = num + itemData.receiptMrpValue
          totSum[i] = data;
          i++;
        })
        let sum = 0;
        for (let j = 0; j < totSum.length; j++) {
          sum += totSum[j];

        }
         this.totalSum=sum
         this.totalValue.emit(this.totalSum)
          // console.log(this.totalSum,'sss')
        
        
      }
    
     })
  }

  private getIssueBranch(){
    this.service.getIssueingBranchName(this.issueId).subscribe(res=>{
      if(res){
        this.issueList=res;
        this.branchTransferRecieptForm.get('issueBranch').patchValue(this.issueList.branchName)
        this.branchTransferRecieptForm.get('issuingBranchId').patchValue(this.issueList.id)
      }
    })
  }

  displayFn(obj: searchIssueNo) {
    
    if (obj && typeof obj === 'string') {
     
      return obj;
    }

    return obj ? obj.issueNo : undefined;
  }
  
}
