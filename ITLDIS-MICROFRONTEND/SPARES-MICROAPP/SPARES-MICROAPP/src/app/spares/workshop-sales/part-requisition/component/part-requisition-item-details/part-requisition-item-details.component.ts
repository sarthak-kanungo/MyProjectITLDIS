import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, AbstractControl, AbstractControlOptions } from '@angular/forms';
import { PartRequisitionPagePresenter, SparePartRequisitionItemPresenter } from '../part-requisition-page/part-requisition-page.presenter';
import { TableConfig, EtTableValueChangeEvent, PatchValue } from 'editable-table';
import { PartRequisitionItemDetailService } from './part-requisition-item-detail.service';
import { PartRequisitionItemDetailApiService } from './part-requisition-item-detail-api.service';
import { MatAutocompleteSelectedEvent  } from '@angular/material';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-part-requisition-item-details',
  templateUrl: './part-requisition-item-details.component.html',
  styleUrls: ['./part-requisition-item-details.component.scss'],
  providers: [PartRequisitionItemDetailService, PartRequisitionItemDetailApiService]
})
export class PartRequisitionItemDetailsComponent implements OnInit {

  itemDetailTable: FormArray;
  itemNumberList$: Observable<SelectList[]>;
  itemsIdArray = [];
  @Input() isView: boolean;
  @Input() isEdit: boolean;

  constructor(
    private partRequisitionPagePresenter: PartRequisitionPagePresenter,
    private partRequisitionItemDetailService: PartRequisitionItemDetailService,
    private partRequisitionItemDetailApiService: PartRequisitionItemDetailApiService
  ) {
    
  }

  ngOnInit() {
      if(!this.isView && !this.isEdit){
          let fg:FormGroup = this.partRequisitionPagePresenter.addNewRowInItemDetails();
          this.itemValueChange(fg);
          
      }
      this.partRequisitionPagePresenter.itemDetailsGroup.subscribe(items => {
          if(items!=null && this.isEdit && (this.itemDetailTable==undefined || this.itemDetailTable==null)){
              items.controls.forEach(fg => {
                  this.itemsIdArray.push((fg as FormGroup).controls.itemNo.value.id);
              })
          }
          this.itemDetailTable = items;
      })
  }
  createNewRow(){
      let fg = this.partRequisitionPagePresenter.addNewRowInItemDetails();
      this.itemNumberList$ = null;
      this.itemValueChange(fg);
  }
  
  deleteRow(){
      this.partRequisitionPagePresenter.deleteRowItemDetail();
      this.itemsIdArray = []; 
      this.itemDetailTable.controls.forEach(fg => {
          if(typeof (fg as FormGroup).controls.itemNo.value === 'object'){
              this.itemsIdArray.push((fg as FormGroup).controls.itemNo.value.id);
          } 
      });
  }
  
  itemValueChange(fg:FormGroup){
      fg.controls.itemNo.valueChanges.subscribe(itemNo => {
          if(itemNo && typeof itemNo == 'string'){
              this.itemNumberList$ = this.partRequisitionItemDetailApiService.searchSparesPartItemNo(itemNo, this.itemsIdArray.join());
              fg.get('itemNo').setErrors({ selectFromList: 'Please select from list' });
          }else{
              fg.get('itemNo').setErrors(null);
          }
      })
  }
  optionSelectedItem(event: MatAutocompleteSelectedEvent, fg: FormGroup){
      
      this.partRequisitionItemDetailApiService.getSparesPartItemNoDetails(event.option.value.itemNo).subscribe(partItemNoDetailRes => {
          fg.patchValue(partItemNoDetailRes);
          if(this.itemsIdArray.find(id => id===fg.controls.itemNo.value.id)==null){  
              this.itemsIdArray.push(fg.controls.itemNo.value.id);
          }
      });
  }
  
  displayFnItemNumber(item:SelectList){
      if (typeof item === 'string') {
          return item;
      }
      return item ? item.itemNo : undefined    
  }
  /*
  editableTableSearchValueChanges(event: EtTableValueChangeEvent<object>) {
    if (event.config.formControlName === 'itemNo') {
      if (this.isView) {
        return;
      }
      // event.tableRow event.tableRow['receiptQuantity'], event.tableRow['invoiceQuantity']
      if (typeof event.tableRow['itemNo'] === 'string') {
        this.searchSparesPartItemNo(event.tableRow['itemNo'], event);
        return;
      }
      
      this.getSparesPartItemNoDetail(event.tableRow['itemNo'].itemNo, event);
        this.patchValueToEditableTable = [{
          rowIndex:event.tableRow['rowIndex'],
          tableRowId: event.tableRow['tableRowId'],
          patchValue: {
            sparePartMaster: {
              id: event.tableRow['itemNo'].id,
              itemNo: event.tableRow['itemNo'].itemNo
            }
          }
        }];
    }
  }
  private searchSparesPartItemNo(itemNo: string, event: EtTableValueChangeEvent<object>) {
    if (!itemNo) {
      return;
    }
    this.partRequisitionItemDetailApiService.searchSparesPartItemNo(itemNo).subscribe(itemNoRes => {
      this.assignListToAutocomplete = {
        list: itemNoRes,
        config: event.config,
        searchKey: event.key
      }
    })
  }
  private getSparesPartItemNoDetail(itemNo, event: EtTableValueChangeEvent<object>) {
    this.partRequisitionItemDetailApiService.getSparesPartItemNoDetails(itemNo).subscribe(partItemNoDetailRes => {
      this.patchValueToEditableTable = [{
        rowIndex:event.tableRow['rowIndex'],
        tableRowId: event.tableRow['tableRowId'],
        patchValue: partItemNoDetailRes
      }];
    })
  }*/
}
