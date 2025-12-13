declare module "ProspectBackground"{
    export interface DropDownOccupation{
        occupation : string;
    }
    
    export interface DropDownSoilType{
        id: number;
        soilType : string;
        deleteFlag? : boolean
    }
    
    export interface DropDownMajorCropGrown{
        id: number;
        cropGrown: string;
        deleteFlag? : boolean
    }
    export interface ProspectBackgroundObj{
        occupation : string,
        otherOccupation? : string,
        landSize : number,
        soilName : string,
        cropName : string
    }

    export  interface EnquirySoilType {
        id: number;
        soilName: string;
        deleteFlag? : boolean
      }
      
    export  interface EnquiryCropGrow {
        cropName: string;
        id: number;
        deleteFlag? : boolean
      }
}