import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ComplaintAssignPopupComponent } from '../../../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
import { ModelSurveyMasterSubmiteDto, SubmitJson } from '../../domain/delear-customer-care-ex-call-domain';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';


@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {
  @Input()
  isEdit: boolean;
  @Input()
  isView: boolean;
  @Input()
  isCreate: boolean;
  @Input()
  complaintDetailsForm: FormArray
  departmentList: any;
  complaintType = ['Complaint', 'Query']


  constructor(private pagePresenter:DelearCustomerCareExCallPagePresenter,
              private service:DelearCustomerCareExCallService,
              private toastr: ToastrService,
              public dialog: MatDialog) { }

  ngOnInit() {
    
    // this.service.getLookupByCode("QA_DEPARTMENT").subscribe(response => {
    //     this.departmentList = response['result'];
    // });
    if(this.isCreate){
      this.pagePresenter.addRowComplaint()
    }
  }
  
  addRow(){
    this.pagePresenter.addRowComplaint();
  }

  deleteRow(index:number){
    this.pagePresenter.deleteRowComplaint(index);
  }
 
  validateDepartment(sfg:FormGroup,ftype){
    let count = 0;
    let flag:boolean = true;
    this.complaintDetailsForm.controls.forEach(fg => {
      if(fg.get('department').value == sfg.get('department').value &&
            fg.get('complaintType').value == sfg.get('complaintType').value ){
        count++;
      }
      if(count>1){
        this.toastr.error('Department/Complaint Type can not be same for multiple complaint');
        fg.get(ftype).reset();
        flag = false;
        return false;
      }
    });
    return flag;
  }
  openComplaintAssignPopup(fg:FormGroup){
    if(this.pagePresenter.machineDetailsForm.get('dealerId').value==undefined || 
            this.pagePresenter.machineDetailsForm.get('dealerId').value == null) {
              this.toastr.error('Select Chassis no');
              this.pagePresenter.machineDetailsForm.get('chassisNo').markAsTouched();
              fg.get('department').reset();
    }else if(this.validateDepartment(fg,'department')){
      fg.get('assignTo').reset();
      fg.get('assignToId').reset();
      const dialogRef = this.dialog.open(ComplaintAssignPopupComponent, {
        width: '90%',
        panelClass: 'confirmation_modal',
        data: {'dealerId':this.pagePresenter.machineDetailsForm.get('dealerId').value, 'department':fg.get('department').value},
        maxHeight: '80vh'
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result && result.data){
          fg.get('assignTo').patchValue(result['data']['name']+" / "+result['data']['designation']);
          fg.get('assignToId').patchValue(result['data']['id']);
        }
      });
    }
  }
}
