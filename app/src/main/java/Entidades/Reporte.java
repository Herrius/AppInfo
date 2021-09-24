package Entidades;

public class Reporte {
    private String idreporte;
    private String direccion;
    private String comentario;
    private String telefono;
    private String correo;

    public Reporte(String idreporte,String direccion, String comentario, String telefono, String correo) {
        this.direccion = direccion;
        this.comentario = comentario;
        this.telefono = telefono;
        this.correo = correo;
        this.idreporte = idreporte;
    }

    public String getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(String idreporte) {
        this.idreporte = idreporte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
