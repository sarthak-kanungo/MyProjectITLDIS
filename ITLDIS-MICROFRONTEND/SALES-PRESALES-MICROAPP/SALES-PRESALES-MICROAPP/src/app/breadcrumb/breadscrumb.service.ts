import { Injectable } from '@angular/core';
import { salePreSaleRoutes } from '../routes/sale-pre-sale-routes';
@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {
  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      ...salePreSaleRoutes
    ];
    return newMenu;
  }
  addMenu(name, path) {
    return { name: name, path: path, children: [] }
  }
}
// name: 'Masters', path: './master', children:[
//   {
//   name: 'itldis Masters', path: './itldismasters', children: [
//     {
//     name: 'Item Master', path: './item-master', children: []