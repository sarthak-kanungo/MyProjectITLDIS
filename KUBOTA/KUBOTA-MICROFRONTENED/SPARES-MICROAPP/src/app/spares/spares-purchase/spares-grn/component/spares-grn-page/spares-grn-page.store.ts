import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import * as uuid from 'uuid';
import { BehaviorSubject, Observable } from 'rxjs';
import { SpareAccpacInvoiceItem } from '../../domain/spare-grn.domain';
import { CustomValidators } from '../../../../../utils/custom-validators';

export class SparesGrnPageStore {

    private _goodsReceiptNoteForm: FormGroup;
    private _itemDetail$ = new BehaviorSubject<FormArray>(undefined);
    private hideFieldsAtItemDetailWhenGrnTypeIsOthers$ = new BehaviorSubject<boolean>(undefined);
    constructor(
        private fb: FormBuilder
    ) { }

    public set hideFieldsAtItemDetailWhenGrnTypeIsOthers(v: boolean) {
        this.hideFieldsAtItemDetailWhenGrnTypeIsOthers$.next(v);
    }
    public get getHideFieldsAtItemDetailWhenGrnTypeIsOthers$(): Observable<boolean> {
        return this.hideFieldsAtItemDetailWhenGrnTypeIsOthers$.asObservable();
    }

    public get grnForm(): FormGroup {
        if (this._goodsReceiptNoteForm) {
            return this._goodsReceiptNoteForm.get('grn') as FormGroup;
        }
        this.createGoodsReceiptNoteForm()
        return this._goodsReceiptNoteForm.get('grn') as FormGroup;
    }

    public get itemDetail$(): Observable<FormArray>|any {
        return this._itemDetail$.asObservable();
    }
    public set itemDetail$(v: any) {
        this._itemDetail$.next(undefined);
    }
    public get getItemDetail(): FormArray {
        return this._itemDetail$.value;
    }

    public get getGoodsReceiptNoteForm(): FormGroup {
        if (this._goodsReceiptNoteForm) {
            return this._goodsReceiptNoteForm;
        }
        return this.createGoodsReceiptNoteForm();
    }

    private createGoodsReceiptNoteForm() {
        if (this._goodsReceiptNoteForm) {
            return this._goodsReceiptNoteForm;
        }
        this._goodsReceiptNoteForm = this.fb.group({
            grn: this.createGrnForm(),
            itemDetail: this.fb.array([]),
            itemDetailTotal: this.createItemDetailTotalForm()
        });
        this.defaultEnableAndDisableFieldsOfGrnForm();
        return this._goodsReceiptNoteForm;
    }
    private createGrnForm() {
        return this.fb.group({
            id: [{ value: null, disabled: true }],
            grnType: [null],
            grnNo: [{value: null, disabled: true}],
            grnDate: [{value: null, disabled: true}],
            grnStatus: [{value: null, disabled: true}],
            supplyType: [null],
            supplierType: [null],
            supplierName: [null],
            sparePurchaseOrder: [null],
            supplierGstNo: [null],
            transportMode: [null],
            supplierAddress1: [null],
            supplierAddress2: [null],
            supplierState: [null],
            transporter: [null],
            invoiceNumber: [null, Validators.compose([Validators.required])],
            invoiceDate: [null],
            invoiceValue: [null],
            grnDoneBy: [null],
            noOfBoxesSent: [null],
            noOfBoxesReceived: [null, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            receiptValue: [null],
            goodsReceiptDate: [null, Validators.compose([Validators.required])],
            store: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
        })
    }
    public defaultEnableAndDisableFieldsOfGrnForm() {
        let grnForm = this.grnForm;
        const listOfEnableFieldFromGrnForm = ['grnType', 'supplierType', 'invoiceNumber', 'noOfBoxesReceived', 'goodsReceiptDate', 'store'];
        listOfEnableFieldFromGrnForm.forEach((formControlName: string) => {
            grnForm.get(formControlName).enable();
        });
        // const listOfDisableFieldFromGrnForm = ['id', 'grnDate', 'grnNo', 'grnStatus', 'supplyType', 'supplierGstNo', 'transportMode', 'supplierAddress1', 'supplierAddress2', 'supplierState', 'transporter', 'invoiceDate', 'invoiceValue', 'grnDoneBy', 'noOfBoxesSent', 'receiptValue', 'sparePurchaseOrder'];
        const listOfDisableFieldFromGrnForm = ['id', 'grnNo', 'grnStatus', 'supplyType', 'supplierGstNo', 'transportMode', 'supplierAddress1', 'supplierAddress2', 'supplierState', 'transporter', 'invoiceDate', 'invoiceValue', 'grnDoneBy', 'noOfBoxesSent', 'receiptValue', 'sparePurchaseOrder'];
        listOfDisableFieldFromGrnForm.forEach((formControlName: string) => {
            grnForm.get(formControlName).disable();
        });
    }
    public defaultEnableAndDisableFieldsOfGrnFormForGrnTypeOthers() {
        const grnForm = this.grnForm;
        const listOfEnableFieldFromGrnForm = ['grnType', 'invoiceNumber', 'noOfBoxesReceived', 'goodsReceiptDate', 'store', 'invoiceDate', 'noOfBoxesSent', 'invoiceValue', 'sparePurchaseOrder'];
        listOfEnableFieldFromGrnForm.forEach((formControlName: string) => {
            if (grnForm.get(formControlName)) {
                grnForm.get(formControlName).enable();
            }
        });
        const listOfDisableFieldFromGrnForm = ['id', 'grnDate', 'grnNo', 'grnStatus', 'supplyType', 'supplierGstNo', 'transportMode', 'supplierAddress1', 'supplierAddress2', 'supplierState', 'transporter', 'grnDoneBy', 'receiptValue'];
        listOfDisableFieldFromGrnForm.forEach((formControlName: string) => {
            if (grnForm.get(formControlName)) {
                grnForm.get(formControlName).disable();
            }
        });
    }
    public insertRowIntoItemDetail(data?, isEdit?) {
        const newFg = this.fb.group(this.getControlsConfigForTableFormArray(data));
        this.defaultEnableAndDisableFieldsOfItemDetail(newFg);
        let itemDetail = (this._goodsReceiptNoteForm.get('itemDetail') as FormArray);
        itemDetail.push(newFg);
        this._itemDetail$.next(itemDetail);
        return newFg
    }
    getControlsConfigForTableFormArray(data: SpareAccpacInvoiceItem = null) {
        return {
            uuid: [{ value: uuid.v4(), disabled: true }],
            id: [{ value: data ? data.id : null, disabled: true }],
            // sparePartGrn: [{ value: (data && data['spareGrnId']) ? { id: data['spareGrnId'] } : null, disabled: true }],
            spmgst: [{ value: data ? data.spmgst : null, disabled: true }],
            spegst: [{ value: data ? data.spegst : null, disabled: true }],
            spmrp: [{ value: data ? data.spmrp : null, disabled: true }],
            sparePartMaster: [{ value: (data && data.sparePartId) ? { id: data.sparePartId, itemNo: data.itemNo } : null, disabled: true }],
            //spareLocalPartMaster: [{ value: (data && data.spareLocalPartId) ? { id: data.spareLocalPartId, dmsItemNumber: data.itemNo } : null, disabled: true }],
            accPacOrderNumber: [data ? data.accpacOrderNo : null, Validators.compose([])],
            dmsPoNumber: [data ? data.dmsPoNo : null],
            sparePurchaseOrder : [{ value: (data && data.sparePurchaseOrderId) ? { id: data.sparePurchaseOrderId} : null, disabled: true }],
            balancedQty: [data ? data.balancedQty : null],
            poQuantity: [(data && data['poQuantity']) ? data['poQuantity'] : null],
            itemNumber: [data ? data.itemNo : null, Validators.compose([Validators.required])],
            itemDescription: [data ? data.itemDescription : null, Validators.compose([Validators.required])],
            binLocation: [(data && data['binLocation']) ? { id: data['binId'], value: data['binLocation'] } : null],
            hsnCode: [data ? data.hsnCode : null],
            unitPrice: [data ? data.unitPrice : null],
            invoiceQty: [data ? data.invoiceQty : null, Validators.compose([Validators.required])],
            assessableValue: [data ? data.assessableValue : null],
            discount: [data ? data.discount : null],
            cgstPercent: [data ? data.cgstPercent : null],
            cgstAmount: [data ? data.cgstAmount : null],
            sgstPercent: [data ? data.sgstPercent : null],
            sgstAmount: [data ? data.sgstAmount : null],
            igstPercent: [data ? data.igstPercent : null],
            igstAmount: [data ? data.igstAmount : null],
            totalReceivedQty: [data ? data['totalReceivedQty'] : null, Validators.compose([Validators.required])],
            totalReceivedValue: [data ? data['totalReceivedValue'] : null, Validators.compose([Validators.required])],
            receivedShortQty: [data ? data['receivedShortQty'] : null, Validators.compose([Validators.required])],
            receivedShortValue: [data ? data['receivedShortValue'] : null, Validators.compose([Validators.required])],
            receivedDamageQty: [data ? data['receivedDamageQty'] : null, Validators.compose([Validators.required])],
            receivedDamageValue: [data ? data['receivedDamageValue'] : null, Validators.compose([Validators.required])],
            netReceivedQty: [data ? data['netReceivedQty'] : null, Validators.compose([Validators.required])],
            netReceivedValue: [data ? data['netReceivedValue'] : null, Validators.compose([Validators.required])],
        }
    }
    public defaultEnableAndDisableFieldsOfItemDetail(itemDetal: FormGroup) {
        const listOfEnableFieldFromItemDetal = ['totalReceivedQty', 'receivedDamageQty', 'binLocation'];
        listOfEnableFieldFromItemDetal.forEach((formControlName: string) => {
            if (itemDetal.get(formControlName)) {
                itemDetal.get(formControlName).enable();
            }
        });
        const listOfDisableFieldFromItemDetal = ['uuid', 'sparePartGrn', 'accPacOrderNumber', 'dmsPoNumber', 'itemNumber', 'itemDescription', 'hsnCode', 'unitPrice', 'invoiceQty', 'assessableValue', 'discount', 'cgstPercent', 'cgstAmount', 'sgstPercent', 'sgstAmount', 'igstPercent', 'igstAmount', 'totalReceivedValue', 'receivedShortQty', 'receivedShortValue', 'receivedDamageValue', 'netReceivedQty', 'netReceivedValue', 'balancedQty'];
        listOfDisableFieldFromItemDetal.forEach((formControlName: string) => {
            if (itemDetal.get(formControlName)) {
                itemDetal.get(formControlName).disable();
            }
        });
    }
    public insertRowIntoItemDetailWhereGrnTypeIsOthers() {
        const newFg = this.fb.group(this.getControlsConfigOfItemDetailForGrnTypeIsOther());
        let itemDetail = (this._goodsReceiptNoteForm.get('itemDetail') as FormArray);
        this.defaultEnableAndDisableFieldsOfItemDetailForGrnTypeOther(newFg);
        itemDetail.push(newFg);
        this._itemDetail$.next(itemDetail);
        return newFg
    }
    private getControlsConfigOfItemDetailForGrnTypeIsOther() {
        return {
            uuid: [uuid.v4()],
            id: [{ value: null, disabled: true }],
            // accPacOrderNumber: [null],
            // dmsPoNumber: [null],
            itemNumber: [null],
            itemDescription: [null],
            binLocation: [null],
            hsnCode: [null],
            unitPrice: [null],
            invoiceQty: [null],
            balancedQty: [null],
            assessableValue: [null],
            discount: [null],
            cgstPercent: [null],
            cgstAmount: [null],
            sgstPercent: [null],
            sgstAmount: [null],
            igstPercent: [null],
            igstAmount: [null],
            totalReceivedQty: [null],
            totalReceivedValue: [null],
            receivedShortQty: [null],
            receivedShortValue: [null],
            receivedDamageQty: [null],
            receivedDamageValue: [null],
            netReceivedQty: [null],
            netReceivedValue: [null],
        }
    }
    public defaultEnableAndDisableFieldsOfItemDetailForGrnTypeOther(itemDetal: FormGroup) {
        const listOfEnableFieldFromItemDetal = ['totalReceivedQty', 'receivedDamageQty', 'binLocation', 'invoiceQty', 'discount', 'unitPrice'];
        listOfEnableFieldFromItemDetal.forEach((formControlName: string) => {
            itemDetal.get(formControlName).enable();
        });
        const listOfDisableFieldFromItemDetal = ['uuid', 'id', 'itemDescription', 'hsnCode', 'itemNumber', 'totalReceivedValue', 'receivedDamageValue', 'assessableValue', 'cgstPercent', 'cgstAmount', 'sgstPercent', 'sgstAmount', 'igstPercent', 'igstAmount', 'receivedShortQty', 'receivedShortValue', 'netReceivedQty', 'netReceivedValue'];
        listOfDisableFieldFromItemDetal.forEach((formControlName: string) => {
            itemDetal.get(formControlName).disable();
        });
    }
    private createItemDetailTotalForm() {
        return this.fb.group({
            basicAmount: [{ value: null, disabled: true }],
            gstAmount: [{ value: null, disabled: true }],
            totalGrnAmount: [{ value: null, disabled: true }]
        });
    }

    public get itemDetailTotalForm(): FormGroup {
        if (this._goodsReceiptNoteForm) {
            return this._goodsReceiptNoteForm.get('itemDetailTotal') as FormGroup;
        }
        this.createGoodsReceiptNoteForm();
        return this._goodsReceiptNoteForm.get('itemDetailTotal') as FormGroup;
    }
}
