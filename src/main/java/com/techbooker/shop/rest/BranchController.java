package com.techbooker.shop.rest;

import com.techbooker.shop.dto.BranchDataDto;
import com.techbooker.shop.exception.custom.ResourceNotFoundException;
import com.techbooker.shop.service.BranchService;
import com.techbooker.shop.service.ShopService;
import com.techbooker.sm.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.techbooker.shop.util.contance.ControllerConstance.*;

@RestController
@RequestMapping(value = API_V1)
@RequiredArgsConstructor
@Validated
@Slf4j
public class BranchController {

    private final BranchService branchService;

    @PostMapping(value = BRANCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<BranchDataDto> save(@Valid @RequestBody BranchDataDto data) throws ResourceNotFoundException {
        log.info("Save branch info : {}", data);
        return new ResponseDto<BranchDataDto>().buildSuccessMsgWithData(branchService.save(data));
    }

    @PutMapping(value = BRANCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<BranchDataDto> update(@Valid @RequestBody BranchDataDto data) throws ResourceNotFoundException {
        branchService.findById(data.getShopId());
        return new ResponseDto<BranchDataDto>().buildSuccessMsgWithData(branchService.update(data));
    }

    @DeleteMapping(value = BRANCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<Void> delete(@Valid @RequestBody BranchDataDto data) {
        branchService.delete(data);
        return new ResponseDto<Void>().buildSuccessMsg();
    }

    @GetMapping(value = BRANCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<List<BranchDataDto>> findAll() {
        return new ResponseDto<List<BranchDataDto>>().buildSuccessMsgWithData(branchService.findAll());
    }

    @GetMapping(value = BRANCH_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<BranchDataDto> findById(@PathVariable("id") String id) {
        return new ResponseDto<BranchDataDto>().buildSuccessMsgWithData(branchService.findById(Long.parseLong(id)));
    }
}
