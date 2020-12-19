import sys
import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart 
from email.mime.text import MIMEText

SMTP_SERVER = 'smtp.fcu.edu.tw' 
SMTP_PORT = 25

def send_email(sender, recipient, subject, message):
	# Create message
	msg = MIMEMultipart()
	msg['To'] = recipient
	msg['From'] = sender
	msg['Subject'] = subject
	part = MIMEText('text', "plain")
	part.set_payload(message)
	msg.attach(part)
	# create smtp session
	session = smtplib.SMTP(SMTP_SERVER, SMTP_PORT)
	session.ehlo()
	# send mail
	session.sendmail(sender, recipient, msg.as_string())
	session.quit()
	print("You email is sent to {0}.".format(recipient))
# end of send_email
	
def main():
	sender = input("Enter sender email address: ")
	recipient = input("Enter recipient email address: ")
	subject = input('Enter your email subject: ')
	message = input('Enter your email message. Press Enter when finished. ')
	send_email(sender, recipient, subject, message)
# end of main

if __name__ == '__main__':
	main()