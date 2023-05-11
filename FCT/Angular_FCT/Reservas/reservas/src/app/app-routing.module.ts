import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { PageRouterModule } from './pages/pages.routing';
import { Page404Component } from './page404/page404.component';
import { AuthRouterModule } from './auth/auth.routing';

const routes: Routes = [
  {path:"",  pathMatch:"full",redirectTo:'/main'},
  {path:"**", component:Page404Component  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes),PageRouterModule,AuthRouterModule],
  exports: [RouterModule]
})

export class AppRoutingModule { }
