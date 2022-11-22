package com.acmebank.account.manager.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class TimeUtil {

  public static Instant instant() {
    return Instant.now();
  }

  public static ZonedDateTime toUtcZoneDateTime() {
    return ZonedDateTime.ofInstant(instant(), ZoneOffset.UTC);
  }

}
