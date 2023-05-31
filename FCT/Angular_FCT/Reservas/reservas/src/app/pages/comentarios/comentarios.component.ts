import { Component, ElementRef, ViewChild } from '@angular/core';
import { Comentarios } from 'src/app/core/models/comentarios';
import { AuthService } from 'src/app/services/auth.service';
import {ComentarioService} from 'src/app/services/comentario.service';
import { MessageService } from 'primeng/api';
import { Renderer2 } from '@angular/core';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css'],
  providers: [MessageService]
})
export class ComentariosComponent {

 
  private _comentarioo: Comentarios = new Comentarios(1);
  comentarios: Comentarios[] = [];
  @ViewChild('commentList') commentList!: ElementRef;
  constructor(private AuthService: AuthService, private comentarioService: ComentarioService,private messageService:MessageService,private renderer: Renderer2  ){
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
         // Guardar el comentario en la lista
        this.comentarios.push(this.comentario);
        this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Comentario guardado con éxito' });
        const commentListElement = this.renderer.selectRootElement('#commentList');
        commentListElement.scrollTop = commentListElement.scrollHeight;
  

         this._comentarioo.fecha_publicacion=new Date()
         this._comentarioo.texto=new String();
          
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
