import { Component, OnInit } from '@angular/core';
import { GoodwillPageStore } from './goodwill-create-page.store';
import { GoodwillPagePresenter } from './goodwill-create-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';
import { JobCardView, ViewPcr, WarrantyPcrPhotos, WarrantyPart } from '../../../product-concern-report/domain/product-concern-report.domain';
import { SparePartRequisitionItem, SubmitGoodwill, Goodwill, Warranty, GoodwillViewMain, LabourList, OutsideChargeList } from '../../domain/goodwill.domain';
import { GoodwillCreatePageWebService } from './goodwill-create-page-web.service';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { WarrantyPartGoodwill, ApproveQuantity, ApprovalParts, ApprovalLabourORCharges } from '../../domain/goodwill.domain'

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';


@Component({
  selector: 'app-goodwill-create-page',
  templateUrl: './goodwill-create-page.component.html',
  styleUrls: ['./goodwill-create-page.component.scss'],
  providers: [GoodwillPageStore, GoodwillPagePresenter, PcrPageWebService, GoodwillCreatePageWebService]
})
export class GoodwillCreatePageComponent implements OnInit {
  isApprove: boolean
  viewName: string
  rejectAllow: boolean = true
  holdAllow: boolean = true
  approveAllow: boolean = true
  goodwillForm: FormGroup;
  implementForm: FormArray;
  outsideChargeForm: FormArray;
  labourChargeForm: FormArray;
  serviceHistoryForm: FormArray;
  approveQuantity = {} as ApproveQuantity;
  approvalHierDetails: any[]
  concernForm: FormGroup;
  failurePartsForm: FormArray;
  remarkForm: FormGroup;
  viewPCRDetails: ViewPcr;
  machineMasterId: number;
  warrantyPcrPhotos: WarrantyPcrPhotos[];
  btnName: string;
  goodwillView: GoodwillViewMain;
  operation: string;
  isView: boolean = false
  viewWcr: boolean = false
  showWcrBtn: boolean = false
  isSubmitDisable: boolean = false
  constructor(
    private goodwillPagePresenter: GoodwillPagePresenter,
    private activatedRoute: ActivatedRoute,
    private pcrPageWebService: PcrPageWebService,
    private goodwillCreatePageWebService: GoodwillCreatePageWebService,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {
    this.goodwillForm = this.goodwillPagePresenter.goodwillForm;
    this.implementForm = this.goodwillPagePresenter.implementForm;
    this.serviceHistoryForm = this.goodwillPagePresenter.serviceHistoryForm;
    this.concernForm = this.goodwillPagePresenter.concernForm;
    this.failurePartsForm = this.goodwillPagePresenter.failurePartsForm;
    this.remarkForm = this.goodwillPagePresenter.remarkForm;
    this.outsideChargeForm = this.goodwillPagePresenter.OutsideLabourChargeForm;
    this.labourChargeForm = this.goodwillPagePresenter.LabourChargeForm;
    this.activeRoute();
  }
  private activeRoute() {
    this.goodwillPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    this.operation = this.goodwillPagePresenter.operation;
    this.activatedRoute.queryParams.subscribe(qParams => {

      this.viewName = qParams.name;
      if (this.viewName === 'approve') {
        this.isApprove = true;
      }
      if (this.goodwillPagePresenter.operation == Operation.CREATE) {
        this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
          this.goodwillForm.get('goodwillDate').patchValue(res);
        });
        this.warrantyPcrView(atob(qParams.pcrNo));
      }
      else if (this.goodwillPagePresenter.operation == Operation.VIEW) {
        this.warrantyGoodwillView(atob(qParams.goodwillNo));
        this.goodwillPagePresenter.disableForm(this.viewName);
        this.isView = true;
      }
    })
  }

  private warrantyGoodwillView(goodwillNo: string) {
    this.goodwillCreatePageWebService.warrantyGoodwillView(goodwillNo).subscribe(
      res => {
        this.goodwillView = res;
        const serviceJobCard = this.goodwillView.goodwillViewDto.warrantyPcr.serviceJobCard;
        const machineInventory = serviceJobCard.machineInventory;
        const sparePartRequisitionItem = this.goodwillView.warrantyPart;
        const labourDetails = this.goodwillView.labourCharge;
        const outsideChargeDetails = this.goodwillView.outSideLabourCharge;
        this.approvalHierDetails = this.goodwillView.approvalDetails;

        const addFailurePart = {} as WarrantyPart;

        this.goodwillView.goodwillViewDto.warrantyPcr.warrantyPcrImplements.forEach(elt => {
          this.goodwillPagePresenter.addRowInImplement(elt);
        });

        this.warrantyPcrPhotos = this.goodwillView.goodwillViewDto.warrantyGoodwillPhoto;
        if (sparePartRequisitionItem) {
          sparePartRequisitionItem.forEach((elt: WarrantyPartGoodwill) => {
            addFailurePart.gwClaimApprovedQuantity = elt.gwClaimApprovedQuantity;
            addFailurePart.description = elt.description;
            addFailurePart.partNo = elt.partNo;
            addFailurePart.claimQuantity = elt.claimQuantity;
            addFailurePart.rejectedQuantity = elt.rejectedQuantity;
            addFailurePart.gwApprovedPercent = elt.gwApprovedPercent;
            addFailurePart.gwClaimQuantity = elt.gwClaimQuantity;
            addFailurePart.complaintDescription = elt.complaintDescription;
            addFailurePart.code = elt.code;
            addFailurePart.failureId = elt.failureId;
            addFailurePart.sparePartRequisitionId = elt.sparePartRequisitionId;
            addFailurePart.priceType = elt.priceType;
            addFailurePart.hodLogin = this.goodwillView.approvalDetails[0]['hodLogin'] == 'Y' ? true : false;
            this.goodwillPagePresenter.addRowInFailurePart(addFailurePart, this.viewName);
          })
        }
        if (labourDetails) {
          labourDetails.forEach(elt => {
            this.goodwillPagePresenter.addRowInLabourCharge(elt, this.viewName);
          })
        }
        if (outsideChargeDetails) {
          outsideChargeDetails.forEach(elt => {
            this.goodwillPagePresenter.addRowInOutsideCharge(elt, this.viewName);
          })
        }

        const goodwillDetails = this.goodwillPagePresenter.patchViewPCRForm(this.goodwillView.goodwillViewDto.warrantyPcr, this.goodwillView.goodwillViewDto);

        goodwillDetails.budgetConsumed = this.goodwillView.goodwillViewDto.budgetConsumed;

        this.serviceHistory(machineInventory.vinId, goodwillDetails);


        if (this.goodwillView.goodwillViewDto.status == 'Approved' && serviceJobCard.invoicedFlag && this.goodwillView.goodwillViewDto.createWcr) {
          this.showWcrBtn = true;
        }
        if (this.goodwillView.goodwillViewDto.wcrNo) {
          this.viewWcr = true
          this.showWcrBtn = false;
        }

      },
      err => {
        this.toastr.error('Error while fetching Goodwill details', 'Error');
      }
    )
  }

  private warrantyPcrView(pcrNo: string) {
    this.pcrPageWebService.warrantyPcrGoodwill(pcrNo).subscribe(
      res => {
        this.viewPCRDetails = res;
        const serviceJobCard = this.viewPCRDetails.warrantyPcrViewDto.serviceJobCard;
        const machineInventory = serviceJobCard.machineInventory;
        const sparePartRequisitionItem = this.viewPCRDetails.warrantyPart;
        const addFailurePart = {} as WarrantyPart;

        this.viewPCRDetails.warrantyPcrViewDto.warrantyPcrImplements.forEach(elt => {
          this.goodwillPagePresenter.addRowInImplement(elt);
        });
        this.implementForm.disable();
        this.machineMasterId = serviceJobCard.machineInventory.machineMaster.id;

        sparePartRequisitionItem.forEach((elt) => {
          addFailurePart.claimQuantity = elt.claimQuantity;
          addFailurePart.rejectedQuantity = elt.rejectedQuantity;
          addFailurePart.description = elt.description;
          addFailurePart.partNo = elt.partNo;
          addFailurePart.sparePartRequisitionId = elt.sparePartRequisitionId;
          addFailurePart.code = elt.code;
          addFailurePart.priceType = elt.priceType;
          addFailurePart.complaintDescription = elt.complaintDescription;
          addFailurePart.failureId = elt.failureId;
          this.goodwillPagePresenter.addRowInFailurePart(addFailurePart, this.viewName);
        })

        if (this.viewPCRDetails.outSideLabourCharge) {
          this.viewPCRDetails.outSideLabourCharge.forEach(elt => {
            this.goodwillPagePresenter.addRowInOutsideCharge(elt, this.viewName);
          })
        }
        if (this.viewPCRDetails.labourCharge) {
          this.viewPCRDetails.labourCharge.forEach(elt => {
            this.goodwillPagePresenter.addRowInLabourCharge(elt, this.viewName);
          })
        }

        this.warrantyPcrPhotos = this.viewPCRDetails.warrantyPcrViewDto.warrantyPcrPhotos;
        const viewPCRDetails = this.goodwillPagePresenter.patchViewPCRForm(this.viewPCRDetails.warrantyPcrViewDto);
        this.serviceHistory(machineInventory.vinId, viewPCRDetails);

      },
      err => {
        this.toastr.error('Error while fetching PCR details', 'Error');
      }
    );
  }
  private serviceHistory(id: number, pcrData?: JobCardView) {
    this.pcrPageWebService.serviceHistory(id).subscribe(serviceHistoryResponse => {
      const serviceHistoryDetails = serviceHistoryResponse;
      if (serviceHistoryDetails.length > 0) {
        serviceHistoryDetails.forEach(elt => {
          this.goodwillPagePresenter.addRowInServiceHistory(elt);
        })
      }
      this.goodwillPagePresenter.patchFormVal({ ...pcrData, ...{ jobCardHistory: serviceHistoryDetails } });
    }, err => {
      this.toastr.error('Error while fetching service history details', 'Error');
    });
  }

  collectSparePartRequisitionItem() {
    const sparePartRequisitionItem: SparePartRequisitionItem[] = [];
    const parts = this.failurePartsForm.getRawValue();
    if (parts) {
      parts.forEach(elt => {
        sparePartRequisitionItem.push({
          id: elt.id,
          gwClaimQuantity: elt.goodwillClaimQuantity,
          priceType: elt.priceType
        });
      });
    }
    return sparePartRequisitionItem;
  }

  private collectLabourChargeList() {
    const labourList: LabourList[] = [];
    const labours = this.labourChargeForm.getRawValue();
    if (labours) {
      labours.forEach((elt) => {
        labourList.push({
          labourChargeId: elt.labourChargeId,
          goodwillAmount: elt.goodwillClaimAmount
        });
      })
    }
    return labourList;
  }
  private collectOutsideChargeList() {
    const outsideChargeList: OutsideChargeList[] = [];
    const charges = this.outsideChargeForm.getRawValue();
    if (charges) {
      charges.forEach((elt) => {
        outsideChargeList.push({
          jobcodeId: elt.jobcodeId,
          goodwillAmount: elt.goodwillClaimAmount
        });
      })
    }
    return outsideChargeList;
  }

  submitGoodwill() {
    const submitGoodwill = {} as SubmitGoodwill;
    const goodwill = {} as Goodwill;
    goodwill.warrantyPcr = {} as Warranty;
    goodwill.dealerRemark = this.remarkForm.get('dealerRemarks').value;
    goodwill.kaiRemark = this.remarkForm.get('kaiRemarks').value;
    goodwill.goodwillReason = this.concernForm.get('goodwillReason').value;
    goodwill.goodwillType = this.goodwillForm.get('goodwillType').value;
    goodwill.sparePartRequisitionItem = this.collectSparePartRequisitionItem();
    goodwill.labourCharge = this.collectLabourChargeList();
    goodwill.outsideJobCharge = this.collectOutsideChargeList();
    goodwill.warrantyPcr.id = this.viewPCRDetails.warrantyPcrViewDto.id;
    goodwill.draftFlag = this.btnName == 'save' ? true : false;
    goodwill.budgetConsumed = this.goodwillForm.get('budgetConsumed').value;

    if (this.goodwillPagePresenter.collectFiles) {
      submitGoodwill.multipartFileList = this.goodwillPagePresenter.collectFiles;
    }
    submitGoodwill.warrantyGoodwill = goodwill;

    this.goodwillCreatePageWebService.saveGoodwill(submitGoodwill).subscribe(
      res => {
        if (res) {
          this.toastr.success(res['message'], 'Success')
          this.router.navigate(['..'], { relativeTo: this.activatedRoute });
        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving', 'Transaction failed');
        }
      },
      err => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    );
  }

  formValid(btnType: string) {
    this.btnName = btnType;
    if (this.viewName === 'approve') {

      if (this.remarkForm.valid && this.failurePartsForm.valid && this.labourChargeForm.valid && this.outsideChargeForm.valid) {
        this.openConfirmDialog();
      }
      else {
        this.markFormAsTouched();
      }

    } else {

      if (this.goodwillForm.valid && this.remarkForm.valid && this.concernForm.valid && this.failurePartsForm.valid && (this.implementForm.valid || this.implementForm.disabled)
        && this.labourChargeForm.valid && this.outsideChargeForm.valid) {
        this.openConfirmDialog();
      }
      else {
        this.markFormAsTouched();
      }
    }

  }

  markFormAsTouched() {
    this.goodwillForm.markAllAsTouched();
    this.implementForm.markAllAsTouched();
    this.failurePartsForm.markAllAsTouched();
    this.labourChargeForm.markAllAsTouched();
    this.outsideChargeForm.markAllAsTouched();
    this.concernForm.markAllAsTouched();
    this.remarkForm.markAllAsTouched();
    this.toastr.error('Please fill all mandatory fields', 'Error');
  }


  openConfirmDialog() {
    const message = `Do you want to ${this.btnName} Goodwill?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {
        this.isSubmitDisable = true;
        switch (this.btnName) {
          case 'submit':
            this.submitGoodwill();
            break;
          case 'approve':
            this.approve('Approve');
            break;
          case 'hold':
            this.approve('Hold');
            break;
          case 'reject':
            this.approve('Reject');
            break;
          default:
            console.log('No such operation exist.');
            this.isSubmitDisable = false;
            break;
        }
      }
    });
  }

  private approve(approvalStatus) {
    const approvalParts: ApprovalParts[] = [];
    const approvalLabours: ApprovalLabourORCharges[] = [];
    const approvalOutsideCharges: ApprovalLabourORCharges[] = [];

    this.approveQuantity.kaiRemarks = this.remarkForm.get('kaiRemarks').value;
    this.approveQuantity.warrantyGwlId = this.goodwillView.goodwillViewDto.id;
    if (this.failurePartsForm) {
      this.failurePartsForm.getRawValue().forEach(elt => {
        approvalParts.push({
          id: elt.id,
          gwApprovedPercent: elt.acceptedPercentage,
          approvedQuantity: elt.goodwillClaimAcceptedQuantity,
          priceType: elt.priceType
        });
      })
      this.approveQuantity.approvalParts = approvalParts;
    }
    if (this.outsideChargeForm) {
      this.outsideChargeForm.getRawValue().forEach(elt => {
        approvalOutsideCharges.push({
          id: elt.jobcodeId,
          approvedAmount: elt.goodwillApprovedAmount
        });
      })
      this.approveQuantity.approvalOutsideCharges = approvalOutsideCharges;
    }

    if (this.labourChargeForm) {
      this.labourChargeForm.getRawValue().forEach(elt => {
        approvalLabours.push({
          id: elt.labourChargeId,
          approvedAmount: elt.goodwillApprovedAmount
        });
      })
      this.approveQuantity.approvalLabours = approvalLabours;
    }

    this.approveQuantity.budgetConsumed = this.goodwillForm.controls.budgetConsumed.value;
    this.approveQuantity.approvalStatus = approvalStatus;

    this.goodwillCreatePageWebService.approveWarrantyGoodwill(this.approveQuantity).subscribe(res => {
      if (res) {
        this.toastr.success(res['message'], 'Success')
        this.router.navigate(['..'], { relativeTo: this.activatedRoute });
      } else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving details', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving details', 'Transaction failed');
    })
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }
  navigateToWcr() {
    this.router.navigate(['../../warrenty-claim-request/create'], { relativeTo: this.activatedRoute, queryParams: { gwNo: this.goodwillView.goodwillViewDto.goodwillNo, id: this.goodwillView.goodwillViewDto.id } });
  }
  navigateTViewoWcr() {
    this.router.navigate(['../../warrenty-claim-request/view'], { relativeTo: this.activatedRoute, queryParams: { wcrNo: btoa(this.goodwillView.goodwillViewDto.wcrNo), name: 'goodwill' } });
  }

  viewPrint(printStatus: string) {
    this.goodwillCreatePageWebService.printGoodwill(this.goodwillForm.get('goodwillNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

}
