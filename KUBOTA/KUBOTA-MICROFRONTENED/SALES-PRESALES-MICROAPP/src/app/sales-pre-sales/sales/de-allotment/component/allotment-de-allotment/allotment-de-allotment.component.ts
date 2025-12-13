import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent, MatCheckboxChange } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { AllotmentDeAllotmentService } from './allotment-de-allotment.service';
import { TableConfig, PatchValue, ETValidationError } from 'editable-table';
import { Subject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectList } from '../../../../../core/model/select-list.model';
import { AllotmentInfoByEnquiryNumber, EditableTableAutocompleteList, MachineDetail } from '../../../../../core/model/allotment-info-by-enquiry-number.model';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Source, DialogResult, RemarkConfiguration } from '../../../../../confirmation-box/confirmation-data';
import { PinCode, AutoComplatePostOffice } from 'ProspectDetails';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
import { AddImplementsContainerService } from '../../../quotation/component/add-implements-container/add-implements-container.service';

@Component({
  selector: 'app-allotment-de-allotment',
  templateUrl: './allotment-de-allotment.component.html',
  styleUrls: ['./allotment-de-allotment.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    AllotmentDeAllotmentService,EnquiryCommonService,AddImplementsContainerService
  ]
})
export class AllotmentDeAllotmentComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;
  show: boolean;
  searchEnquiryNumberList = [] as Array<SelectList>;
  mobileNumberList = [] as Array<SelectList>;
  deallotmentForm: FormGroup
  public onlyImplementsSelected: boolean;
  public pinCodeList: Array<object> = [];
  public districtList: Array<object> = [];
  // public postOfficeList: Array<object> = [];
  public fieldsFromMobileNumber: Array<string> = [];
  public loginUserAddressDetails: object = {} as object;
  public duplicatItemNo: any[]=[];
  onlyimplements:any;

  ////////////////   editabletable   ///////////////
  tableConfig: Array<TableConfig> = [
    {
      title: 'Select',
      formControlName: 'isSelected',
      key: 'isSelected',
      inputField: 'checkbox'
    },
    {
      title: 'Item No.',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'autocomplete',
      displayKey: 'value',
      isNeedValueChanges: true
    },
    {
      title: 'Chassis No.',
      formControlName: 'chassisNo',
      key: 'chassisNo',
      inputField: 'autocomplete',
      displayKey: 'value',
      isNeedValueChanges: true
    }, {
      title: 'Engine No.',
      formControlName: 'engineNo',
      key: 'engineNo',
      inputField: 'input',
      isNeedValueChanges: true
    }, {
      title: 'Color',
      formControlName: 'color',
      key: 'color',
      inputField: 'input',
      displayKey: 'value',
      isNeedValueChanges: true
    }, {
      title: 'GRN No.',
      formControlName: 'grnNo',
      key: 'grnNo',
      inputField: 'input',
      displayKey: 'value',
      isNeedValueChanges: true
    }, {
      title: 'Invoice No.',
      formControlName: 'invoiceNo',
      key: 'invoiceNo',
      inputField: 'input'
    }, {
      title: 'Invoice Date',
      formControlName: 'invoiceDate',
      key: 'invoiceDate',
      inputField: 'input'
    }, {
      title: 'Age in days',
      formControlName: 'ageInDays',
      key: 'ageInDays',
      inputField: 'input'
    }
  ];
  tableConfigForDeAllot: Array<TableConfig> = [
    {
      title: 'Select',
      formControlName: 'isSelected',
      key: 'isSelected',
      inputField: 'checkbox',
      isNeedValueChanges: true
    },
    {
      title: 'Item No.',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'autocomplete',
      displayKey: 'value',
      isNeedValueChanges: true
    },
    {
      title: 'Chassis No.',
      formControlName: 'chassisNo',
      key: 'chassisNo',
      inputField: 'input',
    }, {
      title: 'Engine No.',
      formControlName: 'engineNo',
      key: 'engineNo',
      inputField: 'input'
    }, {
      title: 'Color',
      formControlName: 'color',
      key: 'color',
      inputField: 'input',
      displayKey: 'value'
    }, {
      title: 'GRN No.',
      formControlName: 'grnNo',
      key: 'grnNo',
      inputField: 'input',
      displayKey: 'value'
    }, {
      title: 'Invoice No.',
      formControlName: 'invoiceNo',
      key: 'invoiceNo',
      inputField: 'input'
    }, {
      title: 'Invoice Date',
      formControlName: 'invoiceDate',
      key: 'invoiceDate',
      inputField: 'input'
    }, {
      title: 'Age in days',
      formControlName: 'ageInDays',
      key: 'ageInDays',
      inputField: 'input'
    }
  ];
  tableData: any;
  etFormGroupForTableRow: { [key: string]: any };
  assignTo: EditableTableAutocompleteList;
  getFormData = new Subject<boolean>();
  patchValue: PatchValue[];
  assignListToSelect = [];
  selectPatchValue: object;
  isDeallot: boolean;
  deAllotFlag:number;
  itemNumber: any;
  allotmentInfoFromEnquiryNumber: AllotmentInfoByEnquiryNumber;
  machineDetailList: MachineDetail[];
  public selectedMachineDetailId = null;
  loginUser: import("LoginDto").StorageLoginUser;
  isAllot = true;
  allotmentId: string;
  implementAndAccessoryList = [] as MachineDetail[];
  machineDetailId: any;
  eTValidationError: ETValidationError;
  // implements = new FormControl()
  allotmentType:string = 'Enquiry';
  allotmentQty:number = 1;
  requestNumberAutoList:any[];

  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private allotmentDeAllotmentService: AllotmentDeAllotmentService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private localStorage: LocalStorageService,
    private toastr: ToastrService,
    private enquiryCommonService:EnquiryCommonService,
    private addImplementsContainerService:AddImplementsContainerService,
    private toasterService: ToastrService
  ) {
    this.getLoginUserAddressDetails();
  }

  ngOnInit() {
    this.fieldsFromMobileNumber = ['customerName', 'district', 'pincode', 'address'];
    this.checkFormForDeallotmentOrView()
    this.loginUser = this.localStorage.getLoginUser();
    this.assignListToSelect = [{
      formControlName: 'color',
      list: [{ id: 1, value: 'qty1' }, { id: 2, value: 'qty2' }]
    }, {
      formControlName: 'grnNo',
      list: [{ id: 1, value: 'grnNo1' }, { id: 2, value: 'grnNo2' }]
    }];
    this.deallotmentForm.get('allotmentType').valueChanges.subscribe(allotmentType => {
        this.allotmentType = allotmentType;
        if(!this.isView){
          this.deallotmentForm.get('requestNo').reset();
        }
        if(this.allotmentType == 'Dealer_Transfer'){
          this.allotmentDeAllotmentService.getRequestNumberAutoList('').subscribe(res => {
            this.requestNumberAutoList = res['result'];
          });
        }
    })
    this.deallotmentForm.get('requestNo').valueChanges.subscribe(requestNo => {
      if(requestNo && !this.isView){
        this.deallotmentForm.get('requestNo').setErrors({'selectFromList':'Select From List'})
        if(typeof requestNo == 'object'){
          requestNo = requestNo['requestNo'];
          this.deallotmentForm.get('requestNo').setErrors(null);
        }
        this.allotmentDeAllotmentService.getRequestNumberAutoList(requestNo).subscribe(res => {
          this.requestNumberAutoList = res['result'];
        });
      }else{
        this.deallotmentForm.get('requestNo').setErrors(null);
      }
    });
    this.etFormGroupForTableRow = {
      id: [null],
      itemNo: [{ value: '', disabled: this.isDeallot }],
      chassisNo: [{ value: '', disabled: this.isDeallot}],
      engineNo: [{ value: '', disabled: true }],
      color: [{ value: '', disabled: true }],
      grnNo: [{ value: '', disabled: true }],
      invoiceNo: [{ value: '', disabled: true }],
      invoiceDate: [{ value: '', disabled: true }],
      ageInDays: [{ value: '', disabled: true }],
      isSelected: [{ value: false, disabled: false }]
    }
  }
  private checkFormForDeallotmentOrView() {
    // allotmentId
    this.activatedRoute.paramMap.subscribe(params => {
      if (params.has('allotmentId')) {
        this.isDeallot = true;
        this.isAllot = false;
        this.allotmentId = atob(params.get('allotmentId'));
        this.getAllotmentById(atob(params.get('allotmentId')))
      }
      if (params.has('viewId')) {
        this.isAllot = false;
        this.isView = true;
        this.getAllotmentById(atob(params.get('viewId')));
      }
      if (this.isAllot) {
        this.patchServerDateToForm()
      }
      this.createDeallotment();
    });
  }
  getAllotmentById(allotmentId) {
    this.allotmentDeAllotmentService.getAllotmentById(allotmentId).subscribe(allotmentRes => {
      this.allotmentInfoFromEnquiryNumber = allotmentRes['result'];
      this.deAllotFlag = allotmentRes['result']['isDeAllot'];
      if(this.deAllotFlag){
          this.deallotmentForm.get('deAllotReason').disable();
      }
      this.onlyImplementsSelected = allotmentRes['result']['onlyImplementFlag'] as boolean;
      console.log('this.allotmentInfoFromEnquiryNumber', this.allotmentInfoFromEnquiryNumber);
      this.deallotmentForm.patchValue(this.allotmentInfoFromEnquiryNumber);
      //this.deallotmentForm.get('district').patchValue(this.districtList[this.districtList.findIndex(ele => ele['district'] === allotmentRes['result']['district'])])
      if(this.onlyImplementsSelected){
          this.deallotmentForm.get('mobileNumber').patchValue({'code':allotmentRes['result']['mobileNumber']})
      }
      this.allotmentQty = 0;
      this.machineDetailList = this.allotmentInfoFromEnquiryNumber.machineDetails.filter(res => {
        if (res.productGroup === 'Machinery') {
          this.allotmentQty++;
          return true;
        }
        return false;
      });
      this.deallotmentForm.get('quantity').patchValue(this.allotmentQty);
      this.implementAndAccessoryList = this.allotmentInfoFromEnquiryNumber.machineDetails.filter(res => {
        if (res.productGroup !== 'Machinery') {
          return true;
        }
        return false;
      });
    })
  }
  private machinecheckcount = 0;
  private machineAllotmentDetails:number[] = [];
  machineDetailChecked(event: MatCheckboxChange, id: number) {
    if (event.checked) {
      // this.selectedMachineDetailId = id;
      // this.deallotmentForm.get('machineAllotmentDetails').patchValue({
      //   machineInventoryId:  id 
      // })
      //return;
      if(this.allotmentQty>this.machinecheckcount){
        this.machinecheckcount++;
        this.machineAllotmentDetails.push(id);
      }else{
        this.toastr.warning('Could not select machine more than Allotment Quantity');
        event.source.toggle()
      }
    }else{
      this.machinecheckcount--;
      this.machineAllotmentDetails = this.machineAllotmentDetails.filter(savedid => savedid!=id);
    }
    // this.selectedMachineDetailId = null;
    // this.deallotmentForm.get('machineAllotmentDetails').patchValue(null)
  }
  
  searchValueChanges(event) {
    if (this.onlyImplementsSelected===true) {
      this.onlyimplements='implements'
    }
    else{
      this.onlyimplements=''
    }

    if (event.config.formControlName === 'itemNo' && typeof event.key != 'object' && (event.key || `${event.key}` === '0')) {
      //this.eTValidationError = new ETValidationError(event.config.formControlName, event.tableRow.tableRowId, 'Select from suggestion', { selectList: true })
      // console.log(event.key);
      this.addImplementsContainerService.searchItemNo(event.key, this.onlyimplements, 'ALLOTMENT').subscribe(res => {
          this.assignTo = {
              list: res['result'],
              config: event.config,
              searchKey: event.key
            };
          }, err => {
              
          }
     );
      /*this.allotmentDeAllotmentService.getItemNumber(event.key).subscribe(res => {
        this.assignTo = {
          list: res['result'],
          config: event.config,
          searchKey: event.key
        };
      }, err => {
      });*/
    }
    
    if (event.config.formControlName === 'chassisNo' && typeof event.key != 'object') {
      if(event.tableRow.itemNo.id===undefined){
          this.toastr.error("Select Item No");
          return false;
      }  
      this.eTValidationError = new ETValidationError(event.config.formControlName, event.tableRow.tableRowId, 'Select from suggestion', { selectList: true })
      //let dumyChassiNo: any[]=[144444,255555,366666,477777,588888,];
      this.allotmentDeAllotmentService.getChassisNumbers(event.tableRow.itemNo.id, event.key).subscribe(res => {
        console.log('item res',res);
        this.assignTo = {
          list: res['result'],
          //list: dumyChassiNo,
          config: event.config,
          searchKey: event.key
        };
      }, err => {
      });
    }

    if (event.config.formControlName === 'isSelected' && event.key) {
      this.openDeAllocatImplementDialog();
      this.machineDetailId = event.tableRow.id;
    }
  }


  tableValueChange(event) {
    event.subscribe(res => {
      // console.log('res', res);
    })
  }
  getTableRecordChange(formValue) {
    // console.log('getTableRecordChange=> formValue', formValue);

  }
  count = 0;
  getData() {
    this.count++;
    this.getFormData.next(true);
  }
  etOptionSelected(event) {
    console.log('event--',event);
      this.eTValidationError = new ETValidationError(event.config.formControlName, event.tableRow['tableRowId']);
      //this.optionSelectedChanges.emit(event);
    if (event.option && event.config.formControlName === 'itemNo'  && typeof event.key != 'object') {
         
      this.allotmentDeAllotmentService.getChassisNumbers(event.tableRow.itemNo.id, event.key).subscribe(res => {
          this.assignTo = {
            list: res['result'],
            config: event.config,
            searchKey: event.key
          };
        },
        err => {
      });
       if(this.toCheckDuplicateItemNo(event)){
        let itemNo=event.tableRow.itemNo.itemNo;
        this.duplicatItemNo.push(itemNo);
      }
    }  
    if (event.option && event.config.formControlName === 'chassisNo' && typeof event.key != 'object') {
      //this.deletedTableRecords(event);
      this.eTValidationError = new ETValidationError(event.config.formControlName, event.tableRow.tableRowId, null)
      this.allotmentDeAllotmentService.searchMachineByChassisNumber(event.option.value).subscribe(res => {
       // res.result.itemNo=event.tableRow.itemNo.itemNo;  // added by vinay to set itemNo because on selection its replace itemNo by response that we got from res
        delete res.result.chassisNo;
        this.patchValue = [
          {
            rowIndex: event.rowIndex,
            tableRowId: event.tableRow.tableRowId,
            patchValue: res.result
          }
        ]
      })
    }
    
    
  }
    toCheckDuplicateItemNo(event){
      let flag:boolean = true;
      this.duplicatItemNo.forEach(items =>{
        if(items===event.tableRow.itemNo.itemNo){
          this.toasterService.warning('Item Number already exist');
          flag= false;
          return;
         }
    })
    return true;
    }
    
    deletedTableRecords(event){
      let chk = 0;
        let temp=[];
        this.duplicatItemNo.forEach(value =>{
          event.forEach(element => {
            if (value==element.itemNo.itemNo) {
              chk++;
            } 
          });
          if (chk==0) {  
            temp.push(value)
          }
        })
        this.duplicatItemNo=temp;
    }
    
  ////////////////////   End   ///////////////////////
  createDeallotment() {
    this.deallotmentForm = this.fb.group({
      onlyImplementFlag: [false],
      enquiryNumber: [{ value: null, disabled: this.isDeallot }, Validators.compose([])],
      allotmentNumber: [{ value: null, disabled: true }, Validators.compose([])],
      allotmentDate: [{ value: null, disabled: true }, Validators.compose([])],
      customerName: [{ value: null, disabled: true }, Validators.compose([])],
      mobileNumber: [{ value: null, disabled: true }, Validators.compose([])],
      allotmentType:[{ value: null, disabled: false }, Validators.compose([])],
      requestNo:[{ value: null, disabled: false }, Validators.compose([])],
      country: [{ value: null, disabled: true }, Validators.compose([])],
      state: [{ value: null, disabled: true }, Validators.compose([])],
      district: [{ value: null, disabled: true }, Validators.compose([])],
      pinId: [null],
      pincode: [{ value: null, disabled: true }, Validators.compose([])],
      postOffice: [{ value: null, disabled: true }, Validators.compose([])],
      city: [{ value: null, disabled: true }, Validators.compose([])],
      tehsil: [{ value: null, disabled: true }, Validators.compose([])],
      address: [{ value: null, disabled: true }, Validators.compose([])],
      model: [{ value: null, disabled: true }, Validators.compose([])],
      deAllotReason: [null, Validators.compose([])],
      itemNumber: [{ value: null, disabled: true }, Validators.compose([])],
      itemDescription: [{ value: null, disabled: true }, Validators.compose([])],
      implements: [null, Validators.compose([])],
      machineAllotmentDetails: [null, Validators.compose([])],
      quantity:[{ value: 1, disabled: true }]
    })
    if (this.isDeallot) {
      this.deallotmentForm.get('deAllotReason').setValidators(Validators.required);
      this.deallotmentForm.get('machineAllotmentDetails').clearValidators();
      //this.deallotmentForm.get('deAllotReason').updateValueAndValidity();
    }
    if (this.isView || this.isDeallot) {
      this.deallotmentForm.disable();
      this.deallotmentForm.get('deAllotReason').enable();
    }
    if(!this.isView && !this.isDeallot){
        this.deallotmentForm.get('enquiryNumber').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            this.autoEnquiryNumber(value);
          }
        })
        this.deallotmentForm.get('mobileNumber').valueChanges.subscribe(value => {
          if (typeof value !== 'object' && this.deallotmentForm.get('onlyImplementFlag').value) {
            this.clearFieldsFromMobile();
            this.enableDisableFieldsFromMobile(true);
            this.autoMobileNumber(value);
            return;
          }
          this.enableDisableFieldsFromMobile(false);
        })
        this.deallotmentForm.get('pincode').valueChanges.subscribe(value => {
          if (typeof value === 'string' && this.allotmentType!='Dealer_Transfer') {
            this.getPinCodes(value);
            this.deallotmentForm.get('postOffice').reset();
            this.deallotmentForm.get('city').reset();
            this.deallotmentForm.get('tehsil').reset();
            this.deallotmentForm.get('address').reset();
          }
        });
    }
  }
  private patchServerDateToForm() {
    this.allotmentDeAllotmentService.getSystemGeneratedDate().subscribe(dateRes => {
      this.deallotmentForm.get('allotmentDate').patchValue(dateRes.result);
    })
  }
  displayFn(obj: SelectList): string | number | undefined {
    return obj ? obj.enquiryNumber : undefined;
  }
  displayMobileFn(obj: SelectList): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  enquiryNumberSelected(event: MatAutocompleteSelectedEvent) {
    this.allotmentDeAllotmentService.getEnquiryMachineDetailByEnquiryNumber(event.option.value.id, this.loginUser.id).subscribe(res => {
      this.allotmentInfoFromEnquiryNumber = res['result'];
      if (!this.allotmentInfoFromEnquiryNumber
        || (!this.allotmentInfoFromEnquiryNumber.itemNumber
          && `${this.allotmentInfoFromEnquiryNumber.itemNumber}` !== '0')
      ) {
        this.invalidEnquiry();
      }
      this.deallotmentForm.patchValue(this.allotmentInfoFromEnquiryNumber);
      this.deallotmentForm.get('district').patchValue(this.districtList[this.districtList.findIndex(ele => ele['district'] === this.allotmentInfoFromEnquiryNumber.district)]);
      this.deallotmentForm.get('pincode').patchValue({ pinCode: this.allotmentInfoFromEnquiryNumber.pincode });
      this.machineDetailList = this.allotmentInfoFromEnquiryNumber.machineDetails;
    }, err => {

    });
  }
  private invalidEnquiry(): void {
    let message = 'Item number is not present in enquiry.Please add item number';
    const confirmationData = this.setConfirmationModalData(message);
    confirmationData.buttonName = ['Ok']
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      // if (result === 'Confirm') {
      //   this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      // }

    });
  }

  autoEnquiryNumber(value) {
    /*this.allotmentDeAllotmentService.searchEnquiryCode(value).subscribe(response => {
      this.searchEnquiryNumberList = response.result;
    }, err => {
    })*/
      
      this.enquiryCommonService.searchEnquiryCode(value, 'ALLOTMENT').subscribe(response => {
          this.searchEnquiryNumberList = response['result']
      })  
  }
  autoMobileNumber(value) {
    this.allotmentDeAllotmentService.searchMobileNumber(value).subscribe(response => {
      this.mobileNumberList = response.result;
      if(this.isView || this.isDeallot){
          this.deallotmentForm.get('mobileNumber').patchValue({'code':value})
      }
    }, err => {
    })
  }
  validateForm() {
    this.deallotmentForm.markAllAsTouched();
    console.log('this.deallotmentForm', this.deallotmentForm);
    
    //if ((this.deallotmentForm.get('machineAllotmentDetails').hasError('required') || this.deallotmentForm.get('machineAllotmentDetails').value==null) && !this.deallotmentForm.get('onlyImplementFlag').value && !this.isDeallot) {
    if (this.machineAllotmentDetails.length<1 && !this.deallotmentForm.get('onlyImplementFlag').value && !this.isDeallot) {
      this.toastr.error('Please select machine', 'Required Error');
    }else if (this.deallotmentForm.get('onlyImplementFlag').value && !this.isDeallot) {
        let implementsList = this.deallotmentForm.get('implements').value as object[];
        let saveImplements = [];
        if (!!implementsList &&
          implementsList.length > 0) {
          implementsList.forEach((implement) => {
            if (implement['id']) {
              saveImplements.push({ machineInventoryId: implement['id'] })
            }
          });
        }
        if(saveImplements.length==0){
            this.toastr.error('Please add implement', 'Required Error');
        }else if(this.deallotmentForm.status==='VALID'){
            this.openConfirmDialog();  
        }
    }else if(this.deallotmentForm.status==='VALID'){
        this.openConfirmDialog();  
    }
  }
  saveDeallotment() {
    let implementsList = this.deallotmentForm.get('implements').value as object[];
    let machineAllotmentDetails = this.deallotmentForm.get('machineAllotmentDetails').value;
    let deAllot = {
      allotmentId: this.allotmentId,
      reason: this.deallotmentForm.get('deAllotReason').value
    }
    this.allotmentDeAllotmentService.deAllotMainMachine(deAllot).subscribe(res => {
      this.toastr.success(`De-alloted Successfully`, 'Success');
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }, err => {
      this.toastr.error('De-allotment has Error', 'Error');
    });
  }
  saveAllotment() {
    let implementsList = this.deallotmentForm.get('implements').value as object[];
    let saveImplements = [];
    if (!!implementsList &&
      implementsList.length > 0) {
      implementsList.forEach((implement) => {
        if (implement['id']) {
          saveImplements.push({ machineInventoryId: implement['id'] })
        }
      });
    }
    if(this.machineAllotmentDetails.length>0){
      this.machineAllotmentDetails.forEach((machineId) => {
          saveImplements.push({ machineInventoryId: machineId })
      });
    }
    if (this.deallotmentForm.get('onlyImplementFlag').value) {
      let finalData = this.createOnlyImplemetsObject(saveImplements);
      this.submitAllotment(finalData);
    } else {
      const saveAllotment = this.deallotmentForm.getRawValue();
      if(this.allotmentType == 'Dealer_Transfer'){
        saveAllotment['dealerMachineTransferId'] = this.deallotmentForm.get('requestNo').value.id
      }else {
        saveAllotment['enquiryId']= this.deallotmentForm.get('enquiryNumber').value.id
      }
      saveAllotment['district']=this.deallotmentForm.get('district').value.district,
      saveAllotment['pincode']=this.deallotmentForm.get('pincode').value.pinCode,
      saveAllotment['machineAllotmentDetails']=[
          //{ ...this.deallotmentForm.get('machineAllotmentDetails').value },
          ...saveImplements
        ]
      this.submitAllotment(saveAllotment);
    }
  }
  private createOnlyImplemetsObject(implemetsData) {
    const finalObject = this.deallotmentForm.getRawValue();
    finalObject['machineAllotmentDetails'] = [
      ...implemetsData
    ]
    finalObject['mobileNumber'] = finalObject['mobileNumber'] && typeof finalObject['mobileNumber'] === 'object' ? finalObject['mobileNumber']['code'] : finalObject['mobileNumber'];
    finalObject['district'] = finalObject['district'] && typeof finalObject['district'] === 'object' ? finalObject['district']['district'] : finalObject['district'];
    finalObject['pincode'] = finalObject['pincode'] && typeof finalObject['pincode'] === 'object' ? finalObject['pincode']['pinCode'] : finalObject['pincode'];
    return finalObject;
  }
  private submitAllotment(saveAllotment: object) {
    this.allotmentDeAllotmentService.machineAllotment(saveAllotment).subscribe(saveRes => {
      this.toastr.success(`Alloted Successfully`, 'Success');
      this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    }, err => {
      this.toastr.error('Allotment has Error.', 'Error');
    });
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to Allot machine?';
    if (this.isDeallot) {
      message = 'Do you want to De-Allot machine?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isDeallot) {
        this.saveAllotment();
      }
      if (result === 'Confirm' && this.isDeallot) {
        this.saveDeallotment();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  private openDeAllocatImplementDialog(): void | any {
    let message = 'Do you want to de-allotment implements?';
    const confirmationData = this.setReasonConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      console.log('The dialog was closed', result);
      if (result.button.toLowerCase() === 'de-allot') {
        this.deAllocatImplement(result.remarkText);
      }
    });
  }
  private setReasonConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.remarkConfig = {} as RemarkConfiguration;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'De-Allotment';
    confirmationData.message = message;
    confirmationData.buttonName = ['De-Allot', 'Cancel'];
    confirmationData.remarkConfig.source = Source.REQUIRED;
    return confirmationData;
  }
  deAllocatImplement(reason: string) {
    const deAllot = {
      allotmentId: this.allotmentId,
      machineDetailId: this.machineDetailId,
      reason
    }
    this.allotmentDeAllotmentService.deAllotMachineImplements(deAllot).subscribe(res => {
      console.log('res', res);
      this.toastr.success(`De-alloted Successfully`, 'Success');
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    }, err => {
      this.toastr.error('De-allotment has Error', 'Error');
    })
  }
  redirectToSearchPage() {
    if (this.isAllot) {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
  }
  clearForm() {
    this.deallotmentForm.reset();
    this.machineDetailList = [];
    this.implementAndAccessoryList = [];
    this.deallotmentForm.get('mobileNumber').disable();
    this.onlyImplementsSelected=false;
  }
  public checkBoxChange(event: MatCheckboxChange) {
    this.onlyImplementsSelected = event && event.checked ? true : false;
    if (event && event.checked) {
      this.deallotmentForm.get('mobileNumber').reset();
      this.deallotmentForm.get('mobileNumber').enable();
    } else {
      this.deallotmentForm.get('mobileNumber').reset();
      this.deallotmentForm.get('mobileNumber').disable();
    }
  }
  public mobileNumberSelected(event: MatAutocompleteSelectedEvent) {
    if (event && event instanceof MatAutocompleteSelectedEvent) {
      this.getDataFromMobileNumber(event.option.value.code);
    }
  }
  public getDataFromMobileNumber(mobilenumber: string) {
    this.allotmentDeAllotmentService.getDataFromMobileNumber(mobilenumber).subscribe(res => {
      if (res) {
        this.patchValueToCustomer(res['result']);
      }
    })
  }
  displayFnForPinCode(pin: PinCode) {
    return pin ? pin.pinCode : undefined
  }
  displayFnForPostOffice(postOfficeList: AutoComplatePostOffice) {
    return postOfficeList ? postOfficeList.postOffice : undefined
  }
  pinCodeChanged(event: MatAutocompleteSelectedEvent) {
    if (event && event instanceof MatAutocompleteSelectedEvent && this.allotmentType!='Dealer_Transfer') {
      this.getMainAddressData(event.option.value)
    }
  }
  private clearFieldsFromMobile() {
    this.fieldsFromMobileNumber.forEach(field => this.deallotmentForm.get(field).reset())
  }
  private enableDisableFieldsFromMobile(isEnabled: boolean = false) {
    isEnabled ?
      this.fieldsFromMobileNumber.forEach(field => this.deallotmentForm.get(field).enable()) :
      this.fieldsFromMobileNumber.forEach(field => this.deallotmentForm.get(field).disable())
  }
  public getMainAddressData(pinCodeObj: object) {
    this.allotmentDeAllotmentService.getMainAddressDataFromPin(pinCodeObj['pinID'], pinCodeObj['cityId']).subscribe(res => {
      if (res) {
        this.deallotmentForm.get('city').patchValue(res['result']['city']);
        this.deallotmentForm.get('tehsil').patchValue(res['result']['tehsil']);
        this.deallotmentForm.get('postOffice').patchValue(res['result']['postOffice']);
        this.deallotmentForm.get('pinId').patchValue(res['result']['pinID']);
      }
    })
  }
  public patchValueToCustomer(response: object) {
    this.fieldsFromMobileNumber.forEach(field => this.deallotmentForm.get(field).patchValue(response[field]));
    this.deallotmentForm.get('city').patchValue(response['city']);
    this.deallotmentForm.get('tehsil').patchValue(response['tehsil']);
    this.deallotmentForm.get('address').patchValue(response['address']);
    this.deallotmentForm.get('postOffice').patchValue(response['postOffice']);
    this.deallotmentForm.get('pincode').patchValue({ pinCode: response['pinCode'] });
    this.deallotmentForm.get('pinId').patchValue(response['pinId']);
    this.deallotmentForm.get('district').patchValue(this.districtList[this.districtList.findIndex(ele => ele['district'] === response['district'])]);
  }
  private getPinCodes(searchKey: string,pincode?:string) {
    const districtObj = this.deallotmentForm.get('district').value;
    if (districtObj && typeof districtObj === 'object') {
      this.allotmentDeAllotmentService.getPinCodeAuto(districtObj['districtId'], searchKey).subscribe(res => {
        if (res) {
          this.pinCodeList = res['result']
          if(pincode){
              this.deallotmentForm.get('pincode').patchValue({ pinCode: pincode });
          }
        }
      })
    }

  }
  private getLoginUserAddressDetails() {
    this.allotmentDeAllotmentService.getLoginUserAddressDetails().subscribe(res => {
      if (res) {
        this.loginUserAddressDetails = res['result'];
        this.districtList = res['result'];
        this.deallotmentForm.get('country').patchValue(res['result'][0]['country']);
        this.deallotmentForm.get('state').patchValue(res['result'][0]['state']);
      }
    })
  }
  
  displayFnAutoRequest(value){
    return (value && typeof value === 'object')?value.requestNo:value;
  }

  requestNumberSelected(event){
    const requestNo = event.option.value.requestNo;
    this.allotmentDeAllotmentService.getTransferRequestDetails(requestNo).subscribe(res => {
      let allotmentInfoFromRequestNumber = res['result'];
      let dealerDetails = allotmentInfoFromRequestNumber.DealerDetails;
      this.deallotmentForm.patchValue(dealerDetails);
      this.districtList.push({'district':dealerDetails.district});
      this.deallotmentForm.get('district').patchValue(this.districtList[this.districtList.findIndex(ele => ele['district'] === dealerDetails.district)]);
      this.deallotmentForm.get('pincode').patchValue({ pinCode: dealerDetails.pincode });
      this.deallotmentForm.get('mobileNumber').patchValue(dealerDetails.mobileNumber?dealerDetails.mobileNumber:'');
      this.machineDetailList = allotmentInfoFromRequestNumber.MachineDetails;
      this.allotmentQty = parseInt(this.deallotmentForm.get('quantity').value);
    });
  }
}
