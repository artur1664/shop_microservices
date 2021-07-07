import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OwnerMenuComponent} from './componenets/owner-menu/owner-menu.component';


const routes: Routes = [
  {path: '', component: OwnerMenuComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopOwnerRoutingModule { }
