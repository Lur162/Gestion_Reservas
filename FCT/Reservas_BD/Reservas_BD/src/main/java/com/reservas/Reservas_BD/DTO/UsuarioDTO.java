package com.reservas.Reservas_BD.DTO;

public class UsuarioDTO {

    private String correo;
    private String contrasenia;

    public UsuarioDTO(String nombre, String contrasenia) {
        this.correo = nombre;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}

