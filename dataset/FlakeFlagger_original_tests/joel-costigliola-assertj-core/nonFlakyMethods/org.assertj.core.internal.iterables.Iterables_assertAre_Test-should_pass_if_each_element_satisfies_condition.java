@Test public void should_pass_if_each_element_satisfies_condition(){
  actual=newArrayList("Yoda","Luke");
  iterables.assertAre(someInfo(),actual,jedi);
  verify(conditions).assertIsNotNull(jedi);
}
