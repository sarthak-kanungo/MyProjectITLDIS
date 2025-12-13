export const masterRoutes = [
    {
        name: 'Master', path: 'master', children: [
          {
            name: 'itldis Masters', path: 'itldismasters', children: [
              {
                name: 'Item Master', path: 'item-master', children: []
              },
              {
                name: 'Department Master', path: 'department-master', children: [

                  {
                    name: 'Create Department Master', path: 'create', children: []
                  },
                  {
                    name: 'View Department Master', path: 'view', children: []
                  }
                ]
              },
              {
                name: 'Employee Master', path: 'employee-master', children: [

                  {
                    name: 'Create Employee Master', path: 'create', children: []
                  },
                  {
                    name: 'View Employee Master', path: 'view', children: []
                  },
                  {
                    name: 'Update Employee Master', path: 'update', children: []
                  }
                ]
              }
            ]
          },
          {
            name: 'SalesMasters', path: 'salesmasters', children: [
              {
                name: 'Update Vehicle Registration No.', path: 'updatevehicleregistration', children: [
                  {
                    name: 'Create Update Vehicle Registration No.', path: 'create', children: []
                  }
                ]
              },
              {
                name: 'KAI Activity Type Budget Master', path: 'activityTypeBudget', children: []
              },
            ]
          },
          {
            name: 'Spares Master', path: 'spare-masters', children: [
              {
                name: 'Store Master', path: 'store-master', children: [
                  {
                    name: 'Create Store Master', path: 'create', children: []
                  }
                ]
              }

            ]
          }
        ]
      }
]