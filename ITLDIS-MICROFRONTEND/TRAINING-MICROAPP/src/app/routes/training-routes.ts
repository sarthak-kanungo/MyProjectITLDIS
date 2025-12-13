import { UserAccessGuard } from '../auth/user-access.guard';

export const trainingRoutes = [
    {
        name: 'Training', path: 'training', children: [

            {
                name: 'Training Program Calendar', path: 'training-program-calendar', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    },
                    {
                        name: 'Edit', path: 'edit', children: [],
                    }

                ]
            },
            {
                name: 'Training Nomination', path: 'training-nomination', children: [
                    {
                        name: 'Create', path: 'create', children: [],
                    },
                    {
                        name: 'View', path: 'view', children: [],
                    },
                    {
                        name: 'Edit', path: 'edit', children: [],
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
                    },
                    {
                        name: 'Edit', path: 'edit', children: [],
                    }
                ]
            },
            {
                name: 'Training Program Report', path: 'training-program-report', children: [
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