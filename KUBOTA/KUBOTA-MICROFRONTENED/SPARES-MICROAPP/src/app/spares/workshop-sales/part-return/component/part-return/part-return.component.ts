import { Component, OnInit, Input } from '@angular/core';
import { MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable } from 'rxjs/Observable';
import { PartReturnApiService } from './part-return-api.service';
import { PartReturnPagePresenter } from '../part-return-page/part-return-page-presenter';
import { PartReturnItemSubForm } from '../../domain/part-return.domain';
import { PartReturnService } from './part-return.service';
import { PartIssueApiService } from '../../../part-issue/component/part-issue/part-issue-api.service'
@Component({
  selector: 'app-part-return',
  templateUrl: './part-return.component.html',
  styles: [`
  button:disabled {
    cursor: not-allowed;
    pointer-events: all !important;
  }
  `],
  providers: [PartReturnApiService, PartReturnService, PartIssueApiService]
})
export class PartReturnComponent implements OnInit {
  @Input() isEdit: boolean;
  @Input() isView: boolean;
  reasonForReturn$: Observable<SelectList[]>;
  @Input() partReturnForm: FormGroup;
  sparePartRequisitionList$: Observable<SelectList[]>;
  serviceJobCardList$: Observable<SelectList[]>;
  issueTypeList$: Observable<SelectList[]>;
  requests: any[] = [' Customer Paid', 'Warranty', 'FOC']

  constructor(
    private partReturnApiService: PartReturnApiService,
    private partReturnPagePresenter: PartReturnPagePresenter,
    private partReturnService: PartReturnService,
    private  partIssueApiService:PartIssueApiService
  ) { }

  ngOnInit() {
    this.getReasonsForReturn();
    this.sparePartRequisitionValueChanges();
    this.serviceJobCardValueChanges();
    this.getIssueType();
    this.partReturnForm.get('requisitionType').valueChanges.subscribe(reqType => {
        if(!this.isView){
           if(reqType==='APR'){
               this.partReturnForm.get('sparePartRequisition').enable();
               this.partReturnForm.get('serviceJobCard').disable();
           } else {
               this.partReturnForm.get('serviceJobCard').enable();
               this.partReturnForm.get('sparePartRequisition').disable();
           }
           this.partReturnForm.get('sparePartRequisition').reset();
           this.partReturnForm.get('serviceJobCard').reset();
        }
    });
  }
  private getIssueType() {
      this.issueTypeList$ = this.partIssueApiService.getIssueType();
  }
  private getReasonsForReturn() {
    this.reasonForReturn$ = this.partReturnApiService.getReasonsForReturn();

  }

  private sparePartRequisitionValueChanges() {
    if (this.partReturnForm) {
      this.partReturnForm.get('sparePartRequisition').valueChanges.subscribe((aprSearchVal: string | SelectList) => {

        if (typeof aprSearchVal === 'string') {
          this.sparePartRequisitionList$ = this.partReturnApiService.searchByRequisitionNoMobileNoEmpName(aprSearchVal);
          
        }
      })
    }
  }
  sparePartRequisitionOptionSelected(event: MatAutocompleteSelectedEvent) {
    if (event.option.value) {
      this.partReturnPagePresenter.partReturnItemFormArray.clear();  
      this.partReturnPagePresenter.itemDetailsGroup.next(null);  
      this.getPartIssueDetailsForPartReturnByRequisitionId(event.option.value.id);
      //this.partReturnForm.controls.sparePartRequisition.disable();
    }
  }
  getPartIssueDetailsForPartReturnByRequisitionId(requisitionId: number) {
      this.partReturnApiService.getPartIssueDetailsForPartReturnByRequisitionId(`${requisitionId}`).subscribe(partIssueDetailRes => {

          this.partReturnForm.patchValue(partIssueDetailRes.headerData);
          
          if (partIssueDetailRes.lineItems && partIssueDetailRes.lineItems.length>0) {
            this.partReturnForm.get('partsReceivedBy').patchValue(partIssueDetailRes.lineItems[0]['partsReceivedBy']);
            this.partReturnForm.get('partReceivedById').patchValue(partIssueDetailRes.lineItems[0]['partReceivedById']);
            const partReturnTableData = this.partReturnService.convertLineItemIntoPartReturnItemSubForm(partIssueDetailRes.lineItems)
            partReturnTableData.forEach(lineItem => {
                this.partReturnPagePresenter.addRow(lineItem);
            });
          }
          
        });
  }
  serviceJobCardOptionSelected(event: MatAutocompleteSelectedEvent) {
    if (event.option.value && event.option.value.requisitionId) {
      this.partReturnPagePresenter.partReturnItemFormArray.clear();  
      this.partReturnPagePresenter.itemDetailsGroup.next(null);  
      this.getPartIssueDetailsForPartReturnByRequisitionId(event.option.value.requisitionId);
      this.partReturnForm.get('sparePartRequisition').patchValue({
        id: event.option.value.requisitionId
      });
    }
  }
  private serviceJobCardValueChanges() {
    if (this.partReturnForm) {
      this.partReturnForm.get('serviceJobCard').valueChanges.subscribe((aprSearchVal: string | SelectList) => {
        if (typeof aprSearchVal === 'string') {
          this.serviceJobCardList$ = this.partReturnApiService.searchByJobCardNoForPartReturn(aprSearchVal);
          console.log("serviceJobCardList$--->", this.serviceJobCardList$);
        }
      })
    }
  }
  displayFn(obj: SelectList): string | number | undefined {
    if (obj && typeof obj === 'string') {
      return obj;
    }
    return obj ? obj.value : undefined;
  }
  displayCodeFn(obj: SelectList): string | number | undefined {
    if (obj && typeof obj === 'string') {
      return obj;
    }
    return obj ? obj.code : undefined;
  }

}
