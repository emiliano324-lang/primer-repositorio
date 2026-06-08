package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import config.Config;
import tablemodels.UserTableModel;
import utils.AppFont;
/**
 * Vista principal para la gestión de usuarios.
 * Muestra una tabla con los usuarios y botones para realizar acciones.
 */
public class UsersView extends JPanel {
	
	private JTable table;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPdf;
	/** @return Botón para editar usuario. */
	public JButton getBtnEdit() {
		return btnEdit;
	}
	/** @return Botón para agregar usuario. */
	public JButton getBtnAdd() {
		return btnAdd;
	}
	/** @return Botón para eliminar usuario. */
	public JButton getBtnDelete() {
		return btnDelete;
	}
	/** @return Botón para exportar a PDF. */
	public JButton getBtnPdf() {
		return btnPdf;
	}
	/** @return Índice de la fila seleccionada, o -1 si no hay selección. */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	/**
	 * Constructor. Inicializa los componentes, botones y la tabla de usuarios.
	 */
	public UsersView() {
		setLayout(new BorderLayout());
		table = new JTable();
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelButtons.setBackground(new Color(0, 31, 84));
		
        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");
 
        btnAdd.setBackground(Color.WHITE);
        btnEdit.setBackground(Color.WHITE);
        btnDelete.setBackground(Color.WHITE);
        btnPdf.setBackground(Color.WHITE);
        
        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnPdf);
        add(panelButtons, BorderLayout.NORTH);
	}
	/**
	 * Muestra una ventana para elegir dónde guardar el archivo PDF.
	 * * @return El archivo seleccionado pdf, u null si se cancela.
	 */
	public File selectPdfFile() {
		
		String path = Config.get("users.export.pdf", System.getProperty("user.home"));
		JFileChooser chooser =  new JFileChooser(path);
		
		chooser.setSelectedFile(new File("reporte-usuarios.pdf"));
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF", "pdf");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		
		int option = chooser.showDialog(this, "Exportar PDF de usuarios");

		if(option != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		File file = chooser.getSelectedFile();
		Config.set("users.export.pdf", file.getParent());
		
		if(!file.getName().toLowerCase().endsWith(".pdf")) {
			file = new File(file.getAbsolutePath() + ".pdf");
		}
		
		return file;
	}
	/**
	 * Aplica el diseño visual a la tabla (colores, fuentes, filas intercaladas y selección unica).
	 */
	public void styleTable() {
		table.setRowHeight(35);
		table.setShowGrid(true);
		table.setGridColor(new Color(230, 230, 230));
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setFont(AppFont.normal());
		
		table.setSelectionBackground(new Color(52, 152, 219));
		table.setSelectionForeground(Color.WHITE);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(44, 62, 80));
		header.setForeground(Color.WHITE);
		header.setFont(AppFont.bold());
		header.setPreferredSize(new Dimension(0, 40));
		header.setReorderingAllowed(false);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245));
                    }

                    c.setForeground(Color.BLACK);
                }
				
				if(column == 1) {
					c.setFont(AppFont.bold());
					if(!isSelected) {
						c.setForeground(new Color(41, 128, 185));
					}
				} else {
					c.setFont(AppFont.normal());
				}
				return c;
			}
		});
	}
	/**
	 * Asigna el modelo de datos a la tabla.
	 * * @param model Modelo con los datos de los usuarios.
	 */
	public void setTableModel(UserTableModel model) {
		table.setModel(model);
	}
	/** @return Componente JTable de usuarios. */
	public JTable getTable() {
		return table;
	}
	
}