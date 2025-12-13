import { Injectable } from '@angular/core';
import { masterRoutes } from '../routes/master-routes';
import { salePreSaleRoutes } from '../routes/sale-pre-sale-routes';
import { serviceRoutes } from '../routes/service-routes';
import { spareRoutes } from '../routes/spare-routes';
import { warrantyRoutes } from '../routes/warranty-routes';
import { trainingRoutes } from '../routes/training-routes'
@Injectable({
  providedIn: 'root'
})
export class BreadscrumbService {

  constructor() { }
  getMenu(): Array<any> {
    const menu = [
      {
        name: 'Dashboard',
        path: './dashboard',
        children: []
      },
      {
        name: 'Sales And Presales', path: './sales-pre-sales', children: [
          {
            name: 'Purchase', path: './purchase', children: [
              {
                name: 'Purchase Order', path: './purchase-order', children: [
                  {
                    name: 'Create Purchase Order', path: './create', children: []
                  },
                  {
                    name: 'Approve Purchase Order', path: './approve', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  },
                  {
                    name: 'Update Purchase Order', path: './update', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  },
                  {
                    name: 'View Purchase Order', path: './view', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  }
                ]
              },
              {
                name: 'PO Approval', path: './dealer-po-approval', children: []
              },
              {
                name: 'Channel Finance Indent', path: './channel-finance-indent', children: [
                  {
                    name: 'Create Channel Finance Indent', path: './create'
                  },
                  {
                    name: 'View Channel Finance Indent', path: './view', children: [
                      {
                        name: '', path: '/:id'
                      }
                    ]
                  }
                ]
              },
              {
                name: 'Machine GRN', path: './goods-receipt-note', children: [
                  {
                    name: 'Create Machine GRN', path: './create', children: [],
                  },
                  { name: 'View Machine GRN', path: './view', children: [] },
                  { name: 'Update Machine GRN', path: './edit', children: [] }
                ]
              },
              {
                name: 'Machine Descripancy Complaint', path: './machine-descripancy-complaint', children: [
                  {
                    name: 'Create Machine Descripancy Complaint', path: './create', children: []
                  },
                  {
                    name: 'View Machine Descripancy Complaint', path: './view', children: []
                  },
                  {
                    name: 'Edit Machine Descripancy Complaint', path: './edit', children: []
                  }
                ]
              },
              {
                name: 'Machine Descripancy Claim', path: './descripancy-claim', children: [
                  {
                    name: 'Create Machine Descripancy Claim', path: './create', children: []
                  },
                  {
                    name: 'View Machine Descripancy Claim', path: './view', children: []
                  },
                  {
                    name: 'Edit Machine Descripancy Claim', path: './edit', children: []
                  },
                  {
                    name: 'Approve Machine Descripancy Claim', path: './approve', children: []
                  }
                ]
              },
              {
                name: 'Machine Transfer Request', path: './machine-transfer-request', children: [
                  {
                    name: 'Create Machine Transfer Request', path: './create', children: []
                  },
                  {
                    name: 'View Machine Transfer Request', path: './view', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Pre Sales', path: './pre-sales', children: [
              {
                name: 'Enquiry', path: './enquiry', children: [
                  {
                    name: 'Create Enquiry', path: './create', children: []
                  },
                  {
                    name: 'View App Enquiry', path: './viewMobile', children: []
                  },
                  {
                    name: 'View Enquiry', path: './view', children: []
                  },
                  {
                    name: 'Enquiry Follow Up', path: './followup', children: []
                  },
                  {
                    name: 'Transfer Enquiry', path: './transfer', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Sales', path: './sales', children: [
              {
                name: 'Quotation', path: './quotation', children: [
                  {
                    name: 'Create Quotation', path: './create', children: []

                  },
                  {
                    name: 'View Quotation', path: './view', children: [
                      {
                        name: '', path: './:id'

                      }
                    ]

                  }
                ]
              },
              {
                name: 'Payment Receipt', path: './payment-receipt', children: [
                  {
                    name: 'Create Payment Receipt', path: './create', children: []
                  },
                  {
                    name: 'Payment Receipt View', path: './view', children: []
                  },
                ]
              },
              {
                name: 'Allotment/DeAllotment', path: './allot-deallot', children: [
                  {
                    name: 'Create Allotment/DeAllotment', path: './create', children: []
                  },
                  {
                    name: 'Deallotment', path: './deallot', children: [
                      { name: '', path: '/:allotmentId' }
                    ]
                  },
                  {
                    name: 'View Allotment', path: './view', children: [
                      { name: '', path: '/:viewId' }
                    ]
                  }
                ]
              },
              {
                name: 'Delivery Challan', path: './delivery-challan', children: [
                  {
                    name: 'Create Delivery Challan', path: './create', children: []
                  },
                  {
                    name: 'View DC', path: './view', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  },
                  {
                    name: 'Update DC', path: './update', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  },
                  {
                    name: 'Cancel DC', path: './cancel', children: [
                      {
                        name: '', path: '/:id', children: []
                      }
                    ]
                  }
                ]
              },
              {
                name: 'Invoice', path: './invoice', children: [
                  {
                    name: 'Create Invoice', path: './create', children: []
                  },
                  {
                    name: 'Cancel Invoice', path: './cancel', children: [
                      {
                        name: '', path: '/:id'
                      }
                    ]
                  },
                  {
                    name: '', path: './view', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Branch Transfer', path: './branch-transfer', children: [
              {
                name: 'Branch Transfer Request', path: './branch-transfer-request', children: [
                  {
                    name: 'Create Branch Transfer Request', path: './create', children: []
                  }
                ]
              },
              {
                name: 'Branch Transfer Issue', path: './branch-transfer-issue', children: [
                  {
                    name: 'Create Branch Transfer Issue', path: './create', children: []
                  }
                ]
              },

              {
                name: 'Branch Transfer Receipt', path: './branch-transfer-receipt', children: [
                  {
                    name: 'Create Branch Transfer Receipt', path: './create', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Activity', path: './activity', children: [
              {
                name: 'Source Master', path: './source-master', children: []
              },

              {
                name: 'Marketing Activity Proposal', path: './activity-proposal', children: [
                  {
                    name: 'Create Marketing Activity Proposal', path: './create', children: []
                  },
                  {
                    name: 'View Marketing Activity Proposal', path: './view', children: []
                  },
                  {
                    name: 'Activity Proposal Approval', path: './approve', children: [
                      {
                        name: '', path: '/:actPrpId', children: []
                      }
                    ]
                  }
                ]
              },
              {
                name: 'Marketing Activity Approval', path: './activity-approval', children: [
                  {
                    name: 'Approval', path: './approve', children: []
                  },
                  {
                    name: 'View Marketing Activity Approval', path: './view', children: []
                  }
                ]
              },
              {
                name: 'Marketing Activity Report', path: './actual-report', children: [
                  {
                    name: 'Create Marketing Activity Report', path: './create', children: []
                  },
                  {
                    name: 'View Marketing Activity Report', path: './view', children: []
                  }
                ]
              },
              {
                name: 'Marketing Activity Claim', path: './activity-claim', children: [
                  {
                    name: 'Create Marketing Activity Claim', path: './create', children: []
                  },
                  {
                    name: 'View Marketing Activity Claim', path: './view', children: []
                  },
                  {
                    name: 'Claim Approval', path: './approve', children: [
                      {
                        name: '', path: '/:claimId', children: []
                      }
                    ]
                  }
                ]
              },
              {
                name: 'Marketing Activity Claim Approval', path: './activity-claim-approval', children: [
                  {
                    name: 'Create Marketing Activity Approval', path: './approve', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'Schemes', path: './schemes', children: [
              {
                name: 'Incentive Scheme Master', path: './incentive-scheme-master', children: [
                  {
                    name: 'Create Incentive Scheme Master', path: './create', children: []
                  },
                  {
                    name: 'View Incentive Scheme Master', path: './view', children: []
                  }
                ]
              },
              {
                name: 'Incentive Scheme Claim', path: './incentive-schemes-claim', children: [
                  {
                    name: 'Create Incentive Scheme Claim', path: './create', children: []
                  },
                  {
                    name: 'View Incentive Scheme Claim', path: './view', children: []
                  }
                ]
              }
            ]
          }
        ]
      },
      {
        name: 'Master', path: './master', children: [
          {
            name: 'itldis Masters', path: './itldismasters', children: [
              {
                name: 'Item Master', path: './item-master', children: []
              },
              {
                name: 'Department Master', path: './department-master', children: [

                  {
                    name: 'Create Department Master', path: './create', children: []
                  },
                  {
                    name: 'View Department Master', path: './view', children: []
                  }
                ]
              },
              {
                name: 'Employee Master', path: './employee-master', children: [

                  {
                    name: 'Create Employee Master', path: './create', children: []
                  },
                  {
                    name: 'View Employee Master', path: './view', children: []
                  },
                  {
                    name: 'Update Employee Master', path: './update', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'SalesMasters', path: './salesmasters', children: [
              {
                name: 'Update Vehicle Registration No.', path: './updatevehicleregistration', children: [
                  {
                    name: 'Create Update Vehicle Registration No.', path: './create', children: []
                  }
                ]
              },
              {
                name: 'KAI Activity Type Budget Master', path: './activityTypeBudget', children: []
              },
            ]
          },
          {
            name: 'Spares Master', path: './spare-masters', children: [
              {
                name: 'Store Master', path: './store-master', children: [
                  {
                    name: 'Create Store Master', path: './create', children: []
                  }
                ]
              },
              {

              },

            ]
          }
        ]
      },
      {
        name: 'Support', path: './support', children: [
          {
            name: 'Create Ticket', path: './create', children: []
          },
          // {
          //   name: 'View Ticket', path:'' ,children:[]
          // }
        ]
      },
      {
        name: 'Service', path: './service', children: [

          {
            name: 'Pre Sales Service', path: './pre-sales-service', children: [


              {
                name: 'Product Storage Check Sheet', path: './product-storage-checksheet', children: [
                  {
                    name: 'Create Product Storage Check Sheet', path: './create', children: []
                  },
                  {
                    name: 'View Product Storage Check Sheet', path: './view', children: []
                  },
                  {
                    name: 'Edit Product Storage Check Sheet', path: './edit', children: []
                  },
                ]
              },

              {
                name: 'Machine Receipt Checking', path: './machine-receipt-checking', children: [
                  {
                    name: 'Create Machine Receipt Checking', path: './create', children: []
                  },
                  {
                    name: 'View Machine Receipt Checking', path: './view', children: []
                  },
                  {
                    name: 'Edit Machine Receipt Checking', path: './edit', children: []
                  }
                ]
              },

              {
                name: 'Pre-Delivery Checklist', path: './pre-delivery-checklist', children: [
                  {
                    name: 'Create Pre-Delivery Checklist', path: './create', children: []
                  },
                  {
                    name: 'View Pre-Delivery Checklist', path: './view', children: []
                  },
                  {
                    name: 'Edit Pre-Delivery Checklist', path: './edit', children: []
                  },
                ]
              },

              {
                name: 'Pre Delivery Inspection Search', path: './pre-delivery-inspection', children: [
                  {
                    name: 'Create Pre Delivery Inspection', path: './create'
                  },
                  {
                    name: 'View Pre Delivery Inspection', path: './view'
                  },
                  {
                    name: 'Edit Pre Delivery Inspection', path: './edit'
                  },
                ]
              },

              {
                name: 'Installation', path: './installation', children: [
                  {
                    name: 'Create Installation', path: './create', children: []
                  },
                  {
                    name: 'View Installation', path: './view', children: []
                  },
                  {
                    name: 'Edit Installation', path: './edit', children: []
                  },
                ]
              },

              {
                name: 'Re-Installation', path: './re-installation', children: [
                  {
                    name: 'Create Re-Installation', path: './create', children: []
                  },
                  {
                    name: 'View Re-Installation', path: './view', children: []
                  },
                  {
                    name: 'Edit Re-Installation', path: './edit', children: []
                  },
                ]
              },

            ]

          },
          {
            name: 'Customer Service', path: './customerService', children: [
              {
                name: 'Job Card Search', path: './job-card', children: [
                  {
                    name: 'Create Job Card', path: './create'
                  },
                  {
                    name: 'View Job Card', path: './view'
                  },
                  {
                    name: 'Job Card For PDI', path: './pdi'
                  },
                  {
                    name: 'Job Card For PDC', path: './pdc'
                  },
                  {
                    name: 'Job Card For Edit', path: './edit'
                  },
                ]
              },
              {
                name: 'Service Booking', path: './service-booking', children: [
                  {
                    name: 'Create Service Booking', path: './create', children: [],
                  },
                  {
                    name: 'View Service Booking', path: './view', children: [],
                  },
                  {
                    name: 'Edit Service Booking', path: './edit', children: [],
                  },
                ]
              }
            ]
          },
          {
            name: 'Service Activities Claims', path: './service-activities-claims', children: [
              {
                name: 'Service Activity /Training Proposal', path: './service-activity-training-proposal', children: [
                  {
                    name: 'Create Service Activity /Training Proposal', path: './create'
                  },
                  {
                    name: 'View Service Activity /Training Proposal', path: './view'
                  },
                ]
              }
            ]
          },

        ]
      },
      {
        name: 'Spares', path: './spares', children: [
          {
            name: 'Purchase', path: './purchase', children: [
              {
                name: 'Spares Purchase Order', path: './sparespurchaseorder', children: [
                  { name: 'Create Spares PO', path: './create', children: [] },
                  { name: 'View Spares PO', path: './view', children: [] },
                  { name: 'Update Spares PO', path: './update', children: [] }
                ]
              },
              {
                name: 'Goods Receipt Note', path: './goods-receipt-note', children: [
                  {
                    name: 'Create GRN', path: './create', children: [],
                  },
                  { name: 'View GRN', path: './view', children: [] },
                  { name: 'Update GRN', path: './update', children: [] }
                ]
              },
              {
                name: 'Machine Descripancy Complaint', path: './machine-descripancy-complaint', children: [
                  {
                    name: 'Create Machine Descripancy Complaint', path: './create', children: []
                  },
                  {
                    name: 'View Machine Descripancy Complaint', path: './view', children: []
                  },
                  {
                    name: 'Edit Machine Descripancy Complaint', path: './edit', children: []
                  }
                ]
              },
              {
                name: 'Machine Descripancy Claim', path: './descripancy-claim', children: [
                  {
                    name: 'Create Machine Descripancy Claim', path: './create', children: []
                  },
                  {
                    name: 'View Machine Descripancy Claim', path: './view', children: []
                  },
                  {
                    name: 'Edit Machine Descripancy Claim', path: './edit', children: []
                  },
                  {
                    name: 'Approve Machine Descripancy Claim', path: './approve', children: []
                  }
                ]
              },
              {
                name: 'Machine Transfer Request', path: './machine-transfer-request', children: [
                  {
                    name: 'Create Machine Transfer Request', path: './create', children: []
                  },
                  {
                    name: 'View Machine Transfer Request', path: './view', children: []
                  }
                ]
              },


            ]
          },
          {
            name: 'Counter Sales', path: './countersales', children: [
              {
                name: 'Part Quotation', path: './partquotation', children: [
                  {
                    name: 'Create Part Quotation', path: './create', children: [],
                  },
                  { name: 'View Part Quotation', path: './view', children: [] },
                  { name: 'Update Part Quotation', path: './edit', children: [] }
                ]
              },
              {
                name: 'Customer Order', path: './customerorder', children: [
                  { name: 'Customer Order Create', path: './create', children: [] },
                  {
                    name: 'Update Customer Order', path: './edit', children: [
                      {
                        name: '', path: ':id', children: []
                      }
                    ]
                  },
                  {
                    name: 'View Customer Order', path: './view', children: [
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
            name: 'Workshop Sales', path: './workshop-sales', children: [
              {
                name: 'Part Requisition', path: './part-requisition', children: [
                  { name: 'Create', path: './create', children: [] },
                  { name: 'View', path: './view', children: [] },
                  { name: 'Update', path: './update', children: [] }
                ]
              },
              {
                name: 'Sales Invoice', path: './salesinvoice', children: [
                  {
                    name: 'Sales Invoice Create', path: './create', children: []
                  },
                  { name: 'View Sales Invoice', path: './view', children: [] },
                ]

              },
            ]
          },

        ]
      },

      {
        name: 'Warranty', path: './warranty', children: [

          {
            name: 'Warranty Claims', path: './warranty-claim', children: [

              {
                name: 'Product Concern Report Search', path: './product-concern-report', children: [
                  {
                    name: 'Create Product Concern Report', path: './create', children: [],
                  },
                  {
                    name: 'View Product Concern Report', path: './view', children: [],
                  },
                  {
                    name: 'Edit Product Concern Report', path: './edit', children: [],
                  },
                ]
              },
              {
                name: 'Log Sheet Search', path: './log-sheet', children: [
                  {
                    name: 'Create Log Sheet', path: './create', children: [],
                  },
                  {
                    name: 'View Log Sheet', path: './view', children: [],
                  },
                  {
                    name: 'Edit Log Sheet', path: './edit', children: [],
                  },
                ]
              },
              {
                name: 'Warranty Claim Request Search', path: './warrenty-claim-request', children: [
                  {
                    name: 'Create Warranty Claim Request', path: './create', children: [],
                  }

                ]
              },
              {
                name: 'WCR Report Search', path: './wcr-report', children: [
                  {
                    name: 'Create WCR Report', path: './create', children: [],
                  }

                ]
              },
              {
                name: 'Goodwill Search', path: './goodwill', children: [
                  {
                    name: 'Create Goodwill', path: './create', children: [],
                  }

                ]
              },
              {
                name: 'KAI Inspection Sheet Search', path: './kai-inspection-sheet', children: [
                  {
                    name: 'Create KAI Inspection Sheet', path: './create', children: [],
                  }

                ]
              },
              {
                name: 'Retro Fitment Campaign Search', path: './retro-fitment-campaign', children: [
                  {
                    name: 'Create Retro Fitment Campaign', path: './create', children: [],
                  }
                ]
              },
              {
                name: 'Hotline Report Search', path: './hotline-report', children: [
                  {
                    name: 'Create Hotline Report', path: './create', children: [],
                  }
                ]
              },

            ]

          },



        ]
      },
      {
        name: 'Training', path: './training', children: [

            {
                
                name: 'Training Programme Calender', path: './training-programme-calender', children: [
                    {
                        name: 'Create', path: './create', children: [],
                    },
                    {
                        name: 'View', path: './view', children: [],
                    },
                    {
                        name: 'Edit', path: 'jobCard', children: [],
                    },
                ]
                
            },
            {
                
                name: 'Training Nomination', path: 'training-nomination', children: [
                    {
                        name: 'Create', path: './create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }
                ]
                
            },
            {
                name: 'Attendance Sheet', path: './attendance-sheet', children: [
                    {
                        name: 'Create', path: './create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }

                ]
            },
            {
                name: 'Training Nomination Approval', path: './training-nomination-approval', children: [
                    {
                        name: 'Create', path: './create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }

                ]
            },
        ]
    }
    ];
    const newMenu = [
      {
        name: 'Dashboard',
        path: 'dashboard',
        children: []
      },
      {
        name: 'Support', path: 'support', children: [
          {
            name: 'Create Ticket', path: 'create', children: []
          },
          // {
          //   name: 'View Ticket', path:'' ,children:[]
          // }
        ]
      },
      ...masterRoutes,
      ...salePreSaleRoutes,
      ...serviceRoutes,
      ...spareRoutes,
      ...warrantyRoutes,
      ...trainingRoutes
    ];
    

    return newMenu;
  }

  addMenu(name, path) {
    return { name: name, path: path, children: [] }
  }

}















// name: 'Masters', path: './master', children:[
//   {
//   name: 'itldis Masters', path: './itldismasters', children: [
//     {
//     name: 'Item Master', path: './item-master', children: []