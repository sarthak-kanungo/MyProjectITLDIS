import { Injectable } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { BehaviorSubject } from 'rxjs';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../confirmation-box/confirmation-box.component';
import { MenuNode, MenuObject } from '../domains/role-master';

@Injectable()
export class RoleMasterPresenter {

    menuSubject:BehaviorSubject<MenuObject> = new BehaviorSubject({menuList:[],selectedMenu:[]});
    fetchSelectedMenuSubject:BehaviorSubject<Array<number>> = new BehaviorSubject([]);

    form: FormGroup
    menuForm : FormGroup
    constructor(
        private formBuilder: FormBuilder,
        public dialog: MatDialog
    ) { }

    buildForm() {
        this.form = this.formBuilder.group({
            id:[null],
            roleCode: [''],
            roleName: ['', Validators.compose([Validators.required])],
            applicableTo: [null, Validators.compose([Validators.required])],
            activeStatus: [false],
        })
        return this.form
    }
    buildMenuForm(){
        this.menuForm = this.formBuilder.group({
            menuNode:[null]
        })
        return  this.menuForm;
    }
    buildFormSearch() {
        this.form = this.formBuilder.group({
            roleCode: [''],
            roleName: [''],
            Applicablefor: [''],
            Isactive: [''],
            page: [''],
            size: ['']
        })
        return this.form
    }

    private openConfirmDialog(): void | any {
        let message = 'Do you want to submit Role Master?';
        message = 'Do you want to update Role Master?';

        const confirmationData = this.setConfirmationModalData(message);
        const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
            width: '500px',
            panelClass: 'confirmation_modal',
            data: confirmationData
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed', result);
        });
    }

    private setConfirmationModalData(message: string) {
        const confirmationData = {} as ConfirmDialogData;
        confirmationData.buttonAction = [] as Array<ButtonAction>;
        confirmationData.title = 'Confirmation';
        confirmationData.message = message;
        confirmationData.buttonName = ['Confirm', 'Cancel'];
        return confirmationData;
    }

    getRoleName() {
        return this.form.get('roleName').value
    }

    getFormData() {
        return this.form.value
    }

    getApplicableTo() {
        return this.form.get('applicableTo').value
    }
}