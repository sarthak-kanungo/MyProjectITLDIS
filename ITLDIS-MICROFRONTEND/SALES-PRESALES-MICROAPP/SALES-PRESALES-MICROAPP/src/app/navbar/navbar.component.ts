import { Component, OnInit} from '@angular/core';
import { IFrameMessageSource, IFrameService } from '../root-service/iFrame.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
 
  constructor(
   private iframe: IFrameService
  ) {
  }
  ngOnInit() {
   
  }

  dashboard(){
    //this.router.navigate(['/dashboard'])
    const url = 'dashboard'
    this.iframe.sendRouteChangeRequest(IFrameMessageSource.DASHBOARD, {url});
  }


}
