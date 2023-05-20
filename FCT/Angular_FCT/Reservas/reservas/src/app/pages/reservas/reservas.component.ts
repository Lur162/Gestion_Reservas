import { Component } from '@angular/core';
import { Reserva } from 'src/app/core/models/reserva';
import { AuthService } from 'src/app/services/auth.service';
import { ReservaService } from 'src/app/services/reserva.service';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent {
  private _reserva: Reserva;
  options:{key:number,value:string}[]=[]

  constructor(private AuthService:AuthService, private reservaService:ReservaService){
    //this.AuthService.user.id_usuario
    this._reserva= new Reserva(2)
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

  reservar(){
this.reservaService.generarReserva(this._reserva).subscribe()
  }

    /**
     * Getter reserva
     * @return {Reserva}
     */
	public get reserva(): Reserva {
		return this._reserva;
	}

}
