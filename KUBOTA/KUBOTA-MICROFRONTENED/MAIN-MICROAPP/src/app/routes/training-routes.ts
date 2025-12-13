import { UserAccessGuard } from '../auth/user-access.guard';

export const trainingRoutes = [
    {
        name: 'Training', path: 'training', children: [

            {
                
                name: 'Training Programme Calender', path: 'training-programme-calender', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    },
                    {
                        name: 'Edit', path: 'jobCard', children: [],
                    },
                ]
                
            },
            {
                
                name: 'Training Nomination', path: 'training-nomination', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }
                ]
                
            },
            {
                name: 'Attendance Sheet', path: 'attendance-sheet', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }

                ]
            },
            {
                name: 'Training Nomination Approval', path: 'training-nomination-approval', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    }

                ]
            },
        ]
    }
]