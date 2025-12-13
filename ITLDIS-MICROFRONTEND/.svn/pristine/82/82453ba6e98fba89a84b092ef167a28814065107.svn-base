import { AfterContentChecked, AfterContentInit, AfterViewInit, Component, Inject, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { orgHierDepartment } from '../../../kubota-empolyee-master/component/search-kubota-empolyee/search-kubota-employee';
import { ManageOrganizationHierarchyService } from '../manage-organization-hierarchy.service';
import {MatDialog} from '@angular/material/dialog';
import { ManageOrganizationHierarchyModalComponent } from './manage-organization-hierarchy-modal/manage-organization-hierarchy-modal.component';

@Component({
  selector: 'app-manage-organization-hierarchy-create',
  templateUrl: './manage-organization-hierarchy-create.component.html',
  styleUrls: ['./manage-organization-hierarchy-create.component.scss']
})
export class ManageOrganizationHierarchyCreateComponent implements OnInit  {
  orgHierLevelList: any[] = []
  orgHierForm: FormGroup ;
  orgHierItemForm: FormGroup ;
  departmentsList: any
  parentArray: any[] ;
  updateArray: boolean[];
  deptName: string
  deptId: string
  childArray: any[] = []
  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private manageOrganization: ManageOrganizationHierarchyService,
    public dialog: MatDialog,
  ) {
    this.orgHierForm = this.fb.group({
      department: ''
    })
  }

  ngOnInit() {
    this.employeMastDepartment();
  }
  
  employeMastDepartment() {
    this.manageOrganization.orgHierDepartmentDropdown().subscribe(res => {
      this.departmentsList = res
    })

  }
  

  empMastDeptSelect(deptSelect) {
    this.orgHierItemForm = new FormGroup({});
    this.parentArray=[];
    this.updateArray=[];
    this.deptName = deptSelect.value.departmentName
    this.deptId = deptSelect.value.id
    this.parentArray.length = 0
    this.manageOrganization.getLevelForOrgHier(deptSelect.value.id).subscribe(res => {
      this.orgHierLevelList = res;
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.updateArray.push(false);
          this.orgHierItemForm.addControl(obj.LEVEL_DESC, new FormControl(obj.LEVEL_DESC));
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  displayFnDepartmentName(department: orgHierDepartment) {
    return department ? department.departmentName : undefined;
  }
  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.manageOrganization.getLevelDetailsForOrgHier(levelId, orgHierId).subscribe(res => {

      this.childArray = res
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.manageOrganization.getLevelDetailsForOrgHier(salesHoId.value.level_id, orgHierId).subscribe(res => {
      this.childArray = res;
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
        this.updateArray[i-1]=false;
      }
      this.updateArray[index]=true;
    })
  }
  openUpdateDialog(obj, index): void {
    let parentOrgHierires = [];
    if(index>0){
      parentOrgHierires = this.parentArray[index-1];
    }
    const dialogRef = this.dialog.open(ManageOrganizationHierarchyModalComponent, {
      width: '90%',
      maxHeight: '80vh',
      panelClass: 'confirmation_modal',
      data: {
        parentOrgHiryId:parentOrgHierires, 
        orgHiryId:this.orgHierItemForm.get(obj.LEVEL_DESC).value.org_hierarchy_id, 
        parentHiryId:this.orgHierItemForm.get(obj.LEVEL_DESC).value.parent_org_hierarchy_id, 
        code:this.orgHierItemForm.get(obj.LEVEL_DESC).value.hierarchy_code, 
        description:this.orgHierItemForm.get(obj.LEVEL_DESC).value.hierarchy_desc, 
        isactive:this.orgHierItemForm.get(obj.LEVEL_DESC).value.isactive, 
        levelId : this.orgHierItemForm.get(obj.LEVEL_DESC).value.level_id
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result && result.message == 'success'){
        this.empMastDeptSelect({value:{id:this.deptId, departmentName: this.deptName}});        
      }
    });
  }

  openDialog(levelId, index): void {
    let parentOrgHierires = [];
    if(index>0){
      parentOrgHierires = this.parentArray[index-1];
      if(parentOrgHierires == undefined || parentOrgHierires.length==0){
        this.toastr.error('Parent Organization not available','Mandatory Field');
        return;
      }
    }
    const dialogRef = this.dialog.open(ManageOrganizationHierarchyModalComponent, {
      width: '90%',
      maxHeight: '80vh',
      panelClass: 'confirmation_modal',
      data: {
        parentOrgHiryId:parentOrgHierires, 
        levelId : levelId
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result && result.message == 'success'){
        this.empMastDeptSelect({value:{id:this.deptId, departmentName: this.deptName}});        
      }
    });
  }
  
}
