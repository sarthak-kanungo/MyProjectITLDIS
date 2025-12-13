import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { CustomerMasterCreatePageStore } from '../../customer-master/component/customer-master-create-page/customerMasterCreatePage.store';
import { CustomerMasterCreatePagePresenter } from '../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { CustomerMasterChangeRequestPresenter } from './customerMasterChangeRequest.presenter';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { CustomerMasterDto, SoilTypeDropdown, CropsGrownDropdown, DealerInformation, ProspectSoilType, ProspectCropGrown } from '../../customer-master/domain/customer-master-domain'
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerMasterCreatePageService } from '../../customer-master/component/customer-master-create-page/customer-master-create-page.service';
import { Source, DialogResult } from '../../../../confirmation-box/confirmation-data';

@Component({
  selector: 'app-customer-master-change-request-approval-page',
  templateUrl: './customer-master-change-request-approval-page.component.html',
  styleUrls: ['./customer-master-change-request-approval-page.component.css'],
  providers: [CustomerMasterCreatePagePresenter, CustomerMasterCreatePageStore, CustomerMasterCreatePageService, CustomerMasterChangeRequestPresenter]
})
export class CustomerMasterChangeRequestApprovalPageComponent implements OnInit {
  createForm: FormGroup
  customerMasterDetailsForm: FormGroup
  customerMasterAddressDetailsForm: FormGroup
  customerMasterLandDetailsForm: FormGroup
  purchaseDetails: FormGroup
  vehicalDetail: FormGroup
  changeRequestId: number
  createFormReq: FormGroup
  customerMasterDetailsFormReq: FormGroup
  customerMasterAddressDetailsFormReq: FormGroup
  customerMasterLandDetailsFormReq: FormGroup
  purchaseDetailsReq: FormGroup
  vehicalDetailReq: FormGroup

  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;

  soiltypes: Array<SoilTypeDropdown> = [];
  crops: Array<CropsGrownDropdown> = [];
  dealerInformation: Array<DealerInformation> = []
  colorCheck: boolean = false;
  brandColor: boolean = false;
  modelColor: boolean = false;
  yearColor: boolean = false;
  serialColor: boolean = false
  colorCheckCrop: boolean = false
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerMasterChangeRequestPresenter: CustomerMasterChangeRequestPresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private router: Router,
    private customerMasterCreatePageService: CustomerMasterCreatePageService

  ) { }

  ngOnInit() {
    this.customerMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.createForm = this.customerMasterCreatePagePresenter.createCustomerMasterForm
    this.customerMasterDetailsForm = this.customerMasterCreatePagePresenter.customerCreateDetailsForm
    this.customerMasterAddressDetailsForm = this.customerMasterCreatePagePresenter.createCustomerAddressDetails
    this.customerMasterLandDetailsForm = this.customerMasterCreatePagePresenter.createCustomerLandDetails
    this.purchaseDetails = this.customerMasterCreatePagePresenter.purchaseDetails
    this.vehicalDetail = this.customerMasterCreatePagePresenter.vehicalDetailsForm

    this.createFormReq = this.customerMasterChangeRequestPresenter.customerMasterChangeRequestForm
    this.customerMasterDetailsFormReq = this.customerMasterChangeRequestPresenter.customerCreateDetailsForm
    this.customerMasterAddressDetailsFormReq = this.customerMasterChangeRequestPresenter.createCustomerAddressDetails
    this.customerMasterLandDetailsFormReq = this.customerMasterChangeRequestPresenter.createCustomerLandDetails
    this.purchaseDetailsReq = this.customerMasterChangeRequestPresenter.purchaseDetails
    this.vehicalDetailReq = this.customerMasterChangeRequestPresenter.vehicalDetailsForm

    this.customerMasterCreatePageService.getSoilTypeDropdown().subscribe(response => {
      this.soiltypes = response['result'];

    });

    this.customerMasterCreatePageService.getCropsGrownDropdown().subscribe(response => {
      this.crops = response['result'];

    });

    this.customerMasterCreatePageService.getDealerRegionInfo().subscribe((response) => {
      this.dealerInformation = response['result'];
    });

    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    this.activityRoute.queryParams.subscribe(qParams => {
      if (this.customerMasterCreatePagePresenter.operation === Operation.APPROVE) {
        this.isView = true
        this.getCustomerDetails(qParams.customerCode);
        this.getCustomerChangeRequestDetails(qParams.customerCode);
      }
    })
  }
 

  getCustomerChangeRequestDetails(customerCode) {

    this.customerMasterCreatePageService.getCustomerChangeRequestDetails(customerCode).subscribe(response => {
      var customerMaster: CustomerMasterDto = response['result'];
      this.changeRequestId = customerMaster.custReqId;
      this.customerMasterDetailsFormReq.patchValue(customerMaster);
      this.customerMasterDetailsFormReq.get('customerCode').patchValue(customerCode);

      this.customerMasterAddressDetailsFormReq.patchValue(customerMaster);
      // this.customerMasterAddressDetailsFormReq.get('pinCode').patchValue({'pinCode':customerMaster.pinCode})
      // this.customerMasterAddressDetailsFormReq.get('district').patchValue(this.dealerInformation[this.dealerInformation.findIndex(res => res.district === customerMaster.district)]);
      this.customerMasterLandDetailsFormReq.get('landSize').patchValue(customerMaster.landSize);
      // this.customerMasterLandDetailsFormReq.get('district').patchValue(customerMaster.district);
      let selectedSoilType = [];

      customerMaster.prospectSoilTypes && customerMaster.prospectSoilTypes.forEach(type => {
        selectedSoilType.push(this.soiltypes[this.soiltypes.findIndex(res => res.soilType === type.soilName)].soilType);
      });

      this.customerMasterLandDetailsFormReq.get('soilType').setValue(selectedSoilType);

      let selectedCropGrown = [];

      customerMaster.prospectCropNames && customerMaster.prospectCropNames.forEach(type => {
        selectedCropGrown.push(this.crops[this.crops.findIndex(res => res.cropGrown === type.cropName)].cropGrown);
      })
      this.customerMasterLandDetailsFormReq.get('cropName').setValue(selectedCropGrown);


      customerMaster.prospectMachineryDetails && customerMaster.prospectMachineryDetails.forEach(machinery => {
        this.customerMasterChangeRequestPresenter.addRow(machinery);
      });
      var soil1 = this.customerMasterLandDetailsForm.controls['soilType'].value == null ? [] : this.customerMasterLandDetailsForm.controls['soilType'].value;
      var soil2 = this.customerMasterLandDetailsFormReq.controls['soilType'].value == null ? [] : this.customerMasterLandDetailsFormReq.controls['soilType'].value;
      var count = 0;
      if (soil1.length == soil2.length) {
        for (var i = 0; i < soil1.length; i++) {
          if (soil1[i] === soil2[i])
            count++;
        }

        if (count == soil1.length) {
          this.colorCheck = true
        } else {
          this.colorCheck = false
        }
      } else {
        this.colorCheck = false
      }

      var cropName1 = this.customerMasterLandDetailsForm.controls['cropName'].value == null ? [] : this.customerMasterLandDetailsForm.controls['cropName'].value;
      var cropName2 = this.customerMasterLandDetailsFormReq.controls['cropName'].value == null ? [] : this.customerMasterLandDetailsFormReq.controls['cropName'].value;
      var counts = 0;
      if (cropName1.length == cropName2.length) {
        for (var i = 0; i < cropName1.length; i++) {
          if (cropName1[i] === cropName2[i])
            counts++;
        }

        if (counts == cropName1.length) {
          this.colorCheckCrop = true
        } else {
          this.colorCheckCrop = false
        }
      } else {
        this.colorCheckCrop = false
      }

      if (this.purchaseDetails.controls.dataTable.value.length >= 1 && this.purchaseDetails.controls.dataTable.value.length == this.purchaseDetailsReq.controls.dataTable.value.length) {
        for (var i = 0; i < this.purchaseDetailsReq.controls.dataTable.value.length; i++) {
          var brand1 = this.purchaseDetails.controls.dataTable.value[i]['brand'];
          var brand2 = this.purchaseDetailsReq.controls.dataTable.value[i]['brand'];
          var model1 = this.purchaseDetails.controls.dataTable.value[i]['model'];
          var model2 = this.purchaseDetailsReq.controls.dataTable.value[i]['model'];
          var year1 = this.purchaseDetails.controls.dataTable.value[i]['yearOfPurchase'];
          var year2 = this.purchaseDetailsReq.controls.dataTable.value[i]['yearOfPurchase'];
          var serial1 = this.purchaseDetails.controls.dataTable.value[i]['serialno'];
          var serial2 = this.purchaseDetailsReq.controls.dataTable.value[i]['serialno'];
          if (brand1 !== brand2) {
            this.brandColor = true
          } else if (brand1 === brand2) {
            this.brandColor = false
          }
          if (model1 !== model2) {
            this.modelColor = true;
          } else if (model1 === model2) {
            this.modelColor = false
          }
          if (year1 !== year2) {
            this.yearColor = true
          } else if (year1 === year2) {
            this.yearColor = false
          }
          if (serial1 !== serial2) {
            this.serialColor = true
          } else if (serial1 === serial2) {
            this.serialColor = false
          }
        }
      } else {

      }

      this.createFormReq.disable();

    });
  }

  getCustomerDetails(customerCode) {
    this.customerMasterCreatePageService.getCustomerDetails(customerCode).subscribe(response => {
      var customerMaster: CustomerMasterDto = response['result'];

      this.customerMasterDetailsForm.patchValue(customerMaster);

      if (response['result'].editCount == null || response['result'].editCount == '') {
        this.customerMasterDetailsForm.get('editCount').patchValue(0)

      } else {
        this.customerMasterDetailsForm.get('editCount').patchValue(response['result'].editCount)
      }

      this.customerMasterAddressDetailsForm.patchValue(customerMaster);
      // this.customerMasterAddressDetailsForm.get('pinCode').patchValue({'pinCode':customerMaster.pinCode})
      // this.customerMasterLandDetailsForm.get('district').patchValue(customerMaster.district);
      // this.customerMasterAddressDetailsForm.get('district').patchValue(this.dealerInformation[this.dealerInformation.findIndex(res => res.district === customerMaster.district)]);
      this.customerMasterLandDetailsForm.get('landSize').patchValue(customerMaster.landSize);

      let selectedSoilType = [];

      customerMaster.prospectSoilTypes && customerMaster.prospectSoilTypes.forEach(type => {
        selectedSoilType.push(this.soiltypes[this.soiltypes.findIndex(res => res.soilType === type.soilName)].soilType);
      });

      this.customerMasterLandDetailsForm.get('soilType').setValue(selectedSoilType);

      let selectedCropGrown = [];

      customerMaster.prospectCropNames && customerMaster.prospectCropNames.forEach(type => {
        selectedCropGrown.push(this.crops[this.crops.findIndex(res => res.cropGrown === type.cropName)].cropGrown);
      })
      this.customerMasterLandDetailsForm.get('cropName').setValue(selectedCropGrown);


      customerMaster.prospectMachineryDetails && customerMaster.prospectMachineryDetails.forEach(machinery => {
        this.customerMasterCreatePagePresenter.addRow(machinery);
      });

      customerMaster.vehicleDetails && customerMaster.vehicleDetails.forEach(sale => {
        this.customerMasterCreatePagePresenter.vehicalDetailsAdd(sale);
      });

      // selectedSoilType.forEach( array1Ttems => {

      //   selectedCropGrown.forEach( array2Items => {


      //      if(array1Ttems === array2Items){
      //       this.colorCheck=true;
      //         console.log("It's match in land details old",this.colorCheck);
      //     }
      //     else{
      //         console.log("This item not present in array old =>");
      //     }

      //   })
      // })
      this.createForm.disable();

    });
  }

  private openConfirmDialog(approvalType): void | any {
    let message = `Do you want to ${approvalType} Customer Change Request?`;

    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result.button === 'Confirm') {
        this.submitData(approvalType, result.remarkText);
      }
    });
  }

  private setConfirmationModalData(message: string) {
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

  submitCustomerChangeRequestForm(approvalType) {

    this.openConfirmDialog(approvalType);
  }

  submitData(approvalType: string, remarks: string) {

    var customerMaster = { custReqId: this.changeRequestId, remarks: remarks, approvalType: approvalType };

    this.customerMasterCreatePageService.approveRejectChangeRequest(customerMaster).subscribe(response => {

      // if(response['result']==='FAIL'){
      //     this.toastr.error(`Customer Change Request Not approved `, 'Error')
      //     return true;
      // } else{
      //     this.toastr.success(`Customer Change Request approved Successfully`, 'Success')
      //     return true;
      // }
      if (approvalType === 'approve')
        this.toastr.success(`Customer Change Request approved Successfully`, 'Success')
      else
        this.toastr.success(`Customer Change Request rejected Successfully`, 'Success')

      this.router.navigate(['../'], { relativeTo: this.activityRoute })
    });
  }

}
