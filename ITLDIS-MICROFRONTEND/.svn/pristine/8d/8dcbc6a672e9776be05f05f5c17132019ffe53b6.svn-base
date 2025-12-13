import { Component, OnInit } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { backOrderPresenter } from './back-order-cancellation-approval-presenter';
import { backOrderStore } from './back-order-cancellation-approval-store';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { BackOrderService } from '../../../back-order-cancellation-request/service/back-order.service';
import { backOrderForm, backOrderItem } from '../../../back-order-cancellation-request/component/create-back-order-cancellation-page/back-order-cancellation-domain';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { error } from 'console';


@Component({
  selector: 'app-back-order-cancellation-approval-page',
  templateUrl: './back-order-cancellation-approval-page.component.html',
  styleUrls: ['./back-order-cancellation-approval-page.component.css'],
   providers:[backOrderPresenter,backOrderStore]
})
export class BackOrderCancellationApprovalPageComponent implements OnInit {
  backOrderCancellationForm:FormGroup
  dialogMsg: any;
  backOrderData:any
  draftFlag: any;
  loginUser: any;
  isView:any;
  isEdit:any
  headerId: any;
  dealerId: any;
  dealerName: any;
  dealerCode: any;
  isApprove: boolean;
  constructor(
    private presenter:backOrderPresenter,
    private toaster:ToastrService,
    private dialog:MatDialog,
    private service:BackOrderService,
    private localStorage:LocalStorageService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ){

  }

  ngOnInit() {
    this.backOrderCancellationForm=this.presenter.BOCForm
     this.getDealerDetails()
     this.checkFormOperation()
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    
     if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
       this.backOrderCancellationForm.disable()
    }
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true
      this.activeRoute()
       this.backOrderCancellationForm.disable()
    }
    if(this.presenter.operation==Operation.APPROVE){
      this.isApprove=true;
      
      this.activeRoute()
      this.backOrderCancellationForm.disable()
    }

  }

  private viewBoc(bocNo){

    this.service.getViewDetailsforBOC(bocNo).subscribe(res=>{
       this.headerId=res.id;
      //  this.dealerId=res.dealerMaster.id
         this.dealerId=res.dealerMaster.id?res.dealerMaster.id:null;
         this.dealerCode=res.dealerMaster.dealerCode?res.dealerMaster.dealerCode:null
     if(this.isApprove){
       this.backOrderCancellationForm.get('bocNo').patchValue(res.bocNo?res.bocNo:null)
       this.backOrderCancellationForm.get('dealerName').patchValue(res.dealerMaster.dealerName)
       this.backOrderCancellationForm.get('dealerCode').patchValue(res.dealerMaster.dealerCode)
       res['cancellationDtls'].forEach(issueItem => {
         this.presenter.addRow(issueItem);
       })
     }
     if(this.isView){
      this.backOrderCancellationForm.get('bocNo').patchValue(res.bocNo?res.bocNo:null)
      this.backOrderCancellationForm.get('dealerName').patchValue(res.dealerMaster.dealerName)
      this.backOrderCancellationForm.get('dealerCode').patchValue(res.dealerMaster.dealerCode)
      res['cancellationDtls'].forEach(issueItem => {
        this.presenter.addRow(issueItem);
      })
    }
         
       
    })
   }

   private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewBoc(qParam.bocNo);
    });
  }
 
  private getDealerDetails(){
    this.loginUser = this.localStorage.getLoginUser() 
    
    this.backOrderCancellationForm.get('reqGivenBy').patchValue(this.loginUser.name)
    const loginId=this.backOrderCancellationForm.get('employeeMasterId').patchValue(this.loginUser.id)
   
    this.backOrderCancellationForm.get('mobileNumber').patchValue(this.loginUser.mobileNo)
  }
  Approve(approvalStatus){
    console.log(this.backOrderCancellationForm)
    if(this.backOrderCancellationForm.invalid){
      this.backOrderCancellationForm.markAllAsTouched()
      this.toaster.error("Please Fill All Required Fields")
      return false
    }
    if(this.presenter.itemDetailsArray.length==0){
      this.toaster.error("Part Details Are Not Available")
      return false;
    } 
    if(this.presenter.itemDetailsArray.invalid){
      (this.presenter.itemDetailsArray.controls as FormGroup[]).forEach(fg => {
      console.log(fg.controls)
        fg.controls.kaiAcceptedQty.value===null ||fg.controls.kaiAcceptedQty.value===""|| fg.controls.kaiAcceptedQty.value===undefined;
        this.presenter.itemDetailsArray.markAllAsTouched()
        this.toaster.error("Kai Accepted Qty Can not be Empty")
        return false
        })
    }else{

    }
    if(this.presenter.itemDetailsArray.valid){
      this.openApprovalConfirmationBox(approvalStatus)
    }
     
     
   
  }
  private openApprovalConfirmationBox(approvalStatus){
    this.dialogMsg=approvalStatus;
    console.log(this.dialogMsg,'message')
     let message = 'Do you want to '+approvalStatus+' Back Order Cancellation?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
      if (result && result.button === 'Confirm' ){
         this.approveBoc(result.remarkText, approvalStatus);
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    
    confirmationData.buttonName = ['Confirm', 'Cancel'];
  
        confirmationData.remarkConfig = {
         source: Source.APPROVE
       }
       
    if(this.dialogMsg.toLocaleLowerCase()==='Reject'){
     confirmationData.remarkConfig = {
      source: Source.REJECT
    }
  }     
    return confirmationData;
  }
  private approveBoc(remark,approvalStatus){
    const backOrder = this.presenter.backOrderFormAll.getRawValue() as backOrderForm;
   
    let items = backOrder.itemDetails as backOrderItem[];
    items.forEach(items => {
      
        delete items.purchaseOrderNumber
        delete items.itemNo

    })
    this.backOrderData = {
      ...backOrder.backOrderForm,
    
    }
    
    this.backOrderData['backOrderCancellation']={

      
      
       "id":this.headerId,
      "draftFlag": false,
    'employeeMaster' : { 'id': backOrder.backOrderForm['employeeMasterId'] },
    'dealerMaster':{'id':this.dealerId,'dealerCode':this.dealerCode},
    cancellationDtls: items,
    ...backOrder.backOrderForm,
    }
    this.backOrderData['remark']=remark
    this.backOrderData['approvalStatus']=approvalStatus
    delete this.backOrderData.dealerName
    delete this.backOrderData.employeeMasterId
    delete this.backOrderData.reqGivenBy
 delete this.backOrderData.dealerCode
  //  delete this.backOrderData.id
   delete this.backOrderData.bocNo
   delete this.backOrderData.mobileNumber
   delete this.backOrderData.backOrderCancellation.employeeMasterId,
    delete this.backOrderData.backOrderCancellation.dealerName,
    delete this.backOrderData.backOrderCancellation.dealerCode,
   delete this.backOrderData.backOrderCancellation.reqGivenBy,
   
    this.service.approveBOC(this.backOrderData).subscribe(res=>{
      if(res && res['status']=='200'){
        this.toaster.success(res.message,'Success')
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      }else{
        this.toaster.error(res.message,'Error')
      }
    })
  }
  }


   


