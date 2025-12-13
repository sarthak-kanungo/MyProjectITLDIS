import { Injectable } from '@angular/core';
import { DynamicTableModule } from './dynamic-table.module';
import { ActionButton, DataTable, TableBody } from './dynamic-table.domain';

@Injectable({
  providedIn: DynamicTableModule
})
export class TableDataService {

  constructor() { }
  initDataTable(): DataTable {
    return { headerRow: [], tableBody: {} } as DataTable;
  }
  convertIntoDataTable(tableDataList: Array<object>, responseHeader?,clickOnTableFields?): DataTable | null {
    const dataTable = this.initDataTable();
    if (tableDataList) {
      dataTable.tableBody.content = tableDataList;
      let index = 0;
      for (const key in tableDataList[0]) {
        if (key) {
          // this.arrangeTableHedings(key);
          dataTable.headerRow[index] = { title: key };
          index++;
        }
      }
      if (!!clickOnTableFields && !!dataTable && clickOnTableFields.length > 0) {
        clickOnTableFields.forEach(clickableField => {
          for (let index = 0; index < dataTable.headerRow.length; index++) {
            if (dataTable.headerRow[index].title === clickableField.title) {
              dataTable.headerRow[index].icon = clickableField.icon;
              dataTable.headerRow[index].isClickable = true;
              dataTable.headerRow[index].iconClass = clickableField.iconClass;
              break;
            }
          }
        });
      }
      return dataTable;
    } else {
      return null;
    }
     // // console.log('this.dataTable ===============> ', this.dataTable);
  }
  addActionButton(title: string, matIcon?: string, toolTipText?: string): ActionButton {
    const actionBtn = {} as ActionButton;
    actionBtn.matIcon = matIcon;
    actionBtn.toolTipText = toolTipText;
    actionBtn.title = title;
    return actionBtn;
  }
}
