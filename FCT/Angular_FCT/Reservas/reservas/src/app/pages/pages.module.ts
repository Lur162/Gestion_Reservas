import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ReservasComponent } from './reservas/reservas.component';
import { PageRouterModule } from './pages.routing';
import { InicioComponent } from './inicio/inicio.component';
import { ContactoComponent } from './contacto/contacto.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { ComentariosComponent } from './comentarios/comentarios.component';
//PRIME-NG
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { RippleModule } from 'primeng/ripple';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
  declarations: [
    HomeComponent,
    ReservasComponent,
    InicioComponent,
    ContactoComponent,
    ComentariosComponent

  ],
  imports: [
    CommonModule,
    BrowserModule,
    PageRouterModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CalendarModule,
    InputTextModule,
    RippleModule,
    InputTextareaModule,
    ButtonModule,
    ToastModule,
    CardModule,
    DropdownModule
  ], 
  exports:[
    PageRouterModule
  ]
})
export class PagesModule { }
