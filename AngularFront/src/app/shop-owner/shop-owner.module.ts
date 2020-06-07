import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopOwnerRoutingModule } from './shop-owner-routing.module';
import { EmployeesComponent } from './componenets/employees/employees.component';
import { OwnerMenuComponent } from './componenets/owner-menu/owner-menu.component';
import {MaterialModule} from '../material/material.module';


@NgModule({
  declarations: [EmployeesComponent, OwnerMenuComponent],
  imports: [
    CommonModule,
    ShopOwnerRoutingModule,
    MaterialModule
  ]
})
export class ShopOwnerModule { }
