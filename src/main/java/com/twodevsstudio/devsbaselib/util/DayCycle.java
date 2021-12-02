package com.twodevsstudio.devsbaselib.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayCycle {
  DAY(0, 12300),
  NIGHT(12300, 24000L),
  ALL_DAY(0, 24000);

  private final long minTime;
  private final long maxTime;
}
