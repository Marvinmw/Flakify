@Test public void testNestedSystemPropertiesBinding(){
  this.context.register(NestedConfiguration.class);
  System.setProperty("name","foo");
  System.setProperty("nested.name","bar");
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(NestedProperties.class).length);
  assertEquals("foo",this.context.getBean(NestedProperties.class).name);
  assertEquals("bar",this.context.getBean(NestedProperties.class).nested.name);
}
