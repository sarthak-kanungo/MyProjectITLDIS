import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PartyMasterPagePresenter } from './partyMasterPage.presenter';
import { PartyMasterPageStore } from './partyMasterPage.store';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { CategoryList, SubmitDto } from '../../domain/party-master-domain';
import { PartyMasterPageService } from './party-master-page.service';
import { BaseDto } from 'BaseDto';
import { LoginFormService } from 'src/app/root-service/login-form.service';


@Component({
  selector: 'app-party-master-page',
  templateUrl: './party-master-page.component.html',
  styleUrls: ['./party-master-page.component.scss'],
  providers: [PartyMasterPagePresenter, PartyMasterPageStore, PartyMasterPageService]
})
export class PartyMasterPageComponent implements OnInit {

  searchForm: FormGroup;
  partyMasterDetailsForm: FormGroup
  partyMasterAddressForm: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;
  id: any
  userType;
  constructor(
    private router: Router,
    private partyMasterPagePresenter: PartyMasterPagePresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private partyMasterPageService: PartyMasterPageService,
    private loginService: LoginFormService
  ) {
    this.activityRoute.params.subscribe(prms => {
      this.id = prms['id'];
    }
    )
  }

  ngOnInit() {
    this.searchForm = this.partyMasterPagePresenter.createPartyMasterForm
    this.partyMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
    this.userType = this.loginService.getLoginUserType();
  }
  viewEmployeeMaster(id: any) {
    this.partyMasterPageService.getByIdPartyMaster(id).subscribe(res => {
      this.partyMasterPagePresenter.patchDataFromView(res)
    })
  }
  private viewOrEditOrCreate() {
    if (this.partyMasterPagePresenter.operation === Operation.VIEW) {

      this.partyMasterPagePresenter.partyCreateAddressDetailsForm.disable()
      this.partyMasterPagePresenter.partyCreateDetailsForm.disable()
      this.isView = true
      this.isEdit = false
      this.viewEmployeeMaster(this.id)
    } else if (this.partyMasterPagePresenter.operation === Operation.EDIT) {

      this.isView = false;
      this.isEdit = true;
      this.viewEmployeeMaster(this.id)
    } else if (this.partyMasterPagePresenter.operation === Operation.CREATE) {
      this.isView = false;
      this.isEdit = false;
      this.searchForm = this.partyMasterPagePresenter.createPartyMasterForm
      this.partyMasterDetailsForm = this.partyMasterPagePresenter.partyCreateDetailsForm
      this.partyMasterAddressForm = this.partyMasterPagePresenter.partyCreateAddressDetailsForm
    }

  }

  clearPartyMasterForm() {
    const branchcode = this.partyMasterDetailsForm.get('branchCode').value;
    const country = this.partyMasterAddressForm.get('country').value;
    this.searchForm.reset();
    if(this.userType.toLocaleLowerCase() === 'dealer'){
      this.partyMasterDetailsForm.get('branchCode').patchValue(branchcode);
    }
    this.partyMasterAddressForm.get('country').patchValue(country);
  }

  submitPartyMasterForm() {
    if (this.searchForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.partyMasterPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitData() {
    let submitData = {} as SubmitDto
    submitData = this.searchForm.getRawValue() as SubmitDto;
    console.log("PartyMasterFinal ", submitData);
    var submitDataFinal = {
      ...this.partyMasterAddressForm.getRawValue(),
      ...this.partyMasterDetailsForm.getRawValue()
    }
    submitDataFinal = this.partyMasterPagePresenter.submitData(submitDataFinal)
    if(submitDataFinal.category=='Co-dealer')
    {
      this.toastr.error(`Party Master Can't create with Category " Co-dealer "`)
      return;
    }
    this.partyMasterPageService.submitPartyMaster(submitDataFinal).subscribe(res => {
      if (res) {
        this.searchForm.reset();
        this.toastr.success(`Party Master Created Successfully`, 'Success')
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      }
    }, (error) => {
      this.toastr.error(`Party Master Created Failed`, error)
    })
  }
  updateData() {
    let submitData = {} as SubmitDto
    submitData = this.searchForm.getRawValue() as SubmitDto;
    let partyMasterDetailsForm = this.searchForm.get('partyCreateDetailsForm') as FormGroup
    let partyMasterAddressForm = this.searchForm.get('partyCreateAddressDetailsForm') as FormGroup

    console.log("PartMasterFinal ", submitData);
    var submitDataFinal = {
      ...partyMasterDetailsForm.getRawValue(),
      ...partyMasterAddressForm.getRawValue()
    }
    submitDataFinal = this.partyMasterPagePresenter.updateData(submitDataFinal, this.id)
    if(submitDataFinal.category=='Co-dealer')
    {
      this.toastr.error(`Party Master Can't update with Category " Co-dealer "`)
      return;
    }


    this.partyMasterPageService.updatePartyMaster(submitDataFinal).subscribe(res => {
      if (res) {
        this.searchForm.reset();
        this.router.navigate(['../../'], { relativeTo: this.activityRoute })
        this.toastr.success(`Party Master Updated Successfully`, 'Success')
      }

    }
      , (error) => {
        this.toastr.error(`Party Master Updated Failed`, error)
      })
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
        this.updateData();

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
   ExitPartyMasterForm() {
    this.router.navigate(['../../'], { relativeTo: this.activityRoute })
  }
}