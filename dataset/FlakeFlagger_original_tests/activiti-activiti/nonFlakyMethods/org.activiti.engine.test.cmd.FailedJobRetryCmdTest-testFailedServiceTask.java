@Deployment(resources={"org/activiti/engine/test/cmd/FailedJobRetryCmdTest.testFailedServiceTask.bpmn20.xml"}) public void testFailedServiceTask(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("failedServiceTask");
  assertNotNull(pi);
  waitForExecutedJobWithRetriesLeft(4);
  stillOneJobWithExceptionAndRetriesLeft();
  Job job=fetchJob(pi.getProcessInstanceId());
  assertNotNull(job);
  assertEquals(pi.getProcessInstanceId(),job.getProcessInstanceId());
  assertEquals(4,job.getRetries());
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().processInstanceId(pi.getId()).singleResult();
  assertEquals("failingServiceTask",execution.getActivityId());
  waitForExecutedJobWithRetriesLeft(3);
  job=refreshJob(job.getId());
  assertEquals(3,job.getRetries());
  stillOneJobWithExceptionAndRetriesLeft();
  execution=refreshExecutionEntity(execution.getId());
  assertEquals("failingServiceTask",execution.getActivityId());
  waitForExecutedJobWithRetriesLeft(2);
  job=refreshJob(job.getId());
  assertEquals(2,job.getRetries());
  stillOneJobWithExceptionAndRetriesLeft();
  execution=refreshExecutionEntity(execution.getId());
  assertEquals("failingServiceTask",execution.getActivityId());
  waitForExecutedJobWithRetriesLeft(1);
  job=refreshJob(job.getId());
  assertEquals(1,job.getRetries());
  stillOneJobWithExceptionAndRetriesLeft();
  execution=refreshExecutionEntity(execution.getId());
  assertEquals("failingServiceTask",execution.getActivityId());
  waitForExecutedJobWithRetriesLeft(0);
  job=managementService.createDeadLetterJobQuery().jobId(job.getId()).singleResult();
  assertEquals(0,job.getRetries());
  assertEquals(1,managementService.createDeadLetterJobQuery().withException().count());
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  assertEquals(1,managementService.createDeadLetterJobQuery().count());
  execution=refreshExecutionEntity(execution.getId());
  assertEquals("failingServiceTask",execution.getActivityId());
}
