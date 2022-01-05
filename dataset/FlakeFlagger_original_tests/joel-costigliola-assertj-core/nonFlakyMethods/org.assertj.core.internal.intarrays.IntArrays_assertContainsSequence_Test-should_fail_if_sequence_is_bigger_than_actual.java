@Test public void should_fail_if_sequence_is_bigger_than_actual(){
  AssertionInfo info=someInfo();
  int[] sequence={6,8,10,12,20,22};
  try {
    arrays.assertContainsSequence(info,actual,sequence);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainSequence(actual,sequence));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
