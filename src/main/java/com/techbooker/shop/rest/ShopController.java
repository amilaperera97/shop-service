package com.techbooker.shop.rest;

import com.techbooker.shop.dto.ResponseDto;
import com.techbooker.shop.dto.ShopDataDto;
import com.techbooker.shop.service.ShopService;
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
public class ShopController {

    private final ShopService shopService;

    @PostMapping(value = SHOP, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ShopDataDto> save(@Valid @RequestBody ShopDataDto data) {
        return new ResponseDto<ShopDataDto>().buildSuccessMsgWithData(shopService.save(data));
    }

    @PutMapping(value = SHOP, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ShopDataDto> update(@Valid @RequestBody ShopDataDto data) {
        return new ResponseDto<ShopDataDto>().buildSuccessMsgWithData(shopService.update(data));
    }

    @DeleteMapping(value = SHOP, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<Void> delete(@Valid @RequestBody ShopDataDto data) {
        shopService.delete(data);
        return new ResponseDto<Void>().buildSuccessMsg();
    }

    @GetMapping(value = SHOP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<List<ShopDataDto>> findAll() {
        return new ResponseDto<List<ShopDataDto>>().buildSuccessMsgWithData(shopService.findAll());
    }

    @GetMapping(value = SHOP_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<ShopDataDto> findById(@PathVariable("id") String id) {
        return new ResponseDto<ShopDataDto>().buildSuccessMsgWithData(shopService.findById(Long.parseLong(id)));
    }

}
