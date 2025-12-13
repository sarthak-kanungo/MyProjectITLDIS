/**
 * This is the interface send to pagination api to get pagination data.
 * @property {startSortOrder}: order in which list is sort i.e. 'ASC' or 'DESC'
 * @property {orderField} : which table file want to sort
 * @property {userId}: login userId
 * @property {page}: page number start from zero
 * @property {size}: number of record on single page.
 */
export interface InfoForGetPagination {
  startSortOrder?: string;
  orderField?: string;
  userId?: number;
  page: number;
  size: number;
}
/**
 * dynamic Table Data formate where,
 * @property {headerRow: string[]}: array list of title/heading of table.
 * @property {tableBody:object}: pagination data object which haveing array of table row,
 * pagination Information & sort related information object
 */
export declare interface DataTable {
  headerRow: TableHeading[];
  tableBody: TableBody;
}
export declare interface TableHeading {
  title: string;
  isClickable?: boolean;
  icon?: string;
  iconClass?: string;
}
export declare interface TableBody {
  content: Array<object>;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  size: number;
  sort: Sort;
  totalElements: number;
  totalPages: number;
  empty: boolean;
}
interface Sort {
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}

export interface TableSort {
  active: string;
  direction: string;
  hover?: string;
}

export interface ActionButton {
  title: string;
  toolTipText: string;
  matIcon: string;
}

export interface TableActivityDetail {
  record: object;
  btnAction: string;
  downloadFileType: string;
  download: any;
}

export interface ActionOnTableRecord {
  record: object;
  btnAction: string;
  tableName: string;
  recordIndex?: number;
}
