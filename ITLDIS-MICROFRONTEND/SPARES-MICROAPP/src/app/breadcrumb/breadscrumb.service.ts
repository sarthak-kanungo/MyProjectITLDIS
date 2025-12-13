import { Injectable } from '@angular/core';
import { masterRoutes } from '../routes/master-routes';
import { salePreSaleRoutes } from '../routes/sale-pre-sale-routes';
import { serviceRoutes } from '../routes/service-routes';
import { spareRoutes } from '../routes/spare-routes';
import { warrantyRoutes } from '../routes/warranty-routes';

@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      // {
      //   name: 'Dashboard',
      //   path: 'dashboard',
      //   children: []
      // },
      // {
      //   name: 'Support', path: 'support', children: [
      //     {
      //       name: 'Create Ticket', path: 'create', children: []
      //     },
      //     // {
      //     //   name: 'View Ticket', path:'' ,children:[]
      //     // }
      //   ]
      // },
      // ...masterRoutes,
      // ...salePreSaleRoutes,
      // ...serviceRoutes,
      ...spareRoutes,
      // ...warrantyRoutes
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