import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FileExtension } from '../../domain/retro-fitment-campaign.domain';
import { RfcPagePresenter } from '../retro-fitment-campaign-create-page/retro-fitment-campaign-create-page.presenter';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { RetroFitmentCampaignCreatePageWebService } from '../retro-fitment-campaign-create-page/retro-fitment-campaign-create-page-web.service';
@Component({
  selector: 'app-retro-fitment-campaign',
  templateUrl: './retro-fitment-campaign.component.html',
  styleUrls: ['./retro-fitment-campaign.component.scss'],
  providers: [RetroFitmentCampaignCreatePageWebService]
})
export class RetroFitmentCampaignComponent implements OnInit {

  @Input() rfcForm: FormGroup;
  fileName = 'Choose file';
  isView = false;
  campaignEndDate: Date;
  constructor(
    private rfcPagePresenter: RfcPagePresenter,
    private activatedRoute: ActivatedRoute,
    private service: RetroFitmentCampaignCreatePageWebService
  ) { }

  ngOnInit() {
    this.checkFormOperation();
  }
  private checkFormOperation() {
    this.rfcPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.rfcPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
    }
  }

  fileSelectionChanges(fileInput: Event) {
    const file = fileInput.target['files'][0] as File;
    console.log(file.size)
    const fileExtension = file.name.split('.');
    if (fileExtension[1] == FileExtension.XLSX) {
      this.fileName = file.name;
      this.rfcPagePresenter.collectChassisFiles(file);
    }
  }

  setEndDate(event) {
    this.campaignEndDate = new Date(event.value);
  }
  downloadTemplate() {
    this.service.downloadTemplates("ChassisNo.xlsx").subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        const file = new File([resp.body], "ChassisNo.xlsx", { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }

}
