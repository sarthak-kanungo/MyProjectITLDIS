import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';


@Injectable()
export class OpsStore {
    private readonly orderPlanningForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.orderPlanningForm = this.fb.group({
            formOPS: this.createOrderPlanningForm(),
            itemDetails: this.fb.array([]),
            partDetails:this.fb.array([])
        });
    }



    private createOrderPlanningForm() {
        const formOPS = this.fb.group({
            orderPlanningSheetNo: [{ value: null, disabled: true }],
            orderQtyMonth: [{ value: '', disabled: false }, Validators.required],

            suggestionOrderQty: [{ value: '', disabled: false }, Validators.required],
            orderType: [{ value: '', disabled: false }, Validators.required],
            logic: [{ value: '', disabled: false }, Validators.required],
            Opstatus:[{value:null,disabled:true}],
            date: [{ value: null, disabled: true }],
            totalOrderValue: [{ value: null, disabled: true }]
        });


        return formOPS;
    }
    createItemDetailTableRow(data?) {
        // console.log(data.sparePartMaster.itemNo, 'data,')
        let fg: FormGroup = this.fb.group({
            itemNo: { value: data.itemNo || (data.sparePartMaster ? data.sparePartMaster.itemNo : null), disabled: true },
            itemDesc: { value: data.itemDescription || (data.sparePartMaster ? data.sparePartMaster.itemDescription : null), disabled: true },
            editsparepartId:{value: (data.itemId ? data.itemId : null), disabled: true},
            sparepartId:{value: (data.sparePartMaster ? data.sparePartMaster.id : null), disabled: true},
            reOrderQty:[{value: data ? data.reorderQty : null, disabled: true}],
            dealerStock:[{value:data?data.dealerStock:null,disabled:true}],
            kaiBackOrder:[{value:data?data.kaiBackOrder:null,disabled:true}],
            transitFromKai:[{value:data?data.transitFromKai:null,disabled:true}],
            l12mSales:[{value:data?data.l12mSales:null,disabled:true}],
            suggestedOrderQty:[{value:data?data.suggestedOrderQty:null,disabled:true}],
            id:[{value:data?data.id:null,disabled:true}],
            actualOrderQty:[{value:data?data.actualOrderQty:null,disabled:false}],
            orderValue:[{value:data?data.orderValue:null,disabled:true}],
            gstPercent:[{value:data?data.gstPercent:null,disabled:true}],
            unitPrice:[{value:data?data.unitPrice:null,disabled:true}],
            totalOrderValue:[{value:null,disabled:true}],
            select:[{value:null,disabled:false}]
        })


        return fg;
    }


    buildmaterialDetailsForm() {
        return this.fb.group({
           
            itemNo: { value:  null, disabled: true },
            itemDesc: { value:null, disabled: true },
            editsparepartId:{value:null, disabled: true},
            sparepartId:{value:  null, disabled: true},
            reOrderQty:[{value:null, disabled: true}],
            dealerStock:[{value:null,disabled:true}],
            kaiBackOrder:[{value:null,disabled:true}],
            transitFromKai:[{value:null,disabled:true}],
            l12mSales:[{value:null,disabled:true}],
            suggestedOrderQty:[{valueQty:null,disabled:true}],
            // id:[{valueed:true}],
            actualOrderQty:[{value:null,disabled:false}],
            orderValue:[{value:null,disabled:true}],
            gstPercent:[{value:null,disabled:true}],
            unitPrice:[{value:null,disabled:true}],
            totalOrderValue:[{value:null,disabled:true}],
            
            id:[{value:null,disabled:true}],
        })
    }


    get OPSFormAll() {
        if (this.orderPlanningForm) {
            return this.orderPlanningForm as FormGroup;
        }
    }


    public get partReturnTableControl(): FormArray {
        return this.orderPlanningForm.get('itemDetails') as FormArray;
    }

}