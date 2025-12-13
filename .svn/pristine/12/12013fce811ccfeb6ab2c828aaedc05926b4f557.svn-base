import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { Operation } from '../../../../../utils/operation-util';
import { InstallationPagePresenter } from '../installation-page/installation-page-presenter';
import { BasicInstallationDetailsWebService } from './basic-installation-details-web.service';
import { RepresentativeTypes, AutoChassisNumber, ServiceStaffName, SearchInstallationList, DetailsByChassisNo, AutoCsbNumber, InstallationCheckList } from '../../domain/installation-domain';
import { Observable } from 'rxjs';
import { InstallationCommonWebService } from '../../service/installation-common-web.service';
import { MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-basic-installation-details',
  templateUrl: './basic-installation-details.component.html',
  styleUrls: ['./basic-installation-details.component.scss'],
  providers: [BasicInstallationDetailsWebService]
})
export class BasicInstallationDetailsComponent implements OnInit {

  @Input() installationDetailsForm: FormGroup
  isView: boolean
  isEdit: boolean
  representativeTypes: Array<RepresentativeTypes>
  chassisNoList$: Observable<Array<AutoChassisNumber>>
  csbNumberList$: Observable<Array<AutoCsbNumber>>
  searviceStaffNameList$: Observable<Array<ServiceStaffName>>
  @Output() diCheckpointByChassisNo = new EventEmitter<InstallationCheckList[]>()
  @Output() fiCheckpointByChassisNo = new EventEmitter<InstallationCheckList[]>()

  constructor(
    private installationPagePresenter: InstallationPagePresenter,
    private basicInstallationDetailsWebService: BasicInstallationDetailsWebService,
    private installationCommonWebService: InstallationCommonWebService,
    private toastr : ToastrService
  ) { }

  ngOnInit() {
    this.viewOrEditOrCreate()
    this.getSystemDate()
    this.representativeTypeDropdown()
    this.getChassisNoList()
    this.getCsbNumberList()
    this.getSearviceStaffNameList()
  }

  private viewOrEditOrCreate() {
    if (this.installationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.installationPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    } 
  }

  getSystemDate() {
    if (this.installationPagePresenter.operation === Operation.CREATE) {
      this.basicInstallationDetailsWebService.getSystemGeneratedDate().subscribe(response => {
        const installationDateDate = response['result']
        this.installationDetailsForm.get('installationDate').patchValue(new Date(installationDateDate))
      })
    }
  }
  representativeTypeDropdown() {
    this.basicInstallationDetailsWebService.getRepresentativeTypeDropdown().subscribe(response => {
      this.representativeTypes = response
    })
  }

  private getChassisNoList() {
    if (this.installationPagePresenter.operation === Operation.CREATE) {
      this.installationDetailsForm.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
          this.autoChassisNo(chassisNo)
        }
        if (valueChange !== null) {
          this.installationPagePresenter.setErrorForChassisNo()
        }
      })
    }
  }
  private getCsbNumberList() {
    this.installationDetailsForm.get('csbNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const csbNumber = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoCsbNumber(csbNumber, this.installationDetailsForm.get('model').value)
      }
      if (valueChange !== null) {
        // this.toastr.warning("CSB No Not Available For This chassis No");
        // this.installationDetailsForm.get('csbNumber').reset();
        // this.installationPagePresenter.setErrorForCsbNumber()
      }
    })
  }
  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.basicInstallationDetailsWebService.chassisNoAuto(chassisNo)
  }
  autoCsbNumber(csbNo: string, model:string) {

    this.csbNumberList$ = this.installationCommonWebService.csbNoAuto(csbNo, model)
    if(this.installationPagePresenter.operation === Operation.CREATE){
    this.csbNumberList$.subscribe((data: any) => {
        if(data.length==0){
          this.toastr.warning("CSB No Not Available");
          this.clearCsbNo();
        }
    }, (error: any) => {
      console.error('An error occurred:', error);
      // Handle the error as needed
    });
  }
  }

  clearCsbNo(){
    setTimeout(() => {
      this.installationDetailsForm.get('csbNumber').reset();
    }, 2000);
  }

  
  

  selectedChassisNumber(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.installationDetailsForm.get('chassisNo').setErrors(null);
    }
    // event.option.value.code
    this.basicInstallationDetailsWebService.getDetailsByChassisNo(event.option.value.code).subscribe(response => {
      let result = response['result'];
      if (result) {
        this.installationDetailsForm.patchValue(result)
        if(result.installationType === 'Field Installation'){  
            this.installationDetailsForm.get('csbNumber').patchValue({code : result.csbNo})
        }else{
            this.installationDetailsForm.get('installationId').patchValue(result.installationId)
            this.installationDetailsForm.get('csbNumber').enable()
        }
        //if (result.installationType) {
            this.patchvalueForCheckList(result)
         //}
      }else{
        this.toastr.error(response['message'])
      }
    })
  }

  patchvalueForCheckList(response: DetailsByChassisNo) {
    if (response.installationType === 'Field Installation') {
      const fiDataTable = this.installationPagePresenter.fInstallationCheckListTableRow.get('fiDataTable') as FormArray;
      fiDataTable.clear()
      this.basicInstallationDetailsWebService.getAllFieldInstallationDetails(response.model,this.installationDetailsForm.get('chassisNo').value.code).subscribe(response => {
        this.fiCheckpointByChassisNo.emit(response)
      })
    } else {
      const dataTable = this.installationPagePresenter.dInstallationCheckListTableRow.get('diDataTable') as FormArray;
      dataTable.clear()
      this.basicInstallationDetailsWebService.getAllDeliveryInstallationDetails(response.model,this.installationDetailsForm.get('chassisNo').value.code).subscribe(response => {
        this.diCheckpointByChassisNo.emit(response)
      })
    }
  }

  selectedCsbNumber(event: MatAutocompleteSelectedEvent){
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.installationDetailsForm.get('csbNumber').setErrors(null);
    }
  }

  onKeyDownChasisNo(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.installationPagePresenter.resetForChasssisNo()
    }
  }

  displayFnChassisNo(chassisNumber: AutoChassisNumber) {
    return chassisNumber ? chassisNumber.code : undefined
  }

  selectionRepresentativeType(event: MatSelectChange){
      if(event.value.representativeType === 'Owner'){
        const name = this.installationDetailsForm.get('customerName').value
        this.installationDetailsForm.get('customerRepName').patchValue(name)
      }else {
        this.installationDetailsForm.get('customerRepName').reset()
      }
  }

  private getSearviceStaffNameList() {
    if (this.installationPagePresenter.operation === Operation.CREATE || this.installationPagePresenter.operation === Operation.EDIT) {
      this.installationDetailsForm.get('searviceStaffName').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const serviceStaffName = typeof valueChange == 'object' ? valueChange.customerName : valueChange
          this.autoSearviceStaffName(serviceStaffName)
        }
        if (valueChange !== null) {
          this.installationPagePresenter.setErrorForSearviceStaffName()
        }
      })
    }
  }
  autoSearviceStaffName(serviceStaffName: string) {
    this.searviceStaffNameList$ = this.basicInstallationDetailsWebService.serviceStaffNameAuto(serviceStaffName)
  }
  selectedSearviceStaffName(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.installationDetailsForm.get('searviceStaffName').setErrors(null);
    }
  }
  displayFnSearviceStaffName(serviceStaffName: ServiceStaffName) {
    return serviceStaffName ? serviceStaffName.employeeName : undefined
  }
  displayFnCsbNumber(csbNo: AutoCsbNumber) {
    
    if(typeof(csbNo)=='object'){
      return csbNo ? csbNo.code : undefined
    }else{
      return csbNo ? csbNo : undefined
    }
    
  }

  compareFnRepresentativeType(c1: RepresentativeTypes, c2: SearchInstallationList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.representativeType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.representativeType;
    }
    return c1 && c2 ? c1.representativeType === c2.representativeType : c1 === c2;
  }
}