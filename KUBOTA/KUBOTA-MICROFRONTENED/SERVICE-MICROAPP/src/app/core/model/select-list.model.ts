import { Injectable } from '@angular/core';
import { Adapter, ValidateResponse, ResponseConvertor } from '../adapter';

export interface SelectList {
    id: number;
    value: any;
    code?: any;
}
export interface SelectListKeyMap {
    id: string;
    value: string;
    code?: string;
}

export class SelectList {

    constructor(
        public id: number,
        public value: any,
        public code?: any
    ) { }
    static mock() {
        return new SelectList(null, null);
    }
    static mockData(): SelectList {
        return new SelectList(1, 'static');
    }
}

@Injectable({
    providedIn: 'root'
})
export class SelectListAdapter implements Adapter<Array<SelectList>> {
    constructor(private responseConvertor: ResponseConvertor) { }
    adapt(item: Array<Object>, keyMap) {
        return this.convertResponse(item, keyMap);
    }
    private convertResponse(response: Array<Object>, keyMap?: SelectListKeyMap): Array<SelectList> {
        if (response.length <= 0) {
            return []
        }
        if (ValidateResponse.objectValidator(response[0], SelectList.mock())) {
            return response as Array<SelectList>;
        }
        return response.map(res => this.responseConvertor.transformObject<SelectList>(res, keyMap, SelectList.mock()));
        // return response.map(res => new SelectList(res[keyMap.id], res[keyMap.value]));
    }
}

