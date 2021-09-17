package basics;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MiTipoTabla extends JTable {

	private static final long serialVersionUID = 1L;

	public Component prepareRenderer(TableCellRenderer renderer, int fila, int columna) {
		Component returnComponent = super.prepareRenderer(renderer, fila, columna);
		Color colorAlternativo = new Color(255, 250, 200);
		Color colorBlanco = Color.WHITE;
		if (!returnComponent.getBackground().equals(getSelectionBackground())) {
			Color bg = (fila % 2 == 0 ? colorAlternativo : colorBlanco);
			returnComponent.setBackground(bg);
			bg = null;
		}
		return returnComponent;
	}
}