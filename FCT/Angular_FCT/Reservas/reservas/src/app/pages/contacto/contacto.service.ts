import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContactForm } from "./contacto-form";

@Injectable({
    providedIn: 'root'
  })
  export class ContactoService {
    private restUrl = 'http://localhost:8080/api/contact';
  
    constructor(private http: HttpClient) { }
  
    enviarEmail(contactoForm: ContactForm): Observable<any> {
      return this.http.get<any>(this.restUrl);
    }
  }