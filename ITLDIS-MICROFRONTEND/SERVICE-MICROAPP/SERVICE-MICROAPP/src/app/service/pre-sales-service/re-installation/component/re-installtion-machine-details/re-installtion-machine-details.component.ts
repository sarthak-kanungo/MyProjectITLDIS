import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { FormGroup, FormArray } from '@angular/forms';
import { ReInstallationPagePresenter } from '../re-installation-page/re-installation-page-presenter';
import { RepresentativeDialogData, RepresentativeDialogComponent, BtnAction } from '../representative-dialog/representative-dialog.component';
import { RepresentativeData, chassisNumberList, ReInstallationMachineDetailsList, ViewReInstallation } from '../../domain/re-installation-domain';
import { ReInstallationMachineDetailsWebService } from './re-installation-machine-details-web.service';
import { Observable } from 'rxjs';
import { Operation } from '../../../../../utils/operation-util';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-re-installtion-machine-details',
  templateUrl: './re-installtion-machine-details.component.html',
  styleUrls: ['./re-installtion-machine-details.component.scss'],
  providers: [ReInstallationMachineDetailsWebService]
})
export class ReInstalltionMachineDetailsComponent implements OnInit, OnChanges {

  @Input() machineDetailsTable: FormGroup;
  filteredChassisNoList: Observable<Array<chassisNumberList>>
  chassisNo: string
  uuid: string
  isView: boolean
  chassisId: number
  @Input() viewReInstallation : ViewReInstallation

  constructor(
    public dialog: MatDialog,
    private reInstallationPagePresenter: ReInstallationPagePresenter,
    private reInstallationMachineDetailsWebService: ReInstallationMachineDetailsWebService,
    private toastr: ToastrService,
  ) { }
  ngOnChanges(){
    if(this.viewReInstallation){
      this.viewReInstallation.reInstallationMachineDetailsList.forEach(element => {
        this.chassisId = element.chassisId
        this.chassisNo = element.chassisNo
      })
    }
  }

  ngOnInit() {
    this.viewOrEditOrCreate()
    this.getChassisNo()
  }

  private viewOrEditOrCreate() {
    if (this.reInstallationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.reInstallationPagePresenter.operation === Operation.CREATE) {
      this.reInstallationPagePresenter.addRow()
    }
  }

  getChassisNo() {
    const dataTable = this.machineDetailsTable.get('machineDetailsdataTable') as FormArray;
    dataTable.controls.forEach(value => {
      value.get('chassisNo').valueChanges.subscribe(valueChange => {
        if (valueChange) {
          const machinDetailsIdArray = [];
          dataTable.controls.forEach((element: FormGroup) => {
            if (typeof element.get('chassisNo').value === 'object' && element.get('chassisNo').value) {
              machinDetailsIdArray.push(element.get('chassisNo').value.id);
            }
          });
          const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
          const machineSeries = this.reInstallationPagePresenter.detailsForm.value.series
          console.log("machineSeries ", machineSeries);
          this.filteredChassisNoList = this.reInstallationMachineDetailsWebService.chassisNoAuto(chassisNo, machineSeries.id, machinDetailsIdArray.join())
        }
      })
    })
  }

  chassisNoSelection(event: MatAutocompleteSelectedEvent, index: number) {
    if (event && event['option']['value']) {
      this.chassisNo = event.option.value.code
      this.chassisId = event.option.value.id
      const dataTable = this.machineDetailsTable.get('machineDetailsdataTable') as FormArray;
      const selectedControlUuidValue = dataTable.value[index].uuid
      dataTable.controls.filter((value: FormGroup) => {
        this.uuid = value.get('uuid').value
        if (this.uuid === selectedControlUuidValue) {
          this.reInstallationMachineDetailsWebService.getDetailsByChassisNo(this.chassisNo).subscribe(response => {
            value.get('engineNo').patchValue(response.engineNo)
            value.get('customerName').patchValue(response.customerName)
          })
        }
      })
    }
  }
  displayChassisNoFn(chassisNumber: chassisNumberList) {
    return chassisNumber ? chassisNumber.code : undefined
  }
  addRow(list?: ReInstallationMachineDetailsList) {
    this.setValidationForSubmit(list)
  }
  deleteRow() {
    this.reInstallationPagePresenter.deleteRow()
  }

  setValidationForSubmit(list?: ReInstallationMachineDetailsList) {
    const dataTable = this.machineDetailsTable.get('machineDetailsdataTable') as FormArray;
    const representativesdataTable = this.reInstallationPagePresenter.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
    let selectFlag: boolean
    dataTable.controls.forEach(ele => {
      representativesdataTable.controls.filter(el => {
        if (el.value.chassisNo === ele.value.chassisNo.code || el.value.chassisId === ele.value.chassisNo.id) {
          selectFlag = true
        } else {
          selectFlag = false
        }
      })
    })
    if (dataTable.status === 'VALID') {
      if (selectFlag === true) {
        this.reInstallationPagePresenter.addRow(list)
        this.getChassisNo()
      } else {
        this.toastr.error(`Please fill the Representative Type`, 'Report mandatory field')
      }
    } else {
      this.toastr.error(`Please fill the Chassis No.`, 'Report mandatory field')
    }
  }

  addrepresentative() {
    this.openRepresentativeDialog()
  }

  countOfRepresentatives() {
    const dataTable = this.machineDetailsTable.get('machineDetailsdataTable') as FormArray;
    const representativesdataTable = this.reInstallationPagePresenter.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
    dataTable.controls.forEach(ele => {
      console.log("ele ", ele);
      const id = representativesdataTable.controls.filter(id => id.value.uuid === ele.value.uuid || id.value.chassisId === ele.value.chassisNo.id)
      ele.get('representativeCount').patchValue(id.length)
    })
  }

  private openRepresentativeDialog(): void | any {
    const representativeData = this.setRepresentativeModalData();
    const dialogRef = this.dialog.open(RepresentativeDialogComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: representativeData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Add') {
        representativeData.data.chassisNo = this.chassisNo,
          representativeData.data.chassisId = this.chassisId,
          representativeData.data.uuid = this.uuid
        if (this.chassisNo) {
          if (representativeData.data.representativeType) {
            this.reInstallationPagePresenter.addRepresentativesDetails(representativeData.data)
            this.countOfRepresentatives()
          } else {
            this.toastr.error(`Please fill the Representative Type`, 'Report mandatory field')
          }
        } else {
          this.toastr.error(`Please fill the Chassis No.`, 'Report mandatory field')
        }
      }
    });
  }
  private setRepresentativeModalData() {
    const representativeData = {} as RepresentativeDialogData;
    representativeData.btnAction = [] as Array<BtnAction>;
    representativeData.title = 'Add Representative';
    representativeData.buttonName = ['Add', 'Cancel'];
    representativeData.data = {} as RepresentativeData
    return representativeData;
  }
}