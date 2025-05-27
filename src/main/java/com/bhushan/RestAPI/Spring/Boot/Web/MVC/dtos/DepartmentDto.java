package com.bhushan.RestAPI.Spring.Boot.Web.MVC.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
     private Long id;
     private String title;
     private boolean isActive;

}
