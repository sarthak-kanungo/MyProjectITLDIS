import { Injectable, EventEmitter } from '@angular/core';
import * as XLSX from 'xlsx';
import { } from 'protractor';
type AOA = any[][];

@Injectable()
export class ExcelFileUploadService {
  private data: AOA

  emXls = new EventEmitter<AOA>()

  constructor() { }

  readXls(fileEvent: Event) {
    console.log(fileEvent,'FileEvent##')
    // event.target.files[0]
    console.log('HIIIOiii')
    const target: DataTransfer = <DataTransfer><unknown>(fileEvent.target)
    if (target.files.length !== 1) throw new Error('Cannot use multiple files');
    const reader: FileReader = new FileReader();
    reader.onload = (e: any) => {
      /* read workbook */
      const bstr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });

      /* grab first sheet */
      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];

      /* save data */
      this.data = <AOA>(XLSX.utils.sheet_to_json(ws, { header: 1 }));
      this.emXls.emit(this.data)
    };
    reader.readAsBinaryString(target.files[0]);
  }
}
