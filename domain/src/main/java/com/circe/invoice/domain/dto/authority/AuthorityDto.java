package com.circe.invoice.domain.dto.authority;

import com.circe.invoice.domain.dto.right.RightDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {

  private Integer id;
  private String name;
  private List<RightDto> rights;

}
