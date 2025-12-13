import { Component, OnInit, Output, EventEmitter, Input, SimpleChanges } from '@angular/core';
import { FormControl } from '@angular/forms';
import { PurchaseOrderService } from '../../purchase-order.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-document-upload-container',
  templateUrl: './document-upload-container.component.html',
  styleUrls: ['./document-upload-container.component.scss']
})
export class DocumentUploadContainerComponent implements OnInit {
  public chequeLeafControl = new FormControl('');
  public coveringLetterControl = new FormControl('');
  public creditApplicationControl = new FormControl('');
  public chequeCopyControl = new FormControl(''); //added by mahesh kumar
  public extensionArray: any;
  public extensionChequeLeaf: any;
  public extensionCoveringLetter: any;
  public extensionCreditApplication: any;
  public extensionChequeCopy: any; //added by mahesh kumar
  isCreate:Boolean=false

  @Input() bulkImageUrls = {};
  @Output() sendFormData = new EventEmitter<object>();
  @Input() isView: boolean = false;
  @Input() isEdit: boolean = false;
 @Input() sendTrueOrFalse:any
 @Input() getsendShowCreditFieldEdit:any
 @Input() sendShowCreditFieldEditOrView:any
 @Input() showCheckPhoto:any
 @Input() isApproveOrReject:any
  operation: any;
  constructor(
    private purchaseOrderService: PurchaseOrderService,
    private activatedRoute:ActivatedRoute
  ) { }

  checkFormOperation() {
    this.operation = OperationsUtil.operation(this.activatedRoute);
    // console.log(this.operation,'opration')
    if (this.operation == Operation.VIEW) {
      // console.log(this.operation)
      this.isView = true;
    }
    else if (this.operation == Operation.CREATE) {
      this.isCreate = true

    }

  }

  ngOnInit() {
    this.purchaseOrderService.submitOrResetFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.sendFormData.emit({
          form: 'documentSubmission',
          chequeLeaf: this.chequeLeafControl.value,
          coveringLetter: this.coveringLetterControl.value,
          creditApplication: this.creditApplicationControl.value,
          chequeCopy: this.chequeCopyControl.value //added by mahesh kumar
        });
      } else if (value.toLowerCase() === 'clear') {
        this.chequeLeafControl.reset();
        this.coveringLetterControl.reset();
        this.creditApplicationControl.reset();
        this.chequeCopyControl.reset(); //added by mahesh kumar
      }
    });
    this.startTime()
  }
  ngOnChanges(changes: SimpleChanges) {
  // this.startTime()

    if (this.bulkImageUrls) {
      if (this.bulkImageUrls['chequeLeaf']) {
        this.extensionArray = "";
        this.extensionArray = this.bulkImageUrls['chequeLeaf'].split(".");
        this.extensionChequeLeaf = this.extensionArray[1];
        this.chequeLeafControl.patchValue(this.bulkImageUrls['chequeLeaf'])
      }
      if (this.bulkImageUrls['coveringLetter']) {
        this.extensionArray = "";
        this.extensionArray = this.bulkImageUrls['coveringLetter'].split(".");
        this.extensionCoveringLetter = this.extensionArray[1];
        this.coveringLetterControl.patchValue(this.bulkImageUrls['coveringLetter'])
      }
      if (this.bulkImageUrls['creditApplication']) {
 
        this.extensionArray = "";
        this.extensionArray = this.bulkImageUrls['creditApplication'].split(".");
        this.extensionCreditApplication = this.extensionArray[1];
        console.log(this.extensionCreditApplication ,'forOther')
        this.creditApplicationControl.patchValue(this.bulkImageUrls['creditApplication'])
      }

      //added by mahesh kumar
      if (this.bulkImageUrls['chequeCopy']) {
      
        // console.log(this.bulkImageUrls,'this.bulkImageUrlsthis.bulkImageUrlsthis.bulkImageUrlsthis.bulkImageUrls')
        this.extensionArray = "";
        this.extensionArray = this.bulkImageUrls['chequeCopy'].split(".");
        this.extensionChequeCopy = this.extensionArray[1];
        console.log( this.extensionChequeCopy,' this.extensionChequeCopy')
        this.chequeCopyControl.patchValue(this.bulkImageUrls['chequeCopy'])
      }
    }
  }
  startTime(){
    

setTimeout(()=>{                           // <<<---using ()=> syntax
  console.log(this.showCheckPhoto)
}, 10000);
  }
}