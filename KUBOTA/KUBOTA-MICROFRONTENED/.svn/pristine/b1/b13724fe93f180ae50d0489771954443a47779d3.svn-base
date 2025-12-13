import { Component, OnInit } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { backOrderPresenter } from './back-order-presenter';
import { backOrderStore } from './back-order-store';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { BackOrderService } from '../../service/back-order.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { backOrderForm, backOrderItem } from './back-order-cancellation-domain';
import { Location } from '@angular/common';
@Component({
  selector: 'app-create-back-order-cancellation-page',
  templateUrl: './create-back-order-cancellation-page.component.html',
  styleUrls: ['./create-back-order-cancellation-page.component.css'],
  providers:[backOrderPresenter,backOrderStore]
})
export class CreateBackOrderCancellationPageComponent implements OnInit {
  backOrderCancellationForm:FormGroup
  loginUser:any
  private btnName:string
  isView:any;
  backOrderData:any;
  isEdit:any;
  draftFlag: any;
  headerId: any;
  dealerId: any;
  bocNo:any
  constructor(
     private presenter:backOrderPresenter,
     private localStorage: LocalStorageService,
     private service:BackOrderService,
      private dialog:MatDialog,
      private toaster:ToastrService,
      private activatedRoute:ActivatedRoute,
      private location:Location,
      private router:Router
    
  ) { }

  ngOnInit() {
     this.backOrderCancellationForm=this.presenter.BOCForm
     
     this.getDealerDetails()
     this.checkFormOperation()
     
  }

  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.backOrderCancellationForm.disable()
        
      this.activeRoute();
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
       this.backOrderCancellationForm.disable()
    }

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
  
  private viewBoc(bocNo){
   this.service.getViewDetailsforBOC(bocNo).subscribe(res=>{
      this.headerId=res.id;
      this.bocNo=res.bocNo
      this.dealerId=res.dealerMaster.id
      if(this.isView){
        this.backOrderCancellationForm.get('dealerName').patchValue(res.dealerMaster.dealerName)
        this.backOrderCancellationForm.get('status').patchValue(res.status)
        this.backOrderCancellationForm.get('boc').patchValue(res.bocNo)
        this.backOrderCancellationForm.get('dealerMaster').patchValue(res.dealerMaster.dealerCode)
      res['cancellationDtls'].forEach(issueItem => {
        this.presenter.addRow(issueItem);
      })
    }
    if(this.isEdit){
      this.backOrderCancellationForm.get('dealerName').patchValue(res.dealerMaster.dealerName)
      this.backOrderCancellationForm.get('dealerMaster').patchValue(res.dealerMaster.dealerCode)
      res['cancellationDtls'].forEach(issueItem => {
        this.presenter.addRow(issueItem);
      })
    }
        
      
   })
  }

  downloadpdfForBOC(){
    this.service.downloadBackOrderCancellationReport(this.headerId,this.bocNo,'true').subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }
  
  validateForm(btnType:string){
    
    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'save';
    if (this.btnName === 'submit') {
        this.draftFlag='false'
    } else {
      this.draftFlag='true'
    }
    if(this.backOrderCancellationForm.invalid){
      this.backOrderCancellationForm.markAllAsTouched()
      return false;
    }
   
   
  
    if(this.presenter.itemDetailsArray.value.length==0){
      this.presenter.itemDetailsArray.markAllAsTouched()
      this.toaster.error("Part Details Not Available")
      return false
    }else{

    }

    if(this.presenter.itemDetailsArray.invalid){
      (this.presenter.itemDetailsArray.controls as FormGroup[]).forEach(fg => {
         console.log(fg.controls.cancelQty,'cancel qty')
        fg.controls.cancelQty.value===null ||fg.controls.cancelQty.value===""|| fg.controls.cancelQty.value===undefined;
        this.presenter.itemDetailsArray.markAllAsTouched()
        this.toaster.error("Cancel Qty Can not be Empty")
        return false
        })
    }else{

    }
    if(this.presenter.itemDetailsArray.valid && this.backOrderCancellationForm.valid){
      this.openConfirmDialog()
    }
    
  }

  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Back Order Cancellation?`

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {
       
        this.submitBackOrderCancellationForm()

      }

    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

 
  submitBackOrderCancellationForm() {
    
    const backOrder = this.presenter.backOrderFormAll.getRawValue() as backOrderForm;
   
    let items = backOrder.itemDetails as backOrderItem[];

    this.backOrderData = {
      ...backOrder.backOrderForm,
      cancellationDtls: items,
     draftFlag: this.draftFlag
    }
    
  
    
    if(this.isEdit){
      
      if (typeof backOrder.backOrderForm.dealerMaster !== 'object') {
          this.backOrderData['dealerMaster']={ 'id': this.dealerId,'dealerCode':backOrder.backOrderForm.dealerMaster};
        
      }
      this.backOrderData['id']=this.headerId;
    }

    delete this.backOrderData.dealerName
    delete this.backOrderData.employeeMasterId
    delete this.backOrderData.reqGivenBy

    
  
    this.backOrderData['employeeMaster'] = { 'id': backOrder.backOrderForm['employeeMasterId'] };
    {
      this.service.saveBackOrderCancellation((this.backOrderData as any)).subscribe(res => {
        if(this.isEdit){
          this.toaster.success('Back Order Cancellation Update', 'Success');
           this.router.navigate(['../'], { relativeTo: this.activatedRoute });
           return false;
        }
        if (res) {
          this.toaster.success('Back Order Cancellation Create', 'Success');
           this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        }else{
          this.toaster.error("Back Order Cancellation Not Generate Some Server Side Issue.")
        }
      })
    }



  }

 

  exitForm(){
    this.location.back()
  }
  clearForm(){
    this.backOrderCancellationForm.reset()
    this.presenter.itemDetailsArray.clear();
    this.presenter.itemDetailsGroup.next(null); 
  }

}
