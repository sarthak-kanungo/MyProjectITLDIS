import { AuthGuard } from '../auth/auth.guard';

export const spareRoutes = [
    {
        name: 'Purchase', path: 'purchase', children: [
            {
                name: 'Spares Purchase Order', path: 'sparespurchaseorder', children: [
                    { name: 'Create Spares PO', path: 'create', children: [] },
                    { name: 'View Spares PO', path: 'view', children: [] },
                    { name: 'Update Spares PO', path: 'update', children: [] }
                ]
            },
            {
                name: 'Goods Receipt Note', path: 'goods-receipt-note', children: [
                    {
                        name: 'Create GRN', path: 'create', canActivate: [AuthGuard], children: [],
                    },
                    {
                        name: 'View GRN', path: 'view/:viewId', canActivate: [AuthGuard], children: [
                        ]
                    },
                    { name: 'Update GRN', path: 'update/:updateId', canActivate: [AuthGuard], children: [] }
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
];
