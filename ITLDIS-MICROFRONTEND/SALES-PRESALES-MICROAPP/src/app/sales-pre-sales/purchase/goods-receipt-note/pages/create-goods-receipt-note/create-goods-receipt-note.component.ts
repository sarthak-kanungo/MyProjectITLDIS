import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, AbstractControlOptions, FormArray, AbstractControl } from '@angular/forms';
import { CreateGrnFormService } from './create-grn-form.service';
import { AccPacInvoicePartDetail } from '../../componant/goods-receipt-note-create/goods-receipt-note-create';
import { TableConfig, EtTableValueChangeEvent } from 'editable-table';
import { SaveGRN } from './create-goods-receipt-note-dto';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-create-goods-receipt-note',
  templateUrl: './create-goods-receipt-note.component.html',
  styleUrls: ['./create-goods-receipt-note.component.scss'],
  providers: [CreateGrnFormService]
})
export class CreateGoodsReceiptNoteComponent implements OnInit {

  isEdit: boolean;
  public createGrnForm: FormGroup;
  etGrossTotalValue = 0;
  saveGRN: SaveGRN;
  isView: boolean;
  grnDetail: any;
  private grnStatus: string;
  machineryDetail: any[];
  accessoryDetail: any[];
  machineryGrossTotalValue: any;
  accessoryGrossTotalValue: any;
  isSubmitDisable = true;
  public set accPacInvoicePartDetailList(v: Array<AccPacInvoicePartDetail>) {
    this._accPacInvoicePartDetailList = v;
    this.etGrossTotalValue = 0;
    this._accPacInvoicePartDetailList.forEach(res => {
      this.etGrossTotalValue = this.etGrossTotalValue + res.totalValue;
    })
  }

  public get accPacInvoicePartDetailList(): Array<AccPacInvoicePartDetail> {
    return this._accPacInvoicePartDetailList;
  }

  private _accPacInvoicePartDetailList = [] as Array<AccPacInvoicePartDetail>;
  constructor(
    public dialog: MatDialog,
    private createGrnFormService: CreateGrnFormService,
    private router: Router,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.createGrnForm = this.createGrnFormService.generateGrnForm();
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('viewId')) {
        this.isView = true;
        this.getGrnByDmsGrnId(parseInt(atob(param.get('viewId'))));
        this.createGrnForm.controls.goodsReceiptNote.disable();
      }
      if (param.has('updateId')) {
        this.isEdit = true;
        this.getGrnByDmsGrnId(parseInt(atob(param.get('updateId'))));
      }
    })
  }
  private getGrnByDmsGrnId(grnId: number) {
    this.createGrnFormService.getGrnByDmsGrnNumber(grnId).subscribe(grnRes => {
      this.grnDetail = grnRes['result'];
      this.accPacInvoicePartDetailList = this.grnDetail['grnMachineDetails'];
      this.seperateMachineryAndAccessory(this.accPacInvoicePartDetailList);
      this.etGrossTotalValue = this.grnDetail.grossTotalValue;
      this.patchValueToGoodsReceiptNote(this.grnDetail);
      this.createGrnForm.controls.itemDetails.patchValue(this.machineryDetail);
      this.createGrnForm.controls.accessoryDetail.patchValue(this.accessoryDetail);
    })
  }
  private seperateMachineryAndAccessory(grnMachineDetails: any[]) {
    this.machineryGrossTotalValue = 0;
    this.accessoryGrossTotalValue = 0;
    this.machineryDetail = grnMachineDetails.filter((val)=>{
      if (val && val.category === 'Machinery') {
        this.machineryGrossTotalValue += val.totalValue;
        return true;
      }
    });
    
    this.accessoryDetail = grnMachineDetails.filter((val)=>{
      if (val && val.category !== 'Machinery') {
        this.accessoryGrossTotalValue += val.totalValue;
        return true;
      }
    });
  }
  private patchValueToGoodsReceiptNote(grnDetail) {
    this.createGrnForm.controls.goodsReceiptNote.patchValue({
      grnType: grnDetail['grnType'],
      dmsGrnNumber: grnDetail['dmsGrnNumber'],
      grnDate: grnDetail['grnDate'],
      accPacInvoice: { value: grnDetail['accPacInvoice'].invoiceNumber, id: grnDetail['accPacInvoice'].id },
      invoiceDate: grnDetail['accPacInvoice'].invoiceDate,
      grnStatus: grnDetail['grnStatus'],
      billTo: grnDetail['accPacInvoice'].billTo,
      shipTo: grnDetail['accPacInvoice'].shipTo,
      transporter: grnDetail['transporter'].transporterName,
      driverName: grnDetail['driverName'],
      driverMobile: grnDetail['driverMobile'],
      transporterVehicleNumber: grnDetail['transporterVehicleNumber'],
      lrNo: grnDetail['accPacInvoice'].lrNo,
      grnBy: grnDetail['grnBy'],
    });
  }

  accPacInvoicePartDetailToEt(invoiceList: Array<AccPacInvoicePartDetail>) {
    this.accPacInvoicePartDetailList = invoiceList.map(invoice => {
      invoice.receiptQuantity = invoice.invoiceQuantity;
      return invoice;
    });
    this.seperateMachineryAndAccessory(this.accPacInvoicePartDetailList);
  }
  validateForm(grnStatus: string) {
    if (this.createGrnForm.valid) {
      this.grnStatus = grnStatus;
      this.openConfirmDialog(grnStatus);
    }
  }

  submitData() {
    if(this.etGrossTotalValue == 0){
      this.toastr.success('Total GRN Value can not be 0', 'Error');
      return;
    }
    let saveGRN = this.createGrnFormService.createSaveMachineGrnJSON(this.etGrossTotalValue);
    saveGRN.grossTotalValue = this.etGrossTotalValue;
    saveGRN.grnStatus = this.grnStatus;
    saveGRN.driverMobile = saveGRN.driverMobile.toString();
    if (this.isEdit) {
      saveGRN.id = this.grnDetail.id;
    }
    this.createGrnFormService.createMachineGrn(saveGRN).subscribe(res => {
      if(res['status']=='400'){
        this.toastr.success(res['message'], 'Error');
        this.isSubmitDisable = false;
      }else{
        if (this.isEdit) {
          this.toastr.success('GRN Updated Successfully', 'Success');
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
          return;
        }
        this.toastr.success('GRN Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }
    }, err => {
      this.isSubmitDisable = false;
      if (this.isEdit) {
        this.toastr.error('GRN Updated has Error', 'Error');
        return;
      }
      this.toastr.error('GRN Saved has Error', 'Error');
    });
  }

  private openConfirmDialog(grnStatus): void | any {
    let message = `Do you want to ${grnStatus === 'draft' ? 'save' : 'submit'} Machine GRN?`;
    if (this.isEdit) {
      message = 'Do you want to update Machine GRN?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
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
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  clearForm() {
    this.createGrnForm.get('goodsReceiptNote').patchValue({ resetForm: true });
    this.createGrnForm.get('itemDetails').patchValue({ resetForm: true });
    this.createGrnForm.get('accessoryDetail').patchValue({ resetForm: true });
    this.accPacInvoicePartDetailList = []
    this.etGrossTotalValue = 0;
    this.machineryGrossTotalValue = 0;
    this.accessoryGrossTotalValue = 0;
  }
  redirectToSearchPage() {
    if (this.isView || this.isEdit) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }
  
  viewPrint(printStatus:string){
      this.createGrnFormService.printGrn(this.grnDetail['dmsGrnNumber'], printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
