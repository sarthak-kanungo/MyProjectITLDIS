import { Component, OnInit, Input } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { RfcPagePresenter } from '../retro-fitment-campaign-create-page/retro-fitment-campaign-create-page.presenter';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { RfcApi } from '../../url-utils/retro-fitment-campaign-url';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { ToastrService } from 'ngx-toastr';
import { RetroFitmentCampaignMaterialDetailsWebService } from '../retro-fitment-campaign-material-details/retro-fitment-campaign-material-details-web.service';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { SearchAutocompleteJobCode } from '../../domain/retro-fitment-campaign.domain';

@Component({
  selector: 'app-retro-fitment-campaign-labour-charges',
  templateUrl: './retro-fitment-campaign-labour-charges.component.html',
  styleUrls: ['./retro-fitment-campaign-labour-charges.component.scss'],
  providers: [RetroFitmentCampaignMaterialDetailsWebService]
})
export class RetroFitmentCampaignLabourChargesComponent implements OnInit {
  @Input() labourChargesForm: FormArray;
  files: Array<UploadableFile> = new Array();
  labourChargeHeading: Array<string> = [];
  @Input() warrantyPcrPhotos: WarrantyPcrPhotos;
  isView = false;
  fileStaticPath = RfcApi.staticPath;
  jobCode: any;
  selectedForm: FormGroup;
  selectedCode: any;
  constructor(
    private fileUploadService: FileUploadService,
    private rfcPagePresenter: RfcPagePresenter,
    private activatedRoute: ActivatedRoute,
    private toaster: ToastrService,
    private service: RetroFitmentCampaignMaterialDetailsWebService
  ) { }

  ngOnInit() {
    this.labourChargeHeading = ['Sl.No', 'Job Code', 'Description', 'Hours', 'Claim Amount'];
    // console.log('this.warrantyPcrPhotos', this.warrantyPcrPhotos);
    this.checkFormOperation();
    this.formValueChanges()
  }

  private checkFormOperation() {
    this.rfcPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.rfcPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
    }
    else if (this.rfcPagePresenter.operation == Operation.CREATE) {
      this.rfcPagePresenter.addRowInLabourCharges();
    }
  }
  private formValueChanges() {
    this.labourChargesForm.controls.forEach(elt => {
      elt.get('serviceMtJobcode').valueChanges.subscribe(val => {
        if (val) {
          this.getAutoCompleteJobCode(val);
          // this.labourChargesForm.get('description').reset()
        }
      })
    })
  }
  // add api for job card  no
  private getAutoCompleteJobCode(txt: string) {
    this.service.getAutoCompleteJobCode(txt).subscribe(res => {
      this.jobCode = res;
    })
  }



  jobCodeSelection(evt: MatAutocompleteSelectedEvent, list: FormGroup, ) {
    // console.log(evt, 'evt', 'index', index)
    if (evt) {
      this.selectedForm = list;
      this.selectedCode = evt.option.value.jobcode;

      this.selectedForm.get('description').patchValue(evt.option.value.description);
    }
  }


  fileSelectionChanges(fileInput: Event) {
    if (fileInput.target['files'][0].size > 1000000) {
      this.toaster.error("You can't upload Maximum 1mb file");
      return
    }
    if (fileInput.target['files'][0].size < 100000) {
      this.toaster.error("You can't upload  Minimum 100kb file");
      return
    }
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.rfcPagePresenter.collectRFCFiles(this.files);

    }
  }
  deleteImage(id: string) {
    // console.log(this.files)
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    this.rfcPagePresenter.collectRFCFiles(this.files);
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

  // displayJobCodeFn(jobcode: SearchAutocompleteJobCode) {
  // 	return jobcode ? jobcode.jobcode : undefined
  // }
  addRow() {
    this.rfcPagePresenter.addRowInLabourCharges()
  }
  deleteRow() {
    this.rfcPagePresenter.deleteRowInLabourCharges()
  }
  displayJobCodeFn(jobcode: SearchAutocompleteJobCode) {
		return jobcode ? jobcode.value : undefined
	}
}
