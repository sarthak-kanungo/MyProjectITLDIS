import { Injectable } from '@angular/core';
import { trainingRoutes } from '../routes/training-routes';

@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const newMenu = [
      ...trainingRoutes
    ];
    return newMenu;
  }

  addMenu(name, path) {
    return { name: name, path: path, children: [] }
  }

}
