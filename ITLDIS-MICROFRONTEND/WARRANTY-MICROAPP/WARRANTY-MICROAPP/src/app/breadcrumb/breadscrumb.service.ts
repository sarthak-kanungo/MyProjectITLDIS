import { Injectable } from '@angular/core';
import { warrantyRoutes } from '../routes/warranty-routes';

@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      ...warrantyRoutes
    ];
    return newMenu;
  }

  addMenu(name, path) {
    return { name: name, path: path, children: [] }
  }

}
