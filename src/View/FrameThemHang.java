package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import View.FrameQuanLyBanHang.duLieu;
import controller.ArrayListSP;
import model.Date;
import model.NhomSanPham;
import model.SanPham;

public class FrameThemHang extends JDialog {
	JLabel lbStt, lbMaHang, lbTen, lbLoaiHang, lbHang4, lbNgay, lbThang, lbNam, lbTieuDe;
	JTextField txtStt, txtMaHang, txtTen, txtSoLuong, txtNgay, txtThang, txtNam, txtThemLoaiHang;
	JPanel hang1, hang1_1, hang2, hang3, hang4, hang5, hang6;
	JRadioButton rdMacDinh, rdEdit;
	static JRadioButton rdThemLoaiHang;
	ArrayListSP<NhomSanPham> dsNhom;
	JButton btnThem, btnlamMoi;
//	String[] listPhanLoai = { "Loai 1", "Loai 2", "Loai 3" };
//	JComboBox<String> jcbPhanLoai;
	JComboBox<NhomSanPham> jcbPhanLoai;
	static String loai;

//	int sttSoSanh = Integer.parseInt(txtStt.getText());
	public FrameThemHang() {

		giaoDien();
		xuLiSuKien();
		hienThi();
	}

	private void giaoDien() {

		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		lbTieuDe = new JLabel("Them hang");
		lbTieuDe.setAlignmentX(CENTER_ALIGNMENT);

		lbTen = new JLabel("Ten san pham");
		// hang 1
		Dimension dimLb = lbTen.getPreferredSize();
		Dimension dimtxt1 = new Dimension(80, 30);
		hang1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbMaHang = new JLabel("Ma san pham");
		lbMaHang.setPreferredSize(dimLb);// DÃ²ng nÃ y thÃªm vÃ o lÃ m cho bá»‹ máº¥t chá»¯
		txtMaHang = new JTextField(10);
		txtMaHang.setEditable(false);// mÃ£ sáº£n pháº©m khÃ´ng thá»ƒ thay Ä‘á»•i
		lbStt = new JLabel("STT");
//		lbStt.setPreferredSize(dimLb);
		txtStt = new JTextField(3);
		txtStt.setEditable(false); // khÃ´ng cho nháº­p stt khi chá»�n thÃªm máº·c Ä‘á»‹nh
		hang1_1 = new JPanel(new GridLayout(0, 1));// thÃªm 2 jradioBtn vÃ o 1 panel Ä‘á»ƒ hiá»ƒn thá»‹ dï¿½?c
		rdMacDinh = new JRadioButton("Mac dinh");
		rdEdit = new JRadioButton("Nhap stt­");
		ButtonGroup G = new ButtonGroup();
		G.add(rdMacDinh);
		G.add(rdEdit);
		rdMacDinh.setSelected(true);// chá»�n thÃªm máº·c Ä‘á»‹nh
		hang1_1.add(rdMacDinh);
		hang1_1.add(rdEdit);
		hang1.add(lbMaHang);
		hang1.add(txtMaHang);
		hang1.add(lbStt);
		hang1.add(txtStt);
		hang1.add(hang1_1);

		// hÃ ng 2
		Dimension dimLb2 = new Dimension(120, 20);
		Dimension dimtxt2 = new Dimension(300, 20);
		hang2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		lbTen.setPreferredSize(dimLb);
		txtTen = new JTextField();
		txtTen.setPreferredSize(dimtxt2);
		hang2.add(lbTen);
		hang2.add(txtTen);

		// hÃ ng 3
		hang3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		lbLoaiHang = new JLabel("Loai hang");
		lbLoaiHang.setPreferredSize(dimLb);
		jcbPhanLoai = new JComboBox<NhomSanPham>();
		jcbPhanLoai.setPreferredSize(dimtxt2);
		txtThemLoaiHang = new JTextField();
		txtThemLoaiHang.setPreferredSize(dimtxt2);
		txtThemLoaiHang.setVisible(false);
		rdThemLoaiHang = new JRadioButton("+");
		dsNhom = new ArrayListSP<NhomSanPham>();
		dsNhom = duLieu.duLieuDSNhom();
		Iterator<NhomSanPham> iter = dsNhom.iterator();
		while (iter.hasNext()) {
			NhomSanPham value = iter.next();
			jcbPhanLoai.addItem(value);
		}
		hang3.add(lbLoaiHang);
		hang3.add(jcbPhanLoai);
		hang3.add(txtThemLoaiHang);
		hang3.add(rdThemLoaiHang);

		// hÃ ng 4
//		Dimension dimLb4 = new Dimension(30, 20);
		Dimension dimtxt4 = new Dimension(60, 20);
		hang4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtSoLuong = new JTextField();
		txtNgay = new JTextField();
		txtNgay.setPreferredSize(dimtxt4);
		txtThang = new JTextField();
		txtThang.setPreferredSize(dimtxt4);
		txtNam = new JTextField();
		txtNam.setPreferredSize(dimtxt4);
		hang4.add(lbHang4 = new JLabel("So luong"));
		hang4.add(txtSoLuong);
		hang4.add(lbNgay = new JLabel("Nhap vao Ngay"));
		hang4.add(txtNgay);
		hang4.add(lbThang = new JLabel("Thang"));
		hang4.add(txtThang);
		hang4.add(lbNam = new JLabel("Nam"));
		hang4.add(txtNam);
		lbHang4.setPreferredSize(dimLb);
		txtSoLuong.setPreferredSize(dimtxt4);

		// hÃ ng 5
		hang5 = new JPanel();
		hang5.add(btnThem = new JButton("THEM"));
		hang5.add(btnlamMoi = new JButton("LAM MOI"));

		// hÃ ng 6
		hang6 = new JPanel();
		JLabel lbVer = new JLabel("<html><i>Version 1.0</i></html>");
		lbVer.setAlignmentX(CENTER_ALIGNMENT);
		hang6.add(lbVer);

		add(lbTieuDe);
		add(hang1);
		add(hang2);
		add(hang3);
		add(hang4);
		add(hang5);
		add(hang6);
	}

	private void xuLiSuKien() {
		rdThemLoaiHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (rdThemLoaiHang.isSelected()) {
					jcbPhanLoai.setVisible(false);
					txtThemLoaiHang.setVisible(true);
				} else {
					jcbPhanLoai.setVisible(true);
					txtThemLoaiHang.setVisible(false);
				}
			}
		});
		// xá»­ lÃ­ radio Edit vÃ  Default
		rdEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtStt.setEditable(true);
			}
		});
		rdMacDinh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtStt.setEditable(false);
			}
		});

		// chá»©c nÄƒng lÃ m má»›i
		btnlamMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtTen.setText(null);
				txtSoLuong.setText(null);
				txtNgay.setText(null);
				txtThang.setText(null);
				txtNam.setText(null);
			}
		});

		// chá»©c nÄƒng ThÃªm
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					int stt = Integer.parseInt(txtStt.getText());
					int id = Integer.parseInt(txtMaHang.getText());
					String tenSp = txtTen.getText();
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					int ngay = Integer.parseInt(txtNgay.getText());
					int thang = Integer.parseInt(txtThang.getText());
					int nam = Integer.parseInt(txtNam.getText());
					;
//					FrameQuanLyBanHang.dTM.setRowCount(0);// reset all hang table
					if (rdThemLoaiHang.isSelected()) {
						loai = txtThemLoaiHang.getText();
					} else {
						NhomSanPham nhomSelected = (NhomSanPham) jcbPhanLoai.getSelectedItem();
						loai = nhomSelected.getTenNhom();
					}

					SanPham spMoi = new SanPham(stt, id, tenSp, new NhomSanPham(loai), soLuong,
							new Date(ngay, thang, nam));
					FrameQuanLyBanHang.listSP.Add(spMoi);
					duLieu.dsNhom.Add(spMoi.getPhanLoai());

					SanPham value = FrameQuanLyBanHang.listSP.get(FrameQuanLyBanHang.listSP.getSize() - 1);
					Object[] obj = { value.getStt(), value.getId(), value.getTenSp(), value.getPhanLoai(),
							value.getSoLuong(), value.getNgayNhap() };
					FrameQuanLyBanHang.dTM.setRowCount(FrameQuanLyBanHang.dTM.getRowCount());
					FrameQuanLyBanHang.dTM.insertRow(stt - 1, obj);
					for (int i = 1; i <= FrameQuanLyBanHang.dTM.getRowCount(); i++) {

						FrameQuanLyBanHang.dTM.setValueAt(i, i - 1, 0);
					}

					JOptionPane.showMessageDialog(null, "Them thanh cong!!!");
					SanPham.count_id++;
					SanPham.count_stt++;
					dispose();
				} catch (Exception e2) {
					txtTen.setRequestFocusEnabled(true);
					JOptionPane.showMessageDialog(null, "Vui long nhap lai");

				}
			}
		});

	}

	private void hienThi() {
		setTitle("Them hang vao kho");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setAlwaysOnTop(true);
	}
}
