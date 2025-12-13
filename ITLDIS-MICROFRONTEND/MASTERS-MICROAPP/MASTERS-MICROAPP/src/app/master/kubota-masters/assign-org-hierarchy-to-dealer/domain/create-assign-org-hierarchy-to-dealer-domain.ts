// export interface DealerDetailsForNewUser {
//     id: number,
//     code?: string,
//     displayString:string
// }

// export interface DealerEmployeeCodeForNewUser {
//     id: number
//     employeeName?: string,
//     usercode:string,
//     value:string
// }
export interface FieldLevel {
    LEVEL_ID: number
    LEVEL_CODE?: string,
    LEVEL_DESC: string,
    SEQ_NO: string
}
export interface LevelHierarchy {
    org_hierarchy_id: number
    level_id?: number,
    hierarchy_code: string,
    hierarchy_desc: string,
    parent_org_hierarchy_id?: number
}
export interface DepartmentMasters {
    id: number
}
export interface DropDownDepartments {
    departmentName: string,
    id: number
}
export interface DealerDetails {
    id: number,
    code?: string,
    displayString: string
}


export interface SubmitAssignOrgHierarchyToDealerDto {
    dealerId: any
    hoDepartmentId: any
    orgHierarchyId: any
    dealerHoDepartmentId: any
}
export interface DealerHoDepartment {
    dealerId: any
    hoDepartmentId: any
}

export interface ResponseAssignOrgHierarchyToDealerDto {
    id: any
    dealerCode: any
    dealerName: any
    departmentName: any
    hierarchyCode: any
    hierarchyDesc: any
    department: any

}
