/*
 * Public API Surface of ngsw-search-table
 */

export * from './lib/ngsw-search-table.service';
export * from './lib/ngsw-search-table.component';
export * from './lib/ngsw-search-table.module';
export { ColumnSearch } from './lib/ngsw-search-table.component';
export {
    DataTable,
    InfoForGetPagination,
    TableHeading,
    TableBody,
    TableSort,
    ActionButton,
    TableActivityDetail,
    ActionOnTableRecord,
    ColumnSearchInterface
} from './lib/ngsw-search-table-dto';
