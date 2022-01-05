public void testLegacyCronString() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20140310-04:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20140310-12:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("0 0 12 1/1 * ? *"));
}
