import { Component, OnInit } from '@angular/core';
import { LogSheetPageStore } from './log-sheet-page.store';
import { LogSheetPagePresenter } from './log-sheet-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { LogSheet, LogsheetFailurePartInfo, SparePartMaster, SubmitLogSheet, JobCardPart, SparePartRequisitionItem, LogsheetImplements, FailureCode } from '../../domain/log-sheet.domain';
import { LogSheetWebService } from '../log-sheet/log-sheet-web.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { LogSheetPageWebService } from './log-sheet-page-web.service';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { ObjectUtil } from 'src/app/utils/object-util';
@Component({
  selector: 'app-log-sheet-page',
  templateUrl: './log-sheet-page.component.html',
  styleUrls: ['./log-sheet-page.component.scss'],
  providers: [LogSheetPageStore, LogSheetPagePresenter, LogSheetWebService, LogSheetPageWebService, PcrPageWebService]
})
export class LogSheetPageComponent implements OnInit {
  logSheetForm: FormGroup;
  implementForm: FormArray;
  serviceHistoryForm: FormArray;
  failurePartForm: FormArray;
  implementFormLength: boolean;
  private btnName: string;
  private submitLogsheet = {} as SubmitLogSheet;
  isView = false;
  isEdit = false;
  machineInvetoryId: number;
  warrantyLogsheetPhotosList: WarrantyPcrPhotos;
  store: string;
  // secondStore: string;
  description: string;
  // code: any;
  // code2: string;
  description2: string;
  spareFailureDescription: FailureCode;
  // gg: any;
  againstJobNumber: string;
  getItemNo: string;
  getFailureDescription: any;
  failurecode: string;
  getPartNumber: any;
  constructor(
    private logSheetPagePresenter: LogSheetPagePresenter,
    private logSheetService: LogSheetWebService,
    private logSheetPageWebService: LogSheetPageWebService,
    private pcrService: PcrPageWebService,
    private tostr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {

    this.logSheetForm = this.logSheetPagePresenter.LogSheetForm;
    // console.log(this.logSheetForm,'form')
    this.implementForm = this.logSheetPagePresenter.ImplementForm;
    // console.log(this.implementForm,'imple')
    this.serviceHistoryForm = this.logSheetPagePresenter.ServiceHistoryForm;
    this.failurePartForm = this.logSheetPagePresenter.FailureForm;
    this.checkFormOperation();

  }
  updateVinId(event: any) {
    this.machineInvetoryId = event
  }
  private checkFormOperation() {
    this.logSheetPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.logSheetPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
      this.logSheetForm.disable();
      this.implementForm.disable();
      this.implementForm.valueChanges.subscribe(val => {
        if (val) {
          this.implementFormLength = this.implementForm.length > 0;
        }
      });
      this.activeRoute();
    }
    else if (this.logSheetPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;
      this.logSheetForm.controls.jobCardNo.disable();
      this.logSheetForm.controls.chassisNo.disable();
      this.logSheetForm.controls.logsheetType.disable();
      // this.implementForm.disable();
      this.implementForm.valueChanges.subscribe(val => {
        if (val) {
          this.implementFormLength = this.implementForm.length > 0;
        }
      });
      //this.logSheetForm.disable();
      //this.implementForm.disable();
      // this.implementForm.valueChanges.subscribe(val => {
      //   if(val) {
      //     this.implementFormLength = this.implementForm.length > 0;
      //   }
      // });
      this.activeRoute();
    }
    else if (this.logSheetPagePresenter.operation == Operation.CREATE) {
      this.implementFormLength = this.implementForm.length == 0;
    }
  }

  private activeRoute() {
    this.activatedRoute.queryParamMap.subscribe((qParam) => {
      if (qParam.has('logsheetNo')) {
        this.warrantyLogsheetViewByLogsheetNo(qParam.get(qParam.keys[0]));
      }
    });
  }

  private warrantyLogsheetViewByLogsheetNo(logsheetNo: string) {
  
    this.logSheetPageWebService.warrantyLogsheetViewByLogsheetNo(logsheetNo)
      .subscribe(res => {
        if (res) {
        
          //const sparePartRequisitionItem = res.serviceJobCard == null ? res.logsheetFailurePartInfo : res.serviceJobCard.sparePartRequisitionItem;
          const sparePartRequisitionItem = res.logsheetFailurePartInfo;
          const logsheetImplements = res.logsheetImplements;
          this.machineInvetoryId = res.serviceJobCard == null ? res.machineInventory.vinId : res.serviceJobCard.machineInventory.vinId;
          this.collectFormDetails(sparePartRequisitionItem, logsheetImplements);
          this.warrantyLogsheetPhotosList = res.warrantyLogsheetPhotosList;
          const logsheetViewData = this.logSheetPagePresenter.patchViewLogsheetForm(res);
          this.serviceHistory(this.machineInvetoryId, logsheetViewData);
        }
      }, err => {
        console.log('err', err);
      });
  }

  private collectFormDetails(sparePartRequisitionItem: SparePartRequisitionItem[], logsheetImplements: LogsheetImplements[]) {
    const addFailurePart = {} as JobCardPart;
    sparePartRequisitionItem.forEach(elt => {  
      addFailurePart.id = elt.id;
      addFailurePart.claimQuantity = elt.quantity ? elt.quantity : elt.reqQuantity;
      addFailurePart.failureDescription = elt.failureDescription;
      addFailurePart.keyPartNumber = elt.keyPartNumber ? elt.keyPartNumber : elt.keyPartNumber;
      addFailurePart.failureCode = elt.failureCode ? elt.failureCode : elt.failureCode
      addFailurePart.failureDescription = elt.failureDescription ? elt.failureDescription : elt.failureDescription
      addFailurePart.partNo = elt.sparePartMaster.itemNo;
      addFailurePart.sparePartMasterId = elt.sparePartMaster.itemNo;
      addFailurePart.description = elt.sparePartMaster.itemDescription;
      addFailurePart.sparePartMasterId = this.getPartNumber;
      this.logSheetPagePresenter.addRowInFailurePart(addFailurePart);
    });

    logsheetImplements.forEach(elt => {
      this.logSheetPagePresenter.addRowInImplement(elt);
    });

  }

  private serviceHistory(id: number, logsheetData?: LogSheet) {
    this.pcrService.serviceHistory(id).subscribe(serviceHistryResponse => {
      const serviceHistoryDetails = serviceHistryResponse;
      if (serviceHistoryDetails.length > 0) {
        serviceHistoryDetails.forEach(elt => {
          this.logSheetPagePresenter.addRowInServiceHistory(elt);
        })
      }
      this.logSheetPagePresenter.patchFormVal({ ...logsheetData, ...{ jobCardHistory: serviceHistoryDetails } });
    }, err => {
      console.log('err', err);
    });
  }

  formValid(btnType: string) {
    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'save';
    if (this.logSheetForm.valid) {
      if (btnType == 'submit' && this.logSheetPagePresenter.collectFiles.length == 0) {
        this.tostr.error('Please attach document', 'Mandatory')
        return;
      }
      this.openConfirmDialog();
    }
    else {
      this.markFormAsTouched();
    }
  }

  private markFormAsTouched() {

    this.logSheetForm.markAllAsTouched();
    this.implementForm.markAllAsTouched();
    this.serviceHistoryForm.markAllAsTouched();
    this.failurePartForm.markAllAsTouched();
    this.tostr.error('Please fill all mandatory fields', 'Error');
  }

  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Logsheet?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {
        //if(this.btnName == 'submit') {
        this.submitLogSheetForm();
        // } 
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

  private submitLogSheetForm() {

    this.submitLogsheet.warrantyLogsheet = this.collectSubmitData();
    if (this.logSheetPagePresenter.collectFiles) {
      this.submitLogsheet.multipartFileList = this.logSheetPagePresenter.collectFiles;
    }
    // console.log('this.submitLogsheet', this.submitLogsheet);
    this.logSheetService.saveWarrantyLogsheet(this.submitLogsheet).subscribe(res => {
      this.tostr.success(res['message'], 'Success');
      this.router.navigate(['..'], { relativeTo: this.activatedRoute });
    }, err => {
      console.log('err', err);
      this.tostr.error('Error occured while submitting form', 'Error');
    });
  }
  private collectSubmitData() {
 
    // console.log(this.logSheetForm.getRawValue());
    const submitLogSheet: LogSheet = this.logSheetForm.getRawValue();
    console.log(submitLogSheet,'submitLogsheet')
    const logsheetFailurePartInfo: LogsheetFailurePartInfo[] = [];
    let sparePartMaster = {} as SparePartMaster;
    if (this.btnName == 'save')
      submitLogSheet.draftFlag = true;
    else
      submitLogSheet.draftFlag = false;
    if (submitLogSheet.logsheetNo) { } else {
      delete submitLogSheet.logsheetNo;
    }


    if (submitLogSheet.logsheetType == 'Manual Entry') {
      submitLogSheet.machineInventory = { vinId: this.isEdit ? this.machineInvetoryId : this.logSheetForm.get('chassisNo').value.machineInventoryId };
      if (this.logSheetForm.get('linkjobCardNo').value) {
        submitLogSheet.serviceJobCard = { id: this.logSheetForm.get('linkjobCardNo').value.id };
      }
    }
    else if (submitLogSheet.logsheetType == 'Against Job Card') {
      submitLogSheet.machineInventory = { vinId: this.isEdit ? this.machineInvetoryId : this.machineInvetoryId };
      submitLogSheet.serviceJobCard = { id: this.logSheetForm.get('jobCardNo').value.id };
    }



    submitLogSheet.failureDate = ObjectUtil.getDateIntoDDMMYYYY(submitLogSheet.failureDate);
    this.failurePartForm.value.forEach((elt: JobCardPart) => {
      if (submitLogSheet.logsheetType == 'Manual Entry') {

        //For Creating New Logsheet
        if (this.isEdit) {
          let spareItemNo = elt.partNo as SparePartMaster;
          this.spareFailureDescription = elt.failureCode.failureDescription as FailureCode
          let spareFailureCode = elt.failureCode as FailureCode;

          if (typeof (elt.partNo) === "object" || (elt.failureCode) === "object" ||
            (elt.failureCode.failureDescription === "object")) {
            sparePartMaster.id = spareItemNo.id;
            sparePartMaster.itemNo = spareItemNo.value;
            sparePartMaster.failureCode = spareFailureCode.failureCode;
            sparePartMaster.failureDescription = this.spareFailureDescription;
          }
          else {
            sparePartMaster.id = elt.id;
            sparePartMaster.itemNo = elt.itemNo;
            sparePartMaster.failureCode = elt.failureCode;
            sparePartMaster.failureDescription = elt.failureDescriptions

          }
          this.getItemNo = sparePartMaster.itemNo
          this.getFailureDescription = sparePartMaster.failureDescription;
          this.failurecode = sparePartMaster.failureCode

          logsheetFailurePartInfo.push({
            id: elt.id,
            quantity: elt.claimQuantity,
            failureCode: this.failurecode,
            keyPartNumber: elt.keyPartNumber,
            failureDescription: this.getFailureDescription,
            sparePartMasterId: this.getItemNo,
            sparePartMaster: sparePartMaster,

          });
        }

        //For Updating Logsheet
        if (!this.isEdit) {
          sparePartMaster = elt.partNo as SparePartMaster;
          this.getPartNumber = sparePartMaster.value
          delete sparePartMaster.itemNo;
          delete sparePartMaster.value;
          delete Object.assign(sparePartMaster, { ['itemNo']: sparePartMaster['partNumber'] })['partNumber'];

          if (submitLogSheet.logsheetType == 'Manual Entry' && this.isEdit) {
            let spareItemNo = elt.partNo as SparePartMaster;
            this.spareFailureDescription = elt.failureCode.failureDescription as FailureCode
            let spareFailureCode = elt.failureCode as FailureCode;

            if (typeof (elt.partNo) === "object" || (elt.failureCode) === "object" ||
              (elt.failureCode.failureDescription === "object")) {
              sparePartMaster.id = spareItemNo.id;
              sparePartMaster.itemNo = spareItemNo.value;
              sparePartMaster.failureCode = spareFailureCode.failureCode;
              sparePartMaster.failureDescription = this.spareFailureDescription;
            }
            else {
              sparePartMaster.id = elt.id;
              sparePartMaster.itemNo = elt.itemNo;
              sparePartMaster.failureCode = elt.failureCode;
              sparePartMaster.failureDescription = elt.failureDescriptions

            }
            this.getItemNo = sparePartMaster.itemNo
            this.getFailureDescription = sparePartMaster.failureDescription;
            this.failurecode = sparePartMaster.failureCode
          }

          logsheetFailurePartInfo.push({
            id: elt.id,
            quantity: elt.claimQuantity,
            failureCode: elt.failureCode.failureCode,
            keyPartNumber: elt.keyPartNumber,
            failureDescription: elt.failureCode.failureDescription,
            sparePartMasterId: this.getPartNumber,
            sparePartMaster: sparePartMaster
          });
        }

      }

      if (submitLogSheet.logsheetType == 'Against Job Card') {
        sparePartMaster.id = elt.id;
        sparePartMaster.itemNo = elt.itemNo;
        this.againstJobNumber = sparePartMaster.itemNo;

        // console.log(spareItemNo,'spareItemNo')
        //For Creating New Logsheet
        if (!this.isEdit) {
          let spareItemNo = elt.partNo as SparePartMaster;
          this.spareFailureDescription = elt.failureCode.failureDescription as FailureCode
          let spareFailureCode = elt.failureCode as FailureCode;
          
          if (typeof (elt.partNo) === "object" || (elt.failureCode) === "object" ||
            (elt.failureCode.failureDescription === "object")) {
            sparePartMaster.id = spareItemNo.id;
            sparePartMaster.itemNo = spareItemNo.value;
            sparePartMaster.failureCode = spareFailureCode.failureCode;
            sparePartMaster.failureDescription = this.spareFailureDescription;
          }
          else {
            sparePartMaster.id = elt.id;
            sparePartMaster.itemNo = elt.itemNo;
            sparePartMaster.failureCode = elt.failureCode;
            sparePartMaster.failureDescription = elt.failureDescriptions

          }
          this.getItemNo = sparePartMaster.itemNo
          this.getFailureDescription = sparePartMaster.failureDescription;
          this.failurecode = sparePartMaster.failureCode

          //Updating Data:::::::::::::::::::::::::::::::::::::::
          logsheetFailurePartInfo.push({
            id: elt.id,
            quantity: elt.claimQuantity,
            // add failure code,keypart Numnber,and description
            failureCode: elt.failureCode.failureCode,
            keyPartNumber: elt.keyPartNumber,
            failureDescription: elt.failureCode.failureDescription,
            sparePartMasterId: this.getItemNo,
            sparePartMaster: sparePartMaster
          });

        }

        //For Updating Logsheet
        if (this.isEdit) {
          let spareItemNo = elt.partNo as SparePartMaster;
          this.spareFailureDescription = elt.failureCode.failureDescription as FailureCode
          let spareFailureCode = elt.failureCode as FailureCode;

          if (typeof (elt.partNo) === "object" || (elt.failureCode) === "object" ||
            (elt.failureCode.failureDescription === "object")) {
            sparePartMaster.id = spareItemNo.id;
            sparePartMaster.itemNo = spareItemNo.value;
            sparePartMaster.failureCode = spareFailureCode.failureCode;
            sparePartMaster.failureDescription = this.spareFailureDescription;
          }
          else {
            sparePartMaster.id = elt.id;
            sparePartMaster.itemNo = elt.itemNo;
            sparePartMaster.failureCode = elt.failureCode;
            sparePartMaster.failureDescription = elt.failureDescriptions

          }
          this.getItemNo = sparePartMaster.itemNo
          this.getFailureDescription = sparePartMaster.failureDescription;
          this.failurecode = sparePartMaster.failureCode

          logsheetFailurePartInfo.push({
            id: elt.id,
            quantity: elt.claimQuantity,
            failureCode: this.failurecode,
            keyPartNumber: elt.keyPartNumber,
            failureDescription: this.getFailureDescription,
            sparePartMasterId: this.getItemNo,
            sparePartMaster: sparePartMaster,

          });

        }

      }
    })


    submitLogSheet.logsheetImplements = this.implementForm.value;
    submitLogSheet.logsheetFailurePartInfo = logsheetFailurePartInfo;
    return submitLogSheet;
  }



}
