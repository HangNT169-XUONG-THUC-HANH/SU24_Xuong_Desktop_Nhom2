/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;

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
public class HoaDonResponse {

    private Integer id;

    private Integer khachHangID;

    private Integer nhanVienID;

    private String ma;

    private String maKhachHang;

    private String tenKhachHang;

    private String maNhanVien;

    private String tenNhanVien;

    private Double tongTien;

    private Integer trangThai;
    
    private Date ngayTao;
    
    private String tenNguoiNhan;
    
    private String sdtNguoiNhan;
    
    private String diaChiNguoiNhan;
    
}
