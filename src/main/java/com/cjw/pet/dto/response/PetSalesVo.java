package com.cjw.pet.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author cjw
 */
@Data
public class PetSalesVo {
    private List<List<Object>> source;
}