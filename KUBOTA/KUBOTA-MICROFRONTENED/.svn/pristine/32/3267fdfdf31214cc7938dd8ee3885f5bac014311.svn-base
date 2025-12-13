import { Component, OnInit,ChangeDetectionStrategy } from '@angular/core';
import { AbstractControl, FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { DateService } from 'src/app/root-service/date.service';
import { SavePickList, SavePickItemDetails } from '../domain/pl.domain';
import { PLPagePresenter } from './pick-list-page.presenter';
import { PickListPageStore } from './pickList-page.store';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { PickListServiceService } from '../component/pick-list-service.service';
import { MatDialog } from '@angular/material';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../confirmation-box/confirmation-box.component';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-pick-list-page',
  templateUrl: './pick-list-page.component.html',
  styleUrls: ['./pick-list-page.component.css'],
  providers:[PLPagePresenter, PickListPageStore, PickListServiceService]
})

export class PickListPageComponent implements OnInit {
  plForm: FormGroup
  pickListForm: FormGroup
  isView: boolean;
  isEdit: boolean;
  viewId: number;
  editId:number;
  itemDetailsTable: Observable<Array<FormGroup>>;
  isSubmitDisable:boolean = false;
  constructor(private plpresenter: PLPagePresenter,
    private activateRoute: ActivatedRoute,
    private router: Router,
    private toasterService: ToastrService,
    private dateService: DateService,
    private pickListService : PickListServiceService,
    private location:Location,
    public dialog: MatDialog) { 
      
      this.plForm = this.plpresenter.plForm
      this.pickListForm = this.plForm.get('pickListForm') as FormGroup;
      this.itemDetailsTable = this.plpresenter.getItemDetailsFormObservable;
      
  }

  ngOnInit() {
    this.activateRoute.queryParamMap.subscribe(paramsMap => {
        if (paramsMap && Object.keys(paramsMap['params']).length > 0) {
          if (paramsMap && paramsMap.get('viewId')) {
            this.isView = true;
            this.viewId = parseInt(atob(paramsMap.get('viewId')));
            this.getPickDetailView(this.viewId);
            return;
          }else if (paramsMap && paramsMap.get('editId')) {
              this.isEdit = true;
              this.editId = parseInt(atob(paramsMap.get('editId')));
              this.getPickDetailView(this.editId);
              return;
          }else if (paramsMap && paramsMap.get('custId')) {
            let custNo = atob(paramsMap.get('custId'));
            this.pickListService.getCustomerOrderNo(custNo).subscribe(res=>{
              this.pickListForm.controls.saleOrderNumber.patchValue(res[0]);
              this.plpresenter.saleOrderNumberSubject.next(res[0]['id']+"");
            });
            return;
          }
        }
     });
  }
  
  backBtn(){
    this.location.back();
  }
  buildJsonForPickList() {
    const pickListRowValue = this.plForm.getRawValue()
    
    let saveAndSubmitPickList = {} as SavePickList;
    saveAndSubmitPickList.spareSalesOrderId = pickListRowValue.pickListForm.saleOrderNumber.id;
    saveAndSubmitPickList.picklistHdrId = pickListRowValue.pickListForm.id;    
    let itemDetails:Array<SavePickItemDetails> = [];
    
    pickListRowValue.itemDetails.forEach(element => {
        itemDetails.push({
        picklistDtlId: element.id,    
        pickedItemNo: element.itemNumber,
        spareSalesOrderPartId: element.sparePartDtlId,
        issueQty: element.issueQuantity,
        binLocationId : element.binId,
        storeId : element.storeId,
        unitPrice: element.unitPrice,
        finalIssueQty : (element.issueQuantity-(element.returnedQuantity==null?0:element.returnedQuantity)-(element.returnQuantity==null?0:element.returnQuantity)),
        totalReturnQty : ((element.returnedQuantity==null?0:element.returnedQuantity)+(element.returnQuantity==null?0:element.returnQuantity)),
        returnQty : (element.returnQuantity==null?0:element.returnQuantity),
        spegst : element.spegst,
        spmgst : element.spmgst,
        spmrp : element.mrp
      })
    });
    saveAndSubmitPickList.itemDetails = itemDetails;
    return saveAndSubmitPickList;
  }

  public getPickDetailView(pickId:number){
      this.pickListService.viewPickList(pickId).subscribe(res => {
         if(res){
             const pickHeaders = res['result']['headerResponse'];
             const pickDetails = res['result']['partDetails'];
             this.pickListForm.patchValue(pickHeaders);
             this.pickListForm.controls.saleOrderNumber.patchValue({saleOrderNumber:pickHeaders.saleOrderNumber,id:pickHeaders.salesOrderId})
             pickDetails.forEach(item => {
                 this.plpresenter.addNewRowInItemDetails(item);
             })
         } 
      });
  }
  public validateForm() {
      this.plpresenter.pickListForm.markAllAsTouched();
      if (this.plpresenter.plForm.valid) {
        let fieldToDelete:any[]=[];
        const pickListRowValue = this.plForm.getRawValue();
        let valid:boolean = true; 
        let count:number = 0;
        (this.plForm.controls.itemDetails as FormArray).controls.forEach((element:FormGroup)=> {
            if(element.get('issueQuantity').value == null || element.get('issueQuantity').value==0){
                valid = false;
                fieldToDelete.push(element);
            }else if(element.get('issueQuantity').value>0){
              count++;
            }
        }); 
        if(count>0){   
            this.openConfirmationModal(fieldToDelete);
        }else{
            this.toasterService.error("Issue Qty is required for atleast one item", "Mandatory Field");
        }
      }else{
        this.toasterService.error('Please fill mandatory fields', "Mandatory Field")
      }
  }
    private savePicklist(finalData: SavePickList) {
      this.pickListService.savePicklist(finalData).subscribe(res => {
        if (res) {
          this.toasterService.success(res['message'], 'Success');
          this.router.navigate(['../'], { relativeTo: this.activateRoute })
        }else {
          this.isSubmitDisable = false;
          this.toasterService.error('Error generated while saving', 'Transaction failed');
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toasterService.error('Error generated while saving', 'Transaction failed');
      })
    }
    private openConfirmationModal(fieldToDelete) {
      let message = `Are you sure you want to submit?`;
      if(fieldToDelete && fieldToDelete.length>0){
        message = 'Do you want to procced with partial picklist?';
      }
      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result === 'Confirm') {
          if(fieldToDelete && fieldToDelete.length>0){
            
            fieldToDelete.forEach(fg => {
              ((this.plForm.controls.itemDetails as FormArray).controls.filter(fg1 => fg1==fg)[0] as FormGroup).controls.isSelected.patchValue(true);
            });
            this.plpresenter.deleteRowFromItemDetail();
          }
          const finalObject:SavePickList = this.buildJsonForPickList();
          this.isSubmitDisable = true;
          this.savePicklist(finalObject);
        }
      })
    }
    private setConfirmationModalData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Cancel', 'Confirm'];
      return confirmationData;
    }

    viewPrint(printStatus:string){
      this.pickListService.printPicklList(this.pickListForm.get('picklistNumber').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }

  exit() {
    this.router.navigate([(this.isEdit || this.isView) ? '../' : '../'], { relativeTo: this.activateRoute });
  }
  
}
