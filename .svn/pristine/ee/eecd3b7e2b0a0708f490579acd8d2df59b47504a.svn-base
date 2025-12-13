import { Injectable } from '@angular/core'
import { Adapter, ValidateResponse, ResponseConvertor } from '../adapter'
import { SelectList } from './select-list.model'

export class AllotmentInfoByEnquiryNumber {
    constructor(
        public onlyImplementFlag: boolean,
        public customerName: string,
        public mobileNumber: string,
        public country: string,
        public state: string,
        public district: string,
        public pincode: any,
        public pinId:any,
        public postOffice: string,
        public requestNo: string,
        public allotmentType : string,
        public city: string,
        public tehsil: string,
        public address: string,
        public model: string,
        public itemNumber: string,
        public itemDescription: string,
        public machineDetails: Array<MachineDetail>
    ) { }
    static mockData(): AllotmentInfoByEnquiryNumber {
        return new AllotmentInfoByEnquiryNumber(
            false,
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            [MachineDetail.mockData()]
        )
    }
    static mock(): AllotmentInfoByEnquiryNumber {
        return new AllotmentInfoByEnquiryNumber(
            false,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            [MachineDetail.mock()]
        )
    }
}
export class AllotmentInfoByEnquiryNumberKeyMap {
    constructor(
        public onlyImplementFlag = 'onlyImplementFlag',
        public customerName = 'customerName',
        public mobileNumber = 'mobileNumber',
        
        public requestNo = 'requestNo',
        public allotmentType = 'allotmentType',

        public country = 'country',
        public state = 'state',
        public district = 'district',
        public pincode = 'pinCode',
        public pinId = 'pinId',
        public postOffice = 'postOffice',
        public city = 'city',
        public tehsil = 'tehsil',
        public address = 'address',
        public model = 'model',
        public itemNumber = 'itemNo',
        public itemDescription = 'itemDescription',
        public machineDetails = 'machineDetails') { }
}

export class MachineDetail {
    constructor(
        public stockType: string,
        public itemNo: any,
        public model: string,
        public itemDescription: string,
        public chassisNo: string,
        public engineNo: string,
        public color: string,
        public grnNo: string,
        public invoiceNo: string,
        public invoiceDate: string,
        public id: number,
        public ageInDays: number,
        public productGroup: string
    ) { }
    static mockData(): MachineDetail {
        return new MachineDetail(
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            1,
            0,
            'string'
        )
    }
    static mock(): MachineDetail {
        return new MachineDetail(
            null,    
            null,
            null,   
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
            // null
        )
    }
}
export interface ItemNoData {
    //code: string;
    //value:string;
    itemNo:string;
  }
export interface EditableTableAutocompleteList {
    config: object;
    list: Array<ItemNoData>;
    searchKey: string;
  }
export class MachineDetailKeyMap {
    constructor(
        public stockType = 'stockType',    
        public itemNo = 'itemNo',
        public chassisNo = 'chassisNo',
        public engineNo = 'engineNo',
        public color = 'color',
        public grnNo = 'grnNo',
        public invoiceNo = 'invoiceNo',
        public invoiceDate = 'invoiceDate',
        public id = 'id',
        public ageInDays = 'ageInDays',
        public productGroup = 'productGroup',
        // public allotmentNumber = 'allotmentNumber'
    ) { }
}
@Injectable({
    providedIn: 'root'
})
export class AllotmentInfoByEnquiryNumberAdaptor implements Adapter<AllotmentInfoByEnquiryNumber>{
    constructor(private responseConvertor: ResponseConvertor) { }
    adapt(item: object): AllotmentInfoByEnquiryNumber {
        return this.convertResponse(item)
    }
    private convertResponse(response: object): AllotmentInfoByEnquiryNumber {
        const keyMap = new AllotmentInfoByEnquiryNumberKeyMap();
        if (
            ValidateResponse.objectValidator(response, AllotmentInfoByEnquiryNumber.mock())
            && ValidateResponse.arrayValidator(response[keyMap.machineDetails], MachineDetail.mock())
        ) {
            return response as AllotmentInfoByEnquiryNumber;
        }
        const allotmentInfoByEnquiryNumber = AllotmentInfoByEnquiryNumber.mock();
        const newAllotmentInfoByEnquiryNumber = this.responseConvertor.transformObject<AllotmentInfoByEnquiryNumber>(response, keyMap, allotmentInfoByEnquiryNumber);
        const machineDetailKeyMap = new MachineDetailKeyMap();
        const machineDetailMock = MachineDetail.mock();
        newAllotmentInfoByEnquiryNumber.machineDetails = this.responseConvertor.transformArrayOfObject(
            newAllotmentInfoByEnquiryNumber.machineDetails,
            machineDetailKeyMap,
            machineDetailMock
        );
        return newAllotmentInfoByEnquiryNumber;
    }
    convertIntoMachineDetail(response) {
        const machineDetailKeyMap = new MachineDetailKeyMap();
        const machineDetailMock = MachineDetail.mock();
        return this.responseConvertor.transformObject<MachineDetail>(
            response,
            machineDetailKeyMap,
            machineDetailMock
        );
    }
}