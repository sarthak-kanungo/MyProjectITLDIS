import { Component, OnInit } from '@angular/core';
import { HotlineReportPageStore } from './hotline-report-create-page.store';
import { HotlineReportPagePresenter } from './hotline-report-create-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { RetroFitmentCampaignCreatePageWebService } from '../../../retro-fitment-campaign/component/retro-fitment-campaign-create-page/retro-fitment-campaign-create-page-web.service';
import { hotlineReport } from '../hotline-report/hotline-service';
import { MaterialData, PartData, hotlineReportMachineDetails, submitHTR, warrantyHotlineMachineReport, warrantyHotlinePartReport, } from '../../domain/hotline-report.domain';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-hotline-report-create-page',
  templateUrl: './hotline-report-create-page.component.html',
  styleUrls: ['./hotline-report-create-page.component.scss'],
  providers: [HotlineReportPageStore, HotlineReportPagePresenter, RetroFitmentCampaignCreatePageWebService, hotlineReport]
})
export class HotlineReportCreatePageComponent implements OnInit {
  hotlineReportForm: FormGroup;
  materialDetailForm: FormArray;
  partDetailForm: FormArray;
  hotlineReportAttachments: WarrantyPcrPhotos;
  warrantyPcrPhotos: WarrantyPcrPhotos[];
 
  vendorRespomseForm: FormGroup;
  date: string;
  private btnName: string;
  isView: any
  isEdit: any
  materialDetailsFormLength: any
  partDetailsFormLength: any
  answerPhoto: any = [];
  submitPhoto: any = [];
  allDataAnswer: any;
  printStatus: any
  id: any;
  hotlineNo: any
  constructor(
    private hotlineReportPagePresenter: HotlineReportPagePresenter,
    private toaster: ToastrService,
    public dialog: MatDialog,
    private retroFitment: RetroFitmentCampaignCreatePageWebService,
    private service: hotlineReport,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) { }

  ngOnInit() {

    this.hotlineReportForm = this.hotlineReportPagePresenter.hotlineReportForm;
    this.materialDetailForm = this.hotlineReportPagePresenter.materialDetailForm;
    this.partDetailForm = this.hotlineReportPagePresenter.partDetailForm;
    this.vendorRespomseForm = this.hotlineReportPagePresenter.vendorRespomseForm;
    //  this.getSystemDate()
    this.checkFormOperation()
  }
  private checkFormOperation() {
    this.hotlineReportPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.hotlineReportPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
      this.hotlineReportForm.disable();
      this.materialDetailForm.disable();
      this.partDetailForm.disable();
      this.vendorRespomseForm.disable()
      this.materialDetailForm.valueChanges.subscribe(val => {
        if (val) {
          this.materialDetailsFormLength = this.materialDetailForm.length > 0;
        }
      });
      this.partDetailForm.valueChanges.subscribe(vals => {
        if (vals) {
          this.partDetailsFormLength = this.partDetailForm.length > 0;
        }
      });
      this.activeRoute();
    }
    else if (this.hotlineReportPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;
      this.hotlineReportForm.disable();
      this.materialDetailForm.disable();
      this.partDetailForm.disable();
      this.activeRoute();
    }
    else if (this.hotlineReportPagePresenter.operation == Operation.CREATE) {
      this.materialDetailsFormLength = this.materialDetailForm.length == 0;
      this.partDetailsFormLength = this.partDetailForm.length == 0;
    }


  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewHotlineReport(qParam.hotlineNo);
    });
  }
  private viewHotlineReport(hotlineNo) {
    this.service.viewHotlineReport(hotlineNo).subscribe((res: any) => {
      this.id = res.id
      this.hotlineNo = res.htlrNo
      this.allDataAnswer = res;
      this.hotlineReportForm.patchValue({
        hotlineNo: res.htlrNo,
        hotlinePlantMaster: res.hotlinePlantMaster.plantName,
        departmentMaster: res.departmentMaster.departmentName,
        hotlineDate: res.htlrDate,
        employeeMaster: res.employeeMaster.employeeName,
        dealerDepotMapping: res.dealerDepotMapping.depot,
        conditionFailureMaster: res.conditionFailureMaster.failureDesc,
        failureType: res.failureType.failureType,
        status: res.status,
        complaint: res.complaint,
        remarks: res.remarks,
        failureDate: res.failureDate

      })
      this.vendorRespomseForm.patchValue({
        responseFromVendor: res.vendorResponse
      })

      res.hotlineReportAttachments.forEach((element: any) => {
        if (element.attachStatus === 'Answered') {
          this.answerPhoto.push(element)
        } else if (element.attachStatus === 'Submitted') {
          this.submitPhoto.push(element)
        }
      })
      this.collectMaterialData(res.hotlineReportMachineDetails);
      this.collectPartDetails(res.hotlineReportPartsDetails);


    })

  }
  resetAllForm() {
    this.hotlineReportForm.reset();
    this.materialDetailForm.reset()
    this.partDetailForm.reset();
    this.vendorRespomseForm.reset()
    this.getSystemDate()

  }
  private getSystemDate() {
    this.retroFitment.getSystemDateUrl().subscribe(res => {
      this.date = res;
      this.hotlineReportForm.get('hotlineDate').patchValue(this.date)

    })
  }
  private collectMaterialData(details: warrantyHotlineMachineReport[]) {
    const materialDetail = {} as MaterialData;
    details.forEach(elt => {

      materialDetail.id = elt.id
      materialDetail.machineVinMaster = elt.machineVinMaster.chassisNo ? elt.machineVinMaster.chassisNo : null;
      materialDetail.vendorCode = elt.vendorCode ? elt.vendorCode : null;
      materialDetail.vendorName = elt.vendorName ? elt.vendorName : null;
      materialDetail.vendorInvoiceDate = elt.vendorInvoiceDate ? elt.vendorInvoiceDate : null;
      materialDetail.itemDescription = elt.machineVinMaster['machineMaster'].itemDescription ? elt.machineVinMaster['machineMaster'].itemDescription : null;
      materialDetail.containerNo = elt.containerNo ? elt.containerNo : null;
      this.hotlineReportPagePresenter.addRowInMaterialDetail(materialDetail);
    })
  }
  private collectPartDetails(partDetails: warrantyHotlinePartReport[]) {
    const partDetail = {} as PartData;
    partDetails.forEach(eltData => {
      partDetail.id = eltData.id ? eltData.id : null;
      partDetail.sparePartMaster = eltData.sparePartMaster.itemNo ? eltData.sparePartMaster.itemNo : null;
      partDetail.partDescription = eltData.sparePartMaster.itemDescription ? eltData.sparePartMaster.itemDescription : null;
      partDetail.quantity = eltData.quantity ? eltData.quantity : null;
      partDetail.isClaimmable = eltData.isClaimmable ? eltData.isClaimmable : null

      this.hotlineReportPagePresenter.addRowInPartDetail(partDetail);

    })
  }
  submitHotlinsheet(btnType: any) {
    // console.log(btnType)
    btnType == 'Submitted' ? this.btnName = 'Submitted' : this.btnName = 'Answered';
    let flag: boolean = false;

    if (this.hotlineReportForm.invalid) {
      this.hotlineReportForm.markAllAsTouched()
      this.toaster.error("Please Fill All Mandatory Field")
      return false;
    }
    this.materialDetailForm.controls.forEach((ele: any) => {
      // console.log(ele)
      if (ele.status == "INVALID") {
        // this.IssueForm.get('indentNumber')?.setErrors({ customVal: true });
        ele.markAllAsTouched()
        this.toaster.error('Please fill all required field!')
        flag = true
        return false;
      }
    })
    this.partDetailForm.controls.forEach((ele: any) => {

      if (ele.status == "INVALID") {
        ele.markAllAsTouched()
        this.toaster.error('Please fill all required field!')
        flag = true
        return false;
      }
    })
    if (flag) {
      return false;
    }
    if (this.btnName === 'Answered') {
     
      if (this.vendorRespomseForm.invalid) {
        this.hotlineReportForm.markAllAsTouched()
        this.toaster.error("Please Fill Vendor Response Form")
        return false;
      }
      
     
    }
    this.openConfirmDialog();

  }
  private openConfirmDialog() {
    let message = null;

    if (this.btnName === "Submitted")
      message = `Do you want to Submit?`;
    else
      message = `Do you want to Answer?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {

        this.submitHotlineReport()

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

  private submitHotlineReport() {

    let finalObject;
    const submitHTLForm = {} as submitHTR;

    submitHTLForm.warrantyHotlineReport = this.hotlineReportForm.value
    if (this.btnName == 'Submitted') {
      submitHTLForm.warrantyHotlineReport.status = 'Submitted'
      // finalObject={...submitHTLForm}
      finalObject = submitHTLForm
    } else {

      // console.log(this.vendorRespomseForm.value.responseFromVendor,'dsfsfsfs')
      // console.log(this.vendorRespomseForm.value);
      submitHTLForm.warrantyHotlineReport.status = 'Answered';
      finalObject = {
        vendorResponseTemp: this.vendorRespomseForm.value.responseFromVendor,
        ...this.allDataAnswer,
        image: this.hotlineReportPagePresenter.collectFiles
      }
    }
    submitHTLForm.warrantyHotlineReport.failureDate = this.convertDate(this.hotlineReportForm.value.failureDate);
    submitHTLForm.multipartFileList = this.hotlineReportPagePresenter.collectFiles;
    submitHTLForm.warrantyHotlineReport.hotlineReportMachineDetails = this.materialDetailForm.getRawValue();
    submitHTLForm.warrantyHotlineReport.hotlineReportPartsDetails = this.partDetailForm.getRawValue();
    if (this.btnName == 'Answered' && submitHTLForm.multipartFileList === undefined || submitHTLForm.multipartFileList === null) {
      this.toaster.error('Please attach document for vendor response', 'Mandatory')
      return;
    }
    this.service.saveHotlineReport(finalObject, this.btnName).subscribe(res => {
      if (res['status'] === 200) {
        this.toaster.success(res['message'], 'Success')
        this.router.navigate(['..'], { relativeTo: this.activatedRoute });
      } else if (res['status'] === 409) {
        this.toaster.error(res['message'], "Error");
        return false
      }
    }, err => {
      this.toaster.error('Error Occured', 'Error');
    });
  }
  convertDate(date: Date) {
    if (typeof date == 'object') {
      return `${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}`;
    }
    return date;
  }

  viewPdf() {
    this.printStatus = 'true';
    this.service.viewPdf(this.id, this.hotlineNo, this.printStatus).subscribe((resp: HttpResponse<Blob>) => {

      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })


  }

}
