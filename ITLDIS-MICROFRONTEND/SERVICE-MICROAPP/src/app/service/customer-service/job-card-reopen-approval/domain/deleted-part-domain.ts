export interface DeletedSpareParts {
    uuid: string;
    id?: any;
    sparePartId?: any;
    isSelected: boolean;
    category: Category;
    partNumber: PartNumber;
    quantity?: any;
}

export interface PartNumber {
    id: number;
    itemNo: string;
    partNumber: string;
    value: string;
}

export interface Category {
    category: string;
    id: number;
}