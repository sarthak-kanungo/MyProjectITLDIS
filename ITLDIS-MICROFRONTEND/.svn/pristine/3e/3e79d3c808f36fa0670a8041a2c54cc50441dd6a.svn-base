// export interface Tab {
//     id: number;
//     tabName: string;
//     functionalityMasters: FunctionalityMaster[];
// }

// export interface FunctionalityMaster {
//     id: number;
//     accessibleFlag?: boolean;
//     assignedInRole?: any;
//     module?: string;
//     functionality: string;
//     isSpoiled?: boolean
// }
export interface MenuObject{
  menuList:Array<MenuNode>
  selectedMenu:Array<number>
}
export interface RoleMaster {
    activeStatus: string;
    applicableTo: string;
    roleName: string;
    roleCode: string;
    id?:number;
}

export interface Role {
    functionalityMasters: [];
    roleMaster: RoleMaster;
}

export interface RoleValues {
    roleCode: any;
    roleName: any;
    displayString:any;
    id:any;
}
export interface SearchRoleMaster {
    roleCode: string;
    roleName: string;
    page: number;
    size: number;
    isActive:string;
    applicableFor:string;
  }
  export interface MenuNode {
    element: Menu;
    children?: MenuNode[];
  }
  
  export interface ExampleFlatNode {
    expandable: boolean;
    name: string;
    parentId ?: number;
    id: number;
    level: number;
  }
  export interface Menu {
    id: number;
    parentId?: number;
    description:string
  }

