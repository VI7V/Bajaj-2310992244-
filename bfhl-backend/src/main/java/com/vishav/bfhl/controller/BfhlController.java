package com.vishav.bfhl.controller;

import com.vishav.bfhl.dto.BfhlRequest;
import com.vishav.bfhl.dto.BfhlResponse;
import com.vishav.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(@RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
