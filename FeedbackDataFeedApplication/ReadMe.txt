FeedbackDataFeedApplication
------------------------------
Read and write the data from Excel at the system specified path.
The system will automatically look for events details and its participant’s details on at every 2nd minute.
List of below excel data are available in MONGO database after executed the FeedbackDataFeedApplication.
1. OutReach Event Information 
2. Volunteer_EnrollmentDetails_Not_Attend
3. Volunteer_EnrollmentDetails_Unregistered
4. Outreach Events Summary

Input files are configured in FeedbackDataFeedApplication (application.properties) property files.
-------------------------------------------------------------------------
event.information.file-location=D:\\856370\\Events\\OutReach Event Information.xlsx
event.information.output-file-location=D:\\856370\\Events\\Processed
event.information.should-move-file=true

event.summary.file-location=D:\\856370\\Events\\Outreach Events Summary.xlsx
event.summary.output-file-location=D:\\856370\\Events\\Processed
event.summary.should-move-file=true

volunteer.enroll.notattend.file-location=D:\\856370\\Events\\Volunteer_Enrollment Details_Not_Attend.xlsx
volunteer.enroll.notattend.output-file-location=D:\\856370\\Events\\Processed
volunteer.enroll.notattend.should-move-file=true

volunteer.enroll.unregistered.file-location=D:\\856370\\Events\\Volunteer_Enrollment Details_Unregistered.xlsx
volunteer.enroll.unregistered.output-file-location=D:\\856370\\Events\\Processed
volunteer.enroll.unregistered.should-move-file=true