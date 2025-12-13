import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeAddressService } from 'src/app/master/dealer-masters/dealer-employee-master/component/employee-address/employee-address.service';
import { submitForm} from './dealer-territory-model';
import { MatDialog, MatOption } from '@angular/material';
import { DealerTerritoryMappingService } from '../../service/dealer-territory-mapping.service';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
@Component({
  selector: 'app-create-dealer-territory-mapping',
  templateUrl: './create-dealer-territory-mapping.component.html',
  styleUrls: ['./create-dealer-territory-mapping.component.css'],
  providers: [EmployeeAddressService],
  encapsulation: ViewEncapsulation.None
})
export class CreateDealerTerritoryMappingComponent implements OnInit {
  @ViewChild('allSelected', { static: false })
  private allSelected: MatOption
  addDealerTerritoryForm: FormGroup
  countryId: any;
  stateList$: any;
  stateId: any;
  districtList$: any;
  cityId: any;
  cityList$: any;
  tehsilId: any;
  tehsilList$: any;
  districtId: any;
  dealerList: Object;
  branchList: any;
  dealerId: void;
  country: any;
  stateList: any;
  disricList: any;
  isEdit: boolean;
  isCreate: Boolean;
  stateIdss: any;
  districtList: any;
  tehsilList: any;
  distId: any;
  
  sendStateId: number;
  _operation: string;
  isView: boolean;
  getStateIdForVieworEdit: any;
  viewEditDistricList: any;
  dealerIdEditOrView: any;
  branchListForEditOrView: any;
  brachMaster: any;
  stateIdForVieworEdit: number;
  districListforEditOrView: any;
  disctricListForEditOrView: any;
  tehsilListViewOrEdit: any;
  districIdforEdit: number;
  tehsilListForEdit: any;
  branchEditId: number;
  disctricEditPayload: any;
  headerId: any;
  tehId: any[];
  selectedCheckboxes: any[] = [];
  selectedCheckBox: any
  dropdownSettings: { idField: string; textField: string,itemsShowLimit:number, allowSearchFilter: boolean; enableCheckAll: boolean; closeDropDownOnSelection: boolean; };
  createDropDownSettings: { idField: string; textField: string,itemsShowLimit:number, allowSearchFilter: boolean; enableCheckAll: boolean; closeDropDownOnSelection: boolean; };
  

  map : any = [];
  disctricListForEditOrViews: any;
  territoryNo: string;
  constructor(
    private fb: FormBuilder,
    private service: DealerTerritoryMappingService,
    private toaster: ToastrService,
    private dialog: MatDialog,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  set operation(type: string) {
    this._operation = type
  }
  get operation() {
    return this._operation
  }
  ngOnInit() {
    this.addDealerTerritoryForm = this.fb.group({
      dealerMaster: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      branchMaster: [{ value: null, disabled: false }, Validators.required],
      country: [{ value: null, disabled: true }],
      state: [{ value: null, disabled: true }],
      district: [{ value: null, disabled: false }, Validators.required],
      tehsil: [{ value: null, disabled: false }],
      countryId: [{ value: null, disabled: true }],
      stateId: [{ value: null, disabled: true }],
      id: [{ value: null, disabled: true }],
      IdForEdit: [{ value: null, disabled: true }],
      territoryNo:[{value:null,disabled:true}]
    })


    // if (this.isCreate) {
    this.getFormValueChanges()
    this.getCountry()
    // } 
    this.checkFormOperation()
    this.dropdownSettings = {
      idField: 'id',
      textField: 'tehsil',
      allowSearchFilter: true,
      enableCheckAll: true,
      itemsShowLimit: 3,
      closeDropDownOnSelection: false
    }
    this.createDropDownSettings={
      idField: 'id',
      textField: 'tehsil',
      itemsShowLimit: 3,
      allowSearchFilter: true,
      enableCheckAll: true,
      closeDropDownOnSelection: false
    }
    
  }





  checkFormOperation() {
    
    this.operation = OperationsUtil.operation(this.activatedRoute);
 
    if (this.operation == Operation.VIEW) {
      this.addDealerTerritoryForm.disable()
      this.isView = true;
      this.activeRoute();
    }
    else if (this.operation == Operation.EDIT) {
      this.isEdit = true;
      this.addDealerTerritoryForm.disable()
      this.addDealerTerritoryForm.get('district').disable();
      this.addDealerTerritoryForm.get('tehsil').enable();
      this.activeRoute();
    }
    else if (this.operation == Operation.CREATE) {
      
      this.isCreate = true
    }
    

  }

  private activeRoute() {
    this.activatedRoute.params.subscribe((param) => {
       this.territoryNo=atob(param['territoryNo']);
       this.viewOrEditData(this.territoryNo);
     
    });
    
  }
  
  onKeyEmployeeeCode(event: KeyboardEvent) {
    this.onFocusGetEmployeeCodeList(event);
  }


 onFocusGetEmployeeCodeList(value){
    if (value == null || value == undefined)
        value = '';
  
    if(typeof value !== 'object'){
    }
    else{
      this.dealerList = null;
    }
  }
  private viewOrEditData(terriority) {
    this.service.viewData(terriority).subscribe(res => {
      console.log('res',res)
      this.headerId = res.id ? res.id : null
      this.dealerIdEditOrView = Number(res.dealerMaster.id?res.dealerMaster.id:null)
      this.branchEditId = Number(res.branchMaster.id?res.branchMaster.id:null);
      this.stateIdForVieworEdit = Number(res.state.id?res.state.id:null);
      this.districIdforEdit = Number(res.district.id?res.district.id:null)
      this.getBranchForEditOrView(this.dealerIdEditOrView);

      this.getDistricEditOrView(this.stateIdForVieworEdit);
      this.getTehsilEdit(this.districIdforEdit);
      this.getStateIdForVieworEdit = parseInt(res.state.id)
      if (this.isView) {
        this.addDealerTerritoryForm.get('district').patchValue(this.disctricListForEditOrView)
        this.tehsilListViewOrEdit = res.dealerTerritoryMapDtls
        let ids = [];
        this.tehsilListViewOrEdit.forEach(ele => {
          ids.push(ele.tehsil.tehsil);
        });
        let csv = ids.join(",");
        this.addDealerTerritoryForm.get('tehsil').patchValue(csv)
      }
      if (this.isEdit) {
        this.addDealerTerritoryForm.get('district').patchValue(this.disctricListForEditOrViews)
        this.tehsilListViewOrEdit = res.dealerTerritoryMapDtls

        this.tehId = [];
        this.tehsilListViewOrEdit.forEach(ele => {
          this.tehId.push(ele.id);
         
        });
        
        this.addDealerTerritoryForm.get('IdForEdit').patchValue(this.tehId)
        this.addDealerTerritoryForm.get('territoryNo').patchValue(res.territoryNo)
      }

      if (res) {
        this.brachMaster = res;
        this.disctricListForEditOrViews = res.district.district;
        // console.log(this.disctricListForEditOrViews,'disctricListForEditOrView')
        this.addDealerTerritoryForm.patchValue({
          dealerMaster: res.dealerMaster.dealerName +  res.dealerMaster.dealerCode? res.dealerMaster.dealerName +'  '+ '|' +'  '+res.dealerMaster.dealerCode : null,
          branchMaster: res.branchMaster.branchName ? res.branchMaster.branchName : null,
          state: res.state.state ? res.state.state : null,
          district: res.district.district ? res.district.district : null,
          territoryNo:res.territoryNo?res.territoryNo:null
        })
        this.addDealerTerritoryForm.get('district').setValue(this.disctricListForEditOrViews)

      }

    })


  }

  getBranchForEditOrView(val: any) {
    this.service.getBranchList(val).subscribe(branch => {
      this.branchListForEditOrView = branch
      const selectedValue = this.branchListForEditOrView[0];
      this.addDealerTerritoryForm.get('branchMaster').setValue(selectedValue.branchName)
    })

  }
  getDistricEditOrView(val) {
    this.service.getDistrict(val).subscribe(st => {
      this.districListforEditOrView = st;


    })
  }
  getTehsilEdit(val) {
    this.service.getTehsil(val).subscribe(st => {
      this.tehsilListForEdit = st;
      var newtehsil = [];
      this.tehsilListForEdit.forEach(emp1 => {
        const foundEmp = this.tehsilListViewOrEdit.find(emp2 => emp2.tehsil.id === emp1.id);

        if (foundEmp) {
          newtehsil.push({
            ...emp1,
            ...foundEmp.tehsil.tehsil
          });
        }
      });
      this.selectedCheckBox = newtehsil;
    })
  }



  getFormValueChanges() {
    this.addDealerTerritoryForm.get('dealerMaster').valueChanges.subscribe(res => {
      if (res) {
        this.getDealerDetails(res)
      }
    })
  }

  getDealerDetails(value) {
    if (this.isCreate) {
      this.service.getDealerLists(value).subscribe(result => {
        this.dealerList = result;
      })
    }
  }

  displayDealer(dealer) {
    return dealer ? dealer.dealerName : null
  }

  displayBranch(branch) {
    return branch ? branch.branch_name : null
  }

  displayFnState(stateDate) {
    return stateDate ? stateDate.state : undefined
  }
  displayFnDistrict(dist) {
    return dist ? dist.district : null
  }
  selecredDealer(event) {
    this.dealerId = event.option.value.id
    this.getBranch()
    if (this.isCreate) {
      this.getState()
    } else { }


  }
  selectededBranch(event) {

  }

  getBranch() {
    if (this.isCreate) {
      this.service.getBranchList(this.dealerId).subscribe(branch => {
        this.branchList = branch
        
      })
    } else {

    }
  }

  getCountry() {
    this.service.getCountry().subscribe(country => {
      this.country = country['result'];
      this.country.forEach(ele => {
        this.addDealerTerritoryForm.get('country').patchValue(ele.country);
        this.addDealerTerritoryForm.get('countryId').patchValue(ele.id)
      })

    })
  }

  getState() {

    this.service.getStateList(this.dealerId).subscribe(st => {
      this.stateList = st
      this.stateList.forEach(ele => {
        this.stateIdss = ele.stateId;
        this.sendStateId = Number(this.stateIdss)
        if (this.isCreate) {
          this.getDistric()
          this.addDealerTerritoryForm.get('state').patchValue(ele.state);
          this.addDealerTerritoryForm.get('stateId').patchValue(ele.id);
        } else {
          this.service.getDistrict(this.getStateIdForVieworEdit).subscribe(st => {
            this.districtList = st;
          })
        }


      })

    })
  }

  getDistric() {

    this.service.getDistrict(this.stateIdss).subscribe(st => {
      this.districtList = st;
    })

  }
  selectedDistrict(event) {
    // console.log(event.value.id,'event')
    this.distId = event.value.id ? event.value.id : null
    this.getTehsilList()
  }
  getTehsilList() {
    this.service.getTehsil(this.distId).subscribe(res => {
      this.tehsilList = res
    })
  }




  clearForm() {
    this.addDealerTerritoryForm.reset()
    this.getCountry()
  }

  validateForm() {

    if (this.addDealerTerritoryForm.invalid) {
      this.addDealerTerritoryForm.markAllAsTouched()
      this.toaster.error("Please fill all required field")
      return false
    } else {
      this.openConfirmDialog()
    }
  }

  private openConfirmDialog() {
    let message = 'Do you want to submit Dealer Territory Mapping?';
    if (this.isEdit) {
      message = 'Do you want to update Dealer Territory Mapping?';
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
        this.submitForm();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitForm();
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

  editSelect(event) {
    this.addDealerTerritoryForm.get('tehsil').reset()
    this.map=[];
    for (let i = 0; i < this.districListforEditOrView.length; i++) {
      if (this.districListforEditOrView[i].district == event.value) {
        const data = this.districListforEditOrView[i].id;
        this.disctricEditPayload = data;
        this.service.getTehsil(this.disctricEditPayload).subscribe(st => {
          this.tehsilListForEdit = st;
        })

      }
    }
  }
  submitForm() {
    let submitForm = {} as submitForm;
    let tehsil = []
    submitForm = this.addDealerTerritoryForm.getRawValue()
    if (this.isEdit) {
      if (typeof submitForm.dealerMaster !== 'object') {
        submitForm.dealerMaster = { id: this.dealerIdEditOrView }
      }
      delete submitForm['branchMaster'];
      delete submitForm['state'];
      delete submitForm['tehsil'];
      delete submitForm['stateId'];
      delete submitForm['IdForEdit'];
      submitForm['country'] = { 'id': submitForm['countryId'] };
      submitForm['branchMaster'] = { 'id': this.branchEditId };
      submitForm['state'] = { 'id': this.stateIdForVieworEdit ? this.stateIdForVieworEdit : this.stateIdForVieworEdit };
      submitForm['district'] = { 'id': this.disctricEditPayload ? this.disctricEditPayload : this.districIdforEdit };
      submitForm['id'] = this.headerId;
    }
    if (this.isCreate) {
      submitForm['country'] = { 'id': submitForm['countryId'] };
      submitForm['state'] = { 'id': this.sendStateId };
      delete submitForm.tehsil
      delete submitForm.countryId
    }

    if (this.isCreate) {
      submitForm.dealerTerritoryMapDtls = this.sendSubmitForPayload();
    } else {
      submitForm.dealerTerritoryMapDtls = this.sendSubmitPayloadForEdit();
    }
    if(this.isCreate){
    this.service.saveTerritoryData(submitForm,).subscribe(res => {
      if (res) {

        this.toaster.success(res.message)
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      } else {
        this.toaster.error(res.message)
      }
    })
  }else{
    this.service.saveTerritoryData(submitForm,).subscribe(res => {
      if (res) {

        this.toaster.success("Dealer Territory Updated Success")
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
      } else {
        this.toaster.error(res.message)
      }
    })
  }
  }



  toggleAllSelection() {
    if (this.allSelected.selected) {
      this.addDealerTerritoryForm.get('tehsil').patchValue([...this.tehsilList,'all'])
      // this.addDealerTerritoryForm.controls.tehsil.patchValue([...this.tehsilList, 'all']);
    } else {
      this.addDealerTerritoryForm.get('tehsil').patchValue([]);
    }
  }


  private sendSubmitForPayload() {
    const allDataFromTable = this.addDealerTerritoryForm.getRawValue()
    let sparePartsDetailsSend = []
    const allPartsTogether = []
    allDataFromTable.tehsil.forEach(element => {
      if (element.tehsil && element.id) {
        allPartsTogether.push({
          tehsil: {
            id: element.id,
          },
          id: null,
        })
      }
    })
    sparePartsDetailsSend = [...allPartsTogether]
    return sparePartsDetailsSend.length > 0 ? sparePartsDetailsSend : null
  }

  private sendSubmitPayloadForEdit() {
    const allDataFromTable = this.addDealerTerritoryForm.getRawValue();
    let sparePartsDetailsSend = [];
    const allPartsTogether = [];
    for (let j = 0; j < this.tehsilListViewOrEdit.length; j++) {
      this.map.push({
        tehsil: {
          id: this.tehsilListViewOrEdit[j].tehsil.id,
        },
        // id: this.tehsilListViewOrEdit[j].id,
        id:null
      });
    }
    sparePartsDetailsSend = [...this.map];
    return sparePartsDetailsSend.length > 0 ? sparePartsDetailsSend : null;
  }
  onItemSelect(evt) {
    this.map.push({
      tehsil: {
        id: evt.id,
      },
      id:null
    });
  }
  onSelectAll(evt) {
    this.tehsilListViewOrEdit = [];
    this.map = [];
    console.log(evt.id,'ffff')
    for(let x=0;x<evt.length;x++){
        this.map.push({
        tehsil: {
          id: evt[x].id,
        },
    
        id:null
      });
    }
   
  }
  OnItemDeSelect(event: any) {
   
    for (let j = 0; j < this.tehsilListViewOrEdit.length; j++) {
      if (this.tehsilListViewOrEdit[j].tehsil.id == event.id) {
        this.tehsilListViewOrEdit.pop(j);
      }
    }

    if (this.map.length  > 0) {
      for(let i=0;i<this.map.length;i++){
        this.map.pop(i);
      }
    }
   
  
  }
  onDeSelectAll(event: any) {
    this.tehsilListViewOrEdit = [];
    this.map = [];
  }
  selectAll(event){
    console.log(event,'ddd')
  }
  deSelectAll(event){
    console.log(event,'deselect')
  }
  select(event){
    console.log(event,'select')
  }
  deSelect(event){
   console.log(event,'deslect')
  }
}

