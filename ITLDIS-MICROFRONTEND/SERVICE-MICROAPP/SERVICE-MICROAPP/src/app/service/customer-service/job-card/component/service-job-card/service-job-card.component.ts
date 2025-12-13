import { AfterViewChecked, AfterViewInit, Component, Input, OnChanges, OnInit, } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { JobCardPresenter } from '../create-job-card-page/job-card-presenter';
import { JobServiceCardWebService } from './job-service-card-web.service';
import { MechanicName } from '../../domain/job-card-domain';
import { SelectList } from '../../../../../core/model/select-list.model';

@Component({
  selector: 'app-service-job-card',
  templateUrl: './service-job-card.component.html',
  styleUrls: ['./service-job-card.component.scss'],
  providers: [JobServiceCardWebService]
})
export class ServiceJobCardComponent implements OnInit, AfterViewChecked {
  mechanicNames: MechanicName
  serviceJobCardForm: FormGroup
  @Input()
  isView:boolean
  @Input()
  isEdit:boolean
  @Input()
  isCreate:boolean
  constructor(
    private presenter: JobCardPresenter,
    private jobServiceCardWebService: JobServiceCardWebService,
  ) { }
  ngAfterViewChecked(): void {
    
    if(this.isEdit){
      if(this.serviceJobCardForm.get('mechanicName').value){
        this.serviceJobCardForm.get('mechanicName').disable();
      }
      else{
        this.serviceJobCardForm.get('mechanicName').enable();
      }
      if(this.serviceJobCardForm.get('serviceJobCardEditable').value){
        this.serviceJobCardForm.get('serviceJobCardEditable').disable();
      }else{
        this.serviceJobCardForm.get('serviceJobCardEditable').enable();
      }
    }
  }

  ngOnInit() {
    this.serviceJobCardForm = this.presenter.jobServiceCard

    this.dropDownMechanicNameService()

  }
  dropDownMechanicNameService() {
    this.jobServiceCardWebService.dropDownMechanicName().subscribe(res => {
      this.mechanicNames = res
    })
  }


  compareFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.value === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.value;
    }
    return c1 && c2 ? c1.value === c2.value : c1 === c2;
  }
}
