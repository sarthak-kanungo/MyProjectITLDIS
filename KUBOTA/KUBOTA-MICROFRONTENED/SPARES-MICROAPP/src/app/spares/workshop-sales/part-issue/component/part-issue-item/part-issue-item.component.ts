import { Component, OnInit, Input } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PartIssueApiService } from '../part-issue/part-issue-api.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { PartIssuePagePresenter } from '../part-issue-page/part-issue-page-presenter';
import { Observable } from 'rxjs';
import { ItemPopUpComponent } from '../item-pop-up/item-pop-up.component';
import * as uuid from 'uuid';
import { PartIssueItemSubForm,DropDownCategory } from '../../domain/part-issue.domain';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-part-issue-item',
  templateUrl: './part-issue-item.component.html',
  styleUrls: ['./part-issue-item.component.css'],
  providers:[PartIssueApiService]
})
export class PartIssueItemComponent implements OnInit {

    public itemDetailsArray: Array<FormGroup> = [];
    @Input() public isView: boolean;
    @Input() public isEdit: boolean;
    serviceCategoryList:Array<DropDownCategory> = [];
  constructor(public dialog: MatDialog,
          private fb: FormBuilder,
          private partIssuePagePresenter: PartIssuePagePresenter,
          private partIssueApiService: PartIssueApiService,
          private toastr: ToastrService) { }

  ngOnInit() {
    this.partIssueApiService.serviceCategory().subscribe(res => {
        this.serviceCategoryList = res;
        this.partIssuePagePresenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
            this.itemDetailsArray = res;
            if(this.itemDetailsArray){
                this.itemDetailsArray.forEach(fg => {
                    if(this.isView){
                        fg.controls.remark.disable();
                    }
                    fg.controls.category.patchValue(this.serviceCategoryList.filter(f=>f.id==fg.controls.category.value.id)[0]);
                })
            }
        });
    });
  }
  serviceCategoryDropDown(res1: Array<FormGroup>) {
      this.partIssueApiService.serviceCategory().subscribe(res => {
          this.serviceCategoryList = res;

          this.itemDetailsArray = res1;
          if(this.itemDetailsArray){
              this.itemDetailsArray.forEach(fg => {
                  if(this.isView){
                      fg.controls.remark.disable();
                  }
                  fg.controls.category.patchValue(this.serviceCategoryList.filter(f=>f.id==fg.controls.category.value.id)[0]);
              })
          }
      })
  }
  public issueFunctionCall(fg:FormGroup, currentRowIndex?: number){
      if(fg!=null && fg.get('itemNo').value != null){
          this.openItemDetailsPopUp(fg, currentRowIndex);
      }
  }
  
  openItemDetailsPopUp(fg:FormGroup, currentRowIndex?: number): void {
      let issuedQty:number = 0;
      (this.itemDetailsArray as FormGroup[]).find((fg1) => {
          if (fg1.get('itemNo').value === fg.get('itemNo').value
                  && fg1.get('category').value.id !== fg.get('category').value.id) {
              if(fg1.get('issuedQuantity').value){
                  issuedQty = issuedQty +  parseInt(fg1.get('issuedQuantity').value);
              }
          }   
      });
      if(issuedQty>0){
          this.toastr.error('Same Part under different category is already being issue. Please Delete Part');
          
      }else{
          const dialogRef = this.dialog.open(ItemPopUpComponent, {
              width: '80%',
              maxHeight: '80vh',
              data: {
                  itemNumber: fg.get('itemNo').value,
                  itemDescription : fg.get('itemDescription').value,
                  quantity : fg.get('reqQuantity').value,
                  balanceQuantity : fg.get('balancedQuantity').value,
                  availableStock : fg.get('availableStock').value,
                  category : fg.get('category').value.category,
                  advancedSparePartIssue : (this.partIssuePagePresenter.partIssueForm.get('advancedSparePartIssue')==null?null:this.partIssuePagePresenter.partIssueForm.get('advancedSparePartIssue').value)
              },
              disableClose: true,
            });
            dialogRef.afterClosed().subscribe(result => {
              if(result){
                  let uuids:Array<string> = [];
                      result.list.forEach(obj => {
                         let fg1 = (this.itemDetailsArray as FormGroup[]).find((fg1) => {
                              if (fg1.get('itemNo').value === fg.get('itemNo').value
                                      && fg1.get('category').value.id === fg.get('category').value.id
                                      && uuids.find(uuid => uuid === fg1.get('uuid').value) === undefined) {
                                  
                                  fg1.get('storeMaster').patchValue({id:obj.storeId});
                                  fg1.get('binLocationMaster').patchValue({id:obj.binId});
                                  fg1.get('store').patchValue(obj.store);
                                  fg1.get('binLocation').patchValue(obj.binLocation);
                                  fg1.get('mrp').patchValue(obj.spmrp);
                                  fg1.get('unitPrice').patchValue(obj.unitPrice);
                                  fg1.get('spegst').patchValue(obj.spegst);
                                  fg1.get('spmgst').patchValue(obj.spmgst);
                                  fg1.get('issuedQuantity').patchValue(obj.issueQuantity);
                                  uuids.push(fg1.get('uuid').value);
                                return fg1;
                              }
                         });
                         
                         if(fg1 === undefined || fg1 == null){
                             let item:PartIssueItemSubForm  = {
                                 uom : fg.get('uom').value,    
                                 itemNo : fg.get('itemNo').value,
                                 itemDescription : fg.get('itemDescription').value,
                                 sparePartId: fg.get('sparePartMaster').value.id,
                                 balancedQuantity : fg.get('balancedQuantity').value,
                                 availableStock : fg.get('availableStock').value,
                                 reqQuantity : fg.get('reqQuantity').value,
                                 issuedQuantity : obj.issueQuantity,
                                 storeId : obj.storeId,
                                 binId : obj.binId,
                                 store : obj.store,
                                 binLocation : obj.binLocation,
                                 mrp : obj.spmrp,
                                 unitPrice : obj.unitPrice,
                                 spegst: obj.spegst,
                                 spmgst: obj.spmgst,
                                 category:fg.get('category').value.category,
                                 category_id:fg.get('category').value.id
                             }
                             this.partIssuePagePresenter.addRow(item,currentRowIndex);
                             uuids.push(fg1.get('uuid').value);
                             currentRowIndex++;
                         }
                      });
                  //}
                  //Delete unused line details
                  let count:number = 0;
                  (this.itemDetailsArray as FormGroup[]).find((fg1) => {
                      if(fg1.get('itemNo').value === fg.get('itemNo').value 
                              && fg1.get('category').value.id === fg.get('category').value.id
                              && uuids.find(uuid => uuid === fg1.get('uuid').value) === undefined){
                          fg1.get('isSelected').patchValue(true);
                          count++; 
                      }
                  });
                 if(count>0){
                     this.partIssuePagePresenter.deleteRow();
                 } 
              }else{
                  
              }

            });
      }      
  
    }
  
  deleteRow(){
      this.partIssuePagePresenter.deleteRow();
  }
  
}
