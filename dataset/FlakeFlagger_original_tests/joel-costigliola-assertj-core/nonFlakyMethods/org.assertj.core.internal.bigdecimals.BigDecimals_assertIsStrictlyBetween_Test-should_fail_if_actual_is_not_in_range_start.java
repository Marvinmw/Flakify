@Test public void should_fail_if_actual_is_not_in_range_start(){
  AssertionInfo info=someInfo();
  try {
    bigDecimals.assertIsStrictlyBetween(info,ONE,new BigDecimal(2),TEN);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeBetween(ONE,new BigDecimal(2),TEN,false,false));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
