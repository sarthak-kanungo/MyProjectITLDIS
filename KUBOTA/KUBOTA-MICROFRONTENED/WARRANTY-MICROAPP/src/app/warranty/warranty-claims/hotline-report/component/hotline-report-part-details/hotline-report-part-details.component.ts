import { Component, OnInit, Input } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { HotlineReportPagePresenter } from '../hotline-report-create-page/hotline-report-create-page.presenter';
import { AutoCompleteResult, SearchAutocomplete } from '../../domain/hotline-report.domain';
import { hotlineReport } from '../hotline-report/hotline-service';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
// import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-hotline-report-part-details',
  templateUrl: './hotline-report-part-details.component.html',
  styleUrls: ['./hotline-report-part-details.component.scss'],
  providers: [hotlineReport]
})
export class HotlineReportPartDetailsComponent implements OnInit {

  @Input() partDetailForm: FormArray;
  partNumberList: any
  timeout: any
  isView: any
  isEdit:any
  partDetailsHeading: Array<string> = ['Select', 'Sr No', 'Part Number', 'Part Description', 'Quantity', 'Claimable to Vendor'];
  autoCompleteData: AutoCompleteResult;
  obs: Subscription;
  constructor(
    private hotlineReportPagePresenter: HotlineReportPagePresenter,
    private service: hotlineReport,
    private activatedRoute: ActivatedRoute,
    private toaster: ToastrService
  ) { }

  ngOnInit() {
    this.checkFormOperation()
  }
  private checkFormOperation() {
    this.hotlineReportPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.hotlineReportPagePresenter.operation == Operation.VIEW) {
      this.isView = true;

    }
    else if (this.hotlineReportPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;

    } 
     else if (this.hotlineReportPagePresenter.operation == Operation.CREATE) {
      this.hotlineReportPagePresenter.addRowInPartDetail();
    }
  }
  private formValuesChanges() {

    this.partDetailForm.controls.forEach(elt => {
      elt.get('sparePartMaster').valueChanges.subscribe(val => {
        if (val) {
          this.autoCompletePartNumber(val);
        }
      })

    })
  }
  autoCompletePartNumber(txt: string) {

    if (txt && typeof txt == 'string') {
      this.obs = this.service.autoCompletePartNumber(txt).subscribe(res => {
        this.autoCompleteData = res;
      })

    }
  }

  getPartDetailsByPartNumber(event, list: FormGroup, index) {
    if (event && event['option']['value']) {
      if (!this.checkduplicate(index, event['option']['value']['partNumber'])) {
        this.service.getPartDetailsByPartNumber(event.option.value.value).subscribe(res => {
          list.get('partDescription').patchValue(res.itemDescription)
        });
      } else {
        this.toaster.error("Part No can not be duplicate");
        this.partDetailForm.controls[index].reset();
      }
    }

  }
  checkduplicate(index: number, partNumber: string) {

    if (index > 0) {
      for (let i: number = 0; i < index; i++) {
        console.log(this.partDetailForm.controls[i].get('sparePartMaster').value.value, 'dddasfas***********')
        if (this.partDetailForm.controls[i].get('sparePartMaster').value.value === partNumber) {
          return true;
        }
      }
    }
    return false;
  }

  callPartNumber(event, index) {

    this.formValuesChanges()

  }


  addRow() {

    this.hotlineReportPagePresenter.addRowInPartDetail();
  }
  deleteRow() {
    this.hotlineReportPagePresenter.deleteRow(this.partDetailForm);
  }

  displayCodeFn(partNumber: SearchAutocomplete) {
    return partNumber ? partNumber.value : undefined
  }
  ngOnDestroy() {
    // console.log("Component Destroyed " + this.id);
    // this.obs.unsubscribe();
  }

}
