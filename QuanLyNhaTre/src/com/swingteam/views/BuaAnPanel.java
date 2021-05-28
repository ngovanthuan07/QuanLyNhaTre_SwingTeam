/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.views;

import com.swingteam.SQLconnect.SwinTeamConnect;
import static com.swingteam.SQLconnect.SwinTeamConnect.password;
import static com.swingteam.SQLconnect.SwinTeamConnect.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.swingteam.model.BacLuongModel;
import com.swingteam.model.ChucVuModel;
import com.swingteam.model.NhanVienModel;
import com.swingteam.service.impl.BacLuongService;
import com.swingteam.service.impl.ChucVuService;
import com.swingteam.service.impl.NhanVienService;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.swingteam.model.BacLuongModel;
import com.swingteam.model.ChucVuModel;
import com.swingteam.model.NhanVienModel;
import com.swingteam.service.impl.BacLuongService;
import com.swingteam.service.impl.ChucVuService;
import com.swingteam.service.impl.NhanVienService;
import java.sql.SQLException;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_MONTH;
import java.util.Date;
import javax.rmi.CORBA.Util;
import sun.util.locale.LocaleExtensions;

/**
 *
 * @author ngova
 */
public class BuaAnPanel extends javax.swing.JPanel {

    private Connection conn = null, connCheck = null;
    private ResultSet rs = null, rsCheck = null;
    private PreparedStatement pst;
    private String sql = "SELECT * FROM QuanLyBuaAn";
    private ChucVuService chucVuService;
    private DefaultTableModel modelChucVu, modelOKThuan;

    /**
     * Creates new form BuaAnPanel
     */
    public BuaAnPanel() {
        initComponents();
        loaddata(sql);
        this.loadcombobox();
        this.cb_lop.removeAllItems();
        this.loadlop();
        this.getMcv();
    }
    
    public Connection getConnection() throws SQLException {// connect con Ngo Van Thuan
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHATRE";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public void connection() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loaddata(String sql) {
        try {
            connection();
            String[] arry = {"ID", "Ngày Nhập", "Ngày Báo Ăn", "Số Xuất Ăn", "Thực Đơn Sáng", "Thực Đơn Trưa", "Thực Đơn Chiều", "Tổng Chi Phí", "Mã Nhân Viên", "Mã Giáo Viên"};
            DefaultTableModel model = new DefaultTableModel(arry, 0);

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getInt("id_NgayNhap"));
                vector.add(rs.getString("ngayNhap"));
                vector.add(rs.getString("ngayBaoAn"));
                vector.add(rs.getInt("soXuatAn"));
                vector.add(rs.getString("thucDonSang"));
                vector.add(rs.getString("thucDonTrua"));
                vector.add(rs.getString("thucDonChieu"));
                vector.add(rs.getLong("tongChiPhi"));
                vector.add(rs.getString("maNhanVien"));
                vector.add(rs.getString("maGiaoVien"));
                model.addRow(vector);
            }
            tb_qualybuaan.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void Enabled() {
        cb_nhanvien.setEnabled(true);
        tf_giachieu.setEnabled(true);
        tf_giasang.setEnabled(true);
        cb_lop.setEnabled(true);
        tf_ngaykethuc.setEnabled(true);
        tf_thucdonchieu.setEnabled(true);
        tf_thucdonsang.setEnabled(true);
        tf_thucdontrua.setEnabled(true);
        cb_lop.setEnabled(true);
        cb_chucvu.setEnabled(true);
    }

    private void Disabled() {
        cb_lop.setEnabled(false);
        tf_giachieu.setEnabled(false);
        tf_giasang.setEnabled(false);
        cb_nhanvien.setEnabled(false);
        tf_ngaykethuc.setEnabled(false);
        tf_thucdonchieu.setEnabled(false);
        tf_thucdonsang.setEnabled(false);
        tf_thucdontrua.setEnabled(false);
        cb_lop.setEnabled(false);
        cb_chucvu.setEnabled(false);

    }

    private void reset() {
        cb_chucvu.setSelectedItem("");
        cb_lop.setSelectedItem("");
        cb_nhanvien.setSelectedItem("");
        cb_tienhocsinh.setSelectedItem("");
        tf_giachieu.setText("");
        tf_giasang.setText("");
        tf_giatrua.setText("");
        ((JTextField) tf_ngaykethuc.getDateEditor().getUiComponent()).setText("");
        ((JTextField) tf_batdau.getDateEditor().getUiComponent()).setText("");
        tf_thucdonchieu.setText("");
        tf_thucdonsang.setText("");
        tf_thucdontrua.setText("");
    }

    public void addbuaann() {
        connection();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date_ngayNhap = sdf.format(tf_ngaykethuc.getDate());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String date_ngayBao = sdf.format(tf_batdau.getDate());

        String sqlInsert
                = "insert into QuanLyBuaAn(ngayNhap,ngayBaoAn,soXuatAn,thucDonSang,thucDonTrua,thucDonChieu,tongChiPhi,maNhanVien,maGiaoVien) "
                + " values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sqlInsert);

            String soXuatAn = "";
            String xuatAn = lb_xuatan.getText().trim();
            conn.setAutoCommit(false);
            for (int i = 0; i < xuatAn.length(); i++) {
                char kyTu = xuatAn.charAt(i);
                if (kyTu >= 49 && kyTu <= 57) {
                    soXuatAn += kyTu;
                } else {
                    break;
                }
            }

            int vitriLast = lb_tongtien.getText().lastIndexOf(" ") - 1;
            String tongTien = lb_tongtien.getText().trim().substring(0, vitriLast);
            pst.setString(1, date_ngayNhap);
            pst.setString(2, date_ngayBao);
            pst.setInt(3, Integer.parseInt(soXuatAn));
            pst.setString(4, tf_giasang.getText());
            pst.setString(5, tf_giatrua.getText());
            pst.setString(6, tf_giachieu.getText());
            pst.setLong(7, Long.parseLong(tongTien));
            pst.setString(8, (String) (cb_nhanvien.getSelectedItem().toString().trim()));
            pst.setString(9, (String) (cb_chucvu.getSelectedItem().toString().trim()));

            pst.executeUpdate();
            conn.commit();
            ThongBao("Them thanh cong", "Thanh cong", 3);

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } finally {
            try {
                conn.close();
                pst.close();
                loaddata(sql);
            } catch (Exception e) {
            }
        }
    }

    public void loadcombobox() {
        connection();

        String sqlselect = "Select maNhanVien from NhanVien";
        try {
            PreparedStatement pst = conn.prepareStatement(sqlselect);
            ResultSet rs = pst.executeQuery();
            cb_nhanvien.removeAllItems();;
            while (rs.next()) {
                cb_nhanvien.addItem(rs.getString("maNhanVien"));

            }
        } catch (Exception e) {
        }

    }

    public void loadten() {
        connection();

        String sqlchucvu = "";
        String maTre = "";
        try {
            PreparedStatement pts1 = null;
            ResultSet rs1 = null;
            PreparedStatement pst = conn.prepareStatement("select maTre from TreHocLop where maLop='" + this.getmalop(cb_lop.getSelectedItem().toString()) + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                maTre = rs.getString("maTre");
                sqlchucvu = "Select * from Tre where maTre ='" + maTre + "'";
                pts1 = conn.prepareStatement(sqlchucvu);
                rs1 = pts1.executeQuery();
                while (rs1.next()) {
                    cb_tienhocsinh.addItem(rs1.getString("hoTen"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMcv() {
        connection();
        String select = "select maGiaoVien from GiaoVien ";

        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs1 = ps.executeQuery();
            cb_chucvu.removeAllItems();
            while (rs1.next()) {

                cb_chucvu.addItem(rs1.getString("maGiaoVien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadlop() {
        connection();

        try {
            PreparedStatement ps = conn.prepareStatement("select tenLop from Lop");
            ResultSet rs1 = ps.executeQuery();

            while (rs1.next()) {
                cb_lop.addItem(rs1.getString("tenLop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getmalop(String tenlop) {
        connection();
        String malopString = "";
        try {
            pst = conn.prepareStatement("select maLop from Lop where tenLop =N'" + tenlop + "'");
            rs = pst.executeQuery();
            while (rs.next()) {
                malopString = rs.getString("maLop");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return malopString;
    }

    public long ngay(String dateStart, String dateStop) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays < 0) {
                return -1;
            }
            if (diffDays == 0) {
                return 1;
            }
            return diffDays;

        } catch (Exception e) {
            return -1;
        }
    }

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao,
                tieuDeThongBao, icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_qualybuaan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_lop = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tf_ngaykethuc = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        tf_thucdonsang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_thucdontrua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_thucdonchieu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_giasang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_giatrua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_giachieu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_nhanvien = new javax.swing.JComboBox<>();
        cb_tienhocsinh = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        lb_tongtien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_batdau = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        lb_xuatan = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cb_chucvu = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_deletel = new javax.swing.JButton();
        lb_status = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setLayout(null);

        tb_qualybuaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_qualybuaan);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 650, 310);

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin");

        jLabel3.setText("Tên Học Sinh");

        jLabel4.setText("Tên Lớp");

        cb_lop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lopActionPerformed(evt);
            }
        });

        jLabel5.setText("Ngày Nhập");

        jLabel6.setText("Thực Đơn Bữa Sáng");

        jLabel7.setText("Thực Đơn Buổi Trưa");

        jLabel8.setText("Thực Đơn Buổi Chiều");

        jLabel9.setText("Đơn Giá ");

        tf_giasang.setText("20000");
        tf_giasang.setEnabled(false);

        jLabel10.setText("Đơn Giá");

        tf_giatrua.setText("20000");
        tf_giatrua.setEnabled(false);

        jLabel11.setText("Đơn Giá");

        tf_giachieu.setText("20000");
        tf_giachieu.setEnabled(false);

        jLabel13.setText("Mã Nhân Viên Nhập");

        jLabel12.setText("Mã Giáo Viên");

        cb_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nhanvienActionPerformed(evt);
            }
        });

        jLabel14.setText("Tổng Tiền");

        lb_tongtien.setText("0 VND");

        jLabel15.setText("Ngày Báo Ăn");

        tf_batdau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_batdauKeyTyped(evt);
            }
        });

        jLabel16.setText("Số Xuất Ăn");

        lb_xuatan.setText("xuất");

        jButton2.setText("Tính Số Xuất Ăn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_thucdonchieu)
                                    .addComponent(tf_thucdontrua)
                                    .addComponent(tf_thucdonsang, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(lb_tongtien))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel4)
                                        .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_tienhocsinh, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_lop, javax.swing.GroupLayout.Alignment.TRAILING, 0, 110, Short.MAX_VALUE)
                                    .addComponent(cb_nhanvien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addComponent(jLabel5)))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_giasang, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_giatrua, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_giachieu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tf_ngaykethuc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_batdau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lb_xuatan)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2))
                                    .addComponent(cb_chucvu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cb_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_tienhocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tf_thucdonsang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tf_thucdontrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_thucdonchieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tf_batdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_ngaykethuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_giasang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_giatrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(tf_giachieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lb_tongtien)
                            .addComponent(jLabel16)
                            .addComponent(lb_xuatan))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(670, 130, 600, 300);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Bữa Ăn");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(390, 20, 480, 100);

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/add.png"))); // NOI18N
        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/edit.png"))); // NOI18N
        btn_edit.setText("Sữa");
        btn_edit.setEnabled(false);

        btn_deletel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/delete.png"))); // NOI18N
        btn_deletel.setText("Xóa");
        btn_deletel.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btn_deletel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_deletel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(670, 460, 560, 90);

        lb_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_status.setText("Trạng Thái");
        jPanel1.add(lb_status);
        lb_status.setBounds(100, 470, 440, 20);

        jTabbedPane1.addTab("Quản Lý Bữa Ăn", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1344, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nhanvienActionPerformed
        this.getMcv();
    }//GEN-LAST:event_cb_nhanvienActionPerformed

    private void cb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lopActionPerformed
        this.cb_tienhocsinh.removeAllItems();
        this.loadten();
    }//GEN-LAST:event_cb_lopActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        try {
            this.addbuaann();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reset();
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void tf_batdauKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_batdauKeyTyped

    }//GEN-LAST:event_tf_batdauKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date_batDau = sdf.format(tf_batdau.getDate());
            String date_ketThuc = sdf.format(tf_ngaykethuc.getDate());
            int day = (int) ngay(date_batDau, date_ketThuc);
            if (day < 0) {
                ThongBao("Ngày nhap > Ngay bao", "Loi", 2);
                return;
            }
            lb_xuatan.setText(day + " xuất");
            lb_tongtien.setText(day * 60000 + " VNĐ");
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_deletel;
    private javax.swing.JButton btn_edit;
    private javax.swing.JComboBox<String> cb_chucvu;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JComboBox<String> cb_nhanvien;
    private javax.swing.JComboBox<String> cb_tienhocsinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb_status;
    private javax.swing.JLabel lb_tongtien;
    private javax.swing.JLabel lb_xuatan;
    private javax.swing.JTable tb_qualybuaan;
    private com.toedter.calendar.JDateChooser tf_batdau;
    private javax.swing.JTextField tf_giachieu;
    private javax.swing.JTextField tf_giasang;
    private javax.swing.JTextField tf_giatrua;
    private com.toedter.calendar.JDateChooser tf_ngaykethuc;
    private javax.swing.JTextField tf_thucdonchieu;
    private javax.swing.JTextField tf_thucdonsang;
    private javax.swing.JTextField tf_thucdontrua;
    // End of variables declaration//GEN-END:variables

}
