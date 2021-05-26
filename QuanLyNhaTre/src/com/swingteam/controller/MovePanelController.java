package com.swingteam.controller;
import com.swingteam.bean.DanhMucBean;
import com.swingteam.views.BuaAnPanel;
import com.swingteam.views.GiaoVienPanel;
import com.swingteam.views.HocPhiPanel;
import com.swingteam.views.LopHocPannel;
import com.swingteam.views.NhanVienPanel;
import com.swingteam.views.TrangChuPanel;
import com.swingteam.views.TrePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class MovePanelController {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public MovePanelController(JPanel jpnRoot) {
        this.root = jpnRoot;

    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 199, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LableEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LableEvent implements MouseListener {

        private JPanel node;

        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LableEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuPanel();
                    break;
                case "GiaoVien":
                    node = new GiaoVienPanel();
                    break;
                case "Tre":
                    node = new TrePanel();
                    break;
                case "LopHoc":
                    node = new LopHocPannel();
                    break;
                case "BuaAn":
                    node = new BuaAnPanel();
                    break;
                case "HocPhi":
                    node = new HocPhiPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienPanel();
                    break;
                default:
                    node = new TrangChuPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
           if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(0,204,153));
                jlbItem.setBackground(new Color(0,204,153));
            }
        }

    }

    private void setChangeBackgroud(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            }else{
                item.getJpn().setBackground(new Color(0,204,153));
                item.getJlb().setBackground(new Color(0,204,153));  
            }
        }
    }
}
