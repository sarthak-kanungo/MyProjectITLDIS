export interface LabourChargesItem {
	id?: number;
	isSelected?: boolean;
	frsCode?: string;
	description?: string;
	hour?: string;
	amount?: number;
	category?:categoryType;
}
export interface FrsCodeAutoCompleteList {
	code?: string;
	value?: string;
	id?: number;
	frsCode?: string;
}
export interface categoryType{
    category:string,
    id:number
}
