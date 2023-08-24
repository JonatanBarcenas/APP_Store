package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.CSSRule;

import model.Product;
import model.Provider;
import model.Restock;
import model.RestockRow;
import model.Sale;
import model.TicketRow;
import services.ProductsDataServiceImplementation;
import services.ProvidersDataServiceImplementation;
import services.RestocksDataServiceImplementation;
import services.SalesDataServiceImplementation;

import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Main extends JFrame{


	private JPanel contentPane;
	private RegisterProductsPanel registerProductsPanel;
	private MenuPanel menuPanel;
	private MenuSalesPanel menuSalesPanel;
	private PanelSell panelSell;
	private static ProductsDataServiceImplementation productsDataService;
	private static ProvidersDataServiceImplementation providersDataService;
	private static RestocksDataServiceImplementation restocksDataService;
	private static SalesDataServiceImplementation salesDataService;
	private OptionsPanel optionsPanel;
	private JPanel btnAccept;
	private JPanel btnContinue;
	private JPanel btnExit;
	private HeaderPane headerPane;
	private QueryPane consultPanel;
	private ListPane listPane;
	private Title title;
	private MenuRestockPanel menuRestockPanel;
	private PanelRestock panelRestock;
	private ConsultRestockPanel consultRestockPanel;
	private Home home;
	private ListRestockPane lisRestockPane;
	private ConsultSalePanel consultSalePanel;
	private ListSalePane listSalePane;


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */


	public Main() {

		setBackground(SystemColor.control);
		setType(Type.POPUP);
		setTitle("STORE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);


		productsDataService = new ProductsDataServiceImplementation();
		providersDataService = new ProvidersDataServiceImplementation();
		restocksDataService = new RestocksDataServiceImplementation();
		salesDataService = new SalesDataServiceImplementation();


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		if (home == null) {
			home = new Home();

			home.getBtnProducts().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					home.setVisible(false);
					if (menuPanel == null) {
						menuPanel = new MenuPanel();
						title = new Title("PRODUCTS");
						menuPanel.getBtnClearContentPane().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuPanel.setVisible(false);
								title.setVisible(false);
								contentPane.remove(menuPanel);
								contentPane.remove(title);
								menuPanel = null;
								title = null;
								System.gc();
								home.setVisible(true);
							};
						});

						menuPanel.getBtnList().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								title.setVisible(false);
								menuPanel.setVisible(false);
								headerPane = new HeaderPane("Inventory", "List of products");
								listPane = new ListPane();
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);
								optionsPanel.getBtnContinue().setVisible(false);

								btnExit = optionsPanel.getBtnExit();
								btnExit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										headerPane.setVisible(false);
										listPane.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(listPane);
										contentPane.remove(optionsPanel);
										headerPane = null;
										listPane = null;
										optionsPanel = null;
										System.gc();
										menuPanel.setVisible(true);
										title.setVisible(true);

									};
								});

								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(listPane, BorderLayout.CENTER);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								setVisible(true);
							};
						});


						menuPanel.getBtnConsult().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuPanel.setVisible(false);
								title.setVisible(false);
								headerPane = new HeaderPane("Inventory", "Consult a product");
								consultPanel = new QueryPane(productsDataService.getAllProducts());
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);
								optionsPanel.getBtnContinue().setVisible(false);

								btnExit = optionsPanel.getBtnExit();
								btnExit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										headerPane.setVisible(false);
										consultPanel.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(consultPanel);
										contentPane.remove(optionsPanel);
										headerPane = null;
										consultPanel = null;
										optionsPanel = null;
										System.gc();

										menuPanel.setVisible(true);
										title.setVisible(true);
									};
								});

								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(consultPanel, BorderLayout.CENTER);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								setVisible(true);

							};
						});

						menuPanel.getBtnRegister().addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								menuPanel.setVisible(false);
								title.setVisible(false);
								headerPane = new HeaderPane("Inventory", "Register a product");
								registerProductsPanel = new RegisterProductsPanel(productsDataService.getAllProducts());
								optionsPanel = new OptionsPanel();

								btnAccept = optionsPanel.getBtnAccept();
								btnAccept.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										Product product = registerProductsPanel.getProduct();
										if (product != null) {
											productsDataService.insert(product);
											JOptionPane.showMessageDialog(null, "Done");
											registerProductsPanel.clear();
										}

									};
								});

								btnContinue = optionsPanel.getBtnContinue();
								btnContinue.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										registerProductsPanel.clear();
									};
								});

								btnExit = optionsPanel.getBtnExit();
								btnExit.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {

										headerPane.setVisible(false);
										registerProductsPanel.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(registerProductsPanel);
										contentPane.remove(optionsPanel);
										headerPane = null;
										registerProductsPanel = null;
										optionsPanel = null;
										System.gc();

										menuPanel.setVisible(true);
										title.setVisible(true);

									};
								});

								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								contentPane.add(registerProductsPanel, BorderLayout.CENTER);
								setVisible(true);
							}
						});
						contentPane.add(title, BorderLayout.CENTER);
						contentPane.add(menuPanel, BorderLayout.NORTH);
						setVisible(true);
					}
				}
			});

			home.getBtnRestocks().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					home.setVisible(false);
					if (menuRestockPanel == null) {
						menuRestockPanel = new MenuRestockPanel();
						title = new Title("RESTOCKS");

						menuRestockPanel.getBtnRegister().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								title.setVisible(false);
								menuRestockPanel.setVisible(false);
								panelRestock = new PanelRestock();
								optionsPanel = new OptionsPanel();
								headerPane = new HeaderPane("Restocks", "Add");

								btnAccept = optionsPanel.getBtnAccept();
								btnAccept.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {

										var rows = panelRestock.getRestockRows();

										if (rows.size()>0) {
											Restock restock = new Restock();
											LocalDate date = LocalDate.now(); 
											DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
											String formattedDate = date.format(formatter);
											System.out.println(formattedDate);

											restock.setDate(formattedDate);

											restock.setProvider(getProvidersDataService().getByName(panelRestock.getProviderName()));
											restock.setTotal(panelRestock.getTotalRow());
											getRestocksDataService().insert(restock);
											JOptionPane.showMessageDialog(null, "DONE");
											System.out.println(restock.toString());

											for (RestockRow restockRow : rows) {

												getRestocksDataService().insertRestockRow(restockRow, Integer.parseInt(getRestocksDataService().getLastRestock().getId()));
												System.out.println(restockRow.toString());


											}				

										}

										panelRestock.getLblTotal().setText("$ 0.0");
										panelRestock.removeAll();
										panelRestock.clear();
									};
								});

								btnContinue = optionsPanel.getBtnContinue();
								btnContinue.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										panelRestock.clear();
										panelRestock.removeAll();
									};
								});

								btnExit = optionsPanel.getBtnExit();
								btnExit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										headerPane.setVisible(false);
										panelRestock.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(panelRestock);
										contentPane.remove(optionsPanel);
										headerPane = null;
										panelRestock = null;
										optionsPanel = null;
										System.gc();
										menuRestockPanel.setVisible(true);
										title.setVisible(true);
									};
								});

								contentPane.add(panelRestock, BorderLayout.CENTER);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								contentPane.add(headerPane, BorderLayout.NORTH);
								setVisible(true);
							};
						});

						menuRestockPanel.getBtnConsult().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								title.setVisible(false);
								menuRestockPanel.setVisible(false);
								consultRestockPanel = new ConsultRestockPanel();
								headerPane = new HeaderPane("Restocks", "Consult a restock");
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);

								btnContinue = optionsPanel.getBtnContinue();
								btnContinue.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										consultRestockPanel.clear();
										consultRestockPanel.clearRestockTable();
										consultRestockPanel.getDateChooserFrom().setDate(null);
										consultRestockPanel.getDateChooserTo().setDate(null);
										consultRestockPanel.getTxtFolio().setText("");
									};
								});

								btnExit = optionsPanel.getBtnExit();
								btnExit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										headerPane.setVisible(false);
										consultRestockPanel.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(consultRestockPanel);
										contentPane.remove(optionsPanel);
										headerPane = null;
										consultRestockPanel = null;
										optionsPanel = null;
										System.gc();

										title.setVisible(true);
										menuRestockPanel.setVisible(true);

									};
								});

								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(consultRestockPanel, BorderLayout.CENTER);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
							};
						});

						menuRestockPanel.getBtnList().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								title.setVisible(false);
								menuRestockPanel.setVisible(false);


								lisRestockPane = new ListRestockPane();
								headerPane = new HeaderPane("Restocks", "List Restocks");
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);
								optionsPanel.getBtnContinue().setVisible(false);

								btnExit = optionsPanel.getBtnExit();

								btnExit.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										lisRestockPane.setVisible(false);
										headerPane.setVisible(false);
										optionsPanel.setVisible(false);

										contentPane.remove(lisRestockPane);
										contentPane.remove(headerPane);
										contentPane.remove(optionsPanel);

										lisRestockPane = null;
										headerPane = null;
										optionsPanel = null;

										System.gc();

										title.setVisible(true);
										menuRestockPanel.setVisible(true);
									};
								});


								contentPane.add(lisRestockPane, BorderLayout.CENTER);
								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								setVisible(true);

							};
						});

						menuRestockPanel.getBtnClearContentPane().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuRestockPanel.setVisible(false);
								title.setVisible(false);
								contentPane.remove(menuRestockPanel);
								contentPane.remove(title);
								menuRestockPanel = null;
								title = null;
								System.gc();
								home.setVisible(true);
							};
						});

						contentPane.add(title, BorderLayout.CENTER);
						contentPane.add(menuRestockPanel, BorderLayout.NORTH);
						setVisible(true);
					}

				}
			});

			home.getBtnSales().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					home.setVisible(false);
					if (menuSalesPanel == null) {
						menuSalesPanel = new MenuSalesPanel();
						title = new Title("SALES");

						menuSalesPanel.getBtnRegister().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuSalesPanel.setVisible(false);
								title.setVisible(false);
								panelSell = new PanelSell();
								headerPane = new HeaderPane("Sales", "Add");
								optionsPanel = new OptionsPanel();

								optionsPanel.getBtnAccept().addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										List<TicketRow> ticketRows = panelSell.getTicketRows();
										if (ticketRows.size() >= 1) {
											Sale sale = new Sale();
											sale.setFolio("null");
											LocalDate date = LocalDate.now(); 
											DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
											String formattedDate = date.format(formatter);
											sale.setDate(formattedDate);
											sale.setTotal(panelSell.getTotal());
											salesDataService.insert(sale);

											for (TicketRow ticketRow : ticketRows) {
												salesDataService.insertTicketRow(ticketRow, Integer.parseInt(salesDataService.getLastSale().getFolio()));
											}

											JOptionPane.showMessageDialog(null, "DONE");
											panelSell.clear();
										}else {
											JOptionPane.showMessageDialog(null, "You have not selected any product yet");
										}
									};
								});

								optionsPanel.getBtnContinue().addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										panelSell.clear();
										panelSell.getTicketRows().clear();
									};
								});

								optionsPanel.getBtnExit().addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										panelSell.setVisible(false);
										headerPane.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(panelSell);
										contentPane.remove(headerPane);
										contentPane.remove(optionsPanel);
										panelSell = null;
										headerPane = null;
										optionsPanel = null;
										System.gc();
										title.setVisible(true);
										menuSalesPanel.setVisible(true);
									};
								});

								contentPane.add(panelSell, BorderLayout.CENTER);
								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								setVisible(true);
							};
						});

						menuSalesPanel.getBtnConsult().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuSalesPanel.setVisible(false);
								title.setVisible(false);
								consultSalePanel = new ConsultSalePanel();
								headerPane = new HeaderPane("Sales", "Consult a sale");
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);
								optionsPanel.getBtnContinue().setVisible(false);

								optionsPanel.getBtnExit().addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										consultSalePanel.setVisible(false);
										headerPane.setVisible(false);
										optionsPanel.setVisible(false);
										contentPane.remove(consultSalePanel);
										contentPane.remove(optionsPanel);
										contentPane.remove(headerPane);

										consultPanel = null;
										optionsPanel = null;
										headerPane = null;

										menuSalesPanel.setVisible(true);
										title.setVisible(true);

										System.gc();

									};
								});
								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								contentPane.add(consultSalePanel, BorderLayout.CENTER);
								setVisible(true);
							};
						});

						menuSalesPanel.getBtnList().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								title.setVisible(false);
								menuSalesPanel.setVisible(false);
								headerPane = new HeaderPane("Sales", "List");
								optionsPanel = new OptionsPanel();
								optionsPanel.getBtnAccept().setVisible(false);
								optionsPanel.getBtnContinue().setVisible(false);
								listSalePane = new ListSalePane();
								optionsPanel.getBtnExit().addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										headerPane.setVisible(false);
										optionsPanel.setVisible(false);
										listSalePane.setVisible(false);
										contentPane.remove(headerPane);
										contentPane.remove(optionsPanel);
										contentPane.remove(listSalePane);
										headerPane = null;
										optionsPanel = null;
										listSalePane = null;
										System.gc();
										title.setVisible(true);
										menuSalesPanel.setVisible(true);
									};
								});
								contentPane.add(optionsPanel, BorderLayout.SOUTH);
								contentPane.add(headerPane, BorderLayout.NORTH);
								contentPane.add(listSalePane, BorderLayout.CENTER);
								setVisible(true);
							};
						});

						menuSalesPanel.getBtnClearContentPane().addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								menuSalesPanel.setVisible(false);
								title.setVisible(false);
								contentPane.remove(menuSalesPanel);
								contentPane.remove(title);
								menuSalesPanel = null;
								title = null;
								System.gc();
								home.setVisible(true);
							};
						});

						contentPane.add(menuSalesPanel, BorderLayout.NORTH);
						contentPane.add(title, BorderLayout.CENTER);
						setVisible(true);
					}
				}
			});
			contentPane.add(home, BorderLayout.CENTER);
			setVisible(true);
		}
	}




	protected static SalesDataServiceImplementation getSalesDataService() {
		return salesDataService;
	}


	protected static ProductsDataServiceImplementation getProductsDataService() {

		return productsDataService;

	}


	protected static ProvidersDataServiceImplementation getProvidersDataService() {
		return providersDataService;
	}


	protected static RestocksDataServiceImplementation getRestocksDataService() {
		return restocksDataService;
	}





}
