
package ac.il.shenkar.converterPackage;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.PatternPredicate;

/*
 * GUI class
 */
public class ConverterGUI
{
	
	JFrame frame;
	JPanel panel, bottomPanel;
	JXTable table;
	JScrollPane scroll;
	JLabel label1, label2;
	JButton button;
	JTextArea textArea;
	JTextField textField;
	Object[][] info; 
	String[] top;
	Xml xml=null;
	public String update;
	List <Currency> currencyList;
	int lineFrom=0,lineTo=0;
	
	/**
	* Log4j logger
	*/

	static Logger log4j = Logger.getLogger("org.example.foo.bar");

	/*
	 * Initializing the variables
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConverterGUI () throws Throwable
	{
		log4j.debug("Starting Ilan Currency Rate Exchanger:");

		xml = new Xml();
		xml.readXml();
		currencyList= xml.getCurrlist();
		writeToTable();
		JTextArea lastupdate = new JTextArea("Last Update   - "+"  "+xml.update);
		lastupdate.setBackground(null);
		lastupdate.setFont(new Font("Arial",Font.PLAIN,12));
		lastupdate.setForeground(Color.white);
		
		label1= new JLabel ("Enter Amount");
		label1.setFont(new Font("Arial", Font.BOLD, 15));
		label1.setForeground(Color.WHITE);
		
		textArea= new JTextArea(1,17);
		
		textArea.setFont(new Font("Arial", Font.BOLD, 15));
		textArea.setBackground(null);
		textArea.setForeground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textField= new JTextField(7);
		textField.setFont(new Font("Arial", Font.PLAIN, 13));
		textField.setBackground(Color.white);
		textField.setForeground(Color.black);
		
		button= new JButton("Convert");
		button.setBackground(new Color(200, 200, 200));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.BLACK);
				
		bottomPanel= new JPanel();
		bottomPanel.add(Box.createRigidArea(new Dimension(50,0)));
		bottomPanel.add(label1);
		bottomPanel.add(textField);
		bottomPanel.add(Box.createRigidArea(new Dimension(60,0)));
		bottomPanel.add(button);
		bottomPanel.add(Box.createRigidArea(new Dimension(60,0)));
	
		bottomPanel.add(textArea);
		bottomPanel.setPreferredSize(new Dimension(500,65));
		bottomPanel.setBackground(new Color(66, 67, 160));
		
		
		
		panel = new JPanel ();
		
		panel.setPreferredSize(new Dimension(500,323));
		panel.setBackground(new Color(66, 67, 160));
		
		table = new JXTable(info, top);
		table.setBackground(null);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Arial", Font.BOLD, 13));
		
		JTableHeader header=table.getTableHeader();
		header.setBackground(Color.GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial",Font.BOLD,14));
		
		panel.add(table.getTableHeader());
		/*table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.changeSelection(0, 0, false, false);*/
		HighlightPredicate negativePredicate = new PatternPredicate("^\\-\\d*(\\.?\\d+)", 4, 4);
		ColorHighlighter negativeHighlighter = new ColorHighlighter(negativePredicate, new Color(220, 30, 30), null);
		HighlightPredicate positivePredicate = new PatternPredicate("^[+]?\\d+([.]\\d+)?$", 4, 4);
		ColorHighlighter positiveHighlighter = new ColorHighlighter(positivePredicate, new Color(50, 200, 50), null);
		table.setHighlighters(negativeHighlighter, positiveHighlighter);
		table.setBorder(new MatteBorder(1,1,1,1,Color.white));
		
		panel.add(table);
		panel.add(lastupdate);
		frame = new JFrame("By Ilan Oksenberg & Al Sade");
		frame.add(panel,BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		JPanel panel1 = new JPanel();
		panel1.setSize(500,100);
		JComboBox fromcombo = new JComboBox();
		
		JComboBox tocombo = new JComboBox();
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		fromcombo.setModel(new DefaultComboBoxModel());
		tocombo.setModel(new DefaultComboBoxModel());
		fromcombo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tocombo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		for (int i=0 ; i<currencyList.size(); i++)
		{
			fromcombo.addItem(currencyList.get(i).getCurrencyName()+"-"+currencyList.get(i).getCurrencyCode());
		
		    
		}
		tocombo.setModel(new DefaultComboBoxModel());
		
		for (int i=0 ; i<currencyList.size(); i++)
		{
			tocombo.addItem(currencyList.get(i).getCurrencyName()+"-"+currencyList.get(i).getCurrencyCode());
		
		    
		}
		panel1.add(from);
		panel1.add(fromcombo);
		panel1.add(to);
		panel1.add(tocombo);
		frame.add(panel1);
		
		/*
		 * ComboBoxes listeners
		 */
		
		fromcombo.addActionListener (new ActionListener () {
		   
			public void actionPerformed(ActionEvent e) {
		    	JComboBox comboBox = (JComboBox) e.getSource();
		    	lineFrom = comboBox.getSelectedIndex();    
		    }
		});
		tocombo.addActionListener (new ActionListener () {
			   
			public void actionPerformed(ActionEvent e) {
		    	JComboBox comboBox = (JComboBox) e.getSource();
		    	lineTo = comboBox.getSelectedIndex();

		    }
		});	
		
		/*
		 * "ENTER" key listener & Starts calculation
		 */
		button.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (textField.getText().isEmpty())
				{
					log4j.debug("Wrong Input - Blank (Nothing).");
					textArea.setText("      Numbers Only!");
					
				}
				else if (textField.getText().matches("[0-9]+"))
				{
					
					double sum= xml.calculate(Double.parseDouble(currencyList.get(lineFrom).getCurrencyRate()),Double.parseDouble(currencyList.get(lineFrom).getCurrencyUnit() ), Double.parseDouble(currencyList.get(lineTo).getCurrencyRate()),Double.parseDouble(currencyList.get(lineTo).getCurrencyUnit()) ,java.lang.Double.parseDouble(textField.getText()));
					textArea.setText(""+java.lang.Double.parseDouble(textField.getText())+"  "+currencyList.get(lineFrom).getCurrencyCode()+"   =   "+new DecimalFormat("#0.##").format(sum)+"  "+currencyList.get(lineTo).getCurrencyCode());
					log4j.debug("Query Submited: From "+textField.getText()+" "+currencyList.get(lineFrom).getCurrencyCode()+" ==> To "+textArea.getText()+" "+currencyList.get(lineTo).getCurrencyCode());
					System.out.println();
				}
				
				//Checks incorrect input 
				else if (!textField.getText().contains("^(?=.*?\\p{Lu})(?=.*?[\\p{L}&&[^\\p{Lu}]])(?=.*?\\d)" + "(?=.*?[`~!@#$%^&*()\\-_=+\\\\\\|\\[{\\]};:'\",<.>/?]).*$"))
				{
					// Fraction calculation
				
					if (textField.getText().contains(".") && textField.getText().matches("\\d+\\.\\d+"))
					{
						
						double sum= xml.calculate(Double.parseDouble(currencyList.get(lineFrom).getCurrencyRate()),Double.parseDouble(currencyList.get(lineFrom).getCurrencyUnit() ), Double.parseDouble(currencyList.get(lineTo).getCurrencyRate()),Double.parseDouble(currencyList.get(lineTo).getCurrencyUnit()) ,java.lang.Double.parseDouble(textField.getText()));
						textArea.setText(""+java.lang.Double.parseDouble(textField.getText())+"  "+currencyList.get(lineFrom).getCurrencyCode()+"   =   "+new DecimalFormat("#0.##").format(sum)+"  "+currencyList.get(lineTo).getCurrencyCode());
						log4j.debug("Query Submited: From "+textField.getText()+" "+currencyList.get(lineFrom).getCurrencyCode()+" ==> To "+textArea.getText()+" "+currencyList.get(lineTo).getCurrencyCode());
						System.out.println();
					}
					else 
					{
						textArea.setText("      Numbers Only!");
						log4j.debug("Wrong Input - "+"'"+textField.getText()+"' (Letters).");
					}
				}
			}
			
		});
		
				
		/*
		 * Brings the relevant line details.
		 */
		/*table.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		
			@Override
			public void mousePressed(MouseEvent e)
			{
				JXTable target = (JXTable)e.getSource();
				line = target.getSelectedRow();
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				JXTable target = (JXTable)e.getSource();
				line = target.getSelectedRow();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});*/
	}
	
	/*
	 * Starts GUI
	 */
	public void start()
	{
		  
		log4j.debug("Program Booted Up Successfully.");
		log4j.debug("Waiting for User Input...");
		frame.getRootPane().setDefaultButton(button);	
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(500,445);
        textField.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * Fills the table with the currency list details.
	 */
	public void writeToTable()
	{
		log4j.debug("Importing Data From XML to Java Table...");
		info=new Object [currencyList.size()][currencyList.get(0).getCurrencySize()];

		top=new String [currencyList.get(0).getCurrencySize()];
		top[0]= "Name";
		top[1]= "Country";
		top[2]= "Code";
		top[3]= "Rate";
		top[4]= "Change";
		
		log4j.debug("Done.");
		//top[5]= "Unit"; 
		
		for (int i=0 ; i<currencyList.size(); i++)
		{
			info[i][0]= new String (currencyList.get(i).getCurrencyName());
			info[i][1]= new String (currencyList.get(i).getCountry());
			info[i][2]= new String (currencyList.get(i).getCurrencyCode());
			info[i][3]= new String (currencyList.get(i).getCurrencyRate());
			info[i][4]= new String (currencyList.get(i).getCurrencyChange());
			//info[i][5]= currList.get(i).getUnit(); 
		}
		
	}
		
	/*
	 * Main method  
	 */
	public static void main(String[] args) throws Throwable
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ConverterGUI gui = new ConverterGUI();
					gui.start();
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}

