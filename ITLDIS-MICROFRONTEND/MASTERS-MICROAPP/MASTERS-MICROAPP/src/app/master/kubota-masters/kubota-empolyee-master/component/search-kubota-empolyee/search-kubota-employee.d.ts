export interface autoEmployeeCode {
    employeeCode: string
}
export interface autoEmployeeName {
    employeeName: string
}
export interface SearchEmpMaster {
    employeeCode: string,
    employeeName: string,
    page: number,
    size: number,
}

export interface orgHierData {
    department:orgHierDept[]
    level:orgHierLevel[]
}
export interface orgHierDept {
    dept: any
}

export interface orgHierLevel {
    levelCtrl: any
}

export interface orgHierDeptSales {
    department:string
    HO: string
    ZONE:string
    REGION: string
    AREA:string
    TERRITORY: string
}
export interface orgHierDeptMarketing {
    department:string
    HOD_MARKETING: any
    AM:string
    EXECUTIVE: string
}
export interface orgHierDeptService {
    department:string
    HO_SERVICE: string
    ZONE:string
    AREA: string
    TERRITORY: string
}
export interface orgHierDeptSpare{
    department:string
    HO_SPARES: string
    REGION:string
    TERRITORY: string
}

export interface orgHierJson{
    department:string
    hierarchyDesc: string
    orgHierarchyId:string
}
export interface orgHierDepartment {
    id: number
    departmentName?: string,
}



