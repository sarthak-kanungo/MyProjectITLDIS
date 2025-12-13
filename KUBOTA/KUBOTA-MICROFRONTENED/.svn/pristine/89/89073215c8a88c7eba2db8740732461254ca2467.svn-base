import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup,FormArray } from '@angular/forms';
import { SparesSalesInvoiceCreatePageStore } from './spares-sales-invoice-create-page.store';
import { ItemDetail, LabourDetail, OutsideChargeDetail} from '../../domain/spares-sales-invoice.domain';
import {BehaviorSubject,Observable} from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class SparesSalesInvoiceCreatePresenter {
    private readonly sparesSalesInvoiceCreatePageStore = new SparesSalesInvoiceCreatePageStore(this.fb);
    public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public labourDetailsGroup: BehaviorSubject<FormGroup[]> = new BehaviorSubject<FormGroup[]>(null);
    public outsideChargeDetailsGroup: BehaviorSubject<FormGroup[]> = new BehaviorSubject<FormGroup[]>(null);
    public refernceDocChange: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    public tcsPercChange: BehaviorSubject<number> = new BehaviorSubject<number>(null);
    public refDocSubject:BehaviorSubject<string> = new BehaviorSubject<string>(null);

    constructor(
        private fb: FormBuilder,
        private toasterService: ToastrService
    ) { }

    public get getCreateInvoiceForm(): FormGroup {
        return this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm;
    }
    public get getLabourChargeForm(): FormGroup {
        return this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('labourDetails') as FormGroup;
    }
    public get getOutsideChargeForm(): FormGroup {
        return this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('outsideChargeDetails') as FormGroup;
    }
    
    public patchValueToCreateForm(patchObject: object) {
        patchObject['id'] = null;
        this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('invoiceDetails').patchValue(patchObject);
    }
    public get getItemDetailsFormObservable(): Observable<Array<FormGroup>> {
        return this.itemDetailsGroup.asObservable();
    }
    public addNewRowInItemDetails(dataForPatch?: ItemDetail) {
        
        if (this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('itemDetails').valid) {

            let itemControls = <FormArray>this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('itemDetails');
            
            let newForm = this.sparesSalesInvoiceCreatePageStore.createItemDetailsTableRow(dataForPatch);
            
            itemControls.push(newForm);
           
            this.itemDetailsGroup.next(itemControls.controls as FormGroup[]);
            
            return newForm.controls.uuid.value;
        }
    }
    public addNewRowInLabourDetails(dataForPatch?: LabourDetail) {
        
        //if (this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('labourDetails').valid) {

            let labourControls = <FormArray>this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('labourDetails');
            
            let newForm = this.sparesSalesInvoiceCreatePageStore.createLabourChargesRow(dataForPatch);
            
            labourControls.push(newForm);
           
            this.labourDetailsGroup.next(labourControls.controls as FormGroup[]);
            
            return newForm.controls.uuid.value;
        //}
    }

    public addNewRowInOutsideChargeDetails(dataForPatch?: OutsideChargeDetail) {
        
        //if (this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('outsideChargeDetails').valid) {

            let outsideChargeControls = <FormArray>this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('outsideChargeDetails');
            
            let newForm = this.sparesSalesInvoiceCreatePageStore.createJobCodeRow(dataForPatch);
            
            outsideChargeControls.push(newForm);
           
            this.outsideChargeDetailsGroup.next(outsideChargeControls.controls as FormGroup[]);
            
            return newForm.controls.uuid.value;
        //}
    }


    public resetItemList(){
        let machineDetails = this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('itemDetails') as FormArray;
        let outsideChargeControls = <FormArray>this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('outsideChargeDetails');
        let labourControls = <FormArray>this.sparesSalesInvoiceCreatePageStore.getCreateInvoiceForm.get('labourDetails');
        
        machineDetails.clear();
        outsideChargeControls.clear();
        labourControls.clear();
    }
    public deleteRowFromItemDetail() {
       // debugger;
        let selectedToDelete = [];
        selectedToDelete = this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('itemDetails').value.filter(val => val.isSelected === true);
        if ((this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('itemDetails').value.length - selectedToDelete.length) >= 1) {
            let machineDetails = this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('itemDetails') as FormArray;
            let nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
            machineDetails.clear();
            let tbaseamount:number = 0;
            let tgstamount:number = 0;
            let tinvoiceamount:number = 0;
            nonSelected.forEach(el => {
                machineDetails.push(el)
                tbaseamount = tbaseamount + el.get('netAmount').value;
                tgstamount = tgstamount + el.get('gstAmount').value;
                tinvoiceamount = tbaseamount + tgstamount;
            });
            
            (this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('labourDetails') && this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('labourDetails') as FormArray).controls.forEach( labourDetail => {
                 tbaseamount = tbaseamount + labourDetail.get('netAmount').value;
                 tgstamount = tgstamount + labourDetail.get('gstAmount').value;
                 tinvoiceamount = tbaseamount + tgstamount;
            });
            
            (this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('outsideChargeDetails') && this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('outsideChargeDetails') as FormArray).controls.forEach( outsideChargeDetail => {
                
                tbaseamount = tbaseamount + outsideChargeDetail.get('netAmount').value;
                tgstamount = tgstamount + outsideChargeDetail.get('gstAmount').value;
                tinvoiceamount = tbaseamount + tgstamount;
                
            });
            
            let tcsPerc:number = this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('invoiceDetails').get('tcsPercent').value;
            let tcsAmount:number = 0.0;
            if(tcsPerc!=null){
                tcsAmount = tinvoiceamount * tcsPerc/100;
                tinvoiceamount = tinvoiceamount + tcsAmount;
            }
            this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('invoiceDetails').get('tcsAmount').patchValue(tcsAmount.toFixed(2));
            this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('invoiceDetails').get('totalBaseAmount').patchValue(tbaseamount.toFixed(2));
            this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('invoiceDetails').get('totalTaxAmount').patchValue(tgstamount.toFixed(2));
            this.sparesSalesInvoiceCreatePageStore.sparesInvoiceForm.get('invoiceDetails').get('totalInvoiceAmount').patchValue(tinvoiceamount.toFixed(2));
        }else{
            this.toasterService.warning("All Rows cann't be delete","Warning");
        }
        
    }
}