import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDialogRef, MAT_DIALOG_DATA, ThemePalette } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ManageOrganizationHierarchyService } from '../../manage-organization-hierarchy.service';


@Component({
  selector: 'app-manage-organization-hierarchy-modal',
  templateUrl: './manage-organization-hierarchy-modal.component.html',
  styleUrls: ['./manage-organization-hierarchy-modal.component.css']
})
export class ManageOrganizationHierarchyModalComponent implements OnInit {
  parentHierArray: any[]
  title:string='Add'
  constructor(
    private formBuilder: FormBuilder,
    private toastr : ToastrService,
    private manageHierarchyService : ManageOrganizationHierarchyService,
    public dialogRef: MatDialogRef<ManageOrganizationHierarchyModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { 
      this.parentHierArray = data.parentOrgHiryId;
      if(this.parentHierArray.length==0){
        this.addHierarchyForm.get('parentOrgHierarchyId').clearValidators();
        this.addHierarchyForm.get('parentOrgHierarchyId').updateValueAndValidity();
      }
      if(this.data.orgHiryId){
        this.title="Update";
        this.addHierarchyForm.get('hierarchyCode').disable();
        this.addHierarchyForm.get('isactive').enable();
        this.addHierarchyForm.get('parentOrgHierarchyId').patchValue(data.parentHiryId);
        this.addHierarchyForm.get('hierarchyDesc').patchValue(data.description);
        this.addHierarchyForm.get('hierarchyCode').patchValue(data.code);
        if(data.isactive && data.isactive=='Y'){
          this.addHierarchyForm.get('isactive').patchValue(true);
        }else{
          this.addHierarchyForm.get('isactive').patchValue(false);
        }
      }
  }

  addHierarchyForm:FormGroup = this.formBuilder.group({
    hierarchyCode : [null, Validators.compose([Validators.required])],
    hierarchyDesc : [null, Validators.compose([Validators.required])],
    parentOrgHierarchyId : [null, Validators.compose([Validators.required])],
    orgHierarchyId : [null],
    levelId : [null],
    isactive: [{value:true, disabled:true}]
  });

  ngOnInit() {
    
  }

  validateCode(event){
    if(event.target.value && event.target.value!=''){
      this.manageHierarchyService.validateOrganizationCode(event.target.value).subscribe(response => {
        if(response['message']=='Exist'){
          this.toastr.warning('Code already used.','Warrning');
          this.addHierarchyForm.get('hierarchyCode').reset();
        }
      })
    }
  }

  validateAddForm(){
    this.addHierarchyForm.markAllAsTouched();
    if(this.addHierarchyForm.valid){
      const saveObject = this.addHierarchyForm.getRawValue();
      saveObject['orgHierarchyId'] = this.data.orgHiryId;
      saveObject['levelId'] = this.data.levelId;
      if(saveObject['isactive']==true){
        saveObject['isactive']='Y';
      }else{
        saveObject['isactive']='N';
      }
      this.manageHierarchyService.addHierarchy(saveObject).subscribe(response => {
        if(response){
          this.toastr.success('Details added succesfully');
          this.dialogRef.close({message:'success'});
        }else{
          this.toastr.error('Error while saving details','Transaction Failed');
        }
      },error => {
        this.toastr.error('Error while saving details','Transaction Failed');
      })
    }else{
      this.toastr.error('Enter Required fields details','Mandatory Fields');
    }
  }
  closeDialog(): void {
    this.dialogRef.close();
  }

}
