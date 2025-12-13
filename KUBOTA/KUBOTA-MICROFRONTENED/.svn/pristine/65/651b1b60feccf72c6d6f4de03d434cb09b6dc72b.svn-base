import { Component, OnInit, forwardRef, EventEmitter, Output, OnDestroy } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, ControlValueAccessor, Validator, AbstractControl, ValidationErrors, NG_VALUE_ACCESSOR, NG_VALIDATORS } from '@angular/forms';
import { GoodsReceiptNoteCreateService } from './goods-receipt-note-create.service';
import { map } from 'rxjs/operators';
import { SearchAccPacInvoiceByInvoiceNumber, AccPacInvoicePartDetail } from './goods-receipt-note-create';
import { BehaviorSubject } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { StorageLoginUser } from 'LoginDto';
import { CreateGrnFormService } from '../../pages/create-goods-receipt-note/create-grn-form.service';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-goods-receipt-note-create',
  templateUrl: './goods-receipt-note-create.component.html',
  styleUrls: ['./goods-receipt-note-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    GoodsReceiptNoteCreateService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => GoodsReceiptNoteCreateComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => GoodsReceiptNoteCreateComponent),
      multi: true
    }
  ]
})
export class GoodsReceiptNoteCreateComponent implements OnInit, ControlValueAccessor, Validator, OnDestroy {
  public onTouched: () => void = () => { };
  // grnTypeList: SelectList[];
  grnTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  grnstatus: string[] = [
    'Pending for Approval', 'Approved', 'Posted in ACCPAC'
  ];
  transporterNameList$ = new BehaviorSubject<SelectList[]>(undefined);currentDate: Date;
;
  grnbys: string[] = [
    'Aditya', 'Viraj', 'Sanket'
  ];
  machineGrnFrom: FormGroup
  invoiceNumberList: any;
  loginUserId: number;
  @Output() accPacInvoicePartDetailChange = new EventEmitter<Array<AccPacInvoicePartDetail>>();
  loginUser: StorageLoginUser;
  constructor(private fb: FormBuilder,
    private goodsReceiptNoteCreateService: GoodsReceiptNoteCreateService,
    private login: LoginFormService,
    private localStorageService: LocalStorageService,
    private createGrnFormService:CreateGrnFormService,
    private autocompleteService: AutocompleteService,
    private toastr : ToastrService
  ) { }
  ngOnInit() {
    this.loginUser = this.localStorageService.getLoginUser();
    this.loginUserId = this.login.getLoginUserId()
    this.machineGrnFrom = this.goodsReceiptNoteCreateService.createmachineGrnFrom();
    this.setGrnType(this.loginUserId);
    this.setTransporterName();
    this.invoiceNumberValueChange();
    if (this.loginUser) {
      this.machineGrnFrom.get('grnBy').patchValue(this.loginUser.username);
    }
    this.getDateFromServer();
  }
  private getDateFromServer() {
    this.goodsReceiptNoteCreateService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes['result']) {
        this.machineGrnFrom.get('grnDate').patchValue(dateRes['result']);
        this.currentDate = new Date(dateRes['result']);
      }
    });
  }
  displayFn(obj: { id: number, value: string | number }): string | number | undefined {
    return obj ? obj.value : undefined;
  }
  private invoiceNumberValueChange() {
    this.machineGrnFrom.get('accPacInvoice').valueChanges.subscribe(value => {
      if (value){
        if(this.machineGrnFrom.get('grnType').value){
            this.resetFormOnInvoiceNumberValueChange();
            this.setSearchAccPacInvoiceNumber(value);
        }else{
          this.toastr.error('Please Select GRN Type');
          this.machineGrnFrom.get('accPacInvoice').reset();
          return false;
        }
      }else{
        this.invoiceNumberList = null;
      }
    });
  }
  grnTypeSelectionChange(event:MatSelectChange){
    this.resetFormOnGRNTypeSelectionChange();
  }
  private resetFormOnGRNTypeSelectionChange() {
    this.createGrnFormService.itemDetailControl.patchValue({ resetForm: true });
    this.createGrnFormService.accessoryDetailControl.patchValue({ resetForm: true });
    for (const key in this.machineGrnFrom.controls) {
      if (this.machineGrnFrom.controls.hasOwnProperty(key)) {
        if (key=== 'grnDate'|| key=== 'grnType'|| key=== 'dmsGrnNumber') {
          continue;
        }
        const controls = this.machineGrnFrom.controls[key];
        controls.reset();
      }
    }
    this.machineGrnFrom.get('grnBy').patchValue(this.loginUser.username);
  }
  private resetFormOnInvoiceNumberValueChange() {
    this.createGrnFormService.itemDetailControl.patchValue({ resetForm: true })
    this.createGrnFormService.accessoryDetailControl.patchValue({ resetForm: true });
    for (const key in this.machineGrnFrom.controls) {
      if (this.machineGrnFrom.controls.hasOwnProperty(key)) {
        if (key=== 'accPacInvoice' || key=== 'grnDate'|| key=== 'grnType'|| key=== 'dmsGrnNumber') {
          continue;
        }
        const controls = this.machineGrnFrom.controls[key];
        controls.reset();
      }
    }
  }
  private setGrnType(userId: number) {
    this.goodsReceiptNoteCreateService.getGrnType(userId.toString()).subscribe(grnTypeRes => {
      grnTypeRes && this.grnTypeList$.next(grnTypeRes.result)
    });
  }
  private patchValueToGrnType(grnType, grnTypeList: SelectList[]) {
    if (grnTypeList) {
      let patchVal = grnTypeList.find((ele => ele.value === grnType));
      this.machineGrnFrom.controls.grnType.patchValue(patchVal);
      return;
    }
    const grnTypeListSubscription = this.grnTypeList$.subscribe(grnTypeList => {
      if (grnTypeList && grnTypeList.length > 0) {
        let patchVal = grnTypeList.find((ele => ele.value === grnType));
        this.machineGrnFrom.controls.grnType.patchValue(patchVal);
        grnTypeListSubscription.unsubscribe();
        return;
      }
    });
  }
  private patchValueToTransporter(transporter: string, transporterList: SelectList[]) {
    if (transporterList) {
      let patchVal = transporterList.find((ele => ele.value === transporter));
      this.machineGrnFrom.controls.transporter.patchValue(patchVal);
    }
    this.transporterNameList$.subscribe(transporterList => {
      console.log(transporterList);
      if (transporterList && transporterList.length > 0) {
        let patchVal = transporterList.find((ele => ele.value === transporter));
        this.machineGrnFrom.controls.transporter.patchValue(patchVal);
        console.log(patchVal);
        this.transporterNameList$.unsubscribe();
        return;
      }
    });
  }
  private setTransporterName() {
    this.goodsReceiptNoteCreateService.getTransporterName().subscribe(transporterNameRes => {
      transporterNameRes && (this.transporterNameList$.next(transporterNameRes['result']));
    });
  }
  setSearchAccPacInvoiceNumber(searchValue: any) {
    if (typeof searchValue === 'object') {
      searchValue = searchValue.value
    }
    this.invoiceNumberList = this.goodsReceiptNoteCreateService.searchAccPacInvoiceNumber(searchValue, this.machineGrnFrom.get('grnType').value.value)
      .pipe(
        map(transporterNameRes => transporterNameRes && transporterNameRes['result'])
      );
      this.autocompleteService.validateSelectedFromList(searchValue, this.invoiceNumberList, this.machineGrnFrom.get('accPacInvoice'))
  }
  searchAccPacInvoiceByInvoiceNumber(event: MatAutocompleteSelectedEvent) {
    console.log('searchAccPacInvoiceByInvoiceNumber: ', event);
    this.goodsReceiptNoteCreateService.searchAccPacInvoiceByInvoiceNumber(event.option.value.value, this.machineGrnFrom.get('grnType').value.value).subscribe(accPacInvoiceRes => {
      accPacInvoiceRes && this.patchValueToMachineGrnFrom(accPacInvoiceRes['result']);
    });
    this.autocompleteService.removeSelectedFromListError(this.machineGrnFrom.get('accPacInvoice'));
  }
  private patchValueToMachineGrnFrom(accPacInvoice: SearchAccPacInvoiceByInvoiceNumber) {
    let patchInvoiceObj = { ...accPacInvoice };
    delete patchInvoiceObj.invoiceNumber;
    console.log('patchInvoiceObj: ', patchInvoiceObj);
    this.machineGrnFrom.patchValue(patchInvoiceObj);
    this.accPacInvoicePartDetailChange.emit(accPacInvoice.accPacInvoicePartDetails)
    this.machineGrnFrom.get('grnBy').patchValue(this.loginUser.username);
    if(patchInvoiceObj['lrNo']==''){
      this.machineGrnFrom.get('lrNo').enable();
    }
  }
  validate(control: AbstractControl): ValidationErrors {
    return this.machineGrnFrom.valid ? null : { invalidForm: { valid: false, message: "basicInfoForm fields are invalid" } };
  }
  writeValue(val: any, options?: { emitEvent: true, onlySelf: false }): void {
    // throw new Error("Method not implemented.");
    if (val && val.resetForm) {
      this.machineGrnFrom.reset();
      return;
    }
    val && this.machineGrnFrom.patchValue(val, options);
    val && this.patchValueToGrnType(val.grnType, this.grnTypeList$.value);
    val && this.patchValueToTransporter(val.transporter, this.transporterNameList$.value);
  }
  registerOnChange(fn: any): void {
    // throw new Error("Method not implemented.");
    fn && this.machineGrnFrom.valueChanges.pipe(
      map((value) => this.machineGrnFrom.getRawValue())
    ).subscribe(fn);
  }
  registerOnTouched(fn: any): void {
    fn && (this.onTouched = fn);
    // throw new Error("Method not implemented.");
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.machineGrnFrom.disable() : this.machineGrnFrom.enable();
    // throw new Error("Method not implemented.");
  }

  ngOnDestroy() {
    
  }
}
