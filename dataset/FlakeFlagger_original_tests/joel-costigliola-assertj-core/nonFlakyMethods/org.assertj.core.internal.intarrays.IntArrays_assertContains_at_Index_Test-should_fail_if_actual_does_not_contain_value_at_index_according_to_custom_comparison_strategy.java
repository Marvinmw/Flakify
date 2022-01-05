@Test public void should_fail_if_actual_does_not_contain_value_at_index_according_to_custom_comparison_strategy(){
  AssertionInfo info=someInfo();
  Index index=atIndex(1);
  try {
    arraysWithCustomComparisonStrategy.assertContains(info,actual,6,index);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainAtIndex(actual,6,index,8,absValueComparisonStrategy));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
