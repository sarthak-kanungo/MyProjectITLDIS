export interface SubmitDto { }
export interface DepartmentNameDropdown { }
export interface DepartmentCodeAuto { 
    code:string
    displayString:string
    id:number
    name:string
}
export interface DepartmentNameCreateDropdown { }

export interface DepartmentCodeAndName { 
    code:string
    displayString:string
    id:number
    name:string
}

export interface ActionOnTableRecordForDealerDept {
    record: object;
    btnAction: string;
    tableName: string;
    recordIndex?: number;
  }
