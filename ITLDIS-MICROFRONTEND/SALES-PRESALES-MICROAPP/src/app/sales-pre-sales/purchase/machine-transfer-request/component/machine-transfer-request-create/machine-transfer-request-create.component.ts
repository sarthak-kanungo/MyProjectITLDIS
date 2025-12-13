import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MachineTransferRequestCreateService } from './machine-transfer-request-create.service';
import { of, Observable } from 'rxjs';
// import { LoginService } from '../../../../../login/component/login-form/login.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { CreateMachineTransferRequestService } from '../../pages/create-machine-transfer-request/create-machine-transfer-request.service';
@Component({
  selector: 'app-machine-transfer-request-create',
  templateUrl: './machine-transfer-request-create.component.html',
  styleUrls: ['./machine-transfer-request-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    MachineTransferRequestCreateService
  ]
})
export class MachineTransferRequestCreateComponent implements OnInit {

  @Output() toDealerId = new EventEmitter<number>()

  @Input() isView
  @Input() isApprove
  issuedByName: any
  nameOfDealer: any;
  dealernamePatch: any;
  @Input() public set sendViewResultToCreate(viewData) {
    if (this.isView == true && viewData != undefined) {
      this.machineTransferFrom.disable()
      this.machineTransferFrom.controls.requestNo.patchValue(viewData.requestNumber)
      this.machineTransferFrom.controls.requestStatus.patchValue(viewData.status)
      this.machineTransferFrom.controls.transferFromdealerCode.patchValue(viewData.coDealer.dealerCode);
      this.machineTransferFrom.controls.transferFromdealerName.patchValue(viewData.coDealer.dealerName);

      this.machineTransferFrom.controls.dealerCode.patchValue(viewData.dealerMaster.dealerCode);
      this.machineTransferFrom.controls.dealerName.patchValue(viewData.dealerMaster.dealerName);
      this.machineTransferFrom.controls.gstinNumber.patchValue(viewData.dealerMaster.gstNo);

      this.machineTransferFrom.controls.totalQuantity.patchValue(viewData.totalQuantity)
      
    }
  }
  requestNumberAutoList: Observable<(string | object)[]>;
  transferFromdealerCodeList: Observable<(string | object)[]>;
  public issuedByList = [];
  machineTransferFrom: FormGroup
  loginId
  loginCode
  dateForPatch: Date;
  constructor(
    private fb: FormBuilder,
    private loginService: LoginFormService,
    private createMachineTransferRequestService: CreateMachineTransferRequestService,
    private machineTransferRequestCreateService: MachineTransferRequestCreateService,
    ) { }

  ngOnInit() {
    this.machineTransferFrom = this.createMachineTransferRequestService.getTransferForm();

    this.toDealerChanges()
    this.issuedByName = this.loginService.getLoginUser()
    this.loginId = this.loginService.getLoginUserId()
    this.loginCode = this.loginService.getLoginUserDealerCode()

    this.requestNoChanges()
    this.systemDate()
    if(!this.isView){
      this.getDetailsFromLogin()
    }
  }

  getDetailsFromLogin() {
    this.machineTransferRequestCreateService.getDetailsFromLogindata().subscribe(res => {
      this.machineTransferFrom.controls.dealerCode.patchValue(res['result']['dealerCode']);
      this.machineTransferFrom.controls.dealerName.patchValue(res['result']['dealerName']);
      this.machineTransferFrom.controls.gstinNumber.patchValue(res['result']['gstNumber']);

    })
  }

  systemDate() {
    this.machineTransferRequestCreateService.getDate().subscribe(res => {
      this.dateForPatch = res['result']
    }
    )
  }

  private requestNoChanges() {
    this.machineTransferFrom.get('requestNo').valueChanges.subscribe(changedValue => {
      if(changedValue){
        this.machineTransferRequestCreateService.searchByrequestNo(changedValue).subscribe(res => {
          this.requestNumberAutoList = of(res['result']);
        })
      }
    })
  }

  private toDealerChanges() {
    this.machineTransferFrom.get('transferFromdealerCode').valueChanges.subscribe(changedValue => {
      if(changedValue){
        this.machineTransferFrom.get('transferFromdealerCode').setErrors({'selectFromList':'Select From List'});
        if(typeof changedValue === 'object'){
          this.machineTransferFrom.get('transferFromdealerCode').setErrors(null);
          changedValue = changedValue['dealerCode']
        }  
        this.machineTransferRequestCreateService.searchBytoDealer(changedValue).subscribe(res => {
          this.transferFromdealerCodeList = of(res['result']);
        })
      }else{
        this.machineTransferFrom.get('transferFromdealerCode').setErrors(null);
      }
    })
  }


  getValue(event) {
    console.log('event', event)
    this.toDealerId.emit(event.option['value']['id'])
    console.log('event[\'option\'][\'value\'][\'dealerName\']', event['option']['value']['dealerName'])
    this.machineTransferFrom.get('transferFromdealerName').patchValue(event['option']['value']['dealerName'])
  }
  public selectToDealer(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['dealerCode'] : undefined;
  }




}