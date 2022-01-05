@Test public void testConfigPropertiesWithValidPostProcessor(){
  final Properties configProperties=new Properties();
  configProperties.setProperty(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS,"jsMin");
  victim.setConfigProperties(configProperties);
  createManager();
  assertEquals(1,processorsFactory.getPostProcessors().size());
  assertEquals(JSMinProcessor.class,((ProcessorDecorator)processorsFactory.getPostProcessors().iterator().next()).getDecoratedObject().getClass());
}
