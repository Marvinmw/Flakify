@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryStringVariable(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("stringVar","abcdef");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("stringVar","abcdef");
  vars.put("stringVar2","ghijkl");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("stringVar","azerty");
  ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  ExecutionQuery query=runtimeService.createExecutionQuery().variableValueEquals("stringVar","abcdef");
  List<Execution> executions=query.list();
  assertNotNull(executions);
  assertEquals(2,executions.size());
  query=runtimeService.createExecutionQuery().variableValueEquals("stringVar","abcdef").variableValueEquals("stringVar2","ghijkl");
  Execution execution=query.singleResult();
  assertNotNull(execution);
  assertEquals(processInstance2.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueNotEquals("stringVar","abcdef").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueGreaterThan("stringVar","abcdef").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueGreaterThan("stringVar","z").singleResult();
  assertNull(execution);
  assertEquals(3,runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("stringVar","abcdef").count());
  assertEquals(0,runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("stringVar","z").count());
  executions=runtimeService.createExecutionQuery().variableValueLessThan("stringVar","abcdeg").list();
  assertEquals(2,executions.size());
  List<String> expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());
  List<String> ids=new ArrayList<String>(Arrays.asList(executions.get(0).getId(),executions.get(1).getId()));
  ids.removeAll(expectedIds);
  assertTrue(ids.isEmpty());
  assertEquals(0,runtimeService.createExecutionQuery().variableValueLessThan("stringVar","abcdef").count());
  assertEquals(3,runtimeService.createExecutionQuery().variableValueLessThanOrEqual("stringVar","z").count());
  executions=runtimeService.createExecutionQuery().variableValueLessThanOrEqual("stringVar","abcdef").list();
  assertEquals(2,executions.size());
  expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());
  ids=new ArrayList<String>(Arrays.asList(executions.get(0).getId(),executions.get(1).getId()));
  ids.removeAll(expectedIds);
  assertTrue(ids.isEmpty());
  assertEquals(3,runtimeService.createExecutionQuery().variableValueLessThanOrEqual("stringVar","z").count());
  assertEquals(0,runtimeService.createExecutionQuery().variableValueLessThanOrEqual("stringVar","aa").count());
  execution=runtimeService.createExecutionQuery().variableValueLike("stringVar","azert%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLike("stringVar","%y").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLike("stringVar","%zer%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  assertEquals(3,runtimeService.createExecutionQuery().variableValueLike("stringVar","a%").count());
  assertEquals(0,runtimeService.createExecutionQuery().variableValueLike("stringVar","%x%").count());
  execution=runtimeService.createExecutionQuery().variableValueEquals("azerty").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance3.getId(),execution.getId());
  executions=runtimeService.createExecutionQuery().variableValueEquals("abcdef").list();
  assertEquals(2,executions.size());
  expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());
  ids=new ArrayList<String>(Arrays.asList(executions.get(0).getId(),executions.get(1).getId()));
  ids.removeAll(expectedIds);
  assertTrue(ids.isEmpty());
  execution=runtimeService.createExecutionQuery().variableValueEquals("notmatchinganyvalues").singleResult();
  assertNull(execution);
  runtimeService.deleteProcessInstance(processInstance1.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance2.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance3.getId(),"test");
}