import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { machineInventoryService } from '../machine-inventory-service';
import { MatDatepickerInput } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
@Component({
  selector: 'app-machine-inventory-report',
  templateUrl: './machine-inventory-report.component.html',
  styleUrls: ['./machine-inventory-report.component.css'],
})
export class MachineInventoryReportComponent implements OnInit {
  searchMachineInventoryForm:FormGroup
  disableFutureDate=new Date();
  minDate: Date;
  today =new Date();
  tomorrow=new Date()
  maxDate: Date
  pageTitle="Download Machine Inventory Report";
  constructor(
    private fb:FormBuilder,
    private service:machineInventoryService,
    private toaster:ToastrService
    ) { }

  ngOnInit() {
    this.searchMachineInventoryForm=this.fb.group({
      fromDate:[{value:null,disabled:false}],
      toDate:[{value:null,disabled:false}]
    })
  }

  downloadMachineInventoryReport(){
    // this.service.downloadMachineInventoryRe
    const data=this.searchMachineInventoryForm.getRawValue()
     data.fromDate = data.fromDate ? ObjectUtil.convertDate(data.fromDate) : null
     data.toDate = data.toDate ? ObjectUtil.convertDate(data.toDate) : null
    // if(this.searchMachineInventoryForm.controls.fromDate.value==null ||this.searchMachineInventoryForm.controls.toDate.value==null){
    //     this.toaster.error("Please Select Date First!")
    //     return false;
    // }else{
      this.service.downloadInventoryReport(data).subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
         
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
          saveAs(file);
        }
        else{
          this.toaster.error("Some Server Side Error")
        }
      })
    // }
  }
  setToDate(event: MatDatepickerInput<Date>) {
    console.log(event)
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      console.log(this.minDate)
      let maxDate = new Date(event['value']);
      console.log(maxDate,'s')
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchMachineInventoryForm.get('toDate').value > this.maxDate)
        this.searchMachineInventoryForm.get('toDate').patchValue(this.maxDate);
    }
  }
  clear(){
    this.searchMachineInventoryForm.reset()
  }
}
