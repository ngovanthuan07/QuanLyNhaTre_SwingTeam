/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.views;

import com.swingteam.model.BacLuongModel;
import com.swingteam.model.ChucVuModel;
import com.swingteam.model.NhanVienModel;
import com.swingteam.service.impl.BacLuongService;
import com.swingteam.service.impl.ChucVuService;
import com.swingteam.service.impl.NhanVienService;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ngova
 */
public class NhanVienPanel extends javax.swing.JPanel {

    private NhanVienService nhanVienService;
    private BacLuongService bacLuongService;
    private ChucVuService chucVuService;
    DefaultTableModel modelBacLuong, modelChucVu, modelNhanVien, modelNhanVien_ChucVu, modelNhanVien_BacLuong;

    public NhanVienPanel() {
        initComponents();
        chucVuService = new ChucVuService();
        bacLuongService = new BacLuongService();

        try {
            List<ChucVuModel> chv = chucVuService.findAll();
            List<BacLuongModel> hsbl = bacLuongService.finAll();

            if (chv != null && chv.size() > 0) {
                cbbChucVu_NhanVien.removeAllItems();
                for (int i = 0; i < chv.size(); i++) {
                    ChucVuModel get = chv.get(i);
                    cbbChucVu_NhanVien.addItem(get.getTenChucVu());
                }
            }

            if (hsbl != null && hsbl.size() > 0) {
                cbbHeSoBacLuong_NhanVien.removeAllItems();
                for (int i = 0; i < hsbl.size(); i++) {
                    BacLuongModel get = hsbl.get(i);
                    cbbHeSoBacLuong_NhanVien.addItem(String.valueOf(get.getMaBacLuong()));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        renderBacLuong();
        ChucVuRender();
        nhanVienRender();
    }

    public void nhanVienRender() {
        nhanVienService = new NhanVienService();

        modelNhanVien = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien_NhanVien.setModel(modelNhanVien);

        modelNhanVien.addColumn("Stt");
        modelNhanVien.addColumn("M?? Nh??n Vi??n");
        modelNhanVien.addColumn("T??n Nh??n Vi??n");
        modelNhanVien.addColumn("Ng??y Sinh");
        modelNhanVien.addColumn("Gi???i T??nh");
        modelNhanVien.addColumn("cmnd");
        modelNhanVien.addColumn("Ng??y V??o L??m");
        modelNhanVien.addColumn("?????a Ch???");
        modelNhanVien.addColumn("S??? ??i???n Tho???i");
        modelNhanVien.addColumn("Tr???ng Th??i");

        modelNhanVien.addColumn("Ghi Ch??");
        modelNhanVien.addColumn("M?? B???c L????ng");
        modelNhanVien.addColumn("Ch???c V???");

        List<NhanVienModel> listNhanVien = nhanVienService.findAll();

        if (listNhanVien != null) {
            for (int i = 0; i < listNhanVien.size(); i++) {
                NhanVienModel get = listNhanVien.get(i);
                modelNhanVien.addRow(new Object[]{
                    (i + 1),
                    get.getMaNhanVien(),
                    get.getTenNhanVien(),
                    get.getNgaySinh(),
                    get.getGioiTinh() == 1 ? "Nam" : "N???",
                    get.getCmnd(),
                    get.getNgayVaoLam(),
                    get.getDiaChi(),
                    get.getSoDienThoai(),
                    get.getTrangThai() == 1 ? "??ang l??m" : "Ngh??? vi???c",
                    get.getGhiChu(),
                    get.getMaBacLuong(),
                    get.getTenChucVu()
                });
            }
        }

    }

    public void nhanVienKhongLamRender() {
        nhanVienService = new NhanVienService();

        modelNhanVien = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien_NhanVien.setModel(modelNhanVien);

        modelNhanVien.addColumn("Stt");
        modelNhanVien.addColumn("M?? Nh??n Vi??n");
        modelNhanVien.addColumn("T??n Nh??n Vi??n");
        modelNhanVien.addColumn("Ng??y Sinh");
        modelNhanVien.addColumn("Gi???i T??nh");
        modelNhanVien.addColumn("cmnd");
        modelNhanVien.addColumn("Ng??y V??o L??m");
        modelNhanVien.addColumn("?????a Ch???");
        modelNhanVien.addColumn("S??? ??i???n Tho???i");
        modelNhanVien.addColumn("Tr???ng Th??i");

        modelNhanVien.addColumn("Ghi Ch??");
        modelNhanVien.addColumn("M?? B???c L????ng");
        modelNhanVien.addColumn("Ch???c V???");

        List<NhanVienModel> listNhanVien = nhanVienService.findAllKhongLam();

        if (listNhanVien != null) {
            for (int i = 0; i < listNhanVien.size(); i++) {
                NhanVienModel get = listNhanVien.get(i);
                modelNhanVien.addRow(new Object[]{
                    (i + 1),
                    get.getMaNhanVien(),
                    get.getTenNhanVien(),
                    get.getNgaySinh(),
                    get.getGioiTinh() == 1 ? "Nam" : "N???",
                    get.getCmnd(),
                    get.getNgayVaoLam(),
                    get.getDiaChi(),
                    get.getSoDienThoai(),
                    get.getTrangThai() == 1 ? "??ang l??m" : "Ngh??? vi???c",
                    get.getGhiChu(),
                    get.getMaBacLuong(),
                    get.getTenChucVu()
                });
            }
        }

    }

    public void renderBacLuong() {
        bacLuongService = new BacLuongService();

        modelBacLuong = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_QuanLyBacLuong.setModel(modelBacLuong);
        modelBacLuong.addColumn("STT");
        modelBacLuong.addColumn("M?? B???c L????ng");
        modelBacLuong.addColumn("H??? S???");
        modelBacLuong.addColumn("L????ng C?? B???n");

        List<BacLuongModel> listBacLuong = bacLuongService.finAll();
        if (listBacLuong != null) {
            try {
                for (int i = 0; i < listBacLuong.size(); i++) {
                    BacLuongModel get = listBacLuong.get(i);
                    modelBacLuong.addRow(new Object[]{
                        (i + 1),
                        get.getMaBacLuong(),
                        get.getHeSoBac(),
                        get.getMucLuongCanBan()
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void ChucVuRender() {
        chucVuService = new ChucVuService();

        modelChucVu = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblChucVu_ChucVu.setModel(modelChucVu);
        modelChucVu.addColumn("Stt");
        modelChucVu.addColumn("M?? Ch???c V???");
        modelChucVu.addColumn("T??n Ch???c v???");

        List<ChucVuModel> listChucVu = chucVuService.findAll();

        if (listChucVu != null) {
            try {
                for (int i = 0; i < listChucVu.size(); i++) {
                    ChucVuModel get = listChucVu.get(i);
                    modelChucVu.addRow(new Object[]{
                        (i + 1),
                        get.getMaChucVu(),
                        get.getTenChucVu(),});
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public void clearChucVu() {
        txtMaChucVu_ChucVu.setText("");
        txtTenChucVu_ChucVu.setText("");
    }

    public void clearBacLuong() {
        txtMaBacLuong_QuanLyLuong.setText("");
        txtMucLuongCanBan_QuanLyLuong.setText("");
    }

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao,
                tieuDeThongBao, icon);
    }

    public void setSelectedCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null) {
                if (cbbselected.trim().equals(obj)) {
                    cbb.setSelectedItem(obj);
                }
            }
        }
    }

    public void clearNhanVien() {
        txtMaNhanVien_NhanVien1.setText("");
        txtTenNhanVien_NhanVien1.setText("");
        txtCmnd_NhanVien.setText("");
        cbbChucVu_NhanVien.setSelectedIndex(1);
        cbbHeSoBacLuong_NhanVien.setSelectedIndex(1);
        txtDiaChi_NhanVien.setText("");
        txtSoDT_NhanVien1.setText("");
        txtChuThich_NhanVien1.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanelNhanVien = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNhanVien_NhanVien = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        lblTenNhanVien_NhanVien1 = new javax.swing.JLabel();
        lblMaNhanVien_NhanVien1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtTenNhanVien_NhanVien1 = new javax.swing.JTextField();
        txtMaNhanVien_NhanVien1 = new javax.swing.JTextField();
        txtCmnd_NhanVien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNgayVaoLam_NhanVien = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        rbtnNam_NhanVien = new javax.swing.JRadioButton();
        rbtnNu_NhanVien = new javax.swing.JRadioButton();
        txtNgaySinh_NhanVien = new com.toedter.calendar.JDateChooser();
        lblDiaChi_NhanVien1 = new javax.swing.JLabel();
        lblSDT_NhanVien1 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtChuThich_NhanVien1 = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnThem_NhanVien1 = new javax.swing.JButton();
        btnSua_NhanVien1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtTimKiem_NhanVien1 = new javax.swing.JTextField();
        btnHienThKhongLam_NhanVien = new javax.swing.JToggleButton();
        btnXoa_NhanVien1 = new javax.swing.JButton();
        btnReset_NhanVien1 = new javax.swing.JButton();
        txtDiaChi_NhanVien = new javax.swing.JTextField();
        txtSoDT_NhanVien1 = new javax.swing.JTextField();
        cbbChucVu_NhanVien = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbbHeSoBacLuong_NhanVien = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbTrangThai_NhanVien = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelChucVu = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblChucVu_ChucVu = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTenChucVu_ChucVu = new javax.swing.JTextField();
        btnThem_ChucVu = new javax.swing.JButton();
        btnSua_ChucVu = new javax.swing.JButton();
        txtMaChucVu_ChucVu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblNhanVien_ChucVu = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien_QuanLyLuong = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaBacLuong_QuanLyLuong = new javax.swing.JTextField();
        cbbHeSoBac_QuanLyLuong = new javax.swing.JComboBox<>();
        txtMucLuongCanBan_QuanLyLuong = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnBacLuong_QuanLyLuong = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_QuanLyBacLuong = new javax.swing.JTable();

        jPanelNhanVien.setBackground(new java.awt.Color(102, 204, 255));
        jPanelNhanVien.setPreferredSize(new java.awt.Dimension(1030, 600));
        jPanelNhanVien.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelNhanVienComponentShown(evt);
            }
        });

        tblNhanVien_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "M?? Nh??n Vi??n", "T??n Nh??n Vi??n", "Ng??y Sinh ", "Gi???i T??nh", "CMND", "Ng??y V??o L??m", "Ch???c V???", "?????a Ch???", "S??? ??T", "Ghi Ch??"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVien_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblNhanVien_NhanVien);

        jPanel18.setBackground(new java.awt.Color(0, 204, 153));
        jPanel18.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel19.setBackground(new java.awt.Color(204, 153, 255));

        lblTenNhanVien_NhanVien1.setText("T??n Nh??n Vi??n");

        lblMaNhanVien_NhanVien1.setText("M?? Nh??n Vi??n");

        jLabel48.setText("Ng??y Sinh");

        jLabel49.setText("Gi???i T??nh");

        jLabel50.setText("Ng??y V??o L??m");

        txtMaNhanVien_NhanVien1.setEditable(false);

        jLabel1.setText("CMND");

        jPanel7.setBackground(new java.awt.Color(204, 153, 255));

        rbtnNam_NhanVien.setText("Nam");
        rbtnNam_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNam_NhanVienActionPerformed(evt);
            }
        });

        rbtnNu_NhanVien.setText("N???");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(rbtnNam_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(rbtnNu_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnNu_NhanVien)
                    .addComponent(rbtnNam_NhanVien))
                .addContainerGap())
        );

        txtNgaySinh_NhanVien.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtNgaySinh_NhanVienAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaNhanVien_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenNhanVien_NhanVien1)
                    .addComponent(jLabel50)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenNhanVien_NhanVien1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNhanVien_NhanVien1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCmnd_NhanVien)
                    .addComponent(txtNgayVaoLam_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(txtNgaySinh_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNhanVien_NhanVien1)
                    .addComponent(txtMaNhanVien_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNhanVien_NhanVien1)
                    .addComponent(txtTenNhanVien_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel49)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(txtNgaySinh_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtCmnd_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayVaoLam_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDiaChi_NhanVien1.setText("?????a Ch???");

        lblSDT_NhanVien1.setText("S??? ??T");

        txtChuThich_NhanVien1.setColumns(20);
        txtChuThich_NhanVien1.setRows(5);
        jScrollPane9.setViewportView(txtChuThich_NhanVien1);

        jLabel54.setText("Ch?? Th??ch");

        jLabel55.setText("Ch???c V???");

        btnThem_NhanVien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/add.png"))); // NOI18N
        btnThem_NhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_NhanVien1ActionPerformed(evt);
            }
        });

        btnSua_NhanVien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        btnSua_NhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_NhanVien1ActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(102, 153, 255));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("T??m Ki???m");

        txtTimKiem_NhanVien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiem_NhanVien1KeyPressed(evt);
            }
        });

        btnHienThKhongLam_NhanVien.setText("Nh??n vi??n ngh??? l??m");
        btnHienThKhongLam_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThKhongLam_NhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiem_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnHienThKhongLam_NhanVien)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnHienThKhongLam_NhanVien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXoa_NhanVien1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoa_NhanVien1.setText("???n");
        btnXoa_NhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_NhanVien1ActionPerformed(evt);
            }
        });

        btnReset_NhanVien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/reset.png"))); // NOI18N
        btnReset_NhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_NhanVien1ActionPerformed(evt);
            }
        });

        txtDiaChi_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChi_NhanVienActionPerformed(evt);
            }
        });

        txtSoDT_NhanVien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDT_NhanVien1ActionPerformed(evt);
            }
        });

        jLabel5.setText("H??? S??? B???c L????ng");

        jLabel13.setText("Tr???ng Th??i");

        cbTrangThai_NhanVien.setText("??ang l??m");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiaChi_NhanVien1)
                            .addComponent(lblSDT_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoDT_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbHeSoBacLuong_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua_NhanVien1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa_NhanVien1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset_NhanVien1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiaChi_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChucVu_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbTrangThai_NhanVien))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(btnThem_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReset_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnThem_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbChucVu_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55)))
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDiaChi_NhanVien1)
                            .addComponent(txtDiaChi_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTrangThai_NhanVien))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSDT_NhanVien1)
                                    .addComponent(txtSoDT_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua_NhanVien1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnXoa_NhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel54))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbbHeSoBacLuong_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)))
                .addGap(21, 21, 21))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Danh S??ch Nh??n Vi??n");

        javax.swing.GroupLayout jPanelNhanVienLayout = new javax.swing.GroupLayout(jPanelNhanVien);
        jPanelNhanVien.setLayout(jPanelNhanVienLayout);
        jPanelNhanVienLayout.setHorizontalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addGroup(jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(jLabel11))
                    .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanelNhanVienLayout.setVerticalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1171, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nh??n Vi??n", jPanel2);

        jPanelChucVu.setBackground(new java.awt.Color(102, 204, 255));
        jPanelChucVu.setPreferredSize(new java.awt.Dimension(1030, 600));
        jPanelChucVu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelChucVuComponentShown(evt);
            }
        });

        tblChucVu_ChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "M?? Ch???c V???", "T??n Ch???c V???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChucVu_ChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVu_ChucVuMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblChucVu_ChucVu);

        jPanel21.setBackground(new java.awt.Color(0, 204, 153));

        jLabel25.setText("M?? Ch???c V???");

        jLabel26.setText("T??n Ch???c V???");

        btnThem_ChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/add.png"))); // NOI18N
        btnThem_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_ChucVuActionPerformed(evt);
            }
        });

        btnSua_ChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        btnSua_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_ChucVuActionPerformed(evt);
            }
        });

        txtMaChucVu_ChucVu.setEditable(false);
        txtMaChucVu_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChucVu_ChucVuActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/reset.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnThem_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnSua_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTenChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtMaChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTenChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem_ChucVu)
                    .addComponent(btnSua_ChucVu)
                    .addComponent(jButton1))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tblNhanVien_ChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "M?? Nh??n Vi??n", "T??n Nh??n Vi??n", "Ch???c V???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblNhanVien_ChucVu);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Ch???c V???");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nh??n Vi??n");

        javax.swing.GroupLayout jPanelChucVuLayout = new javax.swing.GroupLayout(jPanelChucVu);
        jPanelChucVu.setLayout(jPanelChucVuLayout);
        jPanelChucVuLayout.setHorizontalGroup(
            jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChucVuLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
            .addGroup(jPanelChucVuLayout.createSequentialGroup()
                .addGroup(jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelChucVuLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jLabel7))
                    .addGroup(jPanelChucVuLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelChucVuLayout.setVerticalGroup(
            jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChucVuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 1157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Ch???c V???", jPanel1);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        tblNhanVien_QuanLyLuong.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblNhanVien_QuanLyLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "T??n Nh??n Vi??n", "H??? S??? B???c", "M???c l????ng c?? b???n", "T???ng L????ng"
            }
        ));
        jScrollPane1.setViewportView(tblNhanVien_QuanLyLuong);

        jPanel4.setBackground(new java.awt.Color(255, 153, 204));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("M?? B???c L????ng");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("H??? s??? b???c:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("M???a l????ng c?? b???n");

        txtMaBacLuong_QuanLyLuong.setEditable(false);

        cbbHeSoBac_QuanLyLuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbHeSoBac_QuanLyLuong, 0, 112, Short.MAX_VALUE)
                    .addComponent(txtMaBacLuong_QuanLyLuong)
                    .addComponent(txtMucLuongCanBan_QuanLyLuong))
                .addGap(105, 105, 105))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaBacLuong_QuanLyLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbHeSoBac_QuanLyLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMucLuongCanBan_QuanLyLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/remove.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnBacLuong_QuanLyLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/add.png"))); // NOI18N
        btnBacLuong_QuanLyLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBacLuong_QuanLyLuongActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/reset.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBacLuong_QuanLyLuong)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBacLuong_QuanLyLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addGap(116, 116, 116))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Danh S??ch L????ng C???a Nh??n Vi??n");

        tbl_QuanLyBacLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "M?? B???c L????ng", "H??? S???", "L????ng C??n B???n"
            }
        ));
        tbl_QuanLyBacLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QuanLyBacLuongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_QuanLyBacLuong);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel12)
                .addContainerGap(514, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(0, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );

        jTabbedPane1.addTab("Qu???n L?? L????ng", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVien_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien_NhanVienMouseClicked
        try {
            int row = tblNhanVien_NhanVien.getSelectedRow();
            txtMaNhanVien_NhanVien1.setText(tblNhanVien_NhanVien.getValueAt(row, 1).toString());
            txtTenNhanVien_NhanVien1.setText(tblNhanVien_NhanVien.getValueAt(row, 2).toString());
            txtCmnd_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 5).toString());
            cbbChucVu_NhanVien.setSelectedIndex(1);
            cbbHeSoBacLuong_NhanVien.setSelectedIndex(1);
            txtDiaChi_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 7).toString());
            txtSoDT_NhanVien1.setText(tblNhanVien_NhanVien.getValueAt(row, 8).toString());
            txtChuThich_NhanVien1.setText(tblNhanVien_NhanVien.getValueAt(row, 10).toString());
            Date date_NgaySinh_NhanVien, data_NgayVaoLam_NhanVien;
            date_NgaySinh_NhanVien = new SimpleDateFormat("yyyy-MM-dd").parse((String) tblNhanVien_NhanVien.getValueAt(row, 3).toString());
            txtNgaySinh_NhanVien.setDate(date_NgaySinh_NhanVien);
            String trangThai = tblNhanVien_NhanVien.getValueAt(row, 9).toString().trim();
            if (trangThai.equals("??ang l??m")) {
                cbTrangThai_NhanVien.setSelected(true);
            } else {
                cbTrangThai_NhanVien.setSelected(false);
            }

            data_NgayVaoLam_NhanVien = new SimpleDateFormat("yyyy-MM-dd").parse((String) tblNhanVien_NhanVien.getValueAt(row, 6).toString());
            txtNgayVaoLam_NhanVien.setDate(data_NgayVaoLam_NhanVien);

            String gioitinh = tblNhanVien_NhanVien.getValueAt(row, 4).toString().trim();
            if (gioitinh.equals("Nam")) {
                rbtnNam_NhanVien.setSelected(true);
                rbtnNu_NhanVien.setSelected(false);
            } else {
                rbtnNu_NhanVien.setSelected(true);
                rbtnNam_NhanVien.setSelected(false);
            }

            setSelectedCombobox(tblNhanVien_NhanVien.getValueAt(row, 11).toString(), cbbHeSoBacLuong_NhanVien);
            setSelectedCombobox(tblNhanVien_NhanVien.getValueAt(row, 12).toString(), cbbChucVu_NhanVien);

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_tblNhanVien_NhanVienMouseClicked

    private void rbtnNam_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNam_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnNam_NhanVienActionPerformed

    private void btnThem_NhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_NhanVien1ActionPerformed

        String regex = "^\\d{9}$";
        if (txtTenNhanVien_NhanVien1.getText().equals("") || txtCmnd_NhanVien.getText().equals("") || txtDiaChi_NhanVien.getText().equals("")
                || txtSoDT_NhanVien1.getText().equals("")) {
            ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
            return;
        }
        if (txtCmnd_NhanVien.getText().trim().matches(regex) == false) {
            ThongBao("so cmnd ph???i 9 s??? c??n c???a b???n l?? " + txtCmnd_NhanVien.getText().length() + " s???", "L???i", 1);
            return;
        }
        if (txtSoDT_NhanVien1.getText().trim().matches("^\\d{10}$") == false) {
            ThongBao("so sdt ph???i 10 c??n c???a b???n l??: " + txtSoDT_NhanVien1.getText().length() + " s???", "L???i", 1);
            return;
        }

        try {
            nhanVienService = new NhanVienService();
            chucVuService = new ChucVuService();
            int gioiTinh = 0;
            int trangThai = 1;
            NhanVienModel nv = new NhanVienModel();

            if (rbtnNam_NhanVien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            nv.setMaNhanVien("");

            nv.setTenNhanVien(txtTenNhanVien_NhanVien1.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txtNgaySinh_NhanVien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txtNgayVaoLam_NhanVien.getDate());

            System.out.println(date);
            if (date.equals("") || date2.equals("")) {
                ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
                return;
            }

            nv.setNgaySinh(date);

            nv.setGioiTinh(gioiTinh);

            nv.setCmnd(txtCmnd_NhanVien.getText());

            nv.setNgayVaoLam(date2);

            nv.setDiaChi(txtDiaChi_NhanVien.getText());

            nv.setSoDienThoai(txtSoDT_NhanVien1.getText());

            nv.setTrangThai(trangThai);

            nv.setGhiChu(txtChuThich_NhanVien1.getText());

            List<ChucVuModel> chucvu = chucVuService.findByTenChucVu(cbbChucVu_NhanVien.getSelectedItem().toString().trim());
            if (chucvu != null) {
                nv.setMaChucVu(chucvu.get(0).getMaChucVu());
            }
            nv.setMaBacLuong(cbbHeSoBacLuong_NhanVien.getSelectedItem().toString().trim());

            String index = nhanVienService.save(nv);
            ThongBao("Th??m Th??nh c??ng", "Th??nh c??ng", 3);
            nhanVienRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearNhanVien();
        }


    }//GEN-LAST:event_btnThem_NhanVien1ActionPerformed

    private void btnSua_NhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_NhanVien1ActionPerformed
        int row = tblNhanVien_NhanVien.getSelectedRow();
        if (row == -1) {
            ThongBao("Vui l??ng ch???n ?? v??o b???ng ????? ch???nh s???a", "erro", 2);
            return;
        }
        if (txtTenNhanVien_NhanVien1.getText().equals("") || txtCmnd_NhanVien.getText().equals("") || txtDiaChi_NhanVien.getText().equals("")
                || txtSoDT_NhanVien1.getText().equals("")) {
            ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
            return;
        }
        if (txtCmnd_NhanVien.getText().trim().matches("^\\d{9}$") == false) {
            ThongBao("so cmnd ph???i 9 s??? c??n c???a b???n l?? " + txtCmnd_NhanVien.getText().length() + " s???", "L???i", 2);
            return;
        }
        if (txtSoDT_NhanVien1.getText().trim().matches("^\\d{10}$") == false) {
            ThongBao("so sdt ph???i 10 c??n c???a b???n l??: " + txtSoDT_NhanVien1.getText().length() + " s???", "L???i", 2);
            return;
        }
        try {
            nhanVienService = new NhanVienService();
            chucVuService = new ChucVuService();
            int gioiTinh = 0;
            NhanVienModel nv = new NhanVienModel();

            if (rbtnNam_NhanVien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            String maNhanVien = txtMaNhanVien_NhanVien1.getText().trim();

            nv.setTenNhanVien(txtTenNhanVien_NhanVien1.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txtNgaySinh_NhanVien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txtNgayVaoLam_NhanVien.getDate());

            nv.setNgaySinh(date);

            nv.setGioiTinh(gioiTinh);

            nv.setCmnd(txtCmnd_NhanVien.getText());

            nv.setNgayVaoLam(date2);

            nv.setDiaChi(txtDiaChi_NhanVien.getText());

            nv.setSoDienThoai(txtSoDT_NhanVien1.getText());

            if (cbTrangThai_NhanVien.isSelected() == true) {
                nv.setTrangThai(1);
            } else {
                nv.setTrangThai(0);
            }

            nv.setGhiChu(txtChuThich_NhanVien1.getText());

            nv.setMaBacLuong(cbbHeSoBacLuong_NhanVien.getSelectedItem().toString().trim());

            nhanVienService.edit(nv, maNhanVien);
            ThongBao("S???a th??nh c??ng", "th??nh c??ng", 3);
            nhanVienRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearNhanVien();
        }


    }//GEN-LAST:event_btnSua_NhanVien1ActionPerformed

    private void txtTimKiem_NhanVien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem_NhanVien1KeyPressed
        char c = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel table = (DefaultTableModel) tblNhanVien_NhanVien.getModel();
            String search = txtTimKiem_NhanVien1.getText().toLowerCase().trim();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
            tblNhanVien_NhanVien.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }

    }//GEN-LAST:event_txtTimKiem_NhanVien1KeyPressed

    private void btnXoa_NhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_NhanVien1ActionPerformed
        int row = tblNhanVien_NhanVien.getSelectedRow();
        if (row == -1) {
            ThongBao("Vui l??ng ch???n ?? v??o b???ng ????? ???n", "erro", row);
            return;
        }
        try {
            nhanVienService = new NhanVienService();
            chucVuService = new ChucVuService();
            int gioiTinh = 0;
            NhanVienModel nv = new NhanVienModel();

            if (rbtnNam_NhanVien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            String maNhanVien = txtMaNhanVien_NhanVien1.getText().trim();

            nv.setTenNhanVien(txtTenNhanVien_NhanVien1.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txtNgaySinh_NhanVien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txtNgayVaoLam_NhanVien.getDate());

            nv.setNgaySinh(date);

            nv.setGioiTinh(gioiTinh);

            nv.setCmnd(txtCmnd_NhanVien.getText());

            nv.setNgayVaoLam(date2);

            nv.setDiaChi(txtDiaChi_NhanVien.getText());

            nv.setSoDienThoai(txtSoDT_NhanVien1.getText());

            nv.setTrangThai(0);

            nv.setGhiChu(txtChuThich_NhanVien1.getText());

            nv.setMaBacLuong(cbbHeSoBacLuong_NhanVien.getSelectedItem().toString().trim());

            nhanVienService.edit(nv, maNhanVien);
            ThongBao("???n th??nh c??ng", "th??nh c??ng", 2);
            nhanVienRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearNhanVien();
        }

    }//GEN-LAST:event_btnXoa_NhanVien1ActionPerformed

    private void btnReset_NhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_NhanVien1ActionPerformed
        txtMaNhanVien_NhanVien1.setText("");
        txtTenNhanVien_NhanVien1.setText("");
        txtCmnd_NhanVien.setText("");
        cbbChucVu_NhanVien.setSelectedIndex(1);
        cbbHeSoBacLuong_NhanVien.setSelectedIndex(1);
        txtDiaChi_NhanVien.setText("");
        txtSoDT_NhanVien1.setText("");
        txtChuThich_NhanVien1.setText("");
    }//GEN-LAST:event_btnReset_NhanVien1ActionPerformed

    private void txtDiaChi_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChi_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChi_NhanVienActionPerformed

    private void txtSoDT_NhanVien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDT_NhanVien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDT_NhanVien1ActionPerformed

    private void jPanelNhanVienComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelNhanVienComponentShown

    }//GEN-LAST:event_jPanelNhanVienComponentShown

    private void tblChucVu_ChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVu_ChucVuMouseClicked
        try {

            int row = tblChucVu_ChucVu.getSelectedRow();
            txtMaChucVu_ChucVu.setText(tblChucVu_ChucVu.getValueAt(row, 1).toString());
            txtTenChucVu_ChucVu.setText(tblChucVu_ChucVu.getValueAt(row, 2).toString());

            String maChucVu = tblChucVu_ChucVu.getValueAt(row, 1).toString().trim();

            nhanVienService = new NhanVienService();

            modelNhanVien_ChucVu = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tblNhanVien_ChucVu.setModel(modelNhanVien_ChucVu);

            modelNhanVien_ChucVu.addColumn("Stt");
            modelNhanVien_ChucVu.addColumn("M?? Nh??n Vi??n");
            modelNhanVien_ChucVu.addColumn("T??n Nh??n Vi??n");
            modelNhanVien_ChucVu.addColumn("Ch???c V???");

            List<NhanVienModel> listNhanVien = nhanVienService.findByCodeMaNhanVien(maChucVu);

            if (listNhanVien != null) {
                for (int i = 0; i < listNhanVien.size(); i++) {
                    NhanVienModel get = listNhanVien.get(i);
                    modelNhanVien_ChucVu.addRow(new Object[]{
                        (i + 1),
                        get.getMaNhanVien(),
                        get.getTenNhanVien(),
                        get.getTenChucVu()
                    });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblChucVu_ChucVuMouseClicked

    private void btnThem_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_ChucVuActionPerformed
        if (txtTenChucVu_ChucVu.getText().equals("")) {
            ThongBao("B???n nh???p b??? thi???u", "L???i", 2);
            return;
        }
        try {
            chucVuService = new ChucVuService();
            ChucVuModel newChucVu = new ChucVuModel();
            newChucVu.setMaChucVu("");
            newChucVu.setTenChucVu(txtTenChucVu_ChucVu.getText());
            String indexChucVu = chucVuService.save(newChucVu);
            ThongBao("Th??nh C??ng", "Th??nh C??ng", 3);

            ChucVuRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearChucVu();
        }
    }//GEN-LAST:event_btnThem_ChucVuActionPerformed

    private void btnSua_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_ChucVuActionPerformed
        try {
            chucVuService = new ChucVuService();
            int row = tblChucVu_ChucVu.getSelectedRow();
            if (row == -1) {
                ThongBao("vui l??ng ch???n v??o ?? ????? ch???nh s???a", "l???i", 2);
                return;
            }
            if (txtTenChucVu_ChucVu.getText().equals("")) {
                ThongBao("B???n nh???p b??? thi???u", "L???i", 2);
                return;
            }
            ChucVuModel cv = new ChucVuModel();
            cv.setMaChucVu(txtMaChucVu_ChucVu.getText());
            cv.setTenChucVu((txtTenChucVu_ChucVu.getText()));
            chucVuService.edit(cv, cv.getMaChucVu());
            ThongBao("S???a th??nh c??ng", "th??nh c??ng", 3);
            ChucVuRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearChucVu();
        }
    }//GEN-LAST:event_btnSua_ChucVuActionPerformed

    private void txtMaChucVu_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChucVu_ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChucVu_ChucVuActionPerformed

    private void jPanelChucVuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelChucVuComponentShown
        return;
    }//GEN-LAST:event_jPanelChucVuComponentShown

    private void btnBacLuong_QuanLyLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBacLuong_QuanLyLuongActionPerformed
        // TODO add your handling code here:
        if (txtMucLuongCanBan_QuanLyLuong.getText().equals("")) {
            ThongBao("Bi Trong", "Loi", 2);
            return;
        }
        if (txtMucLuongCanBan_QuanLyLuong.getText().matches("^\\d{0,20}$") == false) {
            ThongBao("Ban Phai Nhap La So", "Loi", 2);
            return;
        }
        try {
            bacLuongService = new BacLuongService();
            BacLuongModel bl = new BacLuongModel();
            bl.setMaBacLuong("");
            bl.setHeSoBac(Integer.parseInt(cbbHeSoBac_QuanLyLuong.getSelectedItem().toString()));
            bl.setMucLuongCanBan(Long.parseLong(txtMucLuongCanBan_QuanLyLuong.getText()));
            String index = bacLuongService.save(bl);
            ThongBao("Th??nh C??ng", "Th??nh C??ng", 3);
            renderBacLuong();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearBacLuong();
        }
    }//GEN-LAST:event_btnBacLuong_QuanLyLuongActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearChucVu();
        ChucVuRender();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHienThKhongLam_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThKhongLam_NhanVienActionPerformed
        // TODO add your handling code here:
        if (btnHienThKhongLam_NhanVien.isSelected() == true) {
            nhanVienKhongLamRender();
            btnHienThKhongLam_NhanVien.setText("Nh??n vi??n ??ang l??m");
        } else {
            nhanVienRender();
            btnHienThKhongLam_NhanVien.setText("Nh??n vi??n ngh??? l??m");
        }
    }//GEN-LAST:event_btnHienThKhongLam_NhanVienActionPerformed

    private void tbl_QuanLyBacLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QuanLyBacLuongMouseClicked
        // TODO add your handling code here:
        int row = tbl_QuanLyBacLuong.getSelectedRow();
        String maBacLuong = tbl_QuanLyBacLuong.getValueAt(row, 1).toString().trim();

        txtMaBacLuong_QuanLyLuong.setText(maBacLuong);
        setSelectedCombobox(tbl_QuanLyBacLuong.getValueAt(row, 2).toString(), cbbHeSoBac_QuanLyLuong);
        txtMucLuongCanBan_QuanLyLuong.setText(tbl_QuanLyBacLuong.getValueAt(row, 3).toString().trim());

        try {
            nhanVienService = new NhanVienService();

            modelNhanVien_BacLuong = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tblNhanVien_QuanLyLuong.setModel(modelNhanVien_BacLuong);

            modelNhanVien_BacLuong.addColumn("Stt");
            modelNhanVien_BacLuong.addColumn("M?? Nh??n Vi??n");
            modelNhanVien_BacLuong.addColumn("T??n Nh??n Vi??n");
            modelNhanVien_BacLuong.addColumn("H??? s??? b???c");
            modelNhanVien_BacLuong.addColumn("M???c luong");
            modelNhanVien_BacLuong.addColumn("S??? ti???n / th??ng");
            List<NhanVienModel> listNhanVien = nhanVienService.findByCodeMaBacLuong(maBacLuong);

            if (listNhanVien != null) {
                for (int i = 0; i < listNhanVien.size(); i++) {
                    NhanVienModel get = listNhanVien.get(i);
                    modelNhanVien_BacLuong.addRow(new Object[]{
                        (i + 1),
                        get.getMaNhanVien(),
                        get.getTenNhanVien(),
                        get.getHeSoBac(),
                        get.getMucLuongCanBan(),
                        get.getTongLuong()
                    });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tbl_QuanLyBacLuongMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtMaBacLuong_QuanLyLuong.setText("");
        txtMucLuongCanBan_QuanLyLuong.setText("");
        renderBacLuong();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            int row = tbl_QuanLyBacLuong.getSelectedRow();
            if (row == -1) {
                ThongBao("Vui l??ng ch???n ?? ????? ch???nh s???a", "L???i", 2);
                return;
            }
            if (txtMucLuongCanBan_QuanLyLuong.getText().equals("")) {
                ThongBao("Bi Trong", "Loi", 2);
                return;
            }
            if (txtMucLuongCanBan_QuanLyLuong.getText().matches("^\\d{0,20}$") == false) {
                ThongBao("Ban Phai Nhap La So", "Loi", 2);
                return;
            }
            BacLuongModel bacluong = new BacLuongModel();
            bacLuongService = new BacLuongService();

            bacluong.setMaBacLuong(txtMaBacLuong_QuanLyLuong.getText());
            bacluong.setHeSoBac(Integer.parseInt((String) cbbHeSoBac_QuanLyLuong.getSelectedItem()));
            bacluong.setMucLuongCanBan(Long.parseLong(txtMucLuongCanBan_QuanLyLuong.getText()));

            bacLuongService.edit(bacluong, bacluong.getMaBacLuong());
            ThongBao("S???a th??nh c??ng", "th??nh c??ng", 3);
            renderBacLuong();
            clearBacLuong();
        } catch (Exception e) {
            System.out.println(e);
        } 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            int row = tbl_QuanLyBacLuong.getSelectedRow();
            if (row == -1) {
                ThongBao("Vui l??ng ch???n ?? ????? ch???nh x??a", "L???i", 2);
                return;
            }

            bacLuongService = new BacLuongService();

            String maBacLuong = txtMaBacLuong_QuanLyLuong.getText();

            bacLuongService.delete(maBacLuong);
            ThongBao("X??a th??nh c??ng", "Th??nh c??ng", 3);
            renderBacLuong();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearBacLuong();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNgaySinh_NhanVienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtNgaySinh_NhanVienAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgaySinh_NhanVienAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBacLuong_QuanLyLuong;
    private javax.swing.JToggleButton btnHienThKhongLam_NhanVien;
    private javax.swing.JButton btnReset_NhanVien1;
    private javax.swing.JButton btnSua_ChucVu;
    private javax.swing.JButton btnSua_NhanVien1;
    private javax.swing.JButton btnThem_ChucVu;
    private javax.swing.JButton btnThem_NhanVien1;
    private javax.swing.JButton btnXoa_NhanVien1;
    private javax.swing.JCheckBox cbTrangThai_NhanVien;
    private javax.swing.JComboBox<String> cbbChucVu_NhanVien;
    private javax.swing.JComboBox<String> cbbHeSoBacLuong_NhanVien;
    private javax.swing.JComboBox<String> cbbHeSoBac_QuanLyLuong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelChucVu;
    private javax.swing.JPanel jPanelNhanVien;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDiaChi_NhanVien1;
    private javax.swing.JLabel lblMaNhanVien_NhanVien1;
    private javax.swing.JLabel lblSDT_NhanVien1;
    private javax.swing.JLabel lblTenNhanVien_NhanVien1;
    private javax.swing.JRadioButton rbtnNam_NhanVien;
    private javax.swing.JRadioButton rbtnNu_NhanVien;
    private javax.swing.JTable tblChucVu_ChucVu;
    private javax.swing.JTable tblNhanVien_ChucVu;
    private javax.swing.JTable tblNhanVien_NhanVien;
    private javax.swing.JTable tblNhanVien_QuanLyLuong;
    private javax.swing.JTable tbl_QuanLyBacLuong;
    private javax.swing.JTextArea txtChuThich_NhanVien1;
    private javax.swing.JTextField txtCmnd_NhanVien;
    private javax.swing.JTextField txtDiaChi_NhanVien;
    private javax.swing.JTextField txtMaBacLuong_QuanLyLuong;
    private javax.swing.JTextField txtMaChucVu_ChucVu;
    private javax.swing.JTextField txtMaNhanVien_NhanVien1;
    private javax.swing.JTextField txtMucLuongCanBan_QuanLyLuong;
    private com.toedter.calendar.JDateChooser txtNgaySinh_NhanVien;
    private com.toedter.calendar.JDateChooser txtNgayVaoLam_NhanVien;
    private javax.swing.JTextField txtSoDT_NhanVien1;
    private javax.swing.JTextField txtTenChucVu_ChucVu;
    private javax.swing.JTextField txtTenNhanVien_NhanVien1;
    private javax.swing.JTextField txtTimKiem_NhanVien1;
    // End of variables declaration//GEN-END:variables
}
