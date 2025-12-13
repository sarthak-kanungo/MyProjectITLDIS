import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class shareDataBetweenComponent {
   sharedData: any;
    sharedDatas: any;


  constructor() {
    
  }

  setData(data: any) {
    this.sharedData = data;
  }
  setDatas(data: any) {
    console.log(data,'ddddd')
    
    this.sharedDatas = data;
   
  }

  getData(): any {
    console.log(this.sharedData,'dfhjibhghghjsad')
    // return this.sharedData;
  }
  getDatas(): any {
    return this.sharedDatas;
  }  
}

