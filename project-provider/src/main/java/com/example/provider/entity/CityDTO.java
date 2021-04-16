package com.example.provider.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wakening
 */
@ApiModel(description = "城市表")
@ToString(callSuper = true)
@Getter
@Setter
public class CityDTO implements Serializable {

    private static final long serialVersionUID = 5637224111578211415L;

    private Long id;

    private String cityName;

}
