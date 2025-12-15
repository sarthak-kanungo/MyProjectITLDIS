# Camunda BPM Process Definitions

This directory contains BPMN 2.0 process definition files for the ITLDIS application.

## Adding New Processes

1. Create your BPMN file using Camunda Modeler (https://camunda.com/download/modeler/)
2. Save the .bpmn file in this directory
3. Update `ProcessDeploymentListener.java` to include your new process file in the deployment list
4. Restart the application

## Example Process Files

- `sample-process.bpmn` - A simple example process

## Process Naming Convention

Use descriptive names:
- `warranty-claim-approval.bpmn`
- `service-request-workflow.bpmn`
- `purchase-order-approval.bpmn`

## Deployment

Processes in this directory are automatically deployed when the application starts via `ProcessDeploymentListener`.
