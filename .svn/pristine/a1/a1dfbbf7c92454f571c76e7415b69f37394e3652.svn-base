import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { ServiceActivityClaimPagePresenter } from '../service-activity-claim-page/service-activity-claim-page-presenter';
import { Operation } from '../../../../../utils/operation-util';
import { ServiceActivityClaimPhotoWebService } from './service-activity-claim-photo-web.service';
import { ViewActivityClaim } from '../../domain/service-activity-claim.domain';

@Component({
  selector: 'app-service-activity-claim-photo',
  templateUrl: './service-activity-claim-photo.component.html',
  styleUrls: ['./service-activity-claim-photo.component.scss'],
  providers: [ServiceActivityClaimPhotoWebService]
})
export class ServiceActivityClaimPhotoComponent implements OnInit, OnChanges {
  isView:boolean=false
  fileNamelist = []
  isSelectActivityNoEvent: boolean;
  isApprove:boolean=false;
  fileStaticPath: string = ServiceActivityClaimApi.staticPath
  @Input() viewActivityClaim: ViewActivityClaim
  @Input() reportPhoto:any
  constructor(
    private serviceActivityClaimPagePresenter: ServiceActivityClaimPagePresenter,
    private serviceActivityClaimPhotoWebService: ServiceActivityClaimPhotoWebService
  ) { }

  ngOnChanges() {
    
    if (this.viewActivityClaim) {
      // if (this.viewActivityClaim.reportPhotos) {
      //   this.isSelectActivityNoEvent = true
      // }
      // this.viewActivityClaim.reportPhotos.forEach(image => {
      //   this.fileNamelist.push(image);
      // });
      if(this.viewActivityClaim.activityClaimHeads){
        this.isSelectActivityNoEvent = true
        this.viewActivityClaim.activityClaimHeads.forEach(head => {
          let filename = head.headImage;
          let fileType = '';
          if(filename){
              fileType = (filename.toString()).substring(filename.toString().lastIndexOf('.')+1).toLocaleLowerCase();
          }
          if(fileType){
            this.fileNamelist.push({fileName:filename.toString(),fileType:fileType})
          }
        });
      }
    }
  }

  ngOnInit() {
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.VIEW) {
      this.isView=true
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.selectActivityNumberEvent()
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.APPROVAL) {
       this.isApprove=true
    }
  }

  selectActivityNumberEvent() {
    this.serviceActivityClaimPagePresenter.activityClaimForm.get('activityNo').valueChanges.subscribe(value => {
      if (value.activityNumber) {
        this.isSelectActivityNoEvent = true
        this.serviceActivityClaimPhotoWebService.getActivityReportPhotosByProposalId(value.id).subscribe(response => {
          // response['result'].forEach(element => {
          //   this.fileNamelist=element
          // });
          this.fileNamelist=response['result']
        })
      }
    })
  }


}
