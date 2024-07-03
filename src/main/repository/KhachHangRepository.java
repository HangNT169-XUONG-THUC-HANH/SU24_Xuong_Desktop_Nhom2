/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.util.ArrayList;
import main.entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;

/**
 *
 * @author hangnt
 */
public class KhachHangRepository {

    // CRUD 
    // GET ALL 
    // Finish CRUD man hinh Khach hang 
    // Tao giao dien man hinh Nhan vien, Hoa don, Ban hang
    public ArrayList<KhachHang> getAll() {
        // B1: Tao cau SQL 
        String sql = """
                     SELECT Ma,Id, Ten, TenDem, Ho, NgaySinh, Sdt, 
                     DiaChi, ThanhPho, QuocGia, MatKhau, trangthai, gioitinh
                     FROM XUONG_LEVEL1_DESKTOP.dbo.KhachHang;
                     """;
        // B2: Mo cong ket noi 
        // try...with..resource => Tu dong cong ket noi sql
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // table => ResultSet
            ResultSet rs = ps.executeQuery();
            // Doi vs cac cau SQL 
            // su dung excuteQuery => tra ve 1 bang(resultset)
            ArrayList<KhachHang> lists = new ArrayList<>();
            while (rs.next()) {
//                KhachHang kh = new KhachHang(rs.getInt(1),
//                        rs.getString(2), rs.getString(3), rs.getString(4), 
//                        rs.getString(5), 
//                        rs.getDate(6), rs.getString(7), rs.getString(8),
//                        rs.getString(9), rs.getString(10),
//                        rs.getString(11), rs.getInt(12));
//                lists.add(kh); 
                KhachHang kh = new KhachHang();
//                kh.setId(rs.getInt("Id"));
                kh.setId(rs.getInt(2));
                kh.setMa(rs.getString(1));
                kh.setGioiTinh(rs.getBoolean(13));
                // Tu set cac truong con lai 
                lists.add(kh);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangRepository().getAll());
    }
}
