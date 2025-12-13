import { map } from 'rxjs/operators';
import { Observable, of, Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { PoMachineDetailsService } from '../po-machine-details/po-machine-details.service';
import { PoMachineDetailsContainerService } from './po-machine-details-container.service';
import { Depo, SavePurchaseOrder } from '../../pages/purchase-order-create/purchase-order-create';
import { PoMachineDetailsDataHandleService } from '../po-machine-details/po-machine-details-data-handle.service';
import { AddImplementsContainerService } from '../../../../sales/quotation/component/add-implements-container/add-implements-container.service';

import { PurchaseOrderCreateService } from '../../pages/purchase-order-create/purchase-order-create.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { SalesPurchaseOrderCreateService } from '../sales-purchase-order-create/sales-purchase-order-create.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-po-machine-details-container',
  templateUrl: './po-machine-details-container.component.html',
  styleUrls: ['./po-machine-details-container.component.scss'],
  providers: [PoMachineDetailsDataHandleService,SalesPurchaseOrderCreateService, PoMachineDetailsContainerService, AddImplementsContainerService, PoMachineDetailsService, AutocompleteService]
})
export class PoMachineDetailsContainerComponent implements OnInit {
  public tableTitle = ['Select', 'Item No.', 'Item Description', 'Variant',
    'Order Qty.', 'Unit Price', 'Amount'];
  // 'ACCPAC Stock Qty.', 'ACCPAC Location Code',
  @Input() poApprovalDetails: SavePurchaseOrder = {} as SavePurchaseOrder;

  @Input() finalValueMachineType:any
  // @Input() sendTypeValue:string
  @Output() sendFormData = new EventEmitter<object>();
  public itemNumberAutoList: Observable<(string | object)[]>;
  public selectedItemNumberData: object;
  public isApproveReject: boolean = false;
  public isView=false;
  public salesPoApprovalDetailList$ = new Subject<any>();
  machineType:string
  depot:Depo;
  constructor(
    private activatedRoute: ActivatedRoute,
    private poMachineDetailsDataHandleService: PoMachineDetailsDataHandleService,
    private poMachineDetailsContainerService: PoMachineDetailsContainerService,
    private addImplementsContainerService : AddImplementsContainerService,
    private purchaseOrderCreateService : PurchaseOrderCreateService,
    private salesPurchaseOrderCreateService:SalesPurchaseOrderCreateService,
    private loginService:LoginFormService,
    private _toaterService:ToastrService
  ) {
    this.activatedRoute.paramMap.subscribe(params => {
      if (params && params.has('approveId')) {
        this.isApproveReject = true;
        this.getSalesPoApprovalDetailList(atob(params.get('approveId')));
      }
      if (params && params.has('viewId')) {
        this.isView=true;
        let userType = loginService.getLoginUserType();
        if(userType=='itldis'){
          this.getSalesPoApprovalDetailList(params.get('viewId'));
        }
      }
    })
    this.purchaseOrderCreateService.depoChangeSubject.subscribe(depot => {
      console.log(depot,'depotsss')
        this.depot = depot;
        // console.log(this.depot,'depot')
    })
  }
  ngOnChanges(){
    this.machineType=this.finalValueMachineType;
  }
  // ngAfterViewChecked(){
    
  //   this.itemNumberChanges();

  // }
  ngOnInit() {
    // setTimeout(() => {this.itemNumberChanges()},3000);
     this.itemNumberChanges();
    if (this.isApproveReject) {
      this.tableTitle = ['Select', 'Item No.', 'Item Description', 'Variant',
        'Order Qty.', 'Discount Amount', 'Discount Percentage', 'Unit Price', 'Amount'];
    }
    else if(this.isView){
      this.tableTitle = ['Select', 'Item No.', 'Item Description', 'Variant',
      'Order Qty.', 'Discount Amount', 'Discount Percentage', 'Unit Price', 'Amount'];
    }
  }
  getSalesPoApprovalDetailList(poId: string) {
    this.poMachineDetailsContainerService.getSalesPoApprovalDetails(poId)
      .subscribe((approvalListRes) => {
        if (approvalListRes.result) {
          const approvalList = {};
          approvalList['head'] = Object.keys(approvalListRes.result[0]);
          approvalList['list'] = approvalListRes.result
          this.salesPoApprovalDetailList$.next(approvalList);
        }
      });
  }



  
  
  itemNumberChanges() {
    
      
   
      this.poMachineDetailsDataHandleService.itemNumberValueChange.subscribe(value => {
        if (value) {
          /*this.itemNumberAutoList = this.poMachineDetailsContainerService.getItemNumberObjectAutocomplete(value).pipe(
            map(res => res['result']
            ))*/
            this.itemNumberAutoList = this.addImplementsContainerService.searchItemNo(value, '', 'PO',this.machineType
              
            ).pipe(
                    map(res => res['result'])
             );
         }
      })
  }
  getFormStatusAndDataWithFormName(event) {
    this.sendFormData.emit({ form: 'machineDetails', event: event })
  }
  getSelectedItemData(event: object) {
    // console.log(event,'event')
    
    // console.log(this.depot,'depot')
    this.poMachineDetailsContainerService.getDataFromItemNumber(event['event'].option.value.code, this.depot).subscribe(res => {
      if (res) {
        let finalObj = {};
        finalObj['itemData'] = res['result'];
        finalObj['uuid'] = event['uuid'];
        this.selectedItemNumberData = { ...finalObj }
      }
    })
  }

  sendTypeValue(event:any){
    console.log(event,'djdjdjdjd')
  }

}
