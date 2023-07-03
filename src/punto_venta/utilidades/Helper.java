package punto_venta.utilidades;

import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Helper extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5348834688779741246L;

	public void generarTicket(JPanel view, String folio) {
		Toolkit tk = view.getToolkit();

		PrintJob pj = tk.getPrintJob(this, "ticket_" + folio, null);
		Graphics g = pj.getGraphics();
		view.print(g);
		g.dispose();
		pj.end();
	}
}
