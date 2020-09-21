Event Management : http://localhost:8092/email/preparation
1. GET http://localhost:8092/email/preparation/all
2. GET http://localhost:8092/email/preparation/22
Response:
{
    "outReachEventInformId": 29264718351386004302786609687,
    "eventID": "EVNT00046103",
    "baseLocation": null,
    "beneficiaryName": null,
    "councilName": null,
    "eventName": "Be a Teacher @ Kamarajar Illam",
    "eventDescription": null,
    "eventDate": "2018-11-30T18:30:00.000+0000",
    "employeeID": 22,
    "employeeName": "Name 22",
    "volunteerHours": null,
    "travelHours": null,
    "livesImpacted": null,
    "businessUnit": null,
    "status": null,
    "iiepCategory": null
}


Event Management Report : http://localhost:8092/reports/Registered/
1. GET http://localhost:8092/reports/Registered/all
2. GEt http://localhost:8092/reports/Registered/viewEvent
Request:
{
        "outReachEventInformId": null,
        "eventID": null,
        "baseLocation": null,
        "beneficiaryName": null,
        "councilName": null,
        "eventName":null,
        "eventDescription": null ,
        "eventDate": null,
        "employeeID": 1,
        "employeeName": null,
        "volunteerHours": null,
        "travelHours": null,
        "livesImpacted": null,
        "businessUnit": "Banking & Financial Services",
        "status": null,
        "iiepCategory": null
}


3. GET http://localhost:8092/reports/Registered/eventCount
Response : 7


Event Registration(http://localhost:8092/event/Registered)
----------------------------------------------------------
GET: http://localhost:8092/event/Registered/all
GET: http://localhost:8092/event/Registered/22
PUT: http://localhost:8092/event/Registered/updateEvent/22
DELTE: http://localhost:8092/event/Registered/delete/22
POST: http://localhost:8092/event/Registered/save
Request
{
   
    "eventId": "EVNT00046103",
    "eventName": "Be a Teacher @ Kamarajar Illam3",
    "beneficiaryName": "Kamarajar Illam,Tambaram",
    "baseLocation": "Chennai",
    "eventDate": 1544812200000,
    "employeeId": "221"
}
