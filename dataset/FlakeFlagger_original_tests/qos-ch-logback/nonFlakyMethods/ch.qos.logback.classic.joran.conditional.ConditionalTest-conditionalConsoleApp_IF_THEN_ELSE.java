@SuppressWarnings("rawtypes") @Test public void conditionalConsoleApp_IF_THEN_ELSE() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "conditional/conditionalConsoleApp_ELSE.xml";
  configure(configFileAsStr);
  FileAppender fileAppender=(FileAppender)root.getAppender("FILE");
  assertNotNull(fileAppender);
  ConsoleAppender consoleAppender=(ConsoleAppender)root.getAppender("CON");
  assertNull(consoleAppender);
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertNotNull(listAppender);
  StatusChecker checker=new StatusChecker(context);
  checker.assertIsErrorFree();
}
