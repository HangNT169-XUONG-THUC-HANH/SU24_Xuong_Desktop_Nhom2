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
                     DiaChi, ThanhPho, QuocGia, MatKhau, gioitinh
                     FROM XUONG_LEVEL1_DESKTOP.dbo.KhachHang
                     WHERE trangthai = 1
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
//                        rs.getString(11), rs.getInt(12),rs.getBoolean(13));
//                lists.add(kh); 
                KhachHang kh = new KhachHang();
////                kh.setId(rs.getInt("Id"));
//                kh.setId(rs.getInt(2));
//                kh.setMa(rs.getString(1));
//                kh.setGioiTinh(rs.getBoolean(13));
//                // Tu set cac truong con lai 
                kh.setId(rs.getInt(2));
                kh.setMa(rs.getString(1));
                kh.setTen(rs.getString(3));
                kh.setSoDienThoai(rs.getString(7));
                kh.setDiaChi(rs.getString(8));
                kh.setThanhPho(rs.getString(9));
                kh.setQuocGia(rs.getString(10));
                kh.setGioiTinh(rs.getBoolean(12));
                lists.add(kh);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(KhachHang kh) {
        int check = 0;
        String sql = """
                     INSERT INTO [dbo].[KhachHang]
                                ([Ma]
                                ,[Ten]
                                ,[Sdt]
                                ,[DiaChi]
                                , [trangthai]
                              )
                          VALUES(?,?,?,?,1);
                     """;
        // FK khoa ngoai 
        // Delete => Xoa tren giao dien thoi => CSDL 
        // delete : xoa mem 
        // trang thai = 1 => K xoa 
        // trang thai = 0 => Xoa di 
        // delete => Update trang thai = 0 
        // default khi them moi => trang thai = 1
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KhachHang newKhachHang, Integer id) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [Ten] = ?
                           ,[Sdt] = ?
                           ,[DiaChi] = ? 
                      WHERE ID = ?
                     """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newKhachHang.getTen());
            ps.setObject(2, newKhachHang.getSoDienThoai());
            ps.setObject(3, newKhachHang.getDiaChi());
            ps.setObject(4, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(Integer id) {
        int check = 0;
        String sql = """
                     UPDATE [dbo].[KhachHang]
                        SET [trangthai] = 0 
                      WHERE ID = ?
                     """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangRepository().getAll());
    }
}
