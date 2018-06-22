
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Color;

public class Temporizador extends JButton {

private long tInicio;//cremoa las variables de inicio y fin
private long Tfin;


public void iniciar() {//el metodo inicio guarada los milisegundo en el que se inicio 
tInicio=System.nanoTime();
}

public long detenerse() {//detenerse se usa con el proposito de checar los milisegundos donde termino el programa y enviarlos a pantalla
Tfin=System.nanoTime();
System.out.print("tiempo de ejecucion, nanosegundos ");
return (Tfin-tInicio);
}

} 