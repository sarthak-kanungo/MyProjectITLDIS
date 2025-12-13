declare module 'LoginDto' {
    export interface StorageLoginUser {
        id: number;
        name: string;
        username: string;
        dealerCode: string;
        loginUserId?: number;
        token: string;
        userType:string
    }
    export interface Credentials {
        username: string;
        password: string;
    }
}