import { Component, Input, OnInit } from '@angular/core';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { AutoDealer, UserId } from '../../domain/assign-user-to-branch-domain';
import { CreateNewAssignUserToBranchService } from '../create-new-assign-user-to-branch/create-new-assign-user-to-branch.service';

@Component({
  selector: 'app-assign-user-to-branch-search-result',
  templateUrl: './assign-user-to-branch-search-result.component.html',
  styleUrls: ['./assign-user-to-branch-search-result.component.scss']
})
export class AssignUserToBranchSearchResultComponent implements OnInit {

  @Input() userToBranchForm
  dealershipList:AutoDealer
  userIdList:UserId
  dealerId:any
  dealerName:string
  userId:string

  constructor(private assignUserToBranchService:CreateNewAssignUserToBranchService) { }

  ngOnInit() {
    this.userToBranchForm.get('userId')
  }

  getAutodealership(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.assignUserToBranchService.dealerToAssignBranch(value).subscribe(res=>{
        this.dealershipList=res
      })
    }
  }

  displayFndealership(code: AutoDealer) {
    return code ? code.code : undefined;
  }

  dealershipSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.userToBranchForm.get('dealership').setErrors(null);
    }
    this.dealerId= this.userToBranchForm.get('dealership').value.id
    this.userToBranchForm.get('userId').enable();
  }


  getAutoUserId(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.assignUserToBranchService.userToAssignBranch(value,this.dealerId,'ASSIGNUSER').subscribe(res=>{
        this.userIdList=res
      })
    }
  }

  displayFnUserIdDetailes(employeeName: UserId) {
    return employeeName ? employeeName.employeeName : undefined;
  }

  userIdSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.userToBranchForm.get('userId').setErrors(null);
    }
    this.userId= this.userToBranchForm.get('userId').value.employeeName
  }


}
