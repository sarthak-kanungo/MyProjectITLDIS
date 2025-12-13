export const serviceRoutes = [
  {
    name: 'Service', path: 'service', children: [

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
          }
        ]
      },

      {
        name: 'Service Activities Claims', path: 'service-activities-claims', children: [
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

            ]
          },
          {
            name: 'Service Activity Claim/Approval', path: 'service-activity-claim-approval', children: [
              {
                name: 'Approve/Claim Service Approval', path: 'approval', children: []
              },

            ]
          },
          {
            name: 'Service Activity Claim List', path: 'service-activity-claim-list', children: [
              // {
              //   name: 'Approve Claim List', path: 'approval', children: []
              // },

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
                name: 'Inprogress Service Claim', path: 'in-progress', children: []
              },

            ]
          },
          {
            name: 'Service Claim Approval', path: 'service-claim-approval', children: [
              {
                name: 'Approve Service Claim', path: 'approval', children: []
              },

            ]
          },
          {
            name: 'Service Claim Invoice', path: 'service-claim-invoice', children: [
              {
                name: 'Create Service Claim Invoice', path: 'create', children: []
              },

            ]
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

    ]
  }
];