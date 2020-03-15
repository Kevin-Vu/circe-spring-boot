package com.okayo.facture.util;

import com.okayo.facture.dto.designation.CreateDesignationDto;

import java.util.List;

public class DesignationUtil {

    private DesignationUtil(){}

    /**
     * Check the content of a list of CreateDesignationDto
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @return : true if content is valid
     */
    public static boolean checkListCreateDesignationInput(List<CreateDesignationDto> createDesignationDtoList){
        if(createDesignationDtoList == null) {
            return false;
        }
        for(CreateDesignationDto dto : createDesignationDtoList){
            if(!checkCreateDesignationInput(dto))
                return false;
        }
        return true;
    }

    /**
     * Check the content of CreateDesignationDto
     * @param createDesignationDto : CreateDesignationDto
     * @return : true if content is valid
     */
    private static boolean checkCreateDesignationInput(CreateDesignationDto createDesignationDto){
        if(createDesignationDto == null){
            return false;
        }
        if(createDesignationDto.getName() == null || createDesignationDto.getName().isEmpty()){
            return false;
        }
        if(createDesignationDto.getTva() > 100){
            return false;
        }
        return true;
    }

}
