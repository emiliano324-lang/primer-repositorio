package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import tablemodels.UserTableModel;

public class UsersView extends JPanel {
	
	private JTable table;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPdf;

	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnPdf() {
		return btnPdf;
	}
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	public UsersView() {
		setLayout(new BorderLayout());
		table = new JTable();
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnPdf);
        
        add(panelButtons, BorderLayout.NORTH);
	}

	public File selectPdfFile() {
		
		String path = System.getProperty("user.home");
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
		
		if(!file.getName().toLowerCase().endsWith(".pdf")) {
			file = new File(file.getAbsolutePath() + ".pdf");
		}
		
		return file;
	}
	public void setTableModel(UserTableModel model) {
		table.setModel(model);
	}

	public JTable getTable() {
		return table;
	}
	
	
	
	
	
	
	
	
}