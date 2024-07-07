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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDon {
    
    private Integer id;
    
    private Integer khachHangID;
    
    private Integer nhanVienID;
    
    private String ma;
    
    private Date ngayTao;
    
    private Date ngayThanhToan;
    
    private Date ngayShip;
    
    private Date ngayNhanHang;
    
    private Integer tinhTrang;
    
    private String tenNguoiNhan;
    
    private String diaChi;
    
    private String sdt;
    
    private Double tongTien;
    
}
