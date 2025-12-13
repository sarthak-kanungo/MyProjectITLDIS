export interface KubotaUserMasterDomain { 
    id: number 
    roleid: number
    role_name: string
    role_code: string
    assigned:string
}

 
export interface EmployeeIdAuto{}
export interface EmployeeNameAuto{}

export interface EmployeeSearchFilter{
    employeeCode: string
    employeeName: string
    page: number
    size: number
}

export interface EmployeeSearchList{
    id: number
    employeeCode: string
    employeeName: string
    loginIdStatus: string
    employeeStatus: string
}


export interface EmployeeIdAutoCreate{
    employeecode: string,
    employee_name: string,
    displayValue: string
}

export interface LoginIdStatusDropDown{}

export interface AssignRoleDropDown{
    id: number,
    Rolecode: string,
    Role_name: string,
    displayValue: string
}

export interface SubmitDto{
    loginUser: LoginUser,
    mapping: Mapping[]
}

export interface LoginUser{
    id: number;
    userTypeId: number ;   
    userName: string ;
    password: string ;
    loginIdStatus: string ; 
    kubotaEmployeeId: number ;
    confirmPassword: string ;
}
export interface Mapping{
    loginUserId:number
    roleId: number
    isactive:string
}

