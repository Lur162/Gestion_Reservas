import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { AuthGuard } from '../guards/auth.guard';

const routes: Routes = [
  {
    path: 'main',
    component: HomeComponent,
    canActivate:[AuthGuard],
    loadChildren: () =>
      import('./pagesChild.routing').then((m) => m.PageChildRouterModule),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageRouterModule {}
