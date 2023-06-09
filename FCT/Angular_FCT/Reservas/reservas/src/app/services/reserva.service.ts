import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Reserva } from '../core/models/reserva';
import { Observable } from 'rxjs';
import { UsuarioLoged } from '../core/interfaces/auth';
import { Login, Usuario} from '../core/interfaces/auth';

const base_url = environment.base_url + '/reserva';
@Injectable({
  providedIn: 'root'
})
export class ReservaService {


  constructor(private http:HttpClient) {


  }


  generarReserva(reserva: Reserva): Observable<any> {
    return this.http.post<any>(`${base_url}/reservas`, reserva)
  }
  borrarReserva(id: Number, id_user: number):Observable<any>{
    return this.http.delete<any>(`${base_url}/deleteById/${id}/${id_user}`)

  }

}
