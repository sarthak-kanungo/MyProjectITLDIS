import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { UploadableFile } from 'kubota-file-upload';
import { ToastrService } from 'ngx-toastr';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { Operation } from 'src/app/utils/operation-util';
import { ComplaintAssignPopupComponent } from '../../../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
import { DirectSurveyService } from '../../direct-survey.service';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';

@Component({
  selector: 'app-complain',
  templateUrl: './complain.component.html',
  styleUrls: ['./complain.component.css'],
})
export class ComplainComponent implements OnInit {
  complaintForm: FormArray
  complainRecordingForm: FormGroup
  surveyMachineDetailsForm: FormGroup
  directSurveyCreateDetailsFrom:FormGroup
  public files: Array<UploadableFile> = new Array();
  viewTable: boolean
  departmentList: any;
  typeOfComplaint: any

  isEdit: boolean
  isView: boolean
  isCreate: boolean

  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
    private tostr: ToastrService,
    private fileUploadService: FileUploadService,
    private directSurveyService: DirectSurveyService,
    private activityRoute: ActivatedRoute,
    public dialog: MatDialog,
    ) { }

  ngOnInit() {
    this.complaintForm = this.directSurveyPagePresenter.complainForm
    this.complainRecordingForm = this.directSurveyPagePresenter.complainRecordingForm
    this.surveyMachineDetailsForm = this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm
    this.directSurveyCreateDetailsFrom = this.directSurveyPagePresenter.searchDirectSurveyCreateDetailsFrom
    //this.directSurveyPagePresenter.addRowComplain()
    this.getDepartment()
    this.getTypeOfComplaint()
    this.viewEditCreate()
   
    
  }
  private viewEditCreate() {
    if (this.directSurveyPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true 
      this.addRow()
    }
    if (this.directSurveyPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.directSurveyPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }

  getDepartment() {
    this.directSurveyService.getLookupByCode("QA_DEPARTMENT").subscribe(response => {
      this.departmentList = response['result'];
    });
  }
  getTypeOfComplaint() {
    this.directSurveyService.getLookupByCode("TYPE_OF_COMPLAINT").subscribe(response => {
      this.typeOfComplaint = response['result'];
    });
  }

  addRow() {
    let flag:boolean = true;
    if(this.complaintForm.length>0){
      let fg = (this.complaintForm.controls[0] as FormGroup)
      if(fg.controls.department.value && fg.controls.assignTo.value
          && fg.controls.typeOfComplaint.value && fg.controls.description.value){
           
          }else{
            flag = false;
          }
    }
    if (flag) {
      this.directSurveyPagePresenter.addRowComplain()
    }
  }

  deleteRow(index: number) {
    this.directSurveyPagePresenter.removeRowComplain(index)
  }

  deleteAudio(id: string) {
    this.fileUploadService.deleteAudioFile(id)
    this.files = this.files.filter(file => file.id !== id)
    this.files = this.fileUploadService.complaintlistUploadableFiles();
    if (this.files.length == 0) {
      this.viewTable = false
      this.complainRecordingForm.reset();
    }
  }

  uploadRecording(fileInput: Event) {
    let type = fileInput.target['files'][0].type
    if (type==='audio/ogg' || type==='video/x-ms-wmv' || type==='audio/mpeg' || type==='audio/wav') {
      if (this.fileUploadService.audioFile.length < 2) {
        let newType = 'COMPLAINT'
        this.fileUploadService.newtype.next(newType);
        this.fileUploadService.onFileSelect(fileInput)
        this.files = this.fileUploadService.complaintlistUploadableFiles();
        this.directSurveyPagePresenter.complaintRecordingFiles(this.files);

        this.viewTable = true
      }
      else if (this.fileUploadService.audioFile.length >= 2) {
        this.tostr.error('Maximum two file can be attached')
      }
    }
    else {
      this.tostr.error('Please upload a Valid Audio File')
    }

  }

  // isFilesCountFive() {
  //   return this.fileUploadService.fileCount() == 2
  // }

  validateDepartment(sfg:FormGroup,ftype){
    let count = 0;
    let flag:boolean = true;
    this.complaintForm.controls.forEach(fg => {
      if(fg.get('department').value == sfg.get('department').value &&
            fg.get('typeOfComplaint').value == sfg.get('typeOfComplaint').value ){
        count++;
      }
      if(count>1){
        this.tostr.error('Department/Complaint Type can not be same for multiple complaint');
        fg.get(ftype).reset();
        flag = false;
        return false;
      }
    });
    return flag;
  }

  openComplaintAssignPopup(fg:FormGroup){
    if(this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm.get('dealerId').value==undefined || 
            this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm.get('dealerId').value == null) {
              this.tostr.error('Select Chassis no');
              this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm.get('chassisNo').markAsTouched();
              fg.get('department').reset();
    }else if(this.validateDepartment(fg,'department')){
      fg.get('assignTo').reset();
      fg.get('hoUserId').reset();
      const dialogRef = this.dialog.open(ComplaintAssignPopupComponent, {
        width: '90%',
        panelClass: 'confirmation_modal',
        data: {'dealerId':this.directSurveyCreateDetailsFrom.get('soldToDealer').value.id, 'department':fg.get('department').value},
        maxHeight: '80vh'
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result && result.data){
          fg.get('assignTo').patchValue(result['data']['name']+" / "+result['data']['designation']);
          fg.get('hoUserId').patchValue(result['data']['id']);
        }
      });
    }
  }


}
