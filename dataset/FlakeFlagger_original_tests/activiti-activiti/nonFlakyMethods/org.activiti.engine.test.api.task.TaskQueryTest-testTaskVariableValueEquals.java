@Deployment public void testTaskVariableValueEquals() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("unexistingVar","value").count());
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("longVar",928374L);
  variables.put("shortVar",(short)123);
  variables.put("integerVar",1234);
  variables.put("stringVar","stringValue");
  variables.put("booleanVar",true);
  Date date=Calendar.getInstance().getTime();
  variables.put("dateVar",date);
  variables.put("nullVar",null);
  taskService.setVariablesLocal(task.getId(),variables);
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("longVar",928374L).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("shortVar",(short)123).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("integerVar",1234).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("stringVar","stringValue").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("booleanVar",true).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("dateVar",date).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("nullVar",null).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("longVar",999L).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("shortVar",(short)999).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("integerVar",999).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("stringVar","999").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("booleanVar",false).count());
  Calendar otherDate=Calendar.getInstance();
  otherDate.add(Calendar.YEAR,1);
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("dateVar",otherDate.getTime()).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("nullVar","999").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("longVar",999L).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("shortVar",(short)999).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("integerVar",999).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("stringVar","999").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEquals("booleanVar",false).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(928374L).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals((short)123).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(1234).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals("stringValue").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(true).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(date).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEquals(null).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(999999L).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals((short)999).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(9999).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals("unexistingstringvalue").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(false).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEquals(otherDate.getTime()).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueLike("stringVar","string%").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueLike("stringVar","String%").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueLike("stringVar","%Value").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1000).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1234).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThan("integerVar",1240).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1000).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1234).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueGreaterThanOrEqual("integerVar",1240).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1240).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1234).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThan("integerVar",1000).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1240).count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1234).count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueLessThanOrEqual("integerVar",1000).count());
}