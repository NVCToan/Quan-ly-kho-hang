package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.NhomSanPham;
import model.SanPham;

public class FrameQuanLyBanHang extends JFrame {

	JMenuBar jmb = new JMenuBar();
	JMenu jmnCongCu;
	JMenuItem jmnThoat;
	JMenuItem jmnThemSP;
	JMenuItem jmnXoa;
	JMenuItem jmnChinhSua;
	public JButton btnXoa, btnTimKiem, btnXuatHang;
	//static JButton btnHuy; tai sao btnHuy lai cho static
	JLabel lbTieuDe, lbLoaiHang, lbSapXep, lbversion;
	static JLabel lbSucChua, lbThongBao;
	JPanel hang1, hang1_1, hang1_2,hang1_3, hang2,hang2_1, hang3;
	JTextField txtTimKiem;
	static DefaultTableModel dTM = new DefaultTableModel();
	static JTable table;
	int rowSelected = -1;
	NhomSanPham nhomselected = null;
	String[] tenCot = { "STT", "Ma hang", "Ten hang", "Loai hang", "So luong", "Ngay nhap" };
	static JComboBox<NhomSanPham> jcbLoaiHang = new JComboBox<NhomSanPham>();
	static JComboBox<NhomSanPham> jcbSapXep = new JComboBox<NhomSanPham>();
	FrameThemHang themHangUI = new FrameThemHang();
	FrameXuatHang xuatHangUI = new FrameXuatHang();
	FrameCongCu congCu = new FrameCongCu();
	static ArrayListSP<SanPham> listSP = new ArrayListSP<SanPham>();// suc chua mac dinh

	public FrameQuanLyBanHang() {
		super("Quan ly kho hang");
		giaoDien();
		new duLieu();
		xuLiSuKien();
		testConsole();
		hienThi();
	}

	private void giaoDien() {
		
		jmnCongCu = new JMenu("Cong Cu");
		jmnThoat = new JMenuItem("Thoat");
		jmnThemSP = new JMenuItem("Them SP");
		jmnXoa = new JMenuItem("Xoa SP");
		jmnChinhSua = new JMenuItem("Chinh Sua");
		
		jmnCongCu.add(jmnThemSP);
		jmnCongCu.add(jmnXoa);
		jmnCongCu.add(jmnChinhSua);
		jmb.add(jmnCongCu);
		jmb.add(jmnThoat);
		setJMenuBar(jmb);	
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		
		// tieu de
		lbTieuDe = new JLabel("QUAN LY KHO HANG");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);
		add(lbTieuDe);
		add(pnMain);	
		// hang 1
		Dimension dimTxT = new Dimension(130, 25);
		Dimension dimButton = new Dimension(30, 25);
		hang1 = new JPanel(new FlowLayout());
		
		hang1_2 = new JPanel();
		hang1_2.add(lbLoaiHang = new JLabel("Loai hang"));
		jcbLoaiHang.setPreferredSize(dimTxT);
		hang1_2.add(jcbLoaiHang);
		hang1.add(hang1_2);
		
		hang1_3 = new JPanel();
		hang1_3.add(lbSapXep = new JLabel("Sap Xep"));
		jcbSapXep.setPreferredSize(dimTxT);
		hang1_3.add(jcbSapXep);
		hang1.add(hang1_3);
		btnXoa = new JButton("Xoa");
		btnXoa.setEnabled(false);
		hang1_1 = new JPanel();
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(dimTxT);
		hang1_1.add(txtTimKiem);
		hang1_1.add(btnTimKiem = new JButton("Tim kiem"));
		hang1.add(hang1_1);
		hang1.add(btnXoa);

		// hang2
		hang2 = new JPanel();
		hang2.setLayout(new BorderLayout());
		for (int i = 0; i < tenCot.length; i++) {
			dTM.addColumn(tenCot[i]);

		}
		duLieu.taoDoiTuong();
		Iterator<SanPham> iter = listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai().getTenNhom(),
					value.getSoLuong(), value.getNgayNhap() };
			dTM.addRow(obj);
		}
		resetStt_SP();
		resetStt_DTM();

		table = new JTable(dTM);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.disable();

		JScrollPane hehe = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension dimTable = new Dimension(400,270);
		hehe.setPreferredSize(dimTable);
		
		//phan hien thi thong bao
		hang2_1 = new JPanel();
		hang2_1.setLayout(new BorderLayout());
		JPanel pnThongBao = new JPanel();
		pnThongBao.add(new JLabel("Ket qua :"));
		pnThongBao.add(lbThongBao= new JLabel("Da tim thay "+FrameQuanLyBanHang.listSP.getSize()+" san pham"));
		JPanel pnSucChua = new JPanel();
		pnSucChua.add(new JLabel("Suc chua :"));
		pnSucChua.add(lbSucChua = new JLabel(FrameQuanLyBanHang.listSP.getSize()+"/"+FrameQuanLyBanHang.listSP.CAPACITY));
		hang2_1.add(pnThongBao,BorderLayout.WEST);
		hang2_1.add(pnSucChua,BorderLayout.EAST);
		hang2.add(hang2_1,BorderLayout.NORTH);
		hang2.add(hehe, BorderLayout.CENTER);

		// hang 3
		hang3 = new JPanel();
		hang3.add(btnXuatHang = new JButton("Xuat hang"));

		JPanel pnEast = new JPanel();
		pnEast.setPreferredSize(new Dimension(31, 0));
		JPanel pnWest = new JPanel();
		pnWest.setPreferredSize(new Dimension(31, 0));
//		pnMain.add(lbTieuDe);
		pnMain.add(hang1, BorderLayout.NORTH);
		pnMain.add(hang2, BorderLayout.CENTER);
		pnMain.add(hang3, BorderLayout.SOUTH);
		pnMain.add(pnEast, BorderLayout.EAST);
		pnMain.add(pnWest, BorderLayout.WEST);
		
		lbversion = new JLabel("Version 1.0");
		lbversion.setAlignmentX(CENTER_ALIGNMENT);
		add(lbversion);
	}
	private void hienThi() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void xuLiSuKien() {
		jmnThemSP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int indexSpCuoi = FrameQuanLyBanHang.listSP.getSize()-1;
				int tangId = FrameQuanLyBanHang.listSP.get(indexSpCuoi).getId()+1;
				int tangStt = FrameQuanLyBanHang.listSP.get(indexSpCuoi).getStt()+1;
				themHangUI.txtMaHang.setText(""+tangId);
				themHangUI.txtStt.setText("" +tangStt);
				themHangUI.setModal(true); 
				themHangUI.setVisible(true);
			}
		});
		jmnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "Anh oi o lai ... >_<","Thoat",JOptionPane.YES_NO_OPTION);
				if (n == 1) { // 0 la yes, 1 la no
					System.exit(0);
				}
			}
		});

		btnXuatHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuatHangUI.setModal(true);
				xuatHangUI.setVisible(true);
			}
		});

		jmnChinhSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				congCu.setModal(true); 
				congCu.setVisible(true);
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = rowSelected;
				System.out.println(rowSelected);
				FrameQuanLyBanHang.listSP.remove(rowSelected);
				resetSucChua();
				resetDTM();
				resetThongBao();
				resetStt_DTM();
				resetStt_SP();
				txtTimKiem.setText(null);
				JOptionPane.showMessageDialog(null, "Xoa thanh cong !");
				testConsole();
				btnXoa.setEnabled(false);
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt(txtTimKiem.getText());
					if (timKiemSP(ID)!=null) {
						SanPham value = timKiemSP(ID);
						rowSelected = value.getStt() - 1;
						Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
								value.getSoLuong(), value.getNgayNhap() };
						dTM.setRowCount(0);
						dTM.addRow(obj);
						resetThongBao();
						btnXoa.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Khong tim thay SP !");
						txtTimKiem.setText(null);
					}
				} catch (Exception e2) {
					txtTimKiem.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "ID khong hop le !");
					txtTimKiem.setText(null);
				}

			}
		});
		jcbLoaiHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dTM.setRowCount(0);
				nhomselected = (NhomSanPham) jcbLoaiHang.getSelectedItem();
				if (jcbLoaiHang.getSelectedIndex() == 0) {
					FrameQuanLyBanHang.resetDTM();
					resetThongBao();
					FrameQuanLyBanHang.resetStt_DTM();
				}
				else {
					Iterator<SanPham> iter = listSP.iterator();
					while (iter.hasNext()) {
						SanPham value = iter.next();
						if (value.getPhanLoai().getTenNhom() == nhomselected.getTenNhom()) {
							Object[] obj = { value.getStt(), value.getId(), value.getTenSp(),
									value.getPhanLoai().getTenNhom(), value.getSoLuong(), value.getNgayNhap() };
							dTM.addRow(obj);
						}
					}
					resetStt_DTM();
					resetThongBao();
				}
			}
		});
		
	}

	static class duLieu {
		static ArrayListSP<NhomSanPham> dsNhom = new ArrayListSP<NhomSanPham>();

		public static void taoDoiTuong() {// dung trong function giaoDien
			SanPham sp1 = new SanPham(1, 10000, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp2 = new SanPham(2, 10001, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp3 = new SanPham(3, 10002, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp4 = new SanPham(4, 10003, "San pham 4", new NhomSanPham("Loai 2"), 520,
					new model.Date(2, 5, 1990));
			SanPham sp5 = new SanPham(5, 10004, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			SanPham sp6 = new SanPham(1, 10005, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp7 = new SanPham(2, 10006, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp8 = new SanPham(3, 10007, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp9 = new SanPham(4, 10008, "San pham 4", new NhomSanPham("Loai 2"), 520,
					new model.Date(2, 5, 1990));
			SanPham sp10 = new SanPham(5, 10009, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			SanPham sp11 = new SanPham(1, 10010, "San pham 1", new NhomSanPham("Loai 1"), 20,
					new model.Date(2, 01, 2023));
			SanPham sp12 = new SanPham(2, 10011, "San pham 2", new NhomSanPham("Loai 2"), 40,
					new model.Date(12, 5, 2010));
			SanPham sp13 = new SanPham(3, 10012, "San pham 3", new NhomSanPham("Loai 3"), 25,
					new model.Date(8, 1, 2000));
			SanPham sp14 = new SanPham(4, 10013, "San pham 4", new NhomSanPham("Loai 2"), 520,
					new model.Date(2, 5, 1990));
			SanPham sp15 = new SanPham(5, 10014, "San pham 5", new NhomSanPham("Loai 1"), 79,
					new model.Date(7, 4, 2002));
			listSP.Add(sp1);
			listSP.Add(sp2);
			listSP.Add(sp3);
			listSP.Add(sp4);
			listSP.Add(sp5);
			listSP.Add(sp6);
			listSP.Add(sp7);
			listSP.Add(sp8);
			listSP.Add(sp9);
			listSP.Add(sp10);
			listSP.Add(sp11);
			listSP.Add(sp12);
			listSP.Add(sp13);
			listSP.Add(sp14);
			listSP.Add(sp15);

			SanPham.count_id = FrameQuanLyBanHang.listSP.get(FrameQuanLyBanHang.listSP.getSize() - 1).getId() + 1;
			SanPham.count_stt = FrameQuanLyBanHang.listSP.getSize() + 1; // Tu dong set theo stt va ID hien co trong
																			// danh sach
		}

		public static ArrayListSP<NhomSanPham> duLieuDSNhom() {
			
			NhomSanPham nhom0 = new NhomSanPham("ALL");
			NhomSanPham nhom1 = new NhomSanPham("Loai 1");
			NhomSanPham nhom2 = new NhomSanPham("Loai 2");
			NhomSanPham nhom3 = new NhomSanPham("Loai 3");
			dsNhom.Add(nhom0);
			dsNhom.Add(nhom1);
			dsNhom.Add(nhom2);
			dsNhom.Add(nhom3);
			Iterator<NhomSanPham> iter = dsNhom.iterator();
			while (iter.hasNext()) {
				NhomSanPham value = iter.next();
				jcbLoaiHang.addItem(value);
			}
			return dsNhom;
			
		}
//		public static ArrayListSP<NhomSanPham> mtDSNhom()b
//		{
//			return dsNhom;
//		}
	}
	public static void testConsole() {
		StringBuffer sb = new StringBuffer();
		sb.append("Stt-SP"+"\t"+"Ten-SP"+"\t\t"+"Ma-SP"+"\n");
		for (int i = 0; i < FrameQuanLyBanHang.listSP.getSize(); i++) {
				sb.append(FrameQuanLyBanHang.listSP.get(i).getStt()+"\t\t"+FrameQuanLyBanHang.listSP.get(i).getTenSp()+"\t"+FrameQuanLyBanHang.listSP.get(i).getId()+"\n");
		}
		sb.append("------------------------------------------");
		System.out.println(sb);
	}
	public static void resetStt_DTM() {
		for (int i = 0; i < FrameQuanLyBanHang.dTM.getRowCount(); i++) {
			FrameQuanLyBanHang.dTM.setValueAt(i+1,i,0);
		}
	}
	public static void resetDTM() {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		dTM.setRowCount(0);
		while (iter.hasNext()) {
			SanPham value = iter.next();
				Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
						value.getSoLuong(), value.getNgayNhap() };
				dTM.addRow(obj);
			}
	}
	public static void resetStt_SP() {
		for (int i = 0; i < FrameQuanLyBanHang.listSP.getSize(); i++) {
			FrameQuanLyBanHang.listSP.get(i).setStt(i+1);
		}
		}
	public static void resetSucChua() {
		FrameQuanLyBanHang.lbSucChua.setText(FrameQuanLyBanHang.listSP.getSize()+"/"+FrameQuanLyBanHang.listSP.CAPACITY);;
	}
	public static void resetThongBao() {
		FrameQuanLyBanHang.lbThongBao.setText("Da tim thay "+FrameQuanLyBanHang.dTM.getRowCount()+" san pham");
	}
	public static SanPham timKiemSP(int id) {
		Iterator<SanPham> iter = FrameQuanLyBanHang.listSP.iterator();
		while (iter.hasNext()) {
			SanPham value = iter.next();
			if (value.getId() == id) {
				return value;
			}
		}
		return null;
	}
}
