package com.okayo.facture.factory;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class DesignationFactory {

    private DesignationFactory(){}

    public static CreateDesignationDto generateCreateDesignationDto(){
        return new CreateDesignationDto().setName(RandomStringUtils.randomAlphanumeric(30))
                                        .setPrixUnitHt((float) 100.0)
                                        .setQuantite(Integer.valueOf(RandomStringUtils.randomNumeric(1)))
                                        .setReduction((float) 50.0)
                                        .setTotalHt((float) 300.0)
                                        .setTva((float) 19.8);
    }

    public static List<CreateDesignationDto> generateCreateDesignationDtoList(){
        List<CreateDesignationDto> ls = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            ls.add(generateCreateDesignationDto());
        }
        return ls;
    }

}
