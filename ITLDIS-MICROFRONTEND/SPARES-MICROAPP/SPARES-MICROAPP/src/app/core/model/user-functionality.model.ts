export interface UserFunctionality {
    functionality: string;
    routerLink: string;
    sequenceNo: number;
    parentId: number;
    children: UserFunctionality[];
    id: number;
    iconName?: string;
}

export class UserFunctionality {
    constructor(
        public functionality: string,
        public routerLink: string,
        public sequenceNo: number,
        public parentId: number,
        public children: UserFunctionality[],
        public id: number,
        public iconName?: string
    ) { }
    static mock() {
        return new UserFunctionality(
            null,
            null,
            null,
            null,
            [new UserFunctionality(null, null, null, null, [], null)],
            null
        );
    }
    static mockData(): UserFunctionality {
        return new UserFunctionality(
            'static',
            'static',
            0,
            0,
            [new UserFunctionality('static', 'static', 0, 0, [], 0)],
            0
        );
    }
}