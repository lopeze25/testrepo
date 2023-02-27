package com.weldingco.welding.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Worklog {
  private Long workLogPk;
  private String worklogId;
  private String name;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
