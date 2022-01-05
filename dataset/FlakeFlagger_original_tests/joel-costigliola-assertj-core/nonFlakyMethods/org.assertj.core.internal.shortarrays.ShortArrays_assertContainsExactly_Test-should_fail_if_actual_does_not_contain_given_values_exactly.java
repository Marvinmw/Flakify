@Test public void should_fail_if_actual_does_not_contain_given_values_exactly(){
  AssertionInfo info=someInfo();
  short[] expected={6,8,20};
  try {
    arrays.assertContainsExactly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainExactly(actual,expected,newLinkedHashSet((short)20),newLinkedHashSet((short)10)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
