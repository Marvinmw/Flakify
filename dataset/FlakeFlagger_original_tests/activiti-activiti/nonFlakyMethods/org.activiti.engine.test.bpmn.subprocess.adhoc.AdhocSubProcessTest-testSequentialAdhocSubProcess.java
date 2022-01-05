@Deployment public void testSequentialAdhocSubProcess(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("completed",false);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess",variableMap);
  Execution execution=runtimeService.createExecutionQuery().activityId("adhocSubProcess").singleResult();
  assertNotNull(execution);
  List<FlowNode> enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  try {
    runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask2");
    fail("exception expected because can only enable one activity in a sequential ad-hoc sub process");
  }
 catch (  ActivitiException e) {
  }
  taskService.complete(subProcessTask.getId());
  runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask2");
  subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task2 in subprocess",subProcessTask.getName());
  variableMap=new HashMap<String,Object>();
  variableMap.put("completed",true);
  taskService.complete(subProcessTask.getId(),variableMap);
  Task afterTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("After task",afterTask.getName());
  taskService.complete(afterTask.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
