package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;



public class Start extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static Connection conn;
	
	public JSplitPane split;
	public JDesktopPane desktop;

	public Start() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_accounting","root","");
		} catch(ClassNotFoundException e) {
		} catch(SQLException e) {
		} catch(Exception e) {
		}
		
		this.setLayout(new BorderLayout());
		this.setTitle("Stock Accounting");
		this.setIconImage(new ImageIcon("./icons.png").getImage());

		JToolBar toolBar = this.addToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		JMenuBar mbar = new JMenuBar();
		this.setJMenuBar(mbar);
			JMenu mnuMaster = new JMenu("Master");
			mbar.add(mnuMaster);
				JMenuItem mnuManufacturer = new JMenuItem("Manufacturer");
				JMenuItem mnuProduct = new JMenuItem("Product");
				JMenuItem mnuVendor = new JMenuItem("Vendor");
				JMenuItem mnuCustomer = new JMenuItem("Customer");
				JMenuItem mnuActivities = new JMenuItem("Activities");
//				mnuProduct.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent ae) {
//						new ProductMaintenance(desktop, conn);
//					}
//				});
//				mnuManufacturer.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent ae) {
//						new ManufacturerMaintenance(desktop, conn);
//					}
//				});
				mnuMaster.add(mnuManufacturer);
				mnuMaster.add(mnuProduct);
				mnuMaster.addSeparator();
				mnuMaster.add(mnuVendor);
				mnuMaster.add(mnuCustomer);
				mnuMaster.addSeparator();
				mnuMaster.add(mnuActivities);

			JMenu mnuTran = new JMenu("Transaction");
			mbar.add(mnuTran);
				JMenuItem mnuStockEntry = new JMenuItem("Stock Entry");
				JMenu mnuStockReports = new JMenu("Stock Reports");
					JMenuItem mnuCurrentStock = new JMenuItem("Current Stock", new ImageIcon("current-stock.png"));
					JMenuItem mnuPurchase = new JMenuItem("Purchase");
					JMenuItem mnuSales = new JMenuItem("Sales");
				JMenuItem mnuStockAdjustment = new JMenuItem("Stock Adjustment");
				mnuTran.add(mnuStockEntry);
				mnuTran.addSeparator();
				mnuTran.add(mnuStockReports);
					mnuStockReports.add(mnuCurrentStock);
					mnuStockReports.add(mnuPurchase);
					mnuStockReports.add(mnuSales);
				mnuTran.addSeparator();
				mnuTran.add(mnuStockAdjustment);

			JMenu mnuTools = new JMenu("Tools");
			mbar.add(mnuTools);

			JMenu mnuExit = new JMenu("Exit");
			mbar.add(mnuExit);

		desktop = new JDesktopPane();
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel(), desktop);
		split.setDividerLocation(250);
		this.add(split, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				             Toolkit.getDefaultToolkit().getScreenSize().height-50);
		this.setVisible(true);
	}

	private JToolBar addToolBar() {
		JToolBar tb = new JToolBar();
		JButton btnExit = new JButton(new ImageIcon("exit.png"));
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		tb.add(btnExit);
		return tb;
	}
	
	private JPanel buttonPanel() {
		JPanel pnlButtons = new JPanel(new GridLayout(15,1));
		JLabel lblMaster = new JLabel("MASTER", JLabel.CENTER);
		lblMaster.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 16));
		lblMaster.setBorder(BorderFactory.createRaisedBevelBorder());
		lblMaster.setBackground(Color.lightGray);
		lblMaster.setForeground(Color.blue);
		lblMaster.setOpaque(true);
		JButton btnManufacturer = new JButton("Manufacturer");
		JButton btnProduct = new JButton("Product");
		JButton btnVendor = new JButton("Vendor");
		JButton btnCustomer = new JButton("Customer");
		JButton btnActivity = new JButton("Activity");
		btnManufacturer.setFont(new Font("Arial", Font.BOLD, 15));
		btnProduct.setFont(new Font("Arial", Font.BOLD, 15));
		btnVendor.setFont(new Font("Arial", Font.BOLD, 15));
		btnCustomer.setFont(new Font("Arial", Font.BOLD, 15));
		btnActivity.setFont(new Font("Arial", Font.BOLD, 15));
		pnlButtons.add(lblMaster);
		pnlButtons.add(btnManufacturer);
		pnlButtons.add(btnProduct);
		pnlButtons.add(btnVendor);
		pnlButtons.add(btnCustomer);
		pnlButtons.add(btnActivity);
		
		JLabel lblTransaction = new JLabel("TRANSACTION", JLabel.CENTER);
		lblTransaction.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 16));
		lblTransaction.setBorder(BorderFactory.createRaisedBevelBorder());
		lblTransaction.setBackground(Color.lightGray);
		lblTransaction.setForeground(Color.blue);
		lblTransaction.setOpaque(true);
		JButton btnTransaction = new JButton("Stock Transaction");
		btnTransaction.setFont(new Font("Arial", Font.BOLD, 15));
		pnlButtons.add(lblTransaction);
		pnlButtons.add(btnTransaction);

		
		btnActivity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new transacation(desktop, conn);
			}
		});

		return pnlButtons;
	}
	
	public static void main(String[] args) {
		new Start();
	}
}