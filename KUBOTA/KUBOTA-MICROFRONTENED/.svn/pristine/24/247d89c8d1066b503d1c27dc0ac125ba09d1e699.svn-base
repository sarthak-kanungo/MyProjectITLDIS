import { Component, OnInit, Input, OnChanges,ChangeDetectorRef } from '@angular/core';
import { FormGroup, AbstractControlOptions, AbstractControl } from '@angular/forms';
import { PartQuotationPagePresenter } from '../part-quotation-page/part-quotation-page-presenter';
import { TableConfig } from 'editable-table';
import { PartQuotationTableWebService } from './part-quotation-table-web.service';
import { Operation } from '../../../../../utils/operation-util';
import { DetailsByItemNo, QuotationPartDetail } from '../../domain/part-quotation-domain';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs'

@Component({
  selector: 'app-part-quotation-table',
  templateUrl: './part-quotation-table.component.html',
  styleUrls: ['./part-quotation-table.component.scss'],
  providers: [PartQuotationTableWebService]
})
export class PartQuotationTableComponent implements OnInit, OnChanges {
  @Input() markForCheck: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isView: boolean
  tableData: any;
  partQuotationTableForm: FormGroup
  @Input() partQuotationTotalForm: FormGroup
  itemDetailsFormControl: AbstractControl;
  tableConfig: TableConfig[]
  selectPatchValue: object;
  assignTo: { list: any; config: any; searchKey: any; };
  patchValue: { rowIndex: any; tableRowId: any; patchValue: any; }[];
  etControlsConfig: {
    [key: string]: any, options?: AbstractControlOptions | {
      [key: string]: any;
    }
  }
  rowIndex: number
  total: number 
  itemIdArray = [];
  @Input() viewItemDeatils : QuotationPartDetail[]

  constructor(
    private partQuotationPagePresenter: PartQuotationPagePresenter,
    private partQuotationTableWebService: PartQuotationTableWebService,
    private changeDetectorRef: ChangeDetectorRef,
    private toastr: ToastrService,
  ) { }

  ngOnChanges(){
    // if(this.viewItemDeatils){
    //   this.viewItemDeatils.forEach(element => {
    //     console.log('element', element.sparePartMasterId);
    //     this.itemIdArray.push(element.sparePartMasterId);
    //   })
    // }
  }


  ngOnInit() {
    this.partQuotationTableForm = this.partQuotationPagePresenter.partQuotationTableRow as FormGroup
    this.itemDetailsFormControl = this.partQuotationPagePresenter.partQuotationTableRow
    this.tableConfig = this.getTableConfigurationJSON();
    this.etControlsConfig = this.partQuotationPagePresenter.createPartQuotationTableRow();
    this.viewOrEditOrCreate();
    this.changeDetectorMarkForCheck();
    this.partQuotationPagePresenter.onClickClearBehaviorCall.subscribe(val => {
        if(val=='clear'){
            this.partQuotationPagePresenter.partQuotationTotalForm.reset()
        }
    })
  }
  private viewOrEditOrCreate() {
    if (this.partQuotationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
      this.total = this.partQuotationPagePresenter.totalAmt;
      this.totalCalculationForGstAmount()
      //console.log("viewItemDeatils--->", this.viewItemDeatils)
      //this.partQuotationPagePresenter.patchValueForViewQuotation(this.viewItemDeatils)
    }
  }
  editableTableSearchValueChanges(event) {
    if(!this.isView ){
      this.getItemNumberList(event);
      this.calculationForItemDetails(event)
      this.netAmountValueChanges(event)
      this.validationForDiscount(event)
      this.getQuantity(event)
    }else{
        this.total = this.partQuotationPagePresenter.totalAmt;
    }
  }
  getItemNumberList(event) {
    if (event.config.key == "itemNo" && event['key'] !== null) {
      this.itemIdArray = []
      const quotationForm = this.partQuotationPagePresenter.partQuotationForm.getRawValue()
      if (quotationForm.itemDetails.length > 0) {
        quotationForm.itemDetails.forEach(element => {
          console.log("element--->", element)
          if (typeof element.itemNo === 'object' && element.itemNo) {
            this.itemIdArray.push(element.itemNo.id);
            console.log("itemIdArray ", this.itemIdArray);
          }
        })
      }
      const itemNo = typeof event['key'] == 'object' ? event['key']['itemNo'] : event['key']
      this.partQuotationTableWebService.autocompletePartNo(itemNo, this.itemIdArray.join()).subscribe(res => {
        this.assignTo = {
          list: res,
          config: event['config'],
          searchKey: event['key']
        };
      })
      if (event.key == "" || event['key'] === null) {
        this.patchNullDetails(event)
      }
    }

  }

  private patchNullDetails(event: object) {
    // console.log("event ", event);
    this.patchValue = [{
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { itemDescription: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { hsnCode: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { unitPrice: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { discountPercent: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { cgstPercent: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { sgstPercent: '' }
    },
    {
      rowIndex: event['rowIndex'],
      tableRowId: event['tableRow']['tableRowId'],
      patchValue: { igstPercent: '' }
    },
    ];
  }

  getQuantity(event) {
    if (event.config.key == "quantity" && event['key'] !== null) {
      if (event.key == ("" || 0) || event['key'] === null) {
        this.patchNullDetailsForQuantity(event)
      }
    }
  }
  patchNullDetailsForQuantity(event: object) {
    this.patchValue = [
      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { amount: '' }
      },
      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { discountAmount: '' }
      },
      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { netAmount: '' }
      },

      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { cgstAmount: '' }
      },

      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { sgstAmount: '' }
      },
      {
        rowIndex: event['rowIndex'],
        tableRowId: event['tableRow']['tableRowId'],
        patchValue: { igstAmount: '' }
      }
    ]
  }
  validationForDiscount(event) {
    if (event.config.key == "discountPercent" && event['key'] !== null) {
      if (event['key'] > 100) {
        this.toastr.error(`Discount Percent exceeds 100`, 'Report Discount Percent')
      }
    }
  }

  netAmountValueChanges(event) {
    if (event.config.key == "netAmount" && event['key'] !== null) {
      this.totalCalculationForSubTotal()
      this.totalCalculationForGstAmount()
      this.totalInvoiceAmount()
    }
  }
  optionSelected(event) {
    console.log("itemNoSelected--->", event)
    this.patchDetailsFromItemNumbers(event);
  }
  private patchDetailsFromItemNumbers(event) {
    this.rowIndex = event['rowIndex']
    const quotationDetails = this.partQuotationPagePresenter.detailsForm.getRawValue()
    console.log("quotationDetails--->", quotationDetails)
    const state = quotationDetails.state.state
    const itemNo = event.option.itemNo
    this.partQuotationTableWebService.getSparePartDetailsForQuotation(itemNo, state).subscribe(res => {
      console.log("getSparePartDetailsForQuotation--->", res)
      this.patchValueForItemNo(event, res)
    })
  }
  private patchValueForItemNo(event, res: DetailsByItemNo) {
    const quotationDetails = this.partQuotationPagePresenter.detailsForm.getRawValue()
    if (res) {
      this.patchValue = [
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { itemDescription: res.itemDescription }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { hsnCode: res.hsnCode }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { unitPrice: res.unitPrice }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { cgstPercent: res.cgstPercent }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { sgstPercent: res.sgstPercent }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { igstPercent: res.igstPercent }
        },
        {
          rowIndex: event['rowIndex'],
          tableRowId: event['tableRow']['tableRowId'],
          patchValue: { discountPercent: quotationDetails.discountRate }
        }
      ]
    }

  }
  tableValueChange(event) {
    event.subscribe(res => {

    })
  }
  private calculationForItemDetails(res) {
    const amount = res.tableRow.unitPrice * res.tableRow.quantity
    const amt = amount.toFixed(2)
    const discountAmt = amount * (res.tableRow.discountPercent / 100)
    const discountAmount = discountAmt.toFixed(2)
    const netAmt = amount - discountAmt
    const netAmount = netAmt.toFixed(2)
    const cgstAmt = netAmt * (res.tableRow.cgstPercent / 100)
    const cgstAmount = cgstAmt ? cgstAmt.toFixed(2) : 0
    const sgstAmt = netAmt * (res.tableRow.sgstPercent / 100)
    const sgstAmount = sgstAmt ? sgstAmt.toFixed(2) : 0
    const igstAmt = netAmt * (res.tableRow.igstPercent / 100)
    const igstAmount = igstAmt ? igstAmt.toFixed(2) : 0
    const nett = (netAmount && parseFloat(netAmount))
    const cgst = (cgstAmount && parseFloat(cgstAmount))
    const sgst = (sgstAmount && parseFloat(sgstAmount))
    const igst = (igstAmount && parseFloat(igstAmount))
    const totalAmountForItem = nett + (cgst + sgst + igst)
    const totalAmt = totalAmountForItem ? totalAmountForItem.toFixed(2) : 0

    if (amount) {
      this.patchValue = [
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { amount: amt }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { discountAmount: discountAmount }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { netAmount: netAmount }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { cgstAmount: cgstAmount }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { sgstAmount: sgstAmount }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { igstAmount: igstAmount }
        },
        {
          rowIndex: this.rowIndex,
          tableRowId: res.tableRow.tableRowId,
          patchValue: { total: totalAmt }
        }
      ]
    }
  }
  private changeDetectorMarkForCheck() {
      if (this.markForCheck) {
        this.markForCheck.subscribe(res => {
          if (res) {
            console.log('res', res);
            this.changeDetectorRef.markForCheck();
          }
        });
      }
  }
  private totalCalculationForSubTotal() {
    let subtotal = 0
    let discountAmount = 0
    if (this.itemDetailsFormControl.value) {
      this.itemDetailsFormControl.value.forEach(element => {
        subtotal += (element.netAmount && parseFloat(element.netAmount)) || 0
        discountAmount += (element.discountAmount && parseFloat(element.discountAmount)) || 0
      });
      if (subtotal > 0) {
        this.partQuotationTotalForm.get('subTotal').patchValue(subtotal.toFixed(2))
        this.partQuotationPagePresenter.detailsForm.get('totalBasicValue').patchValue(subtotal.toFixed(2))
      } else {
        this.partQuotationTotalForm.get('subTotal').patchValue(0)
        this.partQuotationPagePresenter.detailsForm.get('totalBasicValue').patchValue(0)
      }
      if (discountAmount > 0) {
        this.partQuotationPagePresenter.detailsForm.get('totalDiscountValue').patchValue(discountAmount.toFixed(2))
      } else {
        this.partQuotationPagePresenter.detailsForm.get('totalDiscountValue').patchValue(0)
      }
    }
    //this.changeDetectorRef.detectChanges();
  }
  private totalCalculationForGstAmount() {
    let gstAmount = 0
    let total = 0
    this.itemDetailsFormControl.value.forEach(element => {
      const cgstAmount = (element.cgstAmount && parseFloat(element.cgstAmount))
      const sgstAmount = (element.sgstAmount && parseFloat(element.sgstAmount))
      const igstAmount = (element.igstAmount && parseFloat(element.igstAmount))
      gstAmount += (cgstAmount + sgstAmount + igstAmount) || 0
      total += (element.total && parseFloat(element.total));
    });
    if (gstAmount > 0) {
      this.partQuotationTotalForm.get('gstAmount').patchValue(gstAmount.toFixed(2))
      this.partQuotationPagePresenter.detailsForm.get('totalTaxValue').patchValue(gstAmount.toFixed(2))
      this.total = parseFloat(total.toFixed(2));
    } else {
      this.partQuotationTotalForm.get('gstAmount').patchValue(0)
      this.partQuotationPagePresenter.detailsForm.get('totalTaxValue').patchValue(0)
    }
    //this.changeDetectorRef.detectChanges();
  }
  private totalInvoiceAmount() {
    const value = this.partQuotationTotalForm.getRawValue()
    let totalAmount = 0
    totalAmount = (value.subTotal && parseFloat(value.subTotal)) + (value.gstAmount && parseFloat(value.gstAmount))
    this.partQuotationTotalForm.get('totalInvoiceAmount').patchValue(totalAmount.toFixed(2))
    this.partQuotationPagePresenter.detailsForm.get('totalQuotationAmount').patchValue(totalAmount.toFixed(2))
    //this.changeDetectorRef.detectChanges();
  }
  getTableConfigurationJSON(): Array<TableConfig> {
    return [{
      title: 'Select',
      formControlName: 'isSelected',
      key: 'select',
      inputField: 'checkbox',
    }, {
      title: 'Item No',
      formControlName: 'itemNo',
      key: 'itemNo',
      inputField: 'autocomplete',
      displayKey: 'value',
      patchKey: 'itemNo',
      isNeedValueChanges: true
    },
    {
      title: 'Item Description',
      formControlName: 'itemDescription',
      key: 'itemDescription',
      inputField: 'input',
      displayKey: 'itemDescription'
    },
    {
      title: 'HSN Code',
      formControlName: 'hsnCode',
      key: 'hsnCode',
      inputField: 'input',
      displayKey: 'hsnCode'
    },
    {
      title: 'Unit Price',
      formControlName: 'unitPrice',
      key: 'unitPrice',
      inputField: 'input',
      displayKey: 'unitPrice'
    },
    {
      title: 'Qty',
      formControlName: 'quantity',
      key: 'quantity',
      inputField: 'input',
      isNeedValueChanges: true
    },
    {
      title: 'Amount',
      formControlName: 'amount',
      key: 'amount',
      inputField: 'input',
      displayKey: 'amount'
    },
    {
      title: 'Discount %',
      formControlName: 'discountPercent',
      key: 'discountPercent',
      inputField: 'input',
      displayKey: 'discountPercent',
      isNeedValueChanges: true
    },
    {
     // title: 'Discount Amount', title is commented & added by vinay accordint to requirement
      title: 'Discount Amount',
      formControlName: 'discountAmount',
      key: 'discountAmount',
      inputField: 'input',
      displayKey: 'discountAmount'
    },
    {
      title: 'After Discount Net Amount',
      formControlName: 'netAmount',
      key: 'netAmount',
      inputField: 'input',
      displayKey: 'netAmount',
      isNeedValueChanges: true
    },
    {
      title: 'CGST %',
      formControlName: 'cgstPercent',
      key: 'cgstPercent',
      inputField: 'input',
      displayKey: 'cgstPercent'
    },
    {
      title: 'CGST Amount',
      formControlName: 'cgstAmount',
      key: 'cgstAmount',
      inputField: 'input',
      displayKey: 'cgstAmount'
    },
    {
      title: 'SGST %',
      formControlName: 'sgstPercent',
      key: 'sgstPercent',
      inputField: 'input',
      displayKey: 'sgstPercent'
    },
    {
      title: 'SGST Amount',
      formControlName: 'sgstAmount',
      key: 'sgstAmount',
      inputField: 'input',
      displayKey: 'sgstAmount'
    },
    {
      title: 'IGST %',
      formControlName: 'igstPercent',
      key: 'igstPercent',
      inputField: 'input',
      displayKey: 'igstPercent'
    },
    {
      title: 'IGST Amount',
      formControlName: 'igstAmount',
      key: 'igstAmount',
      inputField: 'input',
      displayKey: 'igstAmount'
    },
    {
      title: 'Total',
      formControlName: 'total',
      key: 'total',
      inputField: 'input',
      displayKey: 'total'
    },
    ]
  }
}