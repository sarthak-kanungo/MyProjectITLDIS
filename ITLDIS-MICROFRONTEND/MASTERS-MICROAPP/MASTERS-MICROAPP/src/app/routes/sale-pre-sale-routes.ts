export const salePreSaleRoutes = [
  {
    name: 'Sales And Presales', path: 'sales-pre-sales', children: [
      {
        name: 'Purchase', path: 'purchase', children: [
          {
            name: 'Purchase Order', path: 'purchase-order', children: [
              {
                name: 'Create Purchase Order', path: 'create', children: []
              },
              {
                name: 'Approve Purchase Order', path: 'approve', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Update Purchase Order', path: 'update', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'View Purchase Order', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'PO Approval', path: 'dealer-po-approval', children: []
          },
          {
            name: 'Channel Finance Indent', path: 'channel-finance-indent', children: [
              {
                name: 'Create Channel Finance Indent', path: 'create', children: []
              },
              {
                name: 'View Channel Finance Indent', path: 'view/:id', children: []
              }
            ]
          },
          {
            name: 'Machine GRN', path: 'goods-receipt-note', children: [
              {
                name: 'Create Machine GRN', path: 'create', children: [],
              },
              { name: 'View Machine GRN', path: 'view/:id', children: [] },
              { name: 'Update Machine GRN', path: 'edit/:id', children: [] }
            ]
          },
          {
            name: 'Machine Descripancy Complaint', path: 'machine-descripancy-complaint', children: [
              {
                name: 'Create Machine Descripancy Complaint', path: 'create', children: []
              },
              {
                name: 'View Machine Descripancy Complaint', path: 'view/:id', children: []
              },
              {
                name: 'Edit Machine Descripancy Complaint', path: 'edit/:id', children: []
              }
            ]
          },
          {
            name: 'Machine Descripancy Claim', path: 'descripancy-claim', children: [
              {
                name: 'Create Machine Descripancy Claim', path: 'create', children: []
              },
              {
                name: 'View Machine Descripancy Claim', path: 'view/:id', children: []
              },
              {
                name: 'Edit Machine Descripancy Claim', path: 'edit/:id', children: []
              },
              {
                name: 'Approve Machine Descripancy Claim', path: 'approve', children: []
              }
            ]
          },
          {
            name: 'Machine Transfer Request', path: 'machine-transfer-request', children: [
              {
                name: 'Create Machine Transfer Request', path: 'create', children: []
              },
              {
                name: 'View Machine Transfer Request', path: 'view/:id', children: []
              }
            ]
          }
        ]
      },
      {
        name: 'Pre Sales', path: 'pre-sales', children: [
          {
            name: 'Enquiry', path: 'enquiry', children: [
              {
                name: 'Create Enquiry', path: 'create', children: []
              },
              {
                name: 'View App Enquiry', path: 'viewMobile', children: []
              },
              {
                name: 'View Enquiry', path: 'view/:id', children: []
              },
              {
                name: 'Enquiry Follow Up', path: 'followup/:id', children: []
              },
              {
                name: 'Transfer Enquiry', path: 'transfer', children: []
              }
            ]
          }
        ]
      },
      {
        name: 'Sales', path: 'sales', children: [
          {
            name: 'Quotation', path: 'quotation', children: [
              {
                name: 'Create Quotation', path: 'create', children: []

              },
              {
                name: 'View Quotation', path: 'view/:id', children: []

              }
            ]
          },
          {
            name: 'Payment Receipt', path: 'payment-receipt', children: [
              {
                name: 'Create Payment Receipt', path: 'create', children: []
              },
              {
                name: 'Payment Receipt View', path: 'view/:id', children: []
              },
            ]
          },
          {
            name: 'Allotment/DeAllotment', path: 'allot-deallot', children: [
              {
                name: 'Create Allotment/DeAllotment', path: 'create', children: []
              },
              {
                name: 'Deallotment', path: 'deallot/:id', children: []
              },
              {
                name: 'View Allotment', path: 'view/viewId', children: []
              }
            ]
          },
          {
            name: 'Delivery Challan', path: 'delivery-challan', children: [
              {
                name: 'Create Delivery Challan', path: 'create', children: []
              },
              {
                name: 'View DC', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Update DC', path: 'update', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'Cancel DC', path: 'cancel', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Invoice', path: 'invoice', children: [
              {
                name: 'Create Invoice', path: 'create', children: []
              },
              {
                name: 'Cancel Invoice', path: 'cance', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              },
              {
                name: 'View Invoice', path: 'view', children: [
                  {
                    name: '', path: ':id', children: []
                  }
                ]
              }
            ]
          }
        ]
      },
      {
        name: 'Branch Transfer', path: 'branch-transfer', children: [
          {
            name: 'Branch Transfer Request', path: 'branch-transfer-request', children: [
              {
                name: 'Create Branch Transfer Request', path: 'create', children: []
              }
            ]
          },
          {
            name: 'Branch Transfer Issue', path: 'branch-transfer-issue', children: [
              {
                name: 'Create Branch Transfer Issue', path: 'create', children: []
              }
            ]
          },

          {
            name: 'Branch Transfer Receipt', path: 'branch-transfer-receipt', children: [
              {
                name: 'Create Branch Transfer Receipt', path: 'create', children: []
              }
            ]
          }
        ]
      },
      {
        name: 'Activity', path: 'activity', children: [
          {
            name: 'Source Master', path: 'source-master', children: []
          },

          {
            name: 'Marketing Activity Proposal', path: 'activity-proposal', children: [
              {
                name: 'Create Marketing Activity Proposal', path: 'create', children: []
              },
              {
                name: 'View Marketing Activity Proposal', path: 'view/:id', children: []
              },
              {
                name: 'Activity Proposal Approval', path: 'approve/:actPrpId', children: []
              }
            ]
          },
          {
            name: 'Marketing Activity Approval', path: 'activity-approval', children: [
              {
                name: 'Approval', path: 'approve', children: []
              },
              {
                name: 'View Marketing Activity Approval', path: 'view/:id', children: []
              }
            ]
          },
          {
            name: 'Marketing Activity Report', path: 'actual-report', children: [
              {
                name: 'Create Marketing Activity Report', path: 'create', children: []
              },
              {
                name: 'View Marketing Activity Report', path: 'view/:id', children: []
              }
            ]
          },
          {
            name: 'Marketing Activity Claim', path: 'activity-claim', children: [
              {
                name: 'Create Marketing Activity Claim', path: 'create', children: []
              },
              {
                name: 'View Marketing Activity Claim', path: 'view/:id', children: []
              },
              {
                name: 'Claim Approval', path: 'approve', children: [
                  {
                    name: '', path: ':claimId', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Marketing Activity Claim Approval', path: 'activity-claim-approval', children: [
              {
                name: 'Create Marketing Activity Approval', path: 'approve', children: []
              }
            ]
          }
        ]
      },
      {
        name: 'Schemes', path: 'schemes', children: [
          {
            name: 'Incentive Scheme Master', path: 'incentive-scheme-master', children: [
              {
                name: 'Create Incentive Scheme Master', path: 'create', children: []
              },
              {
                name: 'View Incentive Scheme Master', path: 'view/:id', children: []
              }
            ]
          },
          {
            name: 'Incentive Scheme Claim', path: 'incentive-schemes-claim', children: [
              {
                name: 'Create Incentive Scheme Claim', path: 'create', children: []
              },
              {
                name: 'View Incentive Scheme Claim', path: 'view/:id', children: []
              }
            ]
          }
        ]
      }
    ]
  }
]