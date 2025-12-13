import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { SpareDescripancyClaimPresenter } from '../store-presenter/spare-descripancy-claim-presenter';
import { SpareClaimService } from '../service/spare-claim.service';
import { debounceTime } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { SpareDescripancyClaimStore } from '../store-presenter/spare-descripancy-claim-store';

@Component({
  selector: 'app-item-details-spare-descripancy-claim',
  templateUrl: './item-details-spare-descripancy-claim.component.html',
  styleUrls: ['./item-details-spare-descripancy-claim.component.css']
})
export class ItemDetailsSpareDescripancyClaimComponent implements OnInit {
   @Input() partDetailsForm:FormArray;
   @Input() grnId:string;
   @Input() claimType:string;
   @Input() editSpareGRNId:any;
   @Input() viewClaimType:boolean=false
   hideMrrFields:boolean;
   viewhideMrrFields:boolean;
   id:string;
   itemList:any;
   itemDetails:any;
   userType:any;
   kubotaUser:boolean=false;
   isView:boolean=false;
   isEdit:boolean=false;
   isCreate:boolean=false;
   isApprove:boolean=false;
  constructor(

    private presenter:SpareDescripancyClaimPresenter,
    private store:SpareDescripancyClaimStore,
    private service:SpareClaimService,
    private toaster:ToastrService,
    private localStorage:LocalStorageService,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    // this.presenter.addRowInMaterialDetail(null);
    this.userType=this.localStorage.getLoginUser();
     if(this.userType.userType=="kubota"){
        this.kubotaUser=true;
     }else{
      this.kubotaUser=false;
     }
     this.checkForOperation();
  }

  checkForOperation(){
    this.hi();
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
    }
    else if (this.presenter.operation == Operation.APPROVE) {
      this.isApprove = true
    }
    else{
      this.presenter.addRowInMaterialDetail();
      this.isCreate = true
    }
  }

  

  ngOnChanges(){
    this.id=this.grnId;
    if(this.claimType==="MRR"){
      this.hideMrrFields=true;
    }else{
      this.hideMrrFields=false;
    }
    
  }
  hi()
  {
    setTimeout(() => {
      if (this.viewClaimType === true) {
         this.viewhideMrrFields = true;
      } else {
         this.viewhideMrrFields = false;
      }
    }, 2000); // 2000 milliseconds (2 seconds) delay
  }
  addRow(){
   this.presenter.addRowInMaterialDetail();
  }
  deleteRow(){
    this.presenter.deleteMaterialDetail(this.partDetailsForm);
  }


  
 


  searchItemNumber(event:any){
    this.partDetailsForm.controls.forEach(partNo => {
      partNo.get('partMaster').valueChanges
        .pipe(debounceTime(300)) // Adjust the debounce time as needed (e.g., 300 milliseconds)
        .subscribe(val => {
          if (val) {
            this.autoComplemetePartNo(val);
          }
        });
    });
  }

  autoComplemetePartNo(text){
    if(this.isCreate){
    this.service.getAutoSearchItemNo(this.id,text).subscribe(res=>{
      if(res){
        this.itemList=res['result'];
      }
    })
  }else{
    this.service.getAutoSearchItemNo(this.editSpareGRNId,text).subscribe(res=>{
      if(res){
        this.itemList=res['result'];
      }
    })
  }
  }
  selectItemNumber(event:any,list:FormGroup,index:any){
    if(this.isCreate){
      if (event && event['option']['value']) {
        if (!this.checkduplicate(index, event['option']['value']['itemNo'])) {
    this.service.getPartDetails(this.id,event.option.value.itemNo).subscribe(res=>{
      if(res){
        if(this.claimType==="MRR"){
          // console.log('if')
        this.itemDetails=res['result'];

        list.get('itemDesc').patchValue(this.itemDetails.itemDescription)
        list.get('invoiceQty').patchValue(this.itemDetails.invoiceQty)
        list.get('receiptQty').patchValue(this.itemDetails.receiptQty)
         list.get('sellingUnitePrice').patchValue(this.itemDetails.sellingUnitPrice)
        // list.get('damageQty').patchValue(this.itemDetails.damagedQty)
        // list.get('excessQty').patchValue(this.itemDetails.excessQty)
        }else{
          this.itemDetails=res['result'];
        list.get('itemDesc').patchValue(this.itemDetails.itemDescription)
        list.get('invoiceQty').patchValue(this.itemDetails.invoiceQty)
        list.get('receiptQty').patchValue(this.itemDetails.receiptQty)
        list.get('shortQty').patchValue(this.itemDetails.shortQty)
        list.get('damageQty').patchValue(this.itemDetails.damagedQty)
        list.get('excessQty').patchValue(this.itemDetails.excessQty);
        list.get('sellingUnitePrice').patchValue(this.itemDetails.sellingUnitPrice)
        }
      }else{
        this.toaster.warning("Item Details is not Available !")
      }
    })
  }
  else {
    this.toaster.error("Item No can't be duplicate");
    this.partDetailsForm.controls[index].reset();
  }
}
  }else{
    if (event && event['option']['value']) {
      if (!this.checkduplicate(index, event['option']['value']['itemNo'])) {
    this.service.getPartDetails(this.editSpareGRNId,event.option.value.itemNo).subscribe(res=>{
      if(res){
        this.itemDetails=res['result'];
        list.get('itemDesc').patchValue(this.itemDetails.itemDescription)
        list.get('invoiceQty').patchValue(this.itemDetails.invoiceQty)
        list.get('receiptQty').patchValue(this.itemDetails.receiptQty)
        list.get('shortQty').patchValue(this.itemDetails.shortQty)
        list.get('damageQty').patchValue(this.itemDetails.damagedQty)
        list.get('excessQty').patchValue(this.itemDetails.excessQty)
      }else{
        this.toaster.warning("Item is not Available !")
      }
      
    })
  }
  else {
    this.toaster.error("Item No can't be duplicate");
    this.partDetailsForm.controls[index].reset();
  }
} 
  }
  }
  displayItemNumber(itemNumber:any){
    if(typeof(itemNumber)==="string"){
      return itemNumber?itemNumber:null
    }else{
    return itemNumber?itemNumber.itemNo:undefined
    }

  }
  checkduplicate(index: number, itemNo: string) {
     if (index > 0) {
      for (let i: number = 0; i < index; i++) {
        if(this.isEdit){
        if (this.partDetailsForm.controls[i].get('partMaster').value===itemNo) {
          return true;
        }
      }else{
        if (this.partDetailsForm.controls[i].get('partMaster').value.itemNo===itemNo) {
          return true;
        }
      }
       }
    }
    return false;
  }
  keyUp(event:KeyboardEvent,list:FormGroup){
   if(event.key==="Backspace" || event.key==="Delete"){
    list.get('itemDesc').reset()
    list.get('invoiceQty').reset()
    list.get('receiptQty').reset()
    list.get('shortQty').reset()
    list.get('damageQty').reset()
    list.get('excessQty').reset()
    list.get('returnQty').reset();
    list.get('value').reset();
    list.get('dealerRemarkReasons').reset();
    list.get('sellingUnitePrice').reset();
   }
  }

  onKeyPressString(event:KeyboardEvent){
    const pattern = /[a-zA-Z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }

  onKeyPressNumericValue(event:KeyboardEvent){
    // console.log(event,'event')
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }

  validateAcceptedQty(event:any,list:any){
     this.presenter.checkQuantity();
  }

  validation(event){
     if(event.key==='Backspace' || event.key==='delete'){
      this.presenter.calCulateValueSellingPrice();
     }
    if(this.claimType==="MRR"){
     this.presenter.calCulateValueSellingPrice();
    }else{
      this.presenter.calCulateValueSellingPrice();
      this.presenter.checkValidation();
    }
   
  }

  

}
