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
public class NhanVienResponse {
    // response => Chua du lieu tra ra 
    // entity => mapping giong y si trong csdl => hibernate
    // dto: data tranfer object => bien thuc the (entity)
    // => thanh cac class phu hop
    // response : Du lieu tra ra 
    // request : Yeu cau cua nguoi dung
    private Integer id;

    private String ma;

    private String hoVaTen;

    private String gioiTinh;

    private String sdt;

    private String diaChi;

    private String maChucVu;

    private String tenChucVu;

}
