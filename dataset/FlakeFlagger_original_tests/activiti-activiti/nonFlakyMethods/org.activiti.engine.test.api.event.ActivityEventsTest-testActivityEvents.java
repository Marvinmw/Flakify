/** 
 * Test starting and completed events for activity. Since these events are dispatched in the core of the PVM, not all individual activity-type is tested. Rather, we test the main types (tasks, gateways, events, subprocesses).
 */
@Deployment public void testActivityEvents() throws Exception {
  listener.setIgnoreRawActivityEvents(false);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("activityProcess");
  assertNotNull(processInstance);
  assertEquals(3,listener.getEventsReceived().size());
  ActivitiActivityEvent activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("theStart",activityEvent.getActivityId());
  assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("theStart",activityEvent.getActivityId());
  assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("shipOrder",activityEvent.getActivityId());
  assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  listener.clearEventsReceived();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  Execution execution=runtimeService.createExecutionQuery().parentId(processInstance.getId()).singleResult();
  assertNotNull(execution);
  assertEquals(5,listener.getEventsReceived().size());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("shipOrder",activityEvent.getActivityId());
  assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("subProcess",activityEvent.getActivityId());
  assertEquals(execution.getId(),activityEvent.getExecutionId());
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("subProcessStart",activityEvent.getActivityId());
  assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("subProcessStart",activityEvent.getActivityId());
  assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("subTask",activityEvent.getActivityId());
  assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  listener.clearEventsReceived();
  Task subTask=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
  assertNotNull(subTask);
  taskService.complete(subTask.getId());
  assertEquals(10,listener.getEventsReceived().size());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("subTask",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("gateway",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("gateway",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("throwMessageEvent",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("throwMessageEvent",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(5);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("endSubProcess",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(6);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("endSubProcess",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(7);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("subProcess",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(8);
  assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());
  assertEquals("theEnd",activityEvent.getActivityId());
  activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(9);
  assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());
  assertEquals("theEnd",activityEvent.getActivityId());
}
