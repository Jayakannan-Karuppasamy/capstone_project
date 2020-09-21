<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;" bgcolor="#f2f2f2">
<form>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td align="center" style="padding: 40px 0 30px 0;">
               <p style="font-family:'Courier New';font-size:30px">Feedback request for ${message} ${eventName} on ${eventDate} ${empName}</p>
            </td>
        </tr>
        <tr bgcolor="#000099">
            <td style="padding: 40px 30px 40px 30px;">
			<table>
				<tr>
					<td colspan="4" ><label style="color:white; font-family:'Courier New';font-size:30px">How do you rate the overall event?</label></td>
				</tr>
				<tr>
					<td>
						<!--<input type="radio" class="custom-control-input" id="defaultInline1" value="1" name="inlineDefaultRadiosExample" style="display: none;" onclick="">-->
						<label class="custom-control-label" for="defaultInline1"></label>
						<image src="cid:/images/rate1.png" height="42" width="42" value="1"/>
					</td>
					<td>
						<!--<input type="radio" class="custom-control-input" id="defaultInline2" name="inlineDefaultRadiosExample">-->
						<label class="custom-control-label" for="defaultInline2"></label>
						<image src="cid:/rate2.png" height="42" width="42"/>
					</td>
					<td>
						<!--<input type="radio" class="custom-control-input" id="defaultInline3" name="inlineDefaultRadiosExample">-->
						<label class="custom-control-label" for="defaultInline3"></label>
						<image src="cid:/images/rate3.png" height="42" width="42"/>
					</td>
					<td>
						<!--<input type="radio" class="custom-control-input" id="defaultInline3" name="inlineDefaultRadiosExample">-->
						<label class="custom-control-label" for="defaultInline3"></label>
						<image src="cid:/images/rate4.png" height="42" width="42"/>
					</td>
					<td>
						<!--<input type="radio" class="custom-control-input" id="defaultInline3" name="inlineDefaultRadiosExample">-->
						<label class="custom-control-label" for="defaultInline3"></label>
						<image src="cid:/images/rate5.png" height="42" width="42"/>
					</td>
				</tr>
			</table>
            </td>
        </tr>
		<tr>
		<td style="padding: 40px 30px 40px 30px;">
			<table>
				<tr bgcolor="#000099">
					<td><label style="color:white; font-family:'Courier New';font-size:30px">What did you like about this volunteering activity? </label></td>
				</tr>
				<tr>
					<td>
						<textarea class="form-control" id="ans1" rows="4" cols="80"></textarea>
					</td>
				</tr>
			</table>	
		<td>			
		</tr>
		<tr>
		<td style="padding: 30px 30px 30px 30px;">
			<table>
				<tr bgcolor="#000099">
					<td><label style="color:white; font-family:'Courier New';font-size:30px"> What can be improved in this volunteering activity? </label></td>
				</tr>
				<tr>
					<td><textarea class="form-control" id="ans2" rows="4" cols="80"></textarea></td>
				</tr>
			</table>	
		</td>			
		</tr>
		<tr>
			<td><input type="reset" value="reset"/></td>
			<td><input type="Submit" value="Submit"/></td>
		</tr>
    </table>   
</form>	
</body>
</html>