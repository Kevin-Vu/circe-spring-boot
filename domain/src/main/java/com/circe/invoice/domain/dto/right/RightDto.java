package com.circe.invoice.domain.dto.right;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RightDto {

  private Integer id;
  private String name;

}
