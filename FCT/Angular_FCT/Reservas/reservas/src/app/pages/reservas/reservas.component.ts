import { Component, ElementRef, ViewChild } from '@angular/core';
import { Reserva } from 'src/app/core/models/reserva';
import { AuthService } from 'src/app/services/auth.service';
import { ReservaService } from 'src/app/services/reserva.service';
import { MessageService } from 'primeng/api';
import { UsuarioLoged } from 'src/app/core/interfaces/auth';
import { Renderer2 } from '@angular/core';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css'],
  providers: [MessageService]
})
export class ReservasComponent {
  @ViewChild('idReservaInput') idReservaInput!: ElementRef;
  private _reserva: Reserva;
  minDate:Date;
  options:{key:number,value:string}[]=[]
  texto: string = 'Código de tu reserva';
  constructor(private AuthService:AuthService, private reservaService:ReservaService,private messageService:MessageService){
    console.log(this.AuthService.user.id)
    
    
   //this.AuthService.user
    this._reserva= new Reserva(this.AuthService.user.id);
    this.minDate=new Date();
    this.options=[
      {key:1,value:'1 Persona'},
      {key:2,value:'2 Personas'},
      {key:3,value:'3 Personas'},
      {key:4,value:'4 Personas'},
      {key:5,value:'5 Personas'},
      {key:6,value:'6 Personas'},
      {key:7,value:'7 Personas'},
      {key:8,value:'8 Personas'},
      {key:9,value:'9 Personas'},
      {key:10,value:'10 Personas'},
      {key:11,value:'11 Personas'},
      {key:12,value:'12 Personas'}
    ]
  }

  clearPlaceholder() {
    if (this.texto === 'Código de tu reserva') {
      this.texto = '';
    }
  }
  onDateSelect(event: any) {
    const selectedDate = new Date(event.value);
    const currentDate = new Date();

    if (selectedDate < currentDate) {
        //NO ES POSIBLE MARCAR UNA FECHA ANTERIOR A LA DE HOY
      this.reserva.fecha_Entrada = new Date(); // Restablecer el valor seleccionado
      this.reserva.fecha_Salida=new Date();
    }
  }
  reservar(){
this.reservaService.generarReserva(this._reserva).subscribe(
  (response) => {
    console.log(response);
    this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Reserva realizada correctamente' });
    console.log('Inicio de sesión');
  },
  (error) => {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Fechas seleccionadas no disponibles' });
  }
)
  }
  //HAY QUE LLAMAR AL BOTON DE ELIMINAR, BORRAR RESERVA
 
  borrarReserva(id:number){
    //TENGO QUE PASAR EL JWT PORQUE SI NO PUEDE BORRAR TODAS LAS RESERVAS INDENDIENTEMENTE SI ES SUYA O NO
    const idUsuario = this.AuthService.user.id;
    this.reservaService.borrarReserva(id,idUsuario ).subscribe(
      (response) => {
        if (response && response.success) {
          console.log(response);
          console.log('Reserva eliminada');
          
          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Reserva borrada correctamente' });
          this.idReservaInput.nativeElement.value = null;
        } else {
          console.log(response);
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se pudo eliminar la reserva' });
          this.idReservaInput.nativeElement.value = null;
        }
        
      },
      (error) => {
        console.log(error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La id de la Reserva es incorrecta' });
      }
    )
  }

    /**
     * Getter reserva
     * @return {Reserva}
     */
	public get reserva(): Reserva {
		return this._reserva;
	}
 
}