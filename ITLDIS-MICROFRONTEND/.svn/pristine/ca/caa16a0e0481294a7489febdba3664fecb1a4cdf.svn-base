import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DealerDetailsForNewUser } from 'src/app/master/dealer-masters/dealer-user/domain/create-dealer-user-domain';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { AssignOrgHierarchyToDealerPopUpComponent } from '../../assign-org-hierarchy-to-dealer-pop-up/assign-org-hierarchy-to-dealer-pop-up.component';
import { DealerDetails, DealerHoDepartment, DropDownDepartments, SubmitAssignOrgHierarchyToDealerDto } from '../../domain/create-assign-org-hierarchy-to-dealer-domain';
import { CreateNewAssignOrgHierarchyToDealerService } from './create-new-assign-org-hierarchy-to-dealer.service';

@Component({
  selector: 'app-create-new-assign-org-hierarchy-to-dealer',
  templateUrl: './create-new-assign-org-hierarchy-to-dealer.component.html',
  styleUrls: ['./create-new-assign-org-hierarchy-to-dealer.component.scss'],
  providers: [CreateNewAssignOrgHierarchyToDealerService]
})
export class CreateNewAssignOrgHierarchyToDealerComponent implements OnInit {

  isEdit: boolean = false;
  isView: boolean = false;
  data: Object;
  dealerId: any
  newDealerList: DealerDetails[] = []
  disable = true;
  functionVal: any
  isShowPcrButton: boolean
  createNewAssignOrgHierarchyToDealerForm: FormGroup;
  private _operation: string
  departmentsList: Array<DropDownDepartments> = [];
  departmentId: number;
  levelHierFlag: boolean = false;
  tableShowFlag: boolean = false;
  tableShowList: any[] = []
  tempTableShowList: any[] = []

  tableShowListAnother: any[] = []
  tableShowListAnotherAfterDelete: any[] = [];
  submitFinalList: any[] = []

  headers: any = {};
  index = -1;
  duplicateItem: boolean = false;
  editDealerSetValue: any;
  paramId: any;
  constructor(
    private createNewAssignOrgHierarchyToDealerService: CreateNewAssignOrgHierarchyToDealerService,
    private fb: FormBuilder,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
  ) {
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        this.paramId = param.get('id');
      }
    });
  }

  ngOnInit() {
    this.operation = OperationsUtil.operation(this.activityRoute)
    this.createAssignOrgHierarchyToDealerForm();
    this.viewOrEditOrCreate()
    this.checkRouterParamsForDealer()
    this.createNewAssignOrgHierarchyToDealerForm.get('dealer').valueChanges.subscribe(dealerCode => {
      
      if(dealerCode){
        let value = dealerCode;
        this.createNewAssignOrgHierarchyToDealerForm.get('dealer').setErrors({'selectFromList':'Select From List'})
        if(typeof dealerCode == 'object'){
          value = dealerCode.code;
          this.createNewAssignOrgHierarchyToDealerForm.get('dealer').setErrors(null);
        }
        this.createNewAssignOrgHierarchyToDealerService.dealerAuto(value).subscribe(res => {
          this.newDealerList = res
        })
      }
    });
  }

  set operation(type: string) {
    this._operation = type
  }
  get operation() {
    return this._operation
  }

  createAssignOrgHierarchyToDealerForm() {
    this.createNewAssignOrgHierarchyToDealerForm = this.fb.group({
      dealer: [''],
      hoDepartment: [''],
    })
    this.dropDownDepartment();

  }
  private viewOrEditOrCreate() {
    if (this.operation === Operation.VIEW) {
      console.log("view");
      this.isView = true
      // this.addRow()
      this.createNewAssignOrgHierarchyToDealerForm.disable()
      console.log(" this.assignOrgHierarchyToDealer ", this.createNewAssignOrgHierarchyToDealerForm);

    } else if (this.operation === Operation.EDIT) {
      this.isEdit = true
      console.log("edit");
    }
    else if (this.operation === Operation.CREATE) {
      console.log("create");
    }
  }

  checkRouterParamsForDealer() {
    this.activityRoute.queryParamMap.subscribe(param => {
      console.log('param--', param);
      console.log('isEdit--', this.isEdit);
      if (param.has('id')) {
        if (this.isEdit == true) {
          this.viewdealerDetails(param.get('id'))
          this.isShowPcrButton = true
        }
        if (this.isView) {
          this.viewdealerDetails(param.get('id'))
          if (param.get('hasButton') == 'false') {
            this.isShowPcrButton = false
          }
          else if (param.get('hasButton') == 'true') {
            this.isShowPcrButton = true
          }
          //	this.employeeMasterCreatePagePresenter.disableForView()
        }
      }

    })
  }


  viewdealerDetails(id: any) {
    let viewDetails = {} as any
    viewDetails.dealerId = id;
    viewDetails.departmentId = null;
    viewDetails.page = 0;
    viewDetails.size = 100;


    this.createNewAssignOrgHierarchyToDealerService.searchByDealerIdAssignOrgHierarchyToDealer(viewDetails).
      subscribe((res: any) => {
        console.log(res.result)
        console.log("res.result")
        if (this.isView) {
          this.tableShowListAnother = res.result;
          if (this.tableShowListAnother != null && this.tableShowListAnother != undefined) {
            this.isView = true;
          }
        }

        else if (this.isEdit) {
          this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.disable();
          this.tableShowFlag = true;
          let length = 0;
          this.tableShowList = res.result;

          for (let i = 0; i < this.tableShowList.length; i++) {
            console.log(this.tableShowList[i])
            this.editDealerSetValue = this.tableShowList[i].dealerName;
            this.tempTableShowList = [];
            this.tempTableShowList.push({
              "key": this.tableShowList[i].hierarchyCode, "value": this.tableShowList[i].hierarchyDesc, "department": this.tableShowList[i].hoDepartmentId, "dealer": this.tableShowList[i].id,
              "orgHierarchyId": this.tableShowList[i].orgHierarchyId, "lastOrgHierarchyId": this.tableShowList[i].orgHierarchyId,
              "departmentName": this.tableShowList[i].departmentName, "dealerName": this.tableShowList[i].dealerName,
            })
            this.tableShowListAnother[length] = this.tempTableShowList
            length++;
          }
          this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.patchValue(this.editDealerSetValue);
        }
      })
  }

  autoCreateDealer(event: KeyboardEvent) {
    let value = event.key
    if (value != null || value != undefined && typeof value !== 'object') {
     
    }
  }

  displayFnDealer(dealer: DealerDetailsForNewUser) {
    return dealer ? dealer.displayString : undefined;
  }

  newDealerSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.createNewAssignOrgHierarchyToDealerForm.get('dealer').setErrors(null);
    }
    this.dealerId = this.createNewAssignOrgHierarchyToDealerForm.get('dealer').value.id;
    this.selectDepartment("ByDealer");
  }
  dropDownDepartment() {
    this.createNewAssignOrgHierarchyToDealerService.getDeprtmentDrop("Dealer").subscribe(res => {
      console.log(res)
      this.departmentsList = res.result
    })
  }

  validateForm() {
    if (this.tableShowListAnother != null && this.tableShowListAnother != undefined &&
      this.tableShowListAnother.length > 0) {
      this.openConfirmDialog();
    } else {
      this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].setValidators([Validators.required])
      this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].updateValueAndValidity()
      this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].setValidators([Validators.required])
      this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].updateValueAndValidity()
      this.createNewAssignOrgHierarchyToDealerForm.markAllAsTouched();
      if (!this.createNewAssignOrgHierarchyToDealerForm.valid) {
        this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
      }
      else {
        this.toastr.error(`Please fill hierarchy mandatory fields`, 'Report mandatory table hierarchy fields')
      }
    }
  }
  resetForm() {
    this.createNewAssignOrgHierarchyToDealerForm.reset();
    this.tableShowListAnother = [];
    this.ngOnInit();
  }

  private buildJsonForSaveKubotaDealerFeildHier() {
    const saveKubotaDealerFeildHier = {
      ...this.buildJsonForCreateKubotaDealerFeildHier()
    }
    return saveKubotaDealerFeildHier;
  }

  buildJsonForCreateKubotaDealerFeildHier() {
    this.submitFinalList = [];
    for (let i = 0; i < this.tableShowListAnother.length; i++) {
      let tableDataSubmit = this.tableShowListAnother[i];
      for (let j = 0; j < tableDataSubmit.length; j++) {
        if (tableDataSubmit[j].orgHierarchyId == tableDataSubmit[j].lastOrgHierarchyId) {
          let SubmitKubotaDealerFeildHier = {} as SubmitAssignOrgHierarchyToDealerDto
          SubmitKubotaDealerFeildHier.orgHierarchyId = tableDataSubmit[j].orgHierarchyId ? tableDataSubmit[j].orgHierarchyId : null
          let DealerHoDepartment = {} as DealerHoDepartment
          if (!this.isEdit) {
            DealerHoDepartment.dealerId = tableDataSubmit[j].dealer ? tableDataSubmit[j].dealer : null
            DealerHoDepartment.hoDepartmentId = tableDataSubmit[j].department ? tableDataSubmit[j].department : null

          }
          else {
            DealerHoDepartment.dealerId = this.paramId ? this.paramId : null
            DealerHoDepartment.hoDepartmentId = tableDataSubmit[j].department ? tableDataSubmit[j].department : null

          }
          SubmitKubotaDealerFeildHier.dealerHoDepartmentId = DealerHoDepartment;
          this.submitFinalList.push(SubmitKubotaDealerFeildHier)
          break;
        }
      }
    }
    console.log(this.submitFinalList)
    console.log("...SubmitKubotaDealerFeildHier..")
    return this.submitFinalList;
  }
  submitData() {
    this.createNewAssignOrgHierarchyToDealerService.submitAssignOrgHierarchyToDealer(this.buildJsonForCreateKubotaDealerFeildHier()).subscribe(res => {
      if (res) {
        this.toastr.success(res['message'], 'Success');
        if (res['message'] == 'Assign Org Hierarchy To Dealer Added successfully') {
          if (this.isEdit) {
            this.router.navigate(['../../'], { relativeTo: this.activityRoute })
          }
          else {
            this.router.navigate(['../'], { relativeTo: this.activityRoute })
          }
        }
      }
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Kubota Dealer Fileld Hierarchy?';
    if (this.isEdit) {
      message = 'Do you want to update Kubota Dealer Fileld Hierarchy?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    console.log('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        if (this.tableShowListAnother != null && this.tableShowListAnother != undefined
          && this.tableShowListAnother.length > 0) {
          this.submitData();
        }
      }
      if (result === 'Confirm' && this.isEdit) {
        if (this.tableShowListAnother != null && this.tableShowListAnother != undefined
          && this.tableShowListAnother.length > 0) {
          this.submitData();
        }
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
  exitForm() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activityRoute });
    } else {
      this.router.navigate(['../'], { relativeTo: this.activityRoute });
    }
  }

  private openConfirmDialogForLevelHier(): void | any {
    const departmentId = this.departmentId;
    const dialogRef = this.dialog.open(AssignOrgHierarchyToDealerPopUpComponent, {
      width: '1000px',
      panelClass: 'confirmation_modal',
      data: { "departmentId": departmentId, "tableList": this.tableShowListAnother }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != null && result != undefined) {
        let popUpForm = result.popUpForm as FormGroup

        if (popUpForm != null && popUpForm != undefined) {
          let lastOrgHierarchyId = result.orgHierarchyId;
          
          console.log(this.tableShowListAnother);

          let guidelines = result.guidelines;
          console.log('The dialog was closed', popUpForm.getRawValue());
          let value = popUpForm.getRawValue()
          this.tableShowList = [];
          var dealerNameValue;
          if (this.isEdit) {
            dealerNameValue = this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value
          }
          else {
            dealerNameValue = this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value.displayString
          }
          Object.keys(value).forEach(element => {
            Object.values(value).forEach((element1: any) => {
              for (let i = 0; i < guidelines.length; i++) {
                if (element == guidelines[i].LEVEL_CODE) {
                  if (element1.level_id == guidelines[i].LEVEL_ID) {
                    this.tableShowList.push({
                      "key": element, "value": element1.hierarchy_desc, "department": this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value.id, "dealer": this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value.id,
                      "orgHierarchyId": element1.org_hierarchy_id, "lastOrgHierarchyId": lastOrgHierarchyId,
                      "departmentName": this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value.departmentName, "dealerName": dealerNameValue,
                    })
                  }
                }
              }
            })
          })
          let length = this.tableShowListAnother.length;
          // if (this.tableShowList != null && this.tableShowList != undefined) {
          //   length++;
          // }
          this.tableShowListAnother[length] = this.tableShowList
          this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].clearValidators()
          this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].clearValidators()
          if (!this.isEdit) {
            this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.
              patchValue("");
          }
          this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.
            patchValue("");
          this.newDealerList = [];
        }
        if (popUpForm != undefined && popUpForm != null) {
          this.tableShowFlag = true;
        }
      }
    });
  }
  compareFnDepartmentName(c1: DropDownDepartments, c2: DropDownDepartments): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.departmentName === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.departmentName;
    }
  }

  selectDepartment(event) {
    this.duplicateItem = false;
    console.log("selectDepartment---->", event)
    if ((event == 'ByDealer')) {
      if (this.departmentId != undefined && this.departmentId != null) {
        this.levelHierFlag = true;
        if (this.levelHierFlag) {
          for (let i = 0; i < this.tableShowListAnother.length; i++) {
            let tableList = this.tableShowListAnother[i];
            let currentDealer = this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value.id;
            let currentHoDepartment = this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value.id;
            if (tableList[0].dealer == currentDealer) {
              if (currentHoDepartment != undefined && currentHoDepartment != null) {
                if (tableList[0].department == currentHoDepartment) {
                  this.duplicateItem = true;
                  this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].setValidators([Validators.required])
                  this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].updateValueAndValidity()
                  this.toastr.error(`Please fill other department mandatory`, 'Report duplicate department')
                  break;
                }
              }
            }
          }
          if (!this.duplicateItem) {
            if (this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value != null &&
              this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value != '' &&
              this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value != undefined) {
              this.openConfirmDialogForLevelHier();
            }
            else {
              this.createNewAssignOrgHierarchyToDealerForm.markAllAsTouched();
              this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].setValidators([Validators.required])
              this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].updateValueAndValidity()
              this.toastr.error(`Please fill  department mandatory`, 'Report mandatory field')
            }
          }
          else {
            return;
          }
        }
      }
    }
    else {
      this.departmentId = event.value.id;
      this.levelHierFlag = true;
      if (this.levelHierFlag) {
        if (this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value != null &&
          this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value != '' &&
          this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value != undefined) {
          this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].clearValidators()
          for (let i = 0; i < this.tableShowListAnother.length; i++) {
            let tableList = this.tableShowListAnother[i];
            let currentDealer = this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value.id;
            let currentHoDepartment = this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value.id;
            let currentHoDepartmentName = this.createNewAssignOrgHierarchyToDealerForm.controls.hoDepartment.value.departmentName;
            let currentDealerName = this.createNewAssignOrgHierarchyToDealerForm.controls.dealer.value;
            if (tableList[0].department == currentHoDepartment) {
              if (currentDealer != undefined && currentDealer != null) {
                if (tableList[0].dealer == currentDealer) {
                  this.duplicateItem = true;
                  this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].setValidators([Validators.required])
                  this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].updateValueAndValidity()
                  this.toastr.error(`Please fill other dealer mandatory`, 'Report dealer department')
                  break;
                }
              }
            }
            else if (tableList[0].departmentName == currentHoDepartmentName) {
              this.duplicateItem = true;
              this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].setValidators([Validators.required])
              this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].updateValueAndValidity()
              this.toastr.error(`Please fill other department mandatory Or delete department from table `, 'Report duplicate department')
              break;
            }
          }
          if (!this.duplicateItem) {
            this.openConfirmDialogForLevelHier();
          }
          else {
            return;
          }
        }
        else {
          this.createNewAssignOrgHierarchyToDealerForm.markAllAsTouched();
          this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].setValidators([Validators.required])
          this.createNewAssignOrgHierarchyToDealerForm.controls['dealer'].updateValueAndValidity()
          this.toastr.error(`Please fill dealer mandatory fields`, 'Report mandatory field')
          return;
        }
      }
    }
  }
  private openConfirmDialogForDeleteRow(indexNumber: any): void | any {
    let message = 'Do you want to delete Kubota Dealer Fileld Hierarchy?';
    const confirmationData = this.setConfirmationModalData(message);
    console.log('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.deleteRow(indexNumber);
      }
      if (result === 'Confirm' && this.isEdit) {
        this.deleteRow(indexNumber);
      }
    });
  }
  deleteRowFromTable(indexNum: any) {
    this.openConfirmDialogForDeleteRow(indexNum);
  }
  deleteRow(indexValue: any) {
    // const index: number = this.tableShowListAnother.indexOf(indexValue);
    this.tableShowListAnother.splice(indexValue, 1);
    this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].clearValidators()
    this.createNewAssignOrgHierarchyToDealerForm.controls['hoDepartment'].patchValue("");


  }
}
