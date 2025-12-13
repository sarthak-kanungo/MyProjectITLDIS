import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class QuestionMasterCreatePageStore{
    private _questionMasterForm:FormGroup
    constructor(private fb:FormBuilder){
        this._questionMasterForm=fb.group({
            questionMasterHeader: this.buildQuestMasterMainForm(),
            questionMasterFormArray : new FormArray([]),
            subAnsFormArray : new FormArray([]),
        })
    }

    public get quesMasterForm(){
        return this._questionMasterForm
    }

    public buildQuestMasterMainForm(){
        return this.fb.group({
            questionCode:[],
            questionType:[,[Validators.required]],
            creationDate:[],
            question:[,[Validators.required]],
            active:[],
        })
    }
    public buildQuestMasterMainFormArray(){
        return this.fb.group({
            active:[],
            quesDtlId:[],
            mainAnswer:[,[Validators.required]],
            subAnswer:[],
            multipleAnswer:[],
            considerDissatisfied:[],
            //considerDissatisfied:['',[Validators.required]],
            subQuest:new FormArray([])
        })
    }

    public buildSubQuestionArray(){
        return this.fb.group({
            isactive:[],
            quesSubDtlId:[],
            subAnsDesc:[],
            isotherapplicable:[],
        })
    }
}
