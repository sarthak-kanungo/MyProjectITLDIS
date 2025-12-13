import { Component, OnInit } from '@angular/core';
import { RfcPagePresenter } from './retro-fitment-campaign-create-page.presenter';
import { RfcPageStore } from './retro-fitment-campaign-create-page.store';
import { FormGroup, FormArray } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { SubmitRfc, WarrantyRetrofitmentCampaignItem, MaterialDetails, LabourDetails, warrantyRetrofitmentJcLabourChargeInfo } from '../../domain/retro-fitment-campaign.domain';
import { RetroFitmentCampaignCreatePageWebService } from './retro-fitment-campaign-create-page-web.service';
import { Router, ActivatedRoute } from '@angular/router';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-retro-fitment-campaign-create-page',
  templateUrl: './retro-fitment-campaign-create-page.component.html',
  styleUrls: ['./retro-fitment-campaign-create-page.component.scss'],
  providers: [RfcPageStore, RfcPagePresenter, RetroFitmentCampaignCreatePageWebService]
})
export class RetroFitmentCampaignCreatePageComponent implements OnInit {

  rfcForm: FormGroup;
  materialDetailsForm: FormArray;
  labourChargesForm: FormArray;
  btnName: string;
  isView = false;
  warrantyRetrofitmentCampaignPhoto: WarrantyPcrPhotos;
  warrantyPcrPhotos: WarrantyPcrPhotos;
  materialDetailsFormLength: boolean;
  labourChargeFormLength: boolean
  date: string;
  printStatus: any
  isEdit:boolean
  id: any;
  retrofitmentNo: any;
  constructor(
    private rfcPagePresenter: RfcPagePresenter,
    private tostr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private retroFitmentCampaignCreatePageWebService: RetroFitmentCampaignCreatePageWebService,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.rfcForm = this.rfcPagePresenter.RFCForm;
    this.materialDetailsForm = this.rfcPagePresenter.MaterialDetailsForm;
    this.labourChargesForm = this.rfcPagePresenter.LabourChargesForm;
    this.checkFormOperation();
    this.getSystemDate();
  }


  private checkFormOperation() {
    this.rfcPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.rfcPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
      this.rfcForm.disable();
      this.materialDetailsForm.disable();
      this.labourChargesForm.disable();
      this.materialDetailsForm.valueChanges.subscribe(val => {
        if (val) {
          this.materialDetailsFormLength = this.materialDetailsForm.length > 0;
        }
      });
      this.labourChargesForm.valueChanges.subscribe(vals => {
        if (vals) {
          this.labourChargeFormLength = this.labourChargesForm.length > 0;
        }
      });
      this.activeRoute();
    }
    else if (this.rfcPagePresenter.operation == Operation.CREATE) {
      this.materialDetailsFormLength = this.materialDetailsForm.length == 0;
      this.labourChargeFormLength = this.labourChargesForm.length == 0;
    }
   
  }
  private getSystemDate() {
    this.retroFitmentCampaignCreatePageWebService.getSystemDateUrl().subscribe(res => {
      this.date = res;
      this.rfcForm.get('retrofitmentDate').patchValue(this.date)

    })
  }

  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      this.viewRetrofitmentCampaign(qParam.retrofitmentNo);
    });
  }

  private viewRetrofitmentCampaign(retrofitmentNo: string) {
    this.retroFitmentCampaignCreatePageWebService.viewRetrofitmentCampaign(retrofitmentNo).subscribe(res => {
      // console.log(res,'resss')
      this.id=res['id'],
      this.retrofitmentNo=res['retrofitmentNo'],
      this.warrantyPcrPhotos = res.warrantyRetrofitmentCampaignPhoto;
      this.collectMaterialDetails(res.warrantyRetrofitmentCampaignItemList);
      this.collectLabourDetails(res.warrantyRetrofitmentJcLabourChargeInfoList)
      this.rfcPagePresenter.patchFormVal(res);

    }, err => {
      console.log('err', err)
    });
  }

  private collectMaterialDetails(details: WarrantyRetrofitmentCampaignItem[]) {
    const addMaterial = {} as MaterialDetails;
    details.forEach(elt => {
       addMaterial.sparePartMaster = elt.sparePartMaster.itemNo;
      addMaterial.description = elt.sparePartMaster.itemDescription;
      addMaterial.quantity = elt.quantity;
      this.rfcPagePresenter.addRowInMaterialDetails(addMaterial);
    })
  }
  private collectLabourDetails(labourDetailss: warrantyRetrofitmentJcLabourChargeInfo[]) {
    const labourDetails = {} as LabourDetails;
    labourDetailss.forEach(elt => {
      labourDetails.serviceMtJobcode = elt.serviceMtJobcode['jobCodeNo'];
      labourDetails.description = elt.serviceMtJobcode.description;
      labourDetails.hours = elt.hours;
      labourDetails.claimAmount = elt.claimAmount
      this.rfcPagePresenter.addRowInLabourCharges(labourDetails);
    })
  }


  formValid(btnType: string) {
    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'approve';

    if (this.rfcForm.valid && this.materialDetailsForm.valid && this.labourChargesForm.valid) {
      this.openConfirmDialog();
    }
    else {
      this.markFormAsTouched();
    }
  }

  private markFormAsTouched() {
    this.rfcForm.markAllAsTouched();
    this.materialDetailsForm.markAllAsTouched();
    this.labourChargesForm.markAllAsTouched();
    this.tostr.error('Please fill all mandatory fields', 'Error');
  }

  private openConfirmDialog() {
    const message = `Do you want to ${this.btnName} Retro Fitment Campaign?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {
        if (this.btnName == 'submit') {
          this.submitRFCForm();
        }
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

  private submitRFCForm() {
    const submitRfcForm = {} as SubmitRfc;
    submitRfcForm.warrantyRetrofitmentCampaign = this.rfcForm.value;
    submitRfcForm.warrantyRetrofitmentCampaign.startDate = this.convertDate(this.rfcForm.value.startDate);
    submitRfcForm.warrantyRetrofitmentCampaign.endDate = this.convertDate(this.rfcForm.value.endDate);
    submitRfcForm.multipartFileList = this.rfcPagePresenter.collectFiles;
    submitRfcForm.multipartFile = this.rfcPagePresenter.chassisFile;
    if (submitRfcForm.multipartFile === undefined || submitRfcForm.multipartFile === null) {
      this.tostr.error("Please Upload Excel file")
      return false
    }
    const listArray = (this.materialDetailsForm.getRawValue())
    listArray.forEach(res => {
      delete res.sparePartMaster.itemNo

    })
    const labourForm = (this.labourChargesForm.getRawValue())
    labourForm.forEach(res => {
      delete res.serviceMtJobcode.value
    })
    submitRfcForm.warrantyRetrofitmentCampaign.warrantyRetrofitmentCampaignItemList = this.materialDetailsForm.getRawValue();
    submitRfcForm.warrantyRetrofitmentCampaign.warrantyRetrofitmentJcLabourChargeInfoList = this.labourChargesForm.getRawValue();

    this.retroFitmentCampaignCreatePageWebService.saveRetrofitmentCampaign(submitRfcForm).subscribe(res => {
      if (res['status'] === 200) {
        this.tostr.success(res['message'], 'Success')
        this.router.navigate(['..'], { relativeTo: this.activatedRoute });
      } else if (res['status'] === 409) {
        this.tostr.error(res['message'], "Error");
        return false
      }
    }, err => {
      this.tostr.error('Error Occured', 'Error');
    });
  }

  convertDate(date: Date) {
    if (typeof date == 'object') {
      return `${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}`;
    }
    return date;
  }
  clear() {
    this.rfcForm.reset()
    this.materialDetailsForm.reset();
    this.labourChargesForm.reset()
    this.getSystemDate()
    this.rfcPagePresenter.LabourChargesForm.clear();
    this.rfcPagePresenter.MaterialDetailsForm.clear();
    this.rfcForm.get('chassisNo').reset;
  //  this.rfcPagePresenter.addRowInMaterialDetails(null)
  }
  printPdf() {
    this.printStatus = 'true';
    this.retroFitmentCampaignCreatePageWebService.printReport(this.id, this.retrofitmentNo, this.printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
      })

  }



}
