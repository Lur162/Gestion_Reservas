import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContactoService } from '../../services/contacto.service';
import { ContactForm } from '../../core/interfaces/contacto-form';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css'],
  providers: [MessageService]
})
export class ContactoComponent {
  //implements OnInit
  private _isDirty: boolean = false;
  contactoFormulario: FormGroup;
  pulsarBtnEnviar: boolean = false;

  constructor(
    private cf: FormBuilder,
    private contactService: ContactoService,
    private messageService: MessageService
    
  ) {
    this.contactoFormulario = this.cf.group({});
  }

  ngOnInit(): void {
    this.contactoFormulario = this.cf.group({
      nombre: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      asunto: [''],
      mensaje: [
        '',
       [ Validators.required,Validators.minLength(5),Validators.maxLength(100)]
      
      ],
    });
  }

   isValidField(id: string) {
    let field = this.contactoFormulario.get(id);
    return (field?.dirty && !field?.valid && this._isDirty) || (!field?.valid && this._isDirty)
  }

  onSubmit(): void {
    if (!this.contactoFormulario.valid) {
      this._isDirty = true;
      this.messageService.add({  severity: 'error', summary: 'Error', detail: 'Debes de completar el formualrio para enviar' });
      return;
    }
    this.contactService.enviarEmail(this.contactoFormulario.value).subscribe(
      (response) => {
        console.log('Correo enviado correctamente');
        // Despúes del correo se vacian los campos
        //this.cf = { nombre: '', correo: '',   asunto: '', mensaje: '' };
        // Después del correo se vacian los campos
          this.contactoFormulario.reset();

      },
      (error) => {
        console.error('Error al enviar el correo:', error);
        // Despues del error de vacian los campos
       // this.contactoFormulario = { nombre: '', correo: '',   asunto: '', mensaje: '' };
      }
    );
          
  }
}
