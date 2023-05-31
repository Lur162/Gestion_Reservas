import { Component } from '@angular/core';
import { Comentarios } from 'src/app/core/models/comentarios';
import { AuthService } from 'src/app/services/auth.service';
import {ComentarioService} from 'src/app/services/comentario.service';
import { MessageService } from 'primeng/api';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css'],
  providers: [MessageService]
})
export class ComentariosComponent {

 
  private _comentarioo: Comentarios = new Comentarios(1);
  comentarios: Comentarios[] = [];

  constructor(private AuthService: AuthService, private comentarioService: ComentarioService,private messageService:MessageService  ){
    this._comentarioo=new Comentarios(this.AuthService.user.id);

  }
  ngOnInit() {
    this.obtenerComentariosAnteriores();
  }

  obtenerComentariosAnteriores() {
    this.comentarioService.obtenerComentarios().subscribe(
      (comentarios) => {
        this.comentarios = comentarios;
      },
      (error) => {
        console.log('Error al obtener los comentarios anteriores', error);
      }
    );
  }

  guardarComentarios(){
    this.comentarioService.guardarComentarios(this._comentarioo).subscribe(

      (response) => {
        console.log(response);
        this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Comentario guardado con éxito' });
          // Guardar el comentario en la lista
          this.comentarios.push(this.comentario);
    
          // Limpiar el formulario
         

      },
      (error) => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No ha sido posible guardar el comentario' });
      }

    )
  }


    
    /**
     * Getter reserva
     * @return {Comentarios}
     */
	public get comentario(): Comentarios {
		return this._comentarioo;
	}




}
