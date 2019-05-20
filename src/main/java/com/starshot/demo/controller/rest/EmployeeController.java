package com.starshot.demo.controller.rest;

import com.starshot.demo.dto.DataTableDto;
import com.starshot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by juliusgutierrez on 19/05/2019.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private UserService userService;

  @Autowired
  public EmployeeController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Search employee.
   * @param name - full name
   * @param active - status
   * @return
   */
  @GetMapping("/search")
  public ResponseEntity getAll(@RequestParam(required = false) String name,
      @RequestParam(required = false) String active) {

    DataTableDto data = new DataTableDto();
    data.setData(userService.getAllUsers(name, active));
    return ResponseEntity.ok(data);
  }


  /**
   * delete.
   * @param id - user id
   * @return
   */
  @GetMapping("/delete/{id}")
  public ResponseEntity delete(@PathVariable String id) {
    String msg = userService.deleteById(id);
    return ResponseEntity.ok(msg);
  }

  /**
   * Create or Update employee time track details.
   * @param id - optional
   * @param fullName - full name of employee
   * @param timeIn - time start
   * @param timeOut - time end
   * @param active - status
   * @return
   */
  @PostMapping("/save")
  public ResponseEntity save(@RequestParam(required = false) Long id,
      @RequestParam String fullName,
      @RequestParam String timeIn,
      @RequestParam String timeOut,
      @RequestParam String active) {
    String msg = userService.save(id, fullName, timeIn, timeOut, active);
    return ResponseEntity.ok(msg);
  }

}
