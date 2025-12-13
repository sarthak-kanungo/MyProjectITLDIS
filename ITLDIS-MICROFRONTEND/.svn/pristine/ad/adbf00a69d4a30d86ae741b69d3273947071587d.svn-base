import { Component, OnInit } from "@angular/core";
import { WcrPageStore } from "./warrenty-claim-request-create-page.store";
import { WcrPagePresenter } from "./warrenty-claim-request-create-page.presenter";
import { FormGroup, FormArray } from "@angular/forms";
import { WarrentyClaimRequestCreatePageService } from "./warrenty-claim-request-create-page.service";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { IFrameService, IFrameMessageSource } from '../../../../../root-service/iFrame.service'
import {
  FailurePart,
  Wcr,
  SparePartRequisitionItem,
  LabourChargeSubmit,
  OutSideChargeSubmit,
  WcrSubmit,
  WcrDomain,
  WarrantyPart
} from "../../domain/warrenty-claim-request.domain";
import {
  ConfirmationBoxComponent,
  ConfirmDialogData
} from "../../../../../confirmation-box/confirmation-box.component";
import { MatDialog } from "@angular/material";
import { OperationsUtil, Operation } from "../../../../../utils/operation-util";
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: "app-warrenty-claim-request-create-page",
  templateUrl: "./warrenty-claim-request-create-page.component.html",
  styleUrls: ["./warrenty-claim-request-create-page.component.scss"],
  providers: [
    WcrPageStore,
    WcrPagePresenter,
    WarrentyClaimRequestCreatePageService,
    PcrPageWebService
  ]
})
export class WarrentyClaimRequestCreatePageComponent implements OnInit {
  wcrForm: FormGroup;
  implementForm: FormArray;
  serviceHistoryForm: FormArray;
  concernForm: FormGroup;
  isShowInspectionBtn:boolean=false;
  idFromView: number
  viewName: string
  requestType:string
  isCreateDealerPaidInvoice:boolean = false
  isCreateKaiInvoice:boolean = false
  approvalHierDetails:any
  failurePartsForm: FormArray;
  labourChargeForm: FormArray;
  outsideChargesForm: FormArray;
  isApprove:boolean=false;
  remarkForm: FormGroup;
  btnName: string;
  implementFormLength: boolean;
  wcrDetails: WcrDomain;
  isView = false;
  wcrNo: string;
  wcrViewDetails: Wcr;
  dealerCode: string;
  wcrStatus: string;
  isSubmitDisable:boolean = false;
  constructor(
    private wcrPagePresenter: WcrPagePresenter,
    private warrentyClaimRequestCreatePageService: WarrentyClaimRequestCreatePageService,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private pcrService: PcrPageWebService,
    private router: Router,
    public dialog: MatDialog,
    private iFrameService: IFrameService
  ) {}

  ngOnInit() {
    this.wcrPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    this.dealerCode = this.wcrPagePresenter.loginUser.dealerCode;
    
    this.allForms();
    this.activeRoute();
  }

  allForms() {
    this.wcrForm = this.wcrPagePresenter.wcrForm;
    this.implementForm = this.wcrPagePresenter.implementForm;
    this.serviceHistoryForm = this.wcrPagePresenter.serviceHistoryForm;
    this.concernForm = this.wcrPagePresenter.concernForm;
    this.failurePartsForm = this.wcrPagePresenter.failurePartsForm;
    this.labourChargeForm = this.wcrPagePresenter.labourChargeForm;
    this.outsideChargesForm = this.wcrPagePresenter.outsideLabourChargeForm;
    this.remarkForm = this.wcrPagePresenter.remarkForm;
  }

  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParams => {
      this.viewName = qParams.name;  
      if(this.viewName == 'approve'){
          this.isApprove = true; 
      }
      if (this.wcrPagePresenter.operation == Operation.CREATE) {
        
        if(qParams.pcrNo)  
            this.pcrWarrantyClaim(qParams.pcrNo, qParams.id, 'PCR');
        if(qParams.gwNo)
            this.pcrWarrantyClaim(qParams.gwNo, qParams.id, 'GOODWILL');
        
      } else if (this.wcrPagePresenter.operation == Operation.VIEW) {
        this.isView = true;
        this.wcrNo = atob(qParams.wcrNo); 
        this.viewWarrantyWcr(this.wcrNo);
        this.remarkForm.get("kaiRemark").disable();
        this.remarkForm.get("inspectionRemark").disable();
      }
    });
  }

  private pcrWarrantyClaim(pcrNo: string, id: string, name:string) {
    this.requestType = name;
    this.warrentyClaimRequestCreatePageService
      .pcrWarrantyClaim(pcrNo, id, name)
      .subscribe(
        res => {
          this.wcrDetails = res;
          if(name==='GOODWILL'){
              this.wcrDetails.pcrWarrantyClaimDto = this.wcrDetails.goodwillViewDto.warrantyPcr;
              this.wcrDetails.pcrWarrantyClaimDto.kaiRemarks = this.wcrDetails.goodwillViewDto.kaiRemarks;
              this.wcrDetails.pcrWarrantyClaimDto.goodwillNo = this.wcrDetails.goodwillViewDto.goodwillNo;
              this.wcrDetails.pcrWarrantyClaimDto.status = this.wcrDetails.goodwillViewDto.status;
              this.wcrDetails.pcrWarrantyClaimDto.id = this.wcrDetails.goodwillViewDto.id;
              this.wcrDetails.pcrWarrantyClaimDto.goodwillDate= this.wcrDetails.goodwillViewDto.goodwillDate
              this.wcrDetails.pcrWarrantyClaimDto.emailId = this.wcrDetails.goodwillViewDto.emailId;
              this.wcrDetails.pcrWarrantyClaimDto.mobileNumber = this.wcrDetails.goodwillViewDto.mobileNumber;
              this.wcrDetails.pcrWarrantyClaimDto.dealerCode = this.wcrDetails.goodwillViewDto.dealerCode;
          }
          this.failurePartItem(this.wcrDetails.warrantyPart);
          
         if(this.wcrDetails.outSideLabourCharge){
              this.wcrDetails.outSideLabourCharge.forEach(elt => {
                  this.wcrPagePresenter.addRowInOutsideCharge(elt);
              });
          }
          if(this.wcrDetails.labourCharge){
              this.wcrDetails.labourCharge.forEach(elt => {
                  this.wcrPagePresenter.addRowInLabourCharge(elt);
              })
          }
          
          
          this.wcrDetails.pcrWarrantyClaimDto.warrantyPcrImplements.forEach(elt => {
            this.wcrPagePresenter.addRowInImplement(elt);
          });
          
          this.serviceHistory(
            this.wcrDetails.pcrWarrantyClaimDto.serviceJobCard.machineInventory.vinId
          );
          this.wcrPagePresenter.patchWcrForm(this.wcrDetails.pcrWarrantyClaimDto);
        },
        err => {
          this.tostr.error("Error while fetching data", "Error");
        }
      );
  }
  private viewWarrantyWcr(wcrNo: string) {
    this.warrentyClaimRequestCreatePageService.viewWarrantyWcr(wcrNo).subscribe(
      res => {
        this.idFromView = res.warrantyWcrView.id;
        //this.isCreateInvoice = (res.warrantyWcrView.wcrStatus==='Approved' && this.dealerCode)?true:false;
        
        this.isCreateKaiInvoice = res.invoiceType['isKaiInvoice'];
        this.isCreateDealerPaidInvoice = res.invoiceType['isDealerPaidInvoice'];;
        
        if(res.warrantyWcrView.warrantyPcr)  
            this.wcrViewDetails = res.warrantyWcrView.warrantyPcr;
        else{
            this.wcrViewDetails = res.warrantyWcrView.warrantyGoodwill.warrantyPcr;
            this.wcrViewDetails.goodwillNo = res.warrantyWcrView.warrantyGoodwill.goodwillNo;
            this.wcrViewDetails.goodwillDate = res.warrantyWcrView.warrantyGoodwill.goodwillDate;
        }
        this.isShowInspectionBtn = res.isShowInspectionBtn;
        this.wcrStatus = res.warrantyWcrView.wcrStatus;
        this.failurePartItem(res.warrantyPart);
       
        if(res.outSideLabourCharge){
            res.outSideLabourCharge.forEach(elt => {
                this.wcrPagePresenter.addRowInOutsideCharge(elt);
            });
        }
        if(res.labourCharge){
            res.labourCharge.forEach(elt => {
                this.wcrPagePresenter.addRowInLabourCharge(elt);
            })
        }
        
        
        this.wcrViewDetails.warrantyPcrImplements.forEach(elt => {
          this.wcrPagePresenter.addRowInImplement(elt);
        });
        this.serviceHistory(this.wcrViewDetails.serviceJobCard.machineInventory.vinId);
        this.wcrPagePresenter.patchWcrForm(this.wcrViewDetails, res.warrantyWcrView);
        this.approvalHierDetails = res.approvalHierDetails;
      },
      err => {
        this.tostr.error("Error while fetching data", "Error");
      }
    );
  }

  private serviceHistory(id: number) {
    this.pcrService.serviceHistory(id).subscribe(
      res => {
        const serviceHistoryDetails = res;
        if (serviceHistoryDetails.length > 0) {
          serviceHistoryDetails.forEach(elt => {
            this.wcrPagePresenter.addRowInServiceHistory(elt);
          });
        }
      },
      err => {
          this.tostr.error("Error while fetching data", "Error");
      }
    );
  }

  private failurePartItem(item: WarrantyPart[]) {
    const failurePart = {} as FailurePart;

    item.forEach(elt => {
      failurePart.itemNo = elt.partNo;
      failurePart.description = elt.description;
      failurePart.approvedQuantity = elt.approvedQuantity;
      failurePart.unitPrice = elt.unitPrice;
      failurePart.claimValue = elt.approvedQuantity * elt.unitPrice;
      failurePart.id = elt.id;
      failurePart.finalApprovedQuantity = elt.claimApprovedQuantity; 
      failurePart.finalApprovedAmount = elt.claimApprovedAmount;
      this.wcrPagePresenter.addRowInFailurePart(failurePart);
    });
  }

  formValid(btnType: string) {
    this.btnName = btnType;
    if(this.btnName == 'submit')
        this.openConfirmDialog();
    else
        this.openApprovalConfirmDialog();
  }

  markFormAsTouched() {
    this.wcrForm.markAllAsTouched();
    this.implementForm.markAllAsTouched();
    this.serviceHistoryForm.markAllAsTouched();
    this.concernForm.markAllAsTouched();
    this.failurePartsForm.markAllAsTouched();
    this.remarkForm.markAllAsTouched();
    this.tostr.error('Please fill all mandatory fields', 'Error');
}

  private openApprovalConfirmDialog() {
      let message = `Do you want to ${this.btnName} Warranty Claim Request?`;

      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: "500px",
        panelClass: "confirmation_modal",
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe((result:DialogResult) => {
          if (result.button === 'Confirm') {
              this.isSubmitDisable = true;
              this.approve(result.remarkText);
           }
      });
  }
    
  private approve(remark){
      let approvalStatus = this.btnName;
      let approveWcr = {id:this.wcrViewDetails.id, remarks:remark, approvalStatus:approvalStatus};
      
      this.warrentyClaimRequestCreatePageService.approveWcr(approveWcr).subscribe(
          res => {
            if(res){
              this.tostr.success(res["message"], "Success");
              this.router.navigate([".."], { relativeTo: this.activatedRoute });
            }else{
              this.isSubmitDisable = false;
              this.tostr.error('Error generated while saving', 'Transaction failed');
            }
          },
          err => {
            this.isSubmitDisable = false;
            this.tostr.error('Error generated while saving', 'Transaction failed');
          }
        );
  }
  
  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Warranty Claim Request?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "Confirm") {
        if (this.btnName == "submit") {
          this.isSubmitDisable = true;
          this.submitWcrForm();
        }
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

  private submitWcrForm() {
    const submitWcr = {} as WcrSubmit;
    //submitWcr.kaiRemark = this.remarkForm.get("kaiRemark").value;
    //submitWcr.inspectionRemark = this.remarkForm.get("inspectionRemark").value;
   
    const itemList: SparePartRequisitionItem[] = [];
    const itemArr = this.failurePartsForm.getRawValue();
    if(itemArr){
        itemArr.forEach((elt) => {
            itemList.push({
                id:elt.id,
                claimApprovedAmount : elt.claimApprovedAmount,
                claimApprovedQuantity : elt.claimApprovedQuantity
          });
        })
    }
   // submitWcr.sparePartRequisitionItem = itemList;
    
    const labourList: LabourChargeSubmit[] = [];
    const labourArr = this.labourChargeForm.getRawValue();
    if(labourArr){
        labourArr.forEach((elt) => {
            labourList.push({
               labourChargeId : elt.labourChargeId,  
              claimApprovedAmount : elt.claimAmount
          });
        });
    }
    //submitWcr.labourCharge = labourList;
    
    const outsideList: OutSideChargeSubmit[] = [];
    const outsideArr = this.outsideChargesForm.getRawValue();
    if(outsideArr){
        outsideArr.forEach((elt) => {
            outsideList.push({
              jobcodeId : elt.jobcodeId,  
              claimApprovedAmount : elt.claimAmount
          });
        });
    }
    //submitWcr.outsidecharge = outsideList;
    if(this.requestType==='PCR')
        submitWcr.warrantyPcr = { id: this.wcrDetails.pcrWarrantyClaimDto.id, ...{serviceJobCard: {id: this.wcrDetails.pcrWarrantyClaimDto.serviceJobCard.id, sparePartRequisitionItem:itemList ,outsideJobCharge:outsideList, labourCharge:labourList }} };
    else
        submitWcr.warrantyGoodwill = { id: this.wcrDetails.pcrWarrantyClaimDto.id, ...{warrantyPcr : {serviceJobCard: {id: this.wcrDetails.pcrWarrantyClaimDto.serviceJobCard.id, sparePartRequisitionItem:itemList ,outsideJobCharge:outsideList, labourCharge:labourList }}} };
    
    submitWcr.draftFlag = this.btnName == "submit" ? false : true;
    
    this.warrentyClaimRequestCreatePageService.saveWcr(submitWcr).subscribe(
      res => {
        if(res){
            this.tostr.success(res["message"], "Success");
            this.router.navigate([".."], { relativeTo: this.activatedRoute });
        }else{
            this.isSubmitDisable = false;
            this.tostr.error('Error generated while saving', 'Transaction failed');
        }
      },
      err => {
          this.isSubmitDisable = false;
          this.tostr.error('Error generated while saving', 'Transaction failed');
      }
    );
  }

  navigateToInspectionSheet() {
    this.router.navigate(['../../kai-inspection-sheet/create'], {relativeTo: this.activatedRoute, queryParams: {wcrNo: this.wcrNo}})
  }
  

  routeToInvoice(claimType:string) {
      const url = 'spares/countersales/salesinvoice/create';
      this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: { id: this.idFromView, wcrNo: this.wcrNo, claimType:claimType} })
  }
  
  viewPrint(printStatus:string){
      this.warrentyClaimRequestCreatePageService.printWCR(this.wcrForm.get('wcrNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }

}
