import {NgModule} from '@angular/core';

import {ShopUserRoutingModule} from './shop-user-routing.module';
import {ProductsComponent} from './components/products/products.component';
import {MaterialModule} from '../material/material.module';
import {CommonModule} from '@angular/common';


@NgModule({
  declarations: [ProductsComponent],
  imports: [
    CommonModule,
    ShopUserRoutingModule,
    MaterialModule
  ]
})
export class ShopUserModule { }
