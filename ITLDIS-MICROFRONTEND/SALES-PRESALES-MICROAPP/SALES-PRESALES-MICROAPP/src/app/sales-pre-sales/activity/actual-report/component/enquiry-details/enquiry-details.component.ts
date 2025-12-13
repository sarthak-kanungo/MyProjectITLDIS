import { Component, OnInit, OnDestroy } from '@angular/core';
import { DateAdapter, MatDialog, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnquiryDetailsService } from './enquiry-details.service';
import { ActivityReportEnquiryDetails, } from 'ActualReportModule';
import { ActivityReportCommonService } from '../activity-report-create/activity-report-common.service';
import { ToastrService } from 'ngx-toastr';
import { UploadableFile } from 'itldis-file-upload';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { ActivatedRoute } from '@angular/router';
import { ActualReportCreatePageService } from '../../pages/actual-report-create/actual-report-create-page.service';
import { DataTable } from 'ngsw-search-table';
import { TableDataService } from 'src/app/ui/dynamic-table/table-data.service';
import { ImagePreviewComponent } from 'src/app/ui/image-preview/image-preview.component';


@Component({
  selector: 'app-enquiry-details',
  templateUrl: './enquiry-details.component.html',
  styleUrls: ['./enquiry-details.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }, EnquiryDetailsService
  ]
})

export class EnquiryDetailsComponent implements OnInit, OnDestroy {

  files: Array<UploadableFile> = new Array()
  isEdit: boolean;
  isView: boolean;
  disable = true;
  actualReportImageForm: FormGroup
  enquiryDetails: Array<ActivityReportEnquiryDetails>
  previewUrl: string
  dataTable: DataTable;
  totalTableElements: number;

  constructor(
    private enquiryDetailsService: EnquiryDetailsService,
    private activityReportCommonService: ActivityReportCommonService,
    private fileUploadService: FileUploadService,
    private actRt: ActivatedRoute,
    private pageService: ActualReportCreatePageService,
    private fb : FormBuilder,
    private toasterService: ToastrService,
    private tableDataService: TableDataService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.clearAll()
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
    this.buttonSubscribers()
  }

  private patchOrCreate() {
    if (this.isView) {
      this.files = this.activityReportCommonService.files
      this.enquiryDetails = this.activityReportCommonService.enquiryDetails
    } else {
      this.patchEnquiriesFromActivityNumberForCreate()
    }
  }

  createActivityReportImageForm() {
    this.actualReportImageForm = this.fb.group({
      images: [null, Validators.required],

    })
  }
  private patchEnquiriesFromActivityNumberForCreate() {
    this.activityReportCommonService.onActivityNumber.subscribe((event) => {
      let activityNumberId = event.proposalId;
      let fromDate = event.actualFromDate;
      let toDate = event.actualToDate; 

      this.enquiryDetailsService.getEnquiryDeatilsFromActivityNumber(activityNumberId,fromDate,toDate).subscribe(dto => {
        this.enquiryDetails = dto.result
        this.activityReportCommonService.enquiryDetails = this.enquiryDetails
        this.activityReportCommonService.onEnquiryDetails.emit(this.enquiryDetails);
      })
    })
  }

  private buttonSubscribers() {
    this.pageService.btnClick.subscribe(which => {
      if(which == 4) {
        this.clearAll()
      }
      if (which == 1){
        if (!(this.files.length > 0 && this.files.length <= 5)) {
          this.markFormAsTouched()
        }
      }
    })
  }

  private markFormAsTouched() {
    for (const key in this.actualReportImageForm.controls) {
      if (this.actualReportImageForm.controls.hasOwnProperty(key)) {
        this.actualReportImageForm.controls[key].markAsTouched();
      }
    }
  }

  fileSelctionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"ACTIVITY_REPORT")
		if(msg != 'OK'){
			this.toasterService.warning(msg);
			this.actualReportImageForm.get('images').reset()
			return false;
		}
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.activityReportCommonService.files = this.files
    }else{
        this.toasterService.error("Maximum 5 documents allowed");
    }
  }

  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

  private initOperationForm() {
    if (this.isView) {
    }
    else {
     this.createActivityReportImageForm()
    }
  }

  private checkOperationType() {
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
  }

  ngOnDestroy() {
    this.clearAll()
  }

  clearAll() {
    this.activityReportCommonService.files = []
    this.activityReportCommonService.enquiryDetails = []
    this.files = []
    this.enquiryDetails = []
    this.fileUploadService.deleteAllFiles()
  }

  showImageInPopup(event){
    
    const dialogRef = this.dialog.open(ImagePreviewComponent, {
      minWidth: '40%',
      panelClass: 'confirmation_modal',
      data: event,
      maxHeight: '80vh'
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }
}
