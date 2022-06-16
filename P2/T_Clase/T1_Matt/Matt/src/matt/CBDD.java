/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt;

import java.sql.*;

public class CBDD {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public CBDD conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String BaseDeDatos = "jdbc:mysql://localhost/db_matt?user=root&password=";
            setConexion(DriverManager.getConnection(BaseDeDatos));
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public ResultSet consultar(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultado;
    }

    public boolean ejecutar(String sql) {
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
     public static void main(String[] args) {
     CBDD baseDatos = new CBDD().conectar();

     if (baseDatos.ejecutar("INSERT INTO dato_matt(dato,fecha_registro) VALUES('A','2015-09-08 10:59:46')")) {
     System.out.println("Ejecución correcta!");
     } else {
     System.out.println("Ocurrió un problema al ejecutar!");
     }
     ResultSet resultados = baseDatos.consultar("SELECT * FROM dato_matt");
     if (resultados != null) {
     try {
     System.out.println("Dato       fecha_registro");
     System.out.println("--------------------------------");
     while (resultados.next()) {
     System.out.println("" + resultados.getString("dato") + "       " + resultados.getDate("fecha_registro"));
     }
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     }*/
}
