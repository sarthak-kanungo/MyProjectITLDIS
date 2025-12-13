import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { BranchIndentServiceService } from '../../service/branch-indent-service.service';
import { indentPresenter } from '../branch-transfer-page/branch-indent-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-branch-indent',
  templateUrl: './create-branch-indent.component.html',
  styleUrls: ['./create-branch-indent.component.css'],
  
})
export class CreateBranchIndentComponent implements OnInit {
  @Input() branchTransferIndentForm:FormGroup
   @Output() branchId = new EventEmitter()
   @Output() editBranchId=new EventEmitter()
  loginUser:any
  date: string;
  isView:boolean=false
  isEdit:boolean=false
  subBranchData:any
  branchIDs:any
  isCreate:any;
  
  constructor(
    private localStorage:LocalStorageService,
    private service:BranchIndentServiceService,
    private presenter:indentPresenter,
    private activatedRoute:ActivatedRoute,
    private toaster:ToastrService,
    ) { }

  ngOnInit() {
     
   this.checkFormOperation()
  
   this.getSubBranch()
  }
  selectionChange(event:any){
    const data=event.value.id
     this.branchId.emit(data)
    this.branchIDs=data
  }
  select(event:any){
    
    
    for(let i=0;i<this.subBranchData.length ;i++){

      if(this.subBranchData[i].branchName==event.value){
       
      const data=this.subBranchData[i].id
        this.editBranchId.emit(data)

      }
    }
  }
  private getSubBranch(){
    this.service.getSubBranch().subscribe(branch=>{
      this.subBranchData=branch['result']
    })
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.branchTransferIndentForm.disable();
      
   
    }else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true;
      // this.branchTransferIndentForm.disable();

   
    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true;
      // this.branchTransferIndentForm.disable();

   
    }
  }
  
  

}
