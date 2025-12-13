declare module "itldis-file-upload" {

    export interface UploadableFile {
        id : string
        previewUrl : string | any
        file : File
    }
}