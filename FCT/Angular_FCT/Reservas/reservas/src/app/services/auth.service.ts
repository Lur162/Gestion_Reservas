import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login, Usuario, UsuarioLoged } from '../core/interfaces/auth';

const base_url = environment.base_url + '/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _user!: UsuarioLoged;

  constructor(private http: HttpClient) {
    this._user=JSON.parse(sessionStorage.getItem('user')||"{}")
  }

  login(loginForm: Login): Observable<any> {
    return this.http
      .post<any>(`${base_url}/login`,loginForm)
      .pipe(
        tap((response) => {
          this._user = response;
          sessionStorage.setItem('user',JSON.stringify(this._user))
          sessionStorage.setItem('token',this._user.token.toString())
        })
      );
  }

  register(createForm: Usuario): Observable<any> {
    return this.http.post<any>(`${base_url}/create`, createForm)
    .pipe(
      tap((response) => {
        this._user = response;
        sessionStorage.setItem('user',JSON.stringify(this._user))
        sessionStorage.setItem('token',this._user.token.toString())
      })
    );
  }

  public get user(): UsuarioLoged {
    return this._user;
  }
}
