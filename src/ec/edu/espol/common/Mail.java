/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.common;

/**
 *
 * @author lfrei
 */
public class Mail {
    private String remitente; // dirección de correo del remitente
    private String destinatario; // dirección de correo del destinario
    private int importancia;
    private String mensaje;
    
    public Mail(String r,String d,int i,String m){
        this.remitente=r;
        this.destinatario=d;
        this.importancia=i;
        this.mensaje=m;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public int getImportancia() {
        return importancia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
