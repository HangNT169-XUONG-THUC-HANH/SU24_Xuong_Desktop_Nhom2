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
import main.config.DBConnect;
import main.entity.HoaDon;
import main.response.HoaDonChiTietResponse;
import main.util.Helper;

/**
 *
 * @author hangnt
 */
public class HoaDonChiTietRepository {

    public ArrayList<HoaDonChiTietResponse> getAll(Integer hoaDonID) {
        String sql = """
                     SELECT hdct.Id ,sp.Ma ,sp.Ten ,
                        hdct.SoLuong ,hdct.DonGia ,
                        hdct.SoLuong * hdct.DonGia ,
                        cts.NamBH ,cts.MoTa ,
                        ms.Ten ,n.Ten ,ds.Ten 
                      FROM  HoaDonChiTiet hdct ,
                        ChiTietSP cts ,MauSac ms ,
                        NSX n ,DongSP ds ,SanPham sp 
                      WHERE hdct.IdChiTietSP  = cts.Id 
                      AND cts.IdSP =sp.Id 
                      AND cts.IdMauSac =ms.Id 
                      AND cts.IdNsx =n.Id 
                      AND cts.IdDongSP =ds.Id
                     AND hdct.Idhoadon = ?
                     """;
        ArrayList<HoaDonChiTietResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse response = HoaDonChiTietResponse.builder()
                        .id(rs.getInt(1))
                        .maSP(rs.getString(2))
                        .tenSP(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .donGia(rs.getDouble(5))
                        .thanhTien(rs.getDouble(6))
                        .namBaoHanh(rs.getInt(7))
                        .moTa(rs.getString(8))
                        .tenMauSac(rs.getString(9))
                        .tenNSX(rs.getString(10))
                        .tenDongSanPham(rs.getString(11))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
      public boolean add(HoaDonChiTietResponse response) {

        int check = 0;

        String sql = """
                    INSERT INTO HoaDonChiTiet
                        (IdHoaDon, IdChiTietSP, SoLuong, DonGia)
                     VALUES(?,?,?,?);
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, response.getHoaDonID());
            ps.setObject(2, response.getCtspID()); // Nhan vien lay tu login
            ps.setObject(3, response.getSoLuong());
            ps.setObject(4, response.getDonGia());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }
}
