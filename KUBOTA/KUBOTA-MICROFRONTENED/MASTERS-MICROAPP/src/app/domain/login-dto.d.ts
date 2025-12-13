declare module 'LoginDto' {
    export interface StorageLoginUser {
        id: number;
        name: string;
        username: string;
        dealerCode: string;
        loginUserId?: number|string;
        token: string;
    }
    export interface Credentials {
        username: string;
        password: string;
    }
}