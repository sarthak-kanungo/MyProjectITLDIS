import { Injectable } from '@angular/core';
import { serviceRoutes } from '../routes/service-routes';

@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      ...serviceRoutes,
    ];
    console.log('newMenunewMenunewMenunewMenu', newMenu);

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