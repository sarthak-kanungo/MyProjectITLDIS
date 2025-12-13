import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { Subject } from 'rxjs';
import { SparesGrnItemDetailApiService } from './spares-grn-item-detail-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { SparesGrnPagePresenter } from '../spares-grn-page/spares-grn-page.presenter';
import { ToastrService } from 'ngx-toastr';
import { PartQuotationPagePresenter } from 'src/app/spares/counter-sales/parts-quotation/component/part-quotation-page/part-quotation-page-presenter';
import { Operation } from 'src/app/utils/operation-util';

@Injectable()
export class SparesGrnItemDetailService {

  private binLocationList$ = new Subject<SelectList[]>();
  
  constructor(
    private sparesGrnPagePresenter: SparesGrnPagePresenter,
    private sparesGrnItemDetailApiService: SparesGrnItemDetailApiService,
    private toastr: ToastrService
  ) { 
    
  }
  
  calcTotalReceivedValue = (unitPrice: number, totalReceivedQty: number) => unitPrice * totalReceivedQty;
  calcReceivedShortQty = (balancedQty: number, totalReceivedQty: number, receivedDamageQty: number) => balancedQty - (totalReceivedQty + receivedDamageQty);
  calcNetReceived = (totalReceived: number, receivedShort: number, receivedDamage: number) => totalReceived + receivedDamage;
  totalReceivedQtyValueChange(itemDetail: FormArray, viewCheck) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      if (viewCheck==='create') {
        let defaultReceivedQty=fg.get('balancedQty').value
        fg.get('totalReceivedQty').patchValue(defaultReceivedQty)
        this.calcAndPatchValueToTotalReceivedValue(fg, defaultReceivedQty);
        this.calcAndPatchValueToReceivedShortQty(fg, defaultReceivedQty);
        this.calcAndPatchValueToNetReceivedQty(fg);
      }
/* above 5 lines are added by vinay to set Default Receipt Qty as balance Qty */
      fg.get('totalReceivedQty').valueChanges.subscribe(val => {
        console.log('fg.get(totalReceivedQty): ', fg.get('totalReceivedQty').errors);
        this.validateTotalReceivedQty(fg);
        // this.validateReceivedDamageQty(fg);
        this.calcAndPatchValueToTotalReceivedValue(fg, val);
        this.calcAndPatchValueToReceivedShortQty(fg, val);
        this.calcAndPatchValueToNetReceivedQty(fg);
      });
    });
  }
  private validateTotalReceivedQty(fg: FormGroup) {
    const totalReceivedQty = fg.get('totalReceivedQty').value;
    const totalReceivedQtyValid = (totalReceivedQty && parseFloat(totalReceivedQty)) || 0;
    const balancedQty = fg.get('balancedQty').value;
    const balancedQtyValid = (balancedQty && parseFloat(`${ balancedQty }`)) || 0;
    const receivedDamageQty = fg.get('receivedDamageQty').value;
    const receivedDamageQtyValid = (receivedDamageQty && parseFloat(receivedDamageQty)) || 0;
    if (totalReceivedQtyValid > (balancedQtyValid - receivedDamageQtyValid)) {
      fg.get('totalReceivedQty').setErrors({ isExceed: true, msg: 'Sum of total received qty and received damage qty' }, { emitEvent: false });
      fg.get('totalReceivedQty').markAsTouched({ onlySelf: true })
      // fg.get('totalReceivedQty').updateValueAndValidity();
      return;
    }
    fg.get('totalReceivedQty').setErrors(null, { emitEvent: false });
    fg.get('totalReceivedQty').markAsTouched({ onlySelf: true })
    // fg.get('totalReceivedQty').updateValueAndValidity();
  }
  private calcAndPatchValueToTotalReceivedValue(fg: FormGroup, totalReceivedQty: any) {
    const unitPrice = fg.get('unitPrice').value;
    const unitPriceValid = (unitPrice && parseFloat(unitPrice)) || 0;
    const totalReceivedQtyValid = (totalReceivedQty && parseFloat(totalReceivedQty)) || 0;
    const totalReceivedValue = this.calcTotalReceivedValue(unitPriceValid, totalReceivedQtyValid);
    fg.get('totalReceivedValue').patchValue(parseFloat(totalReceivedValue.toFixed(2)));
  }
  private calcAndPatchValueToReceivedShortQty(fg: FormGroup, totalReceivedQty?: any) {
    const balancedQty = fg.get('balancedQty').value;
    const balancedQtyValid = (balancedQty && parseFloat(balancedQty)) || 0;
    totalReceivedQty = fg.get('totalReceivedQty').value;
    const totalReceivedQtyValid = (totalReceivedQty && parseFloat(totalReceivedQty)) || 0;
    const receivedDamageQty = fg.get('receivedDamageQty').value;
    const receivedDamageQtyValid = (receivedDamageQty && parseFloat(receivedDamageQty)) || 0;

    let receivedShortQty = 0;
    if (balancedQtyValid >= totalReceivedQtyValid) {
      receivedShortQty = this.calcReceivedShortQty(balancedQtyValid, totalReceivedQtyValid, receivedDamageQtyValid);
    }
    receivedShortQty > 0 ? fg.get('receivedShortQty').patchValue(receivedShortQty) : fg.get('receivedShortQty').patchValue(0);
    this.calcAndPatchValueToReceivedShortValue(fg);
  }
  private calcAndPatchValueToReceivedShortValue(fg: FormGroup) {
    const unitPrice = fg.get('unitPrice').value;
    const unitPriceValid = (unitPrice && parseFloat(unitPrice)) || 0;
    const receivedShortQty = fg.get('receivedShortQty').value;
    const receivedShortQtyValid = (receivedShortQty && parseFloat(receivedShortQty)) || 0;
    const receivedShortValue = this.calcTotalReceivedValue(unitPriceValid, receivedShortQtyValid);
    fg.get('receivedShortValue').patchValue(parseFloat(receivedShortValue.toFixed(2)));
  }
  receivedDamageQtyValueChange(itemDetail: FormArray) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      fg.get('receivedDamageQty').valueChanges.subscribe(val => {
        this.calcAndPatchValueToReceivedDamageValue(fg, val);
        this.calcAndPatchValueToNetReceivedQty(fg);
        this.validateReceivedDamageQty(fg);
        // this.validateTotalReceivedQty(fg);
        this.calcAndPatchValueToReceivedShortQty(fg);
        this.calcAndPatchValueToNetReceivedQty(fg);
      });
    });
  }
  private validateReceivedDamageQty(fg: FormGroup) {
    const totalReceivedQty = fg.get('totalReceivedQty').value;
    const totalReceivedQtyValid = (totalReceivedQty && parseFloat(totalReceivedQty)) || 0;
    const balancedQty = fg.get('balancedQty').value;
    const balancedQtyValid = (balancedQty && parseFloat(`${ balancedQty }`)) || 0;
    const receivedDamageQty = fg.get('receivedDamageQty').value;
    const receivedDamageQtyValid = (receivedDamageQty && parseFloat(receivedDamageQty)) || 0;
    if (receivedDamageQtyValid > (balancedQtyValid - totalReceivedQtyValid)) {
      fg.get('receivedDamageQty').setErrors({ isExceed: true, msg: 'Sum of total received qty and received damage qty' }, { emitEvent: false });
      fg.get('receivedDamageQty').markAsTouched({ onlySelf: true });
      return;
    }
    fg.get('receivedDamageQty').setErrors(null, { emitEvent: false });
    fg.get('receivedDamageQty').markAsTouched({ onlySelf: true });
  }
  private calcAndPatchValueToReceivedDamageValue(fg: FormGroup, val: any) {
    const unitPrice = fg.get('unitPrice').value;
    const unitPriceValid = (unitPrice && parseFloat(unitPrice)) || 0;
    const receivedDamageQtyValid = (val && parseFloat(val)) || 0;
    const receivedDamageValue = this.calcTotalReceivedValue(unitPriceValid, receivedDamageQtyValid);
    fg.get('receivedDamageValue').patchValue(parseFloat(receivedDamageValue.toFixed(2)));
  }
  private calcAndPatchValueToNetReceivedQty(fg: FormGroup) {
    const totalReceivedQty = fg.get('totalReceivedQty').value;
    const totalReceivedQtyValid = (totalReceivedQty && parseFloat(totalReceivedQty)) || 0;
    const receivedShortQty = fg.get('receivedShortQty').value;
    const receivedShortQtyValid = (receivedShortQty && parseFloat(receivedShortQty)) || 0;
    const receivedDamageQty = fg.get('receivedDamageQty').value;
    const receivedDamageQtyValid = (receivedDamageQty && parseFloat(receivedDamageQty)) || 0;
    const netReceivedQty = this.calcNetReceived(totalReceivedQtyValid, receivedShortQtyValid, receivedDamageQtyValid);
    fg.get('netReceivedQty').patchValue(netReceivedQty);
    this.calcAndPatchValueToNetReceivedValue(fg);
    this.calcAndPatchValueToGstAmount();
  }
  private calcAndPatchValueToNetReceivedValue(fg: FormGroup) {
    const totalReceivedValue = fg.get('totalReceivedValue').value;
    const totalReceivedValueValid = (totalReceivedValue && parseFloat(totalReceivedValue)) || 0;
    const receivedShortValue = fg.get('receivedShortValue').value;
    const receivedShortValueValid = (receivedShortValue && parseFloat(receivedShortValue)) || 0;
    const receivedDamageValue = fg.get('receivedDamageValue').value;
    const receivedDamageValueValid = (receivedDamageValue && parseFloat(receivedDamageValue)) || 0;
    const netReceivedValue = this.calcNetReceived(totalReceivedValueValid, receivedShortValueValid, receivedDamageValueValid);
    fg.get('netReceivedValue').patchValue(parseFloat(netReceivedValue.toFixed(2)));
    this.calcAndPatchValueToBasicAmount();
  }
  private calcAndPatchValueToBasicAmount() {
    let basicAmount = 0;
    this.sparesGrnPagePresenter.itemDetailFa.controls.forEach((fg: FormGroup) => {

      const totalReceivedValue = fg.get('netReceivedValue').value;
      const totalReceivedValueValid = (totalReceivedValue && parseFloat(totalReceivedValue)) || 0;
      basicAmount += totalReceivedValueValid;
    })
    this.sparesGrnPagePresenter.itemDeatailTotalForm.get('basicAmount').patchValue(parseFloat(basicAmount.toFixed(2)));
    this.calcAndPatchValueToTotalGrnAmount();
  }
  private calcAndPatchValueToGstAmount() {
    let gstAmount = 0;
    this.sparesGrnPagePresenter.itemDetailFa.controls.forEach((fg: FormGroup) => {
      const netReceivedValue = fg.get('netReceivedValue').value;
      const netReceivedValueValid = (netReceivedValue && parseFloat(netReceivedValue)) || 0;
      const igstPercent = fg.get('igstPercent').value;
      const igstPercentValid = (igstPercent && parseFloat(igstPercent)) || 0;
      if (!igstPercentValid) {
        const sgstPercent = fg.get('sgstPercent').value;
        const sgstPercentValid = (sgstPercent && parseFloat(sgstPercent)) || 0;
        const cgstPercent = fg.get('cgstPercent').value;
        const cgstPercentValid = (cgstPercent && parseFloat(cgstPercent)) || 0;
        gstAmount += (netReceivedValueValid *(sgstPercentValid + cgstPercentValid))/100;
        return;
      }
      // const netGstAmountPerItem = netReceivedValue * igstPercentValid;
      gstAmount += (netReceivedValueValid * igstPercentValid)/100;
    });
    this.sparesGrnPagePresenter.itemDeatailTotalForm.get('gstAmount').patchValue(parseFloat(gstAmount.toFixed(2)));
    this.calcAndPatchValueToTotalGrnAmount();

  }
  private calcAndPatchValueToTotalGrnAmount() {
    const basicAmount = this.sparesGrnPagePresenter.itemDeatailTotalForm.get('basicAmount').value;
    const basicAmountValid = (basicAmount && parseFloat(basicAmount)) || 0;
    const gstAmount = this.sparesGrnPagePresenter.itemDeatailTotalForm.get('gstAmount').value;
    const gstAmountValid = (gstAmount && parseFloat(gstAmount)) || 0;
    const totalGrnAmount = basicAmountValid + gstAmountValid;
    this.sparesGrnPagePresenter.itemDeatailTotalForm.get('totalGrnAmount').patchValue(parseFloat(totalGrnAmount.toFixed(2)));
  }
  binLocationValueChange(itemDetail: FormArray) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      
      fg.get('binLocation').valueChanges.subscribe(val => {
        if(val && typeof val === 'object'){
          val = val['binLocation']
        }
        const storeId = this.sparesGrnPagePresenter.grnForm.get('store').value;
        if (storeId) {
          this.sparesGrnItemDetailApiService.searchBinLocationByStoreId(storeId.id, val, fg.get('itemNumber').value).subscribe(res => {
            console.log("binLocation--->", res)
            this.binLocationList$.next(res);
          });
          return;
        }
        this.toastr.error('Please select store', 'Error');
        this.sparesGrnPagePresenter.grnForm.get('store').markAsTouched();
      });
    });
    return this.binLocationList$.asObservable();
  }
  unitPriceValueChange(itemDetail: FormArray) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      fg.get('unitPrice').valueChanges.subscribe(val => {
        this.calcAndPatchValueToTotalReceivedValue(fg, fg.get('totalReceivedQty').value);
        this.calcAndPatchValueToReceivedShortQty(fg, fg.get('totalReceivedQty').value);
        this.calcAndPatchValueToNetReceivedQty(fg);
      });
    });
  }
  invoiceQtyValueChange(itemDetail: FormArray) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      fg.get('invoiceQty').valueChanges.subscribe(val => {
        this.validateInvoiceQty(fg);
      });
    });
  }
  private validateInvoiceQty(fg: FormGroup) {
    const invoiceQty = parseInt(fg.get('invoiceQty').value) || 0;
    const poQuantity = parseInt(fg.get('poQuantity').value) || 0;
    if (fg.get('poQuantity').value && poQuantity < invoiceQty) {
      fg.get('invoiceQty').setErrors({ isExceed: true, msg: 'Shoudn\'t exceed than po qty' }, { emitEvent: false });
      return;
    }
    fg.get('invoiceQty').setErrors(null);
  }
  discountValueChange(itemDetail: FormArray) {
    itemDetail && itemDetail.controls.forEach((fg: FormGroup) => {
      fg.get('discount').valueChanges.subscribe(val => {
        this.validateDiscount(fg);
      });
    });
  }
  private validateDiscount(fg: FormGroup) {
    const discount = parseInt(fg.get('discount').value) || 0;
    const unitPrice = parseInt(fg.get('unitPrice').value) || 0;
    if (discount > unitPrice) {
      fg.get('discount').setErrors({ isExceed: true, msg: 'Shoudn\'t exceed than unit price' }, { emitEvent: false });
      return;
    }
    fg.get('discount').setErrors(null);
  }
}
