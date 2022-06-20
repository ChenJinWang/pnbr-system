package com.cjw.pet.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author cjw
 */
@Data
public class RegionalDistributionVo {
    private List<List<Object>> source;
}
