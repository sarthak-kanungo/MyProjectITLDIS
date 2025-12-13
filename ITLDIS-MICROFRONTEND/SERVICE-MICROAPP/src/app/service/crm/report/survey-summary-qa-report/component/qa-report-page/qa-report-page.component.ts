import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { DirectSurveyService } from 'src/app/service/crm/direct-survey/direct-survey.service';
import { SurveySummaryReportService } from 'src/app/service/crm/survey-summary-report/survey-summary-report.service';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
import { AutoCompSubModel, ModelBySeries, PopulateByItemNo, Product, SeriesByProduct, Variants } from 'src/app/service/customer-service/job-card/domain/job-card-domain';
import { QaReportPagePagePresenter } from './qa-report-page.presenter';

@Component({
  selector: 'app-qa-report-page',
  templateUrl: './qa-report-page.component.html',
  styleUrls: ['./qa-report-page.component.css'],
  providers:[DirectSurveyService]
})
export class QaReportPageComponent implements OnInit {
  qaReportForm:FormGroup

  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  variants: Array<Variants> = []
  subModelByModel: Array<AutoCompSubModel> = []
  modelBySeries: Array<ModelBySeries> = []
  model: any
  questionList: any=[]
  syurveyType:any[]=[]

  max: Date | null
  serverDate: Date;
  minDate: Date;
  maxDate: Date
  newdate= new Date()


  constructor(private presenter:QaReportPagePagePresenter,
    private jcService: JobCardSearchWebService,
    private ssrService: SurveySummaryReportService,
    private directService:DirectSurveyService,) { }

  ngOnInit() {
    this.qaReportForm = this.presenter.reportForm
    this. getProduct()
    this. getQuestionList()
    this.getSurveyType()
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 1);
    this.minDate = backDate;
    this.qaReportForm.get('fromDate').patchValue(backDate);
    this.qaReportForm.get('toDate').patchValue(new Date());

    this.ssrService.clearFormSubject.subscribe(response => {
      this.seriesByProduct = [];
      this.subModelByModel = [];
      this.modelBySeries = [];
      this.variants = [];
      this.products=[];
    });
  }
  
  getProduct(){
    this.jcService.getAllProduct().subscribe(response => {
      this.products = response.result
    })
  }
  selectProduct(value) {
    this.qaReportForm.controls.series.reset();
    this.qaReportForm.controls.model.reset();
    this.qaReportForm.controls.subModel.reset();
    this.qaReportForm.controls.variant.reset();
    this.seriesByProduct = [];
    this.modelBySeries = [];
    this.subModelByModel = [];
    this.variants = [];
    this.jcService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response.result
    })
  }
  selectSeries(value) {
    this.qaReportForm.controls.model.reset();
    this.qaReportForm.controls.subModel.reset();
    this.qaReportForm.controls.variant.reset();
    this.modelBySeries = [];
    this.subModelByModel = [];
    this.variants = [];
    this.jcService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response.result
    })
  }

  selectModel(value) {
    this.subModelByModel = [];
    this.variants = [];
    this.qaReportForm.controls.subModel.reset();
    this.qaReportForm.controls.variant.reset();
    this.model = value;
    this.jcService.getSubModel(value).subscribe(response => {
      this.subModelByModel = response.result
    })
  }

  selectSubModel(value) {
    this.qaReportForm.controls.variant.reset();
    this.variants = [];
    this.jcService.getVariant(this.model, value).subscribe(response => {
      this.variants = response
    })
  }

  compareFnSeries(c1: SeriesByProduct, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
    }
    return c1 && c2 ? c1.series === c2.series : c1 === c2;
  }

  compareFnModel(c1: ModelBySeries, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.model === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.model;
    }
    return c1 && c2 ? c1.model === c2.model : c1 === c2;
  }

  compareFnSubModel(c1: AutoCompSubModel, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subModel === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subModel;
    }
    return c1 && c2 ? c1.subModel === c2.subModel : c1 === c2;
  }

  getQuestionList(){
    this.ssrService.getQuestion().subscribe(response => {
      this.questionList = response['result']
    })
  }
  getSurveyType(){
    this.ssrService.getQASurveyName().subscribe(response => {
      this.syurveyType = response['result'];
    });
  }

  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.qaReportForm.get('toDate').value > this.maxDate)
        this.qaReportForm.get('toDate').patchValue(this.maxDate);
    }
  }
  


}
