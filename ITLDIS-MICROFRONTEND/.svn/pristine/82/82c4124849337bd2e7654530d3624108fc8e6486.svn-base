import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { TransportServiceService } from '../service/transport-service.service';
import { machineTransportPresenter } from './machine-transport-presenter';
import { machineTransportStore } from './machine-transport-store';


@Component({
  selector: 'app-create-machine-transport',
  templateUrl: './create-machine-transport.component.html',
  styleUrls: ['./create-machine-transport.component.css'],
    providers :[machineTransportPresenter,machineTransportStore]
})
export class CreateMachineTransportComponent implements OnInit {

  modeDeliveryList = [
    { id: 1, name: "Transport" },
    { id: 2, name: "Self Run" },
    {id:3,name:"Self Truck"}
  
  ];
  transportDropdown:any
 
  addrow:boolean=false
  createMachineHeaderForm:FormGroup
  transportForm:FormGroup
  selfTruckForm:FormGroup
  selfRunForm:FormGroup
  staticDropDown: any;
  data: any;
  constructor(private presenter:machineTransportPresenter,private store:machineTransportStore,private toaster:ToastrService,private service:TransportServiceService) { 
    
  }
  ngOnInit(): void {
    
  }

  // ngOnInit() {
  //   this.createMachineHeaderForm=this.presenter.headerForm;
   
  //   this.transportForm=this.presenter.transportForm;
  //     this.selfRunForm=this.presenter.selfRunForm;
     
  //    this.selfTruckForm=this.presenter.selfTruckForm

  //    this.service.getTransportList().subscribe(res => {
  //     if(res){
  //       this.transportDropdown = res['result'];
  //       this.staticDropDown=this.transportDropdown
  //       this.staticDropDown.forEach(res=>{
  //        this.data=res.typeOfDelivery
  //        if(this.data==='Ex Works Delivery'){
  //         console.log('calla')
  //        }else{
  //         console.log('no')
  //        }

  //       })
  //       console.log(this.staticDropDown.typeOfDelivery,'dropdown')
  //      console.log(this.transportDropdown,'aaaaa')
  //     }
      
  //   }, error => {
  //     console.log("DropDown List get Not fetch successfully");
  //   })
  //   this.createMachineHeaderForm.get("typeOfDelivery").setValue("Ex Works Delivery")
     
  //        this.createMachineHeaderForm.get("typeOfDelivery").valueChanges
  //   .subscribe(f=> {
  //     this.onCountryChanged(f);
  // })

    
  // this.createMachineHeaderForm.get("modeDelivery").valueChanges.subscribe(res=>{
  //  this.onModeChange(res)
  // })
    
  // }
  transportAdd:boolean=false
  selfRun:boolean=false
  selfTruck:boolean=false
  onModeChange(value){
    console.log(value)
   if(value==='Transport'){
    this.transportAdd=true
   }else{
    this.transportAdd=false
   }
   if(value==='Self Run'){
    this.selfRun=true
   }else{
    this.selfRun=false
   }
   if(value==='Self Truck'){
    this.selfTruck=true
   }else{
    this.selfTruck=false
   }
  }

  onCountryChanged(value) {
    console.log('onCountryChanged')
    console.log(value)
    if(value==='Ex Works Delivery'){
      //  this.addrow=true
      this.toaster.error("Ready For Approval")
      return false
    }else{
      // this.addrow=false
    }
  }
  
  
 

}
