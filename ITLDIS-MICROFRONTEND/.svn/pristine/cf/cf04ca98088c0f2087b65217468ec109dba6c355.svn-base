import { Injectable } from '@angular/core';

@Injectable()
export class SchemesCalcService {

    mapHeadsToRowsOnExcelData(heads, row) {
        let object = {}
        for (let i = 0; i < heads.length; i++) {
            object[heads[i]] = row[i] ? row[i] : null;
        }
        console.log(object)
        return object
    }

    permutationOfAll(parts: any[]) {
        return parts.reduce((a: any, b: any) =>
            a.reduce(
                (r, v) => r.concat(
                    b.map(w => [].concat(v, w))
                ), []
            )
        );
    }

    spreadPartsToObjects(permuted: any[]) {
        let objs = []
        permuted.forEach(part => objs.push(Object.assign({}, ...part)))
        return objs
    }

    mapExcelData(excelData: any[][], heads) {
        let excelDataWithKeyValues = []
        excelData.forEach(row => {
            excelDataWithKeyValues.push(this.mapHeadsToRowsOnExcelData(heads, row))
        })
        return excelDataWithKeyValues
    }
    
    removeNullParts(parts: any[]) {
        return parts.filter(part => part);
    }

    runTranscation(parts: any[]) {
        let partsWithoutNull = this.removeNullParts(parts)
        console.log(partsWithoutNull)
        if (partsWithoutNull.length >= 2) {
            console.log('Parts Without Null', partsWithoutNull)
            let result = this.permutationOfAll(partsWithoutNull)
            console.log('Permutation Result', result)
            let spreadedParts = this.spreadPartsToObjects(result)
            console.log('spreadedParts', spreadedParts)
            return spreadedParts
        }
        return partsWithoutNull[0]
    }
} 