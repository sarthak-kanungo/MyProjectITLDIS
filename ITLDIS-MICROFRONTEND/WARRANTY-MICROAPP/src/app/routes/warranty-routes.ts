import { UserAccessGuard } from '../auth/user-access.guard';

export const warrantyRoutes = [
    {
        name: 'Warranty Claims', path: 'warranty-claim', children: [

            {
                name: 'Product Concern Report Search', path: 'product-concern-report', children: [
                    {
                        name: 'Create Product Concern Report', path: 'create', children: [],
                    },
                    {
                        name: 'View Product Concern Report', path: 'view', children: [],
                    },
                    {
                        name: 'Create Product Concern Report', path: 'jobCard', children: [],
                    },
                    {
                        name: 'Edit Product Concern Report', path: 'edit', children: [],
                    },
                ]
            },
            {
                name: 'Log Sheet Search', path: 'log-sheet', children: [
                    {
                        name: 'Create Log Sheet', path: 'create', children: [],
                    },
                    {
                        name: 'View Log Sheet', path: 'view', children: [],
                    },
                    {
                        name: 'Edit Log Sheet', path: 'edit', children: [],
                    },
                ]
            },
            {
                name: 'Warranty Claim Request Search', path: 'warrenty-claim-request', children: [
                    {
                        name: 'Create Warranty Claim Request', path: 'create', children: [],
                    },
                    {
                        name: 'View Warranty Claim Request', path: 'view', children: [],
                    }

                ]
            },
            {
                name: 'WCR Report Search', path: 'wcr-report', children: [
                    {
                        name: 'Create WCR Report', path: 'create', children: [],
                    }

                ]
            },
            {
                name: 'Goodwill Search', path: 'goodwill', children: [
                    {
                        name: 'Create Goodwill', path: 'create', children: [],
                    },
                    {
                        name: 'View Goodwill', path: 'view', children: [],
                    }

                ]
            },
            {
                name: 'KAI Inspection Sheet Search', path: 'kai-inspection-sheet', children: [
                    {
                        name: 'Create KAI Inspection Sheet', path: 'create', children: [],
                    },
                    {
                        name: 'View KAI Inspection Sheet', path: 'view', children: [],
                    },
                    {
                        name: 'Approve KAI Inspection Sheet', path: 'approve', children: [],
                    }

                ]
            },
            {
                name: 'Retro Fitment Campaign Search', path: 'retro-fitment-campaign', children: [
                    {
                        name: 'Create Retro Fitment Campaign', path: 'create', children: [],
                    },
                    {
                        name: 'View Retro Fitment Campaign', path: 'view', children: [],
                    }
                ]
            },
            {
                name: 'Hotline Report Search', path: 'hotline-report', children: [
                    {
                        name: 'Create Hotline Report', path: 'create', children: [],
                    },
                    {
                        name: 'View Hotline Report', path: 'view', children: [],
                    },
                    {
                       name:'Submit Hotline Report',path:'edit',children:[],
                    }
                ]
            },
            {
                name: 'Hotline Report Search', path: 'hotline-report', children: [
                    {
                        name: 'Create Hotline Report', path: 'create', children: [],
                    }
                ]
            },
            {
                name: 'WCR Report Search', path: 'wcr-report', children: [
                    {
                        name: 'Create WCR Report', path: 'create', children: [],
                    },
                    {
                        name: 'View WCR Report', path: 'view', children: [],
                    }
                ]
            },
            {
                name: 'Warranty Parts Delivery Challan Search', path: 'warranty-delivery-challan', children: [
                    {
                        name: 'Create Warranty Parts Delivery Challan', path: 'create', children: [],
                    },
                    {
                        name: 'View Warranty Parts Delivery Challan', path: 'view', children: [],
                    },
                    {
                        name: 'Edit Warranty Parts Delivery Challan', path: 'edit', children: [],
                    },
                ]
            },{
                name: 'WCR Parts Final Status', path: 'wcr-final-status', children: [] 
            }

        ]

    }
]