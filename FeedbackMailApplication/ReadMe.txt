Mail Trigger API- (http://localhost:8085/fms/mail/)
-----------------------------------------------------
1. POST http://localhost:8085/fms/mail/participated
{
	"emailDetails":[
	   {
	      "empMailId":"dummy@gmail.com",
	      "empName":"Jayakannan",
	      "subject":"Sending Email with Freemarker HTML Template Example",
	      "message":"Feedback request for",
	      "feedbackType":"Participated",
	      "eventName":"Cognizant Event",
	      "eventDate": "02/04/2020",
	      "questions":null
	   },
	   {
	      "empMailId":"dummy@gmail.com",
	      "empName":"Karuppasamy",
	      "subject":"Sending Email with Freemarker HTML Template Example",
	      "message":" Feedback request for ",
	      "feedbackType":"Participated",
	      "eventName":"Cognizant Event",
	      "eventDate": "02/04/2020",
	      "questions":null
	   }
	]
}
