import { Component, OnInit } from '@angular/core';
import { FoodNode, ExampleFlatNode } from './material-tree-demo.domain';
import { SelectionModel } from '@angular/cdk/collections';
import { FlatTreeControl } from '@angular/cdk/tree';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';

const TREE_DATA: FoodNode[] = [
   {
     name: 'Fruit',
     children: [
       { name: 'Apple' },
       { name: 'Banana' },
       { name: 'Fruit loops' },
     ]
   }, {
     name: 'Vegetables',
     children: [
       {
         name: 'Green',
         children: [
           { name: 'Broccoli' },
           { name: 'Brussels sprouts' },
         ]
       }, {
         name: 'Orange',
         children: [
           { name: 'Pumpkins' },
           { name: 'Carrots' },
         ]
       },
     ]
   },
 ];
@Component({
  selector: 'app-material-tree-demo',
  templateUrl: './material-tree-demo.component.html',
  styleUrls: ['./material-tree-demo.component.css']
})
export class MaterialTreeDemoComponent implements OnInit {

      private _transformer = (node: FoodNode, level: number) => {
        return {
          expandable: !!node.children && node.children.length > 0,
          name: node.name,
          level: level,
        };
      }

      treeControl = new FlatTreeControl<ExampleFlatNode>(
        node => node.level, node => node.expandable);

      treeFlattener = new MatTreeFlattener(
        this._transformer, node => node.level, node => node.expandable, node => node.children);

      /** The selection for checklist */
      checklistSelection = new SelectionModel<ExampleFlatNode>(true /* multiple */);
      
      dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

      constructor() {
        this.dataSource.data = TREE_DATA;
        this.treeControl.expandAll();
      }

      hasChild = (_: number, node: ExampleFlatNode) => node.expandable;


      ngOnInit(): void {
      }
      
      /** Whether all the descendants of the node are selected */
      descendantsAllSelected(node: ExampleFlatNode): boolean {
        const descendants = this.treeControl.getDescendants(node);
        return descendants.every(child => this.checklistSelection.isSelected(child));
      }

      /** Whether part of the descendants are selected */
      descendantsPartiallySelected(node: ExampleFlatNode): boolean {
        const descendants = this.treeControl.getDescendants(node);
        const result = descendants.some(child => this.checklistSelection.isSelected(child));
        return result && !this.descendantsAllSelected(node);
      }

      /** Toggle the to-do item selection. Select/deselect all the descendants node */
      todoItemSelectionToggle(node: ExampleFlatNode): void {
        this.checklistSelection.toggle(node);
        const descendants = this.treeControl.getDescendants(node);
        this.checklistSelection.isSelected(node)
          ? this.checklistSelection.select(...descendants)
          : this.checklistSelection.deselect(...descendants);
      }
}

