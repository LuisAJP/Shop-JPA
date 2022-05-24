package Main;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;

public class Main {
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ignored) {}

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_MAIN, null);
	}

}
