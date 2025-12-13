import { EventEmitter, Inject, Input, Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { FieldLevel, LevelHierarchy } from '../domain/create-assign-org-hierarchy-to-dealer-domain';
import { AssignOrgHierarchyToDealerPopUpService } from './assign-org-hierarchy-to-dealer-pop-up.service';


@Component({
  selector: 'app-assign-org-hierarchy-to-dealer-pop-up',
  templateUrl: './assign-org-hierarchy-to-dealer-pop-up.component.html',
  styleUrls: ['./assign-org-hierarchy-to-dealer-pop-up.component.scss'],
  providers: [AssignOrgHierarchyToDealerPopUpService]
})
export class AssignOrgHierarchyToDealerPopUpComponent implements OnInit {

  dealerFunctionForm: FormGroup
  orgHierItemForm: FormGroup ;
  orgHierarchyId: number;
  @Input() dealerFunctionList
  @Output() functionForm = new EventEmitter<any>()

  departmentId: any
  parentArray:any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []

  constructor(private fb: FormBuilder,
    private assignOrgHierarchyToDealerPopUpService: AssignOrgHierarchyToDealerPopUpService,
    public dialogRef: MatDialogRef<AssignOrgHierarchyToDealerPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    private toastr: ToastrService,) {
    this.departmentId = data.departmentId;
  }

  ngOnInit() {
    this.getLevelByDeprtment(this.departmentId);
  }

  getLevelByDeprtment(departmentId) {
    this.orgHierItemForm = new FormGroup({});
    this.parentArray=[];
    this.parentArray.length = 0
    this.assignOrgHierarchyToDealerPopUpService.getLevelByDepartment(departmentId).subscribe(res => {
      this.orgHierLevelList = res.result;
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.orgHierItemForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }
  
  buildJsonForSubmitForm() {
    this.dialogRef.close({
      orgHierarchyId : this.orgHierarchyId,
      guidelines : this.orgHierLevelList,
      popUpForm : this.orgHierItemForm
    });
  }

  compareFnFieldLevel(c1: any, c2: any): boolean {
    if (typeof c1 === 'object' && typeof c2 === 'string') return c1.hierarchy_desc === c2;
    if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.hierarchy_desc;
  }

  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.assignOrgHierarchyToDealerPopUpService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {

      this.childArray = res.result
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.assignOrgHierarchyToDealerPopUpService.getHierarchyByLevel(salesHoId.value.level_id, orgHierId).subscribe(res => {
      this.childArray = res.result;
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
  clearForm() {
    this.orgHierItemForm.reset();
    this.ngOnInit();
  }
  validateForm() {
      let formValue = this.orgHierItemForm.value;
      Object.keys(formValue).forEach(key => {
        ((formValue[key] === null) || (formValue[key] === '') || (typeof formValue[key] == 'string')) ? delete formValue[key] : (this.orgHierarchyId = formValue[key]['org_hierarchy_id']);
      });
      if(Object.keys(formValue).length==0){
        this.toastr.error(`Please fill level hierarchy`, 'Report empty level heirarchy fields')
      } else {
        //this.orgHierarchyId = 0;
        this.buildJsonForSubmitForm();
      }
  }
    
}
