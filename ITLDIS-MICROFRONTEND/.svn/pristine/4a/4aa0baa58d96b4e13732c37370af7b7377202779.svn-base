import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { UserBranch } from '../../domain/assign-user-to-branch-domain';
import { CreateNewAssignUserToBranchPagePresenter } from '../create-new-assign-user-to-branch/create-new-assign-user-to-branch-page.presenter';
import { CreateNewAssignUserToBranchService } from '../create-new-assign-user-to-branch/create-new-assign-user-to-branch.service';

@Component({
  selector: 'app-branch-accessible',
  templateUrl: './branch-accessible.component.html',
  styleUrls: ['./branch-accessible.component.scss'],
  providers:[CreateNewAssignUserToBranchPagePresenter]
})
export class BranchAccessibleComponent implements OnInit, OnChanges{

  @Input() branchAccessible:FormGroup
  @Input() dealerId:number
  @Input()userId
  @Input()userBranchListView
  userBranchList:Array<UserBranch>
  branchAccessibleForm:FormArray
  @Output() sendBranchId  = new EventEmitter();
  constructor( private assignUserToBranchService:CreateNewAssignUserToBranchService,
    private pagePresenter:CreateNewAssignUserToBranchPagePresenter,
    private activityRoute: ActivatedRoute,
    ) { }

  ngOnInit() {
    this.branchAccessibleForm=this.pagePresenter.branchAccessible;
    this.pagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    
  }

  
  ngOnChanges(){
    if (this.pagePresenter.operation === Operation.CREATE) {
      //this.isView=true
    }
    this.getBranchForUser()
    this.userBranchList=this.userBranchListView
    this.getAccessibletableValue()
    
  }

  getBranchForUser(){
    if (this.dealerId && this.userId) {
      this.assignUserToBranchService.getUserBranch(this.dealerId).subscribe(res=>{
        this.userBranchList=res
        this.sendBranchId.emit(this.userBranchList[0].id+"");
        this.getAccessibletableValue()
      })
    }
  }
  // onChange(event:MatCheckboxChange){
  //   let checkBox= event.checked
  //   console.log('checkBox--',checkBox);
    
  //   console.log('isactive--',this.branchAccessible.get('isactive').value);
  // }

  getAccessibletableValue(){
    if (this.userBranchList) {
      this.userBranchList.forEach(element => {
        if (element.Active_status==='Y') {
            element.Active_status=true
        }
        if (element.IsmainBranch==='Y') {
          element.IsmainBranch=true
        }
        
        this.pagePresenter.addRow(element);
        
  
      });
    }
  }

}
