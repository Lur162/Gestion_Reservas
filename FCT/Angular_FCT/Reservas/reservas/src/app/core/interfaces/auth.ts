export interface Usuario {
  id_usuario: number;
  nombre: string;
  apellido: string;
  correo: string;
  contrasenia: string;
}

export interface Login {
  correo: string;
  contrasenia: string;
}
