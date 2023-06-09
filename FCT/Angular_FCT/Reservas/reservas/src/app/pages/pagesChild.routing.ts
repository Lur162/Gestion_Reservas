import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { ReservasComponent } from './reservas/reservas.component';
import { InicioComponent } from './inicio/inicio.component';
import { ContactoComponent } from './contacto/contacto.component';
import { ComentariosComponent } from './comentarios/comentarios.component';
import { AuthGuard } from '../guards/auth.guard';

const routes: Routes = [
  { path: '', canActivate: [AuthGuard], component: InicioComponent },
  { path: 'reserva', canActivate: [AuthGuard], component: ReservasComponent },
  { path: 'contacto', canActivate: [AuthGuard], component: ContactoComponent },
  {  path: 'comentarios',canActivate: [AuthGuard],component: ComentariosComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PageChildRouterModule {}
