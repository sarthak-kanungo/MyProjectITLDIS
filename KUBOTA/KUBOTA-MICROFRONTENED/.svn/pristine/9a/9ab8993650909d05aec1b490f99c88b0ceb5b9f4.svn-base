import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { StockAdjustmentPresenter } from '../stock-adjustment-page/stock-adjustment.presenter';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';
import { ItemDetailTableApiService } from 'src/app/spares/counter-sales/customer-order/component/item-details-table/item-detail-table-api.service';
import { MatAutocompleteSelectedEvent, MatDialog  } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { SparesGrnApiService } from 'src/app/spares/spares-purchase/spares-grn/component/spares-grn/spares-grn-api.service';
import { BtbtPageWebService } from 'src/app/spares/inventory-management/bin-to-bin-transfer/component/btbt-page/btbt-page-web.service';
import { ItemNoAuto } from '../../domain/stock-adjustment.domain';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { InventoryAdjustmentService } from '../inventory-adjustment/inventory-adjustment.service';

@Component({
  selector: 'app-stock-adjust-item-details',
  templateUrl: './stock-adjust-item-details.component.html',
  styleUrls: ['./stock-adjust-item-details.component.scss'],
  providers:[ItemDetailsWebService, ItemDetailTableApiService, SparesGrnApiService, BtbtPageWebService, InventoryAdjustmentService]
})
export class StockAdjustItemDetailsComponent implements OnInit {
  @Input() isView: boolean;

  itemDetailsTable: FormGroup;
  stores: string[] = [];
  locations: string[] = [];
  adjustmentTypes: string[] = ["Increase", "Decrease"];
  itemNoList$:Observable<Array<any>>;
  itemsIdArray = [];
  storeList$:Observable<Array<any>>;
  binLocations$:Observable<Array<any>>;
  constructor(
    private presenter: StockAdjustmentPresenter,
    private activateRoute: ActivatedRoute,
    private itemDetailTableApiService: ItemDetailTableApiService,
    private itemDetailWebService: ItemDetailsWebService,
    private toastr : ToastrService,
    private sparesGrnApiService: SparesGrnApiService,
    private btbtPageWebService: BtbtPageWebService,
    private inventoryAdjustmentService: InventoryAdjustmentService,
    public dialog: MatDialog,
  ) { }
  ngOnInit() {
    this.itemDetailsTable = this.presenter.stockAdjustmentForm.get('itemDetailsTable') as FormGroup;
    if(!this.isView){
      this.getStoresName();
      this.addRow();
    }
  }
  addRow(data?) {
    let fg:FormGroup = this.presenter.addRow(data)
    if(fg!=null && !this.isView){
        this.itemNoList$ = null;
        this.binLocations$ = null;
        this.itemNoValueChanges(fg);
        this.storeValueChanges(fg);
        //this.locationValueChanges(fg);
        this.adjustmentQtyValueChange(fg);
        this.adjustmentTypeValueChange(fg);
    }
  }

  
  deleteRow() {
    this.presenter.deleteRow();
    this.itemsIdArray = [];
    let fgs = this.itemDetailsTable.get('itemDetailsRowData') as FormArray; 
    fgs.controls.forEach(fg => {
        if(typeof (fg as FormGroup).controls.itemNo.value === 'object'){
            this.itemsIdArray.push((fg as FormGroup).controls.itemNo.value.id);
        } 
    });
  }

  itemNoValueChanges(fg:FormGroup){
      
      fg.controls.itemNo.valueChanges.subscribe(val => {
          if(typeof fg.controls.itemNo.value !== 'object'){
              this.itemNoList$ = this.itemDetailTableApiService.searchByItemNumber(val, this.itemsIdArray.join())
              fg.get('itemNo').setErrors({ selectFromList: 'Please select from list' });
          }else{
              fg.get('itemNo').setErrors(null);
          }
      });
  }
  
  optionSelectedItem(event: MatAutocompleteSelectedEvent, fg: FormGroup) {
      this.presenter.resetFormByItemNo(fg);
      this.itemDetailTableApiService.getItemDetailsByItemNumber(event.option.value.itemNo, '').subscribe(result => {
        
        if(result){
            fg.controls.itemDescription.patchValue(result[0]['itemDescription'])
            fg.controls.mrp.patchValue(result[0]['unitPrice'])
            fg.controls.itemNo.disable();
            if(this.itemsIdArray.find(id => id===fg.controls.itemNo.value.id)==null){  
                this.itemsIdArray.push(fg.controls.itemNo.value.id);
            }
            fg.controls.store.enable();            
        }else{
            this.toastr.error("Item not available");
        }  
        /*let availableStock:number = 0;  
        result.forEach(element => {
            if(element['availableStock']!=null)
                availableStock = availableStock + element['availableStock'];
        })*/
          
        //fg.controls.currentStock.patchValue(availableStock); 
        /*if(availableStock > 0){
            delete result[0]['itemNo']
            fg.patchValue(result[0]);
            fg.controls.itemNo.disable();
            if(this.itemsIdArray.find(id => id===fg.controls.itemNo.value.id)==null){  
                this.itemsIdArray.push(fg.controls.itemNo.value.id);
            }
        }else{
            this.toastr.error("Stock not available");
            fg.controls.itemNo.reset();
        }   */
      });
  }

  private getStoresName() {
    this.storeList$ = this.sparesGrnApiService.getStoresName('BIN2BIN');
  }

  displayFnItemNo(itemNo: ItemNoAuto) {
      if (typeof itemNo === 'string') {
        return itemNo;
      }
      return itemNo ? itemNo.itemNo : undefined
    }
  displayFnLocation(location){
      if (typeof location === 'string') {
          return location;
        }
        return location ? location.value : undefined
  }
  public storeValueChanges(fg:FormGroup){
      let itemNo:string;
      let storeId:number;

      fg.controls.store.valueChanges.subscribe(val => {

          fg.controls.currentStock.reset();
          fg.controls.binLocation.reset();
          fg.controls.transferQty.reset();
          fg.controls.adjustmentType.reset();
          
          if(val!=null){
              if(fg.controls.itemNo.value && fg.controls.itemNo.value.itemNo){
                  itemNo = fg.controls.itemNo.value.itemNo;
                  storeId= val.id;
                  this.binLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, '', itemNo,'');
                  fg.controls.binLocation.enable();
              }else {
                  this.toastr.warning("Select Item Number");
                  fg.controls.store.reset();
              }
          }
       });
    }
  
  public locationValueChanges(event: MatAutocompleteSelectedEvent, fg: FormGroup){
      console.log('vinayy---',event);
      let itemNo:string;
      let storeId:number;
      let binId:number;
  
      fg.controls.currentStock.reset();
      fg.controls.transferQty.reset();
      fg.controls.adjustmentType.reset();
      
      if(fg.controls.itemNo.value){
          itemNo = fg.controls.itemNo.value.itemNo;
          storeId= fg.controls.store.value.id;
          
          let val = fg.controls.binLocation.value;
              
          if(val && typeof val==='object'){
              fg.controls.transferQty.enable();
              fg.controls.mrp.enable();
              fg.controls.adjustmentType.enable();
              
              binId = val.id
              
              this.inventoryAdjustmentService.getStockValueDetails(itemNo, storeId, binId).subscribe(result => {
                  fg.controls.currentStock.patchValue(result); 
              });
              
          }else if(val){
                this.openConfirmDialog(fg);
          }
      }
  }

  binLocationFormate(fg:FormGroup){
    let val = fg.controls.binLocation.value;
    if(val && typeof val!=='object'){
        fg.controls.currentStock.reset();
        fg.controls.transferQty.reset();
        fg.controls.adjustmentType.reset();
        const pattern = /^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]/;
        let binValue =   this.itemDetailsTable.value.itemDetailsRowData;
        binValue.forEach(element => {
            if (!pattern.test(element.binLocation)) {
                fg.get('binLocation').setErrors({
                    binError:'Location should be in Pattern A00-00-00'
                })
            }
        });
    }
    if (fg.get('binLocation').status=='VALID') {
        this.openConfirmDialog(fg);
      }
    
}
  
  public adjustmentQtyValueChange(fg:FormGroup){
      fg.controls.transferQty.valueChanges.subscribe(val => { 
          if(fg.controls.transferQty.value){
             let adjustmentType = fg.controls.adjustmentType.value;
             let stockValue = fg.controls.currentStock.value?fg.controls.currentStock.value:0;
             let qtyAdjust = val;
             
             if(adjustmentType){
                 if(qtyAdjust && adjustmentType==='Decrease'){
                     if(parseInt(qtyAdjust) > parseInt(stockValue)){
                         this.toastr.error('Qty should not be exceed Current Stock');
                         fg.controls.transferQty.patchValue(stockValue);
                     }
                 }
             }else{
                 this.toastr.error('Select Adjustment Type');
                 fg.controls.transferQty.reset();
             }
          }
      });
  }
  
  public adjustmentTypeValueChange(fg:FormGroup){
      fg.controls.adjustmentType.valueChanges.subscribe(val => {
          fg.controls.transferQty.reset();
      });
      
  }
  
  private openConfirmDialog(fg:FormGroup): void | any {
      let message = `Do you want to create new location?`;
      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result === 'Confirm') {

            fg.controls.transferQty.enable();
            fg.controls.mrp.enable();
            fg.controls.adjustmentType.enable();
            
        }else{
            fg.controls.binLocation.reset();
        }
      });
    }
    private setConfirmationModalData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Confirm', 'Cancel'];
      return confirmationData;
    }
}