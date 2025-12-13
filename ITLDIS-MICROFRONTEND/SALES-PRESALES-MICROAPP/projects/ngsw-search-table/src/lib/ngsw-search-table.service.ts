import { Injectable } from '@angular/core';
import { DataTable, ActionButton } from './ngsw-search-table-dto';

@Injectable({ providedIn: 'root' })
export class NgswSearchTableService {

  constructor() { }
  initDataTable(): DataTable {
    return { headerRow: [], tableBody: {} } as DataTable;
  }
  convertIntoDataTable(tableDataList: Array<object>, responseHeader?, clickOnTableFields?): DataTable | null {
    const dataTable = this.initDataTable();
    if(dataTable.titleMap === undefined)
        dataTable.titleMap = new Map<String,String>();
    if (tableDataList) {
      dataTable.tableBody.content = tableDataList;
      let index = 0;
      for (const key in tableDataList[0]) {
        if (key) {
          // this.arrangeTableHedings(key);
          dataTable.headerRow[index] = { title: key };
          dataTable.titleMap.set(key,key);
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
  }
  addActionButton(title: string, matIcon?: string, toolTipText?: string): ActionButton {
    const actionBtn = {} as ActionButton;
    actionBtn.matIcon = matIcon;
    actionBtn.toolTipText = toolTipText;
    actionBtn.title = title;
    return actionBtn;
  }
}
