package com.example.pentagonUniv.domain.college;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("pu_college")
public class College {
    private Long id;
    private String name;
}
