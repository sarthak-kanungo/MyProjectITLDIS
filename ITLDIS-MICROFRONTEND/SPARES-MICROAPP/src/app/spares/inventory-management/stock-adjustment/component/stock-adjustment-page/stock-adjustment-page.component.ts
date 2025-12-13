import { Component, OnInit } from '@angular/core';
import { StockAdjustmentStore } from './stock-adjustment.store';
import { StockAdjustmentPresenter } from './stock-adjustment.presenter';
import { FormGroup } from '@angular/forms';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { DateService } from '../../../../../root-service/date.service';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { SaveStockAdjustment, SubmitItemDetail, ItemErrorDetail } from '../../domain/stock-adjustment.domain';
import { StockAdjustmentPageWebService } from './stock-adjustment-page-web.service';
import { MatDialogConfig  } from '@angular/material';
import { ItemErrorReportComponent } from 'src/app/spares/Utility/item-error-report/item-error-report.component';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-stock-adjustment-page',
  templateUrl: './stock-adjustment-page.component.html',
  styleUrls: ['./stock-adjustment-page.component.css'],
  providers: [StockAdjustmentStore, StockAdjustmentPresenter, StockAdjustmentPageWebService]
})
export class StockAdjustmentPageComponent implements OnInit {
  stockAdjustmentForm: FormGroup
  inventoryAdjustmentDetailsForm:FormGroup
  isView: boolean
  isApprove: boolean
  isEdit: boolean
  isCreate: boolean
  dialogMsg: string;
  isDraft: boolean;
  hierDtls:any
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  isSubmitDisable:boolean = false;
  adjustmentId:string;
  constructor(
    private presenter: StockAdjustmentPresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
    private stockAdjustmentPageWebService: StockAdjustmentPageWebService,
    private ngswSearchTableService: NgswSearchTableService
  ) {

  }
  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.stockAdjustmentForm = this.presenter.stockAdjustmentForm
    this.inventoryAdjustmentDetailsForm = this.presenter.inventoryAdjustmentDetailsForm;
    this.viewOrEditOrCreate();
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.APPROVE) {
      
      this.isView = true
      this.isEdit = false
      if(this.presenter.operation === Operation.APPROVE){
        this.isApprove = true;
      }
      this.activateRoute.paramMap.subscribe(param => {
        if (param.has('id')) {
          this.adjustmentId = atob(param.get('id'));
          this.getAdjustmentForView(atob(param.get('id')));
        }
      })
        
    } else if (this.presenter.operation === Operation.CREATE) {
      
      this.isCreate = true
    }
  }
  
  getAdjustmentForView(id){
      this.stockAdjustmentPageWebService.getAdjustDetails(id).subscribe(response => {
          if(response['result']!=null){
               this.inventoryAdjustmentDetailsForm.patchValue(response['result']['result'][0]);
               const itemDetails: SubmitItemDetail[] = response['result']['result'];
               this.hierDtls = response['result']['hieries'];
              itemDetails.forEach(item => {
                   delete item.adjustmentNumber;
                   delete item.adjustmentDate;
                   delete item.adjustmentStatus;
               })
               this.stockAdjustmentForm.disable();
               this.size=itemDetails.length;
               this.dataTable = this.ngswSearchTableService.convertIntoDataTable(itemDetails);
               this.totalSearchRecordCount = itemDetails.length;
               
          }else{
              this.toastr.error(response['message']);
              this.router.navigate(['../../'], { relativeTo: this.activateRoute });
          }
          
      });
  }
  
  submitData() {
    this.stockAdjustmentPageWebService.postAdjustDetails(this.jsonForSo()).subscribe(response => {
      
      if (response) {
        let displayMsg = response['message']  
        // const errorItemDetailsArray:ItemErrorDetail[] = response['result'];
        // if(errorItemDetailsArray.length>0){
        //   const dialogConfig1 = new MatDialogConfig();
        //   dialogConfig1.disableClose = true;
        //   dialogConfig1.id = "item-error-component";
        //   dialogConfig1.width = "500px";
        //   dialogConfig1.height = "350px";
        //   dialogConfig1.data = errorItemDetailsArray;
        //   const modalDialog = this.dialog.open(ItemErrorReportComponent, dialogConfig1);
        // }else{
        //     this.toastr.success(displayMsg, 'Success')
        // }
        this.toastr.success(displayMsg, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activateRoute });
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving', 'Transaction failed');
    })
  }
  private jsonForSo() {
    const stockAdjustment = {
      ...this.buildJsonForItemDetails()
    }
    return stockAdjustment;
  }
  private buildJsonForItemDetails() {
    const stockAdjustmentRowValue = this.stockAdjustmentForm.getRawValue();
    
    const saveAndSubmitStockAdjustment = {} as SaveStockAdjustment;
    const stockPartDetail:SubmitItemDetail[] = [];
    let count=1;
    stockAdjustmentRowValue.itemDetailsTable.itemDetailsRowData.forEach(element => {
      
      stockPartDetail.push({
          srNo:count++,
          partNo:element['itemNo']['itemNo'],
          storeId:element['store']['id'],
          store:element['store']['value'],
          stockBinId:typeof element['binLocation']=='object'?element['binLocation']['id']:null,
          binLocation:typeof element['binLocation']=='object'?element['binLocation']['value']:element['binLocation'],
          adjustmentType:element['adjustmentType']=='Increase'?'I':'D',
          mrp:element['mrp'],
          qtyAdjusted:element['transferQty'],
          increasedAmount:element['adjustmentType']=='Increase'?(element['mrp']*element['transferQty']):0,
          decreasedAmount:element['adjustmentType']=='Decrease'?(element['mrp']*element['transferQty']):0
      });
    });
    saveAndSubmitStockAdjustment.stockAdjustmentDtls = stockPartDetail;
    return saveAndSubmitStockAdjustment;
  }
  saveAndSubmitForm(isSave?: boolean) {
    
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.validateForm();
  }
  validateForm() {
    
    this.presenter.markFormAsTouched();
    if (this.stockAdjustmentForm.valid) {
      this.openConfirmDialog()
    } else {
      this.stockAdjustmentForm.markAllAsTouched();  
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Stock Adjustment Details?`;
    if (this.isEdit) {
      message = 'Do you want to update Stock Adjustment Details?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
    
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }


  public approvalForm(flag){
    this.openApprovalConfirmDialog(flag);
}

approve(remark, flag) {
    this.stockAdjustmentPageWebService.approveAdjustment(remark, this.adjustmentId+"", flag).subscribe(res => {
      if(res){
        this.toastr.success(res['message'], 'Success');
        this.router.navigate(['../../'], { relativeTo: this.activateRoute })
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error genearated while approval','Transaction failed')
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error genearated while approval','Transaction failed')
    })
  }

  private openApprovalConfirmDialog(flag): void | any {
    let message = `Do you want to ${flag} PO`;
    const confirmationData = this.setConfirmationDialogData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result:DialogResult) => {
        if (result.button === 'Confirm') {
            this.isSubmitDisable = true;
            this.approve(result.remarkText, flag);
         }
    });
  }
  private setConfirmationDialogData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    confirmationData.remarkConfig = {
            source: Source.APPROVE
        }
    return confirmationData;
  }
  

}
