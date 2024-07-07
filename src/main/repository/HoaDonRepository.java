/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.response.HoaDonResponse;
import main.config.DBConnect;

/**
 *
 * @author hangnt
 */
public class HoaDonRepository {

    public ArrayList<HoaDonResponse> getAll() {
        String sql = """
                   SELECT  hd.Id ,hd.IdKH ,hd.IdNV ,
                     hd.TinhTrang ,hd.TongTien ,hd.Ma,
                     kh.Ma ,kh.Ten ,nv.Ma ,nv.Ten 
                   FROM HoaDon hd ,KhachHang kh ,
                     NhanVien nv 
                   WHERE  hd.IdKH  = kh.Id 
                   AND hd.IdNV  = nv.Id 
                     """;
        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonResponse response = HoaDonResponse.builder()
                        .id(rs.getInt(1))
                        .khachHangID(rs.getInt(2))
                        .nhanVienID(rs.getInt(3))
                        .trangThai(rs.getInt(4))
                        .tongTien(rs.getDouble(5))
                        .ma(rs.getString(6))
                        .maKhachHang(rs.getString(7))
                        .tenKhachHang(rs.getString(8))
                        .maNhanVien(rs.getString(9))
                        .tenNhanVien(rs.getString(10))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<HoaDonResponse> getAllByStatus() {
        String sql = """
                   SELECT  hd.Id ,hd.IdKH ,hd.IdNV ,
                     hd.TinhTrang ,hd.TongTien ,hd.Ma,
                     kh.Ma ,kh.Ten ,nv.Ma ,nv.Ten ,
                     hd.TenNguoiNhan ,hd.Sdt ,hd.DiaChi ,hd.NgayTao 
                   FROM HoaDon hd ,KhachHang kh ,
                     NhanVien nv 
                   WHERE  hd.IdKH  = kh.Id 
                   AND hd.IdNV  = nv.Id 
                     AND hd.TinhTrang = 0
                     """;
        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonResponse response = HoaDonResponse.builder()
                        .id(rs.getInt(1))
                        .khachHangID(rs.getInt(2))
                        .nhanVienID(rs.getInt(3))
                        .trangThai(rs.getInt(4))
                        .tongTien(rs.getDouble(5))
                        .ma(rs.getString(6))
                        .maKhachHang(rs.getString(7))
                        .tenKhachHang(rs.getString(8))
                        .maNhanVien(rs.getString(9))
                        .tenNhanVien(rs.getString(10))
                        .tenNguoiNhan(rs.getString(11))
                        .sdtNguoiNhan(rs.getString(12))
                        .diaChiNguoiNhan(rs.getString(13))
                        .ngayTao(rs.getDate(14))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
