package com.cjw.pet.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author cjw
 */
@Data
public class LocalDistributionVo {
    private List<List<Object>> source;
}