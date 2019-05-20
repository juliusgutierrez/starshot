package com.starshot.demo.dto;

import com.starshot.demo.entity.User;

import java.util.List;

/**
 * Created by juliusgutierrez on 19/05/2019.
 */
public class DataTableDto {

  private int draw = 1; //give an inital value to draw in table
  private List<User> data;

  public List<User> getData() {
    return data;
  }

  public void setData(List<User> data) {
    this.data = data;
  }

  public int getDraw() {
    return draw;
  }

  public void setDraw(int draw) {
    this.draw = draw;
  }
}
