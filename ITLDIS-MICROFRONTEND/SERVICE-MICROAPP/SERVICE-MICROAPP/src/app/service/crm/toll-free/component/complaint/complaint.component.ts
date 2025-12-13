import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadableFile } from 'itldis-file-upload';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ComplaintAssignPopupComponent } from '../../../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';
import { DirectSurveyApi } from '../../../direct-survey/url-util/direct-survey-urls';
import { TollFreeSubmiteDto, SubmitJson } from '../../domain/toll-free-domain';
import { TollFreeService } from '../../service/toll-free.service';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';


@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit, OnChanges {
  public fileStaticPath: string = DirectSurveyApi.staticPath;
  @Input()
  isEdit: boolean;
  @Input()
  isView: boolean;
  @Input()
  isCreate: boolean;
  @Input() complaintForm: FormArray
  @Input() referenceDetailsForm: FormGroup
  complaintType = ['Complaint', 'Query']
  selectedToppings;
  public files: Array<UploadableFile> = new Array()
  viewTable: boolean
  departmentList: any;
  @Input()
  fileUploadedList:any[]
  constructor(
    private pagePresenter1: TollFreePagePresenter,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    public dialog: MatDialog,
    private service: TollFreeService, private tostr: ToastrService,
    private fileUploadService: FileUploadService) { }

  ngOnChanges(){
  }

  ngOnInit() {
    this.service.getLookupByCode("QA_DEPARTMENT").subscribe(response => {
      this.departmentList = response['result'];
    });
    if(this.isCreate){
      this.pagePresenter1.addRowComplaint()
    }
    if(this.fileUploadedList && this.fileUploadedList.length>0){
      this.viewTable = true;
      this.fileUploadedList.forEach(file => {
        this.files.push({
          id:file.id,
          previewUrl:this.fileStaticPath+file.fileName,
          file:file
        })
      });
    }
  }
  addRow() {
      this.pagePresenter1.addRowComplaint()
  }

  deleteRow(index: number) {
    //if (this.complaintForm.length > 1) {
      this.pagePresenter1.deleteRowComplaint(index)
    //}
  }

  buildJsonForTollFree() {
    const formValue = this.complaintForm.getRawValue()
    let submitData = {} as TollFreeSubmiteDto
    let tollFreeCallEntity: Array<any> = []
    formValue.forEach(element => {
      tollFreeCallEntity.push({
        department: element['department'],

      })
    })
    submitData.tollFreeCallEntity = tollFreeCallEntity
    return submitData
  }


  clearForm() {
    this.complaintForm.clear()
  }





  deleteAudio(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    if (this.files.length == 0) {
      // this.complaintForm.get('complainRecording').reset();
      this.viewTable = false
    }
    console.log(this.files)
  }

  uploadRecording(fileInput: Event) {
    let type = fileInput.target['files'][0].type
    if (type==='audio/ogg' || type==='video/x-ms-wmv') {
        if (!this.isFilesCountFive()) {
          this.fileUploadService.onFileSelect(fileInput)
          this.files = this.fileUploadService.listUploadableFiles();
          this.pagePresenter1.tollRecordingFiles(this.files);
          this.viewTable=true
        }else{
          this.tostr.error('Max 5 Audio/Video Files can be upload')    
        }
    } else{
      this.tostr.error('Please upload a Valid Audio/Video File')
    }
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }
  validateDepartment(sfg:FormGroup,ftype){
    let count = 0;
    let flag:boolean = true;
    this.complaintForm.controls.forEach(fg => {
      if(fg.get('department').value == sfg.get('department').value &&
            fg.get('complaintType').value == sfg.get('complaintType').value ){
        count++;
      }
      if(count>1){
        this.toastr.error('Department/Complaint Type can not be same for multiple complaint');
        fg.get(ftype).reset();
        flag = false;
        return false;
      }
    });
    return flag;
  }
  openComplaintAssignPopup(fg:FormGroup){
    if(this.pagePresenter1.machineDetailsForm.get('dealerId').value==undefined || 
            this.pagePresenter1.machineDetailsForm.get('dealerId').value == null) {
              this.toastr.error('Select Chassis no');
              this.pagePresenter1.machineDetailsForm.get('chassisNo').markAsTouched();
              fg.get('department').reset();
    }else if(this.validateDepartment(fg,'department')){
      fg.get('assignTo').reset();
      fg.get('assignToId').reset();
      const dialogRef = this.dialog.open(ComplaintAssignPopupComponent, {
        width: '90%',
        panelClass: 'confirmation_modal',
        data: {'dealerId':this.pagePresenter1.machineDetailsForm.get('dealerId').value, 'department':fg.get('department').value},
        maxHeight: '80vh'
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result && result.data){
          fg.get('assignTo').patchValue(result['data']['name']+" / "+result['data']['designation']);
          fg.get('assignToId').patchValue(result['data']['id']);
        }
      });
    }
  }
}
