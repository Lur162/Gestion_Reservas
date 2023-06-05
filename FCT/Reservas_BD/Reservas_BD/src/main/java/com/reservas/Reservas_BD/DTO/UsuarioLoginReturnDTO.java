package com.reservas.Reservas_BD.DTO;
public class UsuarioLoginReturnDTO {

    private String correo;
    private String TOKEN;
    private Long id;

    public UsuarioLoginReturnDTO(String nombre, String TOKEN,Long id) {
        this.correo = nombre;
        this.TOKEN = TOKEN;
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
