import { Component, OnInit } from "@angular/core";
import { WpdcStore } from "./wpdc-page.store";
import { WpdcPagePresenter } from "./wpdc-page.presenter";
import { FormGroup, FormArray } from "@angular/forms";
import { WpdcPageWebService } from "./wpdc-page-web.service";
import { ActivatedRoute, Router } from "@angular/router";
import { WarrantyDeliveryChallan, WarrantyWcr, WpdcJSON, WpdcViewMaster } from '../../domain/warranty-parts-delivery-challan.domain';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { saveAs } from 'file-saver';
import { HttpResponse } from "@angular/common/http";
@Component({
  selector: "app-wpdc-page",
  templateUrl: "./wpdc-page.component.html",
  styleUrls: ["./wpdc-page.component.scss"],
  providers: [WpdcStore, WpdcPagePresenter, WpdcPageWebService]
})
export class WpdcPageComponent implements OnInit {
  wpdcForm: FormGroup;
  materialDetailForm: FormArray;
  transporterDetailForm: FormGroup;
  dcDetail: WarrantyDeliveryChallan[] | WarrantyWcr[];
  btnName: string;
  wcrId:WarrantyWcr[] = [];
  warrantyWcr = [];
  submitPartRequisitionItem = [];
  operation: string;
  isshowTransportDetails = false;
  viewDc: WpdcViewMaster;
  isCreate:boolean = false;
  isSubmitDisable:boolean = false;

  constructor(
    private wpdcPagePresenter: WpdcPagePresenter,
    private wpdcPageWebService: WpdcPageWebService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.wpdcForm = this.wpdcPagePresenter.wpdcForm;
    this.materialDetailForm = this.wpdcPagePresenter.materialDetailForm;
    this.transporterDetailForm = this.wpdcPagePresenter.transporterDetailForm;
    this.checkFormOperation();
  }

  private checkFormOperation() {
    this.wpdcPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    this.operation = this.wpdcPagePresenter.operation;
    this.activatedRoute.queryParams.subscribe(qParams => {
      if (this.wpdcPagePresenter.operation == Operation.CREATE) {
        this.isCreate = true;  
        this.getClaimPartInDc(qParams.ids);
        const wcrId = qParams.ids.split(',');
        this.wcrId = wcrId.map(elt => {
         const wcr= {
           id: Number(elt)
         };
         return wcr;
        })
      }
      else {
        this.viewWarrantyDeliveryChallan(atob(qParams.dcNo));
        this.wpdcPagePresenter.transporterDetailFormValidation();
        this.wpdcPagePresenter.operation == Operation.EDIT ? this.transporterDetailForm.enable() : this.transporterDetailForm.disable();
      }
    });
  }

  

  private getClaimPartInDc(ids: string) {
    
    this.wpdcPageWebService.getClaimPartInDc(ids).subscribe(res => {
      this.dcDetail = res;
      res.forEach(elt => {
        this.wpdcPagePresenter.addRowInMaterialDetail(elt);
      });
      this.wpdcPagePresenter.value(this.wpdcPagePresenter.sum(res, 'value'));
      this.wpdcPagePresenter.amount(this.wpdcPagePresenter.sum(res, 'totalAmount'));
      this.wpdcPagePresenter.gstAmount(this.wpdcPagePresenter.sum(res, 'gstAmount'));

    });
  }
  private viewWarrantyDeliveryChallan(dcNo: string) {
    this.wpdcPageWebService.viewWarrantyDeliveryChallan(dcNo).subscribe(res=> {
      this.viewDc = res;
      this.wpdcPagePresenter.viewWpdc(res);
      if(this.wpdcPagePresenter.operation == Operation.VIEW && this.viewDc.warrantyDeliveryChallanViewDto.transporterName != null && this.viewDc.warrantyDeliveryChallanViewDto.transporterName != '') {
        this.isshowTransportDetails = true;
      }
    })
  }
  formValid(btnType: string) {
    // btnType == "submit" ? (this.btnName = "submit") : (this.btnName = "approve");
    this.btnName = btnType;
    if(this.transporterDetailForm.valid) {
      this.openConfirmDialog();
    }
    else {
      this.transporterDetailForm.markAllAsTouched();
      this.tostr.error('Please fill all mandatory fields', 'Error');
    }
  }

  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Delivery Challan?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "Confirm") {
        this.isSubmitDisable = true;
        this.saveDeliveryChallan();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;
    confirmationData.buttonName = ["Confirm", "Cancel"];
    return confirmationData;
  }

  private saveDeliveryChallan() {
    const serviceMethod = this.operation == Operation.CREATE ? this.wpdcPageWebService.saveDeliveryChallan(this.submitDetails()) : this.wpdcPageWebService.updateWarrantyDeliveryChallan(this.submitDetails());

    serviceMethod.subscribe(
      res => {
        if(res){
          this.tostr.success(res['message'], 'Success');
          this.router.navigate(['..'], {relativeTo: this.activatedRoute});
        }else{
          this.isSubmitDisable = false;
          this.tostr.error('Error generated while saving', 'Transaction failed')
        }
      }, 
      err => {
          this.isSubmitDisable = false;
          this.tostr.error('Error generated while saving', 'Transaction failed');
      }
    );
  }

  private submitDetails() {
    const submitDc ={} as WpdcJSON;

    const date: Date = this.transporterDetailForm.get('dispatchDate').value;
    
    if (date !=null) {
    submitDc.dispatchDate = `${date.getDate()}-${date.getMonth()+1}-${date.getFullYear()}`; 
    }
    submitDc.transporterName = this.transporterDetailForm.get('transporterName').value;
    submitDc.docketNumber = this.transporterDetailForm.get('docketNumber').value;
    submitDc.numberOfBox = this.transporterDetailForm.get('noOfBoxes').value;
    submitDc.draftFlag = this.btnName == 'submit' ? false : true;
    this.operation == Operation.CREATE ? submitDc.warrantyWcr = this.wcrId : submitDc.id = this.viewDc.warrantyDeliveryChallanViewDto.id; 

    return submitDc;
  }

  viewPrint(printStatus:string){
    this.wpdcPageWebService.wpdcPrint(this.wpdcForm.get('deliveryChallanNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
            saveAs(file);
          }
     })
 }

  

}

