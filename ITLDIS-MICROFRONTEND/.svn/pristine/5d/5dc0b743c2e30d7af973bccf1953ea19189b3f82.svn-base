import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { of } from 'rxjs';
import { AbstractControl } from '@angular/forms';

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
}
