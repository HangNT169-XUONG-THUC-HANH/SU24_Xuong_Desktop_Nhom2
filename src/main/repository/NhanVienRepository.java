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
import main.entity.KhachHang;
import main.response.NhanVienResponse;

/**
 *
 * @author hangnt
 */
public class NhanVienRepository {
    
    public ArrayList<NhanVienResponse> getAll() {
        String sql = """
                     SELECT dbo.NhanVien.Id, dbo.NhanVien.Ma,
                        (dbo.NhanVien.Ho +' ' +dbo.NhanVien.TenDem +  ' '+dbo.NhanVien.Ten),
                        dbo.NhanVien.GioiTinh, 
                        dbo.NhanVien.DiaChi, dbo.NhanVien.Sdt, 
                        dbo.ChucVu.Ma AS Expr1, 
                        dbo.ChucVu.Ten AS Expr2
                    FROM  dbo.NhanVien INNER JOIN
                    dbo.ChucVu ON dbo.NhanVien.IdCV = dbo.ChucVu.Id
                     """;
        
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            
            ArrayList<NhanVienResponse> lists = new ArrayList<>();
            while (rs.next()) {
                NhanVienResponse response
                        = NhanVienResponse.builder()
                                .id(rs.getInt(1))
                                .ma(rs.getString(2))
                                .hoVaTen(rs.getString(3))
                                .tenChucVu(rs.getString(8))
                                .maChucVu(rs.getString(7))
                                .gioiTinh(rs.getString(4))
                                .diaChi(rs.getString(5))
                                .sdt(rs.getString(6))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new NhanVienRepository().getAll());
    }
}
