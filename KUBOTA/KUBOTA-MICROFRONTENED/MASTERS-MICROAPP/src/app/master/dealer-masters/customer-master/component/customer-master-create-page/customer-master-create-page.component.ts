import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { CustomerMasterCreatePageStore } from './customerMasterCreatePage.store';
import { CustomerMasterCreatePagePresenter } from './customerMasterCreatePage.presenter';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { CustomerMasterDto, SoilTypeDropdown, CropsGrownDropdown, DealerInformation, ProspectSoilType, ProspectCropGrown } from '../../domain/customer-master-domain'
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerMasterCreatePageService } from './customer-master-create-page.service';


@Component({
  selector: 'app-customer-master-create-page',
  templateUrl: './customer-master-create-page.component.html',
  styleUrls: ['./customer-master-create-page.component.scss'],
  providers: [CustomerMasterCreatePagePresenter, CustomerMasterCreatePageStore, CustomerMasterCreatePageService]
})
export class CustomerMasterCreatePageComponent implements OnInit {

  createForm: FormGroup
  customerMasterDetailsForm: FormGroup
  customerMasterAddressDetailsForm: FormGroup
  customerMasterLandDetailsForm: FormGroup
  purchaseDetails: FormGroup
  vehicalDetail: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  
  soiltypes: Array<SoilTypeDropdown> = [];
  crops: Array<CropsGrownDropdown> = [];
  dealerInformation : Array<DealerInformation> = []

constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private router : Router,
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
  // activityRoute(activityRoute: any): string {
  //   throw new Error("Method not implemented.");
  // }

  private viewOrEditOrCreate() {
    this.activityRoute.queryParams.subscribe(qParams => {   
        if (this.customerMasterCreatePagePresenter.operation === Operation.VIEW) {
            this.isView = true
            this.getCustomerDetails(qParams.customerCode);
        } else if (this.customerMasterCreatePagePresenter.operation === Operation.EDIT) {
            this.isEdit = true;
            this.getCustomerDetails(qParams.customerCode);
        } else {
            this.customerMasterCreatePagePresenter.operation === Operation.CREATE
        }
    })
  }

  getCustomerDetails(customerCode){
      this.customerMasterCreatePageService.getCustomerDetails(customerCode).subscribe(response => {
          var customerMaster:CustomerMasterDto = response['result']; 
          this.customerMasterDetailsForm.patchValue(customerMaster);
          this.customerMasterAddressDetailsForm.patchValue(customerMaster);
          this.customerMasterAddressDetailsForm.get('pinCode').patchValue({'pinCode':customerMaster.pinCode})
          //customerMaster.prospectSoilTypes && this.customerMasterLandDetailsForm.get('soilType').patchValue(customerMaster.prospectSoilTypes[0].soilName);
          this.customerMasterAddressDetailsForm.get('district').patchValue(this.dealerInformation[this.dealerInformation.findIndex(res => res.district === customerMaster.district)]);
          this.customerMasterLandDetailsForm.get('landSize').patchValue(customerMaster.landSize);
          //this.customerMasterLandDetailsForm.get('mobileNumber').patchValue({mobileNumber:customerMaster.mobileNumber});
          /*customerMaster.prospectCropNames && customerMaster.prospectCropNames.forEach(crop => {
              this.customerMasterCreatePagePresenter.cropsFormAdd(crop);
          });*/
          
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
          
          if(this.isView){
              this.createForm.disable();
          }else if(this.isEdit){
              this.customerMasterDetailsForm.get('customerCode').disable();
          }
      });
  }
  /*clearCustomerCreateForm() {
    this.createForm.reset()
    const clrForm = this.customerMasterCreatePagePresenter.purchaseDetails.get('dataTable') as FormArray
    clrForm.clear()
    this.customerMasterCreatePagePresenter.addRow()
  }*/

  submitCustomerCreateForm() {

    if (this.createForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.customerMasterCreatePagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitData() {
      var cutomerDetails = this.customerMasterDetailsForm.getRawValue();
      var addressDetails = this.customerMasterAddressDetailsForm.getRawValue();
      var landDetails = this.customerMasterLandDetailsForm.getRawValue();
      var purchaseDetails = this.purchaseDetails.getRawValue().dataTable;
      addressDetails['pinCode'] = addressDetails['pinCode']['pinCode'];
      addressDetails['district'] = addressDetails['district']['district'];
      //prospectSoilTypes
      //prospectCropNames
      
      var prospectSoilTypes: ProspectSoilType[] = [];
      var prospectCropNames: ProspectCropGrown[] = [];
      
      landDetails.soilType.forEach(soil => {
          prospectSoilTypes.push({soilName:soil});
      })
      landDetails.cropName.forEach(crop => {
          prospectCropNames.push({cropName:crop});
      })
      var customerMaster:CustomerMasterDto = {...cutomerDetails, ...addressDetails, ...landDetails, ...{prospectSoilTypes:prospectSoilTypes}, ...{prospectCropNames:prospectCropNames}, ...{prospectMachineryDetails:purchaseDetails}};
      console.log('customerMaster :',customerMaster)
      this.customerMasterCreatePageService.submitCustomerMaster(customerMaster).subscribe(response => {

          this.toastr.success(`Customer Master Change Request Submitted Successfully`, 'Success')
          
          this.router.navigate(['../'], { relativeTo: this.activityRoute })
      });
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Customer Master?';
    if (this.isEdit) {
      message = 'Do you want to update Customer Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        this.submitData();
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }



}
