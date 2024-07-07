/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;

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
public class HoaDonChiTietResponse {
    
    private Integer id;
    
    private String maSP;
    
    private String tenSP;
   
    private Integer soLuong;
    
    private Double donGia;
    
    private Double thanhTien;
    
    private Integer namBaoHanh;
    
    private String moTa;
    
    private String tenMauSac;
    
    private String tenNSX;
    
    private String tenDongSanPham;
    
    private Integer hoaDonID;
    
    private Integer ctspID;
    
    
}
