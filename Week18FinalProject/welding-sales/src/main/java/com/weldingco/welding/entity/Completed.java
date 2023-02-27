package com.weldingco.welding.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Completed {
  private Long completedPk;
  private Long completedId;
  private Worklog worklog;
  private Order order;
  private LocalDateTime fulfilledDate;
}
