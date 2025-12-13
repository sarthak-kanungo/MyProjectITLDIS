export const serviceRoutes = [
      {
        name: 'Pre Sales Service', path: 'pre-sales-service', children: [
          {
            name: 'Product Storage Check Sheet', path: 'product-storage-checksheet', children: [
              {
                name: 'Create Product Storage Check Sheet', path: 'create', children: []
              },
              {
                name: 'View Product Storage Check Sheet', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Edit Product Storage Check Sheet', path: 'edit', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
            ]
          },{
            name: 'Machine Form F - Form 22', path: 'machineFormf', children: []
          },
          {
            name: 'Machine Receipt Checking', path: 'machine-receipt-checking', children: [
              {
                name: 'Create Machine Receipt Checking', path: 'create', children: []
              },
              {
                name: 'View Machine Receipt Checking', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Edit Machine Receipt Checking', path: 'edit', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Pre-Delivery Checklist', path: 'pre-delivery-checklist', children: [
              {
                name: 'Create Pre-Delivery Checklist', path: 'create', children: []
              },
              {
                name: 'View Pre-Delivery Checklist', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Edit Pre-Delivery Checklist', path: 'edit', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
            ]
          },
          {
            name: 'Pre Delivery Inspection Search', path: 'pre-delivery-inspection', children: [
              {
                name: 'Create Pre Delivery Inspection', path: 'create', children: []
              },
              {
                name: 'View Pre Delivery Inspection', path: 'view', children: []
              },
              {
                name: 'Edit Pre Delivery Inspection', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Installation', path: 'installation', children: [
              {
                name: 'Create Installation', path: 'create', children: []
              },
              {
                name: 'View Installation', path: 'view', children: [
                  { name: '', path: ':id', children: [] }
                ]
              },
              {
                name: 'Edit Installation', path: 'edit', children: [
                  { name: '', path: ':id', children: [] }
                ]
              },
            ]
          },
          {
            name: 'Re-Installation', path: 're-installation', children: [
              {
                name: 'Create Re-Installation', path: 'create', children: []
              },
              {
                name: 'View Re-Installation', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Edit Re-Installation', path: 'edit', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
            ]
          },
        ]
      },

      {
        name: 'Customer Service', path: 'customerService', children: [
          {
            name: 'Job Card Search', path: 'job-card', children: [
              {
                name: 'Create Job Card', path: 'create', children: []
              },
              {
                name: 'View Job Card', path: 'view', children: []
              },
              {
                name: 'Job Card For PDI', path: 'pdi', children: []
              },
              {
                name: 'Job Card For PDC', path: 'pdc', children: []
              },
              {
                name: 'Job Card For Edit', path: 'edit', children: []
              },
            ]
          },
          {
              name: 'Job Card Re-Open Approval', path: 'job-card-reopen-approval', children: [
                {
                  name: 'Job Card Re-Open Approval', path: 'approval', children: []
                },
                {
                  name: 'Job Card Re-Open Approval', path: 'view', children: []
                }
              ]
            },
          {
            name: 'Service Booking', path: 'service-booking', children: [
              {
                name: 'Create Service Booking', path: 'create', children: [],
              },
              {
                name: 'View Service Booking', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ],
              },
              {
                name: 'Edit Service Booking', path: 'edit', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ],
              },
            ]
          },{
            name : 'Machine Service History', path:'machine-service-history', children: [
              
            ]
          }
        ]
      },

      {
        name: 'Activities / Claims', path: 'service-activities-claims', children: [
          {
            name: 'Service Activity Proposal', path: 'service-activity-training-proposal', children: [
              {
                name: 'Create Service Activity Proposal', path: 'create', children: []
              },
              {
                name: 'View Service Activity Proposal', path: 'view', children: []
              },
              {
                name: 'Edit Service Activity Proposal', path: 'edit', children: []
              },
              {
                name: 'Approval Service Activity Proposal', path: 'approval', children: []
              },
            ]
          },
          {
            name: 'Service Activity Claim', path: 'service-activity-claim', children: [
              {
                name: 'Create Service Activity Claim', path: 'create', children: []
              },
              {
                name: 'View Service Activity Claim', path: 'view', children: [{
                  name: '', path: ':id', children: []
                }]
              },
              {
                name: 'Approval Service Activity Claim', path: 'approval', children: [{
                  name: '', path: ':id', children: []
                }]
              },
              {
                name: 'Update Service Activity Claim', path: 'edit', children: [{
                  name: '', path: ':id', children: []
                }]
              },
            ]
          },
          {
            name: 'Service Activity Report', path: 'service-activity-report', children: [
              {
                name: 'Create Service Activity Report', path: 'create', children: []
              },
              {
                name: 'View Service Activity Report', path: 'view', children: [{
                  name:'', path:':id', children:[]
                }]
              },

            ]
          },
          {
            name: 'Service Claim', path: 'service-claim', children: [
              {
                name: 'Create Service Claim', path: 'create', children: []
              },
              {
                name: 'View Service Claim', path: 'view', children: []
              },
              {
                name: 'Inprogress Service Claim', path: 'in-progress', children: []
              },

            ]
          },
          {
            name: 'WCR Claim Management Approval', path: 'wcr-claim-approval', children: [
              {
                name: 'Create', path: 'create', children: []
              },
              {
                name: 'View', path: 'view', children: []
              },
              {
                name: 'Approval', path: 'approval', children: []
              },
            ]
          },
          {
            name: 'Service Claim Management Approval', path: 'service-claim-approval', children: [
              {
                name: 'Create', path: 'create', children: []
              },
              {
                name: 'View', path: 'view', children: []
              },
              {
                name: 'Approval', path: 'approval', children: []
              },
            ]
          },
          {
            name: 'Activity Claim Management Approval', path: 'service-activity-claim-approval', children: [
              {
                name: 'Create', path: 'create', children: []
              },
              {
                name: 'View', path: 'view', children: []
              },
              {
                name: 'Approval', path: 'approval', children: []
              },
            ]
          },
          {
            name: 'Activity Claim Management Approval', path: 'marketing-activity-claim-approval', children: [
              {
                name: 'Create', path: 'create', children: []
              },
              {
                name: 'View', path: 'view', children: []
              },
              {
                name: 'Approval', path: 'approval', children: []
              },
            ]
          },
          {
            name: 'Service Claim Invoice', path: 'service-claim-invoice', children: [ {
              name: 'View Service Claim Invoice', path: 'view', children: []
            }]
          },
          {
            name: 'Service Activity Claim Invoice', path: 'service-activity-claim-invoice', children: [ {
              name: 'View Service Activity Claim Invoice', path: 'view', children: []
            }]
          },
          {
            name: 'Marketing Activity Claim Invoice', path: 'marketing-activity-claim-invoice', children: [ {
              name: 'View Marketing Activity Claim Invoice', path: 'view', children: []
            }]
          },
          {
            name: 'Service Claim Invoice List', path: 'service-claim-invoice-list', children: [
              // {
              //   name: 'Create Service Claim Invoice', path: 'create', children: []
              // },

            ]
          },
          {
            name: 'Service Claim List', path: 'service-claim-list', children: [
              // {
              //   name: 'Create Service Claim List', path: 'create', children: []
              // },

            ]
          },
        ]
      },
      {
        name: 'Reports', path: 'report', children:[
          {
            name: 'Service Monitoring Board', path: 'smb', children: []
          },
          {
            name: 'Installation Monitoring Board', path: 'imb', children: []
          },
          {
            name: 'Fill Ratio Report', path: 'fillRatio', children: []
          },
          {
            name: 'Customer Machine Master Report', path: 'customerMachineMaster', children: []
          }
        ]
      },
      {
        name: 'CRM', path: 'crm', children: [
          {
            name: 'Model Survey Master', path: 'model-survey-master', children: [
              {
                name: 'Create Model Survey Master', path: 'create', children: []
              },
              {
                name: 'View Model Survey Master', path: 'view', children: []
              },
              {
                name: 'Edit Model Survey Master', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Survey Summary Report', path: 'survey-summary-report-telephonic', children: [
              {
                name: 'Create Survey Summary Report', path: 'create', children: []
              },
              {
                name: 'View Survey Summary Report', path: 'view', children: []
              },
              {
                name: 'Edit Survey Summary Report', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Survey Summary Report', path: 'survey-summary-report-direct', children: [
              {
                name: 'Create Survey Summary Report', path: 'create', children: []
              },
              {
                name: 'View Survey Summary Report', path: 'view', children: []
              },
              {
                name: 'Edit Survey Summary Report', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Question Master', path: 'question-master', children: [
              {
                name: 'Create Question Master', path: 'create', children: []
              },
              {
                name: 'View Question Master', path: 'view', children: []
              },
              {
                name: 'Edit Question Master', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Survey', path: 'survey-telephonic', children: [
              {
                name: 'Start Survey', path: 'create', children: []
              },
              {
                name: 'View Survey', path: 'view', children: []
              },
              {
                name: 'Edit Survey', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Survey', path: 'survey-direct', children: [
              {
                name: 'Start Survey', path: 'create', children: []
              },
              {
                name: 'View Survey', path: 'view', children: []
              },
              {
                name: 'Edit Survey', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Complaint Or Query Resolution', path: 'complaint-or-query-resolution', children: [
              {
                name: 'Create Complaint Or Query Resolution', path: 'create', children: []
              },
              {
                name: 'View Complaint Or Query Resolution', path: 'view', children: []
              },
              {
                name: 'Edit Complaint Or Query Resolution', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Delear Customer Care Executive Calls', path: 'customer-care-executive-calls', children: [
              {
                name: 'Create Delear Customer Care Executive Calls', path: 'create', children: []
              },
              {
                name: 'View Delear Customer Care Executive Calls', path: 'view', children: []
              },
              {
                name: 'Edit Delear Customer Care Executive Calls', path: 'edit', children: []
              },
            ]
          },
          {
            name: 'Toll Free', path: 'toll-free-call', children: [
              {
                name: 'Create Toll Free', path: 'create', children: []
              },
              {
                name: 'View Toll Free', path: 'view', children: []
              },
              {
                name: 'Edit Toll Free', path: 'edit', children: []
              },
            ]
          },
          {
            name : 'Reports', path:'reports', children:[
              {
                name: 'Survey Summary QA Report', path: 'survey-summary-qa-report', children: [
                 
                ]
              },
              {
                name: 'Customer Satisfaction Score Report', path: 'customerSatisfactionReport', children: [
                  
                ]
              },
              {
                name: 'Monthly Failure Code Summary Report', path: 'monthlyFailureCodeSummaryReport', children: [
                  
                ]
              }
            ]
          }
        ]
      },
];