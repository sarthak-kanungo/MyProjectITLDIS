import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup } from '@angular/forms';
import { BtBtPagePresenter } from '../btbt-page/btbt-page.presenter';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DateService } from '../../../../../root-service/date.service';

@Component({
  selector: 'app-create-bintobin-transfer',
  templateUrl: './create-bintobin-transfer.component.html',
  styleUrls: ['./create-bintobin-transfer.component.scss']
})
export class CreateBintobinTransferComponent implements OnInit {
  btBtDetailsForm: FormGroup
  
  @Input() isEdit: boolean;
  @Input() isView: boolean;

  constructor(
    private presenter: BtBtPagePresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
  ) {

  }

  ngOnInit() {
    this.btBtDetailsForm = this.presenter.btBtDetailsForm
  }

}
