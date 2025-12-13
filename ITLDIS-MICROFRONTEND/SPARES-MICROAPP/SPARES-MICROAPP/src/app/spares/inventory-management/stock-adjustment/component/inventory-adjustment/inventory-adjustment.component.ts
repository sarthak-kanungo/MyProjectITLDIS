import { Component, OnInit, Input } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StockAdjustmentPresenter } from '../stock-adjustment-page/stock-adjustment.presenter';
import { ToastrService } from 'ngx-toastr';
import { DateService } from '../../../../../root-service/date.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { ModalFileUploadComponent } from 'src/app/spares/Utility/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from 'src/app/spares/Utility/item-error-report/item-error-report.component';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';
import { ItemDetail, ItemErrorDetail } from '../../domain/stock-adjustment.domain'
import { InventoryAdjustmentService } from './inventory-adjustment.service'
@Component({
  selector: 'app-inventory-adjustment',
  templateUrl: './inventory-adjustment.component.html',
  styleUrls: ['./inventory-adjustment.component.scss'],
  providers: [ItemDetailsWebService, InventoryAdjustmentService]
})
export class InventoryAdjustmentComponent implements OnInit {
  inventoryAdjustmentDetailsForm: FormGroup
  @Input() isView: boolean;
  
  constructor(
    private presenter: StockAdjustmentPresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
    private itemDetailsWebService: ItemDetailsWebService,
    private inventoryAdjustmentService: InventoryAdjustmentService
  ) {
      
  }

  ngOnInit() {
    this.inventoryAdjustmentDetailsForm = this.presenter.inventoryAdjustmentDetailsForm
  }
  
  public downloadTemplate() {
      this.itemDetailsWebService.downloadTemplateForPoItemDetail("StockUpload.xlsx").subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {           
              const file = new File([resp.body], "StockUpload.xlsx", { type: 'application/vnd.ms-excel' });
              saveAs(file);
            }
      })
    }

    public uploadExcel() {
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.id = "modal-component";
      dialogConfig.width = "500px";
      const modalDialog = this.dialog.open(ModalFileUploadComponent, dialogConfig);
      modalDialog.afterClosed().subscribe(result => {
        if(result.event === 'upload'){
           let file:File = result.data;
         
             let uploadableFile = { file: file}
             
             this.inventoryAdjustmentService.uploadExcelItemDetail(uploadableFile).subscribe(response => {
                let items:Array<ItemErrorDetail> = response['result'];
                this.toastr.success(response['message']);
                if(items.length>0){
                    const dialogConfig1 = new MatDialogConfig();
                    dialogConfig1.disableClose = true;
                    dialogConfig1.id = "item-error-component";
                    dialogConfig1.width = "500px";
                    dialogConfig1.height = "350px";
                    dialogConfig1.data = items;
                    const modalDialog = this.dialog.open(ItemErrorReportComponent, dialogConfig1);
                }
                if (response['status']==200) {
                  this.router.navigate(['../'], { relativeTo: this.activateRoute });
                }
             })
           }
       });
     }

}
