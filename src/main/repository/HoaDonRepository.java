/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import main.response.HoaDonResponse;
import main.config.DBConnect;
import main.entity.HoaDon;
import main.util.Helper;

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

    // keyword : input => search theo nhieu truong (tim kiem theo moi truong tren table..)
    // keyword: ma hoa don, ma nv, ten nv, ten kh, ma kh,sdt......
    public ArrayList<HoaDonResponse> search(String keyword, Integer trangThai) {
        String sql = """
                    SELECT  hd.Id ,hd.IdKH ,hd.IdNV ,
                        hd.TinhTrang ,hd.TongTien ,hd.Ma,
                        kh.Ma ,kh.Ten ,nv.Ma ,nv.Ten 
                    FROM HoaDon hd ,KhachHang kh , NhanVien nv 
                    WHERE  hd.IdKH  = kh.Id 
                    AND hd.IdNV  = nv.Id 
                    AND hd.TinhTrang = ?
                    """;
        // check neu keyword k nhap gi => k can them gi ca 
        // nhap => moi can cong them vao sql 
        if (keyword.length() > 0) { // isempty
            sql += """
                  AND 
                  	(hd.Ma LIKE ?
                        OR 
                        nv.Ma LIKE ?
                        OR 
                        nv.Ten LIKE ?
                        OR 
                        kh.Ma LIKE ?
                        OR 
                        kh.Ten LIKE ? 
                        OR 
                        kh.Sdt LIKE ?
                  	)
                  """;
        }
//        -- ma hoa don, ma nv, ten nv, ma kh, ten kh, sdt
//        -- DK 1 DK2 DK 3 DK4 
//        -- AND => AND 4 DK
//        -- 09
        // thuoc tinh IS NULL 
        ArrayList<HoaDonResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1; // Vi tri cua dau hoi cham dau tien 
            ps.setObject(index++, trangThai);
            if (keyword.length() > 0) {
                String value = "%" + keyword + "%";
                // search 1 o input nhieu truong
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
            }
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

     public boolean add(HoaDon hoaDon) {

        int check = 0;

        String sql = """
                    INSERT INTO XUONG_LEVEL1_DESKTOP.dbo.HoaDon
                        (IdKH, IdNV, Ma, NgayTao, TinhTrang,TongTien)
                    VALUES(?,?,?,?,?,?);
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, hoaDon.getKhachHangID());
            ps.setObject(2, hoaDon.getNhanVienID()); // Nhan vien lay tu login
            ps.setObject(3, Helper.generateRandomMaHoaDon());
            ps.setObject(4, new Date());
            ps.setObject(5, 0);
            ps.setObject(6, 0);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }
}
