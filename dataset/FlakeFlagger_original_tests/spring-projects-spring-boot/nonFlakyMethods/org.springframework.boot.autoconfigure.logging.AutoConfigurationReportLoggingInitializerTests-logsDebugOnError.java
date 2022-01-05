@Test public void logsDebugOnError(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  this.initializer.initialize(context);
  context.register(ErrorConfig.class);
  try {
    context.refresh();
    fail("Did not error");
  }
 catch (  Exception ex) {
    this.initializer.onApplicationEvent(new ApplicationFailedEvent(new SpringApplication(),new String[0],context,ex));
  }
  assertThat(this.debugLog.size(),not(equalTo(0)));
  assertThat(this.infoLog.size(),equalTo(0));
}
