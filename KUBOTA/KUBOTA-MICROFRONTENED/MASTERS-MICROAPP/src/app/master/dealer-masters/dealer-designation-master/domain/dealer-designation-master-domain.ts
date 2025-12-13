export interface DeptDropdownForDesignation{

    department_code:string
    department_name:string
    id:number
}


export interface SearchDepartmentAuto { }

export interface DesignationAuto { 
    code:string
    displayString:string
    id:number
    name:string
}

export interface ActionOnTableRecordForDealerDesignation {
    record: object;
    btnAction: string;
    tableName: string;
    recordIndex?: number;
  }