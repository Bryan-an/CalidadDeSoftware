package com.itsqmet.cbdd;

/**
 *
 * @author Bryan
 */
public final class Configuracion {

    private String url;
    private String baseDatos;
    private String usuario;
    private static Configuracion c;

    public static Configuracion getConfiguracion(String url,
            String baseDatos, String usuario) {
        if (c == null) {
            c = new Configuracion(url, baseDatos, usuario);
        }

        return c;
    }

    private Configuracion(String url, String baseDatos,
            String usuario) {
        this.url = url;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    private void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
