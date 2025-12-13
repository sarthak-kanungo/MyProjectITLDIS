import { Component, OnInit, Input } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PartyMasterPagePresenter } from '../party-master-page/partyMasterPage.presenter';
import { BranchCodeDetails, CategoryList, PartyCode, PartyName, Title } from '../../domain/party-master-domain';
import { PartyDetailsService } from './party-details.service';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-party-details',
  templateUrl: './party-details.component.html',
  styleUrls: ['./party-details.component.scss'],
  providers: [PartyDetailsService]
})
export class PartyDetailsComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  isCreate:boolean;
  data: Object;
  partyCode$: BaseDto<Array<PartyCode>>;
  partyName$: BaseDto<Array<PartyName>>;
  categories: CategoryList[];
  titles: Array<Title> = [
    'Mr.', 'Miss', 'Mrs.'
  ];
  userType:string;
  partyMasterDetailsForm: FormGroup;
  partyCategoryId: any;
  categoryValue: any;
  branchId: number;
  branchCodeList$: BaseDto<Array<BranchCodeDetails>>
  displayString: any;
  constructor(
    private fb: FormBuilder, public dialog: MatDialog,
    private partyMasterPagePresenter: PartyMasterPagePresenter,
    private partyDetailsService: PartyDetailsService,
    private activityRoute: ActivatedRoute,
    private loginService: LoginFormService,
    private toastr : ToastrService
  ) { }

  ngOnInit() {

    this.userType = this.loginService.getLoginUserType();

    this.partyMasterDetailsForm = this.partyMasterPagePresenter.partyCreateDetailsForm
    this.partyMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    
    this.isView = this.partyMasterPagePresenter.operation === Operation.VIEW;
    this.isEdit = this.partyMasterPagePresenter.operation === Operation.EDIT;
    this.isCreate = this.partyMasterPagePresenter.operation === Operation.CREATE;

    this.partyDetailsService.getCategory().subscribe((response: any) => {
      this.categories = response.result
      if(!this.isCreate && this.partyMasterDetailsForm.get('category').value){
          this.partyMasterDetailsForm.get('category').patchValue(this.categories.filter(category => category.id === this.partyMasterDetailsForm.get('category').value.id)[0]);
      }
    })

    if(this.isCreate){
      this.valueChanges();
      if(this.userType.toLocaleLowerCase() === 'dealer'){
        this.autoBranchCode('');
      }
    }else if(this.isEdit){
      this.partyMasterDetailsForm.get('branchCode').disable();
      this.autoBranchCode('');
    }else{
      this.partyMasterDetailsForm.disable();
      this.autoBranchCode('');
    }
  }
  validatePartyName(){
    const value = this.partyMasterDetailsForm.get('partyName').value;
    if (this.partyMasterDetailsForm.get('category').value && value) {
      this.partyDetailsService.getPartyName(value, this.partyMasterDetailsForm.get('category').value.id).subscribe(response => {
          this.partyName$ = response as BaseDto<Array<PartyName>>
          if(this.partyName$.result.length>0){
            this.partyMasterDetailsForm.get('partyName').setErrors({'alreadyExist':'Party Name already exist'});
          }else{
            this.partyMasterDetailsForm.get('partyName').setErrors(null);
          }
       })
    }else{
      if(value && !this.partyMasterDetailsForm.get('category').value){
        this.partyMasterDetailsForm.get('partyName').reset();
        this.toastr.error('First Select Category','Required Fields');
      }
      this.partyMasterDetailsForm.get('partyName').setErrors(null);
    }
  }
  valueChanges() {
    this.partyMasterDetailsForm.get('branchCode').valueChanges.subscribe(value => {
      this.partyMasterDetailsForm.get('branchCode').setErrors({ selectFromList: 'Please select from list'});
      if (value) {
        if(typeof value == 'object'){
          this.partyMasterDetailsForm.get('branchCode').setErrors(null);
        }
        const code = typeof value == 'object'? value.code:value;
        this.autoBranchCode(code);
      }else{
        this.branchCodeList$ = null;
      }
    });
    
    // this.partyMasterDetailsForm.get('partyName').valueChanges.subscribe(value => {
    //   if (this.partyMasterDetailsForm.get('category').value && value) {
    //     this.partyDetailsService.getPartyName(value, this.partyMasterDetailsForm.get('category').value.id).subscribe(response => {
    //         this.partyName$ = response as BaseDto<Array<PartyName>>
    //         if(this.partyName$.result.length>0){
    //           this.partyMasterDetailsForm.get('partyName').setErrors({'alreadyExist':'Party Name already exist'});
    //         }else{
    //           this.partyMasterDetailsForm.get('partyName').setErrors(null);
    //         }
    //      })
    //   }else{
    //     if(value && !this.partyMasterDetailsForm.get('category').value){
    //       this.partyMasterDetailsForm.get('partyName').reset();
    //       this.toastr.error('First Select Category','Required Fields');
    //     }
    //     this.partyMasterDetailsForm.get('partyName').setErrors(null);
    //   }
    // });
  }
 

  autoBranchCode(branchCode: string) {
    this.partyDetailsService.getBranchCode(branchCode).subscribe(response => {
      this.branchCodeList$ = response as BaseDto<Array<BranchCodeDetails>>;
      if(branchCode===''){
        if(this.isCreate){
          this.partyMasterDetailsForm.get('branchCode').patchValue(this.branchCodeList$.result[0]);
        }else{
          this.partyMasterDetailsForm.get('branchCode').patchValue(this.branchCodeList$.result.filter(branch => branch.id === this.partyMasterDetailsForm.get('branchCode').value.id)[0])
        }
        this.partyMasterDetailsForm.get('branchCode').disable();
      }
    })
  }

  selectedBranchCode(event: MatAutocompleteSelectedEvent) {
    this.branchId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterDetailsForm.get('branchCode').setErrors(null);
    }
  }
  displayFnBranchCode(selectedOption: BranchCodeDetails) {

    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['displayString'] : undefined;
  }
  displayFnBranchCodeView(selectedOption: BranchCodeDetails, branches: any) {
    if (branches != null && branches != undefined) {
      if (!!selectedOption && typeof selectedOption === 'number') {
        return selectedOption;
      }
      for (let i = 0; i < branches.length; i++) {
        if (selectedOption.id === branches[i].id) {
          this.displayString = branches[i].displayString;
        }
      }
      this.partyMasterDetailsForm.get("branchCode").patchValue(this.displayString)
    }
  }
  validateForm() {

    this.openConfirmDialog();
  }

  submitData() {
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Party Master?';
    if (this.isEdit) {
      message = 'Do you want to update Party Master?';
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
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  displayFnForPartyCode(selectedOption: PartyCode) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['partyCode'] : undefined;
  }
  displayFnForPartyName(selectedOption: PartyName) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['partyName'] : undefined;
  }
  selectionChangedCateogory(event) {
    console.log("event ", event);
    this.partyCategoryId = event.value.id
    this.categoryValue = event.value.CategoryCode;
    this.partyMasterDetailsForm.get('partyCategoryId').patchValue(this.partyCategoryId)
    // this.partyMasterDetailsForm.get('category').patchValue(this.categoryValue)
  }
  compareFnCategory(c1: any, c2: any): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.CategoryCode === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.CategoryCode;
    }
    return c1 && c2 ? c1.CategoryCode === c2.CategoryCode : c1 === c2;
  }
}
