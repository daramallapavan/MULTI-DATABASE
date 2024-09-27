package com.noukri.noukriapk.controller;

import com.noukri.noukriapk.service.TestService;
import com.noukri.noukriapk.entity.Address;
import com.noukri.noukriapk.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

private final TestService testService;
@PostMapping("/student")
public Student post(@RequestBody Student studentDto){
    return testService.add(studentDto);
}
@PostMapping("/address")
public Address po(@RequestBody Address address){
    return testService.create(address);
}


}
