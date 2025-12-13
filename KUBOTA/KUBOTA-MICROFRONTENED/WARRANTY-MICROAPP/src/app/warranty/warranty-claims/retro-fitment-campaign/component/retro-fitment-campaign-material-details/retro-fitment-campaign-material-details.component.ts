import { Component, OnInit, Input } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { RfcPagePresenter } from '../retro-fitment-campaign-create-page/retro-fitment-campaign-create-page.presenter';
import { RetroFitmentCampaignMaterialDetailsWebService } from './retro-fitment-campaign-material-details-web.service'
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { AutoCompleteResult } from '../../domain/retro-fitment-campaign.domain';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-retro-fitment-campaign-material-details',
  templateUrl: './retro-fitment-campaign-material-details.component.html',
  styleUrls: ['./retro-fitment-campaign-material-details.component.scss'],
  providers: [RetroFitmentCampaignMaterialDetailsWebService]
})
export class RetroFitmentCampaignMaterialDetailsComponent implements OnInit {

  @Input() materialDetailsForm: FormArray;
  materialDetailHeading: Array<string> = [];
  autoCompleteData: AutoCompleteResult;
  isView = false;

  constructor(
    private rfcPagePresenter: RfcPagePresenter,
    private retroFitmentCampaignMaterialDetailsWebService: RetroFitmentCampaignMaterialDetailsWebService,
    private activatedRoute: ActivatedRoute,
    private toaster: ToastrService
  ) { }

  ngOnInit() {
    this.materialDetailHeading = ['SL.NO.', 'ITEM NO', 'ITEM DESC', 'QUANTITY'];
    this.checkFormOperation();
    this.formValueChanges();
  }

  private checkFormOperation() {
    this.rfcPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.rfcPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
    } else if (this.rfcPagePresenter.operation == Operation.CREATE) {
      this.rfcPagePresenter.addRowInMaterialDetails();
    }
  }

  private formValueChanges() {
    this.materialDetailsForm.controls.forEach(elt => {
      elt.get('sparePartMaster').valueChanges.subscribe(val => {
        if (val) {
          this.autoCompletePartNumber(val);
        }
      })
    })
  }

  private autoCompletePartNumber(txt: string) {
    this.retroFitmentCampaignMaterialDetailsWebService.autoCompletePartNumber(txt).subscribe(res => {
      this.autoCompleteData = res;
    })
  }
  getPartDetailsByPartNumber(event, list: FormGroup, index) {
    if (event && event['option']['value']) {
      if (!this.checkduplicate(index, event['option']['value']['partNumber'])) {
        this.retroFitmentCampaignMaterialDetailsWebService.getPartDetailsByPartNumber(event.option.value.value).subscribe(res => {
          list.get('description').patchValue(res.itemDescription)
        });
      } else {
        this.toaster.error("Item No can not be duplicate");
        this.materialDetailsForm.controls[index].reset();
      }
    }
  }
  checkduplicate(index: number, itemNo: string) {
    // console.log(itemNo, 'number')
    if (index > 0) {
      for (let i: number = 0; i < index; i++) {
        if (this.materialDetailsForm.controls[i].get('sparePartMaster').value.value === itemNo) {
          return true;
        }
      }
    }
    return false;
  }
  itemNoApi(event, i) {
    this.formValueChanges()
  }
  addRow() {
    this.rfcPagePresenter.addRowInMaterialDetails();
  }
  deleteRow() {
    this.rfcPagePresenter.deleteRowInMaterialDetails();
  }

  displayCodeFn(obj: AutoCompleteResult): string | number | undefined {
    return obj ? obj.partNumber : undefined;
  }
}

