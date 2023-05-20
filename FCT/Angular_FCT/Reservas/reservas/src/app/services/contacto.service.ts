import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ContactForm } from '../core/interfaces/contacto-form';
import { environment } from 'src/environments/environment';

const base_url = environment.base_url;
@Injectable({
  providedIn: 'root',
})
export class ContactoService {
  constructor(private http: HttpClient) {}

  enviarEmail(contactoForm: ContactForm): Observable<any> {
    return this.http.post<any>(`${base_url}/contact`, contactoForm);
  }
 
}
