
import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MdComplaintsService } from './md-complaints.service';
import { ItemNoDomain, DetailsByItemNoDomain, TypeDomain, MachineDescripancyComplaintDetail, ViewEditMachineDescComplaintDomain } from 'MachineDescripancyComplaintModule';
import { BaseDto } from 'BaseDto';
import { MachineDescripancyCommonService } from '../machine-descripancy-common.service';
import { ActivatedRoute } from '@angular/router';
import { MachineDescripancyComplaintsCreateService } from '../machine-descripancy-complaints-create/machine-descripancy-complaints-create.service';

@Component({
  selector: 'app-md-complaints',
  templateUrl: './md-complaints.component.html',
  styleUrls: ['./md-complaints.component.scss'],
  providers: [MachineDescripancyComplaintsCreateService]
})
export class MdComplaintsComponent implements OnInit {

  isView: boolean
  isEdit: boolean
  isApprove: boolean
  isSelected: boolean
  complaintForm: FormGroup
  searchItemNo: BaseDto<Array<ItemNoDomain>>
  itemDataDto: BaseDto<DetailsByItemNoDomain>
  dropdownType: BaseDto<Array<TypeDomain>>

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private mdComplaintsService: MdComplaintsService,
    private machineDescripancyCommonService: MachineDescripancyCommonService,
    private machineComplantRt: ActivatedRoute,
    private machineDescripancyComplaintsCreateService: MachineDescripancyComplaintsCreateService
  ) {
    this.dropdownType = {} as BaseDto<Array<TypeDomain>>
    this.dropdownType.result = []
  }

  ngOnInit() {
    this.checkOperationType()
    this.initOperation()
    this.patchOrCreate()
  }

  private patchOrCreate() {
    if (this.isView) {
      console.log(`Viewing Form machine complaints`)
    }
    else if (this.isEdit) {
      console.log(`Editing Form`)
      this.parseIdAndPatchEditForm()
    }
    else {
      console.log(`creating Form`)
      this.createComplaintDetailsForm()
      this.getType()
    }
  }

  private checkOperationType() {
    this.isView = this.machineComplantRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.machineComplantRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    this.isApprove = this.machineComplantRt.snapshot.routeConfig.path.split('/')[0] == 'approve'
  }
  private initOperation() {
    if (this.isView || this.isApprove) {
      this.isView = true;
      this.viewMachineComplaintForm()
    }
    else if (this.isEdit) {
      console.log('In Edit');
      this.editMachineComplaintForm()
    }
    else {
      this.createComplaintDetailsForm()
    }
  }
  private parseIdAndPatchEditForm() {
    this.machineComplantRt.params.subscribe(prms => this.fetchDataForEditId(prms['machineComplaintEditId']))
  }

  private fetchDataForEditId(machineComplaintEditId: number) {
    console.log('Machine Complaint Edit Id ' + machineComplaintEditId);
    this.machineDescripancyComplaintsCreateService.getMachineDescComplaintById(machineComplaintEditId).subscribe(
    )
  }
  editMachineComplaintForm() {
    this.complaintForm = this.fb.group({
      complaintFormArray: this.fb.array([])
    });

    this.machineDescripancyCommonService.emComplaintDetails.subscribe((dmn: ViewEditMachineDescComplaintDomain) => {
      dmn.machineDescripancyComplaintDetails.forEach(dtl => {
        this.addRow(dtl)
      })
    })
  }
  viewMachineComplaintForm() {
    this.complaintForm = this.fb.group({
      complaintFormArray: this.fb.array([])
    });
    this.machineDescripancyCommonService.emComplaintDetails.subscribe((dmn: ViewEditMachineDescComplaintDomain) => {
      dmn.machineDescripancyComplaintDetails.forEach(dtl => {
        this.addRow(dtl)
      })
    })
  }

  private createComplaintDetailsForm() {
    this.complaintForm = this.fb.group({
      complaintFormArray: this.fb.array([])
    });
    this.machineDescripancyCommonService.complaintDetailsForm = this.complaintForm
    this.addRow()
  }

  complaintDetailsRow(domain?: MachineDescripancyComplaintDetail) {
    let compDetails: FormGroup = null
    if (this.isView) {
      compDetails = this.buildRowForView(domain)
    }
    else if (this.isEdit) {
      compDetails = this.buildRowForEdit(domain)
    }
    else {
      compDetails = this.buildRowForCreate()
      compDetails.controls.itemNo.valueChanges.subscribe(value => {
        this.autoItemNumber(value)
      })
    }
    return compDetails
  }

  buildRowForCreate() {
    return this.fb.group({
      isSelected: this.fb.control(false),
      itemDescription: this.fb.control('', Validators.required),
      itemNo: this.fb.control('', Validators.required),
      quantity: this.fb.control('', Validators.required),
      remarks: this.fb.control('', Validators.required),
      type: this.fb.control('', Validators.required)
    });
  }

  buildRowForView(domain?: MachineDescripancyComplaintDetail) {
    console.log('View Domain', domain);

    let grp = this.fb.group({
      isSelected: [{ value: '', disabled: true }],
      itemDescription: this.fb.control({ value: domain.itemDescription, disabled: true }),
      itemNo: this.fb.control({ value: domain.itemNo, disabled: true }),
      quantity: this.fb.control({ value: domain.quantity, disabled: true }),
      remarks: this.fb.control({ value: domain.remarks, disabled: true }),
      type: this.fb.control({ value: domain.type, disabled: true }),
      approvedQuantity: this.fb.control({ value: domain.approvedQuantity, disabled: true })
    });
    grp.controls.itemNo.patchValue({ itemNo: domain.itemNo, id: 1 })
    let type = { complaintType: domain.type, id: 1 }
    this.dropdownType.result.push(type)
    grp.controls.type.patchValue(type)

    return grp
  }

  buildRowForEdit(domain?: MachineDescripancyComplaintDetail) {
    console.log('Edit Domain');
    let grp = this.fb.group({
      isSelected: [{ value: '', disabled: true }],
      itemDescription: this.fb.control({ value: domain.itemDescription, disabled: true }),
      itemNo: this.fb.control({ value: domain.itemNo, disabled: true }),
      quantity: this.fb.control({ value: domain.quantity, disabled: false }),
      remarks: this.fb.control({ value: domain.remarks, disabled: false }),
      type: this.fb.control({ value: domain.type, disabled: false }),
      approvedQuantity: this.fb.control({ value: domain.approvedQuantity, disabled: true })
    });
    grp.controls.itemNo.patchValue({ itemNo: domain.itemNo, id: 1 })
    let type = { complaintType: domain.type, id: 1 }
    this.dropdownType.result.push(type)
    grp.controls.type.patchValue(type)

    return grp

  }

  private getType() {
    this.mdComplaintsService.getComplaintType().subscribe(res => {
      this.dropdownType = res as BaseDto<Array<TypeDomain>>
    })
  }

  optionSelectedItemNo(event, row: FormGroup) {
    console.log(row)
    this.mdComplaintsService.getDetailsByItemId(event.option.value.id).subscribe(response => {
      console.log('response', response);
      this.itemDataDto = response as BaseDto<DetailsByItemNoDomain>
      row.controls.itemDescription.patchValue(this.itemDataDto.result.itemDescription)
    })
  }

  addRow(domain?: MachineDescripancyComplaintDetail) {
    let complaintDetails = this.complaintForm.controls.complaintFormArray as FormArray
    var isinvalid = false;
    if(complaintDetails.controls.length>0){
      complaintDetails.controls.forEach( row => {
        if(row.invalid){
          row.markAllAsTouched();
          isinvalid = true;
        }
      })
    }
    if(isinvalid){
      return false;
    }
    complaintDetails.push(this.complaintDetailsRow(domain))
  }

  deleteRow() {
    let complaintDetails = this.complaintForm.controls.complaintFormArray as FormArray;
    let nonSelected = complaintDetails.controls.filter(machinery => !machinery.value.isSelected)
    console.log('-----', complaintDetails.length);
    console.log('=====', nonSelected.length);
    if ((complaintDetails.length - nonSelected.length)) {
      complaintDetails.clear()
      nonSelected.forEach(el => complaintDetails.push(el))
    } else {
      this.toastr.warning('Select Atleast One Row', 'Item Details')
    }
  }

  autoItemNumber(value) {
    this.mdComplaintsService.getItemNumber(value).subscribe(response => {
      console.log('response', response);
      this.searchItemNo = response as BaseDto<Array<ItemNoDomain>>
    })
  }

  complaintDetailsArray() {
    return this.complaintForm.controls.complaintFormArray as FormArray;
  }
  displayItemNoFn(itNo: ItemNoDomain) {
    return itNo ? itNo.itemNo : undefined
  }

  keyDigits(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}
