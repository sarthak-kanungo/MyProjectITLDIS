import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { UploadableFile } from 'itldis-file-upload';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { ServiceActivityClaimPagePresenter } from '../service-activity-claim-page/service-activity-claim-page-presenter';
import { HeadsDetailsByActivityNumber, ViewActivityClaim } from '../../domain/service-activity-claim.domain';
import { ServiceActivityClaimHeadsWebService } from './service-activity-claim-heads-web.service';
import { FormGroup, FormArray, AbstractControl } from '@angular/forms';
import { element } from 'protractor';
import { Operation } from '../../../../../utils/operation-util';
import { ToastrService } from 'ngx-toastr';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { ServiceActivityClaimPageStore } from '../service-activity-claim-page/service-activity-claim-page.store';
import { ServiceActivityClaimPageWebService } from '../service-activity-claim-page/service-activity-claim-page-web.service';

@Component({
  selector: 'app-service-activity-claim-heads',
  templateUrl: './service-activity-claim-heads.component.html',
  styleUrls: ['./service-activity-claim-heads.component.scss'],
  providers: [ServiceActivityClaimHeadsWebService]
})
export class ServiceActivityClaimHeadsComponent implements OnInit, OnChanges {

  @Input() activityHeadsDetailsForm: FormGroup
  isSelectActivityNoEvent: boolean
  files: Array<UploadableFile> = new Array()
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
  isApproval: boolean
  previewUrl: string
  fileNamelist = []
  fileName: string
  fileStaticPath: string = ServiceActivityClaimApi.staticPath
  totalProposedAmount: number = 0
  totalClaimAmount: number = 0
  totalApprovedAmount: number = 0
  claimApprovedAmount: number = 0
  @Input() viewActivityClaim: ViewActivityClaim

  subActivityTotalApprovedAmount:number=0;

  constructor(
    private serviceActivityClaimPagePresenter: ServiceActivityClaimPagePresenter,
    private serviceActivityClaimHeadsWebService: ServiceActivityClaimHeadsWebService,
    private pageService: ServiceActivityClaimPageWebService,
    private fileUploadService: FileUploadService,
    private toastr : ToastrService
  ) { }

  ngOnChanges() {
    if (this.viewActivityClaim) {
      if (this.viewActivityClaim.activityClaimHeads) {
        this.isSelectActivityNoEvent = true
      }
      this.totalProposedAmount = this.serviceActivityClaimPagePresenter.calculationForProposedAmount()
      this.totalClaimAmount = this.viewActivityClaim.activityClaimHeaderData.totalClaimAmount
      this.totalApprovedAmount = this.viewActivityClaim.activityClaimHeaderData.approvedAmount
      this.serviceActivityClaimPagePresenter.totalServiceActivityClaim.get('totalClaimApprovedAmount').patchValue(this.claimApprovedAmount)
    }
    if(this.isApproval){
      this.activityHeadsDetailsForm.get('headDetailsdataTable')['controls'].forEach(fg => {
        fg.get('claimApprovedAmount').enable();
        fg.get('remark').enable();
        fg.get('claimApprovedAmount').patchValue(fg.get('claimApprovedAmount').value);
        fg.get('actualClaimAmount').disable();
        fg.get('vendorName').disable();
        fg.get('billNo').disable();
        fg.get('billDate').disable();
      });
    }
    if(this.isEdit){
      this.activityHeadsDetailsForm.get('headDetailsdataTable')['controls'].forEach(fg => {
        fg.get('actualClaimAmount').disable();
       
      });
    }
    if(this.isView){
      this.activityHeadsDetailsForm.get('headDetailsdataTable')['controls'].forEach(fg => {
        fg.get('actualClaimAmount').disable();
        fg.get('vendorName').disable();
        fg.get('billNo').disable();
        fg.get('billDate').disable();
      });
    }
  }

  ngOnInit() {
    this.viewOrEditOrCreate();
    this.pageService.totalApprovedAmount.subscribe(response => {
      this.subActivityTotalApprovedAmount = response;
      this.calculateTotal();
    });
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.selectActivityNumberEvent()
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.APPROVAL) {
      this.isApproval = true
      this.claimApprovedAmount = this.totalClaimAmount;

    }else if (this.serviceActivityClaimPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
  
    }
  }
  calculateTotal(){
    let sum:number=0;
    this.activityHeadsDetailsForm.get('headDetailsdataTable')['controls'].forEach(fg => {
        if(fg.get('claimApprovedAmount').value){
          sum = sum + parseInt(fg.get('claimApprovedAmount').value);
        }
    })
    this.claimApprovedAmount = sum + this.subActivityTotalApprovedAmount;
    this.serviceActivityClaimPagePresenter.totalServiceActivityClaim.get('totalClaimApprovedAmount').patchValue(this.claimApprovedAmount)
  }
  selectActivityNumberEvent() {
    this.serviceActivityClaimPagePresenter.activityClaimForm.get('activityNo').valueChanges.subscribe(value => {
      this.totalProposedAmount = 0
      this.totalClaimAmount = 0;
      (this.serviceActivityClaimPagePresenter.headDetailstableData.get('headDetailsdataTable') as FormArray).clear();
      if (value.activityNumber) {
        this.serviceActivityClaimHeadsWebService.getHeadsDataForActivityClaim(value.id).subscribe(response => {
          if (response) {
            this.isSelectActivityNoEvent = true
          }
          response.forEach(head => {
            this.serviceActivityClaimPagePresenter.addHeadsDetails(head)
          })
          this.totalProposedAmount = this.serviceActivityClaimPagePresenter.calculationForProposedAmount()
          this.totalClaimAmount = this.serviceActivityClaimPagePresenter.calculationForClaimAmount()
          this.totalOfClaimAmount()
          this.valueChangesSubActivityClaimAmt()
          this.totalApprovedAmount = this.serviceActivityClaimPagePresenter.patchApprovedAmount(this.totalApprovedAmount)
          this.serviceActivityClaimPagePresenter.totalServiceActivityClaim.get('approvedAmount').patchValue(this.totalApprovedAmount)
        })

      }
    })
  }

  totalOfClaimAmount() {
    const dataTable = this.activityHeadsDetailsForm.get('headDetailsdataTable') as FormArray;
    dataTable.controls.forEach((element: FormGroup) => {
      element.get('actualClaimAmount').valueChanges.subscribe(claimAmt => {
        this.totalClaimAmount = this.serviceActivityClaimPagePresenter.calculationForClaimAmount()
        this.serviceActivityClaimPagePresenter.validateIssueQty(element)
      })
    })
  }

  valueChangesSubActivityClaimAmt() {
    const subActivity = this.serviceActivityClaimPagePresenter.subActivityDetailstableData.get('subActivityDetailsdataTable') as FormArray;
    subActivity.controls.forEach((element: FormGroup) => {
      element.get('actualClaimAmount').valueChanges.subscribe(claimAmt => {
        if (claimAmt) {
          this.totalClaimAmount = this.serviceActivityClaimPagePresenter.calculationForClaimAmount()
          this.serviceActivityClaimPagePresenter.validateIssueQty(element)
        }
      })
    })
  }

  claimApprovedAmountChanges(event: Event) {
    console.log("event ", event.target['value']);
    this.serviceActivityClaimPagePresenter.totalServiceActivityClaim.get('totalClaimApprovedAmount').patchValue(event.target['value'])
  }

  fileSelectionChanges(heads: any, fileInput: Event, img: HTMLElement) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"ACTIVITY_CLAIM")
		if(msg != 'OK'){
			this.toastr.warning(msg);
			heads.get('image').reset()
			return false;
		}
    if (fileInput && fileInput.target['files'][0]) {
      heads.get('image').value = fileInput.target['files'][0]
      this.previewIfImage(heads.get('image').value, img)
    }
  }

  private previewIfImage(file: File, img: HTMLElement) {
    const mimeType = file.type;
    if (mimeType.match(/image\/*/) == null) {
      return '';
    }
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (event) => {
      img['src'] = event.target['result']
    }
  }

}
