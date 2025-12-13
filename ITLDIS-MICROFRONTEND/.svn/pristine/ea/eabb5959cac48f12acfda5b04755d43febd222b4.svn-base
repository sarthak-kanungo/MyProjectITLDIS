import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';

import { OPSService } from '../service/ops.service';
import { OpsStore } from './order-planning-sheet-store';
import { opsPresenter } from './order-planning-sheet-presenter';
import { Location } from '@angular/common';
import { MatDialog, getMatIconFailedToSanitizeUrlError } from '@angular/material';
import { ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ToastrService } from 'ngx-toastr';
import { SparePartRequisitionItem } from './order-planning-sheet-domain';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-order-planning-sheet-page',
  templateUrl: './order-planning-sheet-page.component.html',
  styleUrls: ['./order-planning-sheet-page.component.css'],
  providers: [opsPresenter, OpsStore, OPSService]
})
export class OrderPlanningSheetPageComponent implements OnInit {
  orderPlanningForm: FormGroup
  partDetails:FormArray
  systemDate: any;
  btnName: string;
  isView: any
  isEdit: any
  orderTypeId: number;
  logicTypeId: number;
  draftFlag: boolean;
  isCreate: boolean = false;
  editLogicTypeId: number;
  editOrderTypeId: number;
  headerId: number;
  editTotalOrderValue: any
  orderPlanningSheetNumber: number;
  searchCount: number
  orderPlanningStatus: any;
  constructor(
    private presenter: opsPresenter,
    private location: Location,
    private service: OPSService,
    private dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private toaster: ToastrService,
    private router: Router,
    private store: OpsStore,

  ) { }

  ngOnInit() {
    this.partDetails=this.presenter.materialDetailsForm;
    this.orderPlanningForm = this.presenter.OpsForms;
    this.getSystemDate()
    this.checkFormOperation()
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.activeRoute()
      this.orderPlanningForm.disable();

    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.activeRoute()
      this.orderPlanningForm.disable();

    }
    else if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true
    }

  }

  private getSystemDate() {
    this.service.getSystemDate().subscribe(date => {
      this.systemDate = date
      this.orderPlanningForm.get('date').patchValue(this.systemDate.result)
    })
  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      let opSheetNo = atob(qParam.opSheetNo);
      this.viewBranchTransferReciept(opSheetNo);
    });
  }
  private viewBranchTransferReciept(opSheetNo: any) {
    this.service.getOrderPlannningSheetView(opSheetNo).subscribe(res => {
      if (res) {
        this.headerId = res.id ? res.id : null;
       
      
        this.orderPlanningSheetNumber = res.opsNo ? res.opsNo : null;
        this.orderPlanningStatus = res.status === 'Submitted'?'Submitted':'PO Created';
        this.orderPlanningForm.get('orderPlanningSheetNo').patchValue(res.opsNo ? res.opsNo : null);
        this.orderPlanningForm.get('Opstatus').patchValue(res.status ? res.status : null);
        this.orderPlanningForm.get('orderQtyMonth').patchValue(res.reorderQtyMonths ? res.reorderQtyMonths : null);
        this.orderPlanningForm.get('suggestionOrderQty').patchValue(res.suggestedOrderQty ? res.suggestedOrderQty : null);
        this.orderPlanningForm.get('orderType').patchValue(res['planningOrderType'].orderType ? res['planningOrderType'].orderType : null);
        this.orderPlanningForm.get('logic').patchValue(res['opSheetLogic'].logic ? res['opSheetLogic'].logic : null);
        this.editOrderTypeId = res['planningOrderType'].id,
          this.editLogicTypeId = res['opSheetLogic'].id

          if (this.isView) {
            this.partDetails.disable();
            let totalOrderValue = 0; // Declare totalOrderValue outside the loop
            for (const issueItem of res['orderPlanningSheetDetails']) {
                const orderValue = issueItem.orderValue || 0; // Ensure orderValue is correct
                totalOrderValue += orderValue; // Increment totalOrderValue by orderValue
                console.log(totalOrderValue, 'totalOrderValue');
                this.editTotalOrderValue = Number(totalOrderValue).toFixed(2); // Assign totalOrderValue to this.editTotalOrderValue
                const part = this.store.buildmaterialDetailsForm();
                part.patchValue({
                    itemNo: issueItem.sparePartMaster.itemNo,
                    itemDesc: issueItem.sparePartMaster.itemDescription,
                    reOrderQty: issueItem.reorderQty,
                    dealerStock: issueItem.dealerStock,
                    kaiBackOrder: issueItem.kaiBackOrder,
                    transitFromKai: issueItem.transitFromKai,
                    l12mSales: issueItem.l12mSales,
                    suggestedOrderQty: issueItem.suggestedOrderQty,
                    actualOrderQty: issueItem.actualOrderQty,
                    unitPrice: issueItem.unitPrice,
                    gstPercent: issueItem.gstPercent,
                    id: issueItem.id,
                    sparepartId: issueItem.itemId,
                    orderValue:issueItem.orderValue?issueItem.orderValue:0
                    // editsparepartId: issueItem
                    // totalOrderValue: issueItem.totalOrderValue,
                    // unitPrice: issueItem.unitPrice,
                    // actualOrderQty: issueItem.actualOrderQty
                });
                this.partDetails.controls.push(part);
                this.partDetails.disable();
                // this.presenter.addRow(issueItem);
            }
        }
        
        else {
          let editPostData = {
            logicId: this.editLogicTypeId,
            reOrderMonth: res.reorderQtyMonths,
            suggestedOrderMonth: res.suggestedOrderQty,
            orderTypeId: res['planningOrderType'].id,
            opSheetNo: res.opsNo
          };
        
          let dataPatched = false; // Flag to track if data has been patched
          let subscription; // Variable to hold the subscription
        
          subscription = this.service.getItemDetails(editPostData).subscribe(
            (res) => {
              console.log(res, 'res');
          
              if (!dataPatched) { // Check if data hasn't been patched yet
                let totalOrderValue1 = 0;
                const partsArray = res['result'].map((element: any) => {
                  const part = this.store.buildmaterialDetailsForm();
                  part.patchValue({
                    itemNo: element.itemNo,
                    itemDesc: element.itemDescription,
                    reOrderQty: element.reorderQty,
                    dealerStock: element.dealerStock,
                    kaiBackOrder: element.kaiBackOrder,
                    transitFromKai: element.transitFromKai,
                    l12mSales: element.l12mSales,
                    suggestedOrderQty: element.suggestedOrderQty,
                    actualOrderQty: element.actualOrderQty,
                    unitPrice: element.unitPrice,
                    gstPercent: element.gstPercent,
                    id: element.id?element.id:null,
                    sparepartId: element.itemId?element.itemId:null,
                    orderValue:element.orderValue?element.orderValue:0
                  });
                  return part;
                });
          
                partsArray.forEach(part => {
                  this.partDetails.push(part); // Push each patched form control into the FormArray
                });
          
                totalOrderValue1 = res['result'].reduce((acc: number, issueItem: any) => {
                  const orderValue = issueItem.orderValue || 0;
                  return acc + orderValue;
                }, 0);
                
                this.editTotalOrderValue = totalOrderValue1.toFixed(2);
                
                dataPatched = true; // Set flag to true after data has been patched
              }
            },
            (error) => {
              console.error('Error fetching item details:', error);
            }
          );
          
        
          // Unsubscribe when component is destroyed or when data is no longer needed
         
        }
        
        
      }

      else {
        this.toaster.warning("Please is Not Available")
      }
    })



  }
  validateForm(btnType: string) {

    btnType == 'submit' ? this.btnName = 'submit' : this.btnName = 'save';
    if (btnType === 'submit') {
      this.draftFlag = false
    } else {
      this.draftFlag = true
    }

    if (this.orderPlanningForm.invalid) {
      this.orderPlanningForm.markAllAsTouched()
      return false
    }
    if (this.partDetails.controls.length == 0) {
      // this.presenter..markAllAsTouched()
      this.toaster.error("Part Details Are Not Available")
      return false
    }
   
    // if(this.presenter.itemDetailsGroup)
    this.openConfirmDialog()

  }

  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Order Planning Sheet?`

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {

        this.submitOrderPlanningSheet()

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






  private submitOrderPlanningSheet() {
    const partDetails: SparePartRequisitionItem[] = [];
    let headerData = {
      id: this.headerId ? this.headerId : null,
      planningOrderType: {
        id: this.orderTypeId ? this.orderTypeId : this.editOrderTypeId,
      },
      draftFlag: this.draftFlag,
      reorderQtyMonths: this.orderPlanningForm.get('orderQtyMonth').value.value ? this.orderPlanningForm.get('orderQtyMonth').value.value : this.orderPlanningForm.get('orderQtyMonth').value,
      suggestedOrderQty: this.orderPlanningForm.get('suggestionOrderQty').value.value ? this.orderPlanningForm.get('suggestionOrderQty').value.value : this.orderPlanningForm.get('suggestionOrderQty').value,
      opSheetLogic: {
        id: this.logicTypeId ? this.logicTypeId : this.editLogicTypeId
      },
      totalOrderValue: this.editTotalOrderValue ? this.editTotalOrderValue : (this.presenter.totalOrderValueforView)
    }
    if (this.presenter.materialDetailsForm && this.isCreate) {
      this.presenter.materialDetailsForm.getRawValue().forEach(elt => {
        if (elt.actualOrderQty != null && elt.actualOrderQty != '' && elt.actualOrderQty != undefined) {
          partDetails.push({
            sparePartMaster: {
              id: elt.sparepartId
            },
            id: null,
            reorderQty: Number(elt.reOrderQty),
            dealerStock: elt.dealerStock,
            kaiBackOrder: elt.kaiBackOrder,
            transitFromKai: elt.transitFromKai,
            l12mSales: elt.l12mSales,
            suggestedOrderQty: Number(elt.suggestedOrderQty),
            actualOrderQty: elt.actualOrderQty,
            orderValue: elt.orderValue,
            unitPrice: elt.unitPrice,
            gstPercent: elt.gstPercent
          })
        }

      })
    }
    if (this.presenter.materialDetailsForm && this.isEdit) {
      this.presenter.materialDetailsForm.getRawValue().forEach(elt => {
        if (elt.actualOrderQty != null && elt.actualOrderQty != '' && elt.actualOrderQty != undefined) {
          partDetails.push({
            sparePartMaster: {
              id: elt.sparepartId,
            },
            id: elt.id,
            reorderQty: Number(elt.reOrderQty),
            dealerStock: elt.dealerStock,
            kaiBackOrder: elt.kaiBackOrder,
            transitFromKai: elt.transitFromKai,
            l12mSales: elt.l12mSales,
            suggestedOrderQty: Number(elt.suggestedOrderQty),
            actualOrderQty: elt.actualOrderQty,
            orderValue: elt.orderValue,
            unitPrice: elt.unitPrice,
            gstPercent: elt.gstPercent
          })
        }
      })
    }
    headerData['orderPlanningSheetDetails'] = partDetails;
    this.service.submitOrderPlanningSheet(headerData).subscribe(
      (res) => {
        try {
          if (res['status'] == '200') {
            this.toaster.success(res['message'], 'Success');
            this.router.navigate(['../'], { relativeTo: this.activatedRoute });
          } else if (res['status'] == '400' || res['status'] == '403') {
            this.toaster.error(res['message'], 'Error');
          } else {
            this.toaster.warning(res['status'], 'Warning');
          }
        } catch (error) {
          // Handle errors during processing
          this.toaster.error('An error occurred while processing the response', 'Error');
        }
      },
      (error) => {
        // Handle HTTP error responses
        if (error.status == "403") {
          this.toaster.warning('Data Missing', 'Error');
        } else if(error.status=="400") {
          this.toaster.error('Part Details Data Not Send', 'Error');
        }else{
          this.toaster.warning('Part Details Data Not Send', 'Error');
        }
      }
    );
    

  }


  getOrderTypeId(event: any) {
    // console.log(event)
    this.orderTypeId = event;
  }
  getLogicTypeId(id: number) {
    this.logicTypeId = id
  }



  exitForm() {
    if (this.isCreate) {
      this.location.back();
    }
    if (this.isEdit) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }
  }
  clearForm() {
    this.orderPlanningForm.reset();
    this.orderPlanningForm.get('suggestionOrderQty').disable();
    this.orderPlanningForm.get('orderType').disable();
    this.orderPlanningForm.get('logic').disable();
    this.getSystemDate();
   this.partDetails.clear();
  }
  downloadExcel() {

  }
  ngOnDestroy() {
    // if (subscription) {
    //   subscription.unsubscribe();
    // }
  }
  downloadPdf() {

    this.service.downloadViewPdf(this.headerId, this.orderPlanningSheetNumber, true).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
        saveAs(file);
      }
    })
  }

  getCount(event: any) {
    this.searchCount = event;
  }
}
