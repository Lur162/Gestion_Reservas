import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Page404Component } from './page404/page404.component';
import { PagesModule } from './pages/pages.module';




@NgModule({
  declarations: [
    AppComponent,
    Page404Component,

    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PagesModule
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
