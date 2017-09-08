package nan.javalearn.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class QQWindow extends JFrame{
	private static final long serialVersionUID = -6403073791314955565L;
	JTextArea taHistory = null;
	JTextArea taContent = null;
	JButton btnSend = null;
	JTable tabContacts = null;
	JScrollPane spContent = null;
	JScrollPane spHistory = null;
	
	public QQWindow() {
		init();
	}
	
	private void init(){
		this.setLayout(null);
		this.setSize(600, 400);
		this.setLocation(200, 200);
		
		// Add a TextArea for Recorder.
		taHistory = new JTextArea();
		taHistory.setBounds(30, 30, 370, 240);
		taHistory.setEditable(false); // read only.
		spHistory = new JScrollPane(taHistory);
		spHistory.setBounds(30, 30, 370, 240);
		this.add(spHistory);
		
		// Add a TextArea for Edition
		taContent = new JTextArea();
		taContent.setBounds(30, 280, 300, 50);
		spContent = new JScrollPane(taContent);
		spContent.setBounds(30, 280, 300, 50);
		this.add(spContent);
		
		// Add a Button for Sending.
		btnSend = new JButton();
		btnSend.setBounds(330, 287, 70, 30);
		btnSend.setText("Send");
		this.add(btnSend);
		
		
		// Add a table.
	    tabContacts = new JTable();
	    tabContacts.setBounds(430, 30, 140,	340);
		this.add(tabContacts);
		
		// Show the Window.
		this.setVisible(true);
		
		// Send event.
		btnSend.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String txt = taContent.getText();
				Comm comm = Comm.getInstance(QQWindow.this);
				comm.sendText(txt);
				taContent.setText("");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	public void refreshFriends(final List<String> friends) {
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 1; }
	          public int getRowCount() { return friends.size();}
	          public Object getValueAt(int row, int col) { return friends.get(row); }
	    };
	    tabContacts.setModel(dataModel);
	}

	public void appendHistory(String message) {
		String strOld = taHistory.getText();
		StringBuilder sb = new StringBuilder();
		sb.append(strOld + "\r\n");
		sb.append(message + "\r\n");
		// Update history area.
		taHistory.setText(sb.toString());
	}

	public void updateTabHistory(String addr, String line) {
		String strOld = taHistory.getText();
		StringBuilder sb = new StringBuilder();
		sb.append(strOld + "\r\n");
		sb.append(addr + " says:\r\n");
		sb.append(line + "\r\n");
		// Update history area.
		taHistory.setText(sb.toString());
	}



	
	
	

}
