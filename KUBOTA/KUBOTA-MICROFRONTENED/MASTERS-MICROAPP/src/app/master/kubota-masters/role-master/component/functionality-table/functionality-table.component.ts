import { Component, OnInit, Input } from '@angular/core';
import { ExampleFlatNode, MenuNode } from '../../domains/role-master';
import { MatCheckboxChange, MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material';
import { element } from 'protractor';
import { SelectionModel } from '@angular/cdk/collections';
import { FlatTreeControl } from '@angular/cdk/tree';
import { FormGroup } from '@angular/forms';
import { RoleMasterPresenter } from '../../services/role-master-presenter';

@Component({
  selector: 'app-functionality-table',
  templateUrl: './functionality-table.component.html',
  styleUrls: ['./functionality-table.component.scss']
})
export class FunctionalityTableComponent implements OnInit {

  @Input()
  isView:boolean
  functionalityMasters: Array<MenuNode>
  menuSelected:Array<number> = new Array<number>();
  menuGroup:FormGroup

  private _transformer = (node: MenuNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.element.description,
      id:node.element.id,
      parentId : node.element.parentId,
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

  constructor( private presenter: RoleMasterPresenter) {
  }

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;
  ngOnInit() {
    this.menuGroup = this.presenter.buildMenuForm();
    this.presenter.menuSubject.subscribe(response => {
      this.functionalityMasters = response.menuList;
      this.dataSource.data = this.functionalityMasters;
      //this.treeControl.expandAll();
      const selectedMenu:Array<number> = response.selectedMenu;
      if(selectedMenu && selectedMenu.length>0){
        console.log('selectedMenu', selectedMenu)
        selectedMenu.forEach(menuid => {
          const m1:ExampleFlatNode[] = this.treeControl.dataNodes.filter(node=>node.id === menuid && !node.expandable);
          console.log('m1 :',m1);
          this.checklistSelection.toggle(m1[0]);
        })
      }
    })
    this.presenter.fetchSelectedMenuSubject.subscribe(response => {
      this.menuSelected = response;
      this.treeControl.dataNodes.forEach(node => {
        if(this.checklistSelection.isSelected(node)){
          if(this.menuSelected.indexOf(node.id)<0){
              this.menuSelected.push(node.id);
              if(node.level>0){
                let clevel = node.level;
                let pnode = this.getParent(node);
                while(clevel>0){
                  if(this.menuSelected.indexOf(pnode.id)<0){
                    this.menuSelected.push(pnode.id); 
                  }
                  pnode = this.getParent(pnode);
                  clevel--;
                }
              }
          }
        }
      });
      this.menuGroup.controls.menuNode.patchValue(this.menuSelected);
      console.log('this.menuSelected :',this.menuSelected)
      
    });
  }

  // onAccessiblityChange(event: MatCheckboxChange, master: FunctionalityMaster) {
  //  master.accessibleFlag = event.checked
  // }
  
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


      // addItemToArray(node: ExampleFlatNode){
      //   console.log('this.treeControl :',this.treeControl.dataNodes)
      //   if(this.checklistSelection.isSelected(node)){
      //     if(this.menuSelected.indexOf(node.id)<0){
      //         this.menuSelected.push(node.id);
      //     }
      //     if(node.level>0){
      //         let clevel = node.level;
      //         let pnode = this.getParent(node);
      //         while(clevel>0){
      //           if(this.menuSelected.indexOf(pnode.id)<0){
      //             this.menuSelected.push(pnode.id); 
      //           }
      //           pnode = this.getParent(pnode);
      //           clevel--;
      //         }
      //     }
          
      //   }else{
      //     this.menuSelected = this.menuSelected.filter(n => n!=node.id);
      //   }
      //   if(node.expandable){
      //     const descendants = this.treeControl.getDescendants(node);
      //     while(descendants.length>0){
      //       this.addItemToArray(descendants.pop());
      //     }
      //   }
      //   this.menuGroup.controls.menuNode.patchValue(this.menuSelected);
      //   console.log('this.menuSelected :',this.menuSelected)
      // }


      getParent(node: ExampleFlatNode) {
        const currentLevel = this.treeControl.getLevel(node);
    
        if (currentLevel < 1) {
          return null;
        }
    
        const startIndex = this.treeControl.dataNodes.indexOf(node) - 1;
    
        for (let i = startIndex; i >= 0; i--) {
          const currentNode = this.treeControl.dataNodes[i];
    
          if (this.treeControl.getLevel(currentNode) < currentLevel) {
            return currentNode;
          }
        }
      }
}
