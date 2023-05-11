import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ReservasComponent } from './reservas/reservas.component';
import { PageRouterModule } from './pages.routing';
import { InicioComponent } from './inicio/inicio.component';
import { ContactoComponent } from './contacto/contacto.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


//PRIME-NG
import { CalendarModule } from 'primeng/calendar';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  declarations: [
    HomeComponent,
    ReservasComponent,
    InicioComponent,
    ContactoComponent

  ],
  imports: [
    CommonModule,
    BrowserModule,
    PageRouterModule,
    SharedModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    CalendarModule
  ]
})
export class PagesModule { }
