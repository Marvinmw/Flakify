@Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size(){
  AssertionInfo info=someInfo();
  Collection<String> actual=newArrayList("Yoda");
  try {
    iterables.assertHasSize(info,actual,8);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSize(actual,actual.size(),8).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
