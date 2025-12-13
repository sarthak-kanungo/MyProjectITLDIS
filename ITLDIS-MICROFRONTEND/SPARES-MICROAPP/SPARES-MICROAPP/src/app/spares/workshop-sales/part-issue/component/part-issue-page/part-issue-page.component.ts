import { Component, OnInit } from '@angular/core';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { ObjectUtil } from '../../../../../utils/object-util';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { PartIssuePagePresenter } from './part-issue-page-presenter';
import { PartIssuePageApiService } from './part-issue-page-api.service';
import { ToastrService } from 'ngx-toastr';
import { PartIssueForm, PartIssueItem, PartIssue } from '../../domain/part-issue.domain';
import { FormArray,FormGroup } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable } from 'rxjs';
import { PartIssuePageStore } from './part-issue-page-store';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-part-issue-page',
  templateUrl: './part-issue-page.component.html',
  styles: [``],
  providers: [PartIssuePageApiService, PartIssuePagePresenter, PartIssuePageStore]
})
export class PartIssuePageComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  partIssueStatus: string;
  advancedSparePartIssue: SelectList;
  partRequisition: any;
  isSubmitDisable:boolean = false;
  constructor(
    public dialog: MatDialog,
    public presenter: PartIssuePagePresenter,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private partIssuePageApiService: PartIssuePageApiService
  ) { }

  ngOnInit() {
    this.checkIsGrnForEditOrView();
  }
  private checkIsGrnForEditOrView() {
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('viewId')) {
        this.isView = true;
        this.getPartIssueById(parseInt(atob(param.get('viewId'))));
        this.presenter.partIssuePageForm.disable();
        //this.itemDetailTableConfig = new ItemDetailTableConfig(this.presenter.createItemDetailsTableRowFn(true));
        return;
      }
      if (param.has('updateId')) {
        this.isEdit = true;
        // this.presenter.partIssueForm.get('id').patchValue(param.get('updateId'));
        this.getPartIssueById(parseInt(atob(param.get('updateId'))));
        //this.itemDetailTableConfig = new ItemDetailTableConfig(this.presenter.createItemDetailsTableRowFn());
        return;
      }
      //this.itemDetailTableConfig = new ItemDetailTableConfig(this.presenter.createItemDetailsTableRowFn());
    })
  }
  getPartIssueById(partIssueById: number) {
    this.partIssuePageApiService.getPartIssueById(partIssueById).subscribe(partIssueRes=>{
      
       this.presenter.patchValueToPartIssue(partIssueRes['partRequisition']);
       this.partRequisition  = partIssueRes['partRequisition'];
       this.presenter.partIssueForm.get('issueTo').patchValue({'value':partIssueRes['partRequisition']['issueTo'],'id':partIssueRes['partRequisition']['issueToId']})
       partIssueRes['partRequisitionItem'].forEach(issueItem => {
           this.presenter.addRow(issueItem);
       });
       
       /*if (this.isView) {
         this.presenter.partIssuePageForm.disable();
       }*/
    })
  }
  submitPartIssue() {
    this.presenter.partIssueForm.get('partIssueStatus').patchValue('Submitted');
    this.validateForm();
  }
  savePartIssue() {
    this.presenter.partIssueForm.get('partIssueStatus').patchValue('Draft');
    this.validateForm();
  }
  validateForm() {
    const valid = this.presenter.validate();
    if (valid) {
      this.openConfirmDialog();
    }
  }

  submitData() {
    const partIssueRow = this.presenter.partIssuePageForm.getRawValue() as PartIssueForm;
    let items = partIssueRow.partIssueItem as PartIssueItem[];
    if(partIssueRow.partIssue['advancedSparePartIssue']){
        items.forEach(item => {
            item.advancedSparePartIssue = {'id':partIssueRow.partIssue['advancedSparePartIssue']['id']}
        })
    }
    const partIssue = {
      ...partIssueRow.partIssue,
      sparePartIssueItems: items 
    }
    ObjectUtil.removeNulls(partIssue);
    this.partIssuePageApiService.saveSparePartIssue((partIssue as any)).subscribe(res => {
      if(res){
          if (this.isEdit) {
            this.toastr.success('Part issue Updated Successfully', 'Success');
            this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
            return;
          }
          this.toastr.success('Part issue Saved Successfully', 'Success');
          this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        }else{
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving','Transaction failed');
        }
      }, err => {
        this.isSubmitDisable = false;
        if (this.isEdit) {
          this.toastr.error('Part issue Update has Error', 'Error');
          return;
        }
        this.toastr.error('Part issue Save has Error', 'Error');
      });
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Part Issue?';
    if (this.isEdit) {
      message = 'Do you want to update Part Issue?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  clearAllSubForm() {
    this.presenter.reset()
    // this.grnForm.updateValueAndValidity();
  }
  back() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }
  viewPdf(printStatus:string){
      this.partIssuePageApiService.printPO(this.presenter.partIssueForm.get('partIssueNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
