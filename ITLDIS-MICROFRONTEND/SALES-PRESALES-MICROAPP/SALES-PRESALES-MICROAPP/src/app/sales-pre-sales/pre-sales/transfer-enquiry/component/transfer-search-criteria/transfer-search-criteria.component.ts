import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { SearchEnquiryCode, DropDownTehsil, DropDownSalePerson, TransferEnquiryDomain, SearchTransferEnquiryByValue, TransferByEmployeeId, TransfertoSalesPerson } from 'TransferSearchCriteria';
import { TransferSearchCriteriaService } from './transfer-search-criteria.service';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { LoginFormService } from '../../../../../root-service/login-form.service'; import { MatCheckbox, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-transfer-search-criteria',
  templateUrl: './transfer-search-criteria.component.html',
  styleUrls: ['./transfer-search-criteria.component.scss'],
  providers: [EnquiryCommonService, TransferSearchCriteriaService]
})

export class TransferSearchCriteriaComponent implements OnInit {
  inputCriteriaForm: FormGroup;
  transferEnquiryToForm: FormGroup;
  showDiv: boolean
  salesPersonId: number
  states: string[] = [
    'Hot', 'Warm', 'Cold',
  ];
  autoCloses: string[] = [
    'YES', 'NO',
  ];
  btnClick: number
  loginUserId: number;
  searchEnquiryCode: BaseDto<Array<SearchEnquiryCode>>
  dropdownTehsil: BaseDto<Array<DropDownTehsil>>
  dropdownSalePerson: BaseDto<Array<DropDownSalePerson>>
  transferEnquiryByValue: Array<SearchTransferEnquiryByValue>
  transfertoSalesPerson: BaseDto<Array<TransfertoSalesPerson>>
  transferByEmployeeId = {} as TransferByEmployeeId
  ischecked: boolean
  enquriesIds: number[]
  textValue: String;
  constructor(
    private fb: FormBuilder,
    private enquiryCommonService: EnquiryCommonService,
    private transferSearchCriteriaService: TransferSearchCriteriaService,
    private toastr: ToastrService,
    private loginFormService: LoginFormService,
    private enqRt: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
  }

  ngOnInit() {
    this.createinputCriteriaForm();
    this.sendtransferEnquiryToForm()
    this.dropDownTehsil(this.textValue);
    this.dropDownSalesPerson()
    this.serchEnquirytoTransfer()
  }

  createinputCriteriaForm() {
    this.inputCriteriaForm = this.fb.group({
      salesPerson: [null, Validators.compose([Validators.required])],
      taluka: [null, Validators.compose([Validators.required])],
      autoClose: [null, Validators.compose([Validators.required])],
      enquiryNumber: [null],
      enquiryType: [null],
    })
    this.inputCriteriaForm.controls.enquiryNumber.valueChanges.subscribe(value => {
      this.autoEnquiryNo(value)
    })
    this.inputCriteriaForm.controls.taluka.valueChanges.subscribe(value => {
      this.dropDownTehsil(value)
    })
    this.serchEnquirytoTransfer()
  }

  sendtransferEnquiryToForm() {
    this.transferEnquiryToForm = this.fb.group({
      transferToEmployeeId: [null, Validators.compose([Validators.required])]
    })
  }

  autoEnquiryNo(event) {
    this.enquiryCommonService.searchEnquiryCode(event, 'TRANSFER_ENQUIRY').subscribe(response => {
      this.searchEnquiryCode = response as BaseDto<Array<SearchEnquiryCode>>
    })
  }

  dropDownTehsil(textValue) {
    this.transferSearchCriteriaService.getAllTehsil(textValue).subscribe(response => {
      console.log('tehsil', response);
      this.dropdownTehsil = response as BaseDto<Array<DropDownTehsil>>
      console.log('allTehsil :', this.dropdownTehsil);
    })
  }

  dropDownSalesPerson() {
    this.enquiryCommonService.getAllSalesPerson().subscribe(response => {
      console.log('salesPerson', response);
      this.dropdownSalePerson = response as BaseDto<Array<DropDownSalePerson>>
    })
  }

  onSelectionSalesPerson(value) {
    const sendData = {} as TransferEnquiryDomain
    sendData.salesPerson = value
    sendData.userId = this.loginUserId
    console.log('sendData', sendData);
    this.transferSearchCriteriaService.getEnquiryToTransfer(sendData).subscribe(response => {
      console.log('response', response);
      this.transferEnquiryByValue = response.result
    })
    this.transferSearchCriteriaService.selectedSalesPersonIdsForTransfer = []
    this.serchEnquirytoTransfer()
  }

  serchEnquirytoTransfer() {
    console.log('submit clicked');
    this.showDiv = true
    const sendData = this.inputCriteriaForm.value as TransferEnquiryDomain
    sendData.userId = this.loginUserId
    sendData.enquiryNumber = sendData.enquiryNumber ? sendData.enquiryNumber['enquiryNumber'] : null
    console.log('sendData', sendData);
    this.transferSearchCriteriaService.getEnquiryToTransfer(sendData).subscribe(response => {
      console.log('response', response);
      this.transferEnquiryByValue = response.result
    })
  }

  clearEnquirytoTransfer() {
    this.inputCriteriaForm.reset()
    this.serchEnquirytoTransfer()
  }


  onSelectEnquiry(transferEnquiry: SearchTransferEnquiryByValue, enq: MatCheckbox) {
    console.log(enq)
    console.log('On Select', `Id - ${transferEnquiry.id} Checked - ${!enq.checked}`);

    if (enq.checked) {
      this.transferSearchCriteriaService.addToSelectedEnquiries(transferEnquiry.id)
      this.transferSearchCriteriaService.addToSelectedSalesPersonName(transferEnquiry.salesPersonId)
    }
    else {
      this.transferSearchCriteriaService.removeFromSelectedEnquiries(transferEnquiry.id)
      this.transferSearchCriteriaService.removeFromSelectedSalesPersonName(transferEnquiry.salesPersonId)
    }
    this.transferSearchCriteriaService.getSalesPersonNameInTransfer(this.transferSearchCriteriaService.selectedSalesPersonIdsForTransfer).subscribe(response => {
      console.log('transfertosalesperson', response);
      this.transfertoSalesPerson = response as BaseDto<Array<TransfertoSalesPerson>>
    })
  }

  allSelectEnquiry(allSelect: MatCheckbox) {
    console.log(allSelect.checked);
    this.transferEnquiryByValue.forEach(item => {
      item.isSelected = allSelect.checked
      if (allSelect.checked) {
        this.transferSearchCriteriaService.addToSelectedEnquiries(item.id)
        this.transferSearchCriteriaService.addToSelectedSalesPersonName(item.salesPersonId)
      }
      else {
        this.transferSearchCriteriaService.removeFromSelectedEnquiries(item.id)
        this.transferSearchCriteriaService.removeFromSelectedSalesPersonName(item.salesPersonId)
      }
    })

    this.transferSearchCriteriaService.getSalesPersonNameInTransfer(this.transferSearchCriteriaService.selectedSalesPersonIdsForTransfer).subscribe(response => {
      console.log('transfertosalesperson', response);
      this.transfertoSalesPerson = response as BaseDto<Array<TransfertoSalesPerson>>
    })

  }

  submitTransfer() {
    console.log(this.transferSearchCriteriaService.selectedEnquiryIdsForTransfer.length);
    if (this.transferSearchCriteriaService.selectedEnquiryIdsForTransfer.length > 0 && this.transferSearchCriteriaService.selectedEnquiryIdsForTransfer.length <= Infinity) {
      this.openConfirmDialog()
    } else {
      this.toastr.warning('Atleast Select one Transfer Enquiry is mandatory', 'Report One Select Row')
    }
  }

  submitTransferEnquiry() {
    this.transferSearchCriteriaService.sendTransferEnquiry.enquiryId = this.transferSearchCriteriaService.selectedEnquiryIdsForTransfer
    this.transferSearchCriteriaService.sendTransferEnquiry.transferByEmployeeId = {
      id: this.loginUserId
    }
    this.transferSearchCriteriaService.sendTransferEnquiry.transferToEmployeeId = {
      id: this.transferEnquiryToForm.value.transferToEmployeeId.id
    }
    console.log(this.transferSearchCriteriaService.sendTransferEnquiry);
    this.transferSearchCriteriaService.addTransfer(this.transferSearchCriteriaService.sendTransferEnquiry).subscribe(formdata => {
      console.log('formData', formdata);
      if (formdata) {
        this.toastr.success('Enquiry Transfer Successfully', 'Success')
        this.router.navigate(['../'], { relativeTo: this.enqRt })
      }
    })
  }

  displayEnquiryNoFn(enq: SearchEnquiryCode) {
    return enq ? enq.enquiryNumber : null
  }

  displayTehsil(selectedOption?: object): string | undefined {
      if (!!selectedOption && typeof selectedOption === 'string') {
        return selectedOption;
      }
      return selectedOption ? selectedOption['tehsil'] : undefined;
  }
  
  private openConfirmDialog(): void | any {
    //let message = 'Do you want to Add Enquiry Follow Up?';
    let message = 'Do you want to Transfer Enquiry?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.submitTransferEnquiry();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
}

