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
    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> lists = new ArrayList<>();

        // CODE 
        // B1: Tao SQL 
        String sql = """
                     SELECT Ma, Id, Ten, TenDem, Ho, NgaySinh, 
                     Sdt, DiaChi, ThanhPho, QuocGia, MatKhau, trangthai
                     FROM XUONG_LEVEL1_DESKTOP.dbo.KhachHang;
                     """;
        // B2: Mo cong ket noi => try catch
        // try...with..resource
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Ket qua tra ra la 1 bang => 1 ResultSet 
            ResultSet rs = ps.executeQuery();
            // SQL => SELECT => excuteQuery 
            // doc lan luot tung dong 
            while (rs.next()) {
                // B3: Tao 1 doi tuong
                KhachHang kh = new KhachHang();
//              kh.setId(rs.getInt("Id"));
                kh.setId(rs.getInt(2));
                // set tuong tu cho cac thuoc tinh con lai
                lists.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new KhachHangRepository().getAll());
    }
}
