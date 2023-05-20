import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login, Usuario } from '../core/interfaces/auth';

const base_url = environment.base_url + '/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _user!: Usuario;

  constructor(private http: HttpClient) {}

  login(loginForm: Login): Observable<any> {
    return this.http
      .post<any>(`${base_url}/login`, {
        nombre: '',
        apellido: '',
        ...loginForm,
      })
      .pipe(
        tap((response) => {
          this._user = response;
        })
      );
  }

  register(createForm: Usuario): Observable<any> {
    return this.http.post<any>(`${base_url}/create`, createForm).pipe(
      tap((response) => {
        this._user = response;
      })
    );
  }

  public get user(): Usuario {
    return this._user;
  }
}
