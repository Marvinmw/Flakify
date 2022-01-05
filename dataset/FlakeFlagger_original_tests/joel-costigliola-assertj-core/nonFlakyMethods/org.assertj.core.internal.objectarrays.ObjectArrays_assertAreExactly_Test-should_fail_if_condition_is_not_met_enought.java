@Test public void should_fail_if_condition_is_not_met_enought(){
  testCondition.shouldMatch(false);
  AssertionInfo info=someInfo();
  try {
    actual=array("Yoda","Solo","Leia");
    arrays.assertAreExactly(someInfo(),actual,2,jedi);
  }
 catch (  AssertionError e) {
    verify(conditions).assertIsNotNull(jedi);
    verify(failures).failure(info,elementsShouldBeExactly(actual,2,jedi));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
