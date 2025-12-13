export interface QuestionMasterHeader{
    quesId:number
    surveyTypeId:number
    questionDesc:string
    isactive:string
    createdDate:string
    mainAnswer:Array<MainAnswer>
}
export interface MainAnswer{
    // active:string
    quesDtlId:number
    mainAnsDesc:string
    subAnswerApplicable:string
    multipleAnswer:string
    considerDissatisfied:string
    subAnswer: Array<SubAnswer>
}
export interface SubAnswer{
    subAnsDesc:string
    isotherapplicable:string
    isactive:string
}

export interface QuestionMaster {
    questionType:string
    questionCode:string
    questionId:number
    fromDate: string;
    toDate: string;
    page: number;
    size: number;
  }

  export interface QuestionMasterList {
    id: number;
    questionType: string;
    questionCode: string;
    questionCreationDate: string;
    edit:string
  }

  export interface QuestionCode {
    quesId
    questionCode:string
  }

  export interface ResponseAnswer{
    quesDtlId:number
    active:boolean
    mainAnswer:string
    subAnswer:boolean
    multipleAnswer:boolean
    considerDissatisfied:boolean
    subQuest: Array<ResponseSubAnswer>
  }

  export interface ResponseSubAnswer{
    quesSubDtlId:number
    subAnsDesc:string
    isotherapplicable:boolean
    isactive:boolean
}

export class ResponseSub{
  constructor(
   public subAnsDesc:string,
   public isotherapplicable:string,
   public isactive:string,
   public quesSubDtlId:number
   ){}
}

export interface SubAns{
  subAnsDesc:string
  isotherapplicable:boolean
  isactive:boolean
  quesSubDtlId:number
}