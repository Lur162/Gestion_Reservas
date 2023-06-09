import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';
import { ContactoService } from 'src/app/services/contacto.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {
  //Propiedad que indica si un campo de un formulario ha sido modificado por el usuario
  private _isDirty: boolean = false;
  fomrLogin: FormGroup;


  constructor(
    private cf: FormBuilder,
    private authService: AuthService,
    private router:Router,
    private messageService: MessageService

  ) {
    this.fomrLogin = this.cf.group({});
  }

  ngOnInit(): void {
    //Cuando se inicia el componente, se borran las cookies
    sessionStorage.clear()
    this.fomrLogin = this.cf.group({
      correo: ['', Validators.required],
      contrasenia: ['', [Validators.required]],
    });
  }

   isValidField(id: string) {
    let field = this.fomrLogin.get(id);
    //dirty: si se ha modificado y valid para que se cumplan las validaciones anteriores
    return (field?.dirty && !field?.valid && this._isDirty) || (!field?.valid && this._isDirty)
  }

  login(){
    if (!this.fomrLogin.valid) {
      this._isDirty = true;
      this.messageService.add({  severity: 'error', summary: 'Error', detail: 'Debes de completar el formulario para enviar' });
      return;
    }
    this.authService.login(this.fomrLogin.value).subscribe(
      (response) => {
        console.log(response)


        this.router.navigateByUrl('/')
        console.log('Inicio de sesion')
      },
      (error) => {
        this.messageService.add({  severity: 'error', summary: 'Error', detail: 'Email o contraseña invalidos' });

      }
    );
  }


}
