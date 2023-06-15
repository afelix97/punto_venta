package punto_venta.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class DateTimeThread extends Thread {
	 private JLabel timeLabel;
	private SimpleDateFormat dateFormat;
	
	 public DateTimeThread(JLabel timeLabel) {
	        this.timeLabel = timeLabel;
	        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    }
	 
	 public void run() {
	        while (true) {
	            updateTime();
	            try {
	                Thread.sleep(1000); // Espera 1 segundo antes de actualizar la hora
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private void updateTime() {
	        Date now = new Date();
	        String time = dateFormat.format(now);
	        timeLabel.setText(time);
	    }

}
