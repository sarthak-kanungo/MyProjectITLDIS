import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ActualReportCreatePageService } from './actual-report-create-page.service';
import { ActivatedRoute } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { ActivityReportCreateService } from '../../component/activity-report-create/activity-report-create.service';
@Component({
  selector: 'app-actual-report-create',
  templateUrl: './actual-report-create.component.html',
  styleUrls: ['./actual-report-create.component.scss'],
  providers : [ActivityReportCreateService]
})
export class ActualReportCreateComponent implements OnInit, OnDestroy {
  activityNumber:string
  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;
  statuss: string[] = [
    'Claim Submission Pending', 'Claimed',
  ];
  isSubmitDisable:boolean = false;
  ngUnsubscribe$: Subject<any> = new Subject();

  activityTypes: string[] = [
    'itldis Challange', 'Demo', 'Feel the Difference', 'Big Elephant Small Elephant',
    'Exp/ Exhibition', 'Newspaper', 'Magazine', 'Hoarding', 'Bus Branding', 'Digital Media',
    'Van Campaign', 'Combing', 'Burst Team'
  ];

  constructor(
    public dialog: MatDialog,
    private actRt: ActivatedRoute,
    private pageService: ActualReportCreatePageService,
    private activityReportCreateService: ActivityReportCreateService
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.patchOrCreate()
    this.pageService.btnClick.pipe(takeUntil(this.ngUnsubscribe$)).subscribe(which => {
      if (which == 3) {
        console.log('Three Clicked')
        this.openConfirmDialog();
      }
    })
  }
  
  onClickSubmit() {
    this.pageService.btnClick.emit(1)
  }

  clearData() {
    this.pageService.btnClick.emit(4)
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Submit Activity Report?';
    if (this.isEdit) {
      message = 'Do you want to update  Activity Report?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true
        this.pageService.btnClick.emit(2)
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  private checkOperationType() {
    console.log(this.actRt.snapshot.routeConfig.path)
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    console.log(`Edit = ${this.isEdit} View = ${this.isView}`)
  }

  

  private patchOrCreate() {
    if (this.isView) {
      console.log(`Viweing Form`)
      this.isView = true;
      console.log("this.isView ", this.isView);
    }
    else if (this.isEdit) {
      console.log(`Editing Form`)
    }
    else {
      console.log(`CreatingForm`)
    }
  }

  ngOnDestroy() {
    this.ngUnsubscribe$.next()
    this.ngUnsubscribe$.complete()
  }
  getActivityNumber(event){
      this.activityNumber = event;
  }
  viewPrint(printStatus:string){
      this.activityReportCreateService.printActivityReport(this.activityNumber, printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
      })
  }
}
