package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Machine;
import com.example.demo.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MachineController {
    @Autowired
    MachineRepository machineRepository;

    // Get All Machines
    @GetMapping("/machines")
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    // Create a new Machine
    @PostMapping("/machines")
    public Machine createMachine(@Valid @RequestBody Machine machine) {
        return machineRepository.save(machine);
    }

    // Get a Single Machine
    @GetMapping("/machines/{id}")
    public Machine getMachineById(@PathVariable(value = "id") Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine", "id", id));
    }

    // Update a Machine
    @PutMapping("/machines/{id}")
    public Machine updateMachine(@PathVariable(value = "id") Long id,
                                 @Valid @RequestBody Machine machineDetails) {

        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine", "id", id));

        machine.setStatus(machineDetails.getStatus());

        return machineRepository.save(machine);
    }

    // Delete a Note
    @DeleteMapping("/machines/{id}")
    public ResponseEntity<?> deleteMachine(@PathVariable(value = "id") Long machineId) {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new ResourceNotFoundException("Machine", "id", machineId));

        machineRepository.delete(machine);

        return ResponseEntity.ok().build();
    }


}
