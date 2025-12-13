# editable-table

## Compress Into .tzp
`tar -cvzf editable-table.tgz editable-table`.

This library was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.1.3.

<!-- DEMO: https://ngx-toastr.netlify.com/ -->

<!-- ## Features

- Toast Component Injection without being passed `ViewContainerRef`
- No use of `*ngFor`. Fewer dirty checks and higher performance.
- AoT compilation and lazy loading compatible
- Component inheritance for custom toasts
- SystemJS/UMD rollup bundle
- Animations using Angular's
  [Web Animations API](https://angular.io/docs/ts/latest/guide/animations.html)
- Output toasts to an optional target directive

## Dependencies
Latest version available for each version of Angular

| ngx-toastr   | Angular |
| ------------ | ------- |
| 6.5.0 | 4.x     |
| 8.10.2       | 5.x     |
| 10.0.4       | 8.x 7.x 6.x | -->

## Install

```bash
npm install http://192.168.15.58:8080/editable-table.tgz
```

## Setup

**step 1:** add css

- Run `ng build editable-table` to build the project

**step 2:** add EditableTableModule to NgModule where you want to implement it.

```typescript
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { EditableTableModule } from 'editable-table';

@NgModule({
  imports: [
    CommonModule,
    EditableTableModule // EditableTableModule added
  ],
  bootstrap: [App],
  declarations: [App]
})
class MainModule {}
```

## Use

- Use html tag to your .component.html file.

```HTML
<editable-table [tableConfig]="tableConfig" [tableData]="tableData" [formGroupForTableRow]="formGroup" [assignListToAutocomplete]="assignTo" (searchValueChanges)="searchValueChanges($event)" (tableValueChanges)="tableValueChange($event)" [getFormData]="getFormData" (getTableRecordChange)="getTableRecordChange($event)" [patchValue]="patchValue" (optionSelected)="optionSelected($event)" [assignListToSelect]="assignListToSelect" [patchValueToSelect]="selectPatchValue">
</editable-table>
```

## Options

There are **individual options** and **global options**.

### Individual Options

Passed to `ToastrService.success/error/warning/info/show()`

| Option            | Type                           | Default           | Description                                                                                                                               |
| ----------------- | ------------------------------ | ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| @Input tableConfig    | Array<TableConfig>                      | undefined             | It's contain configuration of table structure.                                                                                                       |
| tableData       | Array<object>                        | undefined             | it's an array of object. The object contain the pair of form control name & its value for generate the row's with default values.                                                                                                       |
| formGroupForTableRow           | {[key: string]: any}                         | undefined              | it's configuration object for create formGroup                                                                                                               |
| extendedTimeOut   | number                         | 1000              | Time to close after a user hovers over toast                                                                                              |
| disableTimeOut    | boolean                        | false             | Disable both timeOut and extendedTimeOut                                                                                                  |
| easing            | string                         | 'ease-in'         | Toast component easing                                                                                                                    |
| easeTime          | string \| number               | 300               | Time spent easing                                                                                                                         |
| enableHtml        | boolean                        | false             | Allow html in message                                                                                                                     |
| progressBar       | boolean                        | false             | Show progress bar                                                                                                                         |
| progressAnimation | `'decreasing' \| 'increasing'` | 'decreasing'      | Changes the animation of the progress bar.                                                                                                |
| toastClass        | string                         | 'ngx-toastr'      | Class on toast                                                                                                                            |
| positionClass     | string                         | 'toast-top-right' | Class on toast container                                                                                                                  |
| titleClass        | string                         | 'toast-title'     | Class inside toast on title                                                                                                               |
| messageClass      | string                         | 'toast-message'   | Class inside toast on message                                                                                                             |
| tapToDismiss      | boolean                        | true              | Close on click                                                                                                                            |
| onActivateTick    | boolean                        | false             | Fires `changeDetectorRef.detectChanges()` when activated. Helps show toast from asynchronous events outside of Angular's change detection |

#### Setting Individual Options

success, error, info, warning take `(message, title, ToastConfig)` pass an
options object to replace any default option.

```typescript
this.toastrService.error('everything is broken', 'Major Error', {
  timeOut: 3000
});
```

### Global Options

All [individual options](#individual-options) can be overridden in the global
options to affect all toasts. In addition, global options include the following
options:

| Option                  | Type    | Default                            | Description                                                                                                      |
| ----------------------- | ------- | ---------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| maxOpened               | number  | 0                                  | Max toasts opened. Toasts will be queued. 0 is unlimited                                                         |
| autoDismiss             | boolean | false                              | Dismiss current toast when max is reached                                                                        |
| iconClasses             | object  | [see below](#iconclasses-defaults) | Classes used on toastr service methods                                                                           |
| newestOnTop             | boolean | true                               | New toast placement                                                                                              |
| preventDuplicates       | boolean | false                               | Block duplicate messages                                                                                         |
| countDuplicates         | boolean | false                              | Displays a duplicates counter (preventDuplicates must be true). Toast must have a title and duplicate message    |
| resetTimeoutOnDuplicate | boolean | false                              | Reset toast timeout on duplicate (preventDuplicates must be true)                                                |

##### iconClasses defaults

```typescript
iconClasses = {
  error: 'toast-error',
  info: 'toast-info',
  success: 'toast-success',
  warning: 'toast-warning'
};
```

#### Setting Global Options

Pass values to `ToastrModule.forRoot()`

```typescript
// root app NgModule
imports: [
  ToastrModule.forRoot({
    timeOut: 10000,
    positionClass: 'toast-bottom-right',
    preventDuplicates: true,
  }),
],
```

### Toastr Service methods return:

```typescript
export interface ActiveToast {
  /** Your Toast ID. Use this to close it individually */
  toastId: number;
  /** the message of your toast. Stored to prevent duplicates */
  message: string;
  /** a reference to the component see portal.ts */
  portal: ComponentRef<any>;
  /** a reference to your toast */
  toastRef: ToastRef<any>;
  /** triggered when toast is active */
  onShown: Observable<any>;
  /** triggered when toast is destroyed */
  onHidden: Observable<any>;
  /** triggered on toast click */
  onTap: Observable<any>;
  /** available for your use in custom toast */
  onAction: Observable<any>;
}
```

### Put toasts in your own container

Put toasts in a specific div inside your application. This should probably be
somewhere that doesn't get deleted. Add `ToastContainerModule` to the ngModule
where you need the directive available.

```typescript
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule, ToastContainerModule } from 'ngx-toastr';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    ToastrModule.forRoot({ positionClass: 'inline' }),
    ToastContainerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
```

Add a div with `toastContainer` directive on it.

```typescript
import { Component, OnInit, ViewChild } from '@angular/core';

import { ToastContainerDirective, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  template: `
  <h1><a (click)="onClick()">Click</a></h1>
  <div toastContainer></div>
`
})
export class AppComponent implements OnInit {
  @ViewChild(ToastContainerDirective) toastContainer: ToastContainerDirective;

  constructor(private toastrService: ToastrService) {}
  ngOnInit() {
    this.toastrService.overlayContainer = this.toastContainer;
  }
  onClick() {
    this.toastrService.success('in div');
  }
}
```

## SystemJS

If you are using SystemJS, you should also adjust your configuration to point to
the UMD bundle.

In your SystemJS config file, `map` needs to tell the System loader where to
look for `ngx-toastr`:

```js
map: {
  'ngx-toastr': 'node_modules/ngx-toastr/bundles/ngx-toastr.umd.min.js',
}
```

## Setup Without Animations

If you do not want to include `@angular/animations` in your project you can
override the default toast component in the global config to use
`ToastNoAnimation` instead of the default one.

In your main module (ex: `app.module.ts`)

```typescript
import {
  ToastrModule,
  ToastNoAnimation,
  ToastNoAnimationModule
} from 'ngx-toastr';

@NgModule({
  imports: [
    // ...

    // BrowserAnimationsModule no longer required
    ToastNoAnimationModule.forRoot(),
  ]
  // ...
})
class AppModule {}
```

That's it! Animations are no longer required.

## Using A Custom Toast

Create your toast component extending Toast see the demo's pink toast for an example
https://github.com/scttcper/ngx-toastr/blob/master/src/app/pink.toast.ts

```typescript
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    ToastrModule.forRoot({
      toastComponent: YourToastComponent // added custom toast!
    })
  ],
  entryComponents: [YourToastComponent], // add!
  bootstrap: [App],
  declarations: [App, YourToastComponent] // add!
})
class AppModule {}
```

## FAQ

