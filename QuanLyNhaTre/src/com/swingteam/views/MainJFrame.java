/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.views;
import com.swingteam.bean.DanhMucBean;
import com.swingteam.controller.MovePanelController;
import java.util.ArrayList;
import java.util.List;
import com.swingteam.views.TrangChuPanel;

/**
 *
 * @author ngova
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();

        MovePanelController controller = new MovePanelController(jpnView);
        controller.setView(jpnTrangChu, jlbTrangChu);

        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("TrangChu", jpnTrangChu, jlbTrangChu));
        listItem.add(new DanhMucBean("GiaoVien", jpnGiaoVien, jlbGiaoVien));
        listItem.add(new DanhMucBean("Tre", jpnTre, jlbTre));
        listItem.add(new DanhMucBean("LopHoc", jpnLopHoc, jblLopHoc));
        listItem.add(new DanhMucBean("BuaAn", jpnBuaAn, jlbBuaAn));
        listItem.add(new DanhMucBean("NhanVien", jpnNhanVien, jlbNhanVien));
        listItem.add(new DanhMucBean("HocPhi", jpnHocPhi, jlbHocPhi));
        listItem.add(new DanhMucBean("DangXuat", jpnDangXuat, jlbDangXuat));
        controller.setEvent(listItem);

        setTitle("Quản Lý Nhà Trẻ");
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jpnTrangChu = new javax.swing.JPanel();
        jlbTrangChu = new javax.swing.JLabel();
        jpnGiaoVien = new javax.swing.JPanel();
        jlbGiaoVien = new javax.swing.JLabel();
        jpnTre = new javax.swing.JPanel();
        jlbTre = new javax.swing.JLabel();
        jpnLopHoc = new javax.swing.JPanel();
        jblLopHoc = new javax.swing.JLabel();
        jpnBuaAn = new javax.swing.JPanel();
        jlbBuaAn = new javax.swing.JLabel();
        jpnNhanVien = new javax.swing.JPanel();
        jlbNhanVien = new javax.swing.JLabel();
        jpnHocPhi = new javax.swing.JPanel();
        jlbHocPhi = new javax.swing.JLabel();
        jpnDangXuat = new javax.swing.JPanel();
        jlbDangXuat = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jpnRoot.setBackground(new java.awt.Color(255, 255, 255));

        jpnMenu.setBackground(new java.awt.Color(255, 255, 255));
        jpnMenu.setLayout(new java.awt.GridLayout(8, 1, 5, 5));

        jpnTrangChu.setBackground(new java.awt.Color(0, 204, 153));

        jlbTrangChu.setBackground(new java.awt.Color(255, 117, 26));
        jlbTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        jlbTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/Home.png"))); // NOI18N
        jlbTrangChu.setText("Trang Chủ");

        javax.swing.GroupLayout jpnTrangChuLayout = new javax.swing.GroupLayout(jpnTrangChu);
        jpnTrangChu.setLayout(jpnTrangChuLayout);
        jpnTrangChuLayout.setHorizontalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        jpnTrangChuLayout.setVerticalGroup(
            jpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnTrangChu);

        jpnGiaoVien.setBackground(new java.awt.Color(0, 204, 153));

        jlbGiaoVien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbGiaoVien.setForeground(new java.awt.Color(255, 255, 255));
        jlbGiaoVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbGiaoVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/nv.png"))); // NOI18N
        jlbGiaoVien.setText("Giáo Viên");

        javax.swing.GroupLayout jpnGiaoVienLayout = new javax.swing.GroupLayout(jpnGiaoVien);
        jpnGiaoVien.setLayout(jpnGiaoVienLayout);
        jpnGiaoVienLayout.setHorizontalGroup(
            jpnGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnGiaoVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnGiaoVienLayout.setVerticalGroup(
            jpnGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnGiaoVien);

        jpnTre.setBackground(new java.awt.Color(0, 204, 153));

        jlbTre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbTre.setForeground(new java.awt.Color(255, 255, 255));
        jlbTre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/outline_face_white_24dp.png"))); // NOI18N
        jlbTre.setText("Trẻ");

        javax.swing.GroupLayout jpnTreLayout = new javax.swing.GroupLayout(jpnTre);
        jpnTre.setLayout(jpnTreLayout);
        jpnTreLayout.setHorizontalGroup(
            jpnTreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTreLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jlbTre, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jpnTreLayout.setVerticalGroup(
            jpnTreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTre, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnTre);

        jpnLopHoc.setBackground(new java.awt.Color(0, 204, 153));

        jblLopHoc.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jblLopHoc.setForeground(new java.awt.Color(255, 255, 255));
        jblLopHoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblLopHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/lophoc.png"))); // NOI18N
        jblLopHoc.setText("Lớp Học");

        javax.swing.GroupLayout jpnLopHocLayout = new javax.swing.GroupLayout(jpnLopHoc);
        jpnLopHoc.setLayout(jpnLopHocLayout);
        jpnLopHocLayout.setHorizontalGroup(
            jpnLopHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLopHocLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jblLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnLopHocLayout.setVerticalGroup(
            jpnLopHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnLopHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jblLopHoc, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnMenu.add(jpnLopHoc);

        jpnBuaAn.setBackground(new java.awt.Color(0, 204, 153));

        jlbBuaAn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbBuaAn.setForeground(new java.awt.Color(255, 255, 255));
        jlbBuaAn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBuaAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/outline_restaurant_white_24dp.png"))); // NOI18N
        jlbBuaAn.setText("Bữa Ăn");

        javax.swing.GroupLayout jpnBuaAnLayout = new javax.swing.GroupLayout(jpnBuaAn);
        jpnBuaAn.setLayout(jpnBuaAnLayout);
        jpnBuaAnLayout.setHorizontalGroup(
            jpnBuaAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBuaAnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbBuaAn, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jpnBuaAnLayout.setVerticalGroup(
            jpnBuaAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbBuaAn, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnBuaAn);

        jpnNhanVien.setBackground(new java.awt.Color(0, 204, 153));

        jlbNhanVien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        jlbNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/kh.png"))); // NOI18N
        jlbNhanVien.setText("Nhân Viên");

        javax.swing.GroupLayout jpnNhanVienLayout = new javax.swing.GroupLayout(jpnNhanVien);
        jpnNhanVien.setLayout(jpnNhanVienLayout);
        jpnNhanVienLayout.setHorizontalGroup(
            jpnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNhanVienLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jlbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnNhanVienLayout.setVerticalGroup(
            jpnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnNhanVien);

        jpnHocPhi.setBackground(new java.awt.Color(0, 204, 153));

        jlbHocPhi.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbHocPhi.setForeground(new java.awt.Color(255, 255, 255));
        jlbHocPhi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbHocPhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/outline_paid_white_24dp.png"))); // NOI18N
        jlbHocPhi.setText("Học Phí");

        javax.swing.GroupLayout jpnHocPhiLayout = new javax.swing.GroupLayout(jpnHocPhi);
        jpnHocPhi.setLayout(jpnHocPhiLayout);
        jpnHocPhiLayout.setHorizontalGroup(
            jpnHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHocPhiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jpnHocPhiLayout.setVerticalGroup(
            jpnHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnHocPhi);

        jpnDangXuat.setBackground(new java.awt.Color(0, 204, 153));
        jpnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnDangXuatMouseClicked(evt);
            }
        });

        jlbDangXuat.setBackground(new java.awt.Color(0, 204, 102));
        jlbDangXuat.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        jlbDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swingteam/img/out.png"))); // NOI18N
        jlbDangXuat.setText("Đăng Xuất");

        javax.swing.GroupLayout jpnDangXuatLayout = new javax.swing.GroupLayout(jpnDangXuat);
        jpnDangXuat.setLayout(jpnDangXuatLayout);
        jpnDangXuatLayout.setHorizontalGroup(
            jpnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        jpnDangXuatLayout.setVerticalGroup(
            jpnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jpnMenu.add(jpnDangXuat);

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );

        jpnView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnDangXuatMouseClicked

    }//GEN-LAST:event_jpnDangXuatMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jblLopHoc;
    private javax.swing.JLabel jlbBuaAn;
    private javax.swing.JLabel jlbDangXuat;
    private javax.swing.JLabel jlbGiaoVien;
    private javax.swing.JLabel jlbHocPhi;
    private javax.swing.JLabel jlbNhanVien;
    private javax.swing.JLabel jlbTrangChu;
    private javax.swing.JLabel jlbTre;
    private javax.swing.JPanel jpnBuaAn;
    private javax.swing.JPanel jpnDangXuat;
    private javax.swing.JPanel jpnGiaoVien;
    private javax.swing.JPanel jpnHocPhi;
    private javax.swing.JPanel jpnLopHoc;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnNhanVien;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnTrangChu;
    private javax.swing.JPanel jpnTre;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
