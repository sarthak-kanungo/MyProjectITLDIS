import { Injectable } from '@angular/core'
import { Adapter, ValidateResponse, ResponseConvertor } from '../adapter'
import { SelectList } from './select-list.model'

export class AllotmentInfoByEnquiryNumber {
    constructor(
        public customerName: string,
        public mobileNumber: string,
        public address: string,
        public pincode: string,
        public postOffice: string,
        public city: string,
        public tehsil: string,
        public model: string,
        public itemNumber: string,
        public itemDescription: string,
        public machineDetails: Array<MachineDetail>
    ) { }
    static mockData(): AllotmentInfoByEnquiryNumber {
        return new AllotmentInfoByEnquiryNumber(
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
        public customerName = 'customerName',
        public mobileNumber = 'mobileNumber',
        public pincode = 'pinCode',
        public postOffice = 'postOffice',
        public city = 'city',
        public tehsil = 'tehsil',
        public model = 'model',
        public address = 'address',
        public itemNumber = 'itemNo',
        public itemDescription = 'itemDescription',
        public machineDetails = 'machineDetails') { }
}

export class MachineDetail {
    constructor(
        public chassisNo: string,
        public engineNo: string,
        public color: string,
        public grnNo: string,
        public invoiceNo: string,
        public invoiceDate: string,
        public id: number,
        public ageInDays: number,
        public productGroup: string,
        // public allotmentNumber: string
    ) { }
    static mockData(): MachineDetail {
        return new MachineDetail(
            'string',
            'string',
            'string',
            'string',
            'string',
            'string',
            1,
            0,
            'string'
            // SelectList.mock(),
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
            null
            // null
        )
    }
}
export class MachineDetailKeyMap {
    constructor(
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