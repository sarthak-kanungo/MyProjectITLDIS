import { Injectable } from '@angular/core';

export interface Adapter<T> {
    adapt<R>(item: any, keyMap?: any): T | R;
    saveAdapt?<R = unknown, S = unknown>(record: any | R): T | S;
    saveAdapt?<R = unknown>(record: any): T | R;
}
export interface ValidateResponse {
    objectValidator<T>(item: T): boolean;
    arrayValidator<T>(item: Array<T>): boolean;
}
export class ValidateResponse implements ValidateResponse {
    static objectValidator<I, T>(item: I, mock: T): boolean {
        let mockProperies = Object.keys(mock);
        let itemMap = new Map(Object.entries(item));
        let validKeyCount = 0;
        mockProperies.forEach(key => {
            if (itemMap.has(key)) {
                validKeyCount += 1;
            }
        });
        if (mockProperies.length === validKeyCount) {
            return true;
        }
        return false;
    }
    static arrayValidator<I, T>(item: Array<T>, mock: T): boolean {
        return ValidateResponse.objectValidator(item[0], mock);
    }
}

@Injectable({
    providedIn: 'root'
})
export class ResponseConvertor {
    transformArrayOfObject<T, KM>(response: Array<object>, keyMap?: object, mock?: object): Array<T> {
        return response.map(res => this.transformObject(res, keyMap, mock));
    }
    transformObject<R>(response: object, keyMap?: object, mock: object = {}): R {
        let newMock = Object.create(mock);
        for (const key in keyMap) {
            if (keyMap.hasOwnProperty(key)) {
                const resKey = keyMap[key];
                newMock[key] = response[resKey]
            }
        }
        return newMock as R;
    }
}