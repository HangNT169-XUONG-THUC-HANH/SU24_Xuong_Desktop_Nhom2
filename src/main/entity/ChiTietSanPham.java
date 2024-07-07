/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import lombok.AllArgsConstructor;
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
public class ChiTietSanPham {

    private Integer id;

    private Integer sanPhamID;

    private Integer nhaSanXuatID;

    private Integer mauSacID;

    private Integer dongSanPhamID;

    private Integer namBaoHanh;

    private String moTa;

    private Integer soLuongTon;

    private Double giaNhap;
    
    private Double giaBan;
}
