import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { ReservasComponent } from './reservas/reservas.component';
import { InicioComponent } from './inicio/inicio.component';
import { ContactoComponent } from './contacto/contacto.component';
import { ComentariosComponent } from './comentarios/comentarios.component';
import { AuthGuard } from '../guards/auth.guard';
import { LoginComponent } from '../auth/login/login.component';

const routes: Routes = [
  { path: '', canActivate: [AuthGuard], component: InicioComponent },
  { path: 'reserva', canActivate: [AuthGuard], component: ReservasComponent },
  { path: 'contacto', canActivate: [AuthGuard], component: ContactoComponent },
  { path:'login',  canActivate: [AuthGuard], component: LoginComponent },
  {  path: 'comentarios',canActivate: [AuthGuard],component: ComentariosComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageChildRouterModule {}
