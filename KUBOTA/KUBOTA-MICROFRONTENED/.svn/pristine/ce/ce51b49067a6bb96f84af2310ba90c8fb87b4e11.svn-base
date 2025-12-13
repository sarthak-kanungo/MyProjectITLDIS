import { Component, ElementRef, OnInit } from '@angular/core';
import * as d3 from 'd3';
import { DataItem, DrillDownService } from './drill-down.service';

@Component({
  selector: 'app-drill-down',
  templateUrl: './drill-down.component.html',
  styleUrls: ['./drill-down.component.css']
})
export class DrillDownComponent{

  dataSource: DataItem[];

  colors: string[];

  service: DrillDownService;

  isFirstLevel: boolean;

  
  constructor(service: DrillDownService, element: ElementRef) {
    this.dataSource = service.filterData('');
    this.colors = service.getColors();
    this.service = service;
    this.isFirstLevel = true;
  }

  onButtonClick() {
    if (!this.isFirstLevel) {
      this.isFirstLevel = true;
      this.dataSource = this.service.filterData('');
    }
  }

  onPointClick(e) {
    if (this.isFirstLevel) {
      this.isFirstLevel = false;
      this.dataSource = this.service.filterData(e.target.originalArgument);
    }
  }

  customizePoint = () => {
    let pointSettings: any;

    pointSettings = {
      color: this.colors[Number(this.isFirstLevel)],
    };

    if (!this.isFirstLevel) {
      pointSettings.hoverStyle = {
        hatching: 'none',
      };
    }

    return pointSettings;
  };

}
