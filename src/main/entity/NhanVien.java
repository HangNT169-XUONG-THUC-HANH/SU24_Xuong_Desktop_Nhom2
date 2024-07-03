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
@AllArgsConstructor  // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter
@Setter
@ToString
@Builder
public class NhanVien {

    private Integer id;
    
    private String ma;
    
    private String ten;
    
    private String tenDem;
    
    private String ho;
    
    private String gioiTinh;
    
    private Date ngaySinh;
    
    private String sdt;
    
    private String diaChi;
    
    private Integer idCV;
    
    private String matKhau;
    
    private Integer trangThai;
}
