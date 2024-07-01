/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hangnt
 */
// Them cac annotation (@) can thiet 
@AllArgsConstructor  // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter
@Setter 
@ToString
@Builder // contructor tuy y tham so 
public class KhachHang {
    // entity => Chua cac class giong y si trong CSDL 
    // Chi can mapping cac thuoc tinh vao entity
    // Khong duoc phep khai bao bien hang loat
    private Integer id;
    
    private String ma;
    
    private String ten;
    
    private String tenDem;
    
    private String ho;
    
    private Date ngaySinh;
    
    private String sdt;
    
    private String diaChi;
    
    private String thanhPho;
    
    private String matKhau;
    
    private Integer trangThai;
}
