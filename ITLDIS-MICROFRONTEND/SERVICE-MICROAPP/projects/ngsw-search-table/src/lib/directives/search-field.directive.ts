import { Directive, Input } from '@angular/core';

@Directive({
  selector: '[ngsw-searchField]'
})
export class SearchFieldDirective {

  @Input('ngsw-searchField') searchFieldRef: HTMLElement;
  // @Input('searchValue') searchValue: string;
  @Input('searchColumnName') searchColumnName: string;
  constructor() { }

}
