import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Comentarios } from '../core/models/comentarios';
import { Observable } from 'rxjs';

const base_url = environment.base_url;
@Injectable({
  providedIn: 'root'
})

export class ComentarioService{

constructor(private http:HttpClient) {}


guardarComentarios(comentario: Comentarios): Observable<any>{
    return this.http.post<any>(`${base_url}/comentario`,comentario)
}
obtenerComentarios() {return this.http.get<any>(`${base_url}/comentarioAll`)
}

}