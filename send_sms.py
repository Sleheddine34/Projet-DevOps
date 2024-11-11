from twilio.rest import Client

account_sid = 'AC981104b902f5215a079a84abff2a8919'
auth_token = 'ebaa79e92a33472845bbeb8bf156de72'  # Replace with your Twilio Auth Token
client = Client(account_sid, auth_token)

message = client.messages.create(
    body="Build Failed: Your DevOps project build was failed.",  # Your message
    from_='whatsapp:+14155238886',  # Your Twilio WhatsApp number
    to='whatsapp:+21694220012'  # Recipient's WhatsApp number
)

print(message.sid)  # Prints the message SID
