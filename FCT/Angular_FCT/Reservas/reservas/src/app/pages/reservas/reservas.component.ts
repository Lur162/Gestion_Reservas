import { Component } from '@angular/core';
import { Reserva } from 'src/app/core/models/reserva';
import { AuthService } from 'src/app/services/auth.service';
import { ReservaService } from 'src/app/services/reserva.service';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css'],
  providers: [MessageService]
})
export class ReservasComponent {
  private _reserva: Reserva;
  minDate:Date;
  options:{key:number,value:string}[]=[]

  constructor(private AuthService:AuthService, private reservaService:ReservaService,private messageService:MessageService){
    console.log(this.AuthService.user.id)
   //this.AuthService.user.id_usuario
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

    /**
     * Getter reserva
     * @return {Reserva}
     */
	public get reserva(): Reserva {
		return this._reserva;
	}
 
}