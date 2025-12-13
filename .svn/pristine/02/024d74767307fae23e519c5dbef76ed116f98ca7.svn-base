import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ManageApprovalService } from '../manage-approval.service';
import { element } from 'protractor';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Component({
  selector: 'app-create-manage-approval-hierarchy',
  templateUrl: './create-manage-approval-hierarchy.component.html',
  styleUrls: ['./create-manage-approval-hierarchy.component.css']
})
export class CreateManageApprovalHierarchyComponent implements OnInit {
  createApprovalHier: FormGroup;
  details: FormArray;
  transacTionName: string;
  data: any
  tranList: any;
  isFinalApproval: boolean = false;
  createDesignationLevelId: any;
  operation: any;
  isEdit: boolean = false;
  sendStatus: string;
  loading: boolean = false
  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private service: ManageApprovalService,
    private toaster: ToastrService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.createApprovalHier = this.fb.group({
      tranType: [{ value: null, disabled: true }],
      details: new FormArray([])
    })
    this.activeRoute();
    this.checkFormOperation();
  }

  private checkFormOperation() {
    this.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.operation == Operation.EDIT) {
      this.isEdit = true;
      this.activeRoute();
    }
  }
  private activeRoute() {
    this.activatedRoute.params.subscribe((param) => {
      this.transacTionName = atob(param['transactionName']);
      this.viewUpdateData(this.transacTionName);
      this.createApprovalHier.get('tranType').patchValue(this.transacTionName)
    });

  }

  private viewUpdateData(transacTionName) {
    this.service.viewTransType(transacTionName).subscribe(res => {
      if (res) {
        res['result'].forEach(ele => {
          this.addDeatils(ele);
        })

      }
    })
  }

  addDeatils(data) {
    let FG = this.fb.group({
      isSelected: [{ value: null, disabled: false }],
      isSelect: [{ value: null, disabled: false }],
      designationLevel: [{ value: null, disabled: false }],
      approverLevelSeq: [{ value: null, disabled: false }, CustomValidators.numberOnly],
      desigLevelId: [{ value: null, disabled: false }],
      grpSeqNo: [{ value: null, disabled: false }, CustomValidators.numberOnly],
      isFinalApprovalStatus: [{ value: false, disabled: false }],
    })
    FG.controls.designationLevel.patchValue(data.designationLevel);
    if (data.isFinalApprovalStatus === 'Y') {
      FG.controls.isFinalApprovalStatus.patchValue(data.isFinalApprovalStatus);
    }

    FG.controls.approverLevelSeq.patchValue(data.approverLevelSeq);
    FG.controls.grpSeqNo.patchValue(data.grpSeqNo);
    FG.controls.desigLevelId.patchValue(data.desigLevelId);
    (this.createApprovalHier.controls.details as FormArray).push(FG);
  }

  submitApproval() {
    if (this.createApprovalHier.controls.details as FormArray) {
      const a = this.createApprovalHier.controls.details as FormArray;
      if (a.status === "INVALID") {
        // console.log('Invaide')
        this.toaster.error("All Field are required");
        (this.createApprovalHier.controls.details as FormArray).markAllAsTouched()
        return false;
      }

    }
    const approvalArrayList = (this.createApprovalHier.controls.details as FormArray).getRawValue();
    console.log(approvalArrayList, 'approvalArrayList')
    const designationLevelMasterObjects = []; // Declare an array to store additional objects
    approvalArrayList.forEach(res => {
      if (res || res.designationLevel) {
        if (res.designationLevel.desigId != res.desigLevelId) {
          const additionalObject = {
            id: res.designationLevel.desigId ? res.designationLevel.desigId : res.desigLevelId,
          };
          designationLevelMasterObjects.push(additionalObject);
        }else{
          const additionalObject = {
            id: res.designationLevel.desigId ? res.designationLevel.desigId : res.desigLevelId,
          };
          designationLevelMasterObjects.push(additionalObject);
        }

      }
    });

    const updatedApprovalFlowMasters = approvalArrayList.map((item, index) => ({
      ...item,
      designationLevelMaster: designationLevelMasterObjects[index],
      transactionName: this.createApprovalHier.get('tranType').value,
    }

    ));
    updatedApprovalFlowMasters.forEach(ele => {
      if (ele.isFinalApprovalStatus === false || ele.isFinalApprovalStatus == null || ele.isFinalApprovalStatus == '') {
        ele.isFinalApprovalStatus = 'N'
      }
    })
    let finalobj = {
      approvalFlowMasters: updatedApprovalFlowMasters,

    }
    this.loading = true;
    this.service.updateApprovalsData(finalobj).subscribe(

      (result) => {
        // Success callback
        if (result) {
          this.loading = false;
          this.toaster.success("Update SuccessFully");
          // this.router.navigate(['..'], { relativeTo: this.activatedRoute });
          this.router.navigate(['../kubotamasters/trans-hier-mngmnt'])
        }
      },
      (error) => {
        this.loading = false;
        // console.error('Error:', error);
        this.toaster.error('An error occurred while updating approvals.');
      }
    );


  }

  searchTransactionType(event: any, fg: any) {
    let value = fg.get('designationLevel').value;
    if (value && value.trim() !== '') {
      try {
        this.service.gethoDesignationLevel(value).subscribe(res => {
          if (res) {
            this.tranList = res['result']
          }
        })

      } catch (error) {
        console.error("An error occurred:", error);
        // Handle error, show user-friendly message, log, etc.
      }
    } else {
      // If val is blank or empty, hide the list
      this.tranList = [];
    }



    // fg.get('designationLevel').valueChanges.subscribe((value: string) => {

    //  this.service.gethoDesignationLevel(value).subscribe(res=>{
    //     if(res){
    //       this.tranList=res['result']
    //     }
    //  })
    // });
  }



  keyUp(event: any) {

  }
  selectTransactionType(event: any) {
    debugger
    this.createDesignationLevelId = event.option.value.desigId;
  }

  exit() {
    this.router.navigate(['../kubotamasters/trans-hier-mngmnt'])
    // this.router.navigate(['../master/kubotamasters/designation-approval'], { relativeTo: this.activatedRoute });

  }

  displayTransactionType(tranName) {
    if (typeof (tranName) === 'object') {
      return tranName ? tranName.desigLevel : null;
    } else {
      return tranName ? tranName : null;
    }
  }
  // this.addDeatils(element);

  addRow() {

    let FG = this.fb.group({
      isSelected: [{ value: null, disabled: false }],
      isSelect: [{ value: null, disabled: false }],
      designationLevel: [{ value: null, disabled: false }],
      approverLevelSeq: [{ value: null, disabled: false }],
      desigLevelId: [{ value: null, disabled: false }],
      grpSeqNo: [{ value: null, disabled: false }],
      isFinalApprovalStatus: [{ value: null, disabled: false }]
    })
    const control = this.createApprovalHier.controls.details as FormArray;
    control.push(FG)
  }
  deleteRow() {
    let data = this.createApprovalHier.controls.details as FormArray;
    const deleteRow = data.value.filter(val => val.isSelect);
    if (deleteRow.length === data.value.length) {
      this.toaster.warning("You Can't Delete All Details ")
      return;
    }
    if ((data.value.length - deleteRow.length) > 0) {
      const control = data;
      const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelect);
      control.clear();
      notSelected.forEach(elt => { control.push(elt) });
    }
  }

  checkBox(event: any, fg: any) {
    if (event.checked === true) {
      fg.get('isFinalApprovalStatus').setValue('Y');
    } else {
      fg.get('isFinalApprovalStatus').setValue('N');
    }

  }

  validateNumericValue(event: KeyboardEvent) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}
