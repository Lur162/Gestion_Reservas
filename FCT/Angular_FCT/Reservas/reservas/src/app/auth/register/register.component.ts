import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';
import { ContactoService } from 'src/app/services/contacto.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [MessageService]
})
export class RegisterComponent {
  //dirty
  private _isDirty: boolean = false;
  formRegister: FormGroup;


  constructor(
    private cf: FormBuilder,
    private authService: AuthService,
    private router:Router,
    private messageService: MessageService

  ) {
    this.formRegister = this.cf.group({});
  }

  ngOnInit(): void {
    //vaciar
    sessionStorage.clear()
    this.formRegister = this.cf.group({
      nombre: ['', Validators.required],
      apellido: ['', [Validators.required]],
      correo: ['', [Validators.required]],
      contrasenia: ['', [Validators.required]],
    });
  }

   isValidField(id: string) {
    let field = this.formRegister.get(id);
   //if else
    return (field?.dirty && !field?.valid && this._isDirty) || (!field?.valid && this._isDirty)
  }

  onSubmit(){
    if (!this.formRegister.valid) {
      this._isDirty = true;
      this.messageService.add({  severity: 'error', summary: 'Error', detail: 'Error al registrar usuario' });
      return;
    }
    this.authService.register(this.formRegister.value).subscribe(
      (response) => {
        console.log(response)

        this.router.navigateByUrl('/login')
       },
      (error) => {

       this.messageService.add({  severity: 'error', summary: 'Error', detail: 'Error al registrar usuario' });

      }
    );
  }
}
