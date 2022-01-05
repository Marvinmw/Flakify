/** 
 * Only do one second type, as the logic is same as in  {@link ProcessInstanceQueryTest} and I do not want to duplicate all test case logic here. Basically copied from{@link ProcessInstanceQueryTest}
 */
@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryDateVariable() throws Exception {
  Map<String,Object> vars=new HashMap<String,Object>();
  Date date1=Calendar.getInstance().getTime();
  vars.put("dateVar",date1);
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult().getId());
  Date date2=Calendar.getInstance().getTime();
  vars=new HashMap<String,Object>();
  vars.put("dateVar",date1);
  vars.put("dateVar2",date2);
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance2.getId()).singleResult().getId());
  Calendar nextYear=Calendar.getInstance();
  nextYear.add(Calendar.YEAR,1);
  vars=new HashMap<String,Object>();
  vars.put("dateVar",nextYear.getTime());
  ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance3.getId()).singleResult().getId());
  Calendar nextMonth=Calendar.getInstance();
  nextMonth.add(Calendar.MONTH,1);
  Calendar twoYearsLater=Calendar.getInstance();
  twoYearsLater.add(Calendar.YEAR,2);
  Calendar oneYearAgo=Calendar.getInstance();
  oneYearAgo.add(Calendar.YEAR,-1);
  HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().variableValueEquals("dateVar",date1);
  List<HistoricProcessInstance> processInstances=query.list();
  assertNotNull(processInstances);
  assertEquals(2,processInstances.size());
  query=historyService.createHistoricProcessInstanceQuery().variableValueEquals("dateVar",date1).variableValueEquals("dateVar2",date2);
  HistoricProcessInstance resultInstance=query.singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance2.getId(),resultInstance.getId());
  Date unexistingDate=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("01/01/1989 12:00:00");
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueEquals("dateVar",unexistingDate).singleResult();
  assertNull(resultInstance);
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueNotEquals("dateVar",date1).singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance3.getId(),resultInstance.getId());
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueGreaterThan("dateVar",nextMonth.getTime()).singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance3.getId(),resultInstance.getId());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueGreaterThan("dateVar",nextYear.getTime()).count());
  assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueGreaterThan("dateVar",oneYearAgo.getTime()).count());
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueGreaterThanOrEqual("dateVar",nextMonth.getTime()).singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance3.getId(),resultInstance.getId());
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueGreaterThanOrEqual("dateVar",nextYear.getTime()).singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance3.getId(),resultInstance.getId());
  assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueGreaterThanOrEqual("dateVar",oneYearAgo.getTime()).count());
  processInstances=historyService.createHistoricProcessInstanceQuery().variableValueLessThan("dateVar",nextYear.getTime()).list();
  assertEquals(2,processInstances.size());
  List<String> expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());
  List<String> ids=new ArrayList<String>(Arrays.asList(processInstances.get(0).getId(),processInstances.get(1).getId()));
  ids.removeAll(expectedIds);
  assertTrue(ids.isEmpty());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueLessThan("dateVar",date1).count());
  assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueLessThan("dateVar",twoYearsLater.getTime()).count());
  processInstances=historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("dateVar",nextYear.getTime()).list();
  assertEquals(3,processInstances.size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("dateVar",oneYearAgo.getTime()).count());
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueEquals(nextYear.getTime()).singleResult();
  assertNotNull(resultInstance);
  assertEquals(processInstance3.getId(),resultInstance.getId());
  processInstances=historyService.createHistoricProcessInstanceQuery().variableValueEquals(date1).list();
  assertEquals(2,processInstances.size());
  expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());
  ids=new ArrayList<String>(Arrays.asList(processInstances.get(0).getId(),processInstances.get(1).getId()));
  ids.removeAll(expectedIds);
  assertTrue(ids.isEmpty());
  resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueEquals(twoYearsLater.getTime()).singleResult();
  assertNull(resultInstance);
  historyService.deleteHistoricProcessInstance(processInstance1.getId());
  historyService.deleteHistoricProcessInstance(processInstance2.getId());
  historyService.deleteHistoricProcessInstance(processInstance3.getId());
}
