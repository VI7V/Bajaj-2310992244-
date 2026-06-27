package com.vishav.bfhl.service;

import com.vishav.bfhl.dto.BfhlRequest;
import com.vishav.bfhl.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processData(BfhlRequest request);
}
