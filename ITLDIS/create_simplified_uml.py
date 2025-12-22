#!/usr/bin/env python3
"""Create a simplified UML diagram that will definitely work"""

mermaid_code = """classDiagram
    class serviceAction {
        +methods
    }
    class serviceOptionsAction {
        +methods
    }
    class serviceDAO {
        +methods
    }
    class serviceForm {
        +properties
    }
    class masterDAO {
        +methods
    }
    class ManageCustomerForm {
        <<abstract>>
    }
    class SWVehicleServiceSchedule
    class SWVehicleServiceFollowup
    class Servicetypemaster
    class Nextservicemaster
    class MSWServiceSchedule
    class SpOrderInvGrn
    class dbConnection
    class MethodUtility {
        <<utility>>
    }
    class HibernateUtil {
        <<utility>>
    }
    class commonUtilMethods {
        <<utility>>
    }
    class JSPViews {
        <<package>>
    }
    class DispatchAction {
        <<framework>>
    }
    class ActionForm {
        <<framework>>
    }
    class ActionMapping {
        <<framework>>
    }
    class Connection {
        <<JDBC>>
    }
    class HttpSession {
        <<servlet>>
    }
    class HttpServletRequest {
        <<servlet>>
    }
    class HttpServletResponse {
        <<servlet>>
    }
    
    serviceAction --> serviceDAO
    serviceAction --> serviceForm
    serviceAction --> dbConnection
    serviceAction --> MethodUtility
    serviceAction --> HibernateUtil
    serviceAction --> commonUtilMethods
    serviceOptionsAction --> serviceDAO
    serviceOptionsAction --> serviceForm
    serviceOptionsAction --> masterDAO
    serviceDAO --> serviceForm
    serviceDAO --> dbConnection
    serviceDAO --> HibernateUtil
    serviceDAO --> SWVehicleServiceSchedule
    serviceDAO --> SWVehicleServiceFollowup
    serviceDAO --> Servicetypemaster
    serviceDAO --> Nextservicemaster
    serviceDAO --> SpOrderInvGrn
    serviceForm --|> ManageCustomerForm
    SWVehicleServiceFollowup --> SWVehicleServiceSchedule
    serviceAction --> JSPViews
    serviceOptionsAction --> JSPViews
    serviceAction --|> DispatchAction
    serviceOptionsAction --|> DispatchAction
    serviceForm --|> ActionForm
    dbConnection --> Connection
    serviceAction --> HttpSession
    serviceOptionsAction --> HttpSession
    serviceAction --> HttpServletRequest
    serviceAction --> HttpServletResponse
    serviceOptionsAction --> HttpServletRequest
    serviceOptionsAction --> HttpServletResponse"""

# Write simplified version
with open('SERVICE-MODULE-UML-DIAGRAM-SIMPLE.md', 'w', encoding='utf-8') as f:
    f.write("# ITLDIS Service Module - UML Class Diagram (Simplified)\n\n")
    f.write("```mermaid\n")
    f.write(mermaid_code)
    f.write("\n```\n")

print("Created simplified diagram")

