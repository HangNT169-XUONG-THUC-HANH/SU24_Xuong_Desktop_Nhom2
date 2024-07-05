/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.util.ArrayList;
import main.entity.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;

/**
 *
 * @author hangnt
 */
public class ChucVuRepository {

    public ArrayList<ChucVu> getAll() {
        String query = """
                      SELECT [Id]
                            ,[Ma]
                            ,[Ten]
                        FROM [dbo].[ChucVu]
                      
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            ArrayList<ChucVu> lists = new ArrayList<>();
            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getInt(1), rs.getString(2), rs.getString(3));
                lists.add(cv);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ChucVu getChucVuByMa(String ma1){
        String query = """
                      SELECT [Id]
                            ,[Ma]
                            ,[Ten]
                        FROM [dbo].[ChucVu]
                       WHERE [Ma] = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                ChucVu cv = new ChucVu(rs.getInt(1), rs.getString(2), rs.getString(3));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

}
