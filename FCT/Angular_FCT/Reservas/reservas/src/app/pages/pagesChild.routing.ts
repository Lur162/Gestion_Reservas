import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { ReservasComponent } from './reservas/reservas.component';
import { InicioComponent } from './inicio/inicio.component';
import { ContactoComponent } from './contacto/contacto.component';
import { ComentariosComponent } from './comentarios/comentarios.component';



const routes: Routes = [
    { path: '', component: InicioComponent },
    { path: 'reserva', component: ReservasComponent },
    { path: 'contacto', component: ContactoComponent },
    {path: 'comentarios', component: ComentariosComponent},
  

   
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PageChildRouterModule {}
