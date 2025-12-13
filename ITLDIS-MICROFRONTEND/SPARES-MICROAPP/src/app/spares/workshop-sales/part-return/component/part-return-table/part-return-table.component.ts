import { Component, OnInit, Input } from '@angular/core';
import { EtTableValueChangeEvent, TableConfig, PatchValue } from 'editable-table';
import { SelectList } from '../../../../../core/model/select-list.model';
import { AbstractControlOptions, AbstractControl, FormGroup } from '@angular/forms';
import { PartReturnPagePresenter } from '../part-return-page/part-return-page-presenter';
import { PartReturnTableService } from './part-return-table.service';
import { PartReturnTableApiService } from './part-return-table-api.service';
import { PartReturnItemSubForm } from '../../domain/part-return.domain';
import { ETValidationError } from '../../../../../../../projects/editable-table/src/lib/editable-table-config';

@Component({
  selector: 'app-part-return-table',
  templateUrl: './part-return-table.component.html',
  styles: ['./part-return-table.component.css'],
  providers: [PartReturnTableService, PartReturnTableApiService]
})
export class PartReturnTableComponent implements OnInit {

  //partReturnItemControl: AbstractControl;
  /*etHideActionBtn = true;
  tableConfig: TableConfig[];
  etControlsConfig: {
    [key: string]: any, options?: AbstractControlOptions | {
      [key: string]: any;
    }
  }
  patchValueToEditableTable: PatchValue[];
  assignListToAutocomplete: { list: SelectList[]; config: TableConfig; searchKey: any; };*/
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  //etValidationError: ETValidationError
  
  itemDetailsArray:FormGroup[];
  
  constructor(
    private sparePartReturnItemPresenter: PartReturnPagePresenter,
    private partReturnTableService: PartReturnTableService,
    private partReturnTableApiService: PartReturnTableApiService
  ) {
    // this.sparePartReturnItemPresenter = sparePartReturnItemPresenter;
    //this.tableConfig = this.partReturnTableService.getTableConfigurationJSON();
    //this.etControlsConfig = this.partReturnTableService.getTableFormGroupJSON();
  }

  ngOnInit() {
    //this.partReturnItemControl = this.sparePartReturnItemPresenter.partReturnItemFormArray;
    //console.log('this.partReturnItemControl',this.partReturnItemControl)
      
      this.sparePartReturnItemPresenter.itemDetailsGroup.subscribe(itemGroups => {
         this.itemDetailsArray = itemGroups as FormGroup[]; 
      });
  }
  
  deleteRow(){
      this.sparePartReturnItemPresenter.deleteRow();
  }
  /*editableTableSearchValueChanges(event: EtTableValueChangeEvent<PartReturnItemSubForm>) {
    if (event.config.formControlName === 'returnQuantity') {

      if (this.isView) {
        return;
      }
      if (event.tableRow.issuedQuantity < event.tableRow.returnQuantity) {
        this.etValidationError = new ETValidationError(event.config.formControlName, event.tableRow.tableRowId, 'Return quantity not exceed than issued quantity', { exceedValue: true })
      } else {
        this.etValidationError = new ETValidationError(event.config.formControlName, event.tableRow.tableRowId);
      }
    }
  }*/
}
