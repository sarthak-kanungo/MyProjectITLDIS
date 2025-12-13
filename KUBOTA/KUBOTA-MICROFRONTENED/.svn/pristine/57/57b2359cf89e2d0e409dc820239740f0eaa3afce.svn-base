import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { MatDialog, MatSelectChange } from '@angular/material';
import { MarketingActivityAddproductDomain, ViewEditActivityProposalDomain, Head } from 'ActivityProposalModule';
import { ActivityProposalService } from '../../activity-proposal.service';
import { ActivatedRoute } from '@angular/router';
import { MarketingActivityCreateContainerService } from '../marketing-activity-create-container/marketing-activity-create-container.service';
import { ToastrService } from 'ngx-toastr';
import { MarketingActivityAddproductService } from './marketing-activity-addproduct.service';


@Component({
  selector: 'app-marketing-activity-addproduct',
  templateUrl: './marketing-activity-addproduct.component.html',
  styleUrls: ['./marketing-activity-addproduct.component.scss']
})
export class MarketingActivityAddproductComponent implements OnInit {
  validStatus: boolean
  itemDetailsFrom: FormGroup;
  isEdit: boolean;
  isView: boolean;
  isSelected: boolean;
  newDealer:boolean=false;
  @Output() noReapeatedHead: EventEmitter<boolean> = new EventEmitter<boolean>()
  isPresent: boolean;
  @Input() validForm: boolean;
  isActivityCategoryKai : boolean
  heads: Head
  @Input() set selectActivityTypeEvent(event: MatSelectChange) {
    if (event) {
      if(event.value.activityType==='ACP BOARD'){
        console.log('New Dealer')
        this.newDealer=true;
      }else{
        this.newDealer=false;
      }
      // console.log("selectActivityTypeEvent event from Addd Header : ", event);
      this.marketingActivityAddproductService.getAllHeadsByActivityTypeId(event.value.id).subscribe(response => {
        console.log("response ", response);
        this.heads = response
        
      })
    }
  }
  @Output() totalAmount: EventEmitter<number> = new EventEmitter<number>()
  @Output() getFormStatusandData = new EventEmitter<object>()
  @Input() set viewEditActivityProposal(value : ViewEditActivityProposalDomain){
    if(value){
      const activityHeads = []
      value.activityProposalHeads.forEach(data => {
        activityHeads.push({
          id: data.id,
          headName : {headName: data.headName},
          amount : data.amount,
          remark : data.remark,
          isMiscellaneous : data.isMiscellaneous
        })
      })
      this.marketingActivityAddproductService.getAllHeadsByActivityTypeId(value.activityProposalHeaderData.activityTypeId ).subscribe(response => {
        console.log("response ", response);
        this.heads = response       
      })

      activityHeads.forEach(element => {
        
        if (element.headName.headName === "Miscellaneous") {
          element.isMiscellaneous = true;
        } else {
          element.isMiscellaneous = false;
        }
        console.log("element--->", element)
        this.addRow(element)
      })

      if(value.activityProposalHeaderData.activityCategory === 'Dealer Own'){
          this.isActivityCategoryKai = false
      }else{
          this.isActivityCategoryKai = true
      }
    }
  }

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private activityProposalService: ActivityProposalService,
    private actRt: ActivatedRoute,
    private marketingActivityCreateContainerService: MarketingActivityCreateContainerService,
    private toastr: ToastrService,
    private marketingActivityAddproductService: MarketingActivityAddproductService
  ) { }

  ngOnInit() {
    this.createItemDetailsFrom()
    this.checkOperationType()
    this.patchOrCreate()
    this.isActivityCategoryKai = true
    
  }
  ngOnChanges() {
      if (this.viewEditActivityProposal) {
        if (this.viewEditActivityProposal.activityProposalHeaderData.activityCategory == 'KAI Supported') {
          this.isActivityCategoryKai = true
        }else{
            this.isActivityCategoryKai = false
        }
      }
  }

  private patchOrCreate() {
    if (this.isView) {
    } else {
      this.addRow()
      this.formForCreateSetup()
    }
  }

  private markFormAsTouchedOfItemDetails() {
    let itemDetails = this.itemDetailsArray()
    itemDetails.markAllAsTouched()
    console.log(itemDetails)
  }

  private createItemDetailsFrom() {
    this.itemDetailsFrom = this.fb.group({
      itemDetails: this.fb.array([])
    });
  }

  private createItemDetailsRow() {
    return this.fb.group({
      isSelected: this.fb.control(false),
      headName: this.fb.control('', Validators.required),
      amount: this.fb.control('', Validators.required),
      remark: this.fb.control(''),
      isMiscellaneous: this.fb.control(false)
    })
  }

  private viewItemDetailsRow(product: MarketingActivityAddproductDomain) {
      return this.fb.group({
        isSelected: this.fb.control({ value: false, disabled: true }),
        headName: this.fb.control({ value: product.headName, disabled: true }),
        amount: this.fb.control({ value: product.amount, disabled: true }),
        remark: this.fb.control({ value: product.remark?product.remark: '' , disabled: true }),
        isMiscellaneous: this.fb.control( product.isMiscellaneous )
      })    
  }

  allowAddItem: boolean = false;

  formForCreateSetup() {
     this.activityProposalService.activityCategoryEvent.subscribe((event : MatSelectChange) => {
          console.log("event ", event);
          if (event.value.category === 'KAI Supported') {
            this.isActivityCategoryKai = true
          }else{
              this.isActivityCategoryKai = false
          }
     }) 
    this.activityProposalService.submitOrResetActivityFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.markFormAsTouchedOfItemDetails()
        if (this.itemDetailsArray().length <= 0) {
          this.toastr.warning('Atleast one add row in Add Head is mandatory', 'Report Add Row')
        }
        else {
          this.getFormStatusandData.emit({ validStatus: this.itemDetailsFrom.valid, formData: this.itemDetailsFrom.value.itemDetails, isActivityCategoryKai : this.isActivityCategoryKai });
        }
      } else if (value.toLowerCase() === 'clear') {
        this.clearHeads()
      }
    })

    
    
    this.itemDetailsFrom.valueChanges.subscribe(frm => {
      this.marketingActivityCreateContainerService.marketingActivityCreateDomain.activityHeads = frm.itemDetails as MarketingActivityAddproductDomain[]
      let total = 0

      frm.itemDetails.forEach(itm => {
      
        let itmDtl = itm as MarketingActivityAddproductDomain
        total += itmDtl.amount;
      });
      this.totalAmount.emit(total)

    })
   
 
    
  }

  private clearHeads() {
    this.itemDetailsFrom.reset();
    this.itemDetailsArray().controls.forEach(row => {
      row['controls'].isSelected.patchValue(true)
      console.log(row)
    })
    this.deleteRow()
    this.addRow()
  }


  keyyear(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  addRow(product?: MarketingActivityAddproductDomain) {
    if(!this.isPresent){
    let itemDetails = this.itemDetailsFrom.controls.itemDetails as FormArray
    // console.log("addRow itemDetails ", itemDetails);
    if (this.isView) {
      itemDetails.push(this.viewItemDetailsRow(product))
    } else {
      itemDetails.push(this.createItemDetailsRow())
    }
  }
  
  }

  deleteRow() {
    let itemDetails = this.itemDetailsFrom.controls.itemDetails as FormArray;
    let nonSelected = itemDetails.controls.filter(machinery => !machinery.value.isSelected)
    if ((itemDetails.length - nonSelected.length)) {
      itemDetails.clear()
      nonSelected.forEach(el => itemDetails.push(el))
      this.isPresent = false;
      this.noReapeatedHead.emit(this.isPresent);
    } else {
      this.toastr.warning('Select Atleast One Row', 'Item Details')
    }
  }

  private checkOperationType() {
    console.log(this.actRt.snapshot.routeConfig.path)
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    console.log(`Edit = ${this.isEdit} View = ${this.isView}`)
  }

  itemDetailsArray() {
    return this.itemDetailsFrom.controls.itemDetails as FormArray;
  }

  compareFnHeads(c1: Head, c2: MarketingActivityAddproductDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.headName === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.headName;
    }
    return c1 && c2 ? c1.headName === c2.headName : c1 === c2;
  }
  
  noHeadRepeatation(event, fg: FormGroup){
    console.log("fg row--->", fg);
    console.log("fg isMiscellaneous--->", fg.get('isMiscellaneous').value);
    if (event.headName == "Miscellaneous") {
      fg.controls.isMiscellaneous.patchValue( true );
    }else{
      fg.controls.isMiscellaneous.patchValue( false );
    }
    let check=0;    
    let itemDetails = this.itemDetailsFrom.controls.itemDetails as FormArray;
    itemDetails.controls.forEach(head => 
    {
      
    let headSelected = head.value.headName.headName;
        if(headSelected==event.headName){
          
          console.log("head repeated");
          check++;
          if (check==2) {
            head['controls'].headName.patchValue(undefined);
            head['controls'].amount.patchValue(undefined);
            head['controls'].remark.patchValue(undefined);
          }
        }
      });
      if (check==1) {
        this.isPresent = false;
      } else {
        this.isPresent = true;
        this.toastr.warning('Select Another Option', "Heads Can't be Repeated!");   
      }
      this.noReapeatedHead.emit(this.isPresent);
  }

  enterValue(event:any,row:any){
    let total = 0
  const  itemDetails =this.itemDetailsFrom.controls.itemDetails as FormArray;
   itemDetails.controls.forEach(element => {
     if( element.get('amount')!==null ||element.get('amount')!==undefined){
    total+=Number(element.get('amount').value)
      // console.log( element.get('amount').value)
     }
     if(total>30000 && this.newDealer){
      this.toastr.error("You Can't Enter More Than 30000");
      element.get('amount').reset();
      return false;
     }
    //  return total;
   });
  }

}

