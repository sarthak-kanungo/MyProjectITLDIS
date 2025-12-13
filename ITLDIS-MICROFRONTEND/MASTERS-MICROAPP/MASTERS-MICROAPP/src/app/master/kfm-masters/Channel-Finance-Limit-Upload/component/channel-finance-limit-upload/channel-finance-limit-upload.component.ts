import { invalid } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { UploadableFile } from 'itldis-file-upload';
import { ToastrService } from 'ngx-toastr';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import * as XLSX from 'xlsx';
import { ChannelFinanceLimitUpload, DealerBankDetailsId, DealerCodeInvalid } from './channel-finance-limit-upload-domain';
import { ChannelFinanceLimitUploadService } from './channel-finance-limit-upload.service';


type AOA = any[][];

@Component({
  selector: 'app-channel-finance-limit-upload',
  templateUrl: './channel-finance-limit-upload.component.html',
  styleUrls: ['./channel-finance-limit-upload.component.css']
})
export class ChannelFinanceLimitUploadComponent implements OnInit {

  channelFinanceLimitUploadForm: FormGroup;
  files: Array<UploadableFile> = new Array();
  data: AOA = [];
  fileName: string = 'CF Limit Upload.xlsx';
  wopts: XLSX.WritingOptions = { bookType: 'xlsx', type: 'array' };
  headers: string[] = ['Dealer_Code', 'Bank_Name', 'Flexi_Loan_Account_No.'];
  validFile:boolean = false;

  constructor(
    private fb : FormBuilder,
    private fileUploadService: FileUploadService,
    private toasterService: ToastrService,
    private channelFinanceLimitUploadService: ChannelFinanceLimitUploadService,
  ) {  }

  ngOnInit() {
    this.createchannelFinanceLimitUploadForm();
    this.clearAll();
  }

  createchannelFinanceLimitUploadForm() {
    this.channelFinanceLimitUploadForm = this.fb.group({
      excelSheet: [null, Validators.required]
    })
  }

  fileSelctionChanges(fileInput: any) {
    if (!this.isFilesCount()) {
      this.importExcelData(fileInput);
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()     
    }else{
        this.toasterService.error("Maximum 1 document is allowed");
        this.clearAll();
    }
  }
  
  isFilesCount() {
    return this.fileUploadService.fileCount() == 1
  }

  importExcelData(fileInput: any){
    const target: DataTransfer = <DataTransfer>(fileInput.target);
    if (target.files.length !== 1) throw new Error('Cannot use multiple files');
    const reader: FileReader = new FileReader();
    reader.onload = (e: any) => {
      /* read workbook */
      const bstr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });
      /* grab first sheet */
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];
      /* save data */
      this.data = <AOA>(XLSX.utils.sheet_to_json(ws, { header: 1 }));
      this.excelFileCheck(this.data);
    };
     reader.readAsBinaryString(target.files[0]);
  }

  excelFileCheck(excelData){
    let filterData = [];
    let check =0;
    let j = 0;   
    excelData.forEach(row => {     
      if (row[0] !== undefined) {
        filterData.push(row);
      }
      row.forEach(cell => {          
        if (j<3) {          
            if (cell !== this.headers[j]) {
              check += 1;
          } 
          j++;        
        }
      });
    });
    this.data = filterData;
    if(check == 0 && this.data.length > 1){
      this.validFile = true;
    } else {
      this.toasterService.error("Invalid Excel Sheet.");
    }
  }

  deleteFile(id: string) {
    this.validFile = false;
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    this.clearAll();
  }

  onClickSubmit(){
    let finalData: Array<ChannelFinanceLimitUpload> = [];
    for (let i = 1; i < this.data.length; i++) {
      let row = this.data[i];
      const cfluObj = {} as ChannelFinanceLimitUpload
      const dbdObj = {} as DealerBankDetailsId     
      for (let j = 0; j < row.length; j++) {
        dbdObj.dealerCode = row[0];
        dbdObj.bankName = row[1];
        cfluObj.dealerBankDetailsId = dbdObj;
        cfluObj.flexiLoanAccountNumber = row[2];
        cfluObj.cfCreditLimit = row[3];
        cfluObj.utilisedLimit = row[4];
        cfluObj.availableAmount = cfluObj.cfCreditLimit - cfluObj.utilisedLimit;         
        }
      finalData.push(cfluObj);    
    }
    this.channelFinanceLimitUploadService.updateExcelData(finalData).subscribe(response => {     
      console.log("response is ----->", response)
      let dealerCodeStatus = response as BaseDto<DealerCodeInvalid>
      if (dealerCodeStatus.result.repeatedCode.length > 0) {
        this.toasterService.warning(dealerCodeStatus.result.repeatedCode+" dealer code repeats in excel data.")
      } 
      if (dealerCodeStatus.result.notFoundCode.length > 0) {
        this.toasterService.error(dealerCodeStatus.result.notFoundCode+" dealer code not present in database.")
      } else {
        this.toasterService.success("Data Submitted Successfully.");
      }
      
      this.validFile = false;
      this.clearAll();        
        
    }); 
  }

  clearAll() {
    this.files = []
    this.fileUploadService.deleteAllFiles()
  }
}
