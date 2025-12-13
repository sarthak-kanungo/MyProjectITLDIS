import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { WpdcPagePresenter } from '../wpdc-page/wpdc-page.presenter';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';

@Component({
  selector: 'app-wpdc',
  templateUrl: './wpdc.component.html',
  styleUrls: ['./wpdc.component.scss'],
  providers: [PcrPageWebService]
})
export class WpdcComponent implements OnInit {
  @Input() wpdcForm: FormGroup;
  @Input() isCreate : boolean
  dealerCode: string;
  constructor(
    private wpdcPagePresenter: WpdcPagePresenter,
    private pcrPageWebService: PcrPageWebService,
  ) { }

  ngOnInit() {
    this.dealerCode =  this.wpdcPagePresenter.loginUser.dealerCode;
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.wpdcPagePresenter.wpdcForm.get('deliveryChallanDate').patchValue(res);
    });
  }

}
