import { AuthGuard } from '../auth/auth.guard';

export const spareRoutes = [
   
    {
        name: 'Purchase', path: 'purchase', children: [
            {
                name: 'Spares Purchase Order', path: 'sparespurchaseorder', children: [
                    { name: 'Create Spares PO', path: 'create', children: [] },
                    { name: 'View Spares PO', path: 'view', children: [] },
                    { name: 'Update Spares PO', path: 'update', children: [] },
                    { name: 'Approve Spares PO', path: 'approve', children: [] }
                ]
            },
            {
                name: 'Block Part Sale', path: 'block-part-sale', children: [
                ]
            },
            // Router For Order Planning Sheet
            {
                name: 'Order Planning Sheet', path: 'order-planning-sheet', children: [
                    {name: 'Create Order Planning Sheet', path: 'create', children: []},
                    {name: 'Update Order Planning Sheet', path: 'edit', children: []},
                    {name: 'View Order Planning Sheet', path: 'view', children: []}
                ]
            },
            // Router for back order cancellation
            {
                name: 'Back Order Cancellation', path: 'back-order-cancellation', children: [
                    {name: 'Create Back Order Cancellation', path: 'create', children: []},
                    {name:'Edit Back Order Cancellation',path:'edit',children:[]},
                    {name:'View Back Order Cancellation',path:'view',children:[]},
                    
                ]
            },
            // Router for back order cancellation Approval
            {
                name: 'Back Order Cancellation Approval', path: 'back-order-cancellation-approve', children: [
                    {name: 'Back Order Cancellation Approval Page', path: 'approve', children: []},
                    {name: 'Back Order Cancellation View Page', path: 'view', children: []},
                ]
            },
            // Router Order Planning Sheet
           

            // Spare Descripancy claim 

            {
                name: 'Spare Descripancy Claim', path: 'discrepancy-mmr-claim', children: [
                    {name: 'Create Spare Descripancy Claim', path: 'create', children: []},
                    {name:'Edit Spare Descripancy Claim',path:'edit',children:[]},
                    {name:'View Spare Descripancy Claim',path:'view',children:[]},
                    {name:'Approve Spare Descripancy Claim',path:'approve',children:[]},
                ]
            },
            {
                name: 'Goods Receipt Note', path: 'goods-receipt-note', children: [
                    {
                        name: 'Create GRN', path: 'create', canActivate: [AuthGuard], children: [],
                    },
                    {
                        name: 'View GRN', path: 'view', children: [
                            { name: '', path: ':viewId', canActivate: [AuthGuard], children: [] }
                        ]
                    },
                    {
                        name: 'Update GRN', path: 'update', children: [
                            { name: '', path: ':updateId', canActivate: [AuthGuard], children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Machine Descripancy Complaint', path: 'machine-descripancy-complaint', children: [
                    {
                        name: 'Create Machine Descripancy Complaint', path: 'create', children: []
                    },
                    {
                        name: 'View Machine Descripancy Complaint', path: 'view', children: []
                    },
                    {
                        name: 'Edit Machine Descripancy Complaint', path: 'edit', children: []
                    }
                ]
            },
            {
                name: 'Machine Descripancy Claim', path: 'descripancy-claim', children: [
                    {
                        name: 'Create Machine Descripancy Claim', path: 'create', children: []
                    },
                    {
                        name: 'View Machine Descripancy Claim', path: 'view', children: []
                    },
                    {
                        name: 'Edit Machine Descripancy Claim', path: 'edit', children: []
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
                        name: 'View Machine Transfer Request', path: 'view', children: []
                    }
                ]
            },
             {
                name: 'Search Transit Details', path: 'transit-details', children: [
                    { name: '', path: ':id', children: [] 
                    
                   },
                    {
                        name: 'View', path: 'view', children: [] 
                    },
                    
                ]
            },
        ]
    },
    {
        name: 'Counter Sales', path: 'countersales', children: [
            {
                name: 'Sales Invoice', path: 'salesinvoice', children: [
                    {
                        name: 'Sales Invoice Create', path: 'create', children: []
                    },
                    {
                        name: 'View Sales Invoice', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Update Sales Invoice', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]

            },
            {
                name: 'Part Quotation', path: 'partquotation', children: [
                    {
                        name: 'Create Part Quotation', path: 'create', children: [],
                    },
                    {
                        name: 'View Part Quotation', path: 'view', children: [{
                            name: '', path: ':id', children: []
                        }]
                    },
                    {
                        name: 'Update Part Quotation', path: 'edit', children: [{
                            name: '', path: ':id', children: []
                        }]
                    }
                ]
            },
            {
                name: 'Customer Order', path: 'customerorder', children: [
                    {
                        name: 'Customer Order Create', path: 'create', children: []
                    },
                    {
                        name: 'View Customer Order', path: 'view', children: [
                            {
                                name: '', path: ':id', children: []
                            }
                        ]
                    },
                    {
                        name: 'Update Customer Order', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Picking List', path: 'picklist', children: [
                    {
                        name: 'Picking List Create', path: 'create', children: []
                    },
                    {
                        name: 'View Picking List', path: 'view', children: [
                            {
                                name: '', path: ':id', children: []
                            }
                        ]
                    },
                    {
                        name: 'Picking List Return', path: 'edit', children: [
                            {
                                name: '', path: ':id', children: []
                            }
                        ]
                    }
                ]
            },
            {
                name: 'Payment Receipt', path: 'paymentreceipt', children: [
                    {
                        name: 'Payment Receipt Create', path: 'create', children: []
                    },
                    {
                        name: 'View Payment Receipt', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                ]

            },
            {
                name: 'Salse Pick List', path: 'picklist', children: [
                    {
                        name: 'Pick List Create', path: 'create', children: []
                    },
                    {
                        name: 'View Pick List', path: 'view', children: [
                            {
                                name: '', path: ':id', children: []
                            }
                        ]
                    },
                    {
                        name: 'Salse Pick List', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
        ]
    },
    {
        name: 'Workshop Sales', path: 'workshop-sales', children: [
            {
                name: 'Part Requisition', path: 'part-requisition', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':viewId', children: [] }
                        ]
                    },
                    {
                        name: 'Update', path: 'update', children: [
                            { name: '', path: ':updateId', children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Part Issue', path: 'part-issue', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':viewId', children: [] }
                        ]
                    },
                    {
                        name: 'Update', path: 'update', children: [
                            { name: '', path: ':updateId', children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Part Return', path: 'part-return', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':viewId', children: [] }
                        ]
                    },
                    {
                        name: 'Update', path: 'update', children: [
                            { name: '', path: ':updateId', children: [] }
                        ]
                    }
                ]
            },

        ]
    },
    {
        name: 'Inventory Management', path: 'inventorymanagement', children: [
            {
                name: 'Spares Bin to Bin Transfer', path: 'bintobintransfer', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Edit', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            // Route for branch indent route
            {
                name: 'Search Branch Transfer Indent', path: 'branch-transfer-indent', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Edit', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            // Branch Transfer Issue Route
            {
                name: 'Search Branch Transfer Issue', path: 'branch-transfer-issue', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Edit', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            // Route for branch transfer reciept
            {
                name: 'Search Branch Transfer Reciept', path: 'branch-transfer-receipt', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Edit', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Spares Stock Adjustment', path: 'stockadjustment', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Approve', path: 'approve', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            },
            {
                name: 'Current Stock', path: 'currentstock', children: [
                    { name: 'Create', path: 'create', children: [] },
                    {
                        name: 'View', path: 'view', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    },
                    {
                        name: 'Edit', path: 'edit', children: [
                            { name: '', path: ':id', children: [] }
                        ]
                    }
                ]
            }
        ]
    },
    {
        name: 'Reports', path: 'reports', children: [
            {
                name: 'Closing Stock Report', path: 'closingStockReport', children: []
            },
            {
                name: 'Non Moving Parts Stock Report', path: 'nonMovingPartsStockReport', children: []
            },
            {
                name: 'Back Order Parts Report', path: 'backOrderPartsReport', children: []
            },
            {
                name: 'Item Movement Report', path: 'itemMovementReport', children: []
            },
            {
                name: 'Inventory Movement Report', path: 'inventoryMovementReport', children: []
            }
        ]
    }
];
