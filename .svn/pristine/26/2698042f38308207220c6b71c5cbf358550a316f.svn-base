import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { indentPresenter } from '../branch-transfer-page/branch-indent-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { ItemDetailTableApiService } from 'src/app/spares/counter-sales/customer-order/component/item-details-table/item-detail-table-api.service';
import { BranchIndentServiceService } from '../../service/branch-indent-service.service';
import { AutoCompleteResult, PartData, SearchAutocomplete } from '../domain/domaim';
import { ToastrService } from 'ngx-toastr';
import { HttpResponse } from '@angular/common/http';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ModalFileUploadComponent } from '../modal-file-upload/modal-file-upload.component';
import { saveAs } from 'file-saver';
import { ItemErrorReportComponent } from '../item-error-report/item-error-report.component';
@Component({
  selector: 'app-indent-item-details-page',
  templateUrl: './indent-item-details-page.component.html',
  styleUrls: ['./indent-item-details-page.component.css'],
  providers:[indentPresenter,BranchIndentServiceService]
})
export class IndentItemDetailsPageComponent implements OnInit {
     @Input() indentItemForm:FormArray
      @Input() branchID;
      private itemDetailsArray: Array<FormGroup> = [];
      
     materialDetailHeading:any
     isView:any
     isEdit:any
     itemNoList:any
     itemsIdArray = [];
     autoCompleteData:any
  itmeID: any=0;
  constructor(private presenter:indentPresenter,
    private activatedRoute:ActivatedRoute,
    private service: BranchIndentServiceService,
    private toaster:ToastrService,
    private matDialog : MatDialog
    ) { }
ngOnChanges(){
  const f=this.branchID
  // console.log(f)
}
  ngOnInit() {
    this.materialDetailHeading = ['SL.NO.', 'ITEM NO', 'ITEM DESC', 'REQUESTING BRANCH STOCK QTY','SUPPLYING BRANCH STOCK QTY','REQUEST QTY'];
    this.checkFormOperation()
    // console.log(this.presenter.indentForm)

  }
  
  addRow(){    
    this.presenter.addRowInItemDetails();
  }
  
  private formValueChanges() {
    this.indentItemForm.controls.forEach(elt => {
      elt.get('sparePartMaster').valueChanges.subscribe(val => {
       
        if (val && val!='') {   
          this.autoCompletePartNumber(val);
        }
      })
    })
  }
  downloadTemplate(){
    this.service.downloadTemplates("SpareItemsDetails.xlsx").subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        const file = new File([resp.body], "SpareItemsDetails.xlsx", { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  public uploadExcel(){
    // debugger
    if(this.presenter.indentForm.controls.reqToBranch.value === '' ||this.presenter.indentForm.controls.reqToBranch.value === null || this.presenter.indentForm.controls.reqToBranch.value === undefined){
        this.toaster.warning('Please select Request to Branch');
        return false;
    }
    
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    const modalDialog = this.matDialog.open(ModalFileUploadComponent, dialogConfig);
  
    modalDialog.afterClosed().subscribe(result => {
      if(result.event === 'upload'){
          let file:File = result.data;
          
          const errorItemDetailsArray:Array<PartData> = [];
          
          let uploadableFile = { file: file,suppByBranchId:this.branchID,existingItems : this.itmeID  }
          
          this.service.uploadExcelPoItemDetail(uploadableFile).subscribe(response => {
      
              let items:Array<PartData> = response['result'];
              
              this.itmeID=items[0].itemId;
              if(items!=null && items.length > 0){
                  items.forEach(data => {
                      if(data.isValidItem === 'Y'){
                        this.presenter.addRowInItemDetails(data,true);
                           
                      }else{
                           errorItemDetailsArray.push(data);
                      }
                  })
              }
             
               this.toaster.success(response['message']);
              if(errorItemDetailsArray.length>0){
                  
                  const dialogConfig1 = new MatDialogConfig();
                  dialogConfig1.disableClose = true;
                  dialogConfig1.id = "item-error-component";
                  dialogConfig1.width = "500px";
                  dialogConfig1.height = "350px";
                  dialogConfig1.data = errorItemDetailsArray;
                  const modalDialog = this.matDialog.open(ItemErrorReportComponent, dialogConfig1);
                  
              }
          })
      }
   })
}
  private autoCompletePartNumber(txt: string) {
    if(!this.isView){
      if(this.presenter.indentForm.controls.reqToBranch.value===null||this.presenter.indentForm.controls.reqToBranch.value==='' ||this.presenter.indentForm.controls.reqToBranch.value===undefined){
        this.toaster.warning("Please Select Request to Branch")
        
        return false;
      }
    }
    this.service.autoCompleteItemNumber(txt).subscribe(res => {
      this.autoCompleteData = res;
    })
  }
  getSpareItemDetails(event, list: FormGroup, index) {
    // debugger
    // console.log(event.option.value.itemNo)
    if (event && event['option']['value']) {
      // if (!this.checkduplicate(index, event['option']['value']['partNumber'])) {
        this.service.getSpareItemDetails(event.option.value.itemNo,this.branchID).subscribe(res => {
         
            list.get('itemDescription').patchValue(res.itemDescription?res.itemDescription:null)
           list.get('reqBranchStockQty').patchValue(res.reqBranchStockQty?res.reqBranchStockQty:null)
          list.get('supBranchStockQty').patchValue(res.suppBranchStockQty?res.suppBranchStockQty:null)
        });
      // } else {
      //   this.toaster.error("Item No can not be duplicate");
      //   this.indentItemForm.controls[index].reset();
      // }
    }
  }
  getItemNumber(){
    this.formValueChanges()
  }

  // checkduplicate(index: number, itemNo: string) {
  //   // console.log(itemNo, 'number')
  //   if (index > 0) {
  //     for (let i: number = 0; i < index; i++) {
  //       if (this.indentItemForm.controls[i].get('sparePartMaster').value.value === itemNo) {
  //         return true;
  //       }
  //     }
  //   }
  //   return false;
  // }
 
  deleteRow(){
    this.presenter.deleteRowInItemDetails()
  }
  change(event){
    
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
    } else if (this.presenter.operation == Operation.CREATE) {
      this.presenter.addRowInItemDetails();
    }else if(this.presenter.operation==Operation.EDIT){
      this.isEdit=true
    }
  }
  displayCodeFn(partNumber:SearchAutocomplete) {
  // console.log(t)
    return partNumber ? partNumber.itemNo : undefined
  }

}
