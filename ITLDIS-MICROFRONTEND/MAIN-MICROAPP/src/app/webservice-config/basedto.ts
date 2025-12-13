export interface BaseDto<T> {
    message: string
    result: T
    status: string,
    count?: number
}