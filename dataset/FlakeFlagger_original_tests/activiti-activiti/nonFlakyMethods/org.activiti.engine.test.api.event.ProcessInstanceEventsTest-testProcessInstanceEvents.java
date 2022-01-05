/** 
 * Test create, update and delete events of process instances.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  assertEquals(5,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.PROCESS_STARTED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertTrue(event instanceof ActivitiProcessStartedEvent);
  assertNull(((ActivitiProcessStartedEvent)event).getNestedProcessDefinitionId());
  assertNull(((ActivitiProcessStartedEvent)event).getNestedProcessInstanceId());
  listener.clearEventsReceived();
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  runtimeService.activateProcessInstanceById(processInstance.getId());
  assertEquals(4,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  listener.clearEventsReceived();
  repositoryService.suspendProcessDefinitionById(processInstance.getProcessDefinitionId(),true,null);
  repositoryService.activateProcessDefinitionById(processInstance.getProcessDefinitionId(),true,null);
  assertEquals(4,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNotEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  listener.clearEventsReceived();
  runtimeService.updateBusinessKey(processInstance.getId(),"thekey");
  assertEquals(1,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"Testing events");
  List<ActivitiEvent> processCancelledEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals(1,processCancelledEvents.size());
  ActivitiCancelledEvent cancelledEvent=(ActivitiCancelledEvent)processCancelledEvents.get(0);
  assertEquals(ActivitiEventType.PROCESS_CANCELLED,cancelledEvent.getType());
  assertEquals(processInstance.getId(),cancelledEvent.getProcessInstanceId());
  assertEquals(processInstance.getId(),cancelledEvent.getExecutionId());
  listener.clearEventsReceived();
}
