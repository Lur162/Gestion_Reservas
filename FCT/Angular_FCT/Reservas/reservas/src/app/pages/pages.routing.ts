import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';

const routes: Routes = [
  {
    path: 'main',
    component: HomeComponent,
    loadChildren: () =>
      import('./pagesChild.routing').then((m) => m.PageChildRouterModule),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageRouterModule {}
