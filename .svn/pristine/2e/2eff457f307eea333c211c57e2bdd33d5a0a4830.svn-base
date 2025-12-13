import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IFrameMessageSource, IFrameService } from 'src/app/root-service/iFrame.service';
import { PCRSearchPagePresenter } from '../pcr-search-page/pcr-search-page.presenter';
import { PCRSearchPageStore } from '../pcr-search-page/pcr-search-page.store';

@Component({
  selector: 'app-service-history',
  templateUrl: './service-history.component.html',
  styleUrls: ['./service-history.component.scss'],
  providers:[PCRSearchPagePresenter, PCRSearchPageStore]
})
export class ServiceHistoryComponent implements OnInit {
  @Input() public serviceHistoryForm: FormGroup;
  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private iFrameService: IFrameService,
    private pcrSearchPagePresenter: PCRSearchPagePresenter) { }

  ngOnInit() {
  }

  jcModal(fg:FormGroup){
    const jobcardno = fg.get('jobCardNo').value;
    const status = fg.get('status').value;
    let url = ''
    if(this.pcrSearchPagePresenter.loginUser.userType==='dealer' && status==='Open')
      url = 'service/customerService/job-card/edit';
    else
      url = 'service/customerService/job-card/view';
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url, queryParam: {id:btoa(jobcardno)} });      
  }
  pcrModal(fg:FormGroup){
    const pcrno = fg.get('pcrNo').value;

    const link = this.router.serializeUrl(this.router.createUrlTree(['warranty/warranty-claim/product-concern-report/view'], {queryParams: { pcrNo : btoa(pcrno) }}));
 
    //this.router.navigate([]).then(result => {
      window.open(link,'popup','directories=0,titlebar=0,toolbar=0,location=0,status=0,menubar=0,width=600,height=400')
    //});  
  }
}
