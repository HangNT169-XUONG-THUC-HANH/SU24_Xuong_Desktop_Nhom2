/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.response.SanPhamChiTietResponse;

/**
 *
 * @author hangnt
 */
public class SanPhamChiTietRepository {

    public ArrayList<SanPhamChiTietResponse> getAll() {
        String sql = """
                 SELECT cts.Id ,sp.Ma ,sp.Ten ,
                        cts.NamBH ,cts.MoTa ,
                        ms.Ten ,n.Ten ,ds.Ten ,
                        cts.SoLuongTon ,cts.GiaBan 
                      FROM  ChiTietSP cts ,MauSac ms ,
                        NSX n ,DongSP ds ,SanPham sp 
                      WHERE cts.IdSP =sp.Id 
                      AND cts.IdMauSac =ms.Id 
                      AND cts.IdNsx =n.Id 
                      AND cts.IdDongSP =ds.Id
                     """;
        ArrayList<SanPhamChiTietResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietResponse(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3),
                        rs.getInt(9),
                        rs.getDouble(10),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<SanPhamChiTietResponse> search(String tenSanPham) {
        String sql = """
                 SELECT cts.Id ,sp.Ma ,sp.Ten ,
                        cts.NamBH ,cts.MoTa ,
                        ms.Ten ,n.Ten ,ds.Ten ,
                        cts.SoLuongTon ,cts.GiaBan 
                      FROM  ChiTietSP cts ,MauSac ms ,
                        NSX n ,DongSP ds ,SanPham sp 
                      WHERE cts.IdSP =sp.Id 
                      AND cts.IdMauSac =ms.Id 
                      AND cts.IdNsx =n.Id 
                      AND cts.IdDongSP =ds.Id
                      AND sp.Ten LIKE ?
                     """;
        ArrayList<SanPhamChiTietResponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + tenSanPham + "%"); // like => %%: contans : Kiem tra chua
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new SanPhamChiTietResponse(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3),
                        rs.getInt(9),
                        rs.getDouble(10),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public static void main(String[] args) {
//        System.out.println(new SanPhamChiTietRepository().search("a"));
        new SanPhamChiTietRepository().search("a").forEach(s->System.out.println(s));
    }
}
