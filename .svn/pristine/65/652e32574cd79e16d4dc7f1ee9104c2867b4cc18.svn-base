import { Component, OnInit, ChangeDetectionStrategy, ViewChildren, ElementRef, ViewChild, QueryList, EventEmitter, Output, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatSelectChange, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { PartIssuePagePresenter } from '../part-issue-page/part-issue-page-presenter';
import { Observable, of } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { PartIssueApiService } from './part-issue-api.service';
import { DateService } from '../../../../../root-service/date.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { StorageLoginUser } from 'LoginDto';

@Component({
  selector: 'app-part-issue',
  templateUrl: './part-issue.component.html',
  styleUrls: ['./part-issue.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    PartIssueApiService
  ],
  // changeDetection: ChangeDetectionStrategy.OnPush
})
export class PartIssueComponent implements OnInit {
  @Input() isEdit: boolean;
  @Input() isView: boolean;
  @Input() viewData: any;
  partIssueForm: FormGroup;
  issueTo$: SelectList[];
  issueAgainstList: SelectList[];
  issueAgainstListTemp: SelectList[];
  issueTypeList$: Observable<SelectList[]>;
  serverDate$: Observable<string>;
  sparePartRequisitionList$: Observable<SelectList[]>;
  jobCardNoList$: Observable<SelectList[]>;
  advancedSparePartIssueNoList$: Observable<SelectList[]>;
  loginUser: StorageLoginUser;
  @ViewChildren('jobCardFieldRef') jobCardFieldRef: QueryList<ElementRef<HTMLElement>>;
  selectedIssueType: string;
  itemDetailList: object[];
  //@Output() partRequisitionItemChange = new EventEmitter<object[]>();
  //@Output() advancedSparePartIssueChange = new EventEmitter<SelectList>();
  constructor(
    httpClient: HttpClient,
    public dialog: MatDialog,
    private fb: FormBuilder,
    private partIssuePagePresenter: PartIssuePagePresenter,
    private partIssueApiService: PartIssueApiService,
    private dateService: DateService,
    private localStorage: LocalStorageService
  ) {
    this.dateService.getSystemGeneratedDate(httpClient).subscribe(dateRes => {
      this.serverDate$ = of(dateRes.result);
    });
  }
  ngAfterViewInit() {
    // 
  }

  ngOnInit() {
    this.loginUser = this.localStorage.getLoginUser();
    this.partIssueForm = this.partIssuePagePresenter.partIssueForm;
    console.log('this.partIssueForm: ', this.partIssueForm);
    // this.createpartIssueForm();
    this.getIssueType();
    this.getRequisitionAgainstIssue();
    this.getIssueTo();
    this.issueTypeValueChanges();
  }
  private getIssueType() {
    this.issueTypeList$ = this.partIssueApiService.getIssueType();
  }
  private getRequisitionAgainstIssue() {
     this.partIssueApiService.getRequisitionAgainstIssue().subscribe(
      res=>{
        this.issueAgainstList = res.result;
        this.issueAgainstListTemp = this.issueAgainstList;
        if (this.viewData) {
          this.partIssueForm.get('issueAgainst').patchValue(this.viewData.issueAgainst)
        }
      }
    );
  }
  private getIssueTo() {
     this.partIssueApiService.getIssueTo().subscribe(res=>{
         this.issueTo$= res.result;
         if (this.viewData) {
            this.partIssueForm.get('issueTo').patchValue({'value':this.viewData.issueTo,'id':this.viewData.issueToId})
         }
     })
  }
  displayFn(obj: SelectList): string | number | undefined {
    if (obj && typeof obj === 'string') {
      return obj;
    }
    return obj ? obj.value : undefined;
  }
  compareFn(c1: SelectList, c2: SelectList): boolean {

    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.value === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.value;
    }
    return c1 && c2 ? c1.value === c2.value : c1 === c2;
  }
  private issueTypeValueChanges() {
    if (this.partIssueForm) {
      this.partIssueForm.get('issueType').valueChanges.subscribe(aprSearchVal => {
        if (aprSearchVal) {
          this.partIssuePagePresenter.resetWhenIssueTypeChange();
          this.selectedIssueType = aprSearchVal.value || aprSearchVal;
          this.addOrRemoveControlAdvancedSparePartIssue(this.selectedIssueType);
          if (this.selectedIssueType === 'APR') {
            this.basicConfigForIssueTypeIsAPR();
          }
          if (this.selectedIssueType === 'Job Card') {
            this.basicConfigForIssueTypeIsJobCard();
          }
        }
      })
    }
  }
  private basicConfigForIssueTypeIsAPR() {
    this.partIssueForm.get('sparePartRequisition').setValidators(Validators.required);
    this.partIssueForm.get('jobCardNo').setValidators(null);
    this.partIssueForm.get('issueAgainst').disable();
    this.partIssueForm.get('sparePartRequisition').updateValueAndValidity();
    this.sparePartRequisitionValueChanges();
  }
  private basicConfigForIssueTypeIsJobCard() {
    this.partIssueForm.get('jobCardNo').setValidators(Validators.required);
    this.partIssueForm.get('sparePartRequisition').setValidators(null);
    this.partIssueForm.get('jobCardNo').updateValueAndValidity();
    this.jobCardNoValueChanges();
    if (this.isView) {
      this.partIssueForm.get('issueAgainst').disable();
      return;
    }
    this.partIssueForm.get('issueAgainst').enable();
  }
  private sparePartRequisitionValueChanges() {
    if (this.partIssueForm) {
      this.partIssueForm.get('sparePartRequisition').valueChanges.subscribe((aprSearchVal: string | SelectList) => {
        console.log('aprSearchVal: ', aprSearchVal);
        if (!aprSearchVal) {
          return;
        }
        if (this.selectedIssueType !== 'Job Card') {
          this.partIssuePagePresenter.resetWhenSparePartRequisitionChange();
        }
        if (typeof aprSearchVal === 'string') {
          this.sparePartRequisitionList$ = this.partIssueApiService.searchByDocumentForPartIssue(aprSearchVal, this.selectedIssueType);
        }
      })
    }
  }
  private getPartRequisitionDetailsById(aprSearchVal: string, issueAgainst: string, apiId?:number) {
    if(apiId==undefined)apiId=0;  
    this.partIssueApiService.getPartRequisitionDetailsById(aprSearchVal, issueAgainst, apiId).subscribe(partRequisitionDetailRes => {
      this.partIssueForm.patchValue(partRequisitionDetailRes['partRequisition']);
      if(partRequisitionDetailRes['partRequisitionItem']){
          partRequisitionDetailRes['partRequisitionItem'].forEach(itemData => {
              this.partIssuePagePresenter.addRow(itemData)
          })
      }
    }, err => {

    });
  }
  private jobCardNoValueChanges() {
    if (this.partIssueForm) {
      this.partIssueForm.get('jobCardNo').valueChanges.subscribe(aprSearchVal => {
        if (!aprSearchVal) {
          return;
        }
        this.partIssuePagePresenter.resetWhenJobCardNoChange();
        if (typeof aprSearchVal === 'string') {
          this.jobCardNoList$ = this.partIssueApiService.searchByDocumentForPartIssue(aprSearchVal, this.selectedIssueType);
        }
      })
    }
  }
  sparePartRequisitionOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.issueAgainstListTemp = this.issueAgainstList;
    this.partIssueForm.get('issueAgainst').patchValue('Stock');
    this.getPartRequisitionDetailsOnSelectOfSparePartRequisition(event.option.value, this.partIssueForm.get('issueAgainst').value);
  }
  jobCardNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.issueAgainstListTemp = this.issueAgainstList;
    if (event.option.value && event.option.value.requisitionNo && event.option.value.requisitionId) {
      this.partIssueForm.get('sparePartRequisition').patchValue({
        id: event.option.value.requisitionId,
        value: event.option.value.requisitionNo
      });
    }
    this.partIssueApiService.checkPastIssueAgainstJobcard(event.option.value.id).subscribe(response => {
      if(response['result']!=null){
        this.issueAgainstListTemp = this.issueAgainstListTemp.filter(o => o.value===response['result']);
      }
    })
  }
  advancedSparePartIssueOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.partIssuePagePresenter.resetWhenIssueAgainstChange();  
    if (event.option.value) {
      const { jobCardNo, issueAgainst } = this.partIssueForm.getRawValue();
      this.getPartRequisitionDetailsOnSelectOfSparePartRequisition(jobCardNo, issueAgainst, event.option.value.id);
      //this.advancedSparePartIssueChange.emit(event.option.value);
    }
  }
  issueAgainstSelectionChange(event: MatSelectChange) {
    this.partIssuePagePresenter.resetWhenIssueAgainstChange();
    const partIssueFormData = this.partIssueForm.getRawValue();
    if (event.value && partIssueFormData && partIssueFormData.issueType) {
      this.addOrRemoveControlAdvancedSparePartIssue(event.value);
      if (partIssueFormData.issueType === 'APR' || partIssueFormData.issueType.value === 'APR') {
        this.getPartRequisitionDetailsOnSelectOfSparePartRequisition(partIssueFormData.sparePartRequisition, event.value);
      }
      if (partIssueFormData.issueType === 'Job Card' || partIssueFormData.issueType.value === 'Job Card') {
        if(event.value == 'Stock'){  
            this.getPartRequisitionDetailsOnSelectOfJobCardNo(partIssueFormData.jobCardNo, event.value);
        }else{
            this.partIssuePagePresenter.resetWhenIssueAgainstChange()    
        }
      }
    }
  }
  private advancedSparePartIssueValueChange() {
    
    if (this.partIssueForm && this.partIssueForm.get('advancedSparePartIssue')) {
      this.partIssueForm.get('advancedSparePartIssue').valueChanges.subscribe(aprSearchVal => {
        this.partIssuePagePresenter.resetWhenIssueAgainstChange();    
        if (typeof aprSearchVal === 'string') {
          this.advancedSparePartIssueNoList$ = this.partIssueApiService.searchApiNo(aprSearchVal);
        }
      })
    }
  }
  private getPartRequisitionDetailsOnSelectOfJobCardNo(jobCardNo: SelectList, issueAgainst: string) {
    if (jobCardNo && typeof jobCardNo === 'object') {
      this.getPartRequisitionDetailsById(`${jobCardNo.requisitionId}`, issueAgainst);
      
    }
  }
  private getPartRequisitionDetailsOnSelectOfSparePartRequisition(sparePartRequisition: SelectList, issueAgainst: string, apiId?:number) {
    if (sparePartRequisition && typeof sparePartRequisition === 'object' && issueAgainst) {
      if (this.selectedIssueType === 'APR') {
          this.getPartRequisitionDetailsById(`${sparePartRequisition.id}`, issueAgainst, apiId);
      }else{
          this.getPartRequisitionDetailsById(`${sparePartRequisition.requisitionId}`, issueAgainst, apiId);
      }
    }
  }
  private addOrRemoveControlAdvancedSparePartIssue(issueAgainst: string) {
    if(!this.isView){
        if (issueAgainst === 'API') {
          this.partIssueForm.addControl('advancedSparePartIssue', new FormControl(null, Validators.required));
          this.advancedSparePartIssueValueChange();
          return true;
        }
        if (this.partIssueForm.get('advancedSparePartIssue')) {
          this.partIssueForm.removeControl('advancedSparePartIssue');
        }
    }
    return false;
  }
  validateForm() {
    this.openConfirmDialog();
  }

  submitData() {
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Parts Issue?';
    if (this.isEdit) {
      message = 'Do you want to update Parts Issue?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {

      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
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
