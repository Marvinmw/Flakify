@Test public void should_fail_if_actual_is_greater_than_other(){
  AssertionInfo info=someInfo();
  try {
    shorts.assertLessThan(info,(short)8,(short)6);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeLess((short)8,(short)6));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}