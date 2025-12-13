import { EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { ItemNumberAuto, ItemDetail } from '../domain/pl.domain';
import { PLPagePresenter } from '../pick-list-page/pick-list-page.presenter';
import { ItemPopUpComponent } from '../item-pop-up/item-pop-up.component';
import { MatDialog } from '@angular/material';
import { CustomValidators, ValidateList } from '../../../../utils/custom-validators';

@Component({
  selector: 'app-pl-item-details',
  templateUrl: './pl-item-details.component.html',
  styleUrls: ['./pl-item-details.component.css']
})
export class PlItemDetailsComponent implements OnInit,OnChanges {

  public itemDetailsArray: Array<FormGroup> = [];
  @Input() public isView: boolean;
  @Input() public isEdit: boolean;
  @Input() itemDetailsTable: Observable<Array<FormGroup>>;
  
  constructor(private fb: FormBuilder,
          private plpresenter: PLPagePresenter,
          public dialog: MatDialog) { }


  ngOnChanges(changes: SimpleChanges): void {
    
  }

  ngOnInit() {
      
       
      this.itemDetailsTable.subscribe((res: Array<FormGroup>) => {
          
          this.itemDetailsArray = res as Array<FormGroup>
          if (res && this.isEdit) {
            this.itemDetailsArray.forEach( fg => {
                fg.get('returnQuantity').setValidators(CustomValidators.max(fg.get('finalIssueQuantity'), "Quantity should not exceed Final Issue Quantity"));
            });  
            /*const newlyAddedFormGroup = this.itemDetailsArray[this.itemDetailsArray.length - 1]
            this.itemNumberValueChanges(newlyAddedFormGroup);
            this.quantityValueChanges(newlyAddedFormGroup);*/
          }
      });
      

      if (!this.isView && !this.isEdit) {
          this.plpresenter.addNewRowInItemDetails();      //Adding one default row while creating the form
      }
  }
  
  public issueFunctionCall(fg:FormGroup, currentRowIndex?: number){
      if(fg!=null && fg.get('itemNumber').value != null){
          this.openItemDetailsPopUp(fg, currentRowIndex);
      }
  }
  
  openItemDetailsPopUp(fg:FormGroup, currentRowIndex?: number): void {
      const dialogRef = this.dialog.open(ItemPopUpComponent, {
        width: '80%',
        maxHeight: '80vh',
        data: {
            itemNumber: fg.get('itemNumber').value,
            itemDescription : fg.get('itemDescription').value,
            quantity : fg.get('orderQuantity').value,
            balanceQuantity : fg.get('balanceQuantity').value,
            availableStock : fg.get('currentStock').value   
        },
        disableClose: true,
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result){
            let uuids:Array<string> = [];
           /* if(result.list.length == 1){
                fg.get('store').patchValue(result.list[0].store);
                fg.get('binLocation').patchValue(result.list[0].binLocation);
                fg.get('storeId').patchValue(result.list[0].storeId);
                fg.get('binId').patchValue(result.list[0].binId);
                fg.get('mrp').patchValue(result.list[0].spmrp);
                fg.get('issueQuantity').patchValue(result.list[0].issueQuantity);
                uuids.push(fg.get('uuid').value);
            } else {*/
                result.list.forEach(obj => {
                   let fg1 = (this.itemDetailsArray as FormGroup[]).find((fg1) => {
                        if (fg1.get('itemNumber').value === fg.get('itemNumber').value && uuids.find(uuid => uuid === fg1.get('uuid').value) === undefined) {
                            
                            fg1.get('store').patchValue(obj.store);
                            fg1.get('binLocation').patchValue(obj.binLocation);
                            fg1.get('storeId').patchValue(obj.storeId);
                            fg1.get('binId').patchValue(obj.binId);
                            fg1.get('mrp').patchValue(obj.unitPrice);
                            fg1.get('issueQuantity').patchValue(obj.issueQuantity);
                            fg1.get('spegst').patchValue(obj.spegst);
                            fg1.get('spmgst').patchValue(obj.spmgst);
                            fg1.get('spmrp').patchValue(obj.spmrp);
                            fg1.get('unitPrice').patchValue(obj.unitPrice);
                            uuids.push(fg1.get('uuid').value);
                          return fg1;
                        }
                   });
                      
                   if(fg1 === undefined || fg1 == null){
                       let item:ItemDetail  = {
                           sparePartDtlId : fg.get('sparePartDtlId').value,
                           itemNumber : fg.get('itemNumber').value,
                           itemDescription : fg.get('itemDescription').value,
                           orderQuantity : fg.get('orderQuantity').value,
                           balanceQuantity : fg.get('balanceQuantity').value,
                           currentStock : fg.get('currentStock').value,
                           unitPrice : obj.unitPrice,
                           issueQuantity : obj.issueQuantity,
                           store : obj.store,
                           binLocation : obj.binLocation,
                           storeId : obj.storeId,
                           binId : obj.binId,
                           mrp : obj.spmrp,
                           spegst : obj.spegst,
                           spmgst : obj.spmgst,
                           finalIssueQuantity : obj.issueQuantity,
                           returnedQuantity : 0, 
                           returnQuantity : 0
                       }
                       this.plpresenter.addNewRowInItemDetails(item, currentRowIndex);
                       uuids.push(fg1.get('uuid').value);
                       currentRowIndex++;
                   }
                });
            //}
            //Delete unused line details
            let count:number = 0;
            (this.itemDetailsArray as FormGroup[]).find((fg1) => {
                if(fg1.get('itemNumber').value === fg.get('itemNumber').value && uuids.find(uuid => uuid === fg1.get('uuid').value) === undefined){
                    fg1.get('isSelected').patchValue(true);
                    count++; 
                }
            });
           if(count>0){
               this.plpresenter.deleteRowFromItemDetail();
           } 
        }else{
            
        }

      });
    }
  
  public deleteRow() {
      this.plpresenter.deleteRowFromItemDetail();
    }
}
