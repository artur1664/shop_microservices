import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from '../components/menu/menu.component';

const routes: Routes = [
  {
    path: '', redirectTo: 'home', pathMatch: 'full',
  },
  {path: 'home', component: MenuComponent},
  {path: 'home/products', loadChildren: () => import('src/app/shop-user/shop-user.module').then(m => m.ShopUserModule)},
  {path: 'home/owner', loadChildren: () => import('src/app/shop-owner/shop-owner.module').then(m => m.ShopOwnerModule)},
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
