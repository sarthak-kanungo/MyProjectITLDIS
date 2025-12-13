import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray } from '@angular/forms';
import { JobCardDetailWebService } from '../job-card-details/job-card-detail-web.service';

@Component({
  selector: 'app-retrofit-ment-page',
  templateUrl: './retrofit-ment-page.component.html',
  styleUrls: ['./retrofit-ment-page.component.css'],
  providers: [JobCardDetailWebService]
})
export class RetrofitMentPageComponent implements OnInit {
  showRetroDetails: boolean = true
  @Input() retroList: [];
  @Input() data: any
  @Output() retroDetails = new EventEmitter()
  @Input() vinId: any
  @Output() retroLabourDetails = new EventEmitter()
  @Output() warrantyObjects = new EventEmitter()
  @Input() warrantydata
  retroDetails1: any = [];
  id: any;
  labourId: any;
  jobcardRetroMappings: any = [];
  retroLabourDetails1: any = [];
  disabledCheckbox: any;
  abc:any
  @Input() patchRetroCheckbox

  constructor(private service: JobCardDetailWebService) { }
  OnChanges() {
console.log(this.retroList,'warrantydata')
// console.log(this.warrantydata)
// console.log(this.data,'data')
  }
  ngOnInit() {
    // console.log(this.data,'data')
    // console.log('mainnn object',this.warrantydata)
    // this.abc=this.warrantydata['warrantyRetrofitmentCampaign']
    // console.log(this.abc)
// console.log(this.warrantydata['warrantyRetrofitmentCampaign'],'7777777777777777777777')
    this.jobcardRetroMappings = []

  }
  getRetroDetails(event: any, id: any, selcetdObject: any,index) {
   debugger
    const data=index
    if(data){
      this.disabledCheckbox=true
    }else{
      this.disabledCheckbox=false
    }
    if(event)
    this.jobcardRetroMappings.push({ 'id': null, 'warrantyRetrofitmentCampaign': selcetdObject });
    this.warrantyObjects.emit(this.jobcardRetroMappings)
    if (event.checked === true) {
      this.getRetro(id)

    }
    else {

      for (let i = 0; i < this.retroDetails1.length; i++) {
        if (this.retroDetails1[i].retroId == id) {
          this.retroDetails1.splice(i, 2);
        }
      }

      for (let j = 0; j < this.retroLabourDetails1.length; j++) {
        // console.log(this.retroLabourDetails1[j].retroId,'ffffffffffffffff')
        if (this.retroLabourDetails1[j].retroId == id) {
          // console.log(console.log('delete ',this.retroLabourDetails[j].id))
          this.retroLabourDetails1.splice(j, 2)
        }
      }

      // this.showRetroDetails=false
    }

  }
  getRetro(id: any) {
    if (id) {
      this.service.getRetroItemAndLabourDetailsById(id).subscribe(res => {

        this.retroDetails1.push(...res.retroItemDetails);
        this.retroDetails.emit(this.retroDetails1)
        if (res.retroLabourInfoDetails) {
          this.retroLabourDetails1.push(...res.retroLabourInfoDetails)
          this.retroLabourDetails.emit(this.retroLabourDetails1)
        }

      })

    }

  }
}
