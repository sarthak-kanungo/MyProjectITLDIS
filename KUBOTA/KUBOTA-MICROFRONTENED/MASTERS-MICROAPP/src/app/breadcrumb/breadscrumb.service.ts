import { Injectable } from '@angular/core';
import { crmRoutes } from '../routes/crm-routes';
import { masterRoutes } from '../routes/master-routes';

@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      ...masterRoutes,...crmRoutes
    ];
    

    return newMenu;
  }

  addMenu(name, path) {
    return { name: name, path: path, children: [] }
  }

}















// name: 'Masters', path: './master', children:[
//   {
//   name: 'Kubota Masters', path: './kubotamasters', children: [
//     {
//     name: 'Item Master', path: './item-master', children: []