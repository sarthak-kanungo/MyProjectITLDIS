import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MachineGoodsReceiptNoteComponent } from './pages/machine-goods-receipt-note/machine-goods-receipt-note.component';
import { CreateGoodsReceiptNoteComponent } from './pages/create-goods-receipt-note/create-goods-receipt-note.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MachineGoodsReceiptNoteComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateGoodsReceiptNoteComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: CreateGoodsReceiptNoteComponent, canActivate: [UserAccessGuard] },
  { path: 'edit/:updateId', component: CreateGoodsReceiptNoteComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GoodsReceiptNoteRoutingModule { }
