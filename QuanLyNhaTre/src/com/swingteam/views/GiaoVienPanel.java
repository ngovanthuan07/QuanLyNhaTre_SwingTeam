/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.views;

import com.swingteam.model.BacLuongModel;
import com.swingteam.model.ChucVuModel;
import com.swingteam.model.GiaoVienModel;
import com.swingteam.service.impl.BacLuongService;
import com.swingteam.service.impl.ChucVuService;
import com.swingteam.service.impl.GiaoVienService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngova
 */
public class GiaoVienPanel extends javax.swing.JPanel {

    public GiaoVienService giaoVienService;
    private BacLuongService bacLuongService;
    DefaultTableModel modelBacLuong, modelGiaoVien_BacLuong, modelGiaoVien_QuanLyGiaoVien;
    public String cmnd = null;
    public String sdt = null;

    public GiaoVienPanel() {
       initComponents();

        bacLuongService = new BacLuongService();
        try {
            List<BacLuongModel> hsbl = bacLuongService.finAll();

            if (hsbl != null && hsbl.size() > 0) {
                cbb_hesobacluong_danhsachgiaovien.removeAllItems();
                for (int i = 0; i < hsbl.size(); i++) {
                    BacLuongModel get = hsbl.get(i);
                    cbb_hesobacluong_danhsachgiaovien.addItem(String.valueOf(get.getMaBacLuong()));
                    cbbBacLuong_Luong.addItem(String.valueOf(get.getMaBacLuong()));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        giaoVienRender();
        giaoVien_Luong_Render();
    }

    public void giaoVien_Luong_Render() {
        giaoVienService = new GiaoVienService();

        modelGiaoVien_BacLuong = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_luong_giaovien.setModel(modelGiaoVien_BacLuong);

        modelGiaoVien_BacLuong.addColumn("Stt");
        modelGiaoVien_BacLuong.addColumn("M?? Gi??o Vi??n");
        modelGiaoVien_BacLuong.addColumn("T??n gi??o vi??n");
        modelGiaoVien_BacLuong.addColumn("M?? B???c L????ng");
        modelGiaoVien_BacLuong.addColumn("L????ng c??n b???n");
        modelGiaoVien_BacLuong.addColumn("T???ng L????ng");

        List<GiaoVienModel> listNhanVien = giaoVienService.findAllBacLuong_GiaoVien();

        if (listNhanVien != null) {
            for (int i = 0; i < listNhanVien.size(); i++) {
                GiaoVienModel get = listNhanVien.get(i);
                modelGiaoVien_BacLuong.addRow(new Object[]{
                    (i + 1),
                    get.getMaGiaoVien(),
                    get.getTenGiaoVien(),
                    get.getMaBacLuong(),
                    get.getMucLuongCanBan(),
                    get.getTongLuong()
                });
            }
        }
    }

    public void giaoVienRender() {
        giaoVienService = new GiaoVienService();

        modelGiaoVien_QuanLyGiaoVien = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_danhsachgiaovien.setModel(modelGiaoVien_QuanLyGiaoVien);

        modelGiaoVien_QuanLyGiaoVien.addColumn("Stt");
        modelGiaoVien_QuanLyGiaoVien.addColumn("M?? Gi??o Vi??n");
        modelGiaoVien_QuanLyGiaoVien.addColumn("T??n Gi??o Vi??n");
        modelGiaoVien_QuanLyGiaoVien.addColumn("Ng??y Sinh");
        modelGiaoVien_QuanLyGiaoVien.addColumn("Gi???i T??nh");
        modelGiaoVien_QuanLyGiaoVien.addColumn("cmnd");
        modelGiaoVien_QuanLyGiaoVien.addColumn("Ng??y V??o L??m");
        modelGiaoVien_QuanLyGiaoVien.addColumn("?????a Ch???");
        modelGiaoVien_QuanLyGiaoVien.addColumn("S??? ??i???n Tho???i");
        modelGiaoVien_QuanLyGiaoVien.addColumn("Tr???ng Th??i");

        modelGiaoVien_QuanLyGiaoVien.addColumn("Ghi Ch??");
        modelGiaoVien_QuanLyGiaoVien.addColumn("M?? B???c L????ng");

        List<GiaoVienModel> listNhanVien = giaoVienService.findAll();

        if (listNhanVien != null) {
            for (int i = 0; i < listNhanVien.size(); i++) {
                GiaoVienModel get = listNhanVien.get(i);
                modelGiaoVien_QuanLyGiaoVien.addRow(new Object[]{
                    (i + 1),
                    get.getMaGiaoVien(),
                    get.getTenGiaoVien(),
                    get.getNgaySinh(),
                    get.getGioiTinh() == 1 ? "Nam" : "N???",
                    get.getCmnd(),
                    get.getNgayVaoLam(),
                    get.getDiaChi(),
                    get.getSoDienThoai(),
                    get.getTrangThai() == 1 ? "??ang l??m" : "Ngh??? vi???c",
                    get.getGhiChu(),
                    get.getMaBacLuong()});
            }
        }
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

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao,
                tieuDeThongBao, icon);
    }

    public void clearGiaoVien_DanhSachGiaoVien() {
        txt_magiaovien_danhsachgiaovien.setText("");
        txt_tengiaovien_danhsachgiaovien.setText("");
        txt_diachi_danhsachgiaovien.setText("");
        txt_sodienthoai_danhsachgiaovien.setText("");
        txt_cmnd_danhsachgiaovien.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhsachgiaovien = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt_cmnd_danhsachgiaovien = new java.awt.TextField();
        txt_magiaovien_danhsachgiaovien = new java.awt.TextField();
        txt_diachi_danhsachgiaovien = new java.awt.TextField();
        jLabel10 = new javax.swing.JLabel();
        txt_sodienthoai_danhsachgiaovien = new java.awt.TextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cb_nam_danhsachgiaovien = new javax.swing.JRadioButton();
        cb_nu_danhsachgiaovien = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_ghichu_danhsachgiaovien = new javax.swing.JTextArea();
        txt_ngaysinh_danhsachgiaovien = new com.toedter.calendar.JDateChooser();
        txt_ngayvaolam_danhsachgiaovien = new com.toedter.calendar.JDateChooser();
        cbb_hesobacluong_danhsachgiaovien = new javax.swing.JComboBox<>();
        txt_tengiaovien_danhsachgiaovien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        textField11 = new java.awt.TextField();
        textField12 = new java.awt.TextField();
        textField13 = new java.awt.TextField();
        textField15 = new java.awt.TextField();
        textField16 = new java.awt.TextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cbb_GiaoVienKhenThuong = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtHinhThucKhenThuong = new java.awt.TextField();
        jButton15 = new javax.swing.JButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        khenThuong_KhenThuong = new javax.swing.JRadioButton();
        namHoc_luong = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNoiDungKhenThuong = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_magiaovien_Luong = new java.awt.TextField();
        txt_tengiaovien_luong = new java.awt.TextField();
        jLabel33 = new javax.swing.JLabel();
        txt_luong_giaovien = new java.awt.TextField();
        txt_luongcanban_giaovien = new java.awt.TextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_luong_giaovien = new javax.swing.JTable();
        cbbBacLuong_Luong = new javax.swing.JComboBox<>();

        jTabbedPane3.setBackground(new java.awt.Color(0, 153, 102));
        jTabbedPane3.setPreferredSize(new java.awt.Dimension(1221, 734));

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));
        jPanel2.setForeground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QU???N L?? GI??O VI??N");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("T??n gi??o vi??n");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("M?? gi??o vi??n");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Gi???i t??nh");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Ng??y sinh");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Ng??y v??o l??m");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("?????a ch???");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("S??? CMND/CCCD");

        jPanel4.setBackground(new java.awt.Color(0, 204, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tbl_danhsachgiaovien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "T??n GV", "M?? GV", "Gi???i t??nh", "Ng??y sinh", "Ng??y v??o l??m", "?????a ch???", "S??? CMND", "Tr??nh ?????"
            }
        ));
        tbl_danhsachgiaovien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhsachgiaovienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhsachgiaovien);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/add.png"))); // NOI18N
        jButton1.setText("Th??m");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        jButton2.setText("S???a");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/remove.png"))); // NOI18N
        jButton3.setText("X??a");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/reset.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txt_magiaovien_danhsachgiaovien.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("S??? ??i???n tho???i");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Ghi ch??");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("H??? s??? b???c l????ng");

        cb_nam_danhsachgiaovien.setText("Nam");

        cb_nu_danhsachgiaovien.setText("N???");

        txt_ghichu_danhsachgiaovien.setColumns(20);
        txt_ghichu_danhsachgiaovien.setRows(5);
        jScrollPane3.setViewportView(txt_ghichu_danhsachgiaovien);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cmnd_danhsachgiaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(txt_ngaysinh_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ngayvaolam_danhsachgiaovien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_magiaovien_danhsachgiaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                    .addComponent(txt_tengiaovien_danhsachgiaovien))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_sodienthoai_danhsachgiaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(txt_diachi_danhsachgiaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cb_nam_danhsachgiaovien)
                                .addGap(71, 71, 71)
                                .addComponent(cb_nu_danhsachgiaovien))
                            .addComponent(jScrollPane3)
                            .addComponent(cbb_hesobacluong_danhsachgiaovien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(353, 353, 353)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_magiaovien_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_nam_danhsachgiaovien)
                        .addComponent(cb_nu_danhsachgiaovien)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_tengiaovien_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngaysinh_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cmnd_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayvaolam_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_diachi_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sodienthoai_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_hesobacluong_danhsachgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 1, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Danh s??ch gi??o vi??n", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("N??m h???c");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("QU???N L?? GI??O VI??N");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("????n v???");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019-2020", "2020-2021", " " }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T??? to??n", "T??? v??n", " " }));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "C??c ti??u ch??", "T??? ????nh gi??", "T??? chuy??n m??n", "Hi???u tr?????ng ????nh gi??"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Danh s??ch gi??o vi??n");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nguy???n V??n A", " " }));

        jPanel8.setBackground(new java.awt.Color(0, 204, 153));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Nh???ng ??i???m m???nh");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Nh???ng ??i???m y???u");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("H?????ng  ph??t huy ??i???m m???nh, kh???c ph???c ??i???m y???u");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Nh???n x??t c???a t??? chuy??n m??n");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Nh???n x??t c???a hi???u tr?????ng");

        textField11.setEnabled(false);

        textField12.setEnabled(false);

        textField13.setEnabled(false);

        textField15.setEnabled(false);

        textField16.setEnabled(false);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setText("Export");

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton7.setText("Import");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textField16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addComponent(textField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textField12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textField13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton6)
                                .addGap(60, 60, 60)
                                .addComponent(jButton7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5.setText("C???p nh???t");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(899, 899, 899)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(429, 429, 429))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))))
        );

        jTabbedPane3.addTab("????nh gi?? gi??o vi??n", jPanel3);

        jPanel9.setBackground(new java.awt.Color(0, 204, 153));

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 51, 51));
        jLabel44.setText("QU???N L?? GI??O VI??N");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setText("N??m h???c");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setText("Danh s??ch gi??o vi??n");

        cbb_GiaoVienKhenThuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "T??n gi??o vi??n", "M?? gi??o vi??n", "N??m h???c", "Quy???t ?????nh khen th?????ng, k??? lu???t", "Th??nh t??ch, Khuy???t ??i???m", "Ph???n th?????ng, khi???n tr??ch"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel48.setText("L???a ch???n");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel49.setText("N???i dung");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setText("H??nh th???c");

        txtHinhThucKhenThuong.setEnabled(false);

        jButton15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/print-131964753156480777.png"))); // NOI18N
        jButton15.setText("In danh s??ch");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jRadioButton7.setText("K??? lu???t");

        khenThuong_KhenThuong.setText("Khen th?????ng");

        txtNoiDungKhenThuong.setColumns(20);
        txtNoiDungKhenThuong.setRows(5);
        jScrollPane5.setViewportView(txtNoiDungKhenThuong);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel45)
                            .addComponent(jLabel49))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namHoc_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_GiaoVienKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(40, 40, 40)
                                .addComponent(txtHinhThucKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(khenThuong_KhenThuong)
                                .addGap(10, 10, 10)
                                .addComponent(jRadioButton7))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(538, 538, 538)
                        .addComponent(jButton15))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jLabel44))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namHoc_luong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(khenThuong_KhenThuong)
                                .addComponent(jRadioButton7)))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_GiaoVienKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(txtHinhThucKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );

        jTabbedPane3.addTab("Khen th?????ng, k??? lu???t", jPanel9);

        jPanel6.setBackground(new java.awt.Color(0, 204, 153));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 51));
        jLabel31.setText("QU???N L?? GI??O VI??N");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("T??n gi??o vi??n");

        txt_magiaovien_Luong.setEditable(false);
        txt_magiaovien_Luong.setEnabled(false);

        txt_tengiaovien_luong.setEditable(false);
        txt_tengiaovien_luong.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("M?? gi??o vi??n");

        txt_luong_giaovien.setEditable(false);

        txt_luongcanban_giaovien.setEditable(false);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("B???c l????ng");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("L????ng c?? b???n");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("L????ng");

        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tbl_luong_giaovien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "T??n GV", "M?? GV", "H??? s??? l????ng", "L????ng c?? b???n", "l????ng"
            }
        ));
        tbl_luong_giaovien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_luong_giaovienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_luong_giaovien);

        cbbBacLuong_Luong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBacLuong_LuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(328, 328, 328)
                            .addComponent(jLabel31))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(485, 485, 485)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_luongcanban_giaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addComponent(txt_luong_giaovien, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addComponent(txt_tengiaovien_luong, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addComponent(txt_magiaovien_Luong, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addComponent(cbbBacLuong_Luong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(581, 581, 581)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 430, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txt_magiaovien_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_tengiaovien_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(txt_luongcanban_giaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_luong_giaovien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbBacLuong_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(103, 103, 103))
        );

        jTabbedPane3.addTab("L????ng", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_magiaovien_danhsachgiaovien.setText("");
        txt_tengiaovien_danhsachgiaovien.setText("");
        txt_diachi_danhsachgiaovien.setText("");
        txt_sodienthoai_danhsachgiaovien.setText("");
        txt_cmnd_danhsachgiaovien.setText("");
        giaoVienRender();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        giaoVienService = new GiaoVienService();
        int row = tbl_danhsachgiaovien.getSelectedRow();
        if (row == -1 || txt_cmnd_danhsachgiaovien.getText().equals("") || txt_sodienthoai_danhsachgiaovien.getText().equals("")) {
            ThongBao("Vui l??ng ch???n ?? v??o b???ng ????? x??a", "erro", 2);
            return;
        }
        try {

            int gioiTinh = 1;
            GiaoVienModel gv = new GiaoVienModel();

            if (cb_nam_danhsachgiaovien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            gv.setMaGiaoVien(txt_magiaovien_danhsachgiaovien.getText());

            gv.setTenGiaoVien(txt_tengiaovien_danhsachgiaovien.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txt_ngaysinh_danhsachgiaovien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txt_ngayvaolam_danhsachgiaovien.getDate());

            if (date.equals("") || date2.equals("")) {
                ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
                return;
            }

            gv.setNgaySinh(date);

            gv.setGioiTinh(gioiTinh);

            gv.setCmnd(txt_cmnd_danhsachgiaovien.getText());

            gv.setNgayVaoLam(date2);

            gv.setDiaChi(txt_diachi_danhsachgiaovien.getText());

            gv.setSoDienThoai(txt_sodienthoai_danhsachgiaovien.getText());

            gv.setTrangThai(0);

            gv.setGhiChu(txt_ghichu_danhsachgiaovien.getText());

            gv.setMaBacLuong(cbb_hesobacluong_danhsachgiaovien.getSelectedItem().toString().trim());

            giaoVienService.edit(gv, gv.getMaGiaoVien(), 1);
            giaoVienRender();
            ThongBao("X??a Th??nh c??ng", "Th??nh c??ng", 3);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearGiaoVien_DanhSachGiaoVien();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        giaoVienService = new GiaoVienService();
        if (txt_tengiaovien_danhsachgiaovien.getText().equals("") || txt_cmnd_danhsachgiaovien.getText().equals("") || txt_diachi_danhsachgiaovien.getText().equals("")
                || txt_sodienthoai_danhsachgiaovien.getText().equals("")) {
            ThongBao("B???n nh???p b??? thi???u", "L???i", 2);
            return;
        }
        if (txt_cmnd_danhsachgiaovien.getText().trim().matches("^\\d{9}$") == false) {
            ThongBao("so cmnd ph???i 9 s??? c??n c???a b???n l?? " + txt_cmnd_danhsachgiaovien.getText().length() + " s???", "L???i", 2);
            return;
        }
        if (txt_sodienthoai_danhsachgiaovien.getText().trim().matches("^\\d{10}$") == false) {
            ThongBao("so sdt ph???i 10 c??n c???a b???n l??: " + txt_sodienthoai_danhsachgiaovien.getText().length() + " s???", "L???i", 2);
            return;
        }
        if (giaoVienService.findByCMND(txt_cmnd_danhsachgiaovien.getText().trim())) {
            ThongBao("cmnd b??? tr??ng", "loi", 2);
            return;
        }
        if (giaoVienService.findBySDT(txt_sodienthoai_danhsachgiaovien.getText().trim())) {
            ThongBao("s??? ??i???n tho???i b??? tr??ng", "loi", 2);
            return;
        }
        try {

            int gioiTinh = 1;
            GiaoVienModel gv = new GiaoVienModel();

            if (cb_nam_danhsachgiaovien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            gv.setMaGiaoVien("");

            gv.setTenGiaoVien(txt_tengiaovien_danhsachgiaovien.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txt_ngaysinh_danhsachgiaovien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txt_ngayvaolam_danhsachgiaovien.getDate());

            if (date.equals("") || date2.equals("")) {
                ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
                return;
            }

            gv.setNgaySinh(date);

            gv.setGioiTinh(gioiTinh);

            gv.setCmnd(txt_cmnd_danhsachgiaovien.getText());

            gv.setNgayVaoLam(date2);

            gv.setDiaChi(txt_diachi_danhsachgiaovien.getText());

            gv.setSoDienThoai(txt_sodienthoai_danhsachgiaovien.getText());

            gv.setTrangThai(1);

            gv.setGhiChu(txt_ghichu_danhsachgiaovien.getText());

            gv.setMaBacLuong(cbb_hesobacluong_danhsachgiaovien.getSelectedItem().toString().trim());

            String index = giaoVienService.save(gv);
            ThongBao("Th??m Th??nh c??ng", "Th??nh c??ng", 2);
            giaoVienRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearGiaoVien_DanhSachGiaoVien();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_danhsachgiaovienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhsachgiaovienMouseClicked
        try {
            int row = tbl_danhsachgiaovien.getSelectedRow();
            txt_magiaovien_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 1).toString());
            txt_tengiaovien_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 2).toString());
            txt_cmnd_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 5).toString());
            cmnd = tbl_danhsachgiaovien.getValueAt(row, 5).toString();
            cbb_hesobacluong_danhsachgiaovien.setSelectedIndex(1);
            txt_diachi_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 7).toString());
            txt_sodienthoai_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 8).toString());
            sdt = tbl_danhsachgiaovien.getValueAt(row, 8).toString();
            txt_ghichu_danhsachgiaovien.setText(tbl_danhsachgiaovien.getValueAt(row, 10).toString());
            Date date_NgaySinh_GiaoVien, data_NgayVaoLam_NhanVien;
            date_NgaySinh_GiaoVien = new SimpleDateFormat("yyyy-MM-dd").parse((String) tbl_danhsachgiaovien.getValueAt(row, 3).toString());
            txt_ngaysinh_danhsachgiaovien.setDate(date_NgaySinh_GiaoVien);

            data_NgayVaoLam_NhanVien = new SimpleDateFormat("yyyy-MM-dd").parse((String) tbl_danhsachgiaovien.getValueAt(row, 6).toString());
            txt_ngayvaolam_danhsachgiaovien.setDate(data_NgayVaoLam_NhanVien);

            String gioitinh = tbl_danhsachgiaovien.getValueAt(row, 4).toString().trim();
            if (gioitinh.equals("Nam")) {
                cb_nam_danhsachgiaovien.setSelected(true);
                cb_nu_danhsachgiaovien.setSelected(false);
            } else {
                cb_nu_danhsachgiaovien.setSelected(true);
                cb_nam_danhsachgiaovien.setSelected(false);
            }

            setSelectedCombobox(tbl_danhsachgiaovien.getValueAt(row, 11).toString(), cbb_hesobacluong_danhsachgiaovien);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tbl_danhsachgiaovienMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        giaoVienService = new GiaoVienService();
        int row = tbl_danhsachgiaovien.getSelectedRow();
        if (row == -1) {
            ThongBao("Vui l??ng ch???n ?? v??o b???ng ????? ch???nh s???a", "erro", 2);
            return;
        }
        if (txt_tengiaovien_danhsachgiaovien.getText().equals("") || txt_cmnd_danhsachgiaovien.getText().equals("") || txt_diachi_danhsachgiaovien.getText().equals("")
                || txt_sodienthoai_danhsachgiaovien.getText().equals("")) {
            ThongBao("B???n nh???p b??? thi???u", "L???i", 2);
            return;
        }
        if (txt_cmnd_danhsachgiaovien.getText().trim().matches("^\\d{9}$") == false) {
            ThongBao("so cmnd ph???i 9 s??? c??n c???a b???n l?? " + txt_cmnd_danhsachgiaovien.getText().length() + " s???", "L???i", 2);
            return;
        }
        if (txt_sodienthoai_danhsachgiaovien.getText().trim().matches("^\\d{10}$") == false) {
            ThongBao("so sdt ph???i 10 c??n c???a b???n l??: " + txt_sodienthoai_danhsachgiaovien.getText().length() + " s???", "L???i", 2);
            return;
        }
        if (!txt_cmnd_danhsachgiaovien.getText().trim().equals(cmnd)) {
            if (giaoVienService.findByCMND(txt_cmnd_danhsachgiaovien.getText().trim())) {
                ThongBao("cmnd b??? tr??ng", "loi", 2);
                return;
            }
        }
        if (!txt_sodienthoai_danhsachgiaovien.getText().trim().equals(sdt)) {
            if (giaoVienService.findBySDT(txt_sodienthoai_danhsachgiaovien.getText().trim())) {
                ThongBao("s??? ??i???n tho???i b??? tr??ng", "loi", 2);
                return;
            }
        }

        try {
            int gioiTinh = 1;
            GiaoVienModel gv = new GiaoVienModel();

            if (cb_nam_danhsachgiaovien.isSelected()) {
                gioiTinh = 1;
            } else {
                gioiTinh = 0;
            }
            gv.setMaGiaoVien(txt_magiaovien_danhsachgiaovien.getText());

            gv.setTenGiaoVien(txt_tengiaovien_danhsachgiaovien.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txt_ngaysinh_danhsachgiaovien.getDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = sdf.format(txt_ngayvaolam_danhsachgiaovien.getDate());

            if (date.equals("") || date2.equals("")) {
                ThongBao("B???n nh???p b??? thi???u", "L???i", 1);
                return;
            }

            gv.setNgaySinh(date);

            gv.setGioiTinh(gioiTinh);

            gv.setCmnd(txt_cmnd_danhsachgiaovien.getText());

            gv.setNgayVaoLam(date2);

            gv.setDiaChi(txt_diachi_danhsachgiaovien.getText());

            gv.setSoDienThoai(txt_sodienthoai_danhsachgiaovien.getText());

            gv.setTrangThai(1);

            gv.setGhiChu(txt_ghichu_danhsachgiaovien.getText());

            gv.setMaBacLuong(cbb_hesobacluong_danhsachgiaovien.getSelectedItem().toString().trim());

            giaoVienService.edit(gv, gv.getMaGiaoVien(), 1);
            ThongBao("S???a Th??nh c??ng", "Th??nh c??ng", 3);
            giaoVienRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearGiaoVien_DanhSachGiaoVien();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbBacLuong_LuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBacLuong_LuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBacLuong_LuongActionPerformed

    private void tbl_luong_giaovienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_luong_giaovienMouseClicked
        // TODO add your handling code here:
        int row = tbl_luong_giaovien.getSelectedRow();
        
        txt_magiaovien_Luong.setText(tbl_luong_giaovien.getValueAt(row, 1).toString());
        txt_tengiaovien_luong.setText(tbl_luong_giaovien.getValueAt(row, 2).toString());
        setSelectedCombobox(tbl_danhsachgiaovien.getValueAt(row, 3).toString(), cbbBacLuong_Luong);
        txt_luongcanban_giaovien.setText(tbl_luong_giaovien.getValueAt(row, 4).toString());
        txt_luong_giaovien.setText(tbl_luong_giaovien.getValueAt(row, 5).toString());


    }//GEN-LAST:event_tbl_luong_giaovienMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        giaoVienService = new GiaoVienService();
        int row = tbl_luong_giaovien.getSelectedRow();
        if (row == -1) {
            ThongBao("Vui l??ng ch???n ?? v??o b???ng ????? ch???nh s???a", "erro", 2);
            return;
        }
        try {
            int gioiTinh = 1;
            GiaoVienModel gv = new GiaoVienModel();
            gv.setMaGiaoVien(txt_magiaovien_Luong.getText());
            gv.setMaBacLuong(cbbBacLuong_Luong.getSelectedItem().toString().trim());
            giaoVienService.edit(gv, gv.getMaGiaoVien(), 2);
            ThongBao("S???a Th??nh c??ng", "Th??nh c??ng", 3);

            txt_tengiaovien_luong.setText("");
            txt_magiaovien_Luong.setText("");
            txt_luongcanban_giaovien.setText("");
            txt_luong_giaovien.setText("");

            giaoVien_Luong_Render();
            giaoVienRender();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cb_nam_danhsachgiaovien;
    private javax.swing.JRadioButton cb_nu_danhsachgiaovien;
    private javax.swing.JComboBox<String> cbbBacLuong_Luong;
    private javax.swing.JComboBox<String> cbb_GiaoVienKhenThuong;
    private javax.swing.JComboBox<String> cbb_hesobacluong_danhsachgiaovien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable6;
    private javax.swing.JRadioButton khenThuong_KhenThuong;
    private com.toedter.calendar.JDateChooser namHoc_luong;
    private javax.swing.JTable tbl_danhsachgiaovien;
    private javax.swing.JTable tbl_luong_giaovien;
    private java.awt.TextField textField11;
    private java.awt.TextField textField12;
    private java.awt.TextField textField13;
    private java.awt.TextField textField15;
    private java.awt.TextField textField16;
    private java.awt.TextField txtHinhThucKhenThuong;
    private javax.swing.JTextArea txtNoiDungKhenThuong;
    private java.awt.TextField txt_cmnd_danhsachgiaovien;
    private java.awt.TextField txt_diachi_danhsachgiaovien;
    private javax.swing.JTextArea txt_ghichu_danhsachgiaovien;
    private java.awt.TextField txt_luong_giaovien;
    private java.awt.TextField txt_luongcanban_giaovien;
    private java.awt.TextField txt_magiaovien_Luong;
    private java.awt.TextField txt_magiaovien_danhsachgiaovien;
    private com.toedter.calendar.JDateChooser txt_ngaysinh_danhsachgiaovien;
    private com.toedter.calendar.JDateChooser txt_ngayvaolam_danhsachgiaovien;
    private java.awt.TextField txt_sodienthoai_danhsachgiaovien;
    private javax.swing.JTextField txt_tengiaovien_danhsachgiaovien;
    private java.awt.TextField txt_tengiaovien_luong;
    // End of variables declaration//GEN-END:variables
}
