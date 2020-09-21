FeedbackManagementApplication
-----------------------------
Feedback Management System, as the name suggests is used to manage the Cognizant Outreach event feedbacks from the participants. 
This includes automated real-time processing of the feedbacks and respond to participants with an email response for their valuable feedbacks. 

application.properties
=======================
Port=8082


Role Creation by Admin (http://localhost:8082/admin/role/)
===============================================================
1. GET : http://localhost:8082/admin/role/all
2. GET : http://localhost:8082/admin/role/1
3. POST : http://localhost:8082/admin/role/createRole
4. PUT : http://localhost:8082/admin/role/updateRole/1 - update only ROLE
5. DELETE : http://localhost:8082/admin/role/deleteEmpId/3
Request:
{
"empRoleId":"4",
"employeeId":"4",
"empFirstName":"jayakannan",
"empLastName":"karuppasamy",
"empEmailId":"856370@cognizant.com",
"empRole":"poc"
}

Feedback Question and Answer by Admin (http://localhost:8082/admin/FeadbackQnA/)
=================================================================================
1. http://localhost:8082/admin/FeadbackQnA/all
2. http://localhost:8082/admin/FeadbackQnA/1 ----------need to create one more service for FeebackType
3. http://localhost:8082/admin/FeadbackQnA/createQnA
4. http://localhost:8082/admin/FeadbackQnA/update/1
5. http://localhost:8082/admin/FeadbackQnA/delete/3

Request:
Question 1:
{
   "questionId":1,
   "question":"FirstQuestion?",
   "feedbackType":"participated",
   "questionType":"Allow Multiple Answer",
   "feedbackAnswer":[
      {
         "answerId":1,
         "questionId":1,
         "answers":"First Answer for the first question."
      },
      {
         "answerId":2,
         "questionId":1,
         "answers":"Second Answer for the first question."
      }
   ]
}

Question 2:
{
   "questionId":2,
   "question":"SecondQuestion?",
   "feedbackType":"Unregistered",
   "questionType":"Free Text Answer",
   "feedbackAnswer":[
      {
         "answerId":1,
         "questionId":2,
         "answers":"First Answer for the second question."
      },
      {
         "answerId":2,
         "questionId":2,
         "answers":"Second Answer for the second question."
      }
   ]
}

Question 3:
{
   "questionId":3,
   "question":"ThirdQuestion?",
   "feedbackType":"Not Participated",
   "questionType":"Custome Question",
   "feedbackAnswer":[
      {
         "answerId":1,
         "questionId":3,
         "answers":"First Answer for the third question."
      },
      {
         "answerId":2,
         "questionId":3,
         "answers":"Second Answer for the third question."
      }
   ]
}


EmailParticipatedFeedback - (http://localhost:8082/email/participated/)
=======================================================================
1. http://localhost:8082/email/participated/all
2. http://localhost:8082/email/participated/2
3. http://localhost:8082/email/participated/save
4. http://localhost:8082/email/participated/update/2
5. http://localhost:8082/email/participated/delete/3
Request
{
	"participateId":1,
	"employeeId":1,
	"eventId":1,
	"rating":1,
	"activityLike":"Employee Feedback for ActivityLike",
	"activityImproved":"Employee Feedback for activityImproved"
}


EmailNotParticipatedFeedback - (http://localhost:8082/email/notparticipated)
==============================================================================
1. http://localhost:8082/email/notparticipated/all
2. http://localhost:8082/email/notparticipated/2
3. http://localhost:8082/email/notparticipated/save
4. http://localhost:8082/email/notparticipated/update/2
5. http://localhost:8082/email/notparticipated/delete/1
Request
{
	"notParticipateId":1,
	"employeeId":1,
	"eventId":1,
	"questionId":1,
	"answerId":1
}

EmailUnRegisteredFeedback - (http://localhost:8082/email/UnRegistered)
======================================================================
1. http://localhost:8082/email/UnRegistered/all
2. http://localhost:8082/email/UnRegistered/1
3. http://localhost:8082/email/UnRegistered/save
4. http://localhost:8082/email/UnRegistered/update/1
5. http://localhost:8082/email/UnRegistered/delete/1
Request
{
	"unRegisteredId":1,
	"employeeId":1,
	"eventId":1,
	"questionId":1,
	"answerId":1
}



