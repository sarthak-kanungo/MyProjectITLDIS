import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { issuePresenter } from '../branch-transfer-issue-page/branch-transfer-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { BranchTransferIssueServiceService } from '../../service/branch-transfer-issue-service.service';
import { ToastrService } from 'ngx-toastr';
import { IDropdownSettings } from 'ng-multiselect-dropdown';


@Component({
  selector: 'app-create-branch-transfer-issue',
  templateUrl: './create-branch-transfer-issue.component.html',
  styleUrls: ['./create-branch-transfer-issue.component.css']
})
export class CreateBranchTransferIssueComponent implements OnInit {
  dropdownSettings:IDropdownSettings;
  @Input() branchTransferIssueForm: FormGroup
  @Output() issueToBranchId=new EventEmitter()
  isView: any;
  isEdit: any
  issueToList: any
  issueBranchList: any
  requestNoList: [];
  requestToBranchId: any
  newrequestNoList: any
  checkBoxId: any
  itemDetailsList:any
 
  dropdownList = [];
  selectedItems = [];
  isCreate:any;
  isDisabled = false;
  id:any
  // dropdownSettings = {};
  constructor(private presenter: issuePresenter,
    private activatedRoute: ActivatedRoute,
    private service: BranchTransferIssueServiceService,
    private toaster: ToastrService,
  ) { }

  ngOnInit() {
   
    this.branchTransferIssueForm.get('reqNo').disable()
    this.dropdownSettings = {
      
      idField: 'id',
      textField: 'reqNo',
     
      allowSearchFilter: true,
      enableCheckAll: false,
      closeDropDownOnSelection:true
   }
    // this.dropdownSettings:IDropdownSettings = {
    //   singleSelection: false,
    //   idField: 'item_id',
    //   textField: 'item_text',
    //   selectAllText: 'Select All',
    //   unSelectAllText: 'UnSelect All',
    //   itemsShowLimit: 3,
    //   allowSearchFilter: true
    // };
   
    this.checkForOperation()
    this.issueToBranch()
    this.getRequestNo()
    // this.assignToBranch()
  }


  private checkForOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.branchTransferIssueForm.disable();


    } else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true;
      // this.branchTransferIndentForm.disable();


    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true;
      // this.branchTransferIndentForm.disable();


    }
  }
  private issueToBranch() {
    
   
    this.service.getIssueToBranch().subscribe(issueData => {
      if (issueData) {
        this.issueToList = issueData['result']
       
       
      }
    })
  }
  getissueToBranchEdit(event){
    
    for(let i=0;i<this.issueToList.length ;i++){

      if(this.issueToList[i].branchName==event.value){
       
      const data=this.issueToList[i].id
          this.issueToBranchId.emit(data)

      }
    }
  }
  getIssueToData(event: any) {
    this.requestToBranchId = event.value.id;
    console.log(this.requestToBranchId,'requestToBranchId')
    if(this.requestToBranchId){
     this.branchTransferIssueForm.get('reqNo').enable()
    }
  }
  disabledCheckBox:boolean=false
  selectedValues: string[] = [];
  getRequestId(event: any) {
    
    let selectedItems = [];
    // for (let i = 0; i < event.reqNo.length; i++) {
    //   if (event.reqNo[i].id) {
    //     selectedItems.push(event.reqNo[i].id);
    //   }
    // }
    selectedItems.push(event.id)
    let commaSeparatedValues = selectedItems.join(',');
    

    this.service.getIndentItemsDetailsByIds(commaSeparatedValues).subscribe(res =>{
      this.itemDetailsList = res
      if(this.itemDetailsList){
        
        this.itemDetailsList.forEach(itemData => {
          
             this.presenter.addRow(itemData)
        })
     }
   
    })
    
    let data=this.newrequestNoList;
    
    
     let selectedItem=data.filter(item=>item.id==event.id)
     
     selectedItem.forEach(ele=>{
      this.id=ele.id
     })
    this.newrequestNoList=data.map(item=>{
      if(item.id==this.id){
        item.isDisabled=true
      }
      else{
        item.isDisabled=false
      }
      return item
    })
   
  }
  private getRequestNo() {

    this.service.getIndentNos(this.requestToBranchId).subscribe(requestData => {
      this.requestNoList = requestData
      this.newrequestNoList = this.requestNoList.slice()
    })
  }
 
}
