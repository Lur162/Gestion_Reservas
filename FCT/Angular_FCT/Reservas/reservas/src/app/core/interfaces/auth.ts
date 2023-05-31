export interface Usuario {
  id_usuario: number;
  nombre: string;
  apellido: string;
  correo: string;
  contrasenia: string;
}

export interface UsuarioLoged {
  id: number;
  token: string;
  correo: string;
}


export interface Login {
  correo: string;
  contrasenia: string;
}
