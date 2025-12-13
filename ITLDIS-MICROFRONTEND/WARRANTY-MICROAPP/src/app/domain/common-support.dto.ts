declare module 'CommonSupportDto' {
    export interface Autocomplete {
        id: number;
        value: string
    }
    export interface SearchAutocomplete {
        id?: number;
        value: string;
        code?: string;
    }
}