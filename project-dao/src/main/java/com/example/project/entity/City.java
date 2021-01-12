package com.example.project.entity;

import java.io.Serializable;

/**
 * @author wakening
 */
public class City implements Serializable {

    private static final long serialVersionUID = 5637224111578211415L;

    private Long id;

    private String cityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
