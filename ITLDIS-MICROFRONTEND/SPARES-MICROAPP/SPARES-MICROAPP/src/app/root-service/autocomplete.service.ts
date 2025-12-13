import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { of, Observable } from 'rxjs';
import { AbstractControl } from '@angular/forms';
import { SelectList } from '../core/model/select-list.model';

@Injectable({
  providedIn: 'root'
})
export class AutocompleteService {

  constructor(
    private httpClient: HttpClient) { }

  private getDataForAutocomplete(params: HttpParams, searchBySerialNumberUrl) {
    return this.httpClient.get(searchBySerialNumberUrl, { params }).pipe(map(res => res['result']));
  }

  public resetFormControlNotHavingObject(formControl: AbstractControl, keyForPatchValue?: string) {
    if (typeof formControl.value !== 'object') {
      formControl.reset();
      return;
    }
    if (!!keyForPatchValue && !!formControl.value && !!formControl.value && typeof formControl.value === 'object') {
      formControl.patchValue(formControl.value[keyForPatchValue]);
    }
  }
  validateSelectedFromList(value: string, list: Observable<SelectList[]>, formControl: AbstractControl) {
    console.log('list: ', list);
    if (this.isRemoveSelectedFromListError(value)) {
      this.removeSelectedFromListError(formControl);
      return;
    }
    if (!list) {
      formControl.setErrors({
        selectFromList: 'Please select from list',
      });
      return null;
    }
    list.toPromise().then(val => {
      console.log('val: ', val);
      const validSelection = val.some((listNode, index) => {
        console.log('value, listNode: ', value, listNode);
        const reducer = d => (d && typeof d === 'object') ? d['value'] : d;
        if (reducer(listNode) === reducer(value)) {
          return true;
        }
      });
      if (!validSelection) {
        formControl.setErrors({
          selectFromList: 'Please select from list',
        });
        return;
      }
      this.removeSelectedFromListError(formControl);
    }).catch((error) => {
      formControl.setErrors({
        selectFromList: 'Please select from list',
      })
    });
  }
  removeSelectedFromListError(formControl: AbstractControl) {
    const error = { ...formControl.errors };
    console.log('error: ', error);
    console.log('formControl.errors: ', formControl.errors);
    if (error && error.selectFromList) {
      delete error.selectFromList;
    }
    if (Object.keys(error).length) {
      formControl.setErrors(error);
      return;
    }
    formControl.setErrors(null);
  }
  private isRemoveSelectedFromListError(val: any) {
    if (!val) {
      return true;
    }
  }
}
