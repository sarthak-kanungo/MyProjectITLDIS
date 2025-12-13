import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { UploadableFile } from 'kubota-file-upload';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { ServiceActivityClaimPagePresenter } from '../service-activity-claim-page/service-activity-claim-page-presenter';
import { Operation } from '../../../../../utils/operation-util';
import { ServiceActivityClaimSubActivityWebService } from './service-activity-claim-sub-activity-web.service';
import { ViewActivityClaim } from '../../domain/service-activity-claim.domain';
import { ServiceActivityClaimPageWebService } from '../service-activity-claim-page/service-activity-claim-page-web.service';

@Component({
  selector: 'app-service-activity-claim-sub-activity',
  templateUrl: './service-activity-claim-sub-activity.component.html',
  styleUrls: ['./service-activity-claim-sub-activity.component.scss'],
  providers: [ServiceActivityClaimSubActivityWebService]
})
export class ServiceActivityClaimSubActivityComponent implements OnInit, OnChanges {

  @Input() activitySubActivityDetailsForm: FormGroup
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
  @Input() viewActivityClaim: ViewActivityClaim

  constructor(
    private serviceActivityClaimPagePresenter: ServiceActivityClaimPagePresenter,
    private pageService:ServiceActivityClaimPageWebService,
    private serviceActivityClaimSubActivityWebService: ServiceActivityClaimSubActivityWebService
  ) { }

  ngOnChanges() {
    if (this.viewActivityClaim) {
      if (this.viewActivityClaim.subActivities.length > 0) {
        this.isSelectActivityNoEvent = true
      }
    }
    
    if(this.isApproval){
      let fg = (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls as FormGroup[];
      (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls.forEach(fg => {
        fg.get('claimApprovedAmount').enable();
        fg.get('remark').enable();
        fg.get('actualClaimAmount').disable();
        fg.get('vendorName').disable();
        fg.get('billNo').disable();
        fg.get('billDate').disable();
      });
    }
    if(this.isView){
      let fg = (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls as FormGroup[];
      (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls.forEach(fg => {
        fg.get('actualClaimAmount').disable();
        fg.get('vendorName').disable();
        fg.get('billNo').disable();
        fg.get('billDate').disable();
      });
    }
    if(this.isEdit){
      let fg = (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls as FormGroup[];
      (this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable') as FormArray).controls.forEach(fg => {
        fg.get('actualClaimAmount').disable();
        // fg.get('remark').enable();
      });
    }
  }


  ngOnInit() {
    this.viewOrEditOrCreate();
  }

  calculateTotal(){
    let sum:number=0;
    this.activitySubActivityDetailsForm.get('subActivityDetailsdataTable')['controls'].forEach(fg => {
        if(fg.get('claimApprovedAmount').value){
          sum = sum + parseInt(fg.get('claimApprovedAmount').value);
        }
    })
    this.pageService.totalApprovedAmount.next(sum);
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.selectActivityNumberEvent()
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.APPROVAL) {
      this.isApproval = true
    } else if(this.serviceActivityClaimPagePresenter.operation === Operation.EDIT){
      this.isEdit = true
    }
    
  }

  selectActivityNumberEvent() {
    this.serviceActivityClaimPagePresenter.activityClaimForm.get('activityType').valueChanges.subscribe(value => {
      if (value === '5 in 1 Camp') {
        this.isSelectActivityNoEvent = true
      }
    })
    this.serviceActivityClaimPagePresenter.activityClaimForm.get('activityNo').valueChanges.subscribe(value => {
      if (value.activityNumber) {
        this.serviceActivityClaimSubActivityWebService.getSubActivityForActivityClaim(value.id).subscribe(response => {
          response.forEach(data => {
            this.serviceActivityClaimPagePresenter.addSubActivityDetails(data)
          })
          this.serviceActivityClaimPagePresenter.calculationForClaimAmount()
        })
      }
    })
  }

  fileSelectionChanges(heads: any, fileInput: Event, img: HTMLElement) {
    if (fileInput && fileInput.target['files'][0]) {
      heads.get('image').value = fileInput.target['files'][0]
      console.log("image ", heads.get('image').value);
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
