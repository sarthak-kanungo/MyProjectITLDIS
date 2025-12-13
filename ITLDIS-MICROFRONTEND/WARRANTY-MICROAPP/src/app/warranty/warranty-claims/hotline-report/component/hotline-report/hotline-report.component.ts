import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { hotlineReport } from './hotline-service';
import { ToastrService } from 'ngx-toastr';
import { HotlineReportPageStore } from '../hotline-report-create-page/hotline-report-create-page.store';
import { HotlineReportPagePresenter } from '../hotline-report-create-page/hotline-report-create-page.presenter';
import { PcrWebService } from '../../../product-concern-report/component/pcr/pcr-web.service';
import { Subscription } from 'rxjs';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotline-report',
  templateUrl: './hotline-report.component.html',
  styleUrls: ['./hotline-report.component.scss'],
  providers: [hotlineReport, HotlineReportPageStore, HotlineReportPagePresenter, PcrWebService,]
})

export class HotlineReportComponent implements OnInit {

  @Input() hotlineReportForm: FormGroup;
 maxDate=new Date();
  depotList: any[];
  failureCodeList: any[];
  planList: any;
  departMentList: any
  failureType: any
  obs: Subscription;
  id: any;
  inchargeList: any;
  isView: boolean;
  isEdit: boolean

  constructor(private service: hotlineReport, private toaster: ToastrService, private store: HotlineReportPageStore, private presenter: HotlineReportPagePresenter, private pcrService: PcrWebService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.getAllAPI()
    this.checkFormOperation()
    this.inchargeList = null
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
    }
    if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true;
    }
  }
  private getAllAPI() {
    this.getDepot()
    this.getFailureCode()
    this.getPlanList()
    this.getDepartmentList()
    // this.dropDownFailureType()
    this.getFailureList()
  }
  private getDepot() {
    this.obs = this.service.dealerDepoList().subscribe(response => {
      this.depotList = response;
    })
  }
  private getFailureCode() {
    this.obs = this.service.failureCodeList().subscribe(response => {
      this.failureCodeList = response;
    })
  }
  private getPlanList() {
    this.obs = this.service.hotlinPlanList().subscribe(response => {
      this.planList = response;
    })
  }
  private getDepartmentList() {

    this.obs = this.service.departmentList().subscribe(response => {
      this.departMentList = response;

    })
  }
  departmentIncharge(id: any) {

    this.obs = this.service.getDepartmentInchargeByDepartment(id.id).subscribe(data => {
      this.inchargeList = data['result']

    })
  }
  private getFailureList() {
    this.obs = this.service.getFailureType().subscribe(result => {
      this.failureType = result;
    }, err => {
      console.log('err', err);
    });

  }
  ngOnDestroy() {

    // this.obs.unsubscribe();
  }
}
