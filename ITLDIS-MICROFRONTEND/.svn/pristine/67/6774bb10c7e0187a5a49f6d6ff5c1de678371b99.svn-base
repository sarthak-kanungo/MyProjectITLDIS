import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { BtBtPagePresenter } from '../btbt-page/btbt-page.presenter';
import { ActivatedRoute } from '@angular/router';
import { ItemNoAuto, ItemDetailRow,ExcelItemDetail } from '../../domain/bin-to-bin-transfer';
import { MatAutocompleteSelectedEvent  } from '@angular/material';
import { Observable } from 'rxjs/Observable';
import { ItemDetailTableApiService } from 'src/app/spares/counter-sales/customer-order/component/item-details-table/item-detail-table-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { SparesGrnApiService } from 'src/app/spares/spares-purchase/spares-grn/component/spares-grn/spares-grn-api.service';
import { BtbtPageWebService } from '../btbt-page/btbt-page-web.service';
import { BaseDto } from 'BaseDto';
import { of } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { ModalFileUploadComponent } from 'src/app/spares/Utility/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from 'src/app/spares/Utility/item-error-report/item-error-report.component';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';
import { MatDialog, MatDialogConfig  } from '@angular/material';

@Component({
  selector: 'app-bin-item-details',
  templateUrl: './bin-item-details.component.html',
  styleUrls: ['./bin-item-details.component.scss'],
  providers: [ItemDetailTableApiService, SparesGrnApiService, BtbtPageWebService, ItemDetailsWebService]
})
export class BinItemDetailsComponent implements OnInit {
  isExcel:boolean=false;
  itemDetailsTable: FormGroup;
  itemNoList$: Observable<Array<ItemNoAuto>>;
  storeList$: Observable<SelectList[]>;
  fromLocations$: Observable<SelectList[]>;
  toLocations$: Observable<SelectList[]>;

  itemsIdArray = [];

  constructor(
    private presenter: BtBtPagePresenter,
    private activateRoute: ActivatedRoute,
    private itemDetailTableApiService: ItemDetailTableApiService,
    private sparesGrnApiService: SparesGrnApiService,
    private btbtPageWebService: BtbtPageWebService,
    private itemDetailsWebService: ItemDetailsWebService,
    private toastr: ToastrService,
    private dialog:MatDialog
  ) { }
  ngOnInit() {
    this.itemDetailsTable = this.presenter.btBtForm.get('itemDetailsTable') as FormGroup;
    this.addRow();
    this.getStoresName();
  }

  displayFnItemNo(itemNo: ItemNoAuto) {
    if (typeof itemNo === 'string') {
      return itemNo;
    }
    return itemNo ? itemNo.itemNo : undefined
  }

  addRow(data?, isExc?:boolean) {
    if(isExc==undefined)isExc=false;
    this.isExcel=isExc;
    
    let fgs = this.itemDetailsTable.get('itemDetailsRowData') as FormArray; 
    if(fgs.length>0 && !this.isExcel){
        let fg = fgs.controls[fgs.length-1] as FormGroup;
        if(fg.valid){
            //fg.disable();
        }else{
           this.toastr.warning("Fill all mandatory field");
           return false;
        }
    }else if(fgs.length>0 && this.isExcel){
        let fg = fgs.controls[fgs.length-1] as FormGroup;
        if(fg.controls.itemNo.value == null){
            fg.controls.isSelected.patchValue(true);
            this.deleteRow();
        }
    }
      
    let fg:FormGroup = this.presenter.addRow(data, this.isExcel);
    this.itemNoList$ = null;
    this.fromLocations$ = null;
    this.toLocations$ = null;
    if(!this.isExcel){
        this.itemNoValueChanges(fg);
    }
    this.storeValueChanges(fg);
    this.getFromBinLocation(fg);
    this.toStoreValueChanges(fg);
    this.getToBinLocation(fg);
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
      this.itemDetailTableApiService.getItemDetailsByItemNumber(event.option.value.itemNo, '').subscribe(result => {
        let availableStock:number = 0;  
        result.forEach(element => {
            if(element['availableStock']!=null)
                availableStock = availableStock + element['availableStock'];
        })        
        if(availableStock > 0){
            delete result[0]['itemNo']
            fg.patchValue(result[0]);
            fg.controls.itemNo.disable();
            if(this.itemsIdArray.find(id => id===fg.controls.itemNo.value.id)==null){  
                this.itemsIdArray.push(fg.controls.itemNo.value.id);
            }
        }else{
            this.toastr.error("Stock not available");
            fg.controls.itemNo.reset();
        }   
      });
      this.presenter.resetFormByItemNo(fg);
  }

  private getStoresName() {
    this.storeList$ = this.sparesGrnApiService.getStoresName('BIN2BIN');
  }

  public storeValueChanges(fg:FormGroup){
      let itemNo:string;
      let storeId:number;
      fg.controls.fromStore.valueChanges.subscribe(val => {
          if(val!=null){
              if(fg.controls.itemNo.value && fg.controls.itemNo.value.itemNo){
                  itemNo = fg.controls.itemNo.value.itemNo;
                  storeId= val.id;
                  this.fromLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, '', itemNo,'');
              }else if(!this.isExcel){
                  this.toastr.warning("Select Item Number");
                  fg.controls.fromStore.reset();
              }
              fg.controls.fromLocation.reset();
              fg.controls.fromBinStock.reset();
              fg.controls.transferQty.reset();
              fg.controls.toStore.reset();
              fg.controls.toLocation.reset();
              fg.controls.toBinStock.reset();
          }
       });
    }
  
  private getFromBinLocation(fg:FormGroup){
      let itemNo:string;
      let storeId:number;
      fg.controls.fromLocation.valueChanges.subscribe(val => {
          
          if(val!=null && typeof val !== 'object'){
              if(fg.controls.itemNo.value && fg.controls.itemNo.value.itemNo){
                  itemNo = fg.controls.itemNo.value.itemNo;
              }else if(!this.isExcel){
                  this.toastr.warning("Select Item Number");
                  fg.controls.fromLocation.reset();
                  return;
              }
              if(fg.controls.fromStore.value && fg.controls.fromStore.value.id){
                  storeId = fg.controls.fromStore.value.id;
              }else if(!this.isExcel){
                  this.toastr.warning("Select From Store");
                  fg.controls.fromLocation.reset();
                  return;
              }
              this.fromLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, val, itemNo,'');
              fg.get('fromLocation').setErrors({ selectFromList: 'Please select from list' });
          }else{
              fg.get('fromLocation').setErrors(null);
          }
          fg.controls.fromBinStock.reset();
          fg.controls.transferQty.reset();
          fg.controls.toStore.reset();
          fg.controls.toLocation.reset();
          fg.controls.toBinStock.reset();
      })
  }
  
  public toStoreValueChanges(fg:FormGroup){
      let itemNo:string;
      let storeId:number;
      let selectedFromBin:number;
      fg.controls.toStore.valueChanges.subscribe(val => {
          
          if(val!=null){
              if(fg.controls.itemNo.value && fg.controls.itemNo.value.itemNo){
                  itemNo = fg.controls.itemNo.value.itemNo;
              }else if(!this.isExcel){
                  this.toastr.warning("Select Item Number");
                  fg.controls.toStore.reset();
                  return;
              }
              if(fg.controls.fromStore.value && fg.controls.fromStore.value.id){
                  
              }else if(!this.isExcel){
                  this.toastr.warning("Select From Store");
                  fg.controls.toStore.reset();
                  return;
              }
              if(fg.controls.fromLocation.value && fg.controls.fromLocation.value.id){
                  selectedFromBin = fg.controls.fromLocation.value.id;
              }else if(!this.isExcel){
                  this.toastr.warning("Select From Bin Location");
                  fg.controls.toStore.reset();
                  return;
              }
              storeId = val.id;
              this.toLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, '', itemNo, selectedFromBin+"");
              
           }
       });
    }
  
  private getToBinLocation(fg:FormGroup){
      let itemNo:string;
      let storeId:number;
      let selectedFromBin:number;
      fg.controls.toLocation.valueChanges.subscribe(val => {
          
          if(val!=null && typeof val !== 'object'){
              if(fg.controls.itemNo.value && fg.controls.itemNo.value.itemNo){
                  itemNo = fg.controls.itemNo.value.itemNo;
              }else if(!this.isExcel){
                  this.toastr.warning("Select Item Number");
                  fg.controls.toLocation.reset();
                  return;
              }
              if(fg.controls.fromStore.value && fg.controls.fromStore.value.id){
                  
              }else if(!this.isExcel){
                  this.toastr.warning("Select From Store");
                  fg.controls.toLocation.reset();
                  return;
              }
              if(fg.controls.fromLocation.value && fg.controls.fromLocation.value.id){
                  selectedFromBin = fg.controls.fromLocation.value.id;
              }else if(!this.isExcel){
                  this.toastr.warning("Select From Bin Location");
                  fg.controls.toLocation.reset();
                  return;
              }
              if(fg.controls.toStore.value && fg.controls.toStore.value.id){
                  storeId = fg.controls.toStore.value.id;
              }else if(!this.isExcel){
                  this.toastr.warning("Select To Store");
                  fg.controls.toLocation.reset();
                  return;
              }
              this.toLocations$ = this.btbtPageWebService.getToBinLocationList(storeId, val, itemNo, selectedFromBin+"");
              //fg.get('toLocation').setErrors({ selectFromList: 'Please select from list' });
          }else{
              //fg.get('toLocation').setErrors(null);
          }
      })   
  }

  compareFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.value === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.value;
    }
    return c1 && c2 ? c1.value === c2.value : c1 === c2;
  }

  displayFnFromLocation(fromLocation: SelectList) {
    if (typeof fromLocation === 'string') {
      return fromLocation;
    }
    return fromLocation ? fromLocation.value : undefined
  }

  optionSelectedFromBin(event: MatAutocompleteSelectedEvent, fg: FormGroup) {
      
    let itemNo:string = fg.controls.itemNo.value.itemNo;
    let storeId:number = fg.controls.fromStore.value.id;
  
    this.btbtPageWebService.getAvlblQtyForStoreBin(itemNo, storeId, event.option.value.id).subscribe((val:BaseDto<any>) => {
        if (val.result == null || val.result.length == 0) {
          this.toastr.warning("No Stock Available")
          fg.controls.fromBinStock.patchValue(0);
        } else {
            fg.controls.fromBinStock.patchValue(val.result[0].CurrentStock);
            fg.controls.transferQty.setValidators(CustomValidators.max(fg.controls.fromBinStock,'Tranfer Quantity should not be more than Stock ' + val.result[0].CurrentStock));
        }             
            
        /*fg.controls.fromBinStock.disable();
        fg.controls.toLocation.enable();*/
    });    
  }

  optionSelectedToBin(event: MatAutocompleteSelectedEvent, fg: FormGroup) {
    let itemNo:string = fg.controls.itemNo.value.itemNo;
    let storeId:number = fg.controls.toStore.value.id;
    this.btbtPageWebService.getAvlblQtyForStoreBin(itemNo, storeId, event.option.value.id).subscribe((val:BaseDto<any>) => {
     
        if (val.result == null || val.result.length == 0) {
          fg.controls.toBinStock.patchValue(0);
        } else {
          fg.controls.toBinStock.patchValue(val.result[0].CurrentStock);
        }             
        
    });    
  }
  

  checkBinLocationFormate(fg:FormGroup){
        const pattern = /^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]/;
        let binValue =   this.itemDetailsTable.value.itemDetailsRowData;
        binValue.forEach(element => {
            if (!pattern.test(element.toLocation)) {
                fg.get('toLocation').setErrors({
                    binError:'Location should be in Pattern A00-00-00'
                })
            }
        });
        
  }
  
  public downloadTemplate() {
      this.itemDetailsWebService.downloadTemplateForPoItemDetail("BIN2BIN.xlsx").subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {           
              const file = new File([resp.body], "BIN2BIN.xlsx", { type: 'application/vnd.ms-excel' });
              saveAs(file);
            }
      })
    }

    public uploadExcel() {
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.id = "modal-component";
      dialogConfig.width = "500px";
      const modalDialog = this.dialog.open(ModalFileUploadComponent, dialogConfig);
      modalDialog.afterClosed().subscribe(result => {
           if(result.event === 'upload'){
               let file:File = result.data;
             
                 const itemDetailsIdArray = [];
                 const errorItemDetailsArray:Array<ExcelItemDetail> = [];
                 
                 let fa = this.itemDetailsTable.get('itemDetailsRowData') as FormArray
                 fa.controls.forEach((element: FormGroup) => {
                   if (element.get('itemNo').value && typeof element.get('itemNo').value === 'object') {
                     itemDetailsIdArray.push(element.get('itemNo').value.id);
                   }
                 });
                 
                 let uploadableFile = { file: file, existingItems : itemDetailsIdArray.join()}
                 this.btbtPageWebService.uploadExcelItemDetail(uploadableFile).subscribe(response => {
                   let items:Array<ExcelItemDetail> = response['result'];
                  
                   if(items!=null && items.length > 0){
                      items.forEach(data => {
                          if (data.availableStock ===0) {
                            this.toastr.error('Stock is not Available')
                            return
                          }
                          else{
                            if(data.isValidItem === 'Y'){
                                //data.itemNo = {'itemNo':data.itemNo};
                                //data.sparePartMasterId = data.id;
                                //data.id = null;
                                let itemDetailRow = {
                                    fromBinStock : data.fromStock,
                                    fromLocation : {id:data.fromLocationId, value:data.fromLocation},
                                    fromStore : {id:data.fromStoreId, value:data.fromStore},
                                    itemNo : {id:data.id, itemNo:data.itemNo},
                                    itemDescription : data.itemDescription,
                                    toBinStock : data.toStock,
                                    toLocation : {id:data.toLocationId, value:data.toLocation},
                                    toStore : {id:data.toStoreId, value:data.toStore},
                                    transferQty : data.transferQuantity,
                                    availableStock : data.availableStock
                                }
                                console.log('itemDetailRow : '+itemDetailRow)
                                this.addRow(itemDetailRow,true);  
                                this.toastr.success(response['message']);        
                             }else{
                                 errorItemDetailsArray.push(data);
                             }
                          }
                       
                      })
                    }
                    // this.toastr.success(response['message']);
                    this.isExcel = false;
                    if(errorItemDetailsArray.length>0){
                        
                        const dialogConfig1 = new MatDialogConfig();
                        dialogConfig1.disableClose = true;
                        dialogConfig1.id = "item-error-component";
                        dialogConfig1.width = "500px";
                        dialogConfig1.height = "350px";
                        dialogConfig1.data = errorItemDetailsArray;
                        const modalDialog = this.dialog.open(ItemErrorReportComponent, dialogConfig1);
                        
                    }
                 })
               }
           });
     }
}