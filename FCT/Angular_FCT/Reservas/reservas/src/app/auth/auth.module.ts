import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { AuthRouterModule } from './auth.routing';

//PRIME-NG
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { RippleModule } from 'primeng/ripple';
import { BrowserModule } from '@angular/platform-browser';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AuthRouterModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    ToastModule,
    InputTextModule,
    RippleModule,
  ]
})
export class AuthModule { }
